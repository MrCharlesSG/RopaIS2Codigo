package Presentacion.ProveedorPresentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIModificarProv extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> marcas;	
	private DefaultListModel<String> modelo;
	
	public GUIModificarProv(){
		marcas = new ArrayList<Integer>();
		modelo = new DefaultListModel<>();
		initGUI();
	}
	
	public void initGUI(){
		setTitle("Alta Proveedor");
		JPanel panel=new JPanel();
		JLabel lID = new JLabel("ID: ");
		JTextField tID= new JTextField(3);
		JLabel lNombre=new JLabel("Nombre (Nuevo): ");
		JTextField tNombre= new JTextField(20);
		this.setLocationRelativeTo(null);
		
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
				JLabel idL= new JLabel("ID Marca: ");
				JTextField idTF = new JTextField(10);
				panelDatos.add(idL);
				panelDatos.add(idTF);
				//botones
				JButton aceptarB= new JButton("Aceptar");
				JButton cancelarB= new JButton("Cancelar");
				
				aceptarB.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{
					ventana.setVisible(false);
					try{
						anadeALista(idTF.getText());
						updateLista();
					}catch(Exception e1){
						Utils.showErrorMsg("Los parametros introducidos son incorrectos");
					}
						
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
				ventana.setVisible(true);
			}

		
	});
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					try{
						setVisible(false);
						
						Controlador.getInstancia().accion(Evento.MODIFICAR_PROVEEDOR, new TProveedor(tNombre.getText(),Integer.parseInt(tID.getText()), marcas, true ));
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
		JPanel IDPanel= new JPanel();
		IDPanel.add(lID);
		IDPanel.add(tID);
		
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(lNombre);
		nombrePanel.add(tNombre);
		
		
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
		panel.add(IDPanel, BorderLayout.NORTH);
		panel.add(nombrePanel);
		panel.add(listaMarcasPanle, BorderLayout.CENTER);
		panel.add(botonesPanel, BorderLayout.SOUTH);
		this.add(panel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(400, 400));
		setResizable(false);
		this.setVisible(true);
		pack();
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.OK:{
			Utils.showCorrectMsg("El proveedor ha sido modificado con exito:"+ System.lineSeparator()+datos.toString());
			this.setVisible(false);
			break;
		}
		case Evento.KO:{
			Utils.showErrorMsg("El proveedor no se ha podido modificar");
			this.setVisible(false);
			break;
		}
		}
	}
	
	private boolean anadeALista(String id) {
		try {
			ArrayList<Integer> aux= new ArrayList<Integer>();
			int iD=Integer.parseInt(id);
			aux.add(iD);
			
			if(Controlador.getInstancia().marcasExisten(aux)) {
				this.marcas.add(iD);
				return true;
			}
			
		}catch(NumberFormatException e1) {}
		return false;
	}
	
	private void updateLista() {
		modelo.clear();
		for (Integer dato : this.marcas) {
			modelo.addElement(dato.toString());
		}
		
	}

}
