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
import Negocio.ProductosDeVenta.TProductosDeVenta;
import Negocio.Ventas.TOAVenta;
import Negocio.Ventas.TVenta;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.GUIMarcaPorID;

public class GUIVentaPorID extends JFrame implements GUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	String[] header = { "ID->PRODUCTO","CANTIDAD", "PRECIO POR PRODUCTO"};
	private DefaultTableModel _dataTableModel;
	
	public GUIVentaPorID() {
		setTitle("Venta por ID");
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador:");
		final JTextField tID= new JTextField(5);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		JPanel texto=new JPanel();
		JPanel botones=new JPanel();
	
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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
					Controlador.getInstancia().accion(Evento.VENTA_POR_ID, new Integer(id));
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
	if( Evento.RES_VENTA_POR_ID_OK==evento){
		TOAVenta v=(TOAVenta) datos;
		
		mostrar(v);

	}
	if( Evento.RES_VENTA_POR_ID_KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se pudo encontrar la marca con ese ID");

	}
	}
	private void mostrar(TOAVenta toav) {
		TVenta v=toav.getVenta();
		Collection<TProductosDeVenta>productos=toav.getProductos();
		setTitle("Mostrar venta");
		this.setMinimumSize(new Dimension(600, 50));
		this.setPreferredSize(new Dimension(600,200));
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		JLabel descripcion=new JLabel("La venta con id de venta"+v.get_id()+" con id de empleado "+v.get_id_empleado()+" y con id de cliente"+v.get_id_cliente()+" tiene los siguientes productos");
		descripcion.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(descripcion);
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
		_dataTableModel.setNumRows(productos.size());
		int i=0;
		for(TProductosDeVenta tpv:productos) {
			_dataTableModel.setValueAt(tpv.getProducto(), i, 0);
			_dataTableModel.setValueAt(tpv.getCantidad(), i, 1);
			_dataTableModel.setValueAt(tpv.getPrecio(), i, 2);
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

		JLabel cierre=new JLabel("El precio total de la venta ha sido"+v.get_precio());
		cierre.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(cierre);
		panel.add(cerrar);
		this.setContentPane(panel);
		pack();
		setVisible(true);
	}
	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}
}
