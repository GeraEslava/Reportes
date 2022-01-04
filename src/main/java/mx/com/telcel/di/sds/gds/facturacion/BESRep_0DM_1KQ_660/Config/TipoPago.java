package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config;


public enum TipoPago {
	
	PAGO_NORMAL("NORMAL")
	,PAGO_ADELANTADO("ADELANTADO");
	
	private String idTipoPago;
	
	private TipoPago(String idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	public String getIdTipoPago() {
		return idTipoPago;
	}
	public static TipoPago fromIdTipoPago(String text) {
		if( text == null) {
			return null;
		}
        for (TipoPago b : TipoPago.values()) {
            if (b.idTipoPago.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
