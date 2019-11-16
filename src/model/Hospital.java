package model;

public class Hospital extends IPS{

	
	private String representante;
	private boolean universitario;
	private boolean acreditado;
	private int numeroMedicos;

	public Hospital(double nit, String nombre, String direccion,String tipo,int nivel,String representante,boolean universitario,boolean acreditado) {
		super(nit, nombre, direccion,tipo,nivel);
		this.representante = representante;
		this.universitario = universitario;
		this.acreditado = false;
		this.numeroMedicos = 0;
		
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public boolean isUniversitario() {
		return universitario;
	}

	public void setUniversitario(boolean universitario) {
		this.universitario = universitario;
	}

	public boolean isAcreditado() {
		return acreditado;
	}

	public void setAcreditado(boolean acreditado) {
		this.acreditado = acreditado;
	}

	public int getNumeroMedicos() {
		return numeroMedicos;
	}

	public void setNumeroMedicos(int numeroMedicos) {
		this.numeroMedicos = numeroMedicos;
	}

	@Override
	public String toString() {
		return "Hospital [representante=" + representante + ", universitario=" + universitario + ", numeroMedicos="
				+ numeroMedicos + "]";
	}

	@Override
	public int compareTo(IPS o) {
		return super.getNombre().compareTo(o.getNombre());
	}


	
	
	
}
