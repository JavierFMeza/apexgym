package datos;

public class LogIn {
	private String id;
	private String usuario;
	private String contrasena;
	private String cedven;
	
	public LogIn(String id, String usuario, String contrasena, String cedven) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.cedven = cedven;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCedven() {
		return cedven;
	}

	public void setCedven(String cedven) {
		this.cedven = cedven;
	}

	

}