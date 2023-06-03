package Presentacion.ProveedorPresentacion;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Main.Utils;
import Negocio.Proveedor.TProveedor;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.GUIMarcaPorID;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIProvPorID extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] header = { "Id", "Nombre", "Marcas"};
	private DefaultTableModel _dataTableModel;	
	
	private JPanel jpanel;
	
	public GUIProvPorID(){
		initGUI();		
	}

	private void initGUI() {
		this.setTitle("Proveedor por ID");
		jpanel=new JPanel();
		JLabel jlabel=new JLabel("Identificador: ");
		this.setLocationRelativeTo(null);
		
		final JTextField jtextID= new JTextField(5);
		JButton JAcceptButton=new JButton("Aceptar");
		JButton JCancelButton=new JButton("Cancelar");
		
		jpanel.add(jlabel);
		jpanel.add(jtextID);
		jpanel.add(JAcceptButton);
		jpanel.add(JCancelButton);
		
		getContentPane().add(jpanel);
		pack();
		
		
		JAcceptButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					setVisible(false);
					int id=Integer.parseInt(jtextID.getText());
					Controlador.getInstancia().setGUI(GUIProvPorID.this);
					Controlador.getInstancia().accion(Evento.PROVEEDOR_POR_ID, new Integer(id));
				}catch(NumberFormatException e1){
					setVisible(false);
				}
				
			}
			
		});
		JCancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		this.setVisible(false);
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.KO:{
			Utils.showErrorMsg("No se pudo encontrar el proveedor con dicho ID");
			setVisible(false);
			break;
		}case Evento.OK:{
			
			listarProveedor((TProveedor) datos);
			break;
		}
		}
	}

	private void listarProveedor(TProveedor datos) {
		//Elimino todos los componentes
		Container contentPane = getContentPane();

		Component[] components = contentPane.getComponents();
		for (Component component : components) {
		    contentPane.remove(component);
		}
		
		setTitle("Empleado por id");
		this.setPreferredSize(new Dimension(500, 200));
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		
		//añado un boton de cerrar
		JButton cerrar =new JButton("Cerrar");
		cerrar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				setVisible(false);
				Component[] components = contentPane.getComponents();
				for (Component component : components) {
				    contentPane.remove(component);
				}
				initGUI();
				}
			});
		
		//añado la lista
		this._dataTableModel = new DefaultTableModel();
		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(1);
		_dataTableModel.setValueAt(datos.getId(), 0, 0);
		_dataTableModel.setValueAt(datos.getNombre(), 0, 1);
	
		
		JTable dataTable = new JTable(_dataTableModel) {
			private static final long serialVersionUID = 1L;

			// we override prepareRenderer to resize columns to fit to content
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		JScrollPane tabelScroll = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(tabelScroll);

		panel.add(cerrar);
		revalidate();
		repaint();
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		Utils.refreshTextFields(jpanel);
		this.setVisible(b);
	}
}
