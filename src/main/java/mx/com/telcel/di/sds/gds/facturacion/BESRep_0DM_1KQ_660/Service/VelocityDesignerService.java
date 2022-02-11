package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Rep0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.RepCiclos0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Reporte0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.ReporteDataRevVo;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityDesignerService implements Constantes{
	
	private static final Logger LOG = LoggerFactory.getLogger(VelocityDesignerService.class);
	static List<LocalDate> mesesReporte = new ArrayList<>();
	private static VelocityContext vc = null;
	private static Template plantillaReporte = null;
	
	public static void generarReporte(List<Reporte0DM> reporte, String pathArchSal) throws IOException {
		if(vc == null) {
//			VelocityEngine ve = new VelocityEngine();
//			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, App.PATH_TEMPLATE);
//			ve.init();
//			LOG.info("Cargando el template : " + App.ARCH_TEMPLATE + " en la ruta de configuracion: " + App.PATH_TEMPLATE);
//			plantillaReporte = ve.getTemplate(App.ARCH_TEMPLATE);
//			
//			vc = new VelocityContext();
//			vc.put("String", String.class);
//			vc.put("StrUtl", StringUtils.class);
			inciarVelocity(Constantes.ARCH_TEMPLATE);
			llenar0Dm(reporte, pathArchSal);
		}
		
	}
	
	public static void llenar0Dm(List<Reporte0DM> reporte, String pathArchSal) throws IOException {
		
		//vc.put("f_a_fin",reporte.getPenalizaciones().getfAmtFineqAmigoFacil());
		//calcularMesesReporte();//g: mesesReporte
		Date fechaGen = new Date();
		String anioGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"yyyy");
		String mesGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"MMMM");
		vc.put("anioGeneracion", anioGeneracion);
		vc.put("mesGeneracion", mesGeneracion.toUpperCase());
		//vc.put("mesesReporte", mesesReporte);
		vc.put("pagosFacturados", reporte);//g: hay que pasarlo por for
		vc.put("cadenaIzq",new StringUtils());//g: ajustarCadenaRellenaIzquierda
		
		StringWriter sw = new StringWriter();
		escribir(pathArchSal, sw);
        vc = null;
	}
	
    public static void datos0Dm(Reporte0DM reporte, String pathArchSal) throws IOException {
		vc.put("ciclo", reporte.getCiclo());
		vc.put("region", reporte.getRegion());
		//vc.put("ciclos", reporte.getCiclos());
		
		StringWriter sw = new StringWriter();
		escribir(pathArchSal, sw);
	}
	
	
	public static void  calcularMesesReporte(){
		LocalDate fechaActual = LocalDate.now();
		mesesReporte.add(fechaActual);
		for(byte i = 1 ; i < MESES_X_PROCESAR; i++) {
			LocalDate fechaNueva = fechaActual.minusMonths(i);
			mesesReporte.add(fechaNueva);
		}
	}
	
	public static void inciarVelocity(String template) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, App.PATH_TEMPLATE);
		ve.init();
		LOG.info("Cargando el template : " + template + " en la ruta de configuracion: " + App.PATH_TEMPLATE);
		plantillaReporte = ve.getTemplate(template);
		
		vc = new VelocityContext();
		vc.put("String", String.class);
		//vc.put("StrUtl", StringUtils.class);
	}
	
	public static void generarReporteODM(List<Reporte0DM> reporte, String pathArchSal) throws IOException {
		if(vc == null) {
//			VelocityEngine ve = new VelocityEngine();
//			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, App.PATH_TEMPLATE);
//			ve.init();
//			LOG.info("Cargando el template : " + App.ARCH_TEMPLATE + " en la ruta de configuracion: " + App.PATH_TEMPLATE);
//			plantillaReporte = ve.getTemplate(App.ARCH_TEMPLATE);
//			
//			vc = new VelocityContext();
//			vc.put("String", String.class);
//			vc.put("StrUtl", StringUtils.class);
			
			inciarVelocity(Constantes.ARCH_TEMPLATE);
		}	
			Date fechaGen = new Date();
			String anioGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"yyyy");
			String mesGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"MMMM");
			vc.put("anioGeneracion", anioGeneracion);
			vc.put("mesGeneracion", mesGeneracion.toUpperCase());
			//vc.put("mesesReporte", mesesReporte);
			vc.put("pagosFacturados", reporte);//g: hay que pasarlo por for
			vc.put("cadenaIzq",new StringUtils());//g: ajustarCadenaRellenaIzquierda
			vc.put("anio",listAnio);
			//vc.put("mes",listMes);
			
			StringWriter sw = new StringWriter();
			escribir(pathArchSal, sw);
		
		
	}
	
	public static StringWriter cabezeraODM( String pathArchSal) throws IOException {
		//if(vc == null) {
			inciarVelocity(Constantes.ARCH_TEMPLATE);
		//}	
			Date fechaGen = new Date();
			String anioGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"yyyy");
			String mesGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"MMMM");
			vc.put("anioGeneracion", anioGeneracion);
			vc.put("mesGeneracion", mesGeneracion.toUpperCase());
			//vc.put("mesesReporte", mesesReporte);
			//vc.put("pagosFacturados", reporte);//g: hay que pasarlo por for
			vc.put("cadenaIzq",new StringUtils());//g: ajustarCadenaRellenaIzquierda
//			vc.put("anio",listAnio);
//			vc.put("mes",listMes);listMesAnio
			vc.put("mesAnio",listMesAnio);
			StringWriter sw = new StringWriter();
			escribir(pathArchSal, sw);
	        vc = null;
			return sw;
	}

	
	public static void cuerpoODM(List<Reporte0DM> reporte, String pathArchSal, StringWriter sw1) throws IOException {
			if(vc == null) {
				inciarVelocity(Constantes.ARCH_TEMPLATE_CUERPO);
			}
			vc.put("pagosFacturados", reporte);//g: 
			//vc.put("total", sumaTotal);//g: 
			vc.put("sUt",new StringUtils());//g: ajustarCadenaRellenaIzquierda
	
 			StringWriter sw = new StringWriter();
			escribir(pathArchSal, sw);
	}

	private static void escribir(String pathArchSal, StringWriter sw) throws IOException {
		plantillaReporte.merge(vc, sw);//g: ?
		Files.write(Paths.get(pathArchSal), sw.toString().getBytes(), StandardOpenOption.APPEND );
	}
	
	
	public static void cuerpoODM(List<Reporte0DM> reporte, String pathArchSal, Reporte0DM sumaTotal) throws IOException {
		if(vc == null) {
			inciarVelocity(Constantes.ARCH_TEMPLATE);
		}
		
		Date fechaGen = new Date();
//		String anioGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"yyyy");
//		String mesGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"MMMM");
//		vc.put("anioGeneracion", anioGeneracion);
//		vc.put("mesGeneracion", mesGeneracion.toUpperCase());
		
		vc.put("mesAnio",listMesAnio);
		vc.put("mesAnioC",listMesAnioC);
		
		vc.put("pagosFacturados", reporte);//g: 
		vc.put("total", sumaTotal);//g: 
		vc.put("sUt",new StringUtils());//g: ajustarCadenaRellenaIzquierda

		StringWriter sw = new StringWriter();
		escribir(pathArchSal, sw);
}
	
	
}
