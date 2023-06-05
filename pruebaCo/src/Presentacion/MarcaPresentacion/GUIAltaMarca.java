/**
 * 
 */
package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.FactoriaPresentacion.FactoriaPresentacionImp;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIAltaMarca extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIAltaMarca() {
		setTitle("Alta Marca");
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel lNombre=new JLabel("Nombre:");
		final JTextField tNombre= new JTextField(20);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
		
		texto.add(lNombre);
		texto.add(tNombre);
		botones.add(aceptar);
		botones.add(cancelar);
		panel.add(texto);
		panel.add(botones);
		
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				try{
					setVisible(false);
					String nombre= tNombre.getText();
					TMarca tM= new TMarca (nombre,-1, true);
					Controlador.getInstancia().accion(Evento.ALTA_MARCA, tM);
				}catch(Exception e1){
				Utils.showErrorMsg("Los parametros introducidos son incorrectos");
				}
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
		});
		this.setVisible(false);
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

	@Override
	public void setGUIVisible(boolean b) {
		Utils.refreshTextFields(panel);
		this.setVisible(b);
	}
	
	
}