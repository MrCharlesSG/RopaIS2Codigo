/**
 * 
 */
package Presentacion.MarcaPresentacion;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacionImp;
import Presentacion.GUI.GUI;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIListarMarcas extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	String[] header = { "Id", "Nombre", "Cantidad", "Activo"};
	private DefaultTableModel _dataTableModel;
	public GUIListarMarcas() {
		Controlador.getInstancia().setGUI(GUIListarMarcas.this);
		Controlador.getInstancia().accion(Evento.LISTAR_MARCAS,null);
	}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.LISTAR_MARCAS==evento)
	{
		Collection<TMarca>marcas=(Collection<TMarca>) datos;
		if(marcas==null)
			marcas=new ArrayList<TMarca>();
		
		this.listar(marcas);
		
	}
		
	}

	private void listar(Collection<TMarca> marcas) {
		setTitle("Listar marcas");
		this.setMinimumSize(new Dimension(500, 500));
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
		
		//añado la lista
		this._dataTableModel = new DefaultTableModel();
		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(marcas.size());
		int i=0;
		for (TMarca m:marcas) {
			_dataTableModel.setValueAt(m.getID(), i, 0);
			_dataTableModel.setValueAt(m.getNombre(), i, 1);
			_dataTableModel.setValueAt(m.getCantidad(), i, 2);
			_dataTableModel.setValueAt(m.getActivo(), i, 3);
			i++;
		}
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
		getContentPane().add(panel);
		pack();
		setVisible(false);
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		Controlador.getInstancia().setGUI(GUIListarMarcas.this);
		Controlador.getInstancia().accion(Evento.LISTAR_MARCAS,null);
		this.setVisible(b);
	}
}