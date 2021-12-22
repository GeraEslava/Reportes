package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Reporte0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.ReporteDataRevVo;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityDesignerService implements Constantes{
	
	private static final Logger LOG = LoggerFactory.getLogger(VelocityDesignerService.class);

	private static VelocityContext vc = null;
	private static Template plantillaReporte = null;
	
	public static void generarReporte(PagosFacturados reporte, String pathArchSal , int pagina) throws IOException {
		if(vc == null) {
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, App.PATH_TEMPLATE);
			ve.init();
			LOG.info("Cargando el template : " + App.ARCH_TEMPLATE + " en la ruta de configuracion: " + App.PATH_TEMPLATE);
			plantillaReporte = ve.getTemplate(App.ARCH_TEMPLATE);
			vc = new VelocityContext();
			vc.put("String", String.class);
			vc.put("StrUtl", StringUtils.class);
			llenar0Dm(reporte, pathArchSal, pagina);
		}
		
	}
	
	public static void llenar0Dm(PagosFacturados reporte, String pathArchSal , int pagina) throws IOException {
		
		//vc.put("f_a_fin",reporte.getPenalizaciones().getfAmtFineqAmigoFacil());
		Date fechaGen = new Date();
		String anioGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"yyyy");
		String mesGeneracion = StringUtils.generarFechaEnFormato(fechaGen,"yyyy");
		vc.put("anioGeneracion", anioGeneracion);
		vc.put("mesGeneracion", mesGeneracion);
		vc.put("REGION", reporte.getRegion());
		vc.put("CICLO", reporte.getCiclo());
		vc.put("GRUPO_ING", reporte.getGrupoIng());
		vc.put("MES_FACTURA", reporte.getMesFactura());
		vc.put("MES_PAGO", reporte.getMesPago());
		vc.put("MONTO_PAGADO", reporte.getMesPago());
		vc.put("IMP_PAGADO", reporte.getImpPagado());
		vc.put("PAGO_SIN_IMP", reporte.getPagoSinImp());
		vc.put("TIPO_PAGO", reporte.getTipoPago());
		StringWriter sw = new StringWriter();
		plantillaReporte.merge(vc, sw);
        Files.write(Paths.get(pathArchSal), sw.toString().getBytes(), StandardOpenOption.APPEND );
	}
	
}
