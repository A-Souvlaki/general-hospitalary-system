package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Paciente {

	private StringProperty nombre_p;
	private StringProperty apellido_p;
	private StringProperty id_p;
	
	public Paciente(String nombre,String apellido,String id) {
		this.nombre_p =  new SimpleStringProperty(nombre);
		this.apellido_p = new SimpleStringProperty(apellido);
		this.id_p = new SimpleStringProperty(id);
	}

	public String getNombre_p() {
		return nombre_p.get();
	}

	public void setNombre_p(String nombre_p) {
		this.nombre_p.set(nombre_p);
	}

	public String getApellido_p() {
		return apellido_p.get();
	}

	public void setApellido_p(String apellido_p) {
		this.apellido_p.set(apellido_p);
	}

	public String getId_p() {
		return id_p.get();
	}

	public void setId_p(String id_p) {
		this.id_p.set(id_p);
	}
	
}
