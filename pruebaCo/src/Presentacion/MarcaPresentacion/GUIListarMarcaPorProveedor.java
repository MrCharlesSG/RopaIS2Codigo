package Presentacion.MarcaPresentacion;

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
import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIListarMarcaPorProveedor extends JFrame implements GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] header = { "Id", "Nombre", "Activo"};
	private DefaultTableModel _dataTableModel;
	private JPanel panel;
	
	public GUIListarMarcaPorProveedor() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Listar Marcas por Proveedor");
		this.setMinimumSize(new Dimension(500, 500));
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		
		JLabel idLabel = new JLabel("ID Proveedor:");
		JTextField jtId= new JTextField(20);
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
					Controlador.getInstancia().accion(Evento.LISTAR_MARCA_POR_PROVEEDORES,Integer.parseInt(jtId.getText()));
				}
		});
		
		panel.add(idLabel);
		panel.add(jtId);
		panel.add(aceptar);
		panel.add(cerrar);
		this.add(panel);
		this.setVisible(false);
	}
	
	

	@Override
	public void update(int evento, Object datos) {
		if(evento==Evento.OK) {
			ArrayList<TMarca> list = (ArrayList<TMarca>) datos;
			listarMarca(list);
			
		}else {
			setVisible(false);
			Utils.showErrorMsg("No se ha podido listar las Marcas");
		}
	}

	@Override
	public void setGUIVisible(boolean b) {
		setVisible(b);
	}
	private void listarMarca(Collection<TMarca> marcas) {
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
		_dataTableModel.setNumRows(marcas.size());
		int i=0;
		for (TMarca m:marcas) {
			_dataTableModel.setValueAt(m.getID(), i, 0);
			_dataTableModel.setValueAt(m.getNombre(), i, 1);
			_dataTableModel.setValueAt(m.getActivo(), i, 2);
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
		revalidate();
		repaint();
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}
	

}
