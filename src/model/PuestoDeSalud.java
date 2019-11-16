package model;

public class PuestoDeSalud extends IPS{
	
	private boolean odontologia;
	private boolean vacunas;
	private boolean laboratorio;
	private boolean farmacia;

	public PuestoDeSalud(double nit, String nombre, String direccion,String tipo,int nivel) {
		super(nit, nombre, direccion,tipo,nivel);
		this.odontologia = true;
		this.vacunas = true;
		this.laboratorio = false;
		this.farmacia = false;
		
	}

	public boolean isOdontologia() {
		return odontologia;
	}

	public void setOdontologia(boolean odontologia) {
		this.odontologia = odontologia;
	}

	public boolean isVacunas() {
		return vacunas;
	}

	public void setVacunas(boolean vacunas) {
		this.vacunas = vacunas;
	}

	public boolean isLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(boolean laboratorio) {
		this.laboratorio = laboratorio;
	}

	public boolean isFarmacia() {
		return farmacia;
	}

	public void setFarmacia(boolean farmacia) {
		this.farmacia = farmacia;
	}

	@Override
	public String toString() {
		return "PuestoDeSalud [odontologia=" + odontologia + ", vacunas=" + vacunas + ", laboratorio=" + laboratorio
				+ ", farmacia=" + farmacia + "]";
	}

	@Override
	public int compareTo(IPS o) {
		return super.getNombre().compareTo(o.getNombre());
	}
	
	
	
}
