package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

public class MainWindow extends JPanel{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private static JPanel options;
		private static JFrame main;
		private static JPanel infoPanel;
		private static JPanel eventPanel;
		private static List<JPanel> listaIGuis;
		
		
		public MainWindow(){
			setListIGUIS();
			initGUI();
		}

		private void setListIGUIS() {
			listaIGuis = new ArrayList<JPanel>();
			listaIGuis.add((JPanel) FactoriaPresentacion.getInstance().generaGUI(Evento.Mostrar_GUI_MARCA));
			listaIGuis.add((JPanel) FactoriaPresentacion.getInstance().generaGUI(Evento.Mostrar_GUI_PRODUCTOS));
			listaIGuis.add((JPanel) FactoriaPresentacion.getInstance().generaGUI(Evento.Mostrar_GUI_PROVEEDORES));
			listaIGuis.add((JPanel) FactoriaPresentacion.getInstance().generaGUI(Evento.Mostrar_GUI_CLIENTES));
			listaIGuis.add((JPanel) FactoriaPresentacion.getInstance().generaGUI(Evento.Mostrar_GUI_EMPLEADOS));
			listaIGuis.add((JPanel) FactoriaPresentacion.getInstance().generaGUI(Evento.Mostrar_GUI_VENTAS));
		}

		private static void initGUI() {
			main= new JFrame("Tienda Ropa");
			main.setBounds(400,400,600,350);
			main.setVisible (true );
			main.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			main.setLocationRelativeTo(null);
			
			JPanel controlPanel = new ControlPanel();
			main.add(controlPanel, BorderLayout.PAGE_START);
			
			options = new JPanel();
			setOptions(options);
			
			main.add(options, BorderLayout.WEST);
			eventPanel = new JPanel();
			main.add(eventPanel);
			//Futuras Versiones añadir info al panel de en medio
			infoPanel = new JPanel();
			infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			main.add(infoPanel, BorderLayout.CENTER);
		}
		
		/*public static void setGUIEvent(JPanel panel) {
			
			main.remove(eventPanel);
			main.add(panel, BorderLayout.EAST);
			main.repaint();
			
		}*/
		
		static void openView(int num){
			
			for(int i=0; i<listaIGuis.size(); i++) {
				if(num==i)
					listaIGuis.get(i).setVisible(true);
				else
					listaIGuis.get(i).setVisible(false);
			}
			
		}
		
		private static void setOptions(JPanel options) {
			for(JPanel p:listaIGuis) {
				p.setVisible(false);
				options.add(p);
			}
		}
		
		public static void updateInfoPanel(JPanel p){
			infoPanel = p;
			infoPanel.setVisible(true);
		}
	
}
