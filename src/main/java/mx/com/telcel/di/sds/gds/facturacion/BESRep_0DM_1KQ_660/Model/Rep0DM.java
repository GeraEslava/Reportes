package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

import java.util.ArrayList;
import java.util.List;

public class Rep0DM {
	
	private String mesFactura;
	private String ciclo;
	private List<RepCiclos0DM> ciclos;
	private List<String> etiquetaFechaPago;
	private Cifras0DM total;
	private List<GrupoIngreso0DM> resumenGrupoIngreso;
	public PagosFacturados rep;

	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public PagosFacturados getRep() {
		return rep;
	}
	public void setRep(PagosFacturados rep) {
		this.rep = rep;
	}
	public String getMesFactura() {
		return mesFactura;
	}
	public List<RepCiclos0DM> getCiclos() {
		return ciclos;
	}
	public List<String> getEtiquetaFechaPago() {
		return etiquetaFechaPago;
	}
	public Cifras0DM getTotal() {
		return total;
	}
	public List<GrupoIngreso0DM> getResumenGrupoIngreso() {
		return resumenGrupoIngreso;
	}
	public void setMesFactura(String mesFactura) {
		this.mesFactura = mesFactura;
	}
	public void setCiclos(List<RepCiclos0DM> ciclos) {
		this.ciclos = ciclos;
	}
	public void setEtiquetaFechaPago(List<String> etiquetaFechaPago) {
		this.etiquetaFechaPago = etiquetaFechaPago;
	}
	public void setTotal(Cifras0DM total) {
		this.total = total;
	}
	public void setResumenGrupoIngreso(List<GrupoIngreso0DM> resumenGrupoIngreso) {
		this.resumenGrupoIngreso = resumenGrupoIngreso;
	}
	@Override
	public String toString() {
		return "Rep0DM [mesFactura=" + mesFactura + ", ciclos=" + ciclos + ", etiquetaFechaPago=" + etiquetaFechaPago
				+ ", total=" + total + ", resumenGrupoIngreso=" + resumenGrupoIngreso + "]";
	}
	

}
