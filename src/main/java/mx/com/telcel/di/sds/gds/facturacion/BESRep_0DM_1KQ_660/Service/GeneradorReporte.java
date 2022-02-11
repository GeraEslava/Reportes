package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.SQLException;
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
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.TipoArchivo;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao.ExtractorInfoReps_0DM_1KQ_660_Dao;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.ArchivoEscrito;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Region0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Rep0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Reporte0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils.FileUtils;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils.StringUtils;

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
//	private String MASK_FECHA_NAME_DONE = App.CONFIG.getProperty("MASK_FECHA_NAME_DONE");
//	private String PREFIX_ARCH_DONE_REPORTE_0DM_C = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_0DM_C");
//	private String PREFIX_ARCH_DONE_REPORTE_0DM_D = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_0DM_D");
//	private String PREFIX_ARCH_DONE_REPORTE_1KQ_QBK = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_1KQ_QBK");
//	private String PREFIX_ARCH_DONE_REPORTE_1KQ_QMD = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_1KQ_QMD");
//	private String PREFIX_ARCH_DONE_REPORTE_1KQ_QPR = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_1KQ_QPR");
//	private String PREFIX_ARCH_DONE_REPORTE_660_CCB = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_660_CCB");
//	private String PREFIX_ARCH_DONE_REPORTE_660_SCB = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_660_SCB");
//	private String PREFIX_ARCH_DONE_REPORTE_660_UCB = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_660_UCB");
//	private static final String TERMINADOR_LINEA_UNIX = "\n";
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
		//Map<TipoArchivo, Map<Byte, List<PagosFacturados>>> region = obtenerRegionReportar();
		//List<ArchivoEscrito> archivosGenerados = consultaEscribeInformacion(region);	
		
//		Map<TipoArchivo, Map<Byte, List<Reporte0DM>> > region1 = obtenerRegionReportar();
//		List<ArchivoEscrito> archivosGenerados1 = consultaEscribeInformacion(region1);		
				
		List<ArchivoEscrito> archivosGenerados1 = obtenerRegionReportar2();
		
		moverDone(archivosGenerados1);
		//obtenerRep0dm();
	}
	
	private void iniciarBDDEmbebidaSqlite() throws SQLException {
		
		String pathDb = this.pathWork + File.separator + "base." + App.APP_NAME + "." + App.ID_EXEC_EXTERNA +".db";
		dbBibesSqlite = new BaseDeDatosEmbebida(DB_NAME_APP, DB_SQLITE, pathDb);
		conBibes = dbBibesSqlite.getConnection() ;
		extractorDao = new ExtractorInfoReps_0DM_1KQ_660_Dao(conBibes);
		
	}
	/**
	 * corta el argumento nombreArchivo con split("_") y lo agrega a un array
	 * despues quita .txt con ramplace() y valida si se encuentra "nombreArchivo"
	 * en TipoArchivo
	 * @param nombreArchivo
	 * @return
	 */
	private TipoArchivo calculaTipoArchivo(String nombreArchivo) {
		if(nombreArchivo != null) {
			String[] compTipoArchivo = nombreArchivo.split("_");
			if(compTipoArchivo.length != 3) {
				return null;
			}
			String tipoArchivo = compTipoArchivo[2].replace(".txt", "");
			LOG.debug("calculaTipoArchivo: "+TipoArchivo.fromIdTipoArchivo(tipoArchivo));
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
			//archivosXEnviar.append(escribirArchivoProcesado(archivoEscrito));
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
	
	
	//Para generar archivos | obtiene mapaRegion y lista de ciclos de region 
	
	private Map<TipoArchivo, Map<Byte, List<Reporte0DM>>> obtenerRegionReportar() throws IOException, SQLException{
		
		Map<TipoArchivo, List<String>> archivosXProcesar = obtenerArchivosProcesar();//g: tiposArchivo, lista<nombreArchiovo> | nombresArchivo = tabla region
		
		Map<TipoArchivo, Map<Byte, List<Reporte0DM>>> listaCiclosXTipoArchivo = new HashMap<>();

		for(TipoArchivo tipoArchivo : archivosXProcesar.keySet()) {
			LOG.debug("tipoArchivo: "+tipoArchivo);
			List<String> archivos = archivosXProcesar.get(tipoArchivo);
			Map<Byte, List<Reporte0DM>> mapRegionCiclos = new HashMap<>();//g:mapa para guardar byte y la cosulta de ciclos
			for(String region : archivos) {
				LOG.info("region: "+region);
				Byte archivo = Byte.parseByte(region.substring(2,3));//g: RO9 queda 9
				List<Reporte0DM> listaCiclos = extractorDao.extraerRegionesXProcesarRep(region);//no se usa el parametro "region"
				//pero si se usan la consulta seria sobre region = region y todas | lo que significa que todas son la misma consulta
				mapRegionCiclos.put(archivo, listaCiclos );//g: Map<Byte, List<Reporte0DM>>
				LOG.info("Procesando el archivo : " + archivo + " de la region: " + region + " con " + listaCiclos.size() + " regione(s)" );
			}
			LOG.info("numero de  archivos: " + archivos.size());//g: 
			LOG.info("mapRegion: " + mapRegionCiclos.size());//g: 
			LOG.info("listaCiclosXTipoArchivo: " + listaCiclosXTipoArchivo.size());//g: 
			listaCiclosXTipoArchivo.put(tipoArchivo, mapRegionCiclos);
		}
		return listaCiclosXTipoArchivo;
	}
	
	private Map<TipoArchivo, List<String>> obtenerArchivosProcesar() throws IOException, SQLException{
	//	List<Map<String,String>> archivosXProc =  extractorDao.extraerTipoPagosXProcesar(); //g: SQL_OBTENER_TIPO_PAGO map<nombreColumna,valorcolumna>
		
	/*	List<Map<String,String>> archivosXProc =  extractorDao.extraerTipoPagosXProcesar2(null);
		Map<TipoArchivo, List<String>> archivosXProcesar = new HashMap<>();
		
		for(Map<String,String> archivo : archivosXProc) {
			String nombreArchivo = archivo.get("REGION");
			TipoArchivo tipoArchivo = calculaTipoArchivo(nombreArchivo);//g: regresa null
			LOG.info("tipoArchivo : " + tipoArchivo );//g:asocia el tipoArchivo con todos los nombreArchivo
			if(!archivosXProcesar.containsKey(tipoArchivo)) {
				archivosXProcesar.put(tipoArchivo, new ArrayList<>());
			}
			List<String> archivos = archivosXProcesar.get(tipoArchivo);
			archivos.add(nombreArchivo);
		}*/
		//quda pendiente validar la funcion calculaTipoArchivo()
		//List<Region0DM> archivosXProc = extractorDao.extraerTipoPagosXProcesar2(null);
		//solo como test para una region
		List<Region0DM> archivosXProc = new ArrayList<Region0DM>();
		Region0DM region0DM = new Region0DM();
		region0DM.setRegion("R01");
		archivosXProc.add(region0DM);
		//
		List<String> listTipoArchivos = new ArrayList<String>();
		
		for (Region0DM elemento : archivosXProc) {
			listTipoArchivos.add(elemento.getRegion());
		}
		
		Map<TipoArchivo, List<String>> archivosXProcesar = new HashMap<>();
		archivosXProcesar.put(null, listTipoArchivos);
		
		return archivosXProcesar;
	}
	
	
	private List<ArchivoEscrito> consultaEscribeInformacion(Map<TipoArchivo, Map<Byte, List<Reporte0DM>>> regionesXTipo) throws SQLException, IOException {
		
		List<ArchivoEscrito> archivosGenerados = new ArrayList<ArchivoEscrito>();
		try{
			for(TipoArchivo tipoArchivo : regionesXTipo.keySet()) {
				Map<Byte, List<Reporte0DM>> cuentasXRegion = regionesXTipo.get(tipoArchivo);

				Iterator<Map.Entry<Byte, List<Reporte0DM>>> iterator = cuentasXRegion.entrySet().iterator();
				int i=0;
			    while (iterator.hasNext()) {//g: recorrido del mapa
			        Map.Entry<Byte, List<Reporte0DM>> entry = iterator.next();
			        Byte region = entry.getKey();
			        List<Reporte0DM> listaCiclosRegion = entry.getValue();
			        
			        LOG.info("i while  : " + i++);
			        LOG.info("Procesando la region : R0" +  region + " con una cantidad de " + listaCiclosRegion.size());
//			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//					String fechaGeneracion = dateFormat.format(new Date());	
					
					String fechaGeneracion = StringUtils.generarFechaEnFormato(new Date(),"yyyyMMddhhmmss");
					
					/*SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMM");
					String fechaGeneracion1 = dateFormat1.format(new Date());*/	
                  //PREFIX_ARCH_DONE_REPORTE_0DM_C=BES_R0
	     		  //PREFIX_ARCH_DONE_REPORTE_0DM_D=BES_R0
//					EXT_ARCH_DONE=.txt
//					EXT_CTL_DONE=_CTL.txt
			        String pathArchivoReporte = pathWork + File.separator + PREFIX_ARCH_DONE_REPORTE_0DM_C + region + "_"   + fechaGeneracion + EXT_ARCH_DONE;
			        //String pahtArchivoCtl = pathWork + File.separator + PREFIX_ARCH_DONE_REPORTE_0DM_C + region + "_"  + fechaGeneracion + EXT_ARCH_CTL;//g: nunca se escribe
			        
			        LOG.info("-- Iniciando con la escritura del archivo : " + pathArchivoReporte );
			        Files.write(Paths.get(pathArchivoReporte), "".getBytes(), StandardOpenOption.CREATE);//g: .txt

				/*for(Reporte0DM ciclo : listaCiclosRegion) {//g: recorrido de la lista
					//	ciclo.setCiclo(String.valueOf(tipoArchivo));//g: no entiendo por que seta con tipoArchivo
						LOG.info("for mapear Reporte: " +ciclo);
						//Rep0DM reporte = mapearInfoReporte(ciclo);
						//PagosFacturados reporte = mapearInfoReporte(ciclo);
						//List<Reporte0DM> reporte = mapearInfoReporte(ciclo);//g: ciclo no se usa, consulta SQL_OBTENER_CICLOS y mapea en ojbtejo lista Reporte0DM
						List<Reporte0DM> reporte = extractorDao.extraerPagosFacturados(ciclo.getRegion());//g: es igual ha este extraerRegionesXProcesarRep llaman al mismo query 
						VelocityDesignerService.generarReporte(reporte, pathArchivoReporte);						
						//pagina++;
					}*/
			        VelocityDesignerService.generarReporte(listaCiclosRegion, pathArchivoReporte);	
					//Se escribe el archivo de control
					//LOG.info("Escribiendo el archivo de control : " + pahtArchivoCtl);//g: nunca se escribe
					//Files.write(Paths.get(pahtArchivoCtl), "".getBytes(), StandardOpenOption.CREATE);
					//Files.write(Paths.get(pathArchivoReporte), "".getBytes(), StandardOpenOption.CREATE);
					
					archivosGenerados.add(new ArchivoEscrito("R0" +  region, pathArchivoReporte, "TXT", tipoArchivo));
					//archivosGenerados.add(new ArchivoEscrito("R0" +  region, pahtArchivoCtl, "CTL",tipoArchivo));
					
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
	
//	private PagosFacturados mapearInfoReporte(PagosFacturados ciclos) throws SQLException  {
//		PagosFacturados reporte = new PagosFacturados();
//		
//		List<PagosFacturados> rep = extractorDao.extraerPagosFacturados(ciclos.getRegion());
//		System.out.println("rep :  --- "+ rep);
//		PagosFacturados pago = (!rep.isEmpty())? rep.get(0) : new PagosFacturados();
//		
//		for(PagosFacturados ciclo : rep) {
//			reporte.setCiclo(ciclo.getCiclo());
//			reporte.setGrupoIng(ciclo.getGrupoIng());
//			reporte.setImpPagado(ciclo.getImpPagado());
//			reporte.setMesFactura(ciclo.getMesFactura());
//			reporte.setMesPago(ciclo.getMesPago());
//			reporte.setMontoPagado(ciclo.getMontoPagado());
//			reporte.setPagoSinImp(ciclo.getPagoSinImp());
//			reporte.setRegion(ciclo.getRegion());
//			reporte.setTipoPago(ciclo.getTipoPago());
//		}
//		
//		System.out.println("reporte llenado: " +reporte);
//		
//		return  reporte;
//}	
	
	
	private List<Reporte0DM> mapearInfoReporte(Reporte0DM ciclos) throws SQLException  {
	//	Reporte0DM reporte = new Reporte0DM();
		
		List<Reporte0DM> rep = extractorDao.extraerPagosFacturados(ciclos.getRegion());
		LOG.info("rep :  --- "+ rep);

		return rep;
}	
//	private List<PagosFacturados> obtenerMeses(Map<TipoArchivo, Map<Byte, List<PagosFacturados>>> region) throws IOException, SQLException{
//		List<PagosFacturados> meses = ArrayList();
//		
//		for(PagosFacturados mes : meses) {
//			//LOG.debug(mes);
//			System.out.println("Meses: "+mes.getGrupoIng());
//		}
//		return meses;
//	}
	
	private Rep0DM obtenerRep0dm() throws IOException, SQLException{
		Rep0DM meses = new Rep0DM();
		//List<Reporte0DM> datos = extractorDao.obtenerCiclos();
		List<Rep0DM> datos1 = extractorDao.queryForCiclos();
		//System.out.println(datos);
		LOG.info("1: " +datos1);
		return meses;

	}


//	private List<PagosFacturados> obtenerFecha() throws IOException, SQLException {
//
//		List<String> archivosXProcesar = obtenerArchivosProcesar();
//
//		List<PagosFacturados> listaCiclosXTipoArchivo = new ArrayList<>();
//
//		for (TipoArchivo tipoArchivo : archivosXProcesar.keySet()) {
//			List<String> archivos = archivosXProcesar.get(tipoArchivo);
//			Map<Byte, List<PagosFacturados>> listaRegion = new HashMap<>();
//			for (String archivo : archivos) {
//				LOG.debug(archivo);
//				Byte region = Byte.parseByte(archivo.substring(2, 3));
//				List<PagosFacturados> listaRegionsA = extractorDao.extraerRegionesXProcesar(archivo);
//				listaRegion.put(region, listaRegionsA);
//				LOG.info("Procesando el archivo : " + archivo + " de la region: " + region + " con "
//						+ listaRegionsA.size() + " region");
//			}
//			listaCiclosXTipoArchivo.add(tipoArchivo, listaRegion);
//		}
//		return listaCiclosXTipoArchivo;
//	}
	
	//Para generar archivos | obtiene mapaRegion y lista de ciclos de region 
	
	private List<ArchivoEscrito> obtenerRegionReportar3() throws IOException, SQLException{
		
		Map<TipoArchivo, List<String>> archivosXProcesar = obtenerArchivosProcesar();//g: tiposArchivo=null, lista<nombreArchiovo> | nombresArchivo = tabla region
		
		//Map<TipoArchivo, Map<Byte, List<Reporte0DM>>> listaCiclosXTipoArchivo = new HashMap<>();

		List<ArchivoEscrito> archivosGenerados = new ArrayList<ArchivoEscrito>();
		try {
			
		for(TipoArchivo tipoArchivo : archivosXProcesar.keySet()) {
			LOG.info("tipoArchivo: "+tipoArchivo);
			List<String> archivos = archivosXProcesar.get(tipoArchivo);
			//Map<Byte, List<Reporte0DM>> mapRegionCiclos = new HashMap<>();//g:mapa para guardar byte y la cosulta de ciclos
			for(String region : archivos) {//for para Regiones
				LOG.info("region: "+region);
				Byte archivo = Byte.parseByte(region.substring(2,3));//g: RO9 queda 9
				//ciclos y fecha
				List<String> fechaMesAnio = StringUtils.calcularMesesReporte("12/09/2021");
				LOG.info("fechaMesAnio: "+fechaMesAnio.get(0));
				List<Region0DM> listCicloRegion = extractorDao.getCiclos(region, fechaMesAnio.get(0));
				LOG.info("listCicloRegion: "+listCicloRegion.size());
				//archivo
				String fechaGeneracion = StringUtils.generarFechaEnFormato(new Date(),"yyyyMMddhhmmss");
				String pathArchivoReporte = pathWork + File.separator + PREFIX_ARCH_DONE_REPORTE_0DM_C + region + "_"   + fechaGeneracion + EXT_ARCH_DONE;
				Files.write(Paths.get(pathArchivoReporte), "".getBytes(), StandardOpenOption.CREATE);//g: .txt
				
				StringWriter sw = VelocityDesignerService.cabezeraODM( pathArchivoReporte);	
				
				for (Region0DM region0DM : listCicloRegion) {//for para ciclos en una region
					
					List<Reporte0DM> listaCiclos = extractorDao.extraerRegionesXProcesarRep(region, fechaMesAnio, region0DM.getCiclo() );
					//	mapRegionCiclos.put(archivo, listaCiclos );//g: Map<Byte, List<Reporte0DM>>
					
					LOG.info("Procesando el archivo : " + archivo + " de la region: " + region + " con " + listaCiclos.size() + " ciclo" );
					//generar reporte con vm
						
					//VelocityDesignerService.generarReporteODM(listaCiclos, pathArchivoReporte);	
					//VelocityDesignerService.cuerpoODM(listaCiclos, pathArchivoReporte, sw);
					
				}
				
				
				archivosGenerados.add(new ArchivoEscrito("R0" +  region, pathArchivoReporte, "TXT", tipoArchivo));
				
			}
			LOG.info("numero de  archivos: " + archivos.size());//g: 
			//LOG.info("tamanio del mapRegion: " + mapRegionCiclos.size());//g: 
			//LOG.info("listaCiclosXTipoArchivo: " + listaCiclosXTipoArchivo.size());//g: 
			//listaCiclosXTipoArchivo.put(tipoArchivo, mapRegionCiclos);
		}
		
		}finally {
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
	
	private List<ArchivoEscrito> obtenerRegionReportar2() throws IOException, SQLException{
		
		Map<TipoArchivo, List<String>> archivosXProcesar = obtenerArchivosProcesar();//g: tiposArchivo=null, lista<nombreArchiovo> | nombresArchivo = tabla region
		
		//Map<TipoArchivo, Map<Byte, List<Reporte0DM>>> listaCiclosXTipoArchivo = new HashMap<>();

		List<ArchivoEscrito> archivosGenerados = new ArrayList<ArchivoEscrito>();
		try {
			
		for(TipoArchivo tipoArchivo : archivosXProcesar.keySet()) {
			LOG.info("tipoArchivo: "+tipoArchivo);
			List<String> archivos = archivosXProcesar.get(tipoArchivo);
			//Map<Byte, List<Reporte0DM>> mapRegionCiclos = new HashMap<>();//g:mapa para guardar byte y la cosulta de ciclos
			for(String region : archivos) {//for para Regiones
				LOG.info("region: "+region);
				Byte archivo = Byte.parseByte(region.substring(2,3));//g: RO9 queda 9
				//ciclos y fecha
				
				String fechaGeneracion = StringUtils.generarFechaEnFormato(new Date(),"yyyyMMddhhmmss");
				String pathArchivoReporte = pathWork + File.separator + PREFIX_ARCH_DONE_REPORTE_0DM_C + region + "_"   + fechaGeneracion + EXT_ARCH_DONE;
				Files.write(Paths.get(pathArchivoReporte), "".getBytes(), StandardOpenOption.CREATE);//g: .txt
				
				List<String> fechas = StringUtils.calcularMesesReporte2("12/09/2021");
				for (String fecha : fechas) {
					
				
				
				//List<String> fechaMesAnio = StringUtils.calcularMesesReporte("12/09/2021");
				List<String> fechaMesAnio = StringUtils.calcularMesesReporte(fecha);
				LOG.info("fechaMesAnio: "+fechaMesAnio.get(0));
				
//				List<Region0DM> listCicloRegion = extractorDao.getCiclos(region, fechaMesAnio.get(0));
//				LOG.info("listCicloRegion: "+listCicloRegion.size());
				
				
				//archivo
//				String fechaGeneracion = StringUtils.generarFechaEnFormato(new Date(),"yyyyMMddhhmmss");
//				String pathArchivoReporte = pathWork + File.separator + PREFIX_ARCH_DONE_REPORTE_0DM_C + region + "_"   + fechaGeneracion + EXT_ARCH_DONE;
//				Files.write(Paths.get(pathArchivoReporte), "".getBytes(), StandardOpenOption.CREATE);//g: .txt
				
				//StringWriter sw = VelocityDesignerService.cabezeraODM( pathArchivoReporte);	
				
//				for (Region0DM region0DM : listCicloRegion) {//for para ciclos en una region
					
					List<Reporte0DM> listas = extractorDao.extraerRegionesXProcesarRep(region, fechaMesAnio, null);
					//	mapRegionCiclos.put(archivo, listaCiclos );//g: Map<Byte, List<Reporte0DM>>
					String ciclo = "";
					List<Reporte0DM> reporte0DMlist = new ArrayList<Reporte0DM>(); //g:llevara todo los elementos 
					Reporte0DM reporte0DMSuma = new Reporte0DM();
					Reporte0DM sumaTotal = new Reporte0DM();
					List<Reporte0DM> reporte = new ArrayList<Reporte0DM>();  //g: lista de ciclos
					
					for (Reporte0DM reporte0DM : listas) {//for para hacer la suma y en una lista todo los registros de un ciclo 
						if(ciclo.equals(reporte0DM.getCiclo()) ) {
						   
					    	suma(reporte0DMSuma, reporte0DM);
					    	
					    	reporte0DM.setCiclo("");
					    	reporte.add(reporte0DM);
							
					    }else {
					    	
					    	if( !"".equals(ciclo) ) {
					    		
					    		suma(sumaTotal, reporte0DMSuma);
					    		sumaTotal.setCiclo("TOTAL");
					    		sumaTotal.setGrupoIng("   ");
					    		
					    		reporte0DMlist.add(reporte0DMSuma);
					    		reporte0DMlist.addAll(reporte);
					    		reporte.clear();
					    		reporte0DMSuma=new Reporte0DM();
					    	}
					    	ciclo = reporte0DM.getCiclo();
					    	
					    	reporte0DMSuma.setCiclo(reporte0DM.getCiclo());
					    	reporte0DMSuma.setGrupoIng("");
					    	
					    	reporte0DMSuma.setTotal(reporte0DM.getTotal());
					    	reporte0DMSuma.setPagosAdelantados(reporte0DM.getPagosAdelantados());
					    	reporte0DMSuma.setSinPagosAdelantados(reporte0DM.getSinPagosAdelantados());
					    	
					    	reporte0DMSuma.setMes(reporte0DM.getMes());
					    	reporte0DMSuma.setMes0(reporte0DM.getMes0());
					    	reporte0DMSuma.setMes1(reporte0DM.getMes1());
					    	reporte0DMSuma.setMes2(reporte0DM.getMes2());
					    	reporte0DMSuma.setMes3(reporte0DM.getMes3());
					    	reporte0DMSuma.setMes4(reporte0DM.getMes4());
					    	reporte0DMSuma.setMes5(reporte0DM.getMes5());
					    	reporte0DMSuma.setMes6(reporte0DM.getMes6());
					    	reporte0DMSuma.setMes7(reporte0DM.getMes7());
					    	reporte0DMSuma.setMes8(reporte0DM.getMes8());
					    	reporte0DMSuma.setMes9(reporte0DM.getMes9());
					    	reporte0DMSuma.setMes10(reporte0DM.getMes10());
					    	reporte0DMSuma.setMes11(reporte0DM.getMes11());
					    	reporte0DMSuma.setMes12(reporte0DM.getMes12());
					    	
					    	reporte0DM.setCiclo("");
					    	reporte.add(reporte0DM);//g: agrag el 1er objeto
					    }
					} 

					LOG.info("Procesando el archivo : " + archivo + " de la region: " + region + " con " + listas.size() + " ciclo" );
					//generar reporte con vm
						
					//VelocityDesignerService.generarReporteODM(listaCiclos, pathArchivoReporte);	
					//VelocityDesignerService.cuerpoODM(listaCiclos, pathArchivoReporte, sw);
					VelocityDesignerService.cuerpoODM(reporte0DMlist, pathArchivoReporte, sumaTotal);
					
				}
				
				
				archivosGenerados.add(new ArchivoEscrito("R0" +  region, pathArchivoReporte, "TXT", tipoArchivo));
				
			}
			LOG.info("numero de  archivos: " + archivos.size());//g: 
			//LOG.info("tamanio del mapRegion: " + mapRegionCiclos.size());//g: 
			//LOG.info("listaCiclosXTipoArchivo: " + listaCiclosXTipoArchivo.size());//g: 
			//listaCiclosXTipoArchivo.put(tipoArchivo, mapRegionCiclos);
		}
		
		}finally {
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

	private void suma(Reporte0DM suma, Reporte0DM reporte0DM) {
		
		suma.setTotal(suma.getTotal() + reporte0DM.getTotal());
		suma.setPagosAdelantados(suma.getTotal() + reporte0DM.getPagosAdelantados());
		suma.setSinPagosAdelantados(suma.getTotal() + reporte0DM.getSinPagosAdelantados());
		
		suma.setMes(suma.getMes() + reporte0DM.getMes());
		suma.setMes0(suma.getMes0() + reporte0DM.getMes0());
		suma.setMes1(suma.getMes1() + reporte0DM.getMes1());
		suma.setMes2(suma.getMes2() + reporte0DM.getMes2());
		suma.setMes3(suma.getMes3() + reporte0DM.getMes3());
		suma.setMes4(suma.getMes4() + reporte0DM.getMes4());
		suma.setMes5(suma.getMes5() + reporte0DM.getMes5());
		suma.setMes6(suma.getMes6() + reporte0DM.getMes6());
		suma.setMes7(suma.getMes7() + reporte0DM.getMes7());
		suma.setMes8(suma.getMes8() + reporte0DM.getMes8());
		suma.setMes9(suma.getMes9() + reporte0DM.getMes9());
		suma.setMes10(suma.getMes10() + reporte0DM.getMes10());
		suma.setMes11(suma.getMes11() + reporte0DM.getMes11());
		suma.setMes12(suma.getMes12() + reporte0DM.getMes12());
	}
}
