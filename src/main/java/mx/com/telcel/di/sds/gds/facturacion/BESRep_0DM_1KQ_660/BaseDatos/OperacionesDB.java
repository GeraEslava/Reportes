package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao.Info_0DM_1KQ_660_Mapper;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturadosVo;

public class OperacionesDB {

	private static OperacionesDB instance;
	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");

	public static OperacionesDB getInstance() {
		if (instance == null) {
			instance = new OperacionesDB();
		}
		return instance;
	}

	public int queryForInt(Connection connection, String query, Object[] parameters) throws SQLException {
		int retorno = 0;
		try {
			LOG.debug("Ejecutando query: " + query + " | Usando parametros: " + Arrays.toString(parameters));
			PreparedStatement ps = connection.prepareStatement(query);
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i].getClass().equals(String.class)) {
					ps.setString((i + 1), (String) parameters[i]);
				} else if (parameters[i].getClass().equals(Date.class)) {
					ps.setDate((i + 1), new java.sql.Date(((java.util.Date) parameters[i]).getTime()));
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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

	public List<Map<String, String>> queryForList(Connection connection, String query, String[] parameters)
			throws SQLException {
		ArrayList<Map<String, String>> lista = new ArrayList<Map<String, String>>();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString((i + 1), parameters[i]);
				}
			}

			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			int numeroColumnas = rsm.getColumnCount();
			LOG.debug("Ejecutando query: " + query + " | Usando parametros: " + Arrays.toString(parameters));

//            int count = 0;
			while (rs.next()) {
				TreeMap<String, String> mapa = new TreeMap<String, String>();
//                count++;
//                if (count%5000==0) LOG.info("- Recuperados " + count + " registros");
				for (int i = 0; i < numeroColumnas; i++) {
					mapa.put(rsm.getColumnName(i + 1), rs.getString(rsm.getColumnName(i + 1)));
				}
				lista.add(mapa);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}
	
	public List<PagosFacturadosVo> queryForRegiones(Connection connection, String query, String nombreArchivo) throws SQLException {
		List<PagosFacturadosVo> lista = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(query)){
			ps.setString(1, nombreArchivo);
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(Info_0DM_1KQ_660_Mapper.mapearRegioness(rs));
				} 
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		return lista;
	}
}
