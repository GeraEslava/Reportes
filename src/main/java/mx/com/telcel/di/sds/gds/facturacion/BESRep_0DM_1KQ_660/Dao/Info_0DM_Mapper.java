package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
