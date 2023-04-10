package ProveedorPresentacion;

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
					int id=Integer.parseInt(jtextID.getText());
					Controlador.getInstancia().accion(Evento.PRODUCTO_POR_ID, new Integer(id));
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
	}
	
	private void mostrarProveedor(TProveedor pr){
		this.removeAll();
		this.setTitle("Proveedor "+ pr.getId());
		JPanel panel=new JPanel();
		
		//añado un boton de cerrar
		JButton cerrar =new JButton("Cerrar");
		cerrar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
			}
		});
		
		//Añado datos Proveedor
		StringBuilder buffer = new StringBuilder();
		buffer.append("Los datos del proveedor son: "+ System.lineSeparator());
		buffer.append("Id: "+ pr.getId()+ System.lineSeparator()+System.lineSeparator());
		buffer.append("Nombre: "+ pr.getNombre()+ System.lineSeparator()+System.lineSeparator());
		buffer.append("Marcas: "+ pr.getMarca()+ System.lineSeparator());
		
		panel.add(new JLabel(buffer.toString()));
		
		panel.add(cerrar);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.RES_PROVEEDOR_POR_ID_KO:{
			Utils.showErrorMsg("No se pudo encontrar el proveedor con dicho ID");
			setVisible(false);
		}case Evento.RES_PROVEEDOR_POR_ID_OK:{
			this.mostrarProveedor((TProveedor)datos);
		}
		}
	}
		
}
