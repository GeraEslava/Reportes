package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.BaseDatos.BaseDeDatosEmbebida;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao.ExtractorInfoReps_0DM_1KQ_660_Dao;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.ArchivoEscrito;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturadosVo;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils.FileUtils;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.TipoArchivo;

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
	
	public void construirReporte() throws IOException, SQLException {
		
		iniciarBDDEmbebidaSqlite();
		Map<TipoArchivo, Map<Byte, List<PagosFacturadosVo>>> reginon = obtenerRegionReportar();
		List<ArchivoEscrito> archivosGenerados = consultaEscribeInformacion(reginon);
		moverDone(archivosGenerados);
	}
	
	private void iniciarBDDEmbebidaSqlite() throws SQLException {
		
		String pathDb = this.pathWork + File.separator + "base." + App.APP_NAME + "." + App.ID_EXEC_EXTERNA +".db";
		dbBibesSqlite = new BaseDeDatosEmbebida(DB_NAME_APP, DB_SQLITE, pathDb);
		conBibes = dbBibesSqlite.getConnection() ;
		extractorDao = new ExtractorInfoReps_0DM_1KQ_660_Dao(conBibes);
		
	}
	
	private TipoArchivo calculaTipoArchivo(String nombreArchivo) {
		if(nombreArchivo != null) {
			String[] compTipoArchivo = nombreArchivo.split("_");
			if(compTipoArchivo.length != 3) {
				return null;
			}
			String tipoArchivo = compTipoArchivo[2].replace(".txt", "");
			return TipoArchivo.fromIdTipoArchivo(tipoArchivo);
		}
		return null;
	}
	
	private void moverDone(List<ArchivoEscrito> archivosGenerados) throws IOException {
		
		String pathDoneFinal = pathDone + File.separator + App.FCH_ENTREGA;		
		FileUtils.crearDirectorio(pathDoneFinal);
		//Aqui escribimos la lista de archivos a enviar
		String pathArchListaProcesados = pathWork 
										+ File.separator 
										+ "ListaArchivosPorEnviar" + "_"
										+ App.APP_NAME + "_" 
										+ App.ID_EXEC_EXTERNA 
										+ EXT_ARCH_DONE;
		//Se mueven los archivos de entrada para conservarlos en la ejecucion
		StringBuilder archivosXEnviar = new StringBuilder();
		for (ArchivoEscrito archivoEscrito : archivosGenerados) {
			FileUtils.moveFileToDirectory(Paths.get(archivoEscrito.getPathFile()).toFile(), pathDoneFinal);
			archivoEscrito.setPathFile(pathDoneFinal + File.separator + Paths.get(archivoEscrito.getPathFile()).toFile().getName());
			archivosXEnviar.append(escribirArchivoProcesado(archivoEscrito));
		}
		if(archivosXEnviar.length() > 0) {
			Files.write(Paths.get(pathArchListaProcesados), archivosXEnviar.toString().getBytes(), StandardOpenOption.CREATE);
		}
	}
	
	private StringBuilder escribirArchivoProcesado(ArchivoEscrito archivoEscrito) {
		StringBuilder str = new StringBuilder(archivoEscrito.getPathFile())
				.append(SEPARADOR_LISTA_ARCHIVOS)
				.append(archivoEscrito.getRegion())
				.append(SEPARADOR_LISTA_ARCHIVOS)
				.append(archivoEscrito.getTipoArchivo().getIdTipoArchivo())
				.append(SEPARADOR_LISTA_ARCHIVOS)
				.append(archivoEscrito.getExtensionArchivo())
				.append(TERMINADOR_LINEA_UNIX);
		return str;
	}
	
	private Map<TipoArchivo, Map<Byte, List<PagosFacturadosVo>>> obtenerRegionReportar() throws IOException, SQLException{
		
		Map<TipoArchivo, List<String>> archivosXProcesar = obtenerArchivosProcesar();
		
		Map<TipoArchivo, Map<Byte, List<PagosFacturadosVo>>> listaCiclosXTipoArchivo = new HashMap<>();

		for(TipoArchivo tipoArchivo : archivosXProcesar.keySet()) {
			List<String> archivos = archivosXProcesar.get(tipoArchivo);
			Map<Byte, List<PagosFacturadosVo>> listaRegion = new HashMap<>();
			for(String archivo : archivos) {
				Byte region = Byte.parseByte(archivo.substring(2,3));
				List<PagosFacturadosVo> listaRegionsA = extractorDao.extraerRegionesXProcesar(archivo);
				listaRegion.put(region, listaRegionsA );
				LOG.info("Procesando el archivo : " + archivo + " de la region: " + region + " con " + listaRegionsA.size() + " region" );
			}
			listaCiclosXTipoArchivo.put(tipoArchivo, listaRegion);
		}
		return listaCiclosXTipoArchivo;
	}
	
	private Map<TipoArchivo, List<String>> obtenerArchivosProcesar() throws IOException, SQLException{
		List<Map<String,String>> archivosXProc =  extractorDao.extraerArchivosXProcesar();
		
		Map<TipoArchivo, List<String>> archivosXProcesar = new HashMap<>();
		
		for(Map<String,String> archivo : archivosXProc) {
			String nombreArchivo = archivo.get("ARCHIVO");
			TipoArchivo tipoArchivo = calculaTipoArchivo(nombreArchivo);
			if(!archivosXProcesar.containsKey(tipoArchivo)) {
				archivosXProcesar.put(tipoArchivo, new ArrayList<>());
			}
			List<String> archivos = archivosXProcesar.get(tipoArchivo);
			archivos.add(nombreArchivo);
		}
		return archivosXProcesar;
	}
	
	private List<ArchivoEscrito> consultaEscribeInformacion(Map<TipoArchivo, Map<Byte, List<PagosFacturadosVo>>> regionesXTipo) throws SQLException, IOException {
		
		List<ArchivoEscrito> archivosGenerados = new ArrayList<ArchivoEscrito>();
		try{
			for(TipoArchivo tipoArchivo : regionesXTipo.keySet()) {
				Map<Byte, List<PagosFacturadosVo>> cuentasXRegion = regionesXTipo.get(tipoArchivo);

				Iterator<Map.Entry<Byte, List<PagosFacturadosVo>>> iterator = cuentasXRegion.entrySet().iterator();
				
			    while (iterator.hasNext()) {
			        Map.Entry<Byte, List<PagosFacturadosVo>> entry = iterator.next();
			        Byte region = entry.getKey();
			        List<PagosFacturadosVo> listaCiclosRegion = entry.getValue();
			        
			        LOG.info("Procesando la region : R0" +  region + " con una cantidad de " + listaCiclosRegion.size());
			        SimpleDateFormat dateFormat = new SimpleDateFormat(MASK_FECHA_NAME_DONE);
					String fechaGeneracion = dateFormat.format(new Date());		
					
			        String pathArchivoReporte = pathWork + File.separator + PREFIX_ARCH_DONE + region + "_" + tipoArchivo.getIdTipoArchivo() + "_" + fechaGeneracion + EXT_ARCH_DONE;
			        String pahtArchivoCtl = pathWork + File.separator + PREFIX_ARCH_DONE + region + "_" + tipoArchivo.getIdTipoArchivo() + "_" + fechaGeneracion + EXT_ARCH_CTL;
			        
			        LOG.info("-- Iniciando con la escritura del archivo : " + pathArchivoReporte );
			        Files.write(Paths.get(pathArchivoReporte), "".getBytes(), StandardOpenOption.CREATE);
					
			        short pagina = 1;
					for(PagosFacturadosVo ciclos : listaCiclosRegion) {
						ciclos.setRegion(String.valueOf(region));
					}
					//Se escribe el archivo de control
					LOG.info("Escribiendo el archivo de control : " + pahtArchivoCtl);
					Files.write(Paths.get(pahtArchivoCtl), "".getBytes(), StandardOpenOption.CREATE);
					
					archivosGenerados.add(new ArchivoEscrito("R0" +  region, pathArchivoReporte, "TXT", tipoArchivo));
					archivosGenerados.add(new ArchivoEscrito("R0" +  region, pahtArchivoCtl, "CTL",tipoArchivo));
					
			    }
			}
		} finally {
			if(conBibes != null ) {
				try {
					conBibes.close();
				} catch (Exception e2) {
					LOG.info(" Error al tratar de cerrar la conexion : " , e2 );
				}
			}
		} 
		return archivosGenerados;
	}
	
}
