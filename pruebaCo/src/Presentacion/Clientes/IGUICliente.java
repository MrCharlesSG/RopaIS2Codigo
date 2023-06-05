
package Presentacion.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.GUI.GUI;

public class IGUICliente extends JPanel implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
					
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_CLIENTE);
				gui.setGUIVisible(true);
				}
		});
		bajaCliente.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_CLIENTE);
				gui.setGUIVisible(true);
				}
		});
		listarCliente.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_CLIENTES);
			gui.setGUIVisible(true);
			}
		});
		modificarCliente.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{	
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_CLIENTE);
			gui.setGUIVisible(true);
			}
		});
		clientePorID.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.CLIENTE_POR_ID);
			gui.setGUIVisible(true);
			}
		});
		
	}

	@Override
	public void update(int evento, Object datos) {}

	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}
}

