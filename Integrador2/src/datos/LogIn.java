package datos;

public class LogIn {
    private String id;
    private String usuario;
    private String contrasena;
    private String profesion;

    public LogIn(String id, String usuario, String contrasena, String profesion) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.profesion = profesion;
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

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
