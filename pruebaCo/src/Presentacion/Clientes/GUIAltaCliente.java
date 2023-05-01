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
import Negocio.Clientes.TClientePremium;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaCliente extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIAltaCliente() {
		setTitle("Alta Cliente");
		JPanel panel=new JPanel();
		this.setLocationRelativeTo(null);
		
		JLabel lNombre=new JLabel("Nombre:");
		final JTextField tNombre= new JTextField(20);
		
		JLabel lApellido1 = new JLabel("Primer apellido");
		final JTextField tApellido1=new JTextField(20);
		
		JLabel lApellido2 = new JLabel("Segundo apellido");		
		final JTextField tApellido2 = new JTextField(20);
		
		JLabel lDNI=new JLabel("DNI");
		final JTextField tDNI=new JTextField(9);
		
		JLabel ltlf=new JLabel("Tlf");
		final JTextField ttlf=new JTextField(9);
		
		JButton premium=new JButton("Premium");
		JButton no_premium=new JButton("No Premium");
		JButton cancelar=new JButton("Cancelar");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
		
		texto.add(lNombre);
		texto.add(tNombre);
		texto.add(lApellido1);
		texto.add(tApellido1);
		texto.add(lApellido2);
		texto.add(tApellido2);
		texto.add(lDNI);
		texto.add(tDNI);
		texto.add(ltlf);
		texto.add(ttlf);
		botones.add(premium);
		botones.add(no_premium);
		botones.add(cancelar);
		panel.add(texto);
		panel.add(botones);
		
		getContentPane().add(panel);
		pack();
		
		premium.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					try{
						String nombre= tNombre.getText();
						String apellido1=tApellido1.getText();
						String apellido2=tApellido2.getText();
						String DNI= tDNI.getText();
						int tlf= Integer.parseInt (ttlf.getText ());
						TClientePremium tCP= new TClientePremium (true, apellido1, apellido2, DNI, -1, nombre, tlf, true);
						Controlador.getInstancia().setGUI(GUIAltaCliente.this);
						Controlador.getInstancia().accion(Evento.ALTA_CLIENTE, tCP);	
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
		this.setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		if(Evento.RES_ALTA_CLIENTE_OK==evento){
		Integer id= (Integer) datos;
		JOptionPane.showMessageDialog(null,"Alta de cliente con ID: "+id.intValue());
		}
		else if( Evento.RES_ALTA_CLIENTE_KO==evento)
		{ 
		JOptionPane.showMessageDialog(null, "No se pudo dar de alta el cliente"); 
		}
		
	}
}
