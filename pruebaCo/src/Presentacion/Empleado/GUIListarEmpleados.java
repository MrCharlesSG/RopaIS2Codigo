package Presentacion.Empleado;

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

import Main.Utils;
import Negocio.Empleado.TEmpleado;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIListarEmpleados extends JFrame implements GUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] header = { "Id", "Nombre", "Apellido 1", "Apellido 2", "DNI", "Teléfono", "Tiempo"};
	private DefaultTableModel _dataTableModel;

	public GUIListarEmpleados(){
		initGUI();
	}
	
	private void initGUI() {
		Controlador.getInstancia().setGUI(GUIListarEmpleados.this);
		this.listaEmpleados(new ArrayList<TEmpleado>());
	}

	private void listaEmpleados(ArrayList<TEmpleado> datos){
		setTitle("Listar Empleados");
		this.setMinimumSize(new Dimension(520, 400));
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
		_dataTableModel.setNumRows(datos.size());
		int i=0;
		for (TEmpleado m:datos) {
			_dataTableModel.setValueAt(m.getID(), i, 0);
			_dataTableModel.setValueAt(m.getNombre(), i, 1);
			_dataTableModel.setValueAt(m.getApellido1(), i, 2);
			_dataTableModel.setValueAt(m.getApellido2(), i, 3);
			_dataTableModel.setValueAt(m.getDNI(), i, 4);
			_dataTableModel.setValueAt(m.getTfno(), i, 5);
			_dataTableModel.setValueAt(m.isTiempoCompleto()? "Completo":"Parcial", i, 6);
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
	public void update(int evento, Object datos) {
		switch(evento){
		case Evento.KO:{
			setVisible(false);
			Utils.showErrorMsg("No se ha podido listar los Empleados");
		}
		case Evento.OK:{
			
			Collection<TEmpleado>emp=(Collection<TEmpleado>) datos;
			if(emp==null)
				emp=new ArrayList<TEmpleado>();
			
			_dataTableModel.setColumnIdentifiers(header);;
			_dataTableModel.setNumRows(emp.size());
			int i=0;
			for (TEmpleado m:emp) {
				_dataTableModel.setValueAt(m.getID(), i, 0);
				_dataTableModel.setValueAt(m.getNombre(), i, 1);
				_dataTableModel.setValueAt(m.getApellido1(), i, 2);
				_dataTableModel.setValueAt(m.getApellido2(), i, 3);
				_dataTableModel.setValueAt(m.getDNI(), i, 4);
				_dataTableModel.setValueAt(m.getTfno(), i, 5);
				_dataTableModel.setValueAt(m.isTiempoCompleto()? "Completo":"Parcial", i, 6);
				i++;
		
			}
		}
		}
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		Controlador.getInstancia().setGUI(GUIListarEmpleados.this);
		Controlador.getInstancia().accion(Evento.LISTAR_EMPLEADO, null);
		this.setVisible(b);
	}
	
}
