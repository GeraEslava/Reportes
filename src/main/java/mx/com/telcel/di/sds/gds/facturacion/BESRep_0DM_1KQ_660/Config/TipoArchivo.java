package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config;


public enum TipoArchivo {
	
	REPORETE_ODM("ODM")
	,REPORETE_1KQ("1KQ")
	,REPORETE_660("660")
	,REPORETE_21K("21K");
	
	private String idTipoArchivo;
	
	private TipoArchivo(String idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}

	public String getIdTipoArchivo() {
		return idTipoArchivo;
	}
	public static TipoArchivo fromIdTipoArchivo(String text) {
		if( text == null) {
			return null;
		}
        for (TipoArchivo b : TipoArchivo.values()) {
            if (b.idTipoArchivo.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
