package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config;


public interface Consultas {
	
	public static final String SQL_OBTENER_PAGOSFACTURADOS = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS ")
			.toString();
	
	public static final String SQL_OBTENER_REGIONES = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS where REGION = ? ")
			.toString();
	
	public static final String SQL_OBTENER_TIPO_PAGO = 
			new StringBuilder(" select REGION, CICLO, GRUPO_ING, MES_FACTURA, MES_PAGO, MONTO_PAGADO, IMP_PAGADO, PAGO_SIN_IMP, TIPO_PAGO ") 
			.append(" FROM PAGOS_FACTURADOS where TIPO_PAGO = ? ")
			.toString();
}