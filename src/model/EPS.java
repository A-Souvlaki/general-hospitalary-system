package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import excepciones.NotFoundException;

public class EPS {

	private Administrador admin;

	private PacienteSubsidiado raizPacienteS;
	
	private PacienteContributario raizPacienteC;

	private AltoNivel raizAltoNivel;

	private Comun raizComun;

	private String nombre;

	private double caraterEconomico;

	private ArrayList<IPS> ipses;

	public EPS(String nombre, double caracterEconomico) {
		this.nombre = nombre;
		this.caraterEconomico = caracterEconomico;
		this.ipses = new ArrayList<IPS>();
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCaraterEconomico() {
		return caraterEconomico;
	}

	public void setCaraterEconomico(double caraterEconomico) {
		this.caraterEconomico = caraterEconomico;
	}

	public void cargarAltoNivel(String path) {

		File file = new File(path);
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String linea;
		        while((linea=br.readLine())!=null) {
		        	String[] write = linea.split(",");
					agregarAltoNivel(write[0], write[1], write[2], Double.parseDouble(write[3]), Integer.parseInt(write[4]));
		        }
				br.close();

			} catch (IOException e) {
				e.getStackTrace();
			}
		}

	}
	
	public void cargarComun(String path) {

		File file = new File(path);
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String linea;
		        while((linea=br.readLine())!=null) {
		        	String[] write = linea.split(",");
					agregarComun(write[0], write[1], write[2], write[3]);
		        }
				br.close();

			} catch (IOException e) {
				e.getStackTrace();
			}
		}

	}
	
	public void cargarSubsidiados(String path) {

		File file = new File(path);
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String linea;
		        while((linea=br.readLine())!=null) {
		        	String[] write = linea.split(",");
					agregarSubsidiado(write[0], write[1], write[2], write[3]);
		        }
				br.close();

			} catch (IOException e) {
				e.getStackTrace();
			}
		}

	}
	
	public void cargarContributivos(String path) {

		File file = new File(path);
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String linea;
		        while((linea=br.readLine())!=null) {
		        	String[] write = linea.split(",");
					agregarContributivo(write[0], write[1], write[2], write[3]);
		        }
				br.close();

			} catch (IOException e) {
				e.getStackTrace();
			}
		}

	}

	// Revisa los metodos del controller de Hospital para ver como se usa xD
	/**
	 * 
	 * This method allows to add a hospital to the IPS list
	 * 
	 * @param nit           The IPS nit
	 * @param nombre        The IPS's name
	 * @param direccion     The IPS's adress
	 * @param tipo          The IPS's type
	 * @param nivel         The IPS's complexity level
	 * @param representante The hospital's agent
	 * @param universitario it indicates if the hospital is university or general
	 * @param acreditado    it indicates if the institution is accredited
	 */
	public void agregarIPS(int nit, String nombre, String direccion, String tipo, int nivel, String representante,
			String universitario, String acreditado) {
		Hospital h = new Hospital(nit, nombre, direccion, tipo, nivel, representante, universitario, acreditado);
		ipses.add(h);
	}

	// Revisa los metodos del controller de Clinica para ver como se usa xD
	/**
	 * This method allows to add a clinic to the IPS list
	 * 
	 * @param nit          The IPS nit
	 * @param nombre       The IPS's name
	 * @param direccion    The IPS's adress
	 * @param tipo         The IPS's type
	 * @param nivel        The IPS's complexity level
	 * @param especialidad The IPS's speciality
	 */
	public void agregarIPS(int nit, String nombre, String direccion, String tipo, int nivel, String especialidad) {
		Clinica c = new Clinica(nit, nombre, direccion, tipo, nivel, especialidad);
		ipses.add(c);
	}

	// Revisa los metodos del Controller de P.Salud para ver como se usa xD
	/**
	 * This method allows to add a health post to the IPS list
	 * 
	 * @param nit              The IPS nit
	 * @param nombre           The IPS's name
	 * @param direccion        The IPS's adress
	 * @param tipo             The IPS's type
	 * @param nivel            The IPS's complexity level
	 * @param jefe_enfermeros  it indicates the principal nurse name in the health
	 *                         post
	 * @param jefe_laboratorio it indicates the principal laboratories man name in
	 *                         the health post
	 * @param vacunas          it indicates if there is a post of vacums in the
	 *                         health post
	 * @param farmacia         it indicates if there is a pharmacy in the health
	 *                         post
	 */
	public void agregarIPS(int nit, String nombre, String direccion, String tipo, int nivel, String jefe_enfermeros,
			String jefe_laboratorio, String vacunas, String farmacia) {
		PuestoDeSalud ps = new PuestoDeSalud(nit, nombre, direccion, tipo, nivel, jefe_enfermeros, jefe_laboratorio,
				vacunas, farmacia);
		ipses.add(ps);
	}

	// Este metodo lo modifique para que funcionara con las tablas de la GUI, asi
	// que decidi usar burbuja para las clinicas, seleccion para hospitales e
	// insercion para puestos de salud
	/**
	 * This method allows to sort a clinics list by "Nit" number using bubble sort
	 * algorithm
	 * 
	 * @return A sort clinic list
	 */
	public ArrayList<Clinica> ordenarClinicasBurbuja_Nit() {
		ArrayList<Clinica> c = obtenerClinicasParaMostrar();
		for (int i = 0; i < c.size(); i++) {
			for (int j = 0; j < c.size() - 1 - i; j++) {
				if (c.get(j).getNit() > c.get(j + 1).getNit()) {
					Clinica temp = c.get(j);
					c.set(j, c.get(j + 1));
					c.set(j + 1, temp);

				}
			}
		}

		return c;
	}

	// Lo mismo que el de arriba
	/**
	 * This method allows to sort a clinics list by name using bubble sort algorithm
	 * 
	 * @return A sort clinic list
	 */
	public ArrayList<Clinica> ordenarClinicasBurbuja_Nombre() {
		ArrayList<Clinica> c = obtenerClinicasParaMostrar();
		for (int i = 0; i < c.size(); i++) {
			for (int j = 0; j < c.size() - 1 - i; j++) {
				if (c.get(j).compareTo(c.get(j + 1)) > 0) {
					Clinica temp = c.get(j);
					c.set(j, c.get(j + 1));
					c.set(j + 1, temp);

				}
			}
		}

		return c;
	}

	// Este metodo lo uso para la busqueda binaria
	/**
	 * This method allows to sort a Hospital list by "Name" number using bubble sort
	 * algorithm
	 * 
	 * @return A sort hospitals list
	 */
	public ArrayList<Hospital> ordenarHospitales_Nombre() {
		ArrayList<Hospital> c = obtenerHospitalesParaMostrar();
		for (int i = 0; i < c.size(); i++) {
			for (int j = 0; j < c.size() - 1 - i; j++) {
				if (c.get(j).compareByName(c.get(j + 1).getNombre()) > 0) {
					Hospital temp = c.get(j);
					c.set(j, c.get(j + 1));
					c.set(j + 1, temp);

				}
			}
		}

		return c;
	}

	// Este metodo lo uso para la busqueda binaria
	/**
	 * This method allows to sort a health post list by "Nit" number using bubble
	 * sort algorithm
	 * 
	 * @return A sort healt post list
	 */
	public ArrayList<PuestoDeSalud> ordenarPuestos_Nit() {
		ArrayList<PuestoDeSalud> c = obtenerPuestosParaMostrar();
		for (int i = 0; i < c.size(); i++) {
			for (int j = 0; j < c.size() - 1 - i; j++) {
				if (c.get(j).getNit() > c.get(j + 1).getNit()) {
					PuestoDeSalud temp = c.get(j);
					c.set(j, c.get(j + 1));
					c.set(j + 1, temp);

				}
			}
		}

		return c;
	}

	// Este ordenamiento funciona en los hospitales por si algo xD
	/**
	 * This method allows to sort a hospitals list by agent using selection sort
	 * algorithm
	 * 
	 * @return A sort clinic list
	 */
	public ArrayList<Hospital> ordenarHospitalesSeleccion_Repre() {
		ArrayList<Hospital> h = obtenerHospitalesParaMostrar();
		for (int i = 0; i < h.size(); i++) {
			Hospital minor = h.get(i);
			int index = i;
			for (int j = i + 1; j < h.size(); j++) {
				if (minor.compareTo(h.get(j)) > 0) {
					minor = h.get(j);
					index = j;
				}
			}
			Hospital temp = h.get(i);
			h.set(i, minor);
			h.set(index, temp);
		}
		return h;
	}

	// Este ordenamiento funciona en los puestos de salud por si algo xD
	/**
	 * This method allows to sort a hospitals list by nurse using insertion sort
	 * algorithm
	 * 
	 * @return A sort health post list
	 */
	public ArrayList<PuestoDeSalud> ordenarInsercion_enfermeros() {
		ArrayList<PuestoDeSalud> p = obtenerPuestosParaMostrar();
		for (int i = 1; i < p.size(); i++) {
			for (int j = i; j > 0 && p.get(j).compareJefe_e(p.get(j - 1)) < 0; j--) {
				PuestoDeSalud temp = p.get(j);
				p.set(j, p.get(j - 1));
				p.set(j - 1, temp);
			}
		}
		return p;
	}

	// Este ordenamiento funciona en los puestos de salud por si algo xD
	/**
	 * This method allows to sort a hospitals list by nurse using insertion sort
	 * algorithm
	 * 
	 * @return A sort health post list
	 */
	public ArrayList<PuestoDeSalud> ordenarInsercion_laboratorio() {
		ArrayList<PuestoDeSalud> p = obtenerPuestosParaMostrar();
		for (int i = 1; i < p.size(); i++) {
			for (int j = i; j > 0 && p.get(j).compareJefe_l(p.get(j - 1)) < 0; j--) {
				PuestoDeSalud temp = p.get(j);
				p.set(j, p.get(j - 1));
				p.set(j - 1, temp);
			}
		}
		return p;
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

	// Lo mismo de arriba
	/**
	 * This method allows to find the index of a clinic in the clinics list
	 * 
	 * @param nit
	 * @return The index with the clinic position
	 * @throws NotFoundException
	 */
	public Clinica buscarClinica_nit(int nit) throws NotFoundException {
		ArrayList<Clinica> c = ordenarClinicasBurbuja_Nit();
		Clinica clinica = null;
		boolean found = false;
		int start = 0;
		int end = c.size() - 1;
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			if (c.get(middle).getNit() == nit) {
				found = true;
				clinica = c.get(middle);
			} else if (c.get(middle).getNit() > nit) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}

		}
		if (clinica == null) {
			throw new NotFoundException();
		}
		return clinica;
	}

	// Lo mismo de arriba
	/**
	 * This method allows to find the index of a clinic in the clinics list
	 * 
	 * @param nit
	 * @return The index with the clinic position
	 * @throws NotFoundException
	 */
	public PuestoDeSalud buscarPuesto_nit(int nit) throws NotFoundException {
		ArrayList<PuestoDeSalud> c = ordenarPuestos_Nit();
		PuestoDeSalud puesto = null;
		boolean found = false;
		int start = 0;
		int end = c.size() - 1;
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			if (c.get(middle).getNit() == nit) {
				found = true;
				puesto = c.get(middle);
			} else if (c.get(middle).getNit() > nit) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}

		}
		if (puesto == null) {
			throw new NotFoundException();
		}
		return puesto;
	}

	/**
	 * This method allows to find the index of a hospital in the clinics list
	 * 
	 * @param nombre
	 * @return A hospital type object
	 * @throws NotFoundException
	 */
	public Hospital buscarHospital_Nombre(String nombre) throws NotFoundException {
		ArrayList<Hospital> h = ordenarHospitales_Nombre();
		Hospital hospital = null;
		boolean found = false;
		int start = 0;
		int end = h.size() - 1;
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			if (h.get(middle).compareByNombre(nombre) == 0) {
				found = true;
				hospital = h.get(middle);
			} else if (h.get(middle).compareByNombre(nombre) > 0) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}

		}
		if (hospital == null) {
			throw new NotFoundException();
		}
		return hospital;
	}

	// Metodo secundario, no es necesario que lo toques!!!!!!

	/**
	 * This method allows create a list with the clinics in the IPS general list
	 * 
	 * @return A list of clinics
	 */
	public ArrayList<Clinica> obtenerClinicasParaMostrar() {
		ArrayList<Clinica> clinicas = new ArrayList<Clinica>();
		for (int i = 0; i < ipses.size(); i++) {
			if (ipses.get(i) instanceof Clinica) {
				clinicas.add((Clinica) ipses.get(i));
			}
		}
		return clinicas;
	}

	//// Metodo secundario, no es necesario que lo toques!!!!!!
	/**
	 * This method allows create a list with the hospitals in the IPS general list
	 * 
	 * @return A list of clinics
	 */
	public ArrayList<PuestoDeSalud> obtenerPuestosParaMostrar() {
		ArrayList<PuestoDeSalud> puestos = new ArrayList<PuestoDeSalud>();
		for (int i = 0; i < ipses.size(); i++) {
			if (ipses.get(i) instanceof PuestoDeSalud) {
				puestos.add((PuestoDeSalud) ipses.get(i));
			}
		}
		return puestos;
	}

	//// Metodo secundario, no es necesario que lo toques!!!!!!
	/**
	 * This method allows create a list with the health post in the IPS general list
	 * 
	 * @return A list of health post
	 */
	public ArrayList<Hospital> obtenerHospitalesParaMostrar() {
		ArrayList<Hospital> hospitales = new ArrayList<Hospital>();
		for (int i = 0; i < ipses.size(); i++) {
			if (ipses.get(i) instanceof Hospital) {
				hospitales.add((Hospital) ipses.get(i));
			}
		}
		return hospitales;
	}

	// _______________________________________________________________________________________________________________________________________________________________//
	// Esto finaliza la parte de las listas y ordenamientos de ArrayList, con las
	// listas decidi cambiar y hacerlo de acuerdo al modelo de esta EPS
	// https://www.epssura.com/index.php/pac-directorio#
	// Mira el link para que te hagas una idea
	// Lo que vienen son metodos de listas
	// _______________________________________________________________________________________________________________________________________________________________//

	// Bruh, este metodo es una busqueda secuencial para un menu chido en al
	// interfaz de medicos
	/**
	 * This method allows to find a clinic to show its doctors
	 * 
	 * @param nit The nit of the Clinic
	 * @return A clinic
	 * @throws NotFoundException if the searched clinic does not exist
	 */
	public Clinica buscarClinica(int nit) throws NotFoundException {
		ArrayList<Clinica> c = obtenerClinicasParaMostrar();
		Clinica buscada = null;
		boolean close = true;
		for (int i = 0; i < c.size() && close; i++) {
			if (nit == c.get(i).getNit()) {
				buscada = c.get(i);
				close = false;
			}
		}

		if (buscada == null) {
			throw new NotFoundException();
		}
		return buscada;
	}

	/**
	 * This method allows to find a health post to show its doctors
	 * 
	 * @param nit The nit of the health post
	 * @return A health post
	 * @throws NotFoundException if the searched clinic does not exist
	 */
	public PuestoDeSalud buscarPuesto(int nit) throws NotFoundException {
		ArrayList<PuestoDeSalud> c = obtenerPuestosParaMostrar();
		PuestoDeSalud buscada = null;
		boolean close = true;
		for (int i = 0; i < c.size() && close; i++) {
			if (nit == c.get(i).getNit()) {
				buscada = c.get(i);
				close = false;
			}
		}

		if (buscada == null) {
			throw new NotFoundException();
		}
		return buscada;
	}

	/**
	 * This method allows to find a hospital to show its doctors
	 * 
	 * @param nit The nit of the hospital post
	 * @return A health post
	 * @throws NotFoundException if the searched clinic does not exist
	 */
	public Hospital buscarHospital(int nit) throws NotFoundException {
		ArrayList<Hospital> c = obtenerHospitalesParaMostrar();
		Hospital buscada = null;
		boolean close = true;
		for (int i = 0; i < c.size() && close; i++) {
			if (nit == c.get(i).getNit()) {
				buscada = c.get(i);
				close = false;
			}
		}

		if (buscada == null) {
			throw new NotFoundException();
		}
		return buscada;
	}

	// Esta cosa pone medicos en la tabla
	/**
	 * This method allows to add a doctor
	 * 
	 * @param m   A doctor to add
	 * @param nit The clinic's nit
	 * @throws NotFoundException if the clinic is not founded
	 * 
	 */
	public void añadirMedicoClinica(MedicoEspecialista m, int nit) throws NotFoundException {
		buscarClinica(nit).insertarMedico(m);
	}

	// Esta cosa los borra
	/**
	 * This method allows to delete a doctor
	 * 
	 * @param m   A doctor to delete
	 * @param nit The clinic's nit
	 * @throws NotFoundException if the clinic is not founded
	 */
	public void borrarMedicoClinica(int licencia, int nit) throws NotFoundException {
		buscarClinica(nit).eliminarMedico(licencia);
	}

	// Esta cosa pone medicos en la tabla
	/**
	 * This method allows to add a doctor
	 * 
	 * @param m   A doctor to add
	 * @param nit The clinic's nit
	 * @throws NotFoundException if the clinic is not founded
	 * 
	 */
	public void añadirMedicoPuesto(MedicoGeneral m, int nit) throws NotFoundException {
		buscarPuesto(nit).insertarMedico(m);
	}

	// Esta cosa los borra
	/**
	 * This method allows to delete a doctor
	 * 
	 * @param m   A doctor to delete
	 * @param nit The clinic's nit
	 * @throws NotFoundException if the clinic is not founded
	 */
	public void borrarMedicoPuesto(int licencia, int nit) throws NotFoundException {
		buscarPuesto(nit).eliminarMedico(licencia);
	}

	// Esta cosa pone medicos en la tabla
	/**
	 * This method allows to add a doctor
	 * 
	 * @param m   A doctor to add
	 * @param nit The hospital's nit
	 * @throws NotFoundException if the hospital is not founded
	 * 
	 */
	public void añadirMedicoHospital(MedicoGeneral m, int nit) throws NotFoundException {
		buscarHospital(nit).insertarMedico(m);
	}

	// Esta cosa los borra
	/**
	 * This method allows to delete a doctor
	 * 
	 * @param m   A doctor to delete
	 * @param nit The hospital's nit
	 * @throws NotFoundException if the hospital is not founded
	 */
	public void borrarMedicoHospital(int licencia, int nit) throws NotFoundException {
		buscarHospital(nit).eliminarMedico(licencia);
	}

	// Esta cosa pone medicos en la tabla
	/**
	 * This method allows to add a doctor
	 * 
	 * @param m   A doctor to add
	 * @param nit The hospital's nit
	 * @throws NotFoundException if the hospital is not founded
	 * 
	 */
	public void añadirMedicoHospital(MedicoEspecialista m, int nit) throws NotFoundException {
		buscarHospital(nit).insertarMedicoEspecialista(m);
	}

	// Esta cosa los borra
	/**
	 * This method allows to delete a doctor
	 * 
	 * @param m   A doctor to delete
	 * @param nit The hospital's nit
	 * @throws NotFoundException if the hospital is not founded
	 */
	public void borrarMedicoHospitalE(int licencia, int nit) throws NotFoundException {
		buscarHospital(nit).eliminarMedicoEspecialista(licencia);
	}
	// __________________________________________________________________________________________________________________________________________________________________//
	// Aqui empienzan los metodos de arboles, que son relativamente cortos

	public void agregarAltoNivel(String id, String codigo, String nombre, double costo, int dosis) {
		AltoNivel c = new AltoNivel(id, codigo, nombre, costo, dosis);
		if (raizAltoNivel == null)
			raizAltoNivel = c;
		else {
			raizAltoNivel.insertar(c);
		}
	}

	public void agregarComun(String id, String codigo, String nombre, String principioActivo) {
		Comun c = new Comun(id, codigo, nombre, principioActivo);
		if (raizComun == null)
			raizComun = c;
		else {
			raizComun.insertar(c);
		}
	}

	public void eliminarAltoNivel(String nombre) {
		raizAltoNivel = raizAltoNivel.eliminar(nombre);
	}

	public void eliminarComun(String nombre) {
		raizComun = raizComun.eliminar(nombre);
	}

	public Collection<AltoNivel> darListaAltoNivel() {
		if (raizAltoNivel == null)
			return null;
		else {
			Collection<AltoNivel> resp = new ArrayList<AltoNivel>();
			raizAltoNivel.inorden(resp);
			return resp;
		}
	}

	public Collection<Comun> darListaComun() {
		if (raizComun == null)
			return null;
		else {
			Collection<Comun> resp = new ArrayList<Comun>();
			raizComun.inorden(resp);
			return resp;
		}
	}

	public int contarHojasAltoNivel() {
		return raizAltoNivel == null ? 0 : raizAltoNivel.contarHojas();
	}

	public int contarComun() {
		return raizComun == null ? 0 : raizComun.contarHojas();
	}

	public int darPesoAltoNivel() {
		return raizAltoNivel == null ? 0 : raizAltoNivel.darPeso();
	}

	public int darPesoComun() {
		return raizComun == null ? 0 : raizComun.darPeso();
	}
	
	//______________________________________________________________________________________________________________________________________________________________________________________________-//
	public void agregarSubsidiado(String nombre, String apellido, String id,String sisben) {
		PacienteSubsidiado c = new PacienteSubsidiado(nombre, apellido, id, sisben);
		if (raizPacienteS == null)
			raizPacienteS = c;
		else {
			raizPacienteS.insertar(c);
		}
	}

	public void agregarContributivo(String nombre, String apellido, String id,String cuota) {
		PacienteContributario c = new PacienteContributario(nombre, apellido, id, cuota);
		if (raizPacienteC == null)
			raizPacienteC = c;
		else {
			raizPacienteC.insertar(c);
		}
	}

	public void eliminarSubsidiado(String nombre) {
		raizPacienteS = raizPacienteS.eliminar(nombre);
	}

	public void eliminarContributivo(String nombre) {
		raizPacienteC = raizPacienteC.eliminar(nombre);
	}

	public Collection<PacienteSubsidiado> darListaSubsiado() {
		if (raizPacienteS == null)
			return null;
		else {
			Collection<PacienteSubsidiado> resp = new ArrayList<PacienteSubsidiado>();
			raizPacienteS.inorden(resp);
			return resp;
		}
	}

	public Collection<PacienteContributario> darListaContributivo() {
		if (raizPacienteC == null)
			return null;
		else {
			Collection<PacienteContributario> resp = new ArrayList<PacienteContributario>();
			raizPacienteC.inorden(resp);
			return resp;
		}
	}

	public int contarHojasSubsidiado() {
		return raizPacienteS == null ? 0 : raizPacienteS.contarHojas();
	}

	public int contarHojasContributivo() {
		return raizPacienteC == null ? 0 : raizPacienteC.contarHojas();
	
	}
	public int darPesoSubsidiado() {
		return raizPacienteS == null ? 0 : raizPacienteS.darPeso();
	}

	public int darPesoContributivo() {
		return raizPacienteC == null ? 0 : raizPacienteC.darPeso();
	}
}
