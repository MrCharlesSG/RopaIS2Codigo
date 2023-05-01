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
public class GUIModificarMarca extends JFrame implements GUI {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIModificarMarca() {
			setTitle("Alta Marca");
			JPanel panel=new JPanel();
			JLabel lId=new JLabel("Identificador:");
			final JTextField tId= new JTextField(10);
			JLabel lNombre=new JLabel("Nombre: ");
			final JTextField tNombre= new JTextField(20);
			JButton aceptar=new JButton("Aceptar:");
			JButton cancelar=new JButton("Cancelar");
			this.setLocationRelativeTo(null);
			
			panel.add(lId);
			panel.add(lId);
		
			panel.add(lNombre);
			panel.add(lNombre);
			panel.add(aceptar);
			panel.add(cancelar);
			getContentPane().add(panel);
			pack();
			
			aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
					TMarca tmarca=new TMarca(tNombre.getText(),Integer.parseInt(tId.getText()), 0,true);
					Controlador.getInstancia().accion(Evento.MODIFICAR_MARCA, tmarca);
				}
			});
			cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
				}
			});
			setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		if(Evento.MODIFICAR_MARCA==evento){
			JOptionPane.showMessageDialog(null, "La marca ha sido modificado con exito");
			this.setVisible(false);
		}
		else if(Evento.RES_MODIFICAR_MARCA_KO==evento){
			JOptionPane.showMessageDialog(null, "No se pudo modificar la marca");
		}
	
	}
}