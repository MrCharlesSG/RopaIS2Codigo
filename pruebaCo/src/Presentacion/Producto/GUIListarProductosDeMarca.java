package Presentacion.Producto;

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
import Negocio.Producto.TProducto;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIListarProductosDeMarca extends JFrame implements GUI {
	private static final long serialVersionUID = 1L;
	String[] header = { "Nombre", "Id", "Categoria", "Talla", "Cantidad", "Precio", " Id Marca"};
	private DefaultTableModel _dataTableModel;
	
	public GUIListarProductosDeMarca() {
		//Controlador.getInstancia().setGUI(GUIListarMarcas.this);
		this.listar(new ArrayList<TProducto>());
		
	}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.LISTAR_PRODUCTOS_POR_MARCA==evento)
	{
		Collection<TProducto>productos=(Collection<TProducto>) datos;
		if(productos==null)
			productos=new ArrayList<TProducto>();

		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(productos.size());
		int i=0;
		for (TProducto p: productos) {
			_dataTableModel.setValueAt(p.getNombre(), i, 0);
			_dataTableModel.setValueAt(p.getIdProducto(), i, 1);
			_dataTableModel.setValueAt(p.getCategoria(), i, 2);
			_dataTableModel.setValueAt(p.getTalla(), i, 3);
			_dataTableModel.setValueAt(p.getCantidad(), i, 4);
			_dataTableModel.setValueAt(p.getPrecio(), i, 5);
			_dataTableModel.setValueAt(p.getIdMarca(), i, 6);
			i++;
		}
	}
		
	}

	private void listar(Collection<TProducto> productos) {
		setTitle("Listar productos de marca");
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
		//_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(productos.size());
		int i=0;
		for (TProducto p: productos) {
			_dataTableModel.setValueAt(p.getNombre(), i, 0);
			_dataTableModel.setValueAt(p.getIdProducto(), i, 1);
			_dataTableModel.setValueAt(p.getCategoria(), i, 2);
			_dataTableModel.setValueAt(p.getTalla(), i, 3);
			_dataTableModel.setValueAt(p.getCantidad(), i, 4);
			_dataTableModel.setValueAt(p.getPrecio(), i, 5);
			_dataTableModel.setValueAt(p.getIdMarca(), i, 6);
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
	//	Controlador.getInstancia().setGUI(GUIListarMarcas.this);
		Controlador.getInstancia().accion(Evento.LISTAR_MARCAS,null);
		this.setVisible(b);
	}
}
