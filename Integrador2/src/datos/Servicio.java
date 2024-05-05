package datos;

public class Servicio {
	private String id;
	private String horario;
	private String fecha;
	private int horas;
	private String nombre;
	
	public Servicio(String id, String horario, String fecha, int horas, String nombre ){
		this.id = id;
		this.horario = horario;
		this.fecha = fecha;
		this.horas = horas;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
