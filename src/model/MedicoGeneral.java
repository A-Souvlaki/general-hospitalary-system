package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedicoGeneral extends Medico{
	
	private StringProperty independencia;

	public MedicoGeneral(String nombre_apellidos, String id, int no_licencia,String independencia) {
		super(nombre_apellidos, id, no_licencia);
		this.independencia = new SimpleStringProperty(independencia);
		
	}

	public String getIndependencia() {
		return independencia.get();
	}

	public void setIndependencia(String independencia) {
		this.independencia.set(independencia);
	}
	
	

}
