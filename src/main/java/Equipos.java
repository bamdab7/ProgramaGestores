

public class Equipos {

	private int codEquipo;
	private String nombre;
	private String ciudad;
	private int anho;
	
	public Equipos() {
		super();
	}

	public Equipos(int codEquipo, String nombre, String ciudad, int anho) {
		super();
		this.codEquipo = codEquipo;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.anho = anho;
	}

	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getAnho() {
		return anho;
	}

	public void setAnho(int anho) {
		this.anho = anho;
	}
	
	
}
