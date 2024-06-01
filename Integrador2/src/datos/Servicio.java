package datos;

public class Servicio {
    private String id;
    private String horario;
    private String fecha;
    private int horas;
    private String nombreEntrenador;
    private String nombreTipoServicio;
    private int precioHora;

    public Servicio(String id, String horario, String fecha, int horas, String nombreEntrenador, String nombreTipoServicio, int precioHora) {
        this.id = id;
        this.horario = horario;
        this.fecha = fecha;
        this.horas = horas;
        this.nombreEntrenador = nombreEntrenador;
        this.nombreTipoServicio = nombreTipoServicio;
        this.precioHora = precioHora;
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

	public String getNombreEntrenador() {
		return nombreEntrenador;
	}

	public void setNombreEntrenador(String nombreEntrenador) {
		this.nombreEntrenador = nombreEntrenador;
	}

	public String getNombreTipoServicio() {
		return nombreTipoServicio;
	}

	public void setNombreTipoServicio(String nombreTipoServicio) {
		this.nombreTipoServicio = nombreTipoServicio;
	}

	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(int precioHora) {
		this.precioHora = precioHora;
	}

}