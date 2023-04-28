package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.Controlador.Controlador;
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
				Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIAltaProveedor());
				}
		});
		
		devol.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){		
					
				Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIBajaProveedor());
				}
		});
		
		listarC.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e){		
				//este no usa el set Gui porque no tiene botones y directamente llama al controlador
				FactoriaPresentacion.getInstance().generaGUIListarProveedor();
		}
		});
		ventaPorID.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e){		
				
				Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIModificarProveedor());
			}
		});
		ventasPorEmpleados.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIVenta_Empleado());
			}
		});
		ventasPorCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIVenta_Cliente());
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
	public void update(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}

}
