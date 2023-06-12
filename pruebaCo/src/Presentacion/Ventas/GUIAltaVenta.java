package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		this.setLocationRelativeTo(null);
		JLabel lID_PR=new JLabel("Identificador de empleado:");
		final JTextField tID_PR= new JTextField(5);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
		
		texto.add(lID);
		texto.add(tID);
		texto.add(lID_PR);
		texto.add(tID_PR);
		botones.add(aceptar);
		botones.add(cancelar);
		panel.add(texto);
		panel.add(botones);
		
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
		setVisible(false);
	}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.RES_ABRIR_VENTA_KO==evento){
		
		JOptionPane.showMessageDialog(null,"Identificadores incorrectos");
	
	}
	if(Evento.RES_ABRIR_VENTA_OK==evento)
	{
		carro=new TreeMap<Integer,Integer>();
		if(carrito==null){
			carrito=new CarritoDialog(this,carro);
		}
			
		boolean status=carrito.open((int)datos);// si esta aqui es que se ha podido abrir la venta y que se le ha devuelto el id con el que se ha abierto 
		
	}
	if( Evento.RES_CERRAR_VENTA_OK==evento){
		
		JOptionPane.showMessageDialog(null,"Se han añadido correctamente los productos seleccionados en el carrito");
	
	}
	if(Evento.RES_CERRAR_VENTA_KO==evento)
	{
		JOptionPane.showMessageDialog(null,"No se han añadido correctamente los productos seleccionados en el carrito");
	}
	
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}
}