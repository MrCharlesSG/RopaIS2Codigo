package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		private static List<JPanel> listaIGguis;
		
		
		public MainWindow(){
			listaIGguis= FactoriaPresentacion.getInstance().getIGUIs();
			initGUI();
		}

		private static void initGUI() {
			main= new JFrame("Tienda Ropa");
			main.setBounds(400,400,600,300);
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
			
			for(int i=0; i<listaIGguis.size(); i++) {
				if(num==i)
					listaIGguis.get(i).setVisible(true);
				else
					listaIGguis.get(i).setVisible(false);
			}
			
		}
		
		private static void setOptions(JPanel options) {
			for(JPanel p:listaIGguis) {
				options.add(p);
			}
		}
		
		public static void updateInfoPanel(JPanel p){
			infoPanel = p;
			infoPanel.setVisible(true);
		}
	
}
