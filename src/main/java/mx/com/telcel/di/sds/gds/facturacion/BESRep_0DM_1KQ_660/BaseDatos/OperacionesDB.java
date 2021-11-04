package mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperacionesDB {

	
	private static OperacionesDB instance;
	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");
	
	public static OperacionesDB getInstance()  {
		 if (instance == null) {
			 instance = new OperacionesDB();
		 }
		 return instance;
	}
	
	public int queryForInt(Connection connection, String query, Object[] parameters) throws SQLException
	{
		int retorno = 0;
		try {
			LOG.debug("Ejecutando query: " + query + " | Usando parametros: " + Arrays.toString(parameters));
			PreparedStatement ps = connection.prepareStatement(query);
			for (int i=0;i<parameters.length;i++)
			{
				if (parameters[i].getClass().equals(String.class))
				{
					ps.setString((i+1), (String) parameters[i]);
				}
				else if (parameters[i].getClass().equals(Date.class))
				{
					ps.setDate((i+1),  new java.sql.Date(((java.util.Date) parameters[i]).getTime()));
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				retorno = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return retorno;
	}
	}
