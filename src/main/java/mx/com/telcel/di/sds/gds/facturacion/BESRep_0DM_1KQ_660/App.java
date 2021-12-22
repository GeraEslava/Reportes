package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Service.GeneradorReporte;

public class App {
	
	public static final String APP_NAME = "BESRep_0DM_1KQ_660";
	public static String ID_PROC;
	public static String FCH_ENTREGA;
	public static String ARCH_TEMPLATE = null;
	public static String PATH_TEMPLATE = null;
	public static String DIR_CFG = null;
	public static String DIR_LOG = null;
	public static String NOM_ARCH_CONFIG = null;	
	public static String FORMATO_CIFRAS = null;
	public static String FORMATO_CIFRAS_SEP_MILES = null;
	public static String FORMATO_FECHA_DET = null;
	public static String TIPOS_FACT_FCH_MAX_FACTURACION = null;
	public static String REASON_CODES_FIN_AMF = null;
	public static String REASON_CODES_EQ_PLAZOS = null;
	public static String REASON_CODES_PEN_SERVICIO = null;
	public static String ID_EXEC_EXTERNA;
	public static int MESES_X_MOSTRAR;
	
	public static Properties CONFIG = null;
	private static Logger LOG = null;
    static {
		DIR_LOG = System.getenv().get("DIR_LOG");
		DIR_CFG = System.getenv().get("DIR_CFG");
		if(DIR_LOG == null) {
			DIR_LOG = "./LOG";
			System.out.println("La variable de ambiente ${DIR_LOG} no fue proporcionada correctamente, se indicara una por defecto");
		}
		
		System.setProperty("logback.configurationFile", DIR_CFG + File.separator + "BESRep_0DM_1KQ_660.logback.xml");
		
    	final SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMddhhmmss");
    	ID_PROC = dt1.format(new Date());
    	FCH_ENTREGA = ID_PROC.substring(0,8);
    	LOG = LoggerFactory.getLogger("funcionalLogger");
    	//Generamos una instancia de propiedades para el acceso a las propiedades de configuracion de la aplicacion
    	CONFIG = new Properties();	
    	NOM_ARCH_CONFIG = APP_NAME + ".application.properties";
		loadProperties(CONFIG, DIR_CFG + File.separator + NOM_ARCH_CONFIG);
		ARCH_TEMPLATE = revisaPropiedad("ARCH_TEMPLATE");
		FORMATO_CIFRAS = revisaPropiedad("FORMATO_CIFRAS");
		FORMATO_CIFRAS_SEP_MILES = revisaPropiedad("FORMATO_CIFRAS_SEP_MILES");
		FORMATO_FECHA_DET = revisaPropiedad("FORMATO_FECHA_DET");
		PATH_TEMPLATE = revisaPropiedad("PATH_TEMPLATES");
		LOG.debug("DESDE APP:  "+PATH_TEMPLATE);
		
		TIPOS_FACT_FCH_MAX_FACTURACION = revisaPropiedad("TIPOS_FACT_FCH_MAX_FACTURACION");
		REASON_CODES_FIN_AMF = revisaPropiedad("REASON_CODES_FIN_AMF");
		REASON_CODES_EQ_PLAZOS = revisaPropiedad("REASON_CODES_EQ_PLAZOS");
		REASON_CODES_PEN_SERVICIO = revisaPropiedad("REASON_CODES_PEN_SERVICIO");
    }

    public static void main( String[] args ) {
    	
    	try {
			LOG.info("#### Iniciando con el proceso para generar el reporte " + APP_NAME + " ####");
			
			if(args.length == 0 || args.length > 1) {
				LOG.info("No se especifico el ID del ejecucion externa, que corresponden a la aplicacion que invoca.");
				System.exit(1);;
			}			
			
			ID_EXEC_EXTERNA = args[0];
			LOG.info("Procesando el conjunto de archivos con ID de ejecucion: " + ID_EXEC_EXTERNA );
			GeneradorReporte generador = new GeneradorReporte();
	    	generador.construirReporte();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	finally {
			LOG.info("#### Terminando con el proceso para generar el reporte " + APP_NAME + " ####" );
		}
    	
    }
    
    public static void loadProperties (Properties objetoProperties, String fileName) {				
		try {
			FileInputStream fis = new FileInputStream(fileName);
			objetoProperties.load(fis);
	        recargarVariablesDeEntorno(objetoProperties);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void loadProperties (Properties objetoProperties, InputStream fis) {				
		try {
			objetoProperties.load(fis);
	        recargarVariablesDeEntorno(objetoProperties);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void recargarVariablesDeEntorno(Properties ficheroPropiedades) throws Exception {
        for (Object key : ficheroPropiedades.keySet()) {
            String keyString = (String) key;
            if (keyString != null && (ficheroPropiedades.get(key) != null) && (!ficheroPropiedades.get(key).equals(""))) {
                if (ficheroPropiedades.get(key).toString().matches("\\{.*\\}")) {
                    String valorVariable = System.getenv(ficheroPropiedades.get(key).toString().substring(1, ficheroPropiedades.get(key).toString().length() - 1));
                    if (valorVariable == null) {
                        LOG.error("La variable de entorno: " + ficheroPropiedades.get(key).toString().substring(1, ficheroPropiedades.get(key).toString().length() - 1) + ", no ha sido fijada correctamente");
                        throw new Exception("La variable de entorno: " + ficheroPropiedades.get(key).toString().substring(1, ficheroPropiedades.get(key).toString().length() - 1) + ", no ha sido fijada correctamente");
                    } else {
                        ficheroPropiedades.setProperty(keyString, valorVariable);
                    }
                }
            } else {
                LOG.error("La propiedad: " + keyString + ", no ha sido fijada correctamente");
                throw new Exception("La propiedad: " + keyString + ", no ha sido fijada correctamente");
            }
        }
    }
    
    public static String revisaPropiedad(String propiedad) {
    	String valorPropiedad = CONFIG.getProperty(propiedad);
    	if( valorPropiedad == null) {
    		System.out.println("La propiedad " + propiedad + " no se encuentra configurada en el archivo: "+ NOM_ARCH_CONFIG + ", por lo que la aplicacion no puede continuar");
    		System.exit(1);
    	} 
    	return valorPropiedad;
    }
	
}
