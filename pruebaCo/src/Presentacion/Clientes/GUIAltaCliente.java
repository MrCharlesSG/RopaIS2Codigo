package Presentacion.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Clientes.TCliente;
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
	
	private JPanel panel;
	
	private JTextField tNombre,tDNI, ttlf, tApellido1, tApellido2;
	private JButton premium, noPremium;
	public GUIAltaCliente() {
	this.setTitle("AltaCliente");{
		panel=new JPanel();
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
		JCheckBox tipo= new JCheckBox("Premium",false);
		
		JPanel panel_codigo= new JPanel();
		JLabel lcodigo=new JLabel("Codigo Postal");
		JTextField tcodigo= new JTextField(9);
		tcodigo.setEnabled(false);
		panel_codigo.add(lcodigo);
		panel_codigo.add(tcodigo);
		
		JPanel panel_poblacion= new JPanel();
		JLabel lpoblacion=new JLabel("Poblacion");
		JTextField tpoblacion= new JTextField(12);
		panel_poblacion.add(lpoblacion);
		panel_poblacion.add(tpoblacion);
		
		tipo.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(tipo.isSelected()){
					tcodigo.setEnabled(true);
					tpoblacion.setEnabled(false);
				}
				
				else{
					tcodigo.setEnabled(false);
					tpoblacion.setEnabled(true);
				}
			}
			
		});
		
		tiempo.add(tipo);
		
		
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(Nombre);
		panel.add(Apellido1);
		panel.add(Apellido2);
		panel.add(DNI);
		panel.add(tlf);
		panel.add(tiempo);
		
		panel.add(panel_codigo);
		
		panel.add(panel_poblacion);
		
		panel.add(aceptar);
		panel.add(cancelar);
		
		getContentPane().add(panel);
		pack();
		
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					try{
						String nombre= tNombre.getText();
						String apellido1=tApellido1.getText();
						String apellido2=tApellido2.getText();
						String DNI= tDNI.getText();
						int tlf= Integer.parseInt (ttlf.getText ());
						//String opc = (String) tipo.getSelectedItem();
						TCliente tCP;
						if(tipo.isSelected()){
							int codigo= Integer.parseInt(tcodigo.getText());
							tCP= new TClientePremium (true, apellido1, apellido2, DNI, -1, nombre, tlf, true,codigo);
							
						}
						else{
							String poblacion= tpoblacion.getText();
							tCP=new TClienteNormal(true, apellido1, apellido2, DNI, -1, nombre, tlf, false,poblacion);
						}
						
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
		this.setVisible(false);
	}
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
	
	@Override
	public void setGUIVisible(boolean b) {
		Utils.refreshTextFields(panel);
		this.setVisible(b);
	}
}
