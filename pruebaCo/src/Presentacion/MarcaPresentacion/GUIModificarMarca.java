/**
 *
 */
package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Main.Utils;
import Negocio.MarcaNegocio.TMarca;
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
public class GUIModificarMarca extends JFrame implements GUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIModificarMarca() {
			setTitle("Modificar Marca");
			JPanel panel=new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			JLabel lId=new JLabel("Identificador:");
			final JTextField tId= new JTextField(10);
			JLabel lNombre=new JLabel("Nombre: ");
			final JTextField tNombre= new JTextField(20);
			JButton aceptar=new JButton("Aceptar:");
			JButton cancelar=new JButton("Cancelar");
			this.setLocationRelativeTo(null);
			JPanel texto=new JPanel();
			JPanel botones=new JPanel();
			
			texto.add(lId);
			texto.add(tId);
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
					TMarca tmarca=new TMarca(tNombre.getText(),Integer.parseInt(tId.getText()), 0,true);
					Controlador.getInstancia().accion(Evento.MODIFICAR_MARCA, tmarca);
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
		if(Evento.RES_MODIFICAR_MARCA_OK==evento){
			JOptionPane.showMessageDialog(null, "La marca ha sido modificado con exito");
			this.setVisible(false);
		}
		else if(Evento.RES_MODIFICAR_MARCA_KO==evento){
			JOptionPane.showMessageDialog(null, "No se pudo modificar la marca");
		}
	
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}
}