package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.GUI.GUI;

public class IGUIVentas extends JPanel implements GUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IGUIVentas(){
		initGUI();			
	}
	
	private void initGUI(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton altaV=new JButton("Alta de Venta");
		JButton devol=new JButton("Devolucion");
		JButton listarC=new JButton("Listar Ventas");
		JButton ventaPorID=new JButton("Venta por ID");
		JButton ventasPorEmpleados = new JButton("Ventas por Empleados");
		JButton ventasPorCliente = new JButton("Ventas por Cliente");
		
		altaV.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){	
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ABRIR_VENTA);
				gui.setGUIVisible(true);
			}
		});
		
		devol.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){		
					
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.DEVOLUCION_VENTA);
				gui.setGUIVisible(true);	
			}
		});
		
		listarC.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e){		
				//este no usa el set Gui porque no tiene botones y directamente llama al controlador
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_VENTAS);
				gui.setGUIVisible(true);
			}
		});
		ventaPorID.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e){		
				
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.VENTA_POR_ID);
				gui.setGUIVisible(true);
			}
		});
		ventasPorEmpleados.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.VENTAS_DE_UN_EMPLEADO);
				gui.setGUIVisible(true);
			}
		});
		ventasPorCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.VENTAS_DE_UN_CLIENTE);
				gui.setGUIVisible(true);
			}
		});
		
		
		this.add(altaV);
		this.add(devol);
		this.add(listarC);
		this.add(ventaPorID);
		this.add(ventasPorEmpleados);
		this.add(ventasPorCliente);
		this.setVisible(false);
	}

	@Override
	public void update(int evento, Object datos) {}
	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}

}
