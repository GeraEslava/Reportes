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
	
	public List<Reporte0DM> extraerRegionesXProcesarRep(String pago) throws SQLException {
		String consulta = SQL_OBTENER_CICLOS;
		LOG.info("Tenemnos la consulta 1 : " + consulta );
		return OperacionesDB.getInstance().queryForRegiones(conBibes, consulta, pago);
	}
	
	public List<Reporte0DM> extraerPagosFacturados(String pago) throws SQLException {
		String consulta = SQL_OBTENER_CICLOS;
		LOG.info("Tenemnos la consulta 3: " + consulta);
		return OperacionesDB.getInstance().queryForRegiones(conBibes, consulta, pago);
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
	
}