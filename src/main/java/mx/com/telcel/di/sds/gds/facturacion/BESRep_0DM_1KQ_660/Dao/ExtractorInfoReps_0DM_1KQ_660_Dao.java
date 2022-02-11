package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Consultas;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Cifras0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Region0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Rep0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.RepCiclos0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Reporte0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.BaseDatos.OperacionesDB;

public class ExtractorInfoReps_0DM_1KQ_660_Dao implements Consultas, Constantes {

	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");
	private static Connection conBibes;

	public ExtractorInfoReps_0DM_1KQ_660_Dao(Connection conBibes) {
		ExtractorInfoReps_0DM_1KQ_660_Dao.conBibes = conBibes;
		LOG.info("CONSULTA PAGOS FACTURADOS");
//		consultaPagosFacturados = SQL_OBTENER_PAGOSFACTURADOS;
//		return OperacionesDB.getInstance().queryForList(conBibes, consultaPagosFacturados, null);

	}

	public List<Reporte0DM> extraerRegionesXProcesar(String pago) throws SQLException {
		String consulta = SQL_OBTENER_REGIONES;
		LOG.info("Tenemnos la consulta 2: " + consulta );
		return OperacionesDB.getInstance().queryForRegiones(conBibes, consulta, pago);
	}
	
	public List<Reporte0DM> extraerRegionesXProcesarRep(String region) throws SQLException {
		String consulta = SQL_OBTENER_CICLOS;
		LOG.info("Tenemnos la consulta 1 : " + consulta );
		return OperacionesDB.getInstance().queryForRegiones(conBibes, consulta, region);
	}
	
	public List<Reporte0DM> extraerPagosFacturados(String region) throws SQLException {//g:
		String consulta = SQL_OBTENER_CICLOS;
		LOG.info("Tenemnos la consulta 3: " + consulta);
		return OperacionesDB.getInstance().queryForRegiones(conBibes, consulta, region);
	}
	
	public ArrayList<Map<String, String>> extraerDatos() throws SQLException {
		String consulta = SQL_OBTENER_TIPO_PAGO;
		LOG.info("Tenemnos la consulta 4: " + consulta );
		return OperacionesDB.getInstance().queryForDatos(conBibes, consulta, null);
	}
	
	public List<Map<String,String>> extraerTipoPagosXProcesar() throws SQLException {
		String consulta = SQL_OBTENER_TIPO_PAGO;
		LOG.info("Tenemnos la consulta 5: " + consulta );
		return OperacionesDB.getInstance().queryForList(conBibes, consulta, null);
	}
	
	public List<Rep0DM> queryForCiclos() throws SQLException {
		String consulta = SQL_OBTENER_MESFACTURA;
		LOG.info("Tenemnos la consulta 1: " + consulta);
		return OperacionesDB.getInstance().queryForMesFactura(conBibes, consulta);
	}
	
	public List<PagosFacturados> obtenerCiclos(String ciclos) throws SQLException {
		String consulta = SQL_OBTENER_INGRESOS;
		LOG.info("Tenemnos la consulta : " + consulta);
		return OperacionesDB.getInstance().queryForCiclos(conBibes, consulta, ciclos);
	}

	public List<RepCiclos0DM> extraerCiclos() throws SQLException {
		String consulta = SQL_OBTENER_DATOSREP;
		LOG.info("Tenemnos la consulta : " + consulta );
		return OperacionesDB.getInstance().queryForCicl(conBibes, consulta);
	}

	public List<Cifras0DM> extraerMontos() throws SQLException {
		String consulta = SQL_OBTENER_DATOSREP;
		LOG.info("Tenemnos la consulta : " + consulta );
		return OperacionesDB.getInstance().queryForCifras(conBibes, consulta);
	}
	
	
	public List<Region0DM> extraerTipoPagosXProcesar2(String region) throws SQLException {
		String consulta = SQL_OBTENER_TIPO_PAGO_2;
		//String[] reg = {region};
		LOG.info("consulta : " + consulta );
		return OperacionesDB.getInstance().qRegionTipoArchivo(conBibes, consulta, null);
		//return OperacionesDB.getInstance().queryForList(conBibes, consulta, null);
	}
	
	public List<Reporte0DM> extraerRegionesXProcesarRep(String region, List fecha, String ciclo) throws SQLException {//g:
		String consulta =   
				new StringBuilder(" SELECT REGION, CICLO, GRUPO_ING, SUM(MONTO_PAGADO) TOTAL, substr(MES_FACTURA, 5, 6)MES, MES_FACTURA, SUM(IMP_PAGADO) PAGOS_ADELANTADOS, SUM (PAGO_SIN_IMP) SIN_PAGOS_ADELANTADOS, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(12)+"' ) MES0, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(11)+"' ) MES1, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(10)+"' ) MES2, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(9)+"'  ) MES3, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(8)+"'  ) MES4, ")
			    .append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(7)+"'  ) MES5, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(6)+"'  ) MES6, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(5)+"'  ) MES7, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(4)+"'  ) MES8, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(3)+"'  ) MES9, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(2)+"'  ) MES10, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(1)+"'  ) MES11, ")
				.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='"+fecha.get(0)+"'  ) MES12 ")
				.append(" FROM PAGOS_FACTURADOS ")
				.append(" WHERE REGION = ? ")
				//.append(" AND CICLO = ? ")
				//.append(" AND TIPO_PAGO = 'NORMAL' ")
				//.append(" AND substr(MES_FACTURA, 5, 6) = '09' ")
				//.append(" AND substr(MES_FACTURA, 1, 4) = '2020' ")
				.append(" AND MES_FACTURA='"+fecha.get(0) +"'")
				.append(" GROUP BY REGION, CICLO, GRUPO_ING, substr(MES_FACTURA, 5, 6), MES_FACTURA ")
				.append(" ORDER BY CICLO ")
				.toString();
		
		LOG.info("Tenemnos la consulta 3: " + consulta);
		return OperacionesDB.getInstance().queryForRegiones(conBibes, consulta, region, ciclo);
	}	
	
	
	public List<Region0DM> getCiclos(String region, String fecha) throws SQLException {
		String consulta = new StringBuilder("select DISTINCT(CICLO) ")
				.append("FROM PAGOS_FACTURADOS ")
				.append("WHERE ")
				.append("MES_FACTURA = ? ")
				.append("AND REGION = ? ")
				.append("ORDER BY REGION ASC ")
				.toString();
		
		LOG.info("consulta : " + consulta );
		return OperacionesDB.getInstance().ciclosXRegion(conBibes, consulta, region, fecha);
	}
}