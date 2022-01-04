package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Rep0DM;
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
	
	public static void generarReporte(PagosFacturados rep, String pathArchSal) throws IOException {
		if(vc == null) {
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, App.PATH_TEMPLATE);
			ve.init();
			LOG.info("Cargando el template : " + App.ARCH_TEMPLATE + " en la ruta de configuracion: " + App.PATH_TEMPLATE);
			plantillaReporte = ve.getTemplate(App.ARCH_TEMPLATE);
			vc = new VelocityContext();
			vc.put("String", String.class);
			vc.put("StrUtl", StringUtils.class);
			llenar0Dm(rep, pathArchSal);
		}
		
	}
	
	public static void llenar0Dm(PagosFacturados rep, String pathArchSal) throws IOException {
		
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
		vc.put("mes_Factura", rep.getMesFactura());
		vc.put("mes_Pago", rep.getMesPago());
		vc.put("monto_Pagado", rep.getMontoPagado());
		vc.put("imp_Pagado", rep.getImpPagado());
		vc.put("pago_Sin_Imp", rep.getPagoSinImp());
		vc.put("tipo_Pago", rep.getTipoPago());
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
