package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Model;

import mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Config.TipoArchivo;

public class ArchivoEscrito {
	private String region;
	private String pathFile;
	private String fechaGeneracion;
	private String extensionArchivo;
	private TipoArchivo tipoArchivo;
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPathFile() {
		return pathFile;
	}
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	public String getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	
	public ArchivoEscrito(String region, String pathFile, String extensionArchivo, TipoArchivo tipoArchivo) {
		super();
		this.region = region;
		this.pathFile = pathFile;
		this.extensionArchivo = extensionArchivo;
		this.tipoArchivo = tipoArchivo;
	}
	
	
	
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	public void setTipoArchivo(TipoArchivo tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	
	public TipoArchivo getTipoArchivo() {
		return tipoArchivo;
	}
	public ArchivoEscrito(String region, String pathFile, String fechaGeneracion, String extensionArchivo) {
		super();
		this.region = region;
		this.pathFile = pathFile;
		this.fechaGeneracion = fechaGeneracion;
		this.extensionArchivo = extensionArchivo;
	}
	
}
