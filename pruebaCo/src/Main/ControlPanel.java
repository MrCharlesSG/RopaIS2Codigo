package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JToolBar;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	private JToolBar _toolBar;
	private JButton quit;
	
	public ControlPanel() {
		initGui();
	}

	private void initGui() {
		setLayout(new BorderLayout());
		_toolBar = new JToolBar();
		add(_toolBar, BorderLayout.PAGE_START);
		_toolBar.setPreferredSize(new Dimension(200, 50));
		
		_toolBar.add(Box.createGlue()); // this aligns the button to the right
		_toolBar.addSeparator();
		quit = new JButton();
		quit.setToolTipText("Quit");
		quit.setIcon(new ImageIcon("resources/exit.png"));
		quit.addActionListener((e) -> Utils.quit(this));
		_toolBar.add(quit);
	}
}
