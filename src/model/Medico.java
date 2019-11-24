package model;

public class Medico {
	
	private String nombre;
	private String apellido;
	private int edad;
	private String licencia;

	private Medico siguiente;
	
	private Cita citaPrincipal;
	
	public Medico(String nombre, String apellido, int edad, String licencia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.licencia = licencia;
		siguiente = null;
		citaPrincipal = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public Medico getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Medico siguiente) {
		this.siguiente = siguiente;
	}

	public Cita getCitaPrincipal() {
		return citaPrincipal;
	}

	public void setCitaPrincipal(Cita citaPrincipal) {
		this.citaPrincipal = citaPrincipal;
	}

	@Override
	public String toString() {
		String m = "";
		m += "\nNombre: " + nombre;
		m += "\nApellido: " + apellido;
		m += "\nEdad: " + edad;
		m += "\nLicencia: " + licencia;
		return m;
	}
	
}
