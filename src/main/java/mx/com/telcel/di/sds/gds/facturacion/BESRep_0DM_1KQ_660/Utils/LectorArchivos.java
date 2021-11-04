package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectorArchivos {
	File archivo;
	String rutaArchivo;
	FileReader fr;
	BufferedReader bf;
	public LectorArchivos(File archivo) throws IOException, FileNotFoundException {
		this.archivo = archivo;
		this.rutaArchivo = archivo.getAbsolutePath();
		abrir();
	}
	public LectorArchivos(String rutaArchivo) throws IOException, FileNotFoundException {
		this.rutaArchivo = rutaArchivo;
		abrir();
	}
	public void abrir()throws IOException, FileNotFoundException {
		archivo = new File( rutaArchivo);
		fr = new FileReader(archivo.getAbsolutePath());
		bf = new BufferedReader(fr);
	}
	
	public String leerLinea() throws IOException {
		return bf.readLine();
	}
	
	public void cerrar() throws IOException {		
		bf.close();
		fr.close();
	}
	public File getArchivo() {
		return archivo;
	}

}
