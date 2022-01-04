package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

import java.util.List;

public class RepCiclos0DM {
	
	private String ciclo;
	private Cifras0DM montos;
	private List<GrupoIngreso0DM> conjuntoIngresos;
	public String getCiclo() {
		return ciclo;
	}
	public Cifras0DM getMontos() {
		return montos;
	}
	public List<GrupoIngreso0DM> getConjuntoIngresos() {
		return conjuntoIngresos;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public void setMontos(Cifras0DM montos) {
		this.montos = montos;
	}
	public void setConjuntoIngresos(List<GrupoIngreso0DM> conjuntoIngresos) {
		this.conjuntoIngresos = conjuntoIngresos;
	}
	@Override
	public String toString() {
		return "RepCiclos0DM [ciclo=" + ciclo + ", montos=" + montos + ", conjuntoIngresos=" + conjuntoIngresos + "]";
	}
	
	
}
