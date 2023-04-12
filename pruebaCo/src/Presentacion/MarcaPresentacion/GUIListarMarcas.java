/**
 * 
 */
package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class GUIListarMarcas extends JFrame implements GUI{
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIListarMarcas() {
		setTitle("Listar marcas");
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
					Controlador.getInstancia().setGUI(GUIListarMarcas.this);
					Controlador.getInstancia().accion(Evento.LISTAR_MARCAS,null);
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
	if( Evento.LISTAR_MARCAS==evento)
	{
		Collection<TMarca>marcas=(Collection<TMarca>) datos;
		StringBuilder str=new StringBuilder();
		str.append("ID: NOMBRE: CANTIDAD: ACTIVO").append(System.lineSeparator());
		for(TMarca m: marcas){
			str.append(m.getID()+":      "+m.getNombre()+":      "+m.getCantidad()+":      "+m.getActivo()).append(System.lineSeparator());
		}
		JOptionPane.showMessageDialog(null, str.toString());
	}
		
	}
}