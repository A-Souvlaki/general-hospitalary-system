package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hospital extends IPS{
	
	private ArrayList<MedicoEspecialista> medicosA;
	private ArrayList<MedicoGeneral> medicosB;
	
	private StringProperty representante;
	private StringProperty universitario;
	private StringProperty acreditado;

	private MedicoEspecialista primeroA;
	private MedicoGeneral primeroB;
	
	public Hospital(int nit, String nombre, String direccion,String tipo,int nivel,String representante,String universitario,String acreditado) {
		super(nit, nombre, direccion,tipo,nivel);
		this.representante = new SimpleStringProperty(representante);
		this.universitario = new SimpleStringProperty(universitario);
		this.acreditado = new SimpleStringProperty(acreditado);
		medicosA = new ArrayList<MedicoEspecialista>();
		medicosB = new ArrayList<MedicoGeneral>();

		
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
	
	public ArrayList<MedicoEspecialista> getMedicosA() {
		return medicosA;
	}

	public void setMedicosA(ArrayList<MedicoEspecialista> medicosA) {
		this.medicosA = medicosA;
	}

	public ArrayList<MedicoGeneral> getMedicosB() {
		return medicosB;
	}

	public void setMedicosB(ArrayList<MedicoGeneral> medicosB) {
		this.medicosB = medicosB;
	}

	@Override
	public int compareTo(IPS o) {
		return 0;
	}


	@Override
	public void eliminarMedico(int numero_licencia) {
		Medico actual = primeroB, siguiente = null, anterior = null;
		if (actual.getNo_licencia() == numero_licencia) {
			siguiente = primeroB.getSiguiente();
			primeroB = (MedicoGeneral) siguiente;
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
		for (int i = 0; i < medicosB.size()&&close; i++) {
			
			if(medicosB.get(i).getNo_licencia() == numero_licencia) {
				medicosB.remove(i);
				close = false;
			}
		}
		
	}

	@Override
	public void insertarMedico(Medico m) {
		MedicoGeneral nuevo = (MedicoGeneral)m;
        if (primeroB==null) {
            primeroB = nuevo;
            medicosB.add(nuevo);
        } else {
            Medico actual = primeroB;
            while(actual.getSiguiente() != null) {
            	actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
            medicosB.add(nuevo);
        } 
		
	}
	
	public void eliminarMedicoEspecialista(int numero_licencia) {
		Medico actual = primeroA, siguiente = null, anterior = null;
		if (actual.getNo_licencia() == numero_licencia) {
			siguiente = primeroA.getSiguiente();
			primeroA = (MedicoEspecialista) siguiente;
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
		for (int i = 0; i < medicosA.size()&&close; i++) {
			
			if(medicosA.get(i).getNo_licencia() == numero_licencia) {
				medicosA.remove(i);
				close = false;
			}
		}
		
	}
	
	public void insertarMedicoEspecialista(Medico m) {
		MedicoEspecialista nuevo = (MedicoEspecialista)m;
        if (primeroA==null) {
            primeroA = nuevo;
            medicosA.add(nuevo);
        } else {
            Medico actual = primeroA;
            while(actual.getSiguiente() != null) {
            	actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
            medicosA.add(nuevo);
        } 
		
	}
	

	
	
	


	
	
	
}
