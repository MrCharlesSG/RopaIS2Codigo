package Presentacion.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
					Controlador.getInstancia().setGUI(GUIBajaCliente.this);
					Controlador.getInstancia().accion(Evento.BAJA_CLIENTE, new Integer(id));
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
