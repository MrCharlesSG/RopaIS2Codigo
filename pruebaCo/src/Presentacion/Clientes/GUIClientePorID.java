package Presentacion.Clientes;

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
import Negocio.Clientes.TCliente;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIClientePorID extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	
	String[] header = { "ID","NOMBRE", "APELLIDO1", "APELLIDO2","DNI", "TELEFONO","PREMIUM","ACTIVO", "CODIGO POSTAL","DESCUENTO", "POBLACION"};
	private DefaultTableModel _dataTableModel;
	
	public GUIClientePorID() {
		initGUI();
	}
	
	private void initGUI(){
		setTitle("Cliente por ID");
		panel=new JPanel();
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
					setVisible(false);
					try{
						setVisible(false);
						int id=Integer.parseInt(tID.getText());
					
						Controlador.getInstancia().accion(Evento.CLIENTE_POR_ID, new Integer(id));
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
		setVisible(false);
	}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.RES_CLIENTE_POR_ID_OK==evento){
	TCliente c=(TCliente) datos;
	this.mostrar(c);
	
	}
	if( Evento.RES_CLIENTE_POR_ID_KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se pudo encontrar el cliente con ese ID");

	}
	}
	private void mostrar(TCliente c) {
		Container contentPane = getContentPane();
		
		Component[] components = contentPane.getComponents();
		for (Component component : components) {
			contentPane.remove(component);
		}
		
		
		setTitle("Mostrar cliente");
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
				Component[] components = contentPane.getComponents();
				for (Component component : components) {
				    contentPane.remove(component);
				}
				initGUI();
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
		_dataTableModel.setColumnIdentifiers(header);;
		_dataTableModel.setNumRows(1);
		int i=0;
		
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
		this.setVisible(b);
	}
}
