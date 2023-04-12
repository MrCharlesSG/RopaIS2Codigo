/**
 * 
 */
package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIModificarMarca extends JFrame implements GUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIModificarMarca() {
		setTitle("Alta Marca");
		JPanel panel=new JPanel();
		JLabel id=new JLabel("Identificador:");
		final JTextField tNombre= new JTextField(20);
		JButton aceptar=new JButton("Aceptar:");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(id);
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
					int id=Integer.parseInt(nombre);
					Controlador.getInstancia().accion(Evento.MODIFICAR_MARCA, new Integer(id));
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
		});
		
	}

	@Override
	public void update(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}
}