package Presentacion.ProveedorPresentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.ProveedorMarca.TProveedorMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIDeleteMarcaOfProveedor extends JFrame implements GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	public GUIDeleteMarcaOfProveedor() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Delete Marca Of Proveedor");
		this.setMinimumSize(new Dimension(500, 500));
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		JLabel idPL = new JLabel("ID-> Prov");
		JTextField jtP= new JTextField();
		JLabel idML = new JLabel("ID-> Marca");
		JTextField jtM= new JTextField();
		
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
							Controlador.getInstancia().setGUI(GUIDeleteMarcaOfProveedor.this);
							Controlador.getInstancia().accion(Evento.DELETE_MARCA_OF_PROVEEDOR,
									new TProveedorMarca(Integer.parseInt(jtP.getText()), Integer.parseInt(jtM.getText())));
						}
				});
		panel.add(idPL);
		panel.add(jtP);
		panel.add(idML);
		panel.add(jtM);
		panel.add(aceptar);
		panel.add(cerrar);
		this.add(panel);
	}
	
	@Override
	public void update(int evento, Object datos) {
		setVisible(false);
		if(evento== Evento.OK) {
			Utils.showCorrectMsg("ELIMINADO CON ÉXITO");
		}else {
			Utils.showErrorMsg("No se ha dado de baja");
		}
	}

	@Override
	public void setGUIVisible(boolean b) {
		Utils.refreshTextFields(panel);
		setVisible(b);
	}

}
