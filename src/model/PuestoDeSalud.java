package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PuestoDeSalud extends IPS{
	
	private StringProperty jefe_enfermeros;
	private StringProperty jefe_laboratorio;
	private StringProperty vacunas;
	private StringProperty farmacia;

	public PuestoDeSalud(int nit, String nombre, String direccion, String tipo, int nivel, String jefe_enfermeros, String jefe_laboratorio, String vacunas, String farmacia) {
		super(nit, nombre, direccion,tipo,nivel);
		this.jefe_enfermeros = new SimpleStringProperty(jefe_enfermeros);
		this.jefe_laboratorio = new SimpleStringProperty(jefe_laboratorio);
		this.vacunas = new SimpleStringProperty(vacunas);
		this.farmacia = new SimpleStringProperty(farmacia);
	}

	public String getJefe_enfermeros() {
		return jefe_enfermeros.get();
	}

	public void setJefe_enfermeros(String jefe_enfermeros) {
		this.jefe_enfermeros.set(jefe_enfermeros);
	}

	public String getJefe_laboratorio() {
		return jefe_laboratorio.get();
	}
	
	public void setJefe_laboratorio(String jefe_laboratorio) {
		this.jefe_laboratorio.set(jefe_laboratorio);
	}

	public String getVacunas() {
		return vacunas.get();
	}

	public void setVacunas(String vacunas) {
		this.vacunas.set(vacunas);
	}

	public String getFarmacia() {
		return farmacia.get();
	}

	public void setFarmacia(String farmacia) {
		this.farmacia.set(farmacia);
	}

	@Override
	public int compareTo(IPS o) {
		return super.getNombre().compareTo(o.getNombre());
	}
	
	public int compareJefe_e(PuestoDeSalud o) {
		return jefe_enfermeros.get().compareTo(o.getJefe_enfermeros());
	}
	
	public int compareJefe_l(PuestoDeSalud o) {
		return jefe_laboratorio.get().compareTo(o.getJefe_laboratorio());
	}

}
