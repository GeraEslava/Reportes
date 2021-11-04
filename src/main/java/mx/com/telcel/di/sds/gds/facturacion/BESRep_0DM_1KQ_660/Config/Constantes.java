package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;

public interface Constantes {
	
	public static final String DB_H2 = "H2";
	public static final String DB_SQLITE = "SQLITE";
	public static final String DB_NAME_APP = "Reportes0DM1KQ660";
	public static final String EXT_ARCH_CTL = ".ctl";
	
	public static final String CADENA_VACIA = "";

	public static final String SEPARADOR_LISTA_ARCHIVOS = "|";
	public static final String ETIQUETA_DIR_PAIS = "MEX";
	
	public static  List<String> TIPOS_ARCHIVO = new ArrayList<String>(Arrays.asList("AB","RA","DF","PE"));
	
	public static final int MESES_X_MOSTRAR = Integer.parseInt(App.CONFIG.getProperty("MESES_X_MOSTRAR")); 
	public static final int MESES_X_PROCESAR = Integer.parseInt(App.CONFIG.getProperty("MESES_X_PROCESAR"));
	
	public static final int LONG_DOMICILIO_POST_FISCAL = 45;
	
	
	
}
