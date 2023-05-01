package Presentacion.Producto;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JPanel;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

	public class IGUIProducto extends JPanel {//probablemente sea mejor hacerlo singleton o algo
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public IGUIProducto(){
			
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JButton altaProducto=new JButton("Alta de producto");
			JButton bajaProducto=new JButton("Baja de producto");
			JButton listarProducto=new JButton("Listar producto");
			JButton modificarProducto=new JButton("Modificar producto");
			JButton productoPorID=new JButton("Producto por ID");
			
			this.add(altaProducto);
			this.add(bajaProducto);
			this.add(listarProducto);
			this.add(modificarProducto);
			this.add(productoPorID);
			this.setVisible(false);
			
			altaProducto.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e)
					{		
						FactoriaPresentacion.getInstance().generaGUIAltaProducto().setVisible(true);
					}
			});
			
			bajaProducto.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{		
						FactoriaPresentacion.getInstance().generaGUIBajaProducto().setVisible(true);
				}
			});
			
			listarProducto.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUIListarProducto().setVisible(true);
			}
			});
			modificarProducto.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUIModificarProducto().setVisible(true);
			}
			});
			productoPorID.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUIProductoPorID().setVisible(true);
			}
			});
			
		}

	}
	


