package model;

import java.util.ArrayList;

public class EPS {

	private Paciente raizPaciente;

	private double caraterEconomico;

	private ArrayList<IPS> ipses;

	public EPS(double caracterEconomico) {
		this.caraterEconomico = caracterEconomico;
		this.ipses = new ArrayList<IPS>();
	}

	/**
	 * 
	 * @param nit
	 * @param nombre
	 * @param direccion
	 * @param tipo
	 * @param nivel
	 * @param representante
	 * @param universitario
	 * @param acreditado
	 */
	public void agregarIPS(double nit, String nombre, String direccion,String tipo,int nivel,String representante,boolean universitario,boolean acreditado) {
		Hospital h = new Hospital(nit, nombre, direccion, tipo, nivel, representante, universitario, acreditado);
		ipses.add(h);
	}
	
	/**
	 * 
	 * @param nit
	 * @param nombre
	 * @param direccion
	 * @param tipo
	 * @param nivel
	 * @param especialidad
	 */
	public void agregarIPS(double nit, String nombre, String direccion,String tipo,int nivel,boolean especialidad) {
		Clinica c = new Clinica(nit, nombre, direccion, tipo, nivel, especialidad);
		ipses.add(c);
	}
	
	/**
	 * 
	 * @param nit
	 * @param nombre
	 * @param direccion
	 * @param tipo
	 * @param nivel
	 */
	public void agregarIPS(double nit, String nombre, String direccion,String tipo,int nivel) {
		PuestoDeSalud ps = new PuestoDeSalud(nit, nombre, direccion, tipo, nivel);
		ipses.add(ps);
	}
	
	/**
	 * 
	 */
	public void OrdenarBurbuja() {
		
		for (int i = 0; i < ipses.size(); i++) {
			for (int j = 0; j < ipses.size(); j++) {
				if(ipses.get(j).getNit()> ipses.get(j+1).getNit()) {
					IPS temp = ipses.get(j);
					ipses.set(j,ipses.get(j+1));
					ipses.set(j+1, temp);
					
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void OrdenarSeleccion() {
		
		for (int i = 0; i < ipses.size(); i++) {
			IPS minor = ipses.get(i);
			int index = i;
			for (int j = i + 1; j < ipses.size(); j++) {
				if (ipses.get(j).compareTo(ipses.get(j+1))>0) {
					minor = ipses.get(j);
					index = j;
				}
			}
			IPS temp = ipses.get(i);
			ipses.set(i, minor);
			ipses.set(index, temp);
		}
	}
	
	/**
	 * 
	 */
	public void ordenarInsercion() {
		for (int i = 0; i < ipses.size(); i++) {
			for (int j = 0; j < ipses.size() - 1 - i; j++) {
				if (ipses.get(j).getNivel() > ipses.get(j + 1).getNivel()) {
					IPS temp = ipses.get(j);
					ipses.set(j, ipses.get(j + 1));
					ipses.set(j + 1, temp);
				}
			}
		}
	}
		
	/**
	 * 
	 * @param nit
	 */
	public void eliminarIPSNIT(double nit) {
		for (int i = 0; i < ipses.size(); i++) {
			if (ipses.get(i).getNit() == nit) {
				ipses.remove(i);
			}
		}
	}
	
	/**
	 * 
	 * @param nit
	 * @return
	 */
	public String buscarIPS_nit(double nit) {
		OrdenarBurbuja();
		String msg = "";
		boolean found = false;
		int start = 0;
		int end = ipses.size() - 1;
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			if (ipses.get(middle).getNit() == nit) {
				found = true;
				msg += ipses.get(middle);
			} else if (ipses.get(middle).getNit() > 0) { 
				end = middle - 1;
			} else {
				start = middle + 1;
			}

		}
		if(msg.equals("")) {
			msg = "No se encontro ningun club con los datos especificados";
		}
		return msg;
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public String buscarIPS_nombre(String nombre) {
		OrdenarBurbuja();
		String msg = "";
		boolean found = false;
		int start = 0;
		int end = ipses.size() - 1;
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			if (ipses.get(middle).compareByNombre(nombre) == 0) {
				found = true;
				msg += ipses.get(middle);
			} else if (ipses.get(middle).compareByNombre(nombre) > 0) { 
				end = middle - 1;
			} else {
				start = middle + 1;
			}

		}
		if(msg.equals("")) {
			msg = "No se encontro ningun club con los datos especificados";
		}
		return msg;
	}
	

}
