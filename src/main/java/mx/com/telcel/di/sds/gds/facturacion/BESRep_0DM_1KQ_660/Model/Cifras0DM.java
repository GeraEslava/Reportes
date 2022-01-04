package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

import java.util.List;

public class Cifras0DM {
	
	private Double Total;
	private Double totalPagaAdelantado;
	private Double totalSinImpuestos;
	private List<Double> fechaPago;
	public Double getTotal() {
		return Total;
	}
	public Double getTotalPagaAdelantado() {
		return totalPagaAdelantado;
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
	public void setTotalPagaAdelantado(Double totalPagaAdelantado) {
		this.totalPagaAdelantado = totalPagaAdelantado;
	}
	public void setTotalSinImpuestos(Double totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}
	public void setFechaPago(List<Double> fechaPago) {
		this.fechaPago = fechaPago;
	}
	@Override
	public String toString() {
		return "Cifras0DM [Total=" + Total + ", totalPagaAdelantado=" + totalPagaAdelantado + ", totalSinImpuestos="
				+ totalSinImpuestos + ", fechaPago=" + fechaPago + ", getTotal()=" + getTotal()
				+ ", getTotalPagaAdelantado()=" + getTotalPagaAdelantado() + ", getTotalSinImpuestos()="
				+ getTotalSinImpuestos() + ", getFechaPago()=" + getFechaPago() + "]";
	}
	
	

}
