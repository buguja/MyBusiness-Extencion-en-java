import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import GUI.Principal;
import Procesos.ManejadorXml;

/**
 * @author Javier Burón Gutiérrez; Lizeth Vásquez Rojas.
 *
 */

public class Ejecutar {

	public static void main(String[] args) {
		ManejadorXml mnXML = new ManejadorXml("c:\\xmls\\");
		ArrayList<Document> documentos = mnXML.getRutasXML();
		Principal principalGUI = null;
		Object[][] datos = new Object[documentos.size()][3];
		
		for(int i=0; i<documentos.size(); i++){
			NamedNodeMap comprobante = documentos.get(i).getElementsByTagName("cfdi:Comprobante").item(0).getAttributes();
			datos[i][0] = comprobante.getNamedItem("folio").getTextContent();
			
			NodeList concepto= documentos.get(i).getElementsByTagName("cfdi:Concepto");
			datos[i][1] = concepto.item(0).getAttributes().getNamedItem("descripcion").getTextContent();
			
			datos[i][2] = new Boolean(true);
			/*Esto está bien pero aun no se puede agregar mas de un atributo en la tabla de del gui principal
			 * 
			 * for(int j=0; j<concepto.getLength(); j++){
				System.out.println(concepto.item(j).getAttributes().getNamedItem("unidad"));
				System.out.println(concepto.item(j).getAttributes().getNamedItem("cantidad"));
				System.out.println(concepto.item(j).getAttributes().getNamedItem("descripcion"));
			}*/
		}
		
		principalGUI = new Principal(datos);
	}
}
