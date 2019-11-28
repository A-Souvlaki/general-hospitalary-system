package model;

import java.io.Serializable;
import java.util.Collection;


public class Comun extends Medicamento implements Serializable {

	private Comun izq;
	private Comun der;

	private String principio;

	public Comun(String id, String codigo, String nombre, String principioActivo) {
		super(id, codigo, nombre);
		this.principio = principioActivo;

	}

	public String getPrincipio() {
		return principio;
	}

	public void setPrincipio(String principio) {
		this.principio = principio;
	}

	public void insertar(Comun nuevo) {
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

	/**
	 * Retorna una colección con los nombres de todos los medicamentos, ordenados
	 * alfabéticamente en orden ascendente
	 * 
	 * @param acumulado colección donde se van agregando los medicamentos
	 *                  ordenadamente
	 */
	public void inorden(Collection<Comun> acumulado) {
		// Recorre en inorden el subárbol izquierdo
		if (izq != null)
			izq.inorden(acumulado);
		// Incluye en el recorrido el contacto de la raíz
		acumulado.add((Comun) this);
		// Recorre en inorden el subárbol derecho
		if (der != null)
			der.inorden(acumulado);
	}

	/**
	 * Retorna el número de medicamentos que hay en el árbol que comienza en este
	 * nodo
	 * 
	 * @return número de medicamentos en el árbol que comienza en este nodo
	 */
	public int darPeso() {
		int p1 = (izq == null) ? 0 : izq.darPeso();
		int p2 = (der == null) ? 0 : der.darPeso();
		return 1 + p1 + p2;
	}

	/**
	 * Retorna el número de hojas que hay en el árbol que comienza en este nodo
	 * 
	 * @return número de hojas que hay en el árbol que comienza en este nodo
	 */
	public int contarHojas() {
		if (esHoja())
			return 1;
		else {
			int h1 = (izq == null) ? 0 : izq.contarHojas();
			int h2 = (der == null) ? 0 : der.contarHojas();
			return h1 + h2;
		}
	}

	/**
	 * Indica si este nodo es una hoja
	 * 
	 * @return true si este nodo es una hoja y false en caso contrario
	 */
	public boolean esHoja() {
		return izq == null && der == null;
	}

	/**
	 * Elimina un contacto del árbol que comienza en este nodo.
	 * 
	 * @param unNombre nombre del contacto que se va a eliminar - hay un contacto en
	 *                 el árbol que se llama unNombre
	 * @return el árbol de contactos después de eliminar el contacto indicado
	 */
	public Comun eliminar(String unNombre) {
		if (esHoja())
			// Tiene que ser el elemento que estamos buscando
			return null;
		if (super.getNombre().compareToIgnoreCase(unNombre) == 0) {
			if (izq == null)
				return der;
			if (der == null)
				return izq;
			// Localiza el menor contacto del subárbol derecho
			Comun sucesor = der.darMenor();
			// Elimina del subárbol derecho el elemento que acaba de localizar
			der = der.eliminar(sucesor.getNombre());
			// Deja el elemento localizado en la raíz del árbol de respuesta
			sucesor.izq = izq;
			sucesor.der = der;
			return sucesor;
		} else if (super.getNombre().compareToIgnoreCase(unNombre) > 0)
			izq = izq.eliminar(unNombre);
		else
			der = der.eliminar(unNombre);
		return this;
	}

	/**
	 * Retorna el medicamento que alfabéticamente corresponde al menor medicamento
	 * del árbol que parte de este nodo
	 * 
	 * @return contacto con menor nombre
	 */
	public Comun darMenor() {
		return (izq == null) ? this : izq.darMenor();
	}

	/**
	 * Compara este medicamento con otro
	 * 
	 * @param o es el otro medicamento con el que se compara
	 * @return -1 si este medicamento es menor al otro, 0 si son iguales y 1 si este
	 *         contacto es mayor al otro
	 */
	public int compareTo(Object o) {
		Comun otro = (Comun) o;
		return super.getNombre().compareToIgnoreCase(otro.getNombre());
	}

}
