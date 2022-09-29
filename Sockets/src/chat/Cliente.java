package chat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import javax.swing.*;

import modelos.PaqueteEnvio;

public class Cliente {
	
	public static void main(String...args) {
		MarcoCliente marcoCliente = new MarcoCliente();
		marcoCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marcoCliente.setVisible(true);
		
	}

}


class MarcoCliente extends JFrame {
	public MarcoCliente() {
		setBounds(600,300,280,350);
		add(new LaminaMarcoCliente());
		setVisible(true);
	}
}
class LaminaMarcoCliente extends JPanel implements ActionListener, Runnable{
	
	private JTextField campoMensaje, nickname, ip;
	private JTextArea campoChat;
	private JButton miBoton;
	
	public LaminaMarcoCliente() {
	
		nickname = new JTextField(8);
		add(nickname);
		
		JLabel texto = new JLabel("-CHAT-");
		add(texto);
		
		ip = new JTextField(8);
		add(ip);
		
		
		campoChat = new JTextArea(12,20);
		add(campoChat);
		
		/*
		campoMensaje =new JTextField(20);
		add(campoMensaje);
		*/
	
		miBoton = new JButton("Enviar");
		miBoton.addActionListener(this);
		add(miBoton);
		
		Thread miHilo = new Thread(this); 
		miHilo.start();
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(this.campoChat.getText());
		try {
			Socket socket = new Socket("192.168.8.46", 9999);
			
		/*	DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
		
			flujoSalida.writeUTF(campoMensaje.getText());
			
			campoMensaje.setText("");
			
			flujoSalida.close();
			
			socket.close();
		*/
			PaqueteEnvio paquete = new PaqueteEnvio(this.ip.getText(), this.campoChat.getText(), this.nickname.getText());
			
			ObjectOutputStream paqueteDatos = new ObjectOutputStream(socket.getOutputStream());
			
		//	System.out.println(paquete);
			paqueteDatos.writeObject(paquete);
			
			socket.close();
			
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		
	}






	@Override
	public void run() {
		try{
			ServerSocket servidorCliente = new ServerSocket(9090);
			
			Socket cliente;
			
			PaqueteEnvio paqueteRecibido;
			
			while(true) {
				cliente = servidorCliente.accept();
				
				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				
				paqueteRecibido = ( PaqueteEnvio ) flujoEntrada.readObject();
				
				campoChat.append("\n" + paqueteRecibido.getNickname() +": "+paqueteRecibido.getMensaje());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
