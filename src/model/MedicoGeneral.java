package model;

public class MedicoGeneral extends Medico{
	
	private int numEstudiantes;
	
	public MedicoGeneral(String nombre, String apellido, int edad, String licencia, int numEstudiantes) {
		super(nombre, apellido, edad, licencia);
		this.numEstudiantes = numEstudiantes;
	}

	public int getNumEstudiantes() {
		return numEstudiantes;
	}

	public void setNumEstudiantes(int numEstudiantes) {
		this.numEstudiantes = numEstudiantes;
	}

	@Override
	public String toString() {
		String m = "";
		m += super.toString();
		m += "\nNumero de estudiantes: " + numEstudiantes;
		return m;
	}
	
}
