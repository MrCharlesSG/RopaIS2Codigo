package Presentacion.Producto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaProducto extends JPanel implements GUI{
	
	public GUIAltaProducto() {
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Alta Producto"), BorderLayout.PAGE_START);
		JPanel panel=new JPanel();
		JLabel lNombre=new JLabel("Nombre:");
		final JTextField tNombre= new JTextField(20);
		
		JLabel lTalla=new JLabel("Talla:");
		final JTextField tTalla= new JTextField(20);
		
		JLabel lCat=new JLabel("Categoria:");
		final JTextField tCat= new JTextField(20);
		
		JLabel lCant=new JLabel("Cantidad:");
		final JTextField tCant= new JTextField(20);
		
		JLabel lIDMarca=new JLabel("IDMarca:");
		final JTextField tIDMarca= new JTextField(20);
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(lNombre);
		panel.add(tNombre);
		panel.add(lTalla);
		panel.add(tTalla);
		panel.add(lCat);
		panel.add(tCat);
		panel.add(lCant);
		panel.add(tCant);
		panel.add(lIDMarca);
		panel.add(tIDMarca);
		panel.add(aceptar);
		panel.add(cancelar);
		this.add(panel, BorderLayout.CENTER);
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					String nombre= tNombre.getText();
					String talla = tTalla.getText();
					String cat = tCat.getText();
					String cant = tCant.getText();
					String IDMarca = tIDMarca.getText();
					//nombre: id: cantidad: talla: categoria: idMarca:
					TProducto tP= new TProducto (nombre,-1, Integer.parseInt(cant), Integer.parseInt(talla), cat, Integer.parseInt(IDMarca));;
					Controlador.getInstancia().setGUI(GUIAltaProducto.this);
					Controlador.getInstancia().accion(Evento.ALTA_PRODUCTO, tP);
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
		// TODO Auto-generated method stub
		
	}
}
