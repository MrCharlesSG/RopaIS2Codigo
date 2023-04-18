package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	private JToolBar _toolBar;
	private JButton quit;
	private JButton marca;
	private JButton producto;
	private JButton proveedor;
	private JButton cliente;
	
	public ControlPanel() {
		initGui();
	}

	private void initGui() {
		setLayout(new BorderLayout());
		_toolBar = new JToolBar();
		add(_toolBar, BorderLayout.PAGE_START);
		_toolBar.setPreferredSize(new Dimension(200, 58));
		
		//boton abrir menu mrcas
		marca = new JButton();
		marca.setToolTipText("abrir men� marcas");
		marca.setText("Marcas");
		marca.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.openView(0);
			}
			
		});
		_toolBar.add(marca);
		
		producto = new JButton();
		producto.setToolTipText("abrir men� productos");
		producto.setText("Productos");
		producto.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.openView(1);
			}
			
		});
		_toolBar.add(producto);
		proveedor = new JButton();
		proveedor.setToolTipText("abrir men� productos");
		proveedor.setText("Prov");
		proveedor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.openView(2);
			}
			
		});
		_toolBar.add(proveedor);
		
		//boton abrir menu clientes
		cliente = new JButton();
		cliente.setToolTipText("abrir men� clientes");
		cliente.setText("Clientes");
		cliente.addActionListener((e) -> MainWindow.openView(3));
		_toolBar.add(cliente);
		
		_toolBar.add(Box.createGlue()); // this aligns the button to the right
		_toolBar.addSeparator();
		quit = new JButton();
		quit.setToolTipText("Quit");
		quit.setIcon(new ImageIcon("resources/exit.png"));
		quit.addActionListener((e) -> Utils.quit(this));
		_toolBar.add(quit);
		
	}
}
