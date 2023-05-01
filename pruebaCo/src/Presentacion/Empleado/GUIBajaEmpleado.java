package Presentacion.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class GUIBajaEmpleado extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIBajaEmpleado() {
		setTitle("Baja de Empleado");
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
					try{
						int id=Integer.parseInt(tID.getText());
						Controlador.getInstancia().setGUI(GUIBajaEmpleado.this);
						Controlador.getInstancia().accion(Evento.BAJA_CLIENTE, id);
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
	if( Evento.OK==evento) {
		Integer id= (Integer) datos;
		JOptionPane.showMessageDialog(null,"Se ha dado de baja correctamente el empleado con ID: "+id.intValue());
	}
	else if( Evento.KO==evento) { 
		JOptionPane.showMessageDialog(null, "No se pudo dar de baja el empleado");	
	}
		
	}
}
