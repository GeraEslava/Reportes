package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

public class PagosFacturados {
//	private Long pagosFacturadosId;
//	private String region;
//	private Double ciclo;
//	private String grupoIng;
//	private String mesFactura;
//	private String mesPago;
//	private Double montoPagado;
//	private Double impPagado;
//	private Double pagoSinImp;
//	private String tipoPago;
	
	private String region;
	private Long ciclo;
	private Long grupoIng;
	private Long mesFactura;
	private Long mesPago;
	private Long montoPagado;
	private Long impPagado;
	private Long pagoSinImp;
	private Long tipoPago;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

//	public Long getPagosFacturadosId() {
//		return pagosFacturadosId;
//	}
//
//	public void setPagosFacturadosId(Long pagosFacturadosId) {
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

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public Long getGrupoIng() {
		return grupoIng;
	}

	public void setGrupoIng(Long grupoIng) {
		this.grupoIng = grupoIng;
	}

	public Long getMesFactura() {
		return mesFactura;
	}

	public void setMesFactura(Long mesFactura) {
		this.mesFactura = mesFactura;
	}

	public Long getMesPago() {
		return mesPago;
	}

	public void setMesPago(Long mesPago) {
		this.mesPago = mesPago;
	}

	public Long getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Long montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Long getImpPagado() {
		return impPagado;
	}

	public void setImpPagado(Long impPagado) {
		this.impPagado = impPagado;
	}

	public Long getPagoSinImp() {
		return pagoSinImp;
	}

	public void setPagoSinImp(Long pagoSinImp) {
		this.pagoSinImp = pagoSinImp;
	}

	public Long getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(Long tipoPago) {
		this.tipoPago = tipoPago;
	}
}
