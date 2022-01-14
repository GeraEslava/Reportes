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
	
	public static void generarReporte(Reporte0DM ciclos, String pathArchSal) throws IOException {
		if(vc == null) {
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, App.PATH_TEMPLATE);
			ve.init();
			LOG.info("Cargando el template : " + App.ARCH_TEMPLATE + " en la ruta de configuracion: " + App.PATH_TEMPLATE);
			plantillaReporte = ve.getTemplate(App.ARCH_TEMPLATE);
			vc = new VelocityContext();
			vc.put("String", String.class);
			vc.put("StrUtl", StringUtils.class);
			llenar0Dm(ciclos, pathArchSal);
		}
		
	}
	
	public static void llenar0Dm(Reporte0DM rep, String pathArchSal) throws IOException {
		
		//vc.put("f_a_fin",reporte.getPenalizaciones().getfAmtFineqAmigoFacil());
		Date fechaGen = new Date();
		String anioGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"yyyy");
		String mesGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"MMMM");
		vc.put("anioGeneracion", anioGeneracion);
		vc.put("mesGeneracion", mesGeneracion.toUpperCase());
		vc.put("mesesReporte", mesesReporte);
		vc.put("region", rep.getRegion());
		vc.put("ciclo", rep.getCiclo());
		vc.put("grupo_Ing", rep.getGrupoIng());
		vc.put("total", rep.getTotal());
		vc.put("pagosAdelantados", rep.getPagosAdelantados());
		vc.put("sinPagosAdelantados", rep.getSinPagosAdelantados());
		vc.put("mes", rep.getMes());
		vc.put("año", rep.getAño());
		vc.put("mes1", rep.getMes1());
		vc.put("mes2", rep.getMes2());
		vc.put("mes3", rep.getMes3());
		vc.put("mes4", rep.getMes4());
		vc.put("mes5", rep.getMes5());
		vc.put("mes6", rep.getMes6());
		vc.put("mes7", rep.getMes7());
		vc.put("mes8", rep.getMes8());
		vc.put("mes9", rep.getMes9());
		vc.put("mes10", rep.getMes10());
		vc.put("mes11", rep.getMes11());
		vc.put("mes12", rep.getMes12());
		
		StringWriter sw = new StringWriter();
		plantillaReporte.merge(vc, sw);
        Files.write(Paths.get(pathArchSal), sw.toString().getBytes(), StandardOpenOption.APPEND );
	}
	
    public static void datos0Dm(Rep0DM reporte, String pathArchSal) throws IOException {
		vc.put("mesFactura", reporte.getMesFactura());
		//vc.put("ciclos", reporte.getCiclos());
		StringWriter sw = new StringWriter();
		plantillaReporte.merge(vc, sw);
        Files.write(Paths.get(pathArchSal), sw.toString().getBytes(), StandardOpenOption.APPEND );
	}
	
	
	private void calcularMesesReporte(){
		LocalDate fechaActual = LocalDate.now();
		mesesReporte.add(fechaActual);
		for(byte i = 1 ; i < MESES_X_PROCESAR; i++) {
			LocalDate fechaNueva = fechaActual.minusMonths(i);
			mesesReporte.add(fechaNueva);
		}
	}
}
