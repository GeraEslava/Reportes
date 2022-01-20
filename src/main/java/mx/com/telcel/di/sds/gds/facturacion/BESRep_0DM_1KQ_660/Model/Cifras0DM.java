package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

import java.util.List;

public class Cifras0DM {
	
	private Double Total;
	private Double totalPagoAdelantado;
	private Double totalSinImpuestos;
	private List<Double> fechaPago;
	
	public Double getTotal() {
		return Total;
	}
	public Double getTotalPagoAdelantado() {
		return totalPagoAdelantado;
	}
	public Double getTotalSinImpuestos() {
		return totalSinImpuestos;
	}
	public List<Double> getFechaPago() {
		return fechaPago;
	}
	public void setTotal(Double total) {
		Total = total;
	}
	public void setTotalPagoAdelantado(Double totalPagaAdelantado) {
		this.totalPagoAdelantado = totalPagaAdelantado;
	}
	public void setTotalSinImpuestos(Double totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}
	public void setFechaPago(List<Double> fechaPago) {
		this.fechaPago = fechaPago;
	}
	@Override
	public String toString() {
		return "Cifras0DM [Total=" + Total + ", totalPagaAdelantado=" + totalPagoAdelantado + ", totalSinImpuestos="
				+ totalSinImpuestos + ", fechaPago=" + fechaPago + ", getTotal()=" + getTotal()
				+ ", getTotalPagaAdelantado()=" + getTotalPagoAdelantado() + ", getTotalSinImpuestos()="
				+ getTotalSinImpuestos() + ", getFechaPago()=" + getFechaPago() + "]";
	}
	
	

}
