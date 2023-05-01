package Presentacion.Producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIBajaProducto extends JFrame implements GUI{
	
	public GUIBajaProducto(){
		this.setTitle("Baja Producto");
		JPanel jpanel=new JPanel();
		this.setLocationRelativeTo(null);
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
				try{
					String id=jTextField.getText();
					TProducto tprod =new TProducto(null, -1, -1, Integer.parseInt(id), null, -1);
					Controlador.getInstancia().setGUI(GUIBajaProducto.this);
					Controlador.getInstancia().accion(Evento.BAJA_PRODUCTO, tprod);
				}catch(Exception e1){
					Utils.showErrorMsg("Los parámetros introducidos son incorrectos");
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
			JOptionPane.showMessageDialog(null,"Se ha dado de baja correctamente el producto");
			break;
		case Evento.KO:
			JOptionPane.showMessageDialog(null, "No se pudo encontrar el producto con ese ID");
			break; 
		}
	}
}
