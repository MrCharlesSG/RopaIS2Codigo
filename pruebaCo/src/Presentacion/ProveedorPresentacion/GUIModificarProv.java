package Presentacion.ProveedorPresentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
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

	public GUIModificarProv(){
		initGUI();
	}
	
	public void initGUI(){
		this.setTitle("Modificar Proveedor");
		JPanel panel= new JPanel();
		this.setMinimumSize(new Dimension(500, 500));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//Solo se permite modificar el nombre del proveedor
		JLabel lID=new JLabel("Identificador: ");
		JTextField jtextID= new JTextField(5);
		JLabel lNombre = new JLabel("Nombre (nuevo): ");
		JTextField jtextNombre= new JTextField(20);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		JLabel lM1=new JLabel("Marca 1 (nueva):");
		final JTextField tM1= new JTextField(5);
		JLabel lM2=new JLabel("Marca 2 (nueva):");
		final JTextField tM2= new JTextField(5);
		JLabel lM3=new JLabel("Marca 3 (nueva):");
		final JTextField tM3= new JTextField(5);
		JLabel lM4=new JLabel("Marca 4 (nueva):");
		final JTextField tM4= new JTextField(5);
		JLabel lM5=new JLabel("Marca 5 (nueva):");
		final JTextField tM5= new JTextField(5);
		
		aceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				setVisible(false);
				String[] datos = new String[2];
				datos[0]=jtextID.getText();
				datos[1]= jtextNombre.getText();
				
				String[] aux= new String[5];
				aux[0]=tM1.getText();
				aux[1]=tM2.getText();
				aux[2]=tM3.getText();
				aux[3]=tM4.getText();
				aux[4]=tM4.getText();
				
				ArrayList<Integer> lista=anadeALista(aux);
				
				Controlador.getInstancia().accion(Evento.MODIFICAR_PROVEEDOR,  new TProveedor(datos[1],Integer.parseInt(datos[0]), lista, true ));
			
				}catch(NumberFormatException e1){
					Utils.showErrorMsg("Tienen que ser numeros");
				}
			}
		});
		
		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		
		panel.add(lID);
		panel.add(jtextID);
		panel.add(lNombre);
		panel.add(jtextNombre);	
		panel.add(Box.createVerticalStrut(10));
		panel.add(lM1);panel.add(tM1);
		panel.add(Box.createVerticalStrut(10));
		panel.add(lM2);panel.add(tM2);		
		panel.add(Box.createVerticalStrut(10));
		panel.add(lM3);panel.add(tM3);		
		panel.add(Box.createVerticalStrut(10));
		panel.add(lM4);panel.add(tM4);		
		panel.add(Box.createVerticalStrut(10));
		panel.add(lM5);panel.add(tM5);
		
		JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(aceptar);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Separación entre los botones
        panelBotones.add(cancelar);
        panelBotones.add(Box.createHorizontalGlue());
        panel.add(panelBotones);
		this.getContentPane().add(panel);
		this.setVisible(true);
		this.pack();
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
	
	private ArrayList<Integer> anadeALista(String[] aux) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(String s:aux){
			if(s!=null && !s.equals("")){
				lista.add(Integer.parseInt(s));
			}
		}
		return lista;
		
	}

}
