package Presentacion.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Clientes.TCliente;
import Negocio.Empleado.TEmpleado;
import Presentacion.Clientes.GUIClientePorID;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIEmpleadoPorID extends JFrame implements GUI{
	public GUIEmpleadoPorID() {
		setTitle("Empleado por ID");
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
					int id=Integer.parseInt(tID.getText());
					Controlador.getInstancia().setGUI(GUIEmpleadoPorID.this);
					Controlador.getInstancia().accion(Evento.EMPLEADO_POR_ID, id);
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
	if( Evento.OK==evento){
	TEmpleado c=(TEmpleado) datos;
		StringBuilder str=new StringBuilder();
		str.append("ID: NOMBRE: APELLIDO1: APELLIDO2: DNI: TLF: TIPO CONTRATO: ACTIVO").append(System.lineSeparator());
		str.append(c.getID()+":      "+c.getNombre()+":      "+c.getApellido1()+":      "+c.getApellido2()+":      "+c.getDNI() +":      "+c.getTfno()+":      "+c.isTiempoCompleto()+":      "+c.isActivo()).append(System.lineSeparator());
		JOptionPane.showMessageDialog(null, str.toString());
	
	}
	if( Evento.KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se pudo encontrar el empleado con ese ID");

	}
	}
}
