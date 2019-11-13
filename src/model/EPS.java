package model;

import java.util.ArrayList;

public class EPS {
	
	private Paciente raizPaciente;
	
	private ArrayList<IPS> ipses;
	
	public EPS() {
		this.ipses = new ArrayList<IPS>();
	}
	
	public void eliminarIPSNIT(double nit) {
		for(int i = 0; i < ipses.size() ;i++) {
			if(ipses.get(i).getNit() == nit) {
				ipses.remove(i);
			}
		}
	}
	
}
