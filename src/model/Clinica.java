package model;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Clinica extends IPS{
	
	private ArrayList<String> servicios;
	
	private StringProperty especialidad;
	
	public Clinica(int nit, String nombre, String direccion,String tipo,int nivel,String especialidad) {
		super(nit, nombre, direccion,tipo,nivel);
		this.especialidad = new SimpleStringProperty(especialidad);
		servicios = new ArrayList<String>();
	}

	public ArrayList<String> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<String> servicios) {
		this.servicios = servicios;
	}

	public String isEspecialidad() {
		return especialidad.get();
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad.set(especialidad);;
	}

	@Override
	public String toString() {
		return super.toString();
		
	}
	
	@Override
	public int compareTo(IPS o) {
		return super.getNombre().compareTo(o.getNombre());
	}
	
	
}
