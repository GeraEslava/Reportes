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
			new StringBuilder(" select CICLO, REGION, GRUPO_ING, MES_FACTURA, TIPO_PAGO ") 
			.append("FROM PAGOS_FACTURADOS ")
			.append("WHERE MES_FACTURA = '202009' ")
			.append("AND REGION ='R01' ")
			.append("ORDER BY REGION ASC ")
			.toString();
	
	public static final String SQL_OBTENER_MESFACTURA = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS ")
			.toString();
	
	public static final String SQL_OBTENER_DATOSREP = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS ")
			.toString();
	
	public static final String SQL_OBTENER_INGRESOS = 
			new StringBuilder(" select * ") 
			.append(" FROM PAGOS_FACTURADOS where REGION = 'R02' AND GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL'")
			.append(" AND substr(MES_FACTURA, 5, 6) = '09' AND substr(MES_FACTURA, 1, 4) = '2020' GROUP BY REGION, CICLO, GRUPO_ING, substr(MES_FACTURA, 5, 6), MES_FACTURA ")
			.toString();
	
	String q1 =" (SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE GRUPO_ING = 'OTROS' AND TIPO_PAGO = 'NORMAL' AND ";

	/*
	public static final String SQL_OBTENER_CICLOS = 
			new StringBuilder(" SELECT REGION, CICLO, GRUPO_ING, SUM(MONTO_PAGADO) TOTAL, substr(MES_FACTURA, 5, 6)MES, MES_FACTURA, SUM(IMP_PAGADO) PAGOS_ADELANTADOS, SUM (PAGO_SIN_IMP) SIN_PAGOS_ADELANTADOS, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 9 + 1 AND substr(MES_FACTURA, 1, 4) = '2020')MES1, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 9 + 2 AND substr(MES_FACTURA, 1, 4) = '2020')MES2, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 9 + 3 AND substr(MES_FACTURA, 1, 4) = '2020')MES3, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 1 AND substr(MES_FACTURA, 1, 4) = '2021')MES4, ")
			.append(q1+"substr(MES_FACTURA, 5, 6) = '09' AND substr(MES_FACTURA, 1, 4) = '2020')MES0, ")
		    .append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 2 AND substr(MES_FACTURA, 1, 4) = '2021')MES5, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 3 AND substr(MES_FACTURA, 1, 4) = '2021')MES6, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 4 AND substr(MES_FACTURA, 1, 4) = '2021')MES7, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 5 AND substr(MES_FACTURA, 1, 4) = '2021')MES8, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 6 AND substr(MES_FACTURA, 1, 4) = '2021')MES9, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 7 AND substr(MES_FACTURA, 1, 4) = '2021')MES10, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 8 AND substr(MES_FACTURA, 1, 4) = '2021')MES11, ")
			.append(q1+"CAST(substr(MES_FACTURA, 5, 6) AS INTEGER) = 0 + 9 AND substr(MES_FACTURA, 1, 4) = '2021')MES12 ")
			.append(" FROM PAGOS_FACTURADOS ")
			.append(" WHERE REGION = ? ")
			.append(" AND GRUPO_ING = 'OTROS' ")
			.append(" AND TIPO_PAGO = 'NORMAL' ")
			.append(" AND substr(MES_FACTURA, 5, 6) = '09' ")
			.append(" AND substr(MES_FACTURA, 1, 4) = '2020' ")
			.append(" GROUP BY REGION, CICLO, GRUPO_ING, substr(MES_FACTURA, 5, 6), MES_FACTURA ")
			.append(" ORDER BY CICLO ")
			.toString();*/
	
	//01/09/2021
	public static final String SQL_OBTENER_CICLOS = 
			new StringBuilder(" SELECT REGION, CICLO, GRUPO_ING, SUM(MONTO_PAGADO) TOTAL, substr(MES_FACTURA, 5, 6)MES, MES_FACTURA, SUM(IMP_PAGADO) PAGOS_ADELANTADOS, SUM (PAGO_SIN_IMP) SIN_PAGOS_ADELANTADOS, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202009' ) MES0, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202010' ) MES1, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202011' ) MES2, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202012' ) MES3, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202101' ) MES4, ")
		    .append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202102' ) MES5, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202103' ) MES6, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202104' ) MES7, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202105' ) MES8, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202106' ) MES9, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202107' ) MES10, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202108' ) MES11, ")
			.append("(SELECT (IMP_PAGADO) FROM PAGOS_FACTURADOS WHERE MES_FACTURA='202109' ) MES12 ")
			.append(" FROM PAGOS_FACTURADOS ")
			.append(" WHERE REGION = ? ")
			//.append(" AND GRUPO_ING = 'OTROS' ")
			//.append(" AND TIPO_PAGO = 'NORMAL' ")
			//.append(" AND substr(MES_FACTURA, 5, 6) = '09' ")
			//.append(" AND substr(MES_FACTURA, 1, 4) = '2020' ")
			.append(" AND MES_FACTURA='202009'")
			.append(" GROUP BY REGION, CICLO, GRUPO_ING, substr(MES_FACTURA, 5, 6), MES_FACTURA ")
			.append(" ORDER BY CICLO ")
			.toString();
	
	public static final String SQL_OBTENER_TIPO_PAGO_2 = 
			new StringBuilder(" select DISTINCT(REGION) ") 
			.append("FROM PAGOS_FACTURADOS ")
			//.append("WHERE MES_FACTURA = '202009' ")
			//.append("AND REGION =? ")
			.append("ORDER BY REGION ASC ")
			.toString();
	
	public static final String SQL_GET_CICLOS = 
			new StringBuilder("select DISTINCT(CICLOS) ")
			.append("FROM PAGOS_FACTURADOS ")
			.append("WHERE ")
			.append("MES_FACTURA = ? ")
			.append("AND REGION =? ")
			.append("ORDER BY REGION ASC ")
			.toString();
}