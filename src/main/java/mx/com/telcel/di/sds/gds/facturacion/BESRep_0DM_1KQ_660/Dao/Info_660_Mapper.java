package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;

public class Info_660_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		test.setRegion(rs.getString("REPORTE660ID"));
		test.setCiclo(rs.getString("REGION"));
		test.setGrupoIng(rs.getString("CICLO"));
		test.setMesFactura(rs.getString("TOTAL"));
		test.setMesPago(rs.getString("MES"));
		test.setMontoPagado(rs.getDouble("MONTO_PAGADO"));
		return test;
	}

}