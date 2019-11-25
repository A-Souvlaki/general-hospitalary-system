package model;

import java.util.ArrayList;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Clinica extends IPS{
	
	
	private ArrayList<MedicoEspecialista> medicos;
	
	private MedicoEspecialista primero;
	
	private StringProperty especialidad;
	
	public Clinica(int nit, String nombre, String direccion,String tipo,int nivel,String especialidad) {
		super(nit, nombre, direccion,tipo,nivel);
		this.especialidad = new SimpleStringProperty(especialidad);
		medicos = new ArrayList<MedicoEspecialista>();
	}

	

	public ArrayList<MedicoEspecialista> getMedicos() {
		return medicos;
	}



	public void setMedicos(ArrayList<MedicoEspecialista> medicos) {
		this.medicos = medicos;
	}



	public String isEspecialidad() {
		return especialidad.get();
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad.set(especialidad);
	}

	@Override
	public String toString() {
		return super.toString();
		
	}
	
	public MedicoEspecialista getPrimero() {
		return primero;
	}

	public void setPrimero(MedicoEspecialista primero) {
		this.primero = primero;
	}

	@Override
	public int compareTo(IPS o) {
		return super.getNombre().compareTo(o.getNombre());
	}

	@Override
	public void insertarMedico(Medico m) {
		
		MedicoEspecialista nuevo = (MedicoEspecialista)m;
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
	
	

	@Override
	public void eliminarMedico(int numero_licencia) {
		Medico actual = primero, siguiente = null, anterior = null;
		if (actual.getNo_licencia() == numero_licencia) {
			siguiente = primero.getSiguiente();
			primero = (MedicoEspecialista) siguiente;
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

	
}
