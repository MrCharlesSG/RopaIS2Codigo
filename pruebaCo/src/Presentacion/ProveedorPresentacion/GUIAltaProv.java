package Presentacion.ProveedorPresentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private ArrayList<Integer> marcas;	
	private DefaultListModel<String> modelo;
	
	public GUIAltaProv() {
		marcas = new ArrayList<Integer>();
		modelo = new DefaultListModel<>();
		initGUI();	
		setVisible(true);
	}
	
	private void initGUI(){
		setTitle("Alta Proveedor");
		JPanel panel=new JPanel();
		JLabel lNombre=new JLabel("Nombre:");
		final JTextField tNombre= new JTextField(20);
		/*
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
		*/
		
		//lista de marcas
		this.updateLista();
		JList<String> jListaMarcas = new JList<String>(this.modelo);
		
		JButton masMarcas = new JButton("+ Marcas");
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		masMarcas.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				JFrame ventana = new JFrame("Añadir Marca a Proveedor");
				ventana.setMinimumSize(new Dimension(200, 200));
				JPanel panelV= new JPanel();
				JPanel panelDatos= new JPanel();
				JPanel panelBotones= new JPanel();
				//datos
				JLabel idL= new JLabel("ID: ");
				JTextField idTF = new JTextField(10);
				panelDatos.add(idL);
				panelDatos.add(idTF);
				//botones
				JButton aceptarB= new JButton("Aceptar");
				JButton cancelarB= new JButton("Cancelar");
				aceptarB.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{		
						anadeALista(idTF.getText());
						updateLista();
						ventana.setVisible(false);
					}
				});
				
				cancelarB.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{		
						ventana.setVisible(false);
					}
				});
				panelBotones.add(aceptarB);
				panelBotones.add(cancelarB);
				panelV.add(panelDatos, BorderLayout.NORTH);
				panelV.add(panelBotones, BorderLayout.SOUTH);
				
				ventana.add(panelV,BorderLayout.CENTER );
				panelV.setVisible(true);
				ventana.setVisible(true);
			}

		
	});
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					try{
						setVisible(false);
						
						Controlador.getInstancia().accion(Evento.ALTA_PROVEEDOR, new TProveedor(tNombre.getText(),2, marcas, true ));
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
		JPanel primerosDatos = new JPanel();
		primerosDatos.setLayout(new FlowLayout());
		primerosDatos.add(lNombre);
		primerosDatos.add(tNombre);
		
		/*
		panel.add(lM1);panel.add(tM1);
		panel.add(lM2);panel.add(tM2);
		
		panel.add(lM3);panel.add(tM3);
		
		panel.add(lM4);panel.add(tM4);
		
		panel.add(lM5);panel.add(tM5);
		*/
		JPanel listaMarcasPanle = new JPanel();
		listaMarcasPanle.setLayout(new FlowLayout());
		JScrollPane scrollPane = new JScrollPane(jListaMarcas);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Lista De Marcas: "));
		listaMarcasPanle.add(scrollPane);
		JPanel botonesPanel = new JPanel();
		botonesPanel.setLayout(new FlowLayout());
		botonesPanel.add(masMarcas);
		botonesPanel.add(aceptar);
		botonesPanel.add(cancelar);
		
		//añado los paneles
		panel.add(primerosDatos, BorderLayout.NORTH);
		panel.add(listaMarcasPanle, BorderLayout.CENTER);
		panel.add(botonesPanel, BorderLayout.SOUTH);
		this.add(panel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(300, 400));
		setResizable(false);
		pack();
		
	}
	
	private boolean anadeALista(String id) {
		try {
			this.marcas.add(Integer.parseInt(id));
			return true;
		}catch(NumberFormatException e1) {
			Utils.showErrorMsg("Tienen que ser numeros");
			return false;
		}
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
	
	private void updateLista() {
		modelo.clear();
		for (Integer dato : this.marcas) {
			modelo.addElement(dato.toString());
		}
		
	}
}
