package model;

import java.util.ArrayList;

public class Clinica extends IPS{
	
	private ArrayList<String> servicios;
	
	private boolean especialidad;
	
	public Clinica(double nit, String nombre, String direccion,String tipo,int nivel,boolean especialidad) {
		super(nit, nombre, direccion,tipo,nivel);
		this.especialidad = especialidad;
		servicios = new ArrayList<String>();
	}

	public ArrayList<String> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<String> servicios) {
		this.servicios = servicios;
	}

	public boolean isEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(boolean especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Clinica [servicios=" + servicios + ", especialidad=" + especialidad + "]";
	}
	
	@Override
	public int compareTo(IPS o) {
		return super.getNombre().compareTo(o.getNombre());
	}
	
	
}
