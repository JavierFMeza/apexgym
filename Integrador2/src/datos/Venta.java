package datos;

public class Venta {
	private String id;
	private String nombre;
	private int precio;
	private String fecha;
	private String vendedor;
	private int cantidad;
	
	public Venta(String id, String nombre, int precio,String fecha, String vendedor, int cantidad){
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.vendedor = vendedor;
		this.cantidad = cantidad;
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	
}