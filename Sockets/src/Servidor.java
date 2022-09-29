import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Servidor {
	/// Hace 2 tareas : Escribe lo que llega por el socket y permanece a la escucha  
	public static void main(String...args) {
		MarcoServidor serv = new MarcoServidor();
		serv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serv.setVisible(true);
		
	}

}

class MarcoServidor extends JFrame implements Runnable{
	JTextArea areaTexto;
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
			while(true){
				Socket miSocket = servidor.accept();
				DataInputStream stream = new DataInputStream(miSocket.getInputStream());
				String mensaje = stream.readUTF();
				miSocket.close();
				this.areaTexto.append("\n" + mensaje);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
