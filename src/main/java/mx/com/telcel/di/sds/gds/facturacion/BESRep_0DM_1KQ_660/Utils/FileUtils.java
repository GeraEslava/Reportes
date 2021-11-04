package mx.com.telcel.di.sds.gds.facturacion.BESRep_0DM_1KQ_660.Utils;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileUtils {
	
	private static final Logger log = LoggerFactory.getLogger("funcionalLogger");
	
	public static List<File> leerArchivos(String pathInput, String prefix, String cadenaEnNombre, String extension, String extensionCtl) throws IOException {		
		
//		log.info("Se tiene el path:" + pathInput + " prefix: " + prefix +  " extension: " + extension);
		List<File> archivosEjecucion = Files.walk(Paths.get(pathInput))
                .filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().startsWith(prefix))
                .filter(file -> file.getFileName().toString().contains(cadenaEnNombre))
                .filter(file -> file.getFileName().toString().endsWith(extension) ||file.getFileName().toString().endsWith(extensionCtl) )//".txt"
                .map(Path::toFile)
                .collect(Collectors.toList());
//		log.info("Tam archivosEjecucion: " + archivosEjecucion.size());;
		return archivosEjecucion;
	}
	public static List<File> leerArchivos(String pathInput, String prefix, String cadenaEnNombre, String extension) throws IOException {		
		
//		log.info("Se tiene el path:" + pathInput + " prefix: " + prefix +  " extension: " + extension);
		List<File> archivosEjecucion = Files.walk(Paths.get(pathInput))
                .filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().startsWith(prefix))
                .filter(file -> file.getFileName().toString().contains(cadenaEnNombre))
                .filter(file -> file.getFileName().toString().endsWith(extension))//".txt"
                .map(Path::toFile)
                .collect(Collectors.toList());
//		log.info("Tam archivosEjecucion: " + archivosEjecucion.size());;
		return archivosEjecucion;
	}

	public static List<File> leerArchivos(String pathInput, String prefix, String extension) throws IOException {		
		
//		log.info("Se tiene el path:" + pathInput + " prefix: " + prefix +  " extension: " + extension);
		List<File> archivosEjecucion = Files.walk(Paths.get(pathInput))
                .filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().startsWith(prefix))
                .filter(file -> file.getFileName().toString().endsWith(extension))//".txt"
                .map(Path::toFile)
                .collect(Collectors.toList());
//		log.info("Tam archivosEjecucion: " + archivosEjecucion.size());;
		return archivosEjecucion;
	}
	
	public static boolean crearDirectorio(String path) {
		File directorio = new File(path);
	    if(directorio.exists()) {
	    	return false;
	    }
	    if( directorio.mkdirs() ) {
	    	return true;
	    }
	    return false;
	}
	
	public boolean moveFileToDirectory(File sourceFile, File targetPath, String idProc) {
	    if (targetPath.exists()) {
	        String newFilePath = targetPath + File.separator + sourceFile.getName() + "." + idProc;
	        File movedFile = new File(newFilePath);
	        if (movedFile.exists())
	            movedFile.delete();
	        return sourceFile.renameTo(new File(newFilePath));
	    } else {
	    	log.warn("unable to move file "+sourceFile.getName()+" to directory "+targetPath+" -> target directory does not exist");
	        return false;
	    }       
	}
	
	public boolean moveFileToDirectory(File sourceFile, File targetPath) {
	    if (targetPath.exists()) {
	        String newFilePath = targetPath + File.separator + sourceFile.getName() ;
	        File movedFile = new File(newFilePath);
	        if (movedFile.exists())
	            movedFile.delete();
	        return sourceFile.renameTo(new File(newFilePath));
	    } else {
	    	log.warn("unable to move file "+sourceFile.getName()+" to directory "+targetPath+" -> target directory does not exist");
	        return false;
	    }       
	}
	
	// we use the older file i/o operations for this rather than the newer jdk7+ Files.move() operation
	public static boolean moveFileToDirectory(File sourceFile, String targetPath, String idProc) {
	    File tDir = new File(targetPath);
	    if (tDir.exists()) {
	        String newFilePath = targetPath + File.separator + sourceFile.getName() + "." + idProc;
	        File movedFile = new File(newFilePath);
	        if (movedFile.exists())
	            movedFile.delete();
	        return sourceFile.renameTo(new File(newFilePath));
	    } else {
	    	log.warn("unable to move file "+sourceFile.getName()+" to directory "+targetPath+" -> target directory does not exist");
	        return false;
	    }       
	}
	
	
	// we use the older file i/o operations for this rather than the newer jdk7+ Files.move() operation
		public static boolean moveFileToDirectory(File sourceFile, String targetPath) {
		    File tDir = new File(targetPath);
		    if (tDir.exists()) {
//		    	log.info("La ruta existe");
		        String newFilePath = targetPath + File.separator + sourceFile.getName() ;
		        File movedFile = new File(newFilePath);
		        if (movedFile.exists()) {
		        	log.info("Eliminando el archivo porque ya existe");
		        	movedFile.delete();
		        }   
		        log.info("Moviendo el archivo a:" + newFilePath);
		        return sourceFile.renameTo(new File(newFilePath));
		    } else {
		    	log.warn("unable to move file "+sourceFile.getName()+" to directory "+targetPath+" -> target directory does not exist");
		        return false;
		    }       
		}
	
	public static boolean moverArchivo(String pathInput, String pathDone) {
		Path origenPath = FileSystems.getDefault().getPath(pathInput);
        Path destinoPath = FileSystems.getDefault().getPath(pathDone);

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.info("Error al tratar de mover el archivo: " + pathInput ,e );
            return false;
        }
		return true;
	}
	
	public boolean concatenarArchivos(String[] arg) throws IOException {
		Path outFile = Paths.get(arg[arg.length-1]);
//	    System.out.println("TO "+outFile);
	    try(FileChannel out = FileChannel.open(outFile, CREATE, WRITE)) {
	    	
	      for(int ix = 0, n = arg.length - 1; ix < n; ix++) {
	    	  
	        Path inFile = Paths.get(arg[ix]);
//	        System.out.println(inFile + "...");
	        try(FileChannel in = FileChannel.open(inFile, READ)) {
	        	
	          for(long p = 0, l = in.size(); p < l; )
	            p += in.transferTo(p, l - p, out);
	        }
	        
	      }
	    }
	    return true;
//	    System.out.println("DONE.");
	}
}
