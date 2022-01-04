package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Rep0DM;

public class Info_0DM_Mapper {
	
	public static Rep0DM mapearRegioness(ResultSet rs) throws SQLException {
		Rep0DM test = new Rep0DM();
		
		test.setCiclos(rs.getArray("CICLOS"));
		test.setMesFactura(rs.getString("MES_FACTURA"));
//		test.setEtiquetaFechaPago(etiquetaFechaPago);
//		test.setCiclos(ciclos);
//		test.setResumenGrupoIngreso(resumenGrupoIngreso);
		test.setTotal(rs.getObject("MONTO_PAGADO"));
		
		return test;
	}

}
