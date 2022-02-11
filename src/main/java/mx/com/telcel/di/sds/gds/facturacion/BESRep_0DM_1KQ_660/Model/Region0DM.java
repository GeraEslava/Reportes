package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

public class Region0DM {

	private String region;
	private String grupoIng;
	private String mesFactura;
	private String tipoPago;
	private String ciclo;
	
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getRegion() {
		return region;
	}
	public String getGrupoIng() {
		return grupoIng;
	}
	public String getMesFactura() {
		return mesFactura;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setGrupoIng(String grupoIng) {
		this.grupoIng = grupoIng;
	}
	public void setMesFactura(String mesFactura) {
		this.mesFactura = mesFactura;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	@Override
	public String toString() {
		return "Region0DM [region=" + region + ", grupoIng=" + grupoIng + ", mesFactura=" + mesFactura + ", tipoPago="
				+ tipoPago + ", ciclo=" + ciclo + "]";
	}

	
}
