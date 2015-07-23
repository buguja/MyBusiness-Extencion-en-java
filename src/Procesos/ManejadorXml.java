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
	private ArrayList<Document> rutasXML;	
	
	public ArrayList<Document> getRutasXML() {
		return rutasXML;
	}

	public ManejadorXml(String rutaFolderXml){
		File f = new File(rutaFolderXml);
		if(!f.exists()){
			System.out.println("la carpeta espesificada no existe");
		}
		
		rutasXML = new ArrayList<Document>();
		File[] archivos = f.listFiles();
		for(int i=0; i<archivos.length; i++){
			String fileXML = rutaFolderXml + archivos[i].getName();
			if(validarExtencionXML(fileXML)){
				rutasXML.add(leerXML(new File(fileXML)));
			}
		}
	}
	
	public boolean validarExtencionXML(String archivo){
		try{
			String extencion = "";
			extencion = archivo.substring(archivo.lastIndexOf("."));
			if(extencion.compareTo(".xml") == 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
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
