package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Ventas.TVenta;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIDevolverVenta extends JFrame implements GUI{
	public GUIDevolverVenta() {
		setTitle("Devolver producto");
		this.setLocationRelativeTo(null);
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador de venta:");
		final JTextField tID= new JTextField(5);
		this.setLocationRelativeTo(null);
		JLabel lID_PR=new JLabel("Identificador de producto:");
		final JTextField tID_PR= new JTextField(5);
		
		JLabel luds=new JLabel("Unidades:");
		final JTextField tuds= new JTextField(5);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
		
		texto.add(lID);
		texto.add(tID);
		texto.add(lID_PR);
		texto.add(tID_PR);
		texto.add(luds);
		texto.add(tuds);
		botones.add(aceptar);
		botones.add(cancelar);
		panel.add(texto);
		panel.add(botones);
		
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{	
				try{
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
		setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.RES_DEVOLUCION_VENTA_OK==evento){
		
		JOptionPane.showMessageDialog(null, "Se ha devuelto con éxito el producto de la venta");
	
	}
	if( Evento.RES_DEVOLUCION_VENTA_KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se ha podido devolver este producto");

	}
	}
}
