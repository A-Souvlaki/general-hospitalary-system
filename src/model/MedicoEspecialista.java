package model;

import java.io.Serializable;

public class MedicoEspecialista extends Medico implements Serializable{
	
	private String medico_especialista;

	public MedicoEspecialista(String nombre_apellidos, String id, int no_licencia,String especialidad) {
		super(nombre_apellidos, id, no_licencia);
		this.medico_especialista = especialidad;
		
	}
	
	

	public String getMedico_especialista() {
		return medico_especialista;
	}



	public void setMedico_especialista(String medico_especialista) {
		this.medico_especialista = medico_especialista;
	}



	
	
	

}
