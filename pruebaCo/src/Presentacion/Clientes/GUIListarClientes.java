package Presentacion.Clientes;

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

import Negocio.Clientes.TCliente;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.GUIListarMarcas;


public class GUIListarClientes extends JFrame implements GUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] header = { "ID","NOMBRE", "APELLIDO1", "APELLIDO2","DNI", "TELEFONO","PREMIUM","ACTIVO","CODIGO POSTAL","DESCUENTO", "POBLACION"};
	private DefaultTableModel _dataTableModel;
	
	public GUIListarClientes(){
	
		listar(new ArrayList<TCliente>());
	}
	@Override
	public void update(int evento, Object datos) {
		if( Evento.LISTAR_CLIENTES==evento)
		{
			Collection<TCliente>clientes=(Collection<TCliente>) datos;
			
			if(clientes==null)
				clientes=new ArrayList<TCliente>();
			
			_dataTableModel.setColumnIdentifiers(header);
			_dataTableModel.setNumRows(clientes.size());
			int i=0;
			for(TCliente c:clientes){
				_dataTableModel.setValueAt(c.getID(), i, 0);
				_dataTableModel.setValueAt(c.getNombre(), i, 1);
				_dataTableModel.setValueAt(c.getApellido1(), i, 2);
				_dataTableModel.setValueAt(c.getApellido2(), i, 3);
				_dataTableModel.setValueAt(c.getDNI(), i, 4);
				_dataTableModel.setValueAt(c.getTelefono(), i, 5);
				_dataTableModel.setValueAt(c.getPremium(), i, 6);
				_dataTableModel.setValueAt(c.getActivo(), i, 7);

				
				if(c.getPremium()){
					_dataTableModel.setValueAt(c.get_codigo(), i, 8);
					_dataTableModel.setValueAt(c.get_descuento()+"%", i, 9);
				}
				else{
					_dataTableModel.setValueAt(c.get_poblacion(), i, 10);
				}
				
				i++;
				
			}
			this.setVisible(true);
		}
	}
	private void listar(Collection<TCliente> clientes) {
		setTitle("Listar clientes");
		this.setMinimumSize(new Dimension(600, 50));
		this.setPreferredSize(new Dimension(600,200));
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		_dataTableModel.setColumnIdentifiers(header);
		_dataTableModel.setNumRows(clientes.size());
		int i=0;
		for(TCliente c:clientes){
			_dataTableModel.setValueAt(c.getID(), i, 0);
			_dataTableModel.setValueAt(c.getNombre(), i, 1);
			_dataTableModel.setValueAt(c.getApellido1(), i, 2);
			_dataTableModel.setValueAt(c.getApellido2(), i, 3);
			_dataTableModel.setValueAt(c.getDNI(), i, 4);
			_dataTableModel.setValueAt(c.getTelefono(), i, 5);
			_dataTableModel.setValueAt(c.getPremium(), i, 6);
			_dataTableModel.setValueAt(c.getActivo(), i, 7);
			
			
			if(c.getPremium()){
				_dataTableModel.setValueAt(c.get_codigo(), i, 8);
				_dataTableModel.setValueAt(c.get_descuento()+"%", i, 9);
			}
			else{
				_dataTableModel.setValueAt(c.get_poblacion(), i, 10);
			}
			
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
		setVisible(false);
	}

	
	@Override
	public void setGUIVisible(boolean b) {
		
		Controlador.getInstancia().accion(Evento.LISTAR_CLIENTES,null);
	
	}
}