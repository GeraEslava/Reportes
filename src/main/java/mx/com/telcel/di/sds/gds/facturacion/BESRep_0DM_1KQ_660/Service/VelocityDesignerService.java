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

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityDesignerService implements Constantes{
	
	private static final Logger LOG = LoggerFactory.getLogger(VelocityDesignerService.class);

	private static VelocityContext vc = null;
	private static Template plantillaReporte = null;
	
	public static void generarReporte(Reporte0DM reporte, String pathArchSal , int pagina) throws IOException {
		if(vc == null) {
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, App.PATH_TEMPLATE);
			ve.init();
			LOG.info("Cargando el template : " + App.ARCH_TEMPLATE + " en la ruta de configuracion: " + App.PATH_TEMPLATE);
			plantillaReporte = ve.getTemplate(App.ARCH_TEMPLATE);
			vc = new VelocityContext();
			vc.put("String", String.class);
			vc.put("StrUtl", StringUtils.class);
		}
		
	}
	
	public static void llenar0Dm(PagosFacturados reporte, String pathArchSal , int pagina) {
		
		//vc.put("f_a_fin",reporte.getPenalizaciones().getfAmtFineqAmigoFacil());
		vc.put("REGION", reporte.getRegion());
		vc.put("CICLO", reporte.getCiclo());
		vc.put("GRUPO_ING", reporte.getGrupoIng());
		vc.put("MES_FACTURA", reporte.getMesFactura());
		vc.put("MES_PAGO", reporte.getMesPago());
		vc.put("MONTO_PAGADO", reporte.getMesPago());
		vc.put("IMP_PAGADO", reporte.getImpPagado());
		vc.put("PAGO_SIN_IMP", reporte.getPagoSinImp());
		vc.put("TIPO_PAGO", reporte.getTipoPago());
	}
	
}
