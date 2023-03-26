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

public class GUIMarcaPorProducto extends JFrame{
	
	public GUIMarcaPorProducto(){
		this.setTitle("Marca Por Producto");
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
				TMarca tmarca=new TMarca(nombre, 0, 0);
				Controlador.getInstancia().accion(Evento.MARCA_POR_PRODUCTO, tmarca);
				
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

