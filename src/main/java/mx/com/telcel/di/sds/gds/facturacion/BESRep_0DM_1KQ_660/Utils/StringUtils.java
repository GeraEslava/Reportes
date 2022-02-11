package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.App;
import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.Constantes;

import java.math.RoundingMode;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringUtils implements Constantes {
	private static final Logger LOG = LoggerFactory.getLogger(StringUtils.class);
	private static DecimalFormat decimalformatter = null;
	private static DecimalFormat decimalFormatterSepMiles = null;
	static {
		decimalformatter = new DecimalFormat(App.FORMATO_CIFRAS);
		decimalformatter.setRoundingMode(RoundingMode.DOWN);
		
		decimalFormatterSepMiles = new DecimalFormat(App.FORMATO_CIFRAS_SEP_MILES);
		decimalFormatterSepMiles.setRoundingMode(RoundingMode.DOWN);
		
	}
	
	 public static String getMonthForInt(int num) {
	        String month = "wrong";
	        DateFormatSymbols dfs = new DateFormatSymbols();
	        String[] months = dfs.getMonths();
	        if (num >= 0 && num <= 11 ) {
	            month = months[num];
	        }
	        return month;
	    }
	/**
	 * Regresa la fecha con formato especificado en el parametro "format"
	 * @param fecha
	 * @param format
	 * @return
	 */
	public static String generarFechaEnFormato(Date fecha, String format) {
		if(fecha == null) {
			return CADENA_VACIA;
		}
		SimpleDateFormat fechaCad = new SimpleDateFormat(format); //"dd/MM/yyyy"
	    String fechaResult = fechaCad.format(fecha);
	    return fechaResult;
	}
	
	public static String generarFechaConFormato(String fecha, String formato, String formatoFinal) {
		if(fecha == null || CADENA_VACIA.equals(fecha)) {
			return ajustarCadenaRellenaIzquierda("",formatoFinal.length());
		}
		 SimpleDateFormat format = new SimpleDateFormat(formato);
		try {
			Date parsed = format.parse(fecha);
			return StringUtils.generarFechaEnFormato(parsed, formatoFinal);
//			java.sql.Date sql = new java.sql.Date(parsed.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static LocalDate generarCadEnIsoFechaLocal(String fecha) {
//		LOG.info(" fecha : " + fecha   + " formato : " +  formato);
		if(fecha == null || CADENA_VACIA.equals(fecha)) {
			return null;
		}
		// convert String to LocalDate
		LocalDate localDate = LocalDate.parse(fecha);
		return localDate;
		
	}
	public static LocalDate calculaLocalFromDate(Date fecha) {
		return Instant.ofEpochMilli(fecha.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	public static LocalDate generarFechaLocalConFormato(String fecha, String formato) {
//		LOG.info(" fecha : " + fecha   + " formato : " +  formato);
		if(fecha == null || CADENA_VACIA.equals(fecha)) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		// convert String to LocalDate
		LocalDate localDate = LocalDate.parse(fecha, formatter);
		return localDate;
		
	}
	public static Date generarFechaConFormato(String fecha, String formato) {
		LOG.info(" fecha : " + fecha   + " formato : " +  formato);
		if(fecha == null || CADENA_VACIA.equals(fecha)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formato);
		try {
			Date parsed = format.parse(fecha);
//			java.sql.Date sql = new java.sql.Date(parsed.getTime());
//			return sql;
			return parsed;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String asignarCadena(String cadena) {
		return (cadena == null)?CADENA_VACIA:cadena.trim();
	}
	
	public static String printFecha(LocalDate fecha) {
		if(fecha == null) {
			return CADENA_VACIA;
		}
		return fecha.format(DateTimeFormatter.ofPattern(App.FORMATO_FECHA_DET));
	}
	public static String printFecha(LocalDate fecha, int longitud) {
		return ajustarCadenaDerecha(printFecha(fecha), longitud);
	}
	
	
	public static String printNumberSeparadorMiles(double numero, int longitud) {
		return ajustarCadenaRellenaIzquierda(getStrFromDoubleConSepMiles(numero), longitud);
	}
	
	public static String printNumber(double numero, int longitud) {
		return ajustarCadenaRellenaIzquierda(getStrFromDouble(numero), longitud);
	}
	
	public static String printNumberGreaterThanZero(double numero, int longitud) {
		if(numero != 0) {
			return ajustarCadenaRellenaIzquierda(getStrFromDouble(numero), longitud);
		}
		return ajustarCadenaRellenaIzquierda(CADENA_VACIA, longitud);
	}
	
	public static String ajustarCadenaRellenaIzquierda(String cadena, int longitud) {
		if(cadena == null) {
			return cadena;
		} else if(cadena.length() > longitud ) {
			return cadena.substring(0,longitud);
		} else if(cadena.length() < longitud ) {
			return padLeft(cadena,   longitud);
		} 
		return cadena;
	}
	public static String ajustarCadenaDerecha(String cadena, int longitud) {
		if(cadena == null) {
			return cadena;
		} else if(cadena.length() > longitud ) {
			return cadena.substring(0,longitud);
		} else if(cadena.length() < longitud ) {
			return padRight(cadena,   longitud);
		} 
		return cadena;
	}	
//	protected static String asignarCadena(String cadena) {
//		return (cadena == null)?"":cadena.trim();
//	}
	public static String padRight(String s, int n) {
	     return String.format("%-" + n + "s", s);  
	}
	public static String getStrFromDoubleConSepMiles(double numero) {
		return decimalFormatterSepMiles.format(numero);
	}
	public static String getStrFromDouble(double numero) {
		return decimalformatter.format(numero);
	}

	public static String padLeft(String s, int n) {
	    return String.format("%" + n + "s", s);  
	} 
	
	public static String pointerToRight(boolean value, Double mensaje, String mes, String acctid) {
		if(value) {
			return "<<<====" + mensaje+ " (" + mes+") "+ acctid +" ";
		} else {
			return CADENA_VACIA;
		}
	}
	
	
	/*
	 * ajusta rCadena a la Derecha
	 */
	public static String left(String cadena, int longitud) {
		if(cadena == null) {
			return cadena;
		} 
//		else if(cadena.length() > longitud ) {
//			return cadena.substring(0,longitud);
//		} else if(cadena.length() < longitud ) {
			return String.format("%" + longitud + "s", cadena);   
//		} 
//		return cadena;		
	}
	
	public static final List<String> calcularMesesReporte(String fecha){
		
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("d/MM/uuuu");
		
		if(!"".equals(fecha) && fecha!=null ) {
			fechaActual = LocalDate.parse(fecha,formater);
		}

		List<String> mesesReporte = new ArrayList<>();
		String conformato = fechaActual.format(formater);
		String recorFecha = recortar(conformato);
		String mes = recorFecha.substring(0,2);
		String anio =  recorFecha.substring(3,7);
		mesesReporte.add( anio.concat(mes));//2020 01
//		listAnio.add(anio);
//		listMes.add(MES[Integer.valueOf(mes)- 1]);
		listMesAnio.add(MES[Integer.valueOf(mes)- 1].concat(" ").concat(anio));// mes año | ENE 2020
		listMesAnioC.add(MESCOMPLETO[Integer.valueOf(mes)- 1].concat(" DE ").concat(anio));
		//mesesReporte.add(recorFecha);
		/*
		for(byte i = -1 ; i > -13; i--) {
			LocalDate fechaNueva = fechaActual.minusMonths(i);
			conformato = fechaNueva.format(formater);
			recorFecha = recortar(conformato);
			mesesReporte.add(recorFecha);
		}*/
		for(byte i = 1 ; i < 13; i++) {
			LocalDate fechaNueva = fechaActual.minusMonths(i);
			conformato = fechaNueva.format(formater);
			recorFecha = recortar(conformato);
			 mes = recorFecha.substring(0,2);
			 anio =  recorFecha.substring(3,7);
			mesesReporte.add( anio.concat(mes));
//			listAnio.add(anio);
//			listMes.add(MES[ Integer.valueOf(mes) - 1 ] );
			listMesAnio.add(MES[Integer.valueOf(mes)- 1].concat(" ").concat(anio));
			listMesAnioC.add(MESCOMPLETO[Integer.valueOf(mes)- 1].concat(" DE ").concat(anio));
			//mesesReporte.add(recorFecha);
		}
		LOG.info("mesesReporte: " + mesesReporte.toString());
		 return mesesReporte;
	}
	
	
	
	public static String  recortar(String s) {
		if(s.length()<10) {
			return s.substring(2,9);
		}
		return s.substring(3,10);
	}
	
	
	public static String left(double numero, int longitud) {
		String cadena = getStrFromDoubleConSepMiles(numero);
		if(cadena == null) {
			return cadena;
		} 
			return String.format("%" + longitud + "s", cadena);   
	
	}
	
	public static final List<String> calcularMesesReporte2(String fecha){
		
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("d/MM/uuuu");
		
		if(!"".equals(fecha) && fecha!=null ) {
			fechaActual = LocalDate.parse(fecha,formater);
		}

		List<String> mesesReporte = new ArrayList<>();
		String conformato = fechaActual.format(formater);

		mesesReporte.add(conformato);//2020 01

		for(byte i = 1 ; i < 13; i++) {
			LocalDate fechaNueva = fechaActual.minusMonths(i);
			conformato = fechaNueva.format(formater);

			mesesReporte.add( conformato);

		}
		LOG.info("fechas: " + mesesReporte.toString());
		 return mesesReporte;
	}
	
	public static final String validarString(String s) {
		if("".equals(s) || s == null ) {
			return "0.00";
		}
		return s;
	}
}
