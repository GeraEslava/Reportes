package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

public class PagosFacturados {

	private String region;
	private String ciclo;
	private String grupoIng;
	private String mesFactura;
	private String mesPago;
	private Double montoPagado;
	private Double impPagado;
	private Double pagoSinImp;
	private String tipoPago;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String ciclos) {
		this.region = ciclos;
	}

//	public String getPagosFacturadosId() {
//		return pagosFacturadosId;
//	}
//
//	public void setPagosFacturadosId(String pagosFacturadosId) {
//		this.pagosFacturadosId = pagosFacturadosId;
//	}

//	public Double getCiclo() {
//		return ciclo;
//	}
//
//	public void setCiclo(Double ciclo) {
//		this.ciclo = ciclo;
//	}
//
//	public String getGrupoIng() {
//		return grupoIng;
//	}
//
//	public void setGrupoIng(String grupoIng) {
//		this.grupoIng = grupoIng;
//	}
//
//	public String getMesFactura() {
//		return mesFactura;
//	}
//
//	public void setMesFactura(String mesFactura) {
//		this.mesFactura = mesFactura;
//	}
//
//	public String getMesPago() {
//		return mesPago;
//	}
//
//	public void setMesPago(String mesPago) {
//		this.mesPago = mesPago;
//	}
//
//	public Double getMontoPagado() {
//		return montoPagado;
//	}
//
//	public void setMontoPagado(Double montoPagado) {
//		this.montoPagado = montoPagado;
//	}
//
//	public Double getImpPagado() {
//		return impPagado;
//	}
//
//	public void setImpPagado(Double impPagado) {
//		this.impPagado = impPagado;
//	}
//
//	public Double getPagoSinImp() {
//		return pagoSinImp;
//	}
//
//	public void setPagoSinImp(Double pagoSinImp) {
//		this.pagoSinImp = pagoSinImp;
//	}
//
//	public String getTipoPago() {
//		return tipoPago;
//	}
//
//	public void setTipoPago(String tipoPago) {
//		this.tipoPago = tipoPago;
//	}

	@Override
	public String toString() {
		return "PagosFacturados [ region=" + region + ", ciclo=" + ciclo
				+ ", grupoIng=" + grupoIng + ", mesFactura=" + mesFactura + ", mesPago=" + mesPago + ", montoPagado="
				+ montoPagado + ", impPagado=" + impPagado + ", pagoSinImp=" + pagoSinImp + ", tipoPago=" + tipoPago + "]";
	}

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

	public String getMesFactura() {
		return mesFactura;
	}

	public void setMesFactura(String mesFactura) {
		this.mesFactura = mesFactura;
	}

	public String getMesPago() {
		return mesPago;
	}

	public void setMesPago(String mesPago) {
		this.mesPago = mesPago;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Double getImpPagado() {
		return impPagado;
	}

	public void setImpPagado(Double impPagado) {
		this.impPagado = impPagado;
	}

	public Double getPagoSinImp() {
		return pagoSinImp;
	}

	public void setPagoSinImp(Double pagoSinImp) {
		this.pagoSinImp = pagoSinImp;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
}
