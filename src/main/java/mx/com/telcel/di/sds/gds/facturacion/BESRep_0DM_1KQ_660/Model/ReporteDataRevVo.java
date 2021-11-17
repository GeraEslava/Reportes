package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

public class ReporteDataRevVo {
	public PagosFacturadosVo region;
	public Reporte0DM reporte0DM;
	public Reporte1KQ reporte1KQ;
	public Reporte660 reporte660;
	
	public PagosFacturadosVo getRegion() {
		return region;
	}
	public void setRegion(PagosFacturadosVo region) {
		this.region = region;
	}
	public Reporte0DM getReporte0DM() {
		return reporte0DM;
	}
	public void setReporte0DM(Reporte0DM reporte0dm) {
		reporte0DM = reporte0dm;
	}
	public Reporte1KQ getReporte1KQ() {
		return reporte1KQ;
	}
	public void setReporte1KQ(Reporte1KQ reporte1kq) {
		reporte1KQ = reporte1kq;
	}
	public Reporte660 getReporte660() {
		return reporte660;
	}
	public void setReporte660(Reporte660 reporte660) {
		this.reporte660 = reporte660;
	}
	@Override
	public String toString() {
		return "ReporteDataRevVo [region=" + region + ", reporte0DM=" + reporte0DM + ", reporte1KQ=" + reporte1KQ
				+ ", reporte660=" + reporte660 + "]";
	}
}
