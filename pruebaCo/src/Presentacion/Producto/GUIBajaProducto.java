package Presentacion.Producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIBajaProducto extends JFrame{
	
	public GUIBajaProducto(){
		this.setTitle("Baja Producto");
		JPanel jpanel=new JPanel();
		JLabel jlabel=new JLabel("IDProducto: ");
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
				String id=jTextField.getText();
				TProducto tprod =new TProducto(null, Integer.parseInt(id), -1, -1, null, -1);
				Controlador.getInstancia().accion(Evento.BAJA_PRODUCTO, tprod);
				
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
