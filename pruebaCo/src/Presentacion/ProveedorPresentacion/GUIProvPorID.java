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

public class GUIProvPorID extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIProvPorID(){
		initGUI();		
	}

	private void initGUI() {
		this.setTitle("Producto por ID");
		JPanel jpanel=new JPanel();
		JLabel jlabel=new JLabel("Identificador: ");
		this.setLocationRelativeTo(null);
		
		final JTextField jtextID= new JTextField(5);
		JButton JAcceptButton=new JButton("Aceptar");
		JButton JCancelButton=new JButton("Cancelar");
		
		jpanel.add(jlabel);
		jpanel.add(jtextID);
		jpanel.add(JAcceptButton);
		jpanel.add(JCancelButton);
		
		getContentPane().add(jpanel);
		pack();
		
		
		JAcceptButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					setVisible(false);
					Integer id=Integer.parseInt(jtextID.getText());
					Controlador.getInstancia().accion(Evento.PROVEEDOR_POR_ID,id);
				}catch(Exception e1){
					Utils.showErrorMsg("El ID debe de ser un numero");
					setVisible(false);
				}
				
			}
			
		});
		JCancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		this.setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.KO:{
			Utils.showErrorMsg("No se pudo encontrar el proveedor con dicho ID");
			setVisible(false);
		}case Evento.OK:{
			Utils.showCorrectMsg("Esto son los datos del Proveedor:"+System.lineSeparator()+datos.toString());
		}
		}
	}
		
}
