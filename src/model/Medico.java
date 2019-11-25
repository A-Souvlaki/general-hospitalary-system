package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Medico {
	
	private StringProperty nombre_apellidos;
	private StringProperty id;
	private IntegerProperty no_licencia;

	private Cita principal;
	
	private Medico siguiente;
	
	public Medico(String nombre_a,String id,int no,Medico siguiente) {
		this.nombre_apellidos = new SimpleStringProperty(nombre_a);
		this.id = new SimpleStringProperty(id);
		this.no_licencia = new SimpleIntegerProperty(no);
		this.siguiente = siguiente;
	}
	
	public Medico(String nombre_a,String id,int no) {
		this.nombre_apellidos = new SimpleStringProperty(nombre_a);
		this.id = new SimpleStringProperty(id);
		this.no_licencia = new SimpleIntegerProperty(no);
		
	}

	public String getNombre_apellidos() {
		return nombre_apellidos.get();
	}

	public void setNombre_apellidos(String nombre_apellidos) {
		this.nombre_apellidos.set(nombre_apellidos);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public Integer getNo_licencia() {
		return no_licencia.get();
	}

	public void setNo_licencia(Integer no_licencia) {
		this.no_licencia.set(no_licencia);
	}

	public Cita getPrincipal() {
		return principal;
	}

	public void setPrincipal(Cita principal) {
		this.principal = principal;
	}

	public Medico getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Medico siguiente) {
		this.siguiente = siguiente;
	}
	
	
	
}
