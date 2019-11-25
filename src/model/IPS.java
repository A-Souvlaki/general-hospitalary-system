package model;

import java.util.Comparator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class IPS implements Comparable<IPS>,Registrable {
	
	
	public final static String PUBLICO = "Publico";
	public final static String PRIVADO = "Privado";
	public final static String MIXTO = "Mixto";
	
	private IntegerProperty nit;
	private StringProperty nombre;
	private StringProperty direccion;
	private StringProperty tipo;
	
	private IntegerProperty nivel;
	
	public IPS(int nit, String nombre, String direccion, String tipo, int nivel) {
		this.nit = new SimpleIntegerProperty(nit);
		this.nombre = new SimpleStringProperty(nombre);
		this.direccion = new SimpleStringProperty(direccion);
		this.tipo = new SimpleStringProperty(tipo);
		this.nivel = new SimpleIntegerProperty(nivel);
	}

	public int getNivel() {
		return nivel.get();
	}

	public void setNivel(int nivel) {
		this.nivel.set(nivel);
	}

	public int getNit() {
		return nit.get();
	}

	public String getNombre() {
		return nombre.get();
	}

	public String getDireccion() {
		return direccion.get();
	}
	
	public String getTipo() {
		return tipo.get();
	}

	public void setTipo(String tipo) {
		this.tipo.set(tipo);;
	}
	
	

	@Override
	public String toString() {
		return "IPS [nit=" + nit + ", nombre=" + nombre + ", direccion=" + direccion + ", tipo=" + tipo + ", primero="
				+ ", nivel=" + nivel + "]";
	}

	public int compareByNombre(String n) {
		return this.nombre.get().compareTo(n);
	}
	
	
	

	
	
	
	

}
