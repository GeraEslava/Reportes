package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;

public interface Constantes {
	
	public static final String DB_H2 = "H2";
	public static final String DB_SQLITE = "SQLITE";
	public static final String DB_NAME_APP = "Reportes_0DM_1KQ_660";
	public static final String EXT_ARCH_CTL = ".ctl";
	
	public static final String CADENA_VACIA = "";

	public static final String SEPARADOR_LISTA_ARCHIVOS = "|";
	public static final String ETIQUETA_DIR_PAIS = "MEX";
	
	public static  List<String> TIPOS_ARCHIVO = new ArrayList<String>(Arrays.asList("AB","RA","DF","PE"));
	
	public static final int MESES_X_MOSTRAR = Integer.parseInt(App.CONFIG.getProperty("MESES_X_MOSTRAR")); 
	public static final int MESES_X_PROCESAR = Integer.parseInt(App.CONFIG.getProperty("MESES_X_PROCESAR"));
	
	public static final int LONG_DOMICILIO_POST_FISCAL = 45;
	
	public static final String[] MES = {"ENE","FEB","MAR","ABR","MAY","JUN","JUL","AGO","SEP","OCT","NOV","DIC"};
	public static final String[] MESCOMPLETO = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
	//public static final List<String> fechaAnioMes = new ArrayList<String>();
	
	
	public static final String MASK_FECHA_NAME_DONE = App.CONFIG.getProperty("MASK_FECHA_NAME_DONE");
	public static final String PREFIX_ARCH_DONE_REPORTE_0DM_C = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_0DM_C");
	public static final String PREFIX_ARCH_DONE_REPORTE_0DM_D = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_0DM_D");
	public static final String PREFIX_ARCH_DONE_REPORTE_1KQ_QBK = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_1KQ_QBK");
	public static final String PREFIX_ARCH_DONE_REPORTE_1KQ_QMD = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_1KQ_QMD");
	public static final String PREFIX_ARCH_DONE_REPORTE_1KQ_QPR = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_1KQ_QPR");
	public static final String PREFIX_ARCH_DONE_REPORTE_660_CCB = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_660_CCB");
	public static final String PREFIX_ARCH_DONE_REPORTE_660_SCB = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_660_SCB");
	public static final String PREFIX_ARCH_DONE_REPORTE_660_UCB = App.CONFIG.getProperty("PREFIX_ARCH_DONE_REPORTE_660_UCB");
	public static final String TERMINADOR_LINEA_UNIX = "\n";
			
	public static final List<String> listAnio = new ArrayList<String>();
	public static final List<String> listMesAnioC = new ArrayList<String>();
	public static final List<String> listMesAnio = new ArrayList<String>();
	
	
	public static final String ARCH_TEMPLATE_CUERPO = App.revisaPropiedad("ARCH_TEMPLATE_CUERPO");
	public static final String ARCH_TEMPLATE = App.revisaPropiedad("ARCH_TEMPLATE");
	
}
