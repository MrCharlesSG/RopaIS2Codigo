package Presentacion.Empleado;

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
import Negocio.Empleado.TEmpleadoTC;
import Negocio.Empleado.TEmpleadoTP;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaEmpleado extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField tNombre,tDNI, ttlf, tApellido1, tApellido2;
	private JButton tc, tp;
	public GUIAltaEmpleado() {
		setTitle("Alta Empleado");
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
		tiempo.add(tc=		new JButton("Tiempo Completo"));
		tiempo.add( tp=new JButton("Tiempo Parcial"));
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
		
		tc.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
					try{
					String nombre= tNombre.getText();
					String apellido1=tApellido1.getText();
					String apellido2=tApellido2.getText();
					String DNI= tDNI.getText();
					int tlf= Integer.parseInt (ttlf.getText ());
					//String nombre, String apellido1, String apellido2, String DNI, int tfno, int ID, boolean activo
					TEmpleadoTC tCP= new TEmpleadoTC (nombre, apellido1, apellido2, DNI, tlf, -1,  true);
					Controlador.getInstancia().setGUI(GUIAltaEmpleado.this);
					Controlador.getInstancia().accion(Evento.ALTA_EMPLEADO, tCP);
				}catch(Exception e1){
					Utils.showErrorMsg("Los parametros introducidos son incorrectos");
				}
				}
		});
		
		tp.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
		{		
			setVisible(false);
			try{
				String nombre= tNombre.getText();
				String apellido1=tApellido1.getText();
				String apellido2=tApellido2.getText();
				String DNI= tDNI.getText();
				int tlf= Integer.parseInt (ttlf.getText ());
				//String nombre, String apellido1, String apellido2, String DNI, int tfno, int ID, boolean activo
				TEmpleadoTP tCP= new TEmpleadoTP (nombre, apellido1, apellido2, DNI, tlf, -1,  true);
				Controlador.getInstancia().setGUI(GUIAltaEmpleado.this);
				Controlador.getInstancia().accion(Evento.ALTA_EMPLEADO, tCP);
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
		if(Evento.OK==evento){
		Integer id= (Integer) datos;
		JOptionPane.showMessageDialog(null,"Alta de empleado con ID: "+id.intValue());
		}
		else if( Evento.KO==evento)
		{ 
		JOptionPane.showMessageDialog(null, "No se pudo dar de alta el empleado"); 
		}
		
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}
	
}
