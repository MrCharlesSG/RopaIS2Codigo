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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Clientes.TCliente;
import Negocio.Clientes.TClienteNormal;
import Negocio.Clientes.TClientePremium;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIModificarCliente extends JFrame implements GUI{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;

		public GUIModificarCliente() {
			setTitle("Modificar cliente");
			this.setLocationRelativeTo(null);
			panel=new JPanel();
			
			JTextArea desc=new JTextArea("Introduzca el dni y posteriormente los datos que desea modificar");
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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
			
			JLabel lid= new JLabel("ID");
			final JTextField tID = new JTextField(9);
			
			JCheckBox tipo= new JCheckBox("Premium",false);
			
			JLabel lcodigo=new JLabel("Codigo Postal");
			JTextField tcodigo= new JTextField(9);
			tcodigo.setEnabled(false);
			
			JLabel lpoblacion=new JLabel("Poblacion");
			JTextField tpoblacion= new JTextField(12);
			
			
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
			
			JButton ok=new JButton("Aceptar");
			JButton cancelar=new JButton("Cancelar");
			
			JPanel botones =new JPanel();
			botones.add(ok);
			botones.add(cancelar);
			panel.add(desc);
			panel.add(lid);
			panel.add(tID);
			panel.add(lDNI);
			panel.add(tDNI);
			panel.add(lNombre);
			panel.add(tNombre);
			panel.add(lApellido1);
			panel.add(tApellido1);
			panel.add(lApellido2);
			panel.add(tApellido2);
			panel.add(ltlf);
			panel.add(ttlf);
			panel.add(tipo);
			panel.add(lcodigo);
			panel.add(tcodigo);
			panel.add(lpoblacion);
			panel.add(tpoblacion);
			panel.add(botones);
			getContentPane().add(panel);
			pack();
			
			ok.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{		
						setVisible(false);
						try{
							String id= tID.getText();
							String nombre= tNombre.getText();
							String apellido1=tApellido1.getText();
							String apellido2=tApellido2.getText();
							String DNI= tDNI.getText();
							int tlf= Integer.parseInt (ttlf.getText ());
							
							TCliente tC;
							if(tipo.isSelected()){
								int codigo= Integer.parseInt(tcodigo.getText());
								 tC= new TClientePremium (true, apellido1, apellido2, DNI, Integer.parseInt(id), nombre, tlf, true,codigo);
							}
							else{
								String poblacion= tpoblacion.getText();
								 tC= new TClienteNormal (true, apellido1, apellido2, DNI, Integer.parseInt(id), nombre, tlf, false, poblacion);
							}
							
							Controlador.getInstancia().accion(Evento.MODIFICAR_CLIENTE, tC);
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

		@Override
		public void update(int evento, Object datos) {
			switch(evento){
			case Evento.RES_MODIFICAR_CLIENTE_OK:{
				Utils.showCorrectMsg("El cliente" +datos.toString()+" ha sido modificado con exito");
				this.setVisible(false);
				break;
			}
			case Evento.RES_MODIFICAR_CLIENTE_KO:{
				Utils.showErrorMsg("El cliente no se ha podido modificar");
				this.setVisible(false);
				break;
			}
			}
			
		}
		
		@Override
		public void setGUIVisible(boolean b) {
			Utils.refreshTextFields(panel);
			this.setVisible(b);
		}

}