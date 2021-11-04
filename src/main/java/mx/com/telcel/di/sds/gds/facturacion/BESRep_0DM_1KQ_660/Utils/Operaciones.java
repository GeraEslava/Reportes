package mx.com.telcel.di.sds.gds.facturacion.Reportes_0DM_iKQ_660.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class Operaciones {
	
	private static final Logger LOG = LoggerFactory.getLogger("funcionalLogger");

	public static Double sumDoubles(Double a,Double b) {
//		LOG.info("a: " + a  + "  b: " +  b );
		BigDecimal valA = new BigDecimal(a.toString());
		BigDecimal valB = new BigDecimal(b.toString());
		return valA.add(valB).doubleValue();
	}
	
	public static Double sumarListaDoubles(List<Double> lista) {
		BigDecimal acum = new BigDecimal("0.0");
		for(Double a : lista) {
			BigDecimal valA = new BigDecimal(a.toString());
			acum = acum.add(valA);
//			LOG.info("Es: " + a  + " acum : " +  acum.doubleValue());
		}
		return acum.doubleValue();
	}
	
	public static Double restaDoubles(Double a,Double b) {
//		LOG.info("a: " + a  + "  b: " +  b );
		BigDecimal valA = new BigDecimal(a.toString());
		BigDecimal valB = new BigDecimal(b.toString());
		return valA.subtract(valB).doubleValue();
	}
	
	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
