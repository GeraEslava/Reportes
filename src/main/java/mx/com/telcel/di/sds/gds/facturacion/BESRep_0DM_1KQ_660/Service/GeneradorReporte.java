package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.BaseDatos.BaseDeDatosEmbebida;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao.ExtractorInfoReps_0DM_1KQ_660_Dao;

public class GeneradorReporte implements Constantes {
	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");
	ExtractorInfoReps_0DM_1KQ_660_Dao extractorDao;
	
	private String pathInput;
	private String pathWork;
	private String pathDone;
	private String PREFIX_ARCH_DONE;
	private String EXT_ARCH_DONE;
	private String EXT_ARCH_CTL;
	private String MODO_APP;
	private String MASK_FECHA_NAME_DONE = App.CONFIG.getProperty("MASK_FECHA_NAME_DONE");
	private static final String TERMINADOR_LINEA_UNIX = "\n";
	BaseDeDatosEmbebida dbBibesSqlite = null;
	
	Connection conBibes = null;
	List<LocalDate> mesesReporte = new ArrayList<>();
	
	public GeneradorReporte() {
		this.pathInput = App.CONFIG.getProperty("PATH_INPUT");
		this.pathWork = App.CONFIG.getProperty("PATH_WORK");
		this.pathDone = App.CONFIG.getProperty("PATH_DONE");
		PREFIX_ARCH_DONE = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE");
		EXT_ARCH_DONE = App.CONFIG.getProperty("EXT_ARCH_DONE");
		EXT_ARCH_CTL = App.CONFIG.getProperty("EXT_CTL_DONE");
		MODO_APP = App.CONFIG.getProperty("MODO_APP");
	}
	
	public void contruirReporte() throws IOException, SQLException {
		

	}
	
	private void iniciarBDDEmbebidaSqlite() throws SQLException {
		
		String pathDb = this.pathWork + File.separator + "base." + App.APP_NAME + "." + App.ID_EXEC_EXTERNA +".db";
		dbBibesSqlite = new BaseDeDatosEmbebida(DB_NAME_APP, DB_SQLITE, pathDb);
		conBibes = dbBibesSqlite.getConnection() ;
		extractorDao = new ExtractorInfoReps_0DM_1KQ_660_Dao(conBibes);
		
	}
}
