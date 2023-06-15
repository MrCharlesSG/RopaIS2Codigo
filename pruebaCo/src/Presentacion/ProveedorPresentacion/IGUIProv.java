package Presentacion.ProveedorPresentacion;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JPanel;



import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.GUI.GUI;

	public class IGUIProv extends JPanel implements GUI{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public IGUIProv(){
			initGUI();			
		}
		
		private void initGUI(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JButton altaProv=new JButton("Alta de proveedor");
			JButton bajaProv=new JButton("Baja de proveedor");
			JButton listarProv=new JButton("Listar proveedor");
			JButton modificarProv=new JButton("Modificar proveedor");
			JButton provPorID=new JButton("Proveedor por ID");
			JButton marcasDeProv=new JButton("Proveedores Por Marca");
			JButton addMarcaToProv = new JButton("Add Marca to Proveedor");
			JButton deleteMarcaToProv = new JButton("Delete Marca of Proveedor");
			
			marcasDeProv.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){	
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_PROVEEDORES_POR_MARCA);
				gui.setGUIVisible(true);
			}
			});
			
			deleteMarcaToProv.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){	
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.DELETE_MARCA_OF_PROVEEDOR);
				gui.setGUIVisible(true);	
			}
			});
			
			addMarcaToProv.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){	
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ADD_MARCA_TO_PROVEEDOR);
				gui.setGUIVisible(true);	
			}
			});
			
			altaProv.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e){	
					GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_PROVEEDOR);
					gui.setGUIVisible(true);	
				}
			});
			
			bajaProv.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e){		
						
					GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_PROVEEDOR);
					gui.setGUIVisible(true);	
				}
			});
			
			listarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_PROVEEDORES);
					gui.setGUIVisible(true);
				}
			});
			modificarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					
					GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_PROVEEDOR);
					gui.setGUIVisible(true);
				}
			});
			provPorID.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					
					GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.PROVEEDOR_POR_ID);
					gui.setGUIVisible(true);
				}
			});
			
			this.add(altaProv);
			this.add(bajaProv);
			this.add(listarProv);
			this.add(modificarProv);
			this.add(provPorID);
			this.add(marcasDeProv);
			this.add(addMarcaToProv);
			this.add(deleteMarcaToProv);
			this.setVisible(false);
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
	


