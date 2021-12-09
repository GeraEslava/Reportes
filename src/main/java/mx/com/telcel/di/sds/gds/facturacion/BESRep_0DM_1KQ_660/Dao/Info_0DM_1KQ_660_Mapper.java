package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;

public class Info_0DM_1KQ_660_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		test.setRegion(rs.getString("REGION"));
		test.setCiclo(rs.getLong("CICLO"));
		test.setGrupoIng(rs.getLong("GRUPO_ING"));
		test.setMesFactura(rs.getLong("MES_FACTURA"));
		test.setMesPago(rs.getLong("MES_PAGO"));
		test.setMontoPagado(rs.getLong("MONTO_PAGADO"));
		test.setImpPagado(rs.getLong("IMP_PAGADO"));
		test.setPagoSinImp(rs.getLong("PAGO_SIN_IMP"));
		test.setTipoPago(rs.getLong("TIPO_PAGO"));
		return test;
	}
		
}