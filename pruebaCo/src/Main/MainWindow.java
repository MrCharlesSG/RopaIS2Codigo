package Main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Presentacion.MarcaPresentacion.IGUIMarca;
import Presentacion.Producto.IGUIProducto;

public class MainWindow extends JPanel{
	
		private static IGUIMarca marca = new IGUIMarca();
		private static IGUIProducto prod = new IGUIProducto();
		private JPanel options;
		private static JPanel infoPanel;
	
		public MainWindow(){
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
			options.add(marca);
			options.add(prod);
			w.add(options, BorderLayout.WEST);
			
			infoPanel = new JPanel();
			infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			w.add(infoPanel, BorderLayout.CENTER);
		}
		
		static void openView(int num){
			switch(num){
			case 0:
				prod.setVisible(false);
				marca.setVisible(true);
				break;
			case 1:
				prod.setVisible(true);
				marca.setVisible(false);
				break;
			}
		}
		
		public static void updateInfoPanel(JPanel p){
			infoPanel = p;
			infoPanel.setVisible(true);
		}
	
}
