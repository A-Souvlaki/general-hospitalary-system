package model;

import java.io.Serializable;
import java.util.ArrayList;


public class PuestoDeSalud extends IPS implements Serializable {

	private ArrayList<MedicoGeneral> medicos;

	private String jefe_enfermeros;
	private String jefe_laboratorio;
	private String vacunas;
	private String farmacia;

	private MedicoGeneral primero;

	public PuestoDeSalud(int nit, String nombre, String direccion, String tipo, int nivel, String jefe_enfermeros,
			String jefe_laboratorio, String vacunas, String farmacia) {
		super(nit, nombre, direccion, tipo, nivel);
		this.jefe_enfermeros = jefe_enfermeros;
		this.jefe_laboratorio = jefe_laboratorio;
		this.vacunas = vacunas;
		this.farmacia = farmacia;
		medicos = new ArrayList<MedicoGeneral>();
	}

	public String getJefe_enfermeros() {
		return jefe_enfermeros;
	}

	public void setJefe_enfermeros(String jefe_enfermeros) {
		this.jefe_enfermeros = jefe_enfermeros;
	}

	public String getJefe_laboratorio() {
		return jefe_laboratorio;
	}

	public void setJefe_laboratorio(String jefe_laboratorio) {
		this.jefe_laboratorio = jefe_laboratorio;
	}

	public String getVacunas() {
		return vacunas;
	}

	public void setVacunas(String vacunas) {
		this.vacunas = vacunas;
	}

	public String getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(String farmacia) {
		this.farmacia = farmacia;
	}

	public MedicoGeneral getPrimero() {
		return primero;
	}

	public void setPrimero(MedicoGeneral primero) {
		this.primero = primero;
	}

	public ArrayList<MedicoGeneral> getMedicos() {
		return medicos;
	}

	public void setMedicos(ArrayList<MedicoGeneral> medicos) {
		this.medicos = medicos;
	}

	@Override
	public int compareTo(IPS o) {
		return super.getNombre().compareTo(o.getNombre());
	}

	public int compareJefe_e(PuestoDeSalud o) {
		return jefe_enfermeros.compareTo(o.getJefe_enfermeros());
	}

	public int compareJefe_l(PuestoDeSalud o) {
		return jefe_laboratorio.compareTo(o.getJefe_laboratorio());
	}

	@Override
	public void eliminarMedico(int numero_licencia) {
		Medico actual = primero, siguiente = null, anterior = null;
		if (actual.getNo_licencia() == numero_licencia) {
			siguiente = primero.getSiguiente();
			primero = (MedicoGeneral) siguiente;
		} else {
			while (actual.getSiguiente() != null) {
				if (actual.getNo_licencia() == numero_licencia) {
					anterior = actual;
					siguiente = actual.getSiguiente();
				}
				actual = actual.getSiguiente();
			}
			anterior.setSiguiente(siguiente.getSiguiente());
		}

		boolean close = true;
		for (int i = 0; i < medicos.size() && close; i++) {

			if (medicos.get(i).getNo_licencia() == numero_licencia) {
				medicos.remove(i);
				close = false;
			}
		}

	}

	@Override
	public void insertarMedico(Medico m) {
		MedicoGeneral nuevo = (MedicoGeneral) m;
		if (primero == null) {
			primero = nuevo;
			medicos.add(nuevo);
		} else {
			Medico actual = primero;
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(nuevo);
			medicos.add(nuevo);
		}
	}

}
