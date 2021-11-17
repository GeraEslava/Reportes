package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Consultas;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturadosVo;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.BaseDatos.OperacionesDB;


public class ExtractorInfoReps_0DM_1KQ_660_Dao implements Consultas, Constantes {

	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");
	private Connection conBibes;

	public ExtractorInfoReps_0DM_1KQ_660_Dao(Connection conBibes) {

		LOG.info("CONSULTA PAGOS FACTURADOS");
//		consultaPagosFacturados = SQL_OBTENER_PAGOSFACTURADOS;
//		return OperacionesDB.getInstance().queryForList(conBibes, consultaPagosFacturados, null);

	}

	public List<Map<String,String>> extraerArchivosXProcesar() throws SQLException {
		String consulta = SQL_OBTENER_ARCHIVOS_A_GENERAR;
//		LOG.info("Tenemnos la consulta : " + consulta );
		return OperacionesDB.getInstance().queryForList(conBibes, consulta, null);
	}
	
	public List<Map<String, String>> extraerPagosFacturados() throws SQLException {
		String consulta = SQL_OBTENER_PAGOSFACTURADOS;
		LOG.info("Tenemnos la consulta : " + consulta);
		return OperacionesDB.getInstance().queryForList(conBibes, consulta, null);
	}
	
	public List<PagosFacturadosVo> extraerRegionesXProcesar(String nombreArchivo) throws SQLException {
		String consulta = SQL_OBTENER_REGIONES;
//		LOG.info("Tenemnos la consulta : " + consulta );
		return OperacionesDB.getInstance().queryForRegiones(conBibes, consulta, nombreArchivo);
	}

}