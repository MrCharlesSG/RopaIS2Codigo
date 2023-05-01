package Presentacion.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

public class IGUIEmpleado extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IGUIEmpleado() {
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	JButton altaEmpleado=new JButton("Alta de empleado");
	JButton bajaEmpleado=new JButton("Baja de empleado");
	JButton listarEmpleado=new JButton("Listar empleado");
	JButton modificarEmpleado=new JButton("Modificar empleado");
	JButton empleadoPorID=new JButton("empleado por ID");
	
	this.add(altaEmpleado);
	this.add(bajaEmpleado);
	this.add(listarEmpleado);
	this.add(modificarEmpleado);
	this.add(empleadoPorID);
	this.setVisible(false);
	
	altaEmpleado.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				
				FactoriaPresentacion.getInstance().generaGUIAltaEmpleado();
			}
	});
	bajaEmpleado.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				FactoriaPresentacion.getInstance().generaGUIBajaEmpleado();
		}
	});
	listarEmpleado.addActionListener(new ActionListener()
	{ public void actionPerformed(ActionEvent e)
		{		
			FactoriaPresentacion.getInstance().generaGUIListarEmpleado();
	}
	});
	modificarEmpleado.addActionListener(new ActionListener()
	{ public void actionPerformed(ActionEvent e)
		{	
			FactoriaPresentacion.getInstance().generaGUIModificarEmpleado();
	}
	});
	empleadoPorID.addActionListener(new ActionListener()
	{ public void actionPerformed(ActionEvent e)
		{		
			FactoriaPresentacion.getInstance().generaGUIEmpleadoPorID();
	}
	});
	
	}

}
