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
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacionImp;
import Presentacion.GUI.GUI;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIBajaMarca extends JFrame implements GUI{
	/**
	 * 
	 */
	
	private JPanel panel;
	private static final long serialVersionUID = 1L;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIBajaMarca() {
		setTitle("Baja de Marca");
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel lID=new JLabel("Identificador:");
		final JTextField tID= new JTextField(5);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
		
		texto.add(lID);
		texto.add(tID);
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
					int id=Integer.parseInt(tID.getText());
					Controlador.getInstancia().accion(Evento.BAJA_MARCA, id);
					setVisible(false);
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
		setVisible(false);
		
		}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.RES_BAJA_MARCA_OK==evento) {
		Integer id= (Integer) datos;
		Utils.showCorrectMsg("La marca "+ id+ " se ha dado de baja.");
	}
	else if( Evento.RES_BAJA_MARCA_KO==evento) { 
		Utils.showCorrectMsg( "No se pudo dar de baja la marca");	
	}
		
	}

	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
		
	}
}