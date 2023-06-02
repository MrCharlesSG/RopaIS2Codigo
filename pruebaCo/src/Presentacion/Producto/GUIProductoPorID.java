package Presentacion.Producto;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sergvaz99
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/

public class GUIProductoPorID extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] header = { "Nombre", "Id", "Categoria", "Talla", "Cantidad", " Id Marca"};
	private DefaultTableModel _dataTableModel;
	
	private JPanel jpanel;
	
	public GUIProductoPorID(){
		initGUI();
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.KO:{
			Utils.showErrorMsg("No se pudo encontrar el producto con dicho ID");
			setVisible(false);
			break;
		}case Evento.OK:{
			listarProducto((TProducto)datos);
			break;
		}
		}
	}
	
	private void initGUI(){
		this.setTitle("Producto por ID");
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
				setVisible(false);
				try{
					String id=jtextID.getText();
					Controlador.getInstancia().setGUI(GUIProductoPorID.this);
					Controlador.getInstancia().accion(Evento.PRODUCTO_POR_ID, Integer.parseInt(id));
				}catch(Exception e1){
					Utils.showErrorMsg("Los parametros introducidos son incorrectos");
				}
				
				
			}
			
		});
		JCancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
	}

	private void listarProducto(TProducto datos) {
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
	
		this._dataTableModel = new DefaultTableModel();
		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(1);
		_dataTableModel.setValueAt(datos.getNombre(), 0, 0);
		_dataTableModel.setValueAt(datos.getIdProducto(), 0, 1);
		_dataTableModel.setValueAt(datos.getCategoria(), 0, 2);
		_dataTableModel.setValueAt(datos.getTalla(), 0, 3);
		_dataTableModel.setValueAt(datos.getCantidad(), 0, 4);
		_dataTableModel.setValueAt(datos.getIdMarca(), 0, 5);
	
		
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
