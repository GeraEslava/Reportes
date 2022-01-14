package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config;


public interface Consultas {
	
	public static final String SQL_OBTENER_PAGOSFACTURADOS = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS where REGION = ? ")
			.toString();
	
	public static final String SQL_OBTENER_REGIONES = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS where REGION = ? ")
			.toString();
	
	public static final String SQL_OBTENER_TIPO_PAGO = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS ")
			.toString();
	
	public static final String SQL_OBTENER_MESFACTURA = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS")
			.toString();
	
	public static final String SQL_OBTENER_DATOSREP = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS ")
			.toString();
	
	public static final String SQL_OBTENER_INGRESOS = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS where REGION = ? ")
			.toString();

	public static final String SQL_OBTENER_CICLOS = 
			new StringBuilder(" SELECT REGION, CICLO, GRUPO_ING, SUM(MONTO_PAGADO) TOTAL, substr(MES_FACTURA, 5, 6)MES, MES_FACTURA, SUM(IMP_PAGADO), SUM (PAGO_SIN_IMP), ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 9 + 1 AND substr(MES_FACTURA, 1, 4) = '2020')MES1, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 9 + 2 AND substr(MES_FACTURA, 1, 4) = '2020')MES2, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 9 + 3 AND substr(MES_FACTURA, 1, 4) = '2020')MES3, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 1 AND substr(MES_FACTURA, 1, 4) = '2021')MES4, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND substr(MES_FACTURA, 5, 6) = '09' AND substr(MES_FACTURA, 1, 4) = '2020')MES0, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 2 AND substr(MES_FACTURA, 1, 4) = '2021')MES5, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 3 AND substr(MES_FACTURA, 1, 4) = '2021')MES6, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 4 AND substr(MES_FACTURA, 1, 4) = '2021')MES7, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 5 AND substr(MES_FACTURA, 1, 4) = '2021')MES8, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 6 AND substr(MES_FACTURA, 1, 4) = '2021')MES9, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 7 AND substr(MES_FACTURA, 1, 4) = '2021')MES10, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 8 AND substr(MES_FACTURA, 1, 4) = '2021')MES11, ")
			.append(" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE CICLO = 14 AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 9 AND substr(MES_FACTURA, 1, 4) = '2021')MES12 ")
			.append(" FROM PAGOS_FACTURADOS ")
			.append(" WHERE REGION = ? ")
			//.append(" GRUPO_ING = 'OTROS' ")
			.append(" AND TIPO_PAGO = 'NORMAL' ")
			//.append(" AND substr(MES_FACTURA, 5, 6) = '09' ")
			//.append(" AND substr(MES_FACTURA, 1, 4) = '2020' ")
			.append(" GROUP BY REGION, CICLO, GRUPO_ING, substr(MES_FACTURA, 5, 6), MES_FACTURA ")
			.append(" ORDER BY CICLO ")
			.toString();
}