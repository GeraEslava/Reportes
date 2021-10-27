package mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.Service;

import mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.Config.Constantes;

public class ReportDesigner  implements Constantes{
	
	protected static String TERMINADOR_LINEA = ("A".equals(App.CONFIG.getProperty("FORMATO_WINDOWS"))) ?"\r\n":"\n";
	
}
