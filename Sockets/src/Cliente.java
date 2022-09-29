import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

public class Cliente {
	
	public static void main(String...args) {
		MarcoCliente marcoCliente = new MarcoCliente();
		marcoCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marcoCliente.setVisible(true);
		
	}

}


class MarcoCliente extends JFrame{
	public MarcoCliente() {
		setBounds(600,300,280,350);
		add(new LaminaMarcoCliente());
		setVisible(true);
	}
}
class LaminaMarcoCliente extends JPanel implements ActionListener{
	
	private JTextField campo1;
	
	private JButton miBoton;
	public LaminaMarcoCliente() {
		JLabel texto = new JLabel("CLIENTE");
		add(texto);
		campo1 =new JTextField(20);
		add(campo1);
		miBoton = new JButton("Enviar");
		miBoton.addActionListener(this);
		add(miBoton);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(this.campo1.getText());
		try {
			Socket socket = new Socket("127.0.0.1", 9999);
		//	Socket socket = new Socket("192.168.8.47", 9999);	
			DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
		
			flujoSalida.writeUTF(campo1.getText());
			
			flujoSalida.close();
			
			campo1.setText("");
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
	}
}
