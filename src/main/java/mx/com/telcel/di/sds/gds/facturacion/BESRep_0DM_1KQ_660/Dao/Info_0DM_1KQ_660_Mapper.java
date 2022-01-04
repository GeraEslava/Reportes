package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;

public class Info_0DM_1KQ_660_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		test.setRegion(rs.getString("REGION"));
		test.setCiclo(rs.getString("CICLO"));
		test.setGrupoIng(rs.getString("GRUPO_ING"));
		test.setMesFactura(rs.getString("MES_FACTURA"));
		test.setMesPago(rs.getString("MES_PAGO"));
		test.setMontoPagado(rs.getDouble("MONTO_PAGADO"));
		test.setImpPagado(rs.getDouble("IMP_PAGADO"));
		test.setPagoSinImp(rs.getDouble("PAGO_SIN_IMP"));
		test.setTipoPago(rs.getString("TIPO_PAGO"));
		return test;
	}
		
}