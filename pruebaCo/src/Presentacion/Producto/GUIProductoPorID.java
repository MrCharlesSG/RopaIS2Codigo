package Presentacion.Producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIProductoPorID extends JFrame implements GUI{
	
	public GUIProductoPorID(){
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
				setVisible(false);
				String id=jtextID.getText();
				TProducto tprod =new TProducto(null, Integer.parseInt(id), -1, -1, null, -1);
				Controlador.getInstancia().setGUI(GUIProductoPorID.this);
				Controlador.getInstancia().accion(Evento.PRODUCTO_POR_ID, tprod);
				
			}
			
		});
		JCancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		
		
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.RES_PRODUCTO_POR_ID_OK:
			//imprimir por pantalla el elemento
			StringBuilder str= new StringBuilder();
			TProducto tprod= (TProducto) datos;
			str.append("Se ha encontrado el producto con ID: " + tprod.getIdProducto() + System.lineSeparator());
			str.append("NOMBRE: " + tprod.getNombre() + "ID: " + tprod.getIdProducto() + "CANTIIDAD: " + tprod.getCantidad()
			+ "TALLA: "+ tprod.getTalla() +  "CATEGORIA: " + tprod.getCategoria() + "IDMARCA: " + tprod.getIdMarca());
			JOptionPane.showMessageDialog(null, str);
			break;
		case Evento.RES_BAJA_PRODUCTO_KO:
			JOptionPane.showMessageDialog(null, "No se pudo encontrar el producto con ese ID");
			break; 
		}
	}
		
}
