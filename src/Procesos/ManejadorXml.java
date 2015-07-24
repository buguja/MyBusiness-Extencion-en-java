package Procesos;

import java.io.File;
import java.util.ArrayList;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ManejadorXml {
	private ArrayList<String> nombresXML;	
	
	public ArrayList<String> getNombresXML() {
		return nombresXML;
	}
	
	/**
	 * Obtiene de la carpeta con los xml, los nombres de los archivos xml ubicados en la carpeta y estos nombres son
	 * almacenados en la varible "nombresXML". 
	 * @param rutaFolderXml Ruta estática de la carpeta que contiene los archivos xml a procesar
	 * 
	 */
	public ManejadorXml(String rutaFolderXml){
		File f = new File(rutaFolderXml);
		if(f.exists()){ //Comprobando que la carpeta especificada, exista o sea accecible
			nombresXML = new ArrayList<String>();
			File[] archivos = f.listFiles();
			for(int i=0; i<archivos.length; i++){
				String fileXML = archivos[i].getName();
				if(validarExtencionXML(fileXML)){
					nombresXML.add(fileXML); //Almacenando los nombres de los xml
				}
			}
		} else {
			System.out.println("La carpeta espesificada no existe");
		}
	}
	
	/**
	 * Valida que la cadena recibida sea el nombre de un archivo xml.
	 * @param archivo nombre y extención del archivo encontrado en la carpeta.
	 * @return "Verdadero" encaso de que el archivo sea un xml,
	 *         "Falso" en el caso contrario.
	 */
	private boolean validarExtencionXML(String archivo){
		try{
			String extencion = "";
			extencion = archivo.substring(archivo.lastIndexOf("."));
			if(extencion.compareTo(".xml") == 0){
				return true;
			}
		}catch(Exception e){

		}
		return false;
	}
	
	/**
	 * Lee el contenido del archivo xml y lo almacena en un objeto tipo Document para ser retornado
	 * @param archivo La instancia del archivo a procesar
	 * @return El contenido del archivo xml, el tipo de retorno es un objeto Document
	 */
	public Document leerXML(File archivo){
		Document documento = null;
		if(!archivo.exists()){
			return null;
		}
		
		try{
			documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo);
			documento.getDocumentElement().normalize();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return documento;
	}
}
