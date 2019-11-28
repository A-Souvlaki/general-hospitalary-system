package model;

import java.io.Serializable;
import java.util.Comparator;

public abstract class IPS implements Comparable<IPS>, Registrable, Serializable {

	public final static String PUBLICO = "Publico";
	public final static String PRIVADO = "Privado";
	public final static String MIXTO = "Mixto";

	private int nit;
	private String nombre;
	private String direccion;
	private String tipo;

	private int nivel;

	public IPS(int nit, String nombre, String direccion, String tipo, int nivel) {
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tipo = tipo;
		this.nivel = nivel;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "IPS [nit=" + nit + ", nombre=" + nombre + ", direccion=" + direccion + ", tipo=" + tipo + ", primero="
				+ ", nivel=" + nivel + "]";
	}

	public int compareByNombre(String n) {
		return this.nombre.compareTo(n);
	}

}
