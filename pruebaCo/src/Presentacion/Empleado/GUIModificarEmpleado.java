package Presentacion.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import Presentacion.Clientes.GUIModificarCliente;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIModificarEmpleado extends JFrame implements GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIModificarEmpleado() {
		setTitle("Modificar empleado");
		
		JPanel panel=new JPanel();
		
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
		
		DefaultComboBoxModel<String> opciones=new DefaultComboBoxModel<>();
		opciones.addElement("Tiempo Parcial");
		opciones.addElement("Tiempo Completo");
		JComboBox<String>box=new JComboBox<>(opciones);
		
		JButton ok=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		JPanel botones =new JPanel();
		botones.add(ok);
		botones.add(cancelar);
		panel.add(desc);
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
		panel.add(botones);
		getContentPane().add(panel);
		pack();
		
		ok.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					String nombre= tNombre.getText();
					String apellido1=tApellido1.getText();
					String apellido2=tApellido2.getText();
					String DNI= tDNI.getText();
					int tlf= Integer.parseInt (ttlf.getText ());
					String tipo=(String) box.getSelectedItem();
					TEmpleado t;
					if(tipo.equalsIgnoreCase("Premium")){
						//
						 t= new TEmpleadoTC(nombre, apellido1, apellido2, DNI,tlf, -1, true);
					}
					else{
						 t= new TEmpleadoTP(nombre, apellido1, apellido2, DNI, tlf, -1 , true);
					}
					Controlador.getInstancia().setGUI(GUIModificarEmpleado.this);
					Controlador.getInstancia().accion(Evento.MODIFICAR_CLIENTE, t);
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
}
