package Presentacion.ProveedorPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Proveedor.TProveedor;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIBajaProv extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIBajaProv(){
		
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Baja Producto");
		JPanel jpanel=new JPanel();
		JLabel jlabel=new JLabel("ID del Proveedor: ");
		final JTextField jTextField=new JTextField(20);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		
		jpanel.add(jlabel);
		jpanel.add(jTextField);
		jpanel.add(aceptar);
		jpanel.add(cancelar);
		
		this.getContentPane().add(jpanel);
		this.pack();
		
		aceptar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try{
					int id=Integer.parseInt(jTextField.getText());
					Controlador.getInstancia().accion(Evento.BAJA_PROVEEDOR, new TProveedor(null, id,null,true ));				
				}catch(Exception e1){
					Utils.showErrorMsg("Los parametros introducidos son incorrectos");
				}
			}
			
		});
		
		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//MainWindow.setGUIEvent(new JPanel());
			}
			
		});
		setVisible(true);
		//MainWindow.setGUIEvent(jpanel);
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.OK:{
			Utils.showCorrectMsg("El proveedor con id: "+datos.toString()+System.lineSeparator()+"Ha sido eliminado correctamente");
			this.setVisible(false);
			break;
		}case Evento.KO:{
			Utils.showErrorMsg("Ha habido un error al eliminar el proveedor");
			this.setVisible(false);
			break;
		}
		}
	}
}
