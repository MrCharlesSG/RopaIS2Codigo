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

public class GUIListarProductos extends JFrame{
	
	public GUIListarProductos(){
		setTitle("Listar Prodcutos");
		JPanel panel=new JPanel();
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					Controlador.getInstancia().accion(Evento.LISTAR_MARCAS,null);
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
			
		});
	}
}
