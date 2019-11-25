package model;

import java.util.Collection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PacienteSubsidiado extends Paciente{
	
	private StringProperty sisben_p;
	
	private PacienteSubsidiado izq;
	private PacienteSubsidiado der;

	public PacienteSubsidiado(String nombre, String apellido, String id,String sis) {
		super(nombre, apellido, id);
		this.sisben_p = new SimpleStringProperty(sis);
	}

	public String getSisben_p() {
		return sisben_p.get();
	}

	public void setSisben_p(String sisben_p) {
		this.sisben_p.set(sisben_p);
	}
	
	public void insertar(PacienteSubsidiado nuevo) {
		if (compareTo(nuevo) > 0) {
			// Debe agregar el nuevo contacto por el subárbol izquierdo
			if (izq == null)
				izq = nuevo;
			else
				izq.insertar(nuevo);
		} else {
			// Debe agregar el nuevo contacto por el subárbol derecho
			if (der == null)
				der = nuevo;
			else
				der.insertar(nuevo);
		}
	}
	
	public void inorden(Collection<PacienteSubsidiado> acumulado) {
		// Recorre en inorden el subárbol izquierdo
		if (izq != null)
			izq.inorden(acumulado);
		// Incluye en el recorrido el contacto de la raíz
		acumulado.add((PacienteSubsidiado) this);
		// Recorre en inorden el subárbol derecho
		if (der != null)
			der.inorden(acumulado);
	}
	
	public int darPeso() {
		int p1 = (izq == null) ? 0 : izq.darPeso();
		int p2 = (der == null) ? 0 : der.darPeso();
		return 1 + p1 + p2;
	}
	
	public int contarHojas() {
		if (esHoja())
			return 1;
		else {
			int h1 = (izq == null) ? 0 : izq.contarHojas();
			int h2 = (der == null) ? 0 : der.contarHojas();
			return h1 + h2;
		}
	}
	
	public boolean esHoja() {
		return izq == null && der == null;
	}
	
	public PacienteSubsidiado eliminar(String unNombre) {
		if (esHoja())
			// Tiene que ser el elemento que estamos buscando
			return null;
		if (super.getId_p().compareToIgnoreCase(unNombre) == 0) {
			if (izq == null)
				return der;
			if (der == null)
				return izq;
			// Localiza el menor contacto del subárbol derecho
			PacienteSubsidiado sucesor = der.darMenor();
			// Elimina del subárbol derecho el elemento que acaba de localizar
			der = der.eliminar(sucesor.getId_p());
			// Deja el elemento localizado en la raíz del árbol de respuesta
			sucesor.izq = izq;
			sucesor.der = der;
			return sucesor;
		} else if (super.getId_p().compareToIgnoreCase(unNombre) > 0)
			izq = izq.eliminar(unNombre);
		else
			der = der.eliminar(unNombre);
		return this;
	}

	public PacienteSubsidiado darMenor() {
		return (izq == null) ? this : izq.darMenor();
	}
	
	public int compareTo(Object o) {
		PacienteSubsidiado otro = (PacienteSubsidiado) o;
		return super.getNombre_p().compareToIgnoreCase(otro.getNombre_p());
	}



	
	
	

}
