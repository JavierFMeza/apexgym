package datos;

public class Producto {
	private String id;
	private String nombre;
	private String detalles;
	private int precio;
	private String tipo;

	public Producto(String id, String nombre, int precio, String tipo, String detalles) {
		this.id = id;
		this.nombre = nombre;
		this.detalles = detalles;
		this.precio = precio;
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}