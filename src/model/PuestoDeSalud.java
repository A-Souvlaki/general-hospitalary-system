package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PuestoDeSalud extends IPS{
	
	private ArrayList<MedicoGeneral> medicos;
	
	private StringProperty jefe_enfermeros;
	private StringProperty jefe_laboratorio;
	private StringProperty vacunas;
	private StringProperty farmacia;
	
	private MedicoGeneral primero;

	public PuestoDeSalud(int nit, String nombre, String direccion,String tipo,int nivel,String jefe_enfermeros,String jefe_laboratorio, String vacunas,String farmacia) {
		super(nit, nombre, direccion,tipo,nivel);
		this.jefe_enfermeros = new SimpleStringProperty(jefe_enfermeros);
		this.jefe_laboratorio = new SimpleStringProperty(jefe_laboratorio);
		this.vacunas = new SimpleStringProperty(vacunas);
		this.farmacia = new SimpleStringProperty(farmacia);
		medicos = new ArrayList<MedicoGeneral>();
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
		return jefe_enfermeros.get().compareTo(o.getJefe_enfermeros());
	}
	
	public int compareJefe_l(PuestoDeSalud o) {
		return jefe_laboratorio.get().compareTo(o.getJefe_laboratorio());
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
		for (int i = 0; i < medicos.size()&&close; i++) {
			
			if(medicos.get(i).getNo_licencia() == numero_licencia) {
				medicos.remove(i);
				close = false;
			}
		}
		
	}


	@Override
	public void insertarMedico(Medico m) {
		MedicoGeneral nuevo = (MedicoGeneral)m;
        if (primero==null) {
            primero = nuevo;
            medicos.add(nuevo);
        } else {
            Medico actual = primero;
            while(actual.getSiguiente() != null) {
            	actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
            medicos.add(nuevo);
        } 
	}

}
