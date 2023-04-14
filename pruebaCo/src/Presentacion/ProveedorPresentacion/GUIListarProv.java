package Presentacion.ProveedorPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

public class GUIListarProv extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIListarProv(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Listar Proveedores");
		JPanel panel=new JPanel();
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					Controlador.getInstancia().accion(Evento.LISTAR_MARCAS,null);
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
			
		});
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	private void listaProveedores(ArrayList<Object> datos){
		this.removeAll();
		this.setTitle("Lista de Proveedores");
		JPanel panel=new JPanel();
		
		//añado un boton de cerrar
		JButton cerrar =new JButton("Cerrar");
		cerrar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
			}
		});
		
		//añado la lista
		DefaultListModel<String> modelo = new DefaultListModel<>();
		for (Object dato : datos) {
		    modelo.addElement(dato.toString());
		}
		JList<String> list = new JList<>(modelo);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Lista De Proveedores: "));
		panel.add(scrollPane);
		panel.add(cerrar);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.RES_LISTAR_PROVEEDOR_KO:{
			setVisible(false);
			Utils.showErrorMsg("No se ha podido listar los proveedores");
		}
		case Evento.RES_LISTAR_PROVEEDOR_OK:{
			this.listaProveedores((ArrayList<Object>)datos);
		}
		}
	}
}
