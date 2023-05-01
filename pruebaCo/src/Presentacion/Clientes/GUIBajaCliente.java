package Presentacion.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIBajaCliente extends JFrame implements GUI{
	public GUIBajaCliente() {
		setTitle("Baja de Cliente");
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador:");
		final JTextField tID= new JTextField(5);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
		
		texto.add(lID);
		texto.add(tID);
		botones.add(aceptar);
		botones.add(cancelar);
		panel.add(texto);
		panel.add(botones);
		
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					try{
						int id=Integer.parseInt(tID.getText());
						Controlador.getInstancia().setGUI(GUIBajaCliente.this);
						Controlador.getInstancia().accion(Evento.BAJA_CLIENTE, new Integer(id));
					}catch(Exception e1){
						Utils.showErrorMsg("Los parametros introducidos son incorrectos");
					}
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
	if( Evento.RES_BAJA_CLIENTE_OK==evento) {
		Integer id= (Integer) datos;
		JOptionPane.showMessageDialog(null,"Se ha dado de baja correctamente el cliente con ID: "+id.intValue());
	}
	else if( Evento.RES_BAJA_CLIENTE_KO==evento) { 
		JOptionPane.showMessageDialog(null, "No se pudo dar de baja el cliente");	
	}
		
	}
}
