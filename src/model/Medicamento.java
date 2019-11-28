package model;

import java.io.Serializable;

public abstract class Medicamento implements Serializable{
	
	private String id;
	private String codigo_atc;
	private String nombre;

	public Medicamento(String id,String codigo,String nombre) {
		this.id = id;
		this.codigo_atc =  codigo;
		this.nombre =  nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id =(id);
	}

	public String getCodigo_atc() {
		return codigo_atc;
	}

	public void setCodigo_atc(String codigo_atc) {
		this.codigo_atc =codigo_atc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
