package Presentacion.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoTC;
import Negocio.Empleado.TEmpleadoTP;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIModificarEmpleado extends JFrame implements GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;

	public GUIModificarEmpleado() {
		setTitle("Modificar empleado");
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
		final JTextField tID= new JTextField(9);
		
		DefaultComboBoxModel<String> opciones=new DefaultComboBoxModel<>();
		opciones.addElement("Tiempo Parcial");
		opciones.addElement("Tiempo Completo");
		JComboBox<String>box=new JComboBox<>(opciones);
		
		
		JLabel lsalario= new JLabel("Salario");
		JTextField tsalario = new JTextField(9);
		tsalario.setEnabled(false);
		
		JLabel lbonus= new JLabel("Bonus");
		JTextField tbonus= new JTextField(9);
		tbonus.setEnabled(false);
		
		JLabel lhoras= new JLabel("Horas");
		JTextField thoras= new JTextField(9);
		
		JLabel lprecio_hora= new JLabel("Precio/hora");
		JTextField tprecio_hora= new JTextField(9);
		
		box.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				String opc= (String) box.getSelectedItem();
				if(opc.equalsIgnoreCase("Tiempo Parcial")){
					thoras.setEnabled(true);
					tprecio_hora.setEnabled(true);
					tsalario.setEnabled(false);
					tbonus.setEnabled(false);
				}
				else{
					thoras.setEnabled(false);
					tprecio_hora.setEnabled(false);
					tsalario.setEnabled(true);
					tbonus.setEnabled(true);
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
		panel.add(box);
		panel.add(lsalario);
		panel.add(tsalario);
		panel.add(lbonus);
		panel.add(tbonus);
		panel.add(lhoras);
		panel.add(thoras);
		panel.add(lprecio_hora);
		panel.add(tprecio_hora);
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
						String tipo=(String) box.getSelectedItem();
						TEmpleado t;
						if(tipo.equalsIgnoreCase("Tiempo Completo")){
							int salario = Integer.parseInt(tsalario.getText());
							int bonus = Integer.parseInt(tbonus.getText());
							 t= new TEmpleadoTC(nombre, apellido1, apellido2, DNI,tlf, Integer.parseInt(id), true,salario, bonus);
						}
						else{
							int horas= Integer.parseInt(thoras.getText());
							int precio_hora =Integer.parseInt(tprecio_hora.getText());
							 t= new TEmpleadoTP(nombre, apellido1, apellido2, DNI, tlf, Integer.parseInt(id) , true, horas, precio_hora);
						}
						Controlador.getInstancia().accion(Evento.MODIFICAR_EMPLEADO, t);	
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
		case Evento.OK:{
			Utils.showCorrectMsg("El empleado ha sido modificado con exito:"+ System.lineSeparator()+datos.toString());
			this.setVisible(false);
			break;
		}
		case Evento.KO:{
			Utils.showErrorMsg("El empleado no se ha podido modificar");
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
