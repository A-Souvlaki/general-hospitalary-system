package model;

public class MedicoEspecialista extends Medico{
	
	private String especialidad;
	
	public MedicoEspecialista(String nombre, String apellido, int edad, String licencia, String especialidad) {
		super(nombre, apellido, edad, licencia);
		this.especialidad = especialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		String m = "";
		m += super.toString();
		m += "\nEspecialidad: " + especialidad;
		return m;
	}
	
}
