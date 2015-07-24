/**
 * @author Javier Bur�n Guti�rrez; Lizeth V�squez Rojas.
 *
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Procesos.ManejadorXml;

public class Principal extends JFrame implements ActionListener {
	private JLabel tituloLbl;
	private JLabel infoLbl;
	private JTable tabla;
	private JTextArea area;
	
	public Principal(ManejadorXml manejadorXml) {
		//setModal(true);
		setTitle("CONTROL DE INVENTARIO");
		
		//CREACI�N DE PANELES
		JPanel panelDerecho = new JPanel();
		JPanel panelNorte = new JPanel();
		
		JPanel panelTituloLbl = new JPanel();
		JPanel panelInfoLbl = new JPanel();
		JPanel panelLista = new JPanel();
		
		//CREACI�N DEL CONTENIDO DE LOS PANELES
		tituloLbl = new JLabel("CONTROL DE INVENTARIO");
		infoLbl = new JLabel("Desarrollado por: " + "\n" + "Javier Bur�n Gutierrez | Lizeth V�squez Rojas");
		area = new JTextArea("Vista Factura");
		/*JScrollPane panel = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		area.setPreferredSize(new Dimension(200,390));*/
		
		//AGREGANDO CONTENIDO A LOS PANELES
		panelTituloLbl.add(tituloLbl);
		panelInfoLbl.add(infoLbl);
		
		//AGREGANDO PANEL A LOS PANELES CONTENEDORES
		panelNorte.setLayout(new GridLayout(1,3));
		panelNorte.add(panelTituloLbl);
		panelNorte.add(panelInfoLbl);
		//panelDerecho.add(panelLista);
		
		//MOSTRANDO PANEL EN LA VENTANA
		add(panelNorte,BorderLayout.NORTH);
		add(panelDerecho,BorderLayout.EAST);
		
		//CREACI�N DE LA TABLA
		String[] columnNames = {"Folio","Descripci�n","Todo",};
		Object[][] datos = {
				{"AH013D4", "PCC Ricardo Martinez Velazco", new Boolean(true)},
				{"AH015E9", "Ponle Color y Computo", new Boolean(false) },
				{"AH018F1", "Factura 2", new Boolean(true)}
		}; 
		
		DefaultTableModel dtm= new DefaultTableModel(datos,columnNames);
		 tabla = new JTable(dtm) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
	        };
		 tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
		 JScrollPane scrollPane = new JScrollPane(tabla);
		 getContentPane().add(scrollPane,BorderLayout.CENTER); 
		 
		 //CREACI�N DE LA LISTA
		 String[] nombres = {"CFDI-MAVR791010KM9-A-3338","CFDI-MAVR791010KM9-A-3338","CFDI-MAVR791010KM9-A-3338","Divian", 
							 "Leslie","Paz","Andrea","Macarena","Margarita","Daniela","Divian", 
							 "Leslie","Paz","Andrea","Macarena","Margarita","Daniela","Divian", 
							 "Leslie","Paz","Andrea","CFDI-MAVR791010KM9-A-3338","CFDI-MAVR791010KM9-A-3338","CFDI-MAVR791010KM9-A-3338"
							 ,"CFDI-MAVR791010KM9-A-3338","CFDI-MAVR791010KM9-A-3338"};
		 JList lista = new JList(nombres);
		 JScrollPane scroll = new JScrollPane(lista);
		 getContentPane().add(scroll, BorderLayout.EAST);
				 
		//ESCUCHAR A LOS EVENTOS
		//botonSalir.addActionListener(this);
		
		setSize(790,470);
		setLocationRelativeTo(null);
		//setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
