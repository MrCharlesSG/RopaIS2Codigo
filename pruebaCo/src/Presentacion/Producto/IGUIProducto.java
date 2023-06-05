package Presentacion.Producto;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JPanel;

import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.GUI.GUI;

	public class IGUIProducto extends JPanel implements GUI{//probablemente sea mejor hacerlo singleton o algo
		
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
			JButton productosPorMarca=new JButton("Productos por ID de marca");
			this.add(altaProducto);
			this.add(bajaProducto);
			this.add(listarProducto);
			this.add(modificarProducto);
			this.add(productoPorID);
			this.add(productosPorMarca);
			this.setVisible(false);
			
			productosPorMarca.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e)
				{		
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_PRODUCTOS_POR_MARCA);
				gui.setGUIVisible(true);
				}
		});
			
			altaProducto.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e)
					{		
					GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_PRODUCTO);
						gui.setGUIVisible(true);
					}
			});
			
			bajaProducto.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{		
					GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_PRODUCTO);
						gui.setGUIVisible(true);
				}
			});
			
			listarProducto.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_PRODUCTOS);
					gui.setGUIVisible(true);
				}
			});
			modificarProducto.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_PRODUCTO);
					gui.setGUIVisible(true);
				}
			});
			productoPorID.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.PRODUCTO_POR_ID);
					gui.setGUIVisible(true);
				}
			});
			
		}

		@Override
		public void update(int evento, Object datos) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setGUIVisible(boolean b) {
			this.setVisible(b);
		}
		
		

	}
	


