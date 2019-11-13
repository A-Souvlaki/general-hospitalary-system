package model;

public abstract class IPS {
	
	private double nit;
	private String nombre;
	private String direccion;
	
	public IPS(double nit, String nombre, String direccion) {
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public double getNit() {
		return nit;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}
	
	@Override
	public String toString() {
		String mensaje = "";
		mensaje += "\nIPS.";
		mensaje += "\nRegistro NIT: " + nit;
		mensaje += "\nNombre: " + nombre;
		mensaje += "\nDireccion: " + direccion;
		return mensaje;
	}

}
