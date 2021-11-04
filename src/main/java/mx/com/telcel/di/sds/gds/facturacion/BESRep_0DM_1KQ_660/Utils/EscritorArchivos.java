package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
public class EscritorArchivos {
	private File fichero;
	private OutputStreamWriter ow;
	private BufferedWriter escritor;
	private String rutaArchivo;
	public static final String ENCODE_CP1252 = "Cp1252";
	public static final String ENCODE_ISO88591 = "ISO-8859-1";
	public static final String ENCODE_UTF8 = "UTF-8";
	private String codificacion;
	
	public EscritorArchivos() {
		
	}
	public EscritorArchivos(String rutaArchivo) throws IOException {
		this.rutaArchivo = rutaArchivo;
		this.codificacion = ENCODE_UTF8;
		abrirEscritorArchivo();
	}
	public EscritorArchivos(String rutaArchivo, String codificacion) throws IOException {
		this.rutaArchivo = rutaArchivo;
		this.codificacion = codificacion;
		abrirEscritorArchivo();
	}
	
	private void abrirEscritorArchivo() throws IOException {
		fichero = new File( rutaArchivo);
		ow = new OutputStreamWriter(new FileOutputStream(fichero) , this.codificacion);
		escritor = new BufferedWriter(ow);		
	}
	public void escribe(String linea) throws IOException  {
		escritor.write(linea);
	}
	public void cerrar() throws IOException  {
		escritor.close();
		ow.close();
	}
}