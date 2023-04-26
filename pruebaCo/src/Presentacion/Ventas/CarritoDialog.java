package Presentacion.Ventas;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.MarcaPresentacion.GUIAltaMarca;

public class CarritoDialog extends Dialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer,Integer> carro;
	private boolean status;
	public CarritoDialog (Frame parent,Map<Integer,Integer> c){
		super(parent);
		carro=c;
	this.initGUI() ;
	}
	private void initGUI() {
		setTitle("Seleccion de productos");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JLabel descripcion=new JLabel("<html><p>Elige un producto por su id y selecciona una cantidad</p></html>");
		descripcion.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(descripcion);
		
		JPanel panel=new JPanel();
		JLabel lprod=new JLabel("ID Producto:");
		final JTextField tprod= new JTextField(20);
		JLabel lcanti=new JLabel("Cantidad:");
		final JTextField tcant= new JTextField(20);
		JButton aniadir=new JButton("Añadir");
		JButton elim=new JButton("Eliminar");
		JButton aceptar=new JButton("Aceptar");
		panel.add(lprod);
		panel.add(tprod);
		panel.add(lcanti);
		panel.add(tcant);
		
		mainPanel.add(panel);
		JPanel botones=new JPanel();
		botones.add(aniadir);
		botones.add(elim);
		botones.add(aceptar);
		
		mainPanel.add(botones);
		aniadir.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{	
			carro.put(Integer.parseInt(tprod.getText()), Integer.parseInt(tcant.getText()));
			tprod.setText(null);
			tcant.setText(null);
				
			}
	});
		elim.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				if(carro.containsKey(Integer.parseInt(tprod.getText()))&&carro.get(Integer.parseInt(tprod.getText()))>=Integer.parseInt(tcant.getText())){
					carro.replace((Integer.parseInt(tprod.getText())), carro.get(Integer.parseInt(tprod.getText()))-Integer.parseInt(tcant.getText()));
					if(carro.get(Integer.parseInt(tprod.getText()))==0){
						carro.remove(Integer.parseInt(tprod.getText()));
					}
				}
				tprod.setText(null);
				tcant.setText(null);
			}
	});
		aceptar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
				status=true;
				tprod.setText(null);
				tcant.setText(null);
			}
	});
		
		setPreferredSize(new Dimension(400, 400));
		pack();
	}
	public boolean  open() {
	
		if (getParent() != null)
			setLocation(//
					getParent().getLocation().x + getParent().getWidth() / 2 - getWidth() / 2, //
					getParent().getLocation().y + getParent().getHeight() / 2 - getHeight() / 2);
		pack();
		setVisible(true);
		return status;
	}
}