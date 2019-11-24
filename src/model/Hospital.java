package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hospital extends IPS{

	
	private StringProperty representante;
	private StringProperty universitario;
	private StringProperty acreditado;


	public Hospital(int nit, String nombre, String direccion,String tipo,int nivel,String representante,String universitario,String acreditado) {
		super(nit, nombre, direccion,tipo,nivel);
		this.representante = new SimpleStringProperty(representante);
		this.universitario = new SimpleStringProperty(universitario);
		this.acreditado = new SimpleStringProperty(acreditado);

		
	}

	public String getRepresentante() {
		return representante.get();
	}

	public void setRepresentante(String representante) {
		this.representante.set(representante);
	}
	
	


	@Override
	public String toString() {
		return "Hospital [representante=" + representante + ", universitario=" + universitario + "]";
	}

	
	public int compareTo(Hospital o) {
		return representante.get().compareTo(o.getRepresentante());
	}
	
	public int compareByName(String n) {
		return super.getNombre().compareTo(n);
	}

	public String getUniversitario() {
		return universitario.get();
	}

	public void setUniversitario(String universitario) {
		this.universitario.set(universitario);
	}

	public String getAcreditado() {
		return acreditado.get();
	}

	public void setAcreditado(String acreditado) {
		this.acreditado.set(acreditado);
	}

	@Override
	public int compareTo(IPS o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	


	
	
	
}
