package Presentacion.MarcaPresentacion;

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
import Presentacion.ProveedorPresentacion.GUIAddMarcaToProveedor;

public class GUIaddProveedorToMarca extends JFrame implements GUI{

	
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	public GUIaddProveedorToMarca(){
		initGUI();
	}
	
	
	private void initGUI(){
		setTitle("Add Proveedor to Marca");
		this.setMinimumSize(new Dimension(500, 500));
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		JLabel idMarca=new JLabel("ID-> Marca");
		JTextField jTextMarca=new JTextField();
		JLabel idProveedor=new JLabel("ID-> Proveedor");
		JTextField jTextProveedor=new JTextField();
		
		//boton de cerrar
			JButton cerrar =new JButton("Cerrar");
			cerrar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
					{		
						setVisible(false);
					}
			});
		//boton de aceptar
			JButton aceptar =new JButton("Aceptar");
			aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
					{		
						setVisible(false);
						Controlador.getInstancia().setGUI(GUIaddProveedorToMarca.this);;
						Controlador.getInstancia().accion(Evento.ADD_PROVEEDOR_TO_MARCA,
								new TProveedorMarca(Integer.parseInt(jTextProveedor.getText()), Integer.parseInt(jTextMarca.getText())));
					}
			});
			
		
		panel.add(idMarca);
		panel.add(jTextMarca);
		panel.add(idProveedor);
		panel.add(jTextProveedor);
		panel.add(aceptar);
		panel.add(cerrar);
		this.add(panel);
	}
	
	@Override
	public void update(int evento, Object datos) {
		setVisible(false);
		if(evento==Evento.OK){
			Utils.showCorrectMsg(datos.toString());
		}
		else {
			Utils.showErrorMsg("No se ha dado de alta");
		}
	}

	@Override
	public void setGUIVisible(boolean b) {
		Utils.refreshTextFields(panel);
		setVisible(b);
		
	}

}
