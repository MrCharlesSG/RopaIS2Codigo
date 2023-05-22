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
						FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_PRODUCTO);
					}
			});
			
			bajaProducto.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{		
						FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_PRODUCTO);
				}
			});
			
			listarProducto.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_PRODUCTOS);
			}
			});
			modificarProducto.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_PRODUCTO);
			}
			});
			productoPorID.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUI(Evento.PRODUCTO_POR_ID);
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
	


