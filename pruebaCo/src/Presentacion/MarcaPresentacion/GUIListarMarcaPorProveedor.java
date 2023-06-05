package Presentacion.MarcaPresentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.ProveedorPresentacion.GUIListarProveedoresPorMarca;

public class GUIListarMarcaPorProveedor extends JFrame implements GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	
	public GUIListarMarcaPorProveedor() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Listar Marcas por Proveedor");
		setTitle("Listar Proveedores");
		this.setMinimumSize(new Dimension(500, 500));
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		
		JLabel idLabel = new JLabel("ID Proveedor:");
		JTextField jtId= new JTextField();
		//añado un boton de cerrar
		JButton cerrar =new JButton("Cerrar");
		cerrar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
				}
		});
		
		//Añado boton aceptar
		JButton aceptar =new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					//Controlador.getInstancia().setGUI(GUIListarMarcaPorProveedor.this);
					Controlador.getInstancia().accion(Evento.LISTAR_MARCA_POR_PROVEEDORES,Integer.parseInt(jtId.getText()));
				}
		});
		panel.add(idLabel);
		panel.add(jtId);
		panel.add(aceptar);
		panel.add(cerrar);
		this.add(panel);
	}
	
	

	@Override
	public void update(int evento, Object datos) {
		if(evento==Evento.OK) {
			ArrayList<TMarca> list = (ArrayList<TMarca>) datos;
			setVisible(false);
			Utils.showCorrectMsg(list.toString());
		}else {
			setVisible(false);
			Utils.showErrorMsg("No se ha podido listar las Marcas");
		}
	}

	@Override
	public void setGUIVisible(boolean b) {
		Utils.refreshTextFields(panel);
		setVisible(b);
	}


}
