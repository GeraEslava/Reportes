package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;

public class Info_1KQ_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		test.setRegion(rs.getString("REPORTE1KQID"));
		test.setCiclo(rs.getLong("CF"));
		test.setGrupoIng(rs.getLong("GRUPOING"));
		test.setMesFactura(rs.getLong("AJUSTES"));
		test.setMesPago(rs.getLong("TOTAL"));
		test.setMontoPagado(rs.getLong("MES"));
		test.setImpPagado(rs.getLong("AÑO"));

		return test;
	}

}