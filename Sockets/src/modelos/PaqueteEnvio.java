package modelos;

import java.io.Serializable;

public class PaqueteEnvio implements Serializable{
	
	private String ip, mensaje, nickname;

	
	@Override
	public String toString() {
		return "PaqueteEnvio [ip=" + ip + ", mensaje=" + mensaje + ", nickname=" + nickname + "]";
	}
	public PaqueteEnvio(String ip, String mensaje, String nickname) {
		this.ip = ip; 
		this.mensaje = mensaje;
		this.nickname = nickname;
	}
	public PaqueteEnvio() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the mansaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mansaje the mansaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

}
