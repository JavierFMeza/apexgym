package datos;

public class Session {
	private static LogIn usuario;
	private static boolean isAdmin;

	public static LogIn getUsuario() {
		return Session.usuario;
	}

	public static void setUsuario(LogIn usuario) {
		Session.usuario = usuario;
	}

	public static boolean isAdmin() {
		return isAdmin;
	}

	public static void setAdmin(boolean isAdmin) {
		Session.isAdmin = isAdmin;
	}
	
	
	
}