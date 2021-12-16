package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;

public class Info_660_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		test.setRegion(rs.getString("REPORTE660ID"));
		test.setCiclo(rs.getLong("REGION"));
		test.setGrupoIng(rs.getLong("CICLO"));
		test.setMesFactura(rs.getLong("TOTAL"));
		test.setMesPago(rs.getLong("MES"));
		test.setMontoPagado(rs.getLong("AÑO"));
		return test;
	}

}