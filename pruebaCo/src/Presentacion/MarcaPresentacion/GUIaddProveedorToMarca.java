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

	
	
	public GUIaddProveedorToMarca(){
		initGUI();
	}
	
	
	private void initGUI(){
		setTitle("Add Proveedor to Marca");
		//this.setMinimumSize(new Dimension(500, 500)); por??
		JPanel panel=new JPanel();
		panel.setMinimumSize(new Dimension(500, 500));
		JPanel texto=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		JLabel idMarca=new JLabel("ID-> Marca");
		final JTextField jTextMarca=new JTextField(20);
		JLabel idProveedor=new JLabel("ID-> Proveedor");
		final JTextField jTextProveedor=new JTextField(20);
		JPanel botones=new JPanel();
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
						Controlador.getInstancia().accion(Evento.ADD_PROVEEDOR_TO_MARCA,
								new TProveedorMarca(Integer.parseInt(jTextProveedor.getText()), Integer.parseInt(jTextMarca.getText())));
					}
			});
			
		
		texto.add(idMarca);
		texto.add(jTextMarca);
		texto.add(idProveedor);
		texto.add(jTextProveedor);
		botones.add(aceptar);
		botones.add(cerrar);
		panel.add(texto);
		panel.add(botones);
		this.getContentPane().add(panel);
		this.pack();
	}
	
	@Override
	public void update(int evento, Object datos) {
		setVisible(false);
		if(evento==Evento.OK){
			Utils.showCorrectMsg("Se ha podido dar de alta como proveedor de la marca ");
		}
		else {
			Utils.showErrorMsg("No se ha podido dar de alta como proveedor de la marca ");
		}
	}

	@Override
	public void setGUIVisible(boolean b) {
		setVisible(b);
		
	}

}
