package Presentacion.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Clientes.TCliente;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;


public class GUIListarClientes extends JFrame implements GUI {
	public GUIListarClientes(){
		setTitle("Listar clientes");
		JPanel panel=new JPanel();
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					Controlador.getInstancia().setGUI(GUIListarClientes.this);
					Controlador.getInstancia().accion(Evento.LISTAR_CLIENTES,null);
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
		if( Evento.LISTAR_CLIENTES==evento)
		{
			Collection<TCliente>clientes=(Collection<TCliente>) datos;
			StringBuilder str=new StringBuilder();
			str.append("ID: NOMBRE: APELLIDO1: APELLIDO2: DNI: TLF: PREMIUM: ACTIVO").append(System.lineSeparator());
			for(TCliente c: clientes){
				str.append(c.getID()+":      "+c.getNombre()+":      "+c.getApellido1()+":      "+c.getApellido2()+":      "+c.getDNI()+":      "+c.getTelefono()+":      "+c.getPremium()+":      "+c.getActivo()).append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(null, str.toString());
		}
	}

}