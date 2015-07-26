/**
 * @author Javier Burón Gutiérrez; Lizeth Vásquez Rojas.
 *
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import Procesos.ManejadorXml;

public class Principal extends JFrame implements ListSelectionListener, ActionListener {
	private JList lista;
	private JTextField rutaCarpetaXML;
	private ManejadorXml mnXML;
	private JLabel nombreEmisorFactura = new JLabel("prueba");
	private JLabel rfcEmisorFactura = new JLabel("prueba");
	private JLabel folioFactura = new JLabel("prueba");
	private JLabel fechFactura = new JLabel("prueba");
	private JLabel folioFiscal = new JLabel("prueba");
	
	public Principal(){
		String ruta = obtenerRutaDirectorio();
		if(ruta == null){
			dispose();
			System.exit(1);
		}
		rutaCarpetaXML = new JTextField(ruta);
		this.mnXML = new ManejadorXml(rutaCarpetaXML.getText());
	
		Object[][] datos = new Object[100][7];
		datos = null;
		JPanel panelNorte = new JPanel(new BorderLayout());
		JPanel establecerRutaXML =new JPanel(new FlowLayout());
		rutaCarpetaXML.setEditable(false);
		rutaCarpetaXML.setPreferredSize(new Dimension(500, 30));
		establecerRutaXML.add(rutaCarpetaXML);
		JButton buscarRuta = new JButton("Elejir ruta");
		buscarRuta.addActionListener(this);
		establecerRutaXML.add(buscarRuta);
		
		JPanel datosFactura = new JPanel(new BorderLayout());
		nombreEmisorFactura.setHorizontalAlignment(JLabel.CENTER);
		datosFactura.add(BorderLayout.NORTH, nombreEmisorFactura);
		datosFactura.add(BorderLayout.WEST, rfcEmisorFactura);
		folioFiscal.setHorizontalAlignment(JLabel.CENTER);
		datosFactura.add(BorderLayout.CENTER, folioFiscal);
		datosFactura.add(BorderLayout.EAST, folioFactura);
		fechFactura.setHorizontalAlignment(JLabel.CENTER);
		datosFactura.add(BorderLayout.SOUTH, fechFactura);
		
		panelNorte.add(BorderLayout.WEST, new JLabel("logo"));
		panelNorte.add(BorderLayout.CENTER, establecerRutaXML);
		panelNorte.add(BorderLayout.EAST, new JLabel(acercaDe()));
		panelNorte.add(BorderLayout.SOUTH, datosFactura);
		
		add(BorderLayout.NORTH, panelNorte);
		add(BorderLayout.CENTER, crearTablaScroll(datos));
		add(BorderLayout.EAST, crearListaScroll(this.mnXML));
		configurarVentana();
	}
	
	private String acercaDe(){
		/*return "<html><body><table>"
				+ "<tr><th>Desarrollado por:</th><th>Dirigido por:</th></tr>"
				+ "<tr><td>Javier Burón Gutiérrea.<br>"
				+ "Lizeth Vásquez Rojas.</td><td>Ing. Ricardo Martínez Velázquez.</td></tr></table></body></html>";*/
		return "Desarrollado: Dirigido: ";
	}
	
	private void obtenerDatosFactura(Document document){
		NodeList emisor = document.getElementsByTagName("cfdi:Emisor");
		nombreEmisorFactura.setText(emisor.item(0).getAttributes().getNamedItem("nombre").getTextContent());
		rfcEmisorFactura.setText(emisor.item(0).getAttributes().getNamedItem("rfc").getTextContent());
		
		NodeList comprobante = document.getElementsByTagName("cfdi:Comprobante");
		folioFactura.setText(comprobante.item(0).getAttributes().getNamedItem("folio").getTextContent());
		fechFactura.setText(comprobante.item(0).getAttributes().getNamedItem("fecha").getTextContent());
		
		NodeList timbreFiscalDigital = document.getElementsByTagName("tfd:TimbreFiscalDigital");
		folioFiscal.setText(timbreFiscalDigital.item(0).getAttributes().getNamedItem("UUID").getTextContent());
	}
	
	private String obtenerRutaDirectorio(){
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setMultiSelectionEnabled(false);
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			return fc.getSelectedFile().getPath()+ "\\";
		}
		return null;
	}
	
	private JScrollPane crearListaScroll(ManejadorXml mnXML){
		String[] nombres = new String[mnXML.getNombresXML().size()];
		 mnXML.getNombresXML().toArray(nombres);
		 lista = new JList(nombres);
		 lista.addListSelectionListener(this);
		return new JScrollPane(lista);
	}
	
	private JScrollPane crearTablaScroll(Object[][] datos){
		String[] TitulosColumnas = {"Artículo","Descripción","Unidad","Cantidad","Precio","Importe", "Elegir"};
		DefaultTableModel dtm= new DefaultTableModel(datos, TitulosColumnas);
		JTable tabla = new JTable(dtm){
			public Class getColumnClass(int column){
            	if(column == (TitulosColumnas.length-1)){
                	return Boolean.class;
                }
                return String.class;
            }
		};
		tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
		return new JScrollPane(tabla);
	}
	
	private Object[][] obtenerDatosXML(String rutaXML){
		NodeList concepto = mnXML.leerXML(new File(rutaCarpetaXML.getText() + rutaXML)).getElementsByTagName("cfdi:Concepto");
		NamedNodeMap comprobante = concepto.item(0).getAttributes();
		Object[][] datos = new Object[concepto.getLength()][7];
		for(int i=0; i<concepto.getLength(); i++){
			datos[i][0] = comprobante.getNamedItem("noIdentificacion").getTextContent();
			datos[i][1] = comprobante.getNamedItem("descripcion").getTextContent();
			datos[i][2] = comprobante.getNamedItem("unidad").getTextContent();
			datos[i][3] = comprobante.getNamedItem("cantidad").getTextContent();
			datos[i][4] = comprobante.getNamedItem("valorUnitario").getTextContent();
			datos[i][5] = comprobante.getNamedItem("importe").getTextContent();
			datos[i][6] = new Boolean(true);;
		}
		return datos;
	}
	
	private void configurarVentana(){
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void crearCarpetaProcesados(){
		String carpetaProcesados = rutaCarpetaXML.getText() + "procesados";
		
		File procesados = new File(carpetaProcesados);
		if(!procesados.exists()){
			if(!procesados.mkdir()){
				System.out.println("No fue posible crear el directorio \"procesados\"");
			}
		}
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if((arg0.getSource() == lista) && !arg0.getValueIsAdjusting()){ //Escuchamos que el click sea a la lista de archivos xml
			BorderLayout tableLayout = (BorderLayout) getContentPane().getLayout();
			Component componente = tableLayout.getLayoutComponent(BorderLayout.CENTER);
			if(componente != null){
				remove(componente);
			}
			add(BorderLayout.CENTER, crearTablaScroll(obtenerDatosXML(lista.getSelectedValue().toString())));
			obtenerDatosFactura(mnXML.leerXML(new File(rutaCarpetaXML.getText() + lista.getSelectedValue().toString())));
			revalidate();
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		rutaCarpetaXML.setText(obtenerRutaDirectorio());
		crearCarpetaProcesados();
		mnXML=null;
		mnXML = new ManejadorXml(rutaCarpetaXML.getText());
		BorderLayout listLayout = (BorderLayout) getContentPane().getLayout();
		Component componente = listLayout.getLayoutComponent(BorderLayout.EAST);
		if(componente != null){
			remove(componente);
		}
		add(BorderLayout.EAST, crearListaScroll(this.mnXML));
		revalidate();
	}
}
