package Presentacion.ProveedorPresentacion;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JPanel;


import Presentacion.Controlador.Controlador;
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
				FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_PROVEEDORES_POR_MARCA);
				}
			});
			
			deleteMarcaToProv.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){	
				FactoriaPresentacion.getInstance().generaGUI(Evento.DELETE_MARCA_OF_PROVEEDOR);
				}
			});
			
			addMarcaToProv.addActionListener(new ActionListener()
			{ 
			public void actionPerformed(ActionEvent e){	
				FactoriaPresentacion.getInstance().generaGUI(Evento.ADD_MARCA_TO_PROVEEDOR);
				}
			});
			
			altaProv.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e){	
					FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_PROVEEDOR);
					}
			});
			
			bajaProv.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e){		
						
					FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_PROVEEDOR);
					}
			});
			
			listarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_PROVEEDORES);
			}
			});
			modificarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					
					FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_PROVEEDOR);
				}
			});
			provPorID.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					
					FactoriaPresentacion.getInstance().generaGUI(Evento.PROVEEDOR_POR_ID);
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
	


