package Presentacion.Ventas;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import Negocio.ProductosDeVenta.TCarrito;
import Negocio.ProductosDeVenta.TProductosDeVenta;
import Negocio.Ventas.TVenta;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.GUIAltaMarca;

public class CarritoDialog extends Dialog {
	/**
	 * 
	 */
	String[] header = { "ID PRODUCTO","CANTIDAD"};
	private DefaultTableModel _dataTableModel;
	private static final long serialVersionUID = 1L;
	private Map<Integer,Integer> carro;
	private boolean status;
	private TCarrito prodDeVenta;
	
	public CarritoDialog (Frame parent,Map<Integer,Integer> c){
		super(parent,true);
		carro=c;
		prodDeVenta=new TCarrito();
	this.initGUI() ;
	}
	private void initGUI() {
		
		setTitle("Carrito de de productos");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JLabel descripcion=new JLabel("<html><p>Elige un producto por su id y selecciona una cantidad</p></html>");
		descripcion.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(descripcion);
		this.add(mainPanel);
		JPanel panel1=new JPanel();
		JLabel lprod=new JLabel("ID Producto:");
		final JTextField tprod= new JTextField(20);
		JPanel panel2=new JPanel();
		JLabel lcanti=new JLabel("Cantidad:");
		final JTextField tcant= new JTextField(20);
		panel1.add(lprod);
		panel1.add(tprod);
		panel2.add(lcanti);
		panel2.add(tcant);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		JButton aniadir=new JButton("Añadir");
		aniadir.setToolTipText("Añade n unidades de un producto");
		JButton elim=new JButton("Eliminar");
		elim.setToolTipText("Elimina n unidades de un producto");
		JButton aceptar=new JButton("Aceptar");
		
		//añado la lista
				this._dataTableModel = new DefaultTableModel(){
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				_dataTableModel.setColumnIdentifiers(header);
				this.updateTableModel();
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
		mainPanel.add(tabelScroll);

		
		
		JPanel botones=new JPanel();
		botones.add(aniadir);
		botones.add(elim);
		botones.add(aceptar);
		
		mainPanel.add(botones);
		aniadir.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{	
			try{
				Integer id=Integer.parseInt(tprod.getText());
				Integer cantidad=Integer.parseInt(tcant.getText());
				if(!carro.containsKey(id))
					carro.put(id,cantidad );
				else{
					carro.put(id, carro.get(id)+cantidad);
				}
				updateTableModel();
			}
			catch(Exception e1){
				Utils.showErrorMsg("Los parametros introducidos no son validos, intentelo de nuevo" );
			}
			
			tprod.setText(null);
			tcant.setText(null);
				
			}
	});
		elim.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		try{
				int actual=carro.get(Integer.parseInt(tprod.getText()));
				int nuevo=Integer.parseInt(tcant.getText());
				if(carro.containsKey(Integer.parseInt(tprod.getText()))&&actual>=nuevo){
					
					carro.replace((Integer.parseInt(tprod.getText())), actual-nuevo);
					
					if(carro.get(Integer.parseInt(tprod.getText()))==0){
						carro.remove(Integer.parseInt(tprod.getText()));
					}
					updateTableModel();
				}
			}catch(Exception e1){
				Utils.showErrorMsg("Los parametros introducidos no son validos, intentelo de nuevo" );
			}
				tprod.setText(null);
				tcant.setText(null);
			}
	});
		aceptar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
				status=true;
				tprod.setText(null);
				tcant.setText(null);
				prodDeVenta.setProductos(carro);
				Controlador.getInstancia().accion(Evento.CERRAR_VENTA, prodDeVenta);
			}
	});
		
		setPreferredSize(new Dimension(400, 400));
		pack();
		setResizable(false);
		setVisible(false);
	}
	private void updateTableModel() {
			_dataTableModel.setNumRows(carro.keySet().size());
			int i=0;
			for (int k :carro.keySet()) {
				_dataTableModel.setValueAt(k, i, 0);
				_dataTableModel.setValueAt(carro.get(k), i, 1);
				i++;
			}
		
	}
	public boolean  open(int datos) {
		prodDeVenta.setVenta(datos);//paso el id de la venta
		if (getParent() != null)
			setLocation(//
					getParent().getLocation().x + getParent().getWidth() / 2 - getWidth() / 2, //
					getParent().getLocation().y + getParent().getHeight() / 2 - getHeight() / 2);
		pack();
		setVisible(true);
		return status;
	}
}