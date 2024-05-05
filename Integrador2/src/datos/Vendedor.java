package datos;

public class Vendedor {
	private String cedula;
	private String nombre;
	private String fec_nac;
	private String telefono;
	
	public Vendedor(String cedula, String nombre, String fec_nac, String telefono) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.fec_nac = fec_nac;
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFec_nac() {
		return fec_nac;
	}

	public void setFec_nac(String fec_nac) {
		this.fec_nac = fec_nac;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
