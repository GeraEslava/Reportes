package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.GrupoIngreso0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.PagosFacturados;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Region0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Reporte0DM;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model.Reporte0DM2;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils.StringUtils;

public class Info_0DM_1KQ_660_Mapper {
	
	public static PagosFacturados mapearRegioness(ResultSet rs) throws SQLException {
		PagosFacturados test = new PagosFacturados();
		test.setGrupoIng(rs.getString("GRUPO_ING"));
		test.setMesFactura(rs.getString("MES_FACTURA"));
		test.setRegion(rs.getString("REGION"));
		test.setTipoPago(rs.getString("TIPO_PAGO"));
		return test;
	}
	
	public static GrupoIngreso0DM mapearIngresos(ResultSet rs) throws SQLException {
		GrupoIngreso0DM test = new GrupoIngreso0DM();
		test.setGrupoIngreso(rs.getString("GRUPO_ING"));
		return test;
	} 
		
	public static Region0DM mapearRegiones(ResultSet rs) throws SQLException{
		Region0DM region0DM = new Region0DM();
		region0DM.setGrupoIng(rs.getString("GRUPO_ING"));
		region0DM.setMesFactura(rs.getString("MES_FACTURA"));
		region0DM.setRegion(rs.getString("REGION"));
		region0DM.setTipoPago(rs.getString("TIPO_PAGO"));
		return region0DM;
		
	}
	
	public static Reporte0DM2 mapearDatos(ResultSet rs) throws SQLException {
		Reporte0DM2 test = new Reporte0DM2();
		test.setRegion(rs.getString("REGION"));
		test.setCiclo(rs.getString("CICLO"));
		test.setGrupoIng(rs.getString("GRUPO_ING"));
		test.setPagosAdelantados(rs.getString("PAGOS_ADELANTADOS"));
		test.setSinPagosAdelantados(rs.getString("SIN_PAGOS_ADELANTADOS"));
		test.setMes(rs.getString("MES"));
		test.setMes0(rs.getString("MES0"));
		test.setMes1(rs.getString("MES1"));
		test.setMes2(rs.getString("MES2"));
		test.setMes3(rs.getString("MES3"));
		test.setMes4(rs.getString("MES4"));
		test.setMes5(rs.getString("MES5"));
		test.setMes6(rs.getString("MES6"));
		test.setMes7(rs.getString("MES7"));
		test.setMes8(rs.getString("MES8"));
		test.setMes9(rs.getString("MES9"));
		test.setMes10(rs.getString("MES10"));
		test.setMes11(rs.getString("MES11"));
		test.setMes12(rs.getString("MES12"));
		test.setTotal(rs.getString("TOTAL"));
		return test;
	}
	
	
	public static Reporte0DM mapearDatosFormat(ResultSet rs) throws SQLException {
		Reporte0DM test = new Reporte0DM();
		test.setRegion( rs.getString("REGION") );
		test.setCiclo( rs.getString("CICLO") );
		test.setGrupoIng( rs.getString("GRUPO_ING")  );
		/*test.setPagosAdelantados(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("PAGOS_ADELANTADOS")) ) );
		test.setSinPagosAdelantados(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("SIN_PAGOS_ADELANTADOS")) ) );
		test.setMes(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES")) ) );
		test.setMes0(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES0")) ) );
		test.setMes1(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES1")) ) );
		test.setMes2(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES2")) ) );
		test.setMes3(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES3")) ) );
		test.setMes4(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES4")) ) );
		test.setMes5(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES5")) ) );
		test.setMes6(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES6")) ) );
		test.setMes7(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES7")) ) );
		test.setMes8(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES8")) ) );
		test.setMes9(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES9")) ) );
		test.setMes10(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES10")) ) );
		test.setMes11(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES11")) ) );
		test.setMes12(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("MES12")) ) );
		test.setTotal(StringUtils.getStrFromDoubleConSepMiles( Double.valueOf(rs.getString("TOTAL")) ) );*/
		
		test.setPagosAdelantados( Double.valueOf(StringUtils.validarString(rs.getString("PAGOS_ADELANTADOS")) ) );
		test.setSinPagosAdelantados( Double.valueOf( StringUtils.validarString(rs.getString("SIN_PAGOS_ADELANTADOS")) ) );
		test.setMes( Double.valueOf( StringUtils.validarString( rs.getString("MES")) ) );
		test.setMes0(Double.valueOf( StringUtils.validarString( rs.getString("MES0")) ) );
		test.setMes1(Double.valueOf( StringUtils.validarString( rs.getString("MES1")) ) );
		test.setMes2(Double.valueOf( StringUtils.validarString( rs.getString("MES2")) ) );
		test.setMes3(Double.valueOf( StringUtils.validarString( rs.getString("MES3")) ) );
		test.setMes4(Double.valueOf( StringUtils.validarString( rs.getString("MES4")) ) );
		test.setMes5(Double.valueOf( StringUtils.validarString( rs.getString("MES5")) ) );
		test.setMes6(Double.valueOf( StringUtils.validarString( rs.getString("MES6")) ) );
		test.setMes7(Double.valueOf( StringUtils.validarString( rs.getString("MES7")) ) );
		test.setMes8(Double.valueOf( StringUtils.validarString( rs.getString("MES8")) ) );
		test.setMes9(Double.valueOf( StringUtils.validarString( rs.getString("MES9")) ) );
		test.setMes10( Double.valueOf( StringUtils.validarString( rs.getString("MES10")) ) );
		test.setMes11( Double.valueOf( StringUtils.validarString( rs.getString("MES11")) ) );
		test.setMes12( Double.valueOf( StringUtils.validarString( rs.getString("MES12")) ) );
		test.setTotal( Double.valueOf( StringUtils.validarString( rs.getString("TOTAL")) ) );		
		
		
	/*	test.setPagosAdelantados( rs.getString("PAGOS_ADELANTADOS") );
		test.setSinPagosAdelantados(rs.getString("SIN_PAGOS_ADELANTADOS")  );		
		test.setMes0(rs.getString("MES0")  );
		test.setMes1( rs.getString("MES1") );
		test.setMes2( rs.getString("MES2") );
		test.setMes3( rs.getString("MES3") );
		test.setMes4( rs.getString("MES4") );
		test.setMes5( rs.getString("MES5") );
		test.setMes6( rs.getString("MES6") );
		test.setMes7( rs.getString("MES7") );
		test.setMes8( rs.getString("MES8") );
		test.setMes9( rs.getString("MES9") );
		test.setMes10(  rs.getString("MES10") );
		test.setMes11(  rs.getString("MES11") );
		test.setMes12(  rs.getString("MES12") );
		test.setTotal(  rs.getString("TOTAL") );*/
		
		return test;
	}
	
	public static Region0DM mapRegionesTipoArchivo(ResultSet rs) throws SQLException{
		Region0DM region0DM = new Region0DM();
		region0DM.setRegion(rs.getString("REGION"));
		return region0DM;
		
	}
	
	
	public static Region0DM mapCiclosXRegion(ResultSet rs) throws SQLException{
		Region0DM region0DM = new Region0DM();
		region0DM.setCiclo(rs.getString("CICLO"));
		return region0DM;
		
	}
}