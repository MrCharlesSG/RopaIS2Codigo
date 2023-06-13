package Presentacion.Ventas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Negocio.MarcaNegocio.TMarca;
import Negocio.Ventas.TVenta;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIListarVentas extends JFrame implements GUI{
	String[] header = { "ID","ID EMPLEADO", "ID CLIENTE", "PRECIO", "ACTIVO"};
	private DefaultTableModel _dataTableModel;
	
	public GUIListarVentas() {
		this.setLocationRelativeTo(null);
		
		
	}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.LISTAR_VENTAS==evento)
	{
		Collection<TVenta>ventas=(Collection<TVenta>) datos;
		this.listar(ventas);
	}
		
	}

	private void listar(Collection<TVenta> ventas) {
		setTitle("Listar ventas");
		this.setMinimumSize(new Dimension(600, 600));
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
		this._dataTableModel = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(ventas.size());
		int i=0;
		for (TVenta v:ventas) {
			_dataTableModel.setValueAt(v.get_id(), i, 0);
			_dataTableModel.setValueAt(v.get_id_empleado(), i, 1);
			_dataTableModel.setValueAt(v.get_id_cliente(), i, 2);
			_dataTableModel.setValueAt(v.get_precio(), i, 3);
			_dataTableModel.setValueAt(v.get_activo(), i, 4);
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
		this.setContentPane(panel);
		pack();
		setVisible(true);
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		
		Controlador.getInstancia().accion(Evento.LISTAR_VENTAS,null);
	}
}
