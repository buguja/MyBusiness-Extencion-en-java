package GUI;

import javax.swing.*; 
import java.awt.*; 

public class JListYMas{ 
	
	public static void main(String[] args){ 
		//Creación de la ventana 
		JFrame ventana = new JFrame("Ejemplo JList con Scroll"); 
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ventana.setBounds(200,250,300,200); 
		ventana.setLayout(new GridLayout()); 
		
		//Creación del panel, que contendra JList 
		JPanel panel = new JPanel(); 
		panel.setLayout(null); 
		
		//creación de los elememtos que componen la lista 
		String[] nombres = {"Ana","Margarita","Daniela","Divian", 
		"Leslie","Paz","Andrea","Macarena"}; 
		//creación del objeto lista 
		JList lista = new JList(nombres); 
		//se cambia la orientación de presentación y el ajuste 
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		//selecciona un elemento de la lista 
		Object[] seleccion = lista.getSelectedValues(); 
		//recoge el indice de los seleccionados 
		int[] indices = lista.getSelectedIndices(); 
		// aquí se crea el objeto, es decir la barra de desplazamiento 
		JScrollPane barraDesplazamiento = new JScrollPane(lista); 
		barraDesplazamiento.setBounds(10,30,200,110); 
		//Agrega la barra de desplazamiento al panel 
		panel.add(barraDesplazamiento); 
		
		ventana.add(panel); 
		ventana.setVisible(true); 
	} 
}