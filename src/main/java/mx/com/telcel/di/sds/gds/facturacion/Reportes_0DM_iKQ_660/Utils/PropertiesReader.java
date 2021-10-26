package mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class PropertiesReader {
	static Properties properties;

  private static final Logger LOG = LoggerFactory.getLogger(PropertiesReader.class);

  /**
   * Carga el properties indicado por el path
   *
   * @param path
   * @return Properties
   */
  public static Properties loadProperties(String path) throws Exception {
	  InputStream in = null;
      try {
    	  
          if (properties == null) {
              properties = new Properties();
              in = getResourceInputStream(path);
              properties.load(in);
          }
          
      } catch (IOException e) {
    	  
          LOG.error("Ha ocurrido el siguiente error: " + e.getMessage(), e);          
          throw e;
      } finally {
		if(in != null) {
			try {
				in.close();
			} catch (Exception e) {
				LOG.error("Ha ocurrido el siguiente error: " + e.getMessage(), e);
			}
		}
	  }
      return properties;
  }
  public static void loadProperties (Properties objetoProperties, String fileName)  
 	{		
 		FileInputStream fis = null;
 		try {
 			fis = new FileInputStream(fileName); 
 			objetoProperties.load(fis);
 			//objetoProperties.load(Consumer.class.getResourceAsStream("/"+fileName));
// 			recargarVariablesDeEntorno(objetoProperties);
 			
 		} catch (Exception e) {
 			LOG.error("Error al tratar de abrir el archivo de propiedades: " + fileName, e);			
 		} finally {
 			try {
 				if(fis != null) {
 					fis.close();
 				}
 			} catch (IOException exCerrArch) {
 				LOG.error("Error al tratar de cerrar el archivo de propiedades: " + fileName, exCerrArch);
 			}
 		}
 	}
  private final static InputStream getResourceInputStream(String resource) {
      InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
      if (in == null) {
          in = resource.getClass().getResourceAsStream(resource);
      }
      if (in == null) {
          File file = new File(resource);
          if (file.exists()) {
              try {
                  in = new FileInputStream(resource);
              } catch (FileNotFoundException e) {
                  in = null;
              }
          }
      }
      return in;
  }
}
