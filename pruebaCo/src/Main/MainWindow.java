package Main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JPanel{
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
		}
	
}
