package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config;


public interface Consultas {
	
	public static final String SQL_OBTENER_PAGOSFACTURADOS = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS ")
			.toString();
	
}