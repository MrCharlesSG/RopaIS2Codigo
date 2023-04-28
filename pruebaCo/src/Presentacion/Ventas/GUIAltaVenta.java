package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class GUIAltaVenta extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer,Integer> carro;
	private CarritoDialog carrito;
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
					try{
						int id_cliente=Integer.parseInt(tID.getText());
						int id_empleado=Integer.parseInt(tID_PR.getText());
						TVenta venta=new TVenta(-1,id_empleado,id_cliente,10.0,0,true);

						Controlador.getInstancia().setGUI(GUIAltaVenta.this);
						Controlador.getInstancia().accion(Evento.ABRIR_VENTA, venta);
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
	if( Evento.RES_ABRIR_VENTA_KO==evento){
		
		JOptionPane.showMessageDialog(null,"Identificadores incorrectos");
	
	}
	if(Evento.RES_ABRIR_VENTA_OK==evento)
	{
		if(carrito==null)
			carrito=new CarritoDialog(this,carro);
		boolean status=carrito.open();
		if(status){
			TVenta venta=(TVenta)datos;
			venta.setProductos(carro);
			Controlador.getInstancia().setGUI(GUIAltaVenta.this);
			Controlador.getInstancia().accion(Evento.CERRAR_VENTA, venta);
		}
	}
	if( Evento.RES_CERRAR_VENTA_OK==evento){
		
		JOptionPane.showMessageDialog(null,"Se han añadido correctamente los productos seleccionados en el carrito");
	
	}
	if(Evento.RES_CERRAR_VENTA_KO==evento)
	{
		JOptionPane.showMessageDialog(null,"No se han añadido correctamente los productos seleccionados en el carrito");
	}
	
	}
}