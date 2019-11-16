package model;

import java.util.Comparator;

public abstract class IPS implements Comparable<IPS> {
	
	
	public final static String PUBLICO = "Publico";
	public final static String PRIVADO = "Privado";
	public final static String MIXTO = "Mixto";
	
	private double nit;
	private String nombre;
	private String direccion;
	private String tipo;
	
	private Medico primero;
	
	private int nivel;
	
	public IPS(double nit, String nombre, String direccion, String tipo, int nivel) {
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tipo = tipo;
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public double getNit() {
		return nit;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Medico getPrimero() {
		return primero;
	}

	public void setPrimero(Medico primero) {
		this.primero = primero;
	}

	@Override
	public String toString() {
		return "IPS [nit=" + nit + ", nombre=" + nombre + ", direccion=" + direccion + ", tipo=" + tipo + ", primero="
				+ primero + ", nivel=" + nivel + "]";
	}

	public int compareByNombre(String n) {
		return nombre.compareTo(n);
	}
	
	
	
	

}
