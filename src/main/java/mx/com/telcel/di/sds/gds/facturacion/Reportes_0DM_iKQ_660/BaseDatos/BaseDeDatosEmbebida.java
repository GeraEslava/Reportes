package mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.Config.Constantes;


public class BaseDeDatosEmbebida implements Constantes {
	private Connection connection;
	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");
	
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
	
	public BaseDeDatosEmbebida(String nombre, String tipo, String pathDb) {
		try {
		 	LOG.info("Creando la conexion a la base de datos: " + nombre + "[" + tipo + "]...");
			if(DB_SQLITE.equals(tipo)) {
				inicializarSqlite(pathDb);
			} else if(DB_H2.equals(tipo)) {
				inicializarH2(pathDb);
			} else {
				LOG.info("El tipo especificado para  la base de datos no esta soportado por la app: " + tipo );
			}
			
		} catch (Exception e) {
			LOG.info("La conexion a la base de datos ha fallado: " + nombre +"[" + tipo + "]");
			LOG.error("Error",e);
		} finally {
			if(this.connection != null) {
				LOG.info("La conexion a la base de datos " + nombre  + "[" + tipo + "] ha sido exitosa");
			} 
		}
	}
	
	private void inicializarH2(String pathDb) throws ClassNotFoundException, SQLException {
		java.util.Properties props = new java.util.Properties();
		props.put("user", "sa");
		props.put("password", "");
//		props.put("defaultRowPrefetch", "30");
//		props.put("defaultBatchValue", "5");

		Class.forName("org.h2.Driver");
		connection = DriverManager.getConnection("jdbc:h2:" + pathDb);
		connection.setAutoCommit(false);
		connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	}
	
	private void inicializarSqlite(String pathDb) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + pathDb);
		connection.setAutoCommit(false);
		connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	}

}
