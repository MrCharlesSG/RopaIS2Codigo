package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Presentacion.Clientes.IGUICliente;
import Presentacion.Empleado.IGUIEmpleado;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.IGUIMarca;
import Presentacion.Producto.IGUIProducto;
import Presentacion.ProveedorPresentacion.IGUIProv;
import Presentacion.Ventas.IGUIVentas;

public class MainWindow extends JPanel{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private static IGUIMarca marca;
		private static IGUIProducto prod;
		private static IGUIProv prov;
		private static IGUICliente clientes;
		private static IGUIEmpleado empleados;
		private static IGUIVentas ventas;
		private JPanel options;
		private static JPanel infoPanel;
		private static ArrayList<JPanel> iguis;
		
		
		public MainWindow(){
			iguis= new ArrayList<JPanel>();
			iguis.add( FactoriaPresentacion.getInstance().generaIGUIMarca());
			iguis.add(FactoriaPresentacion.getInstance().generaIGUIProducto());
			iguis.add(FactoriaPresentacion.getInstance().generaIGUIProveedores());
			iguis.add(FactoriaPresentacion.getInstance().generaIGUICliente());
			iguis.add( FactoriaPresentacion.getInstance().generaIGUIEmpleado());
			iguis.add(FactoriaPresentacion.getInstance().generaIGUIVenta());
			initGUI();
		}

		private void initGUI() {
			JFrame w= new JFrame("");
			w.setBounds(400,400,600,300);
			w.setVisible (true );
			w.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			
			JPanel controlPanel = new ControlPanel();
			w.add(controlPanel, BorderLayout.PAGE_START);
			
			options = new JPanel();
			setOptions(options);
			
			w.add(options, BorderLayout.WEST);
			
			//Futuras Versiones añadir info al panel de en medio
			infoPanel = new JPanel();
			infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			w.add(infoPanel, BorderLayout.CENTER);
		}
		
		static void openView(int num){
			
			for(int i=0; i<iguis.size(); i++) {
				if(num==i)
					iguis.get(i).setVisible(true);
				else
					iguis.get(i).setVisible(false);
			}
			
		}
		
		private static void setOptions(JPanel options) {
			for(JPanel p:iguis) {
				options.add(p);
			}
		}
		
		public static void updateInfoPanel(JPanel p){
			infoPanel = p;
			infoPanel.setVisible(true);
		}
	
}
