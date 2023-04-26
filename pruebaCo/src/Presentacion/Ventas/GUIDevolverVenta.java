package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

public class GUIDevolverVenta extends JFrame implements GUI{
	public GUIDevolverVenta() {
		setTitle("Devolver producto");
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador de venta:");
		final JTextField tID= new JTextField(5);
		
		JLabel lID_PR=new JLabel("Identificador de producto:");
		final JTextField tID_PR= new JTextField(5);
		
		JLabel luds=new JLabel("Unidades:");
		final JTextField tuds= new JTextField(5);
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(lID);
		panel.add(tID);
		panel.add(lID_PR);
		panel.add(tID_PR);
		panel.add(luds);
		panel.add(tuds);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					int id=Integer.parseInt(tID.getText());
					int id_pr=Integer.parseInt(tID_PR.getText());
					int cantidad=Integer.parseInt(tuds.getText());
					List<Integer> datos=new ArrayList<Integer>();
					datos.add(id);
					datos.add(id_pr);
					datos.add(cantidad);
					Controlador.getInstancia().setGUI(GUIDevolverVenta.this);
					Controlador.getInstancia().accion(Evento.DEVOLUCION_VENTA, datos);
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
	if( Evento.RES_DEVOLUCION_VENTA_OK==evento){
		
		JOptionPane.showMessageDialog(null, "Se ha devuelto con éxito el producto");
	
	}
	if( Evento.RES_DEVOLUCION_VENTA_KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se ha podido devolver este producto");

	}
	}
}
