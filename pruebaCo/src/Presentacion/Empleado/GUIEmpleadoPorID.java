package Presentacion.Empleado;

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
import Negocio.Empleado.TEmpleado;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIEmpleadoPorID extends JFrame implements GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] header = { "Id", "Nombre", "Apellido 1", "Apellido 2", "DNI", "Teléfono", "Tiempo"};
	private DefaultTableModel _dataTableModel;
	public GUIEmpleadoPorID() {
		setTitle("Empleado por ID");
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador:");
		final JTextField tID= new JTextField(5);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					try{
						int id=Integer.parseInt(tID.getText());
						Controlador.getInstancia().setGUI(GUIEmpleadoPorID.this);
						Controlador.getInstancia().accion(Evento.EMPLEADO_POR_ID, id);	
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
			setVisible(false);
			Utils.showErrorMsg("No se ha podido listar los Empleados");
		}
		case Evento.OK:{
			this.listaEmpleado((TEmpleado)datos);
		}
	}
}

	private void listaEmpleado(TEmpleado datos) {
		//Elimino todos los componentes
		Container contentPane = getContentPane();

		Component[] components = contentPane.getComponents();
		for (Component component : components) {
			contentPane.remove(component);
		}
		
		setTitle("Proveedor por id");
		this.setPreferredSize(new Dimension(520, 200));
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
		
		datos.getApellido1();
		
		//añado la lista
		this._dataTableModel = new DefaultTableModel();
		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(1);
		_dataTableModel.setValueAt(datos.getID(), 0, 0);
		_dataTableModel.setValueAt(datos.getNombre(), 0, 1);
		_dataTableModel.setValueAt(datos.getApellido1(), 0, 2);
		_dataTableModel.setValueAt(datos.getApellido2(), 0, 3);
		_dataTableModel.setValueAt(datos.getDNI(), 0, 4);
		_dataTableModel.setValueAt(datos.getTfno(), 0, 5);
		_dataTableModel.setValueAt(datos.isTiempoCompleto()? "Completo":"Parcial", 0, 6);
	
		
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
