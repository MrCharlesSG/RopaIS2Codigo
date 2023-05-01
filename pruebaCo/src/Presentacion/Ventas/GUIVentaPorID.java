package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.MarcaNegocio.TMarca;
import Negocio.Ventas.TVenta;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.GUIMarcaPorID;

public class GUIVentaPorID extends JFrame implements GUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public GUIVentaPorID() {
		setTitle("Venta por ID");
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador:");
		final JTextField tID= new JTextField(5);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					int id=Integer.parseInt(tID.getText());
					Controlador.getInstancia().setGUI(GUIVentaPorID.this);
					Controlador.getInstancia().accion(Evento.VENTA_POR_ID, new Integer(id));
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
	if( Evento.RES_VENTA_POR_ID_OK==evento){
		TVenta v=(TVenta) datos;
		StringBuilder str=new StringBuilder();
		str.append("ID: ID EMPLEADO: ID CLIENTE: PRECIO: UNIDADES: ACTIVO: PRODUCTOS ").append(System.lineSeparator());
		str.append(v.get_id()+":      "+v.get_id_empleado()+":      "+v.get_id_cliente()+":      "+v.get_precio()+":      "+v.get_contador()+":      "+v.get_activo()+":      ");
		Map<Integer,Integer>vm=v.get_map();
		for(Integer id : v.get_map().keySet()){
			str.append(id+" "+vm.get(id)+ " ");
		}
		str.append(System.lineSeparator());
		JOptionPane.showMessageDialog(null, str.toString());
	}
	if( Evento.RES_VENTA_POR_ID_KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se pudo encontrar la marca con ese ID");

	}
	}
}
