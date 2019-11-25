package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedicoEspecialista extends Medico{
	
	private StringProperty medico_especialista;

	public MedicoEspecialista(String nombre_apellidos, String id, int no_licencia,String especialidad) {
		super(nombre_apellidos, id, no_licencia);
		this.medico_especialista = new SimpleStringProperty(especialidad);
		
	}
	
	

	public String getMedico_especialista() {
		return medico_especialista.get();
	}



	public void setMedico_especialista(String medico_especialista) {
		this.medico_especialista.set(medico_especialista);
	}



	
	
	

}
