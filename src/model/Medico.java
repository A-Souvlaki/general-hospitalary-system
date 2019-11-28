package model;

import java.io.Serializable;

public abstract class Medico implements Serializable{
	
	private String nombre_apellidos;
	private String id;
	private Integer no_licencia;

	
	
	private Medico siguiente;
	
	public Medico(String nombre_a,String id,int no,Medico siguiente) {
		this.nombre_apellidos = nombre_a;
		this.id =  id;
		this.no_licencia =  no;
		this.siguiente = siguiente;
	}
	
	public Medico(String nombre_a,String id,int no) {
		this.nombre_apellidos =nombre_a;
		this.id = id;
		this.no_licencia = no;
		
	}

	

	public String getNombre_apellidos() {
		return nombre_apellidos;
	}

	public void setNombre_apellidos(String nombre_apellidos) {
		this.nombre_apellidos = nombre_apellidos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNo_licencia() {
		return no_licencia;
	}

	public void setNo_licencia(Integer no_licencia) {
		this.no_licencia = no_licencia;
	}


	public Medico getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Medico siguiente) {
		this.siguiente = siguiente;
	}
	
	
	
}
