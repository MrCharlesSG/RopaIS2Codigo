package Presentacion.ProveedorPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIModificarProv extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIModificarProv(){
		initGUI();
	}
	
	public void initGUI(){
		this.setTitle("Modificar Proveedor");
		JPanel panel= new JPanel();
		//Solo se permite modificar el nombre del proveedor
		JLabel lID=new JLabel("Identificador: ");
		JTextField jtextID= new JTextField(5);
		JLabel lNombre = new JLabel("Nombre: ");
		JTextField jtextNombre= new JTextField(20);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		
		
		aceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String[] datos = new String[2];
				datos[0]=jtextID.getText();
				datos[1]= jtextNombre.getText();
				Controlador.getInstancia().accion(Evento.MODIFICAR_PROVEEDOR, datos);
			}
			
		});
		
		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		
		panel.add(lID);
		panel.add(jtextID);
		panel.add(lNombre);
		panel.add(jtextNombre);
		panel.add(aceptar);
		panel.add(cancelar);
		this.getContentPane().add(panel);
		this.pack();
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.RES_MODIFICAR_PROVEEDOR_OK:{
			Utils.showCorrectMsg("El proveedor ha sido modificado con exito:"+ System.lineSeparator()+datos.toString());
			this.setVisible(false);
			break;
		}
		case Evento.RES_MODIFICAR_PROVEEDOR_KO:{
			Utils.showErrorMsg("El proveedor no se ha podido modificar");
			this.setVisible(false);
			break;
		}
		}
	}
}
