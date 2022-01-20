package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Cifras0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.GrupoIngreso0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Rep0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.RepCiclos0DM;

public class Info_0DM_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		
//		test.setCiclos(rs.getString("CICLOS"));
		test.setMesFactura(rs.getString("MES_FACTURA"));
//		test.setEtiquetaFechaPago(etiquetaFechaPago);
//		test.setCiclos(ciclos);
//		test.setResumenGrupoIngreso(resumenGrupoIngreso);
//		test.setTotal(rs.getString("MONTO_PAGADO"));
		
		return test;
	}
	
	public static Rep0DM mapearCiclos(ResultSet rs) throws SQLException {
		Rep0DM test = new Rep0DM();
		test.setMesFactura(rs.getString("MES_FACTURA"));
		return test;
	}
	
	public static RepCiclos0DM mapearCiclo(ResultSet rs) throws SQLException {
		RepCiclos0DM test = new RepCiclos0DM();
		test.setCiclo(rs.getString("CICLO"));
		return test;
	}

	public static GrupoIngreso0DM mapearGrupoIng(ResultSet rs) throws SQLException {
		GrupoIngreso0DM test = new GrupoIngreso0DM();
		test.setGrupoIngreso(rs.getString("GRUPO_ING"));
		return test;
	}
	
	public static Cifras0DM mapearCifras(ResultSet rs) throws SQLException {
		Cifras0DM test = new Cifras0DM();
		//test.setTotal(rs.getDouble("TOTAL"));
		test.setTotalPagoAdelantado(rs.getDouble("MONTO_PAGADO"));
		test.setTotalSinImpuestos(rs.getDouble("PAGO_SIN_IMP"));
		return test;
	}	
	
}
