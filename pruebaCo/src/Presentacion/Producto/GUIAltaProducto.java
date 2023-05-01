package Presentacion.Producto;

import java.awt.BorderLayout;
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
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaProducto extends JFrame implements GUI{
	
	private JLabel lNombre;
	private final JTextField tNombre;
	
	private JLabel lTalla;
	private final JTextField tTalla;
	
	private JLabel lCat;
	private final JTextField tCat;
	
	private JLabel lCant;
	private final JTextField tCant;
	
	private JLabel lIDMarca;
	private final JTextField tIDMarca;
	
	private JButton aceptar;
	private JButton cancelar;

	public GUIAltaProducto() {
		this.setTitle("Alta Producto");
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		JPanel nombre = new JPanel();
		nombre.add(lNombre=new JLabel("Nombre:"));
		nombre.add(tNombre= new JTextField(20));
		
		JPanel talla = new JPanel();
		talla.add(lTalla=new JLabel("Talla:"));
		talla.add(tTalla= new JTextField(20));
		
		JPanel cat = new JPanel();
		cat.add(lCat=new JLabel("Categoria:"));
		cat.add(tCat= new JTextField(20));
		
		JPanel cant = new JPanel();
		cant.add(lCant=new JLabel("Cantidad:"));
		cant.add(tCant= new JTextField(20));
		
		JPanel IdMarca = new JPanel();
		IdMarca.add(lIDMarca=new JLabel("IDMarca:"));
		IdMarca.add(tIDMarca= new JTextField(20));
		
		JPanel okKo = new JPanel();
		okKo.add(aceptar=new JButton("Aceptar"));
		okKo.add(cancelar=new JButton("Cancelar"));
		
		panel.add(nombre);
		panel.add(talla);
		panel.add(cat);
		panel.add(cant);
		panel.add(IdMarca);
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
						//nombre: id: cantidad: talla: categoria: idMarca:
						TProducto tP= new TProducto (nombre, Integer.parseInt(cant), Integer.parseInt(talla), -1, cat, Integer.parseInt(IDMarca));;
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
		case Evento.RES_ALTA_PRODUCTO_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Se ha dado de alta correctamente el producto");
			break;
		case Evento.RES_ALTA_PRODUCTO_KO:
			JOptionPane.showMessageDialog(null, "No se pudo dar de alta el producto correctamente");
			break; 
		}
	}
}
