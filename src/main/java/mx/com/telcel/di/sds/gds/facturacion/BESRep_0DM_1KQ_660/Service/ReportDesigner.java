package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;

public class ReportDesigner  implements Constantes{
	
	protected static String TERMINADOR_LINEA = ("A".equals(App.CONFIG.getProperty("FORMATO_WINDOWS"))) ?"\r\n":"\n";
	
}
