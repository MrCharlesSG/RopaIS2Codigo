package Presentacion.Producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIModificarProducto extends JFrame{
	
	public GUIModificarProducto(){
		this.setTitle("Modificar Producto");
		JPanel jpanel=new JPanel();
		JLabel jlabel=new JLabel("Nombre: ");
		final JTextField jTextField=new JTextField(20);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		jpanel.add(jlabel);
		jpanel.add(jTextField);
		jpanel.add(aceptar);
		jpanel.add(cancelar);
		
		this.getContentPane().add(jpanel);
		this.pack();
		
		aceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String nombre=jTextField.getName();
				int id=Integer.parseInt(nombre);
				Controlador.getInstancia().accion(Evento.MODIFICAR_PRODUCTO, new Integer(id));
				
			}
			
		});
		
		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
	}
}
