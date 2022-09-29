package chat;
import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modelos.PaqueteEnvio;

public class Servidor {
	/// Hace 2 tareas : Escribe lo que llega por el socket y permanece a la escucha  
	public static void main(String...args) {
		MarcoServidor serv = new MarcoServidor();
		serv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serv.setVisible(true);
		
	}

}

class MarcoServidor extends JFrame implements Runnable{
	private JTextArea areaTexto;
	public MarcoServidor() {
		setBounds(800,300,280,350);
		
		JPanel miLamina = new JPanel();
		miLamina.setLayout(new BorderLayout());
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		miLamina.add(areaTexto, BorderLayout.CENTER);	
		add(miLamina);
		Thread miHilo = new Thread(this);
		miHilo.start();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Estoy a la escucha");
		try {
			ServerSocket  servidor = new ServerSocket(9999);
			 
			PaqueteEnvio paqueteRecibido;
			
			while(true){
				
				Socket miSocket = servidor.accept();
				ObjectInputStream paqueteDatos = new ObjectInputStream(miSocket.getInputStream());
				paqueteRecibido = (PaqueteEnvio) paqueteDatos.readObject();
				
				
		//		System.out.println(paqueteRecibido);
				this.areaTexto.append("\nDe :  =>"+paqueteRecibido.getNickname());
				this.areaTexto.append("\nMensaje:  =>"+paqueteRecibido.getMensaje());
				this.areaTexto.append("\nPara:  =>"+paqueteRecibido.getIp());
				
				
				
				Socket enviarDestinatario = new Socket(paqueteRecibido.getIp(), 9090);
				System.out.println("para   "+ paqueteRecibido.getIp());
				ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviarDestinatario.getOutputStream());
				paqueteReenvio.writeObject(paqueteRecibido);
		
				
				
				paqueteReenvio.close();
				enviarDestinatario.close();
				miSocket.close();
				
			/*	DataInputStream stream = new DataInputStream(miSocket.getInputStream());
				String mensaje = stream.readUTF();
				miSocket.close();
				this.areaTexto.append("\n" + mensaje);
			*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
