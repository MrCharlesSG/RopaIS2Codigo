package Presentacion.ProveedorPresentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Proveedor.TProveedor;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIAltaProv extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIAltaProv() {
		initGUI();	
		setVisible(true);
	}
	
	private void initGUI(){
		setTitle("Alta Proveedor");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(500, 500));
		this.setMaximumSize(new Dimension(500, 500));
		this.add(new JLabel("Alta Proveedor"), BorderLayout.PAGE_START);
		JPanel panel=new JPanel();
		JLabel lNombre=new JLabel("Nombre:");
		final JTextField tNombre= new JTextField(20);
		
		//Maximo va a haber 5 marcas por porveedor
		JLabel lM1=new JLabel("Marca 1:");
		final JTextField tM1= new JTextField(20);
		JLabel lM2=new JLabel("Marca 2:");
		final JTextField tM2= new JTextField(20);
		JLabel lM3=new JLabel("Marca 3:");
		final JTextField tM3= new JTextField(20);
		JLabel lM4=new JLabel("Marca 4:");
		final JTextField tM4= new JTextField(20);
		JLabel lM5=new JLabel("Marca 5:");
		final JTextField tM5= new JTextField(20);
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					try{
						setVisible(false);
						String nombre= tNombre.getText();
						
						String[] aux= new String[5];
						aux[0]=tM1.getText();
						aux[1]=tM2.getText();
						aux[2]=tM3.getText();
						aux[3]=tM4.getText();
						aux[4]=tM4.getText();
						
						ArrayList<Integer> lista=anadeALista(aux);
						
						Controlador.getInstancia().accion(Evento.ALTA_PROVEEDOR, new TProveedor(nombre,2, lista, true ));
					}catch(NumberFormatException e1){
						Utils.showErrorMsg("Tienen que ser numeros");
					}
				}

			
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
		});
		
		panel.add(lNombre);
		panel.add(tNombre);
		
		
		panel.add(lM1);panel.add(tM1);
		panel.add(lM2);panel.add(tM2);
		
		panel.add(lM3);panel.add(tM3);
		
		panel.add(lM4);panel.add(tM4);
		
		panel.add(lM5);panel.add(tM5);
		
		
		panel.add(aceptar);
		panel.add(cancelar);
		this.add(panel, BorderLayout.CENTER);
		pack();
		
	}
	
	private ArrayList<Integer> anadeALista(String[] aux) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(String s:aux){
			if(s!=null && !s.equals("")){
				lista.add(Integer.parseInt(s));
			}
		}
		return lista;
		
	}

	@Override
	public void update(int evento, Object datos) {
		
		switch(evento){
		case Evento.OK:{
			Utils.showCorrectMsg("El Proveedor ha sido creado correctamente con el siguiente id: "+datos.toString());
			this.setVisible(false);
			break;
		}case Evento.KO:{
			Utils.showErrorMsg("Ha habido un error al crear el proveedor");
			this.setVisible(false);
			break;
		}
		}
	}
}
