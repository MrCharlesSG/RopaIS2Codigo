package Presentacion.Producto;

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

public class GUIListarProductos extends JFrame implements GUI{
	
	public GUIListarProductos(){
		setTitle("Listar Prodcutos");
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
					Controlador.getInstancia().setGUI(GUIListarProductos.this);
					Controlador.getInstancia().accion(Evento.LISTAR_PRODUCTOS,null);
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
			
		});
	}

	@Override
	public void update(int evento, Object datos) {
		Collection<TProducto>prod=(Collection<TProducto>) datos;
		StringBuilder str=new StringBuilder();
		str.append("NOMBRE:	ID: CATEGORIA: TALLA: CANTIDAD: IDMARCA:").append(System.lineSeparator());
		for(TProducto p: prod){
			str.append(p.getNombre()+":      "+p.getIdProducto()+":      "+p.getCategoria()+":      "+p.getTalla()
			+":      "+p.getCantidad()+":      "+p.getIdMarca()).append(System.lineSeparator());
		}
		JOptionPane.showMessageDialog(null, str.toString());
	}
}
