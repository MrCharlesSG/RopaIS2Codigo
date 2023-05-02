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
import Negocio.Clientes.TClienteNormal;
import Negocio.Clientes.TClientePremium;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaCliente extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tNombre,tDNI, ttlf, tApellido1, tApellido2;
	private JButton premium, noPremium;
	public GUIAltaCliente() {
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		
		JPanel Nombre = new JPanel();
		Nombre.add(new JLabel("Nombre:"));
		Nombre.add(tNombre=new JTextField(20));
		
		JPanel Apellido1= new JPanel();
		Apellido1.add( new JLabel("Primer apellido"));
		Apellido1.add(tApellido1=new JTextField(20));
		
		JPanel Apellido2 = new JPanel();
		Apellido2.add( new JLabel("Segundo apellido"));		
		Apellido2.add(tApellido2=new JTextField(20));
		
		JPanel DNI = new JPanel();
		DNI.add( new JLabel("DNI"));
		DNI.add(tDNI= new JTextField(20));
		
		JPanel tlf = new JPanel();
		tlf.add( new JLabel("Tlf"));
		tlf.add( ttlf=new JTextField(20));
		
		JPanel tiempo=new JPanel();
		tiempo.add(premium=		new JButton("Premium"));
		tiempo.add( noPremium=new JButton("No Premium"));
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(Nombre);
		panel.add(Apellido1);
		panel.add(Apellido2);
		panel.add(DNI);
		panel.add(tlf);
		panel.add(tiempo);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
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
		
		noPremium.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
				try{
					String nombre= tNombre.getText();
					String apellido1=tApellido1.getText();
					String apellido2=tApellido2.getText();
					String DNI= tDNI.getText();
					int tlf= Integer.parseInt (ttlf.getText ());
					TClienteNormal tc= new TClienteNormal (true, apellido1, apellido2, DNI, -1, nombre, tlf, false);
					Controlador.getInstancia().setGUI(GUIAltaCliente.this);
					Controlador.getInstancia().accion(Evento.ALTA_CLIENTE, tc);	
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
