package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.GrupoIngreso0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Region0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.RepCiclos0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Reporte0DM;

public class Info_0DM_1KQ_660_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		test.setGrupoIng(rs.getString("GRUPO_ING"));
		test.setMesFactura(rs.getString("MES_FACTURA"));
		test.setRegion(rs.getString("REGION"));
		test.setTipoPago(rs.getString("TIPO_PAGO"));
		return test;
	}
	
	public static GrupoIngreso0DM mapearIngresos(ResultSet rs) throws SQLException {
		GrupoIngreso0DM test = new GrupoIngreso0DM();
		test.setGrupoIngreso(rs.getString("GRUPO_ING"));
		return test;
	} 
		
	public static Region0DM mapearRegiones(ResultSet rs) throws SQLException{
		Region0DM test = new Region0DM();
		test.setGrupoIng(rs.getString("GRUPO_ING"));
		test.setMesFactura(rs.getString("MES_FACTURA"));
		test.setRegion(rs.getString("REGION"));
		test.setTipoPago(rs.getString("TIPO_PAGO"));
		return test;
		
	}
	
	public static Reporte0DM mapearDatos(ResultSet rs) throws SQLException {
		Reporte0DM test = new Reporte0DM();
		test.setRegion(rs.getString("REGION"));
		test.setCiclo(rs.getString("CICLO"));
		test.setGrupoIng(rs.getString("GRUPO_ING"));
		test.setPagosAdelantados(rs.getString("PAGOS_ADELANTADOS"));
		test.setSinPagosAdelantados(rs.getString("SIN_PAGOS_ADELANTADOS"));
		test.setMes(rs.getString("MES"));
		test.setMes0(rs.getString("MES0"));
		test.setMes1(rs.getString("MES1"));
		test.setMes2(rs.getString("MES2"));
		test.setMes3(rs.getString("MES3"));
		test.setMes4(rs.getString("MES4"));
		test.setMes5(rs.getString("MES5"));
		test.setMes6(rs.getString("MES6"));
		test.setMes7(rs.getString("MES7"));
		test.setMes8(rs.getString("MES8"));
		test.setMes9(rs.getString("MES9"));
		test.setMes10(rs.getString("MES10"));
		test.setMes11(rs.getString("MES11"));
		test.setMes12(rs.getString("MES12"));
		test.setTotal(rs.getString("TOTAL"));
		return test;
	}
}