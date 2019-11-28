package model;

import java.io.Serializable;

public abstract class Paciente implements Serializable {

	private String nombre_p;
	private String apellido_p;
	private String id_p;
	
	public Paciente(String nombre,String apellido,String id) {
		this.nombre_p = nombre;
		this.apellido_p = apellido;
		this.id_p = id;
	}

	public String getNombre_p() {
		return nombre_p;
	}

	public void setNombre_p(String nombre_p) {
		this.nombre_p = nombre_p;
	}

	public String getApellido_p() {
		return apellido_p;
	}

	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}

	public String getId_p() {
		return id_p;
	}

	public void setId_p(String id_p) {
		this.id_p = id_p;
	}
	
}
