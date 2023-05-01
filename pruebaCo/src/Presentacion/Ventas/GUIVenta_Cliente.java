package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Ventas.TVenta;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIVenta_Cliente extends JFrame implements GUI{
	public GUIVenta_Cliente() {
		setTitle("Ventas de cliente");
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
					Controlador.getInstancia().setGUI(GUIVenta_Cliente.this);
					Controlador.getInstancia().accion(Evento.VENTAS_DE_UN_CLIENTE, new Integer(id));
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
	if( Evento.RES_VENTAS_DE_UN_CLIENTE_OK==evento){
		Collection<TVenta> ventas= (Collection<TVenta>) datos;
		StringBuilder str=new StringBuilder();
		str.append("ID de Venta").append(System.lineSeparator());
		for(TVenta v: ventas){
			str.append(v.get_id()).append(System.lineSeparator());
		}
		JOptionPane.showMessageDialog(null, str.toString());
	
	}
	if( Evento.RES_VENTAS_DE_UN_CLIENTE_KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se pudo encontrar el cliente con ese ID");

	}
	}
}
