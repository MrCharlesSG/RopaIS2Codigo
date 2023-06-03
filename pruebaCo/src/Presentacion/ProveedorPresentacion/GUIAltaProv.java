package Presentacion.ProveedorPresentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Proveedor.TProveedor;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaProv extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> modelo;
	
	private JPanel panel;
	
	public GUIAltaProv() {
		modelo = new DefaultListModel<>();
		initGUI();	
		setVisible(false);
	}
	
	private void initGUI(){
		setTitle("Alta Proveedor");
		panel=new JPanel();
		JLabel lNombre=new JLabel("Nombre:");
		final JTextField tNombre= new JTextField(20);
		
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					try{
						setVisible(false);
						
						Controlador.getInstancia().accion(Evento.ALTA_PROVEEDOR, new TProveedor(tNombre.getText(),2, true ));
					}catch(Exception e1){
						Utils.showErrorMsg("Error");
					}
				}

			
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
		});
		JPanel primerosDatos = new JPanel();
		primerosDatos.setLayout(new FlowLayout());
		primerosDatos.add(lNombre);
		primerosDatos.add(tNombre);
	
		
		JPanel listaMarcasPanle = new JPanel();
		listaMarcasPanle.setLayout(new FlowLayout());
		JPanel botonesPanel = new JPanel();
		botonesPanel.setLayout(new FlowLayout());
		botonesPanel.add(aceptar);
		botonesPanel.add(cancelar);
		
		//añado los paneles
		panel.add(primerosDatos, BorderLayout.NORTH);
		panel.add(listaMarcasPanle, BorderLayout.CENTER);
		panel.add(botonesPanel, BorderLayout.SOUTH);
		this.add(panel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(300, 400));
		setResizable(false);
		this.setLocationRelativeTo(null);
		pack();
		
	}

	@Override
	public void update(int evento, Object datos) {
		
		switch(evento){
		case Evento.OK:{
			Utils.showCorrectMsg("El Proveedor ha sido creado correctamente con el siguiente id: "+datos.toString());
			this.setVisible(false);
			break;
		}case Evento.KO:{
			Utils.showErrorMsg("Ha habido un error al crear el proveedor");
			this.setVisible(false);
			break;
		}
		}
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		Utils.refreshTextFields(panel);
		this.setVisible(b);
	}
}
