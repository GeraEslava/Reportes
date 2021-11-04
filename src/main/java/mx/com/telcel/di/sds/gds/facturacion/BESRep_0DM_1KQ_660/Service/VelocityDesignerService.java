package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;



public class VelocityDesignerService implements Constantes{
	
	private static final Logger LOG = LoggerFactory.getLogger(VelocityDesignerService.class);

	private static VelocityContext vc = null;
	private static Template plantillaReporte = null;
	private static Template plantillaRepVacio = null;
}
