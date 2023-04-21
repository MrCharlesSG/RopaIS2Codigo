package Presentacion.Empleado;

import java.awt.Dimension;
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
import Presentacion.ProveedorPresentacion.GUIListarProv;

public class GUIListarEmpleados extends JFrame implements GUI{

	public GUIListarEmpleados(){
		initGUI();
	}
	
	private void initGUI() {
		Controlador.getInstancia().setGUI(GUIListarEmpleados.this);
		Controlador.getInstancia().accion(Evento.LISTAR_EMPLEADO,null);
	}

	private void listaEmpleados(ArrayList<Object> datos){
		this.setTitle("Lista de Empleados");
		this.setMinimumSize(new Dimension(500, 500));
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
		scrollPane.setBorder(BorderFactory.createTitledBorder("Lista De Empleados: "));
		panel.add(scrollPane);
		panel.add(cerrar);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.KO:{
			setVisible(false);
			Utils.showErrorMsg("No se ha podido listar los Empleados");
		}
		case Evento.OK:{
			this.listaEmpleados((ArrayList<Object>)datos);
		}
		}
	}
}
