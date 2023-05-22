package Presentacion.Producto;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import Main.Utils;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.GUIAltaMarca;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIModificarProducto extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel id;
	private JFormattedTextField idT;
	
	private JPanel nombre;
	private JTextField nombreT;
	
	private JPanel cantidad;
	private JFormattedTextField cantidadT;
	
	private JPanel talla;
	private JFormattedTextField tallaT;
	
	private JPanel categoria;
	private JTextField categoriaT;
	
	private JPanel idMarca;
	private JFormattedTextField idMarcaT;
	
	private JPanel but;
	private JButton aceptar;
	private JButton cancelar;
	
	private static final Dimension DIM = new Dimension(220, 20);
	
	public GUIModificarProducto(){
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		this.setLocationRelativeTo(null);
		this.setTitle("Modificar Producto");
		JPanel jpanel=new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
		
		jpanel.add(new JLabel("Introduzca el 'id' del producto a modificar."));
		
		id = new JPanel();
		id.add((new JLabel("ID: ")));
		id.add((idT = new JFormattedTextField(numberFormatter)));
		idT.setMaximumSize(DIM);
		idT.setMinimumSize(DIM);
		idT.setPreferredSize(DIM);
		jpanel.add(id);
		
		jpanel.add(new JLabel("Introduzca los datos a modificar."));
		
		nombre = new JPanel();
		nombre.add((new JLabel("Nombre: ")));
		nombre.add((nombreT = new JTextField(20)));
		jpanel.add(nombre);
		
		cantidad = new JPanel();
		cantidad.add((new JLabel("Cantidad: ")));
		cantidad.add((cantidadT = new JFormattedTextField(numberFormatter)));
		cantidadT.setMaximumSize(DIM);
		cantidadT.setMinimumSize(DIM);
		cantidadT.setPreferredSize(DIM);
		jpanel.add(cantidad);
		
		talla = new JPanel();
		talla.add((new JLabel("Talla: ")));
		talla.add((tallaT = new JFormattedTextField(numberFormatter)));
		tallaT.setMaximumSize(DIM);
		tallaT.setMinimumSize(DIM);
		tallaT.setPreferredSize(DIM);
		jpanel.add(talla);
		
		categoria = new JPanel();
		categoria.add((new JLabel("Categoría: ")));
		categoria.add((categoriaT = new JTextField(20)));
		jpanel.add(categoria);
		
		idMarca = new JPanel();
		idMarca.add((new JLabel("idMarca: ")));
		idMarca.add((idMarcaT = new JFormattedTextField(numberFormatter)));
		idMarcaT.setMaximumSize(DIM);
		idMarcaT.setMinimumSize(DIM);
		idMarcaT.setPreferredSize(DIM);
		jpanel.add(idMarca);
		
		but = new JPanel();
		but.add((aceptar=new JButton("Aceptar")));
		but.add((cancelar=new JButton("Cancelar")));
		jpanel.add(but);
		
		this.getContentPane().add(jpanel);
		this.pack();
		
		aceptar.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try{
					Controlador.getInstancia().setGUI(GUIModificarProducto.this);
					Controlador.getInstancia().accion(Evento.MODIFICAR_PRODUCTO, new TProducto(
							nombreT.getText(), Integer.parseInt(cantidadT.getText()), Integer.parseInt(tallaT.getText()),
							Integer.parseInt(idT.getText()) ,categoriaT.getText(), Integer.parseInt(idMarcaT.getText())));
				}catch(Exception e1){
					Utils.showErrorMsg("Los parametros introducidos son incorrectos");
				}
			}
			
		});
		
		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Se ha modificado correctamente el producto con ID: "+id.intValue());
			break;
		case Evento.KO:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el producto con ese ID");
			break; 
		}
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}
}
