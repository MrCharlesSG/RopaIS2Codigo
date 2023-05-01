/**
 * 
 */
package Presentacion.MarcaPresentacion;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Main.Utils;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIMarcaPorID extends JFrame implements GUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	
	String[] header = { "Id", "Nombre", "Cantidad", "Activo"};
	private DefaultTableModel _dataTableModel;
	
	public GUIMarcaPorID() {
		setTitle("Marca por ID");
		JPanel panel=new JPanel();
		
		JLabel lID=new JLabel("Identificador:");
		final JTextField tID= new JTextField(5);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
		
		texto.add(lID);
		texto.add(tID);
	
		botones.add(aceptar);
		botones.add(cancelar);
		panel.add(texto);
		panel.add(botones);
		
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				try{
					setVisible(false);
					int id=Integer.parseInt(tID.getText());
					Controlador.getInstancia().setGUI(GUIMarcaPorID.this);
					Controlador.getInstancia().accion(Evento.MARCA_PORID, new Integer(id));
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
		setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.KO:{
			Utils.showErrorMsg("No se pudo encontrar el producto con dicho ID");
			setVisible(false);
			break;
		}case Evento.OK:{
			listarMarca((TMarca)datos);
			break;
		}
		}
	}

	private void listarMarca(TMarca datos) {
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
				}
			});
		
		this._dataTableModel = new DefaultTableModel();
		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(1);
			_dataTableModel.setValueAt(datos.getID(), 0, 0);
			_dataTableModel.setValueAt(datos.getNombre(), 0, 1);
			_dataTableModel.setValueAt(datos.getCantidad(), 0, 2);
			_dataTableModel.setValueAt(datos.getActivo(), 0, 3);

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
}