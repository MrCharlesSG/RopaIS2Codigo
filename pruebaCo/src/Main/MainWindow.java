package Main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.MarcaPresentacion.IGUIMarca;
import Presentacion.Producto.IGUIProducto;
import Presentacion.ProveedorPresentacion.IGUIProv;

public class MainWindow extends JPanel{
	
		private static IGUIMarca marca;
		private static IGUIProducto prod;
		private static IGUIProv prov;
		private JPanel options;
		private static JPanel infoPanel;
	
		public MainWindow(){
			marca = FactoriaPresentacion.getInstance().generaIGUIMarca();
			prod = FactoriaPresentacion.getInstance().generaIGUIProducto();
			prov = FactoriaPresentacion.getInstance().generaIGUIProveedores();
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
			options.add(prov);
			
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
				prov.setVisible(false);
				break;
			case 1:
				prod.setVisible(true);
				marca.setVisible(false);
				prov.setVisible(false);
				break;
			
			case 2:
				prod.setVisible(false);
				marca.setVisible(false);
				prov.setVisible(true);
				break;
			}
		}
		
		public static void updateInfoPanel(JPanel p){
			infoPanel = p;
			infoPanel.setVisible(true);
		}
	
}
