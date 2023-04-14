
IGUICliente


	package Presentacion.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

public class IGUICliente extends JPanel{
	public IGUICliente() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton altaCliente=new JButton("Alta de cliente");
		JButton bajaCliente=new JButton("Baja de cliente");
		JButton listarCliente=new JButton("Listar clientes");
		JButton modificarCliente=new JButton("Modificar cliente");
		JButton clientePorID=new JButton("Cliente por ID");
		
		this.add(altaCliente);
		this.add(bajaCliente);
		this.add(listarCliente);
		this.add(modificarCliente);
		this.add(clientePorID);
		this.setVisible(false);
		
		altaCliente.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					
					FactoriaPresentacion.getInstance().generaGUIAltaCliente();
				}
		});
		bajaCliente.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUIBajaCliente();
			}
		});
		listarCliente.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				FactoriaPresentacion.getInstance().generaGUIListarCliente();
		}
		});
		modificarCliente.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{	
				FactoriaPresentacion.getInstance().generaGUIModificarCliente();
		}
		});
		clientePorID.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				FactoriaPresentacion.getInstance().generaGUIClientePorID();
		}
		});
		
	}
}

