package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Medicamento{
	
	private StringProperty id;
	private StringProperty codigo_atc;
	private StringProperty nombre;

	public Medicamento(String id,String codigo,String nombre) {
		this.id = new SimpleStringProperty(id);
		this.codigo_atc = new SimpleStringProperty(codigo);
		this.nombre = new SimpleStringProperty(nombre);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getCodigo_atc() {
		return codigo_atc.get();
	}

	public void setCodigo_atc(String codigo_atc) {
		this.codigo_atc.set(codigo_atc);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	
}
