package model;

import java.io.Serializable;
import java.util.Collection;


public class PacienteContributario extends Paciente implements Serializable{
	
	private String cuota;
	
	private PacienteContributario izq;
	private PacienteContributario der;

	public PacienteContributario(String nombre, String apellido, String id,String cuota) {
		super(nombre, apellido, id);
		this.cuota =cuota;
	}


	public String getCuota() {
		return cuota;
	}


	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	
	public void insertar(PacienteContributario nuevo) {
		if (compareTo(nuevo) > 0) {
			// Debe agregar el nuevo contacto por el sub�rbol izquierdo
			if (izq == null)
				izq = nuevo;
			else
				izq.insertar(nuevo);
		} else {
			// Debe agregar el nuevo contacto por el sub�rbol derecho
			if (der == null)
				der = nuevo;
			else
				der.insertar(nuevo);
		}
	}
	
	public void inorden(Collection<PacienteContributario> acumulado) {
		// Recorre en inorden el sub�rbol izquierdo
		if (izq != null)
			izq.inorden(acumulado);
		// Incluye en el recorrido el contacto de la ra�z
		acumulado.add((PacienteContributario) this);
		// Recorre en inorden el sub�rbol derecho
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
	
	public PacienteContributario eliminar(String unNombre) {
		if (esHoja())
			// Tiene que ser el elemento que estamos buscando
			return null;
		if (super.getId_p().compareToIgnoreCase(unNombre) == 0) {
			if (izq == null)
				return der;
			if (der == null)
				return izq;
			// Localiza el menor contacto del sub�rbol derecho
			PacienteContributario sucesor = der.darMenor();
			// Elimina del sub�rbol derecho el elemento que acaba de localizar
			der = der.eliminar(sucesor.getId_p());
			// Deja el elemento localizado en la ra�z del �rbol de respuesta
			sucesor.izq = izq;
			sucesor.der = der;
			return sucesor;
		} else if (super.getId_p().compareToIgnoreCase(unNombre) > 0)
			izq = izq.eliminar(unNombre);
		else
			der = der.eliminar(unNombre);
		return this;
	}

	public PacienteContributario darMenor() {
		return (izq == null) ? this : izq.darMenor();
	}
	
	public int compareTo(Object o) {
		PacienteContributario otro = (PacienteContributario) o;
		return super.getNombre_p().compareToIgnoreCase(otro.getNombre_p());
	}

}
