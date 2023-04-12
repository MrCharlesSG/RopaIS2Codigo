/**
 * 
 */
package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIAltaMarca extends JFrame implements GUI{
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIAltaMarca() {
		setTitle("Alta Marca");
		JPanel panel=new JPanel();
		JLabel lNombre=new JLabel("Nombre:");
		final JTextField tNombre= new JTextField(20);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(lNombre);
		panel.add(tNombre);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					String nombre= tNombre.getText();
					TMarca tM= new TMarca (nombre,-1,1, 0);
					Controlador.getInstancia().setGUI(GUIAltaMarca.this);
					Controlador.getInstancia().accion(Evento.ALTA_MARCA, tM);
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		if(Evento.RES_ALTA_MARCA_OK==evento){
		Integer id= (Integer) datos;
		JOptionPane.showMessageDialog(null,"Alta de marca con ID: "+id.intValue());
		}
		else if( Evento.RES_ALTA_MARCA_KO==evento)
		{ 
		JOptionPane.showMessageDialog(null, "No se pudo dar de alta la marca"); 
		}
		
	}
}