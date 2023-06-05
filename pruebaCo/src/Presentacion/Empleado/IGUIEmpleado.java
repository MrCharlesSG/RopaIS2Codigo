package Presentacion.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.GUI.GUI;

public class IGUIEmpleado extends JPanel implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IGUIEmpleado() {
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	JButton altaEmpleado=new JButton("Alta de empleado");
	JButton bajaEmpleado=new JButton("Baja de empleado");
	JButton listarEmpleado=new JButton("Listar empleados");
	JButton modificarEmpleado=new JButton("Modificar empleado");
	JButton empleadoPorID=new JButton("Empleado por ID");
	
	this.add(altaEmpleado);
	this.add(bajaEmpleado);
	this.add(listarEmpleado);
	this.add(modificarEmpleado);
	this.add(empleadoPorID);
	this.setVisible(false);
	
	altaEmpleado.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_EMPLEADO);
			gui.setGUIVisible(true);
			}
	});
	bajaEmpleado.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_EMPLEADO);
			gui.setGUIVisible(true);
			}
	});
	listarEmpleado.addActionListener(new ActionListener()
	{ public void actionPerformed(ActionEvent e)
		{		
		GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_EMPLEADO);
		gui.setGUIVisible(true);
		}
	});
	modificarEmpleado.addActionListener(new ActionListener()
	{ public void actionPerformed(ActionEvent e)
		{	
		GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_EMPLEADO);
		gui.setGUIVisible(true);
		}
	});
	empleadoPorID.addActionListener(new ActionListener()
	{ public void actionPerformed(ActionEvent e)
		{		
		GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.EMPLEADO_POR_ID);
		gui.setGUIVisible(true);
		}
	});
	
	}

	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}


	@Override
	public void update(int evento, Object datos) {}
	
}
