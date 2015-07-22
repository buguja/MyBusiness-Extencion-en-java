/**
 * @author Javier Burón Gutiérrez; Lizeth Vásquez Rojas.
 *
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Principal extends JDialog implements ActionListener {
	private JLabel logoLbl;
	private JLabel tituloLbl;
	private JLabel infoLbl;
	
	public Principal() {
		setModal(true);
		
		//Creacion de paneles
		/*JPanel panelDerecho = new JPanel();*/
		JPanel panelCentro = new JPanel(); 
		JPanel panelNorte = new JPanel();
		
		JPanel panelTituloLbl = new JPanel();
		JPanel panelInfoLbl = new JPanel();
		JPanel panelTabla = new JPanel();
		
		//Creación de contenido de los paneles
		tituloLbl = new JLabel("CONTROL DE INVENTARIO");
		infoLbl = new JLabel("Desarrollado por: " + "\n" + "Javier Burón Gutierrez | Lizeth Vásquez Rojas");
		
		//Agregando contenido a su panel correspondiente
		panelTituloLbl.add(tituloLbl);
		panelInfoLbl.add(infoLbl);
		
		//Agregando panel a paneles
		panelNorte.setLayout(new GridLayout(1,3));
		panelNorte.add(panelTituloLbl);
		panelNorte.add(panelInfoLbl);
		
		//Mostrando panel en ventana
		add(panelNorte,BorderLayout.NORTH);
		
		//Creación de tabla
		Object[][] datos = {
				{"AH017D4", "PCC Ricardo Martinez Velazco", new Boolean(false)},
				{"AH017E9", "Ponle Color y Computo", new Boolean(true) },
				{"AH017F1", "Factura 2", new Boolean(false)},
		};
		String[] columnNames = {"Folio","Descripción","Todo",};
		DefaultTableModel dtm= new DefaultTableModel(datos,columnNames);
		final JTable table = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		//Escuchar a los eventos
		//botonSalir.addActionListener(this);
		
		setSize(790,470);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args){
		new Principal();
	}
}
