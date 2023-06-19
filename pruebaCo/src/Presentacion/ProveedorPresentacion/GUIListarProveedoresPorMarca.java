package Presentacion.ProveedorPresentacion;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import Negocio.MarcaNegocio.TMarca;
import Negocio.Proveedor.TProveedor;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIListarProveedoresPorMarca extends JFrame implements GUI  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	String[] header = { "Id", "Nombre"};
	private DefaultTableModel _dataTableModel;
	
	public GUIListarProveedoresPorMarca() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Listar Marcas de Proveedor");
		setTitle("Listar Proveedores");
		this.setMinimumSize(new Dimension(500, 500));
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		
		JLabel idLabel = new JLabel("ID Marca:");
		JTextField jtId= new JTextField();
		//añado un boton de cerrar
		JButton cerrar =new JButton("Cerrar");
		cerrar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
				}
		});
		
		//Añado boton aceptar
		JButton aceptar =new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					
					Controlador.getInstancia().accion(Evento.LISTAR_PROVEEDORES_POR_MARCA,Integer.parseInt(jtId.getText()));
				}
		});
		panel.add(idLabel);
		panel.add(jtId);
		panel.add(aceptar);
		panel.add(cerrar);
		this.add(panel);
	}
	
	private void listaProveedores(ArrayList<TProveedor> datos){
		setTitle("Listar Proveedores Por Marca");
		this.setMinimumSize(new Dimension(500, 500));
		panel=new JPanel();
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
		_dataTableModel.setNumRows(datos.size());
		int i=0;
		for (TProveedor m:datos) {
			_dataTableModel.setValueAt(m.getId(), i, 0);
			_dataTableModel.setValueAt(m.getNombre(), i, 1);
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
		setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
		if(evento==Evento.OK) {
			ArrayList<TProveedor> list = (ArrayList<TProveedor>) datos;
			this.listaProveedores(list);
		}else {
			setVisible(false);
			Utils.showErrorMsg("No se ha podido listar");
		}
	}

	@Override
	public void setGUIVisible(boolean b) {
		setVisible(b);
	}

}
