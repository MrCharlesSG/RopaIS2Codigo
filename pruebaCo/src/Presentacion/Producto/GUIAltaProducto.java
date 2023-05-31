package Presentacion.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaProducto extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField tNombre;
	
	private final JTextField tTalla;
	
	private final JTextField tCat;
	
	private final JTextField tCant;
	
	private final JTextField tIDMarca;
	
	private final JTextField tPrecio;
	
	private JButton aceptar;
	private JButton cancelar;

	public GUIAltaProducto() {
		this.setTitle("Alta Producto");
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		JPanel nombre = new JPanel();
		nombre.add(new JLabel("Nombre:"));
		nombre.add(tNombre= new JTextField(20));
		
		JPanel talla = new JPanel();
		talla.add(new JLabel("Talla:"));
		talla.add(tTalla= new JTextField(20));
		
		JPanel cat = new JPanel();
		cat.add(new JLabel("Categoria:"));
		cat.add(tCat= new JTextField(20));
		
		JPanel cant = new JPanel();
		cant.add(new JLabel("Cantidad:"));
		cant.add(tCant= new JTextField(20));
		
		JPanel precio = new JPanel();
		precio.add(new JLabel("Precio"));
		precio.add(tPrecio = new JTextField(20));
		
		JPanel IdMarca = new JPanel();
		IdMarca.add(new JLabel("IDMarca:"));
		IdMarca.add(tIDMarca= new JTextField(20));
		
		JPanel okKo = new JPanel();
		okKo.add(aceptar=new JButton("Aceptar"));
		okKo.add(cancelar=new JButton("Cancelar"));
		
		panel.add(nombre);
		panel.add(talla);
		panel.add(cat);
		panel.add(cant);
		panel.add(IdMarca);
		panel.add(precio);
		panel.add(okKo);
		this.getContentPane().add(panel);
		this.pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					try{
						String nombre= tNombre.getText();
						String talla = tTalla.getText();
						String cat = tCat.getText();
						String cant = tCant.getText();
						String IDMarca = tIDMarca.getText();
						String precio = tPrecio.getText();
						
						//nombre: id: cantidad: talla: categoria: idMarca:
						TProducto tP= new TProducto (nombre, Integer.parseInt(cant), Integer.parseInt(talla), -1, cat,
								Integer.parseInt(IDMarca), Double.parseDouble(precio));;
								
						Controlador.getInstancia().setGUI(GUIAltaProducto.this);
						Controlador.getInstancia().accion(Evento.ALTA_PRODUCTO, tP);
					}catch(Exception e1){
						Utils.showErrorMsg("Los parametros introducidos son incorrectos");
					}
					
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
		});
		
	
}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Se ha dado de alta correctamente el producto");
			break;
		case Evento.KO:
			JOptionPane.showMessageDialog(null, "No se pudo dar de alta el producto correctamente");
			break; 
		}
	}

	@Override
	public void setGUIVisible(boolean b) {
		tPrecio.setText("");
		tPrecio.repaint();
		tNombre.setText("");
		tNombre.repaint();
		tTalla.setText("");
		tTalla.repaint();
		tCat.setText("");
		tCat.repaint();
		tCant.setText("");
		tCant.repaint();
		tIDMarca.setText("");
		tIDMarca.repaint();
		this.setVisible(b);
	}
}
