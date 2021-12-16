package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

public class PagosFacturadosVo {
	
	private Long pagosFacturadosId;
	private String region;
	private Long ciclo;
	private Long grupoIng;
	private Long mesFactura;
	private Long mesPago;
	private Long montoPagado;
	private Long impPagado;
	private Long pagoSinImp;
	private Long tipoPago;
	private String nombreArchivo;
	
	public Long getPagosFacturadosId() {
		return pagosFacturadosId;
	}
	public void setPagosFacturadosId(Long pagosFacturadosId) {
		this.pagosFacturadosId = pagosFacturadosId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	@Override
	public String toString() {
		return "PagosFacturadosVo [pagosFacturadosId=" + pagosFacturadosId + ", region=" + region + ", ciclo=" + ciclo
				+ ", grupoIng=" + grupoIng + ", mesFactura=" + mesFactura + ", mesPago=" + mesPago + ", montoPagado="
				+ montoPagado + ", impPagado=" + impPagado + ", pagoSinImp=" + pagoSinImp + ", tipoPago=" + tipoPago
				+ ", nombreArchivo=" + nombreArchivo + "]";
	}

}
