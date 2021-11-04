package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.BaseDatos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils.PropertiesReader;


public class BaseDeDatos {
	private Connection connection;
	private static Properties databaseInfo = null;
	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");
	static {
		databaseInfo = new Properties();
		PropertiesReader.loadProperties(databaseInfo, String.valueOf(System.getenv().get("DIR_CFG")) + File.separator + "database.properties");
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public BaseDeDatos(String tipo) {
		try {
		 	LOG.info("Creando conexion a base de datos ..." + tipo);
			if (databaseInfo == null) {
				databaseInfo = new Properties();
				PropertiesReader.loadProperties(databaseInfo, String.valueOf(System.getenv().get("DIR_CFG")) + File.separator + "database.properties");
			}
			String host = databaseInfo.getProperty(tipo + "_HOST");
			int port = new Integer(databaseInfo.getProperty(tipo + "_PORT")).intValue();
			String sid = databaseInfo.getProperty(tipo + "_SID");

			java.util.Properties props = new java.util.Properties();
			props.put("user", databaseInfo.getProperty(tipo + "_USER"));
			props.put("password", databaseInfo.getProperty(tipo + "_PASSWORD"));
			props.put("defaultRowPrefetch", "30");
			props.put("defaultBatchValue", "5");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + sid, props);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			LOG.info("Conexion  a la base de datos " + tipo +" ha sido exitosa");
		} catch (Exception e) {
			LOG.info("Conexion a la base de datos ha fallado: " + tipo);
			LOG.error("Error:",e);
		}
		
	}
	
	public BaseDeDatos() {}
	
	public int pruebaConexion(String tipo) {
		int valor=0;
		try {
			LOG.info("Testeando conexion a la base de datos " + tipo +".....");
			if (databaseInfo == null) {
				databaseInfo = new Properties();
				PropertiesReader.loadProperties(databaseInfo,String.valueOf(System.getenv().get("DIR_CFG")) + File.separator +  "database.properties");
			}
			String host = databaseInfo.getProperty(tipo + "_HOST");
			int port = new Integer(databaseInfo.getProperty(tipo + "_PORT")).intValue();
			String sid = databaseInfo.getProperty(tipo + "_SID");

			java.util.Properties props = new java.util.Properties();
			props.put("user", databaseInfo.getProperty(tipo + "_USER"));
			props.put("password", databaseInfo.getProperty(tipo + "_PASSWORD"));
			props.put("defaultRowPrefetch", "30");
			props.put("defaultBatchValue", "5");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + sid, props);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			LOG.info("Conexion  a la base de datos " + tipo + " ha sido exitosa");
			valor=1;
		} catch (Exception e) {
			valor=0;
			LOG.info("Conexion a la base de datos ha fallado: " + tipo);
			LOG.error("Error al intentar realizar la conexion",e);
		}finally {
			try {
				if (connection!=null) { 
					connection.close();	
					LOG.info("Conexion cerrada");
				}
			}catch(Exception e) {
				LOG.error("Error: " + e);
			}
		}
		return valor;
	}
}
