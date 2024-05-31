package datos;

public class LogIn {
	private String id;
	private String usuario;
	private String contrasena;
	
	public LogIn(String id, String usuario, String contrasena) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		
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
	

}