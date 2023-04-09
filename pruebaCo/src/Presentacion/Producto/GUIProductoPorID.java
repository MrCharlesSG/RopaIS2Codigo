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

public class GUIProductoPorID extends JFrame{
	
	public GUIProductoPorID(){
		this.setTitle("Producto por ID");
		JPanel jpanel=new JPanel();
		JLabel jlabel=new JLabel("Identificador: ");
		
		final JTextField jtextID= new JTextField(5);
		JButton JAcceptButton=new JButton("Aceptar");
		JButton JCancelButton=new JButton("Cancelar");
		
		jpanel.add(jlabel);
		jpanel.add(jtextID);
		jpanel.add(JAcceptButton);
		jpanel.add(JCancelButton);
		
		getContentPane().add(jpanel);
		pack();
		
		
		JAcceptButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int id=Integer.parseInt(jtextID.getText());
				Controlador.getInstancia().accion(Evento.PRODUCTO_POR_ID, new Integer(id));
				
			}
			
		});
		JCancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		
		
	}
		
}
