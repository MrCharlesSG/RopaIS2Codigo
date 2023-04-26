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

import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;

public class GUIAltaVenta extends JFrame implements GUI{
	public GUIAltaVenta() {
		setTitle("Alta Venta");
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador de cliente:");
		final JTextField tID= new JTextField(5);
		
		JLabel lID_PR=new JLabel("Identificador de empleado:");
		final JTextField tID_PR= new JTextField(5);
		
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(lID);
		panel.add(tID);
		panel.add(lID_PR);
		panel.add(tID_PR);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					int id_cliente=Integer.parseInt(tID.getText());
					int id_empleado=Integer.parseInt(tID_PR.getText());
					Controlador.comprobarempleado(id_empleado);
					Controlador.comprobarcliente(id_cliente);
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
	if( Evento.RES_ABRIR_VENTA_KO==evento){
		
		JOptionPane.showMessageDialog(null,"Identificadores incorrectos");
	
	}
	if(Evento.RES_ABRIR_VENTA_OK==evento)
	{
		

	}
	}
}
