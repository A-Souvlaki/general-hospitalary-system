package model;

import java.io.Serializable;

public class MedicoGeneral extends Medico implements Serializable{
	
	private String independencia;

	public MedicoGeneral(String nombre_apellidos, String id, int no_licencia,String independencia) {
		super(nombre_apellidos, id, no_licencia);
		this.independencia = independencia;
		
	}

	public String getIndependencia() {
		return independencia;
	}

	public void setIndependencia(String independencia) {
		this.independencia = independencia;
	}
	
	

}
