package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

import java.util.Date;

public class Reporte0DM {

	private String ciclo;
	private String grupoIng;
	private Double total;
	private Double pagosAdelantados;
	private Double sinPagosAdelantados;
	private Date mes;
	private Date año;


	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getGrupoIng() {
		return grupoIng;
	}

	public void setGrupoIng(String grupoIng) {
		this.grupoIng = grupoIng;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getPagosAdelantados() {
		return pagosAdelantados;
	}

	public void setPagosAdelantados(Double pagosAdelantados) {
		this.pagosAdelantados = pagosAdelantados;
	}

	public Double getSinPagosAdelantados() {
		return sinPagosAdelantados;
	}

	public void setSinPagosAdelantados(Double sinPagosAdelantados) {
		this.sinPagosAdelantados = sinPagosAdelantados;
	}

	public Date getMes() {
		return mes;
	}

	public void setMes(Date mes) {
		this.mes = mes;
	}

	public Date getAño() {
		return año;
	}

	public void setAño(Date año) {
		this.año = año;
	}

}