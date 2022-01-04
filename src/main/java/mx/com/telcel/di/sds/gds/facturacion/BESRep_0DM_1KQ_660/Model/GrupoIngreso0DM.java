package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

public class GrupoIngreso0DM {
	
	private String grupoIngreso;
	private Cifras0DM datosCifras;
	public String getGrupoIngreso() {
		return grupoIngreso;
	}
	public Cifras0DM getDatosCifras() {
		return datosCifras;
	}
	public void setGrupoIngreso(String grupoIngreso) {
		this.grupoIngreso = grupoIngreso;
	}
	public void setDatosCifras(Cifras0DM datosCifras) {
		this.datosCifras = datosCifras;
	}
	@Override
	public String toString() {
		return "GrupoIngreso0DM [grupoIngreso=" + grupoIngreso + ", datosCifras=" + datosCifras + ", getGrupoIngreso()="
				+ getGrupoIngreso() + ", getDatosCifras()=" + getDatosCifras() + "]";
	}

	
}
