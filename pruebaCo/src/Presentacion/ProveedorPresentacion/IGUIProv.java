package Presentacion.ProveedorPresentacion;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JPanel;


import Presentacion.Controlador.Controlador;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

	public class IGUIProv extends JPanel {
		
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
			
			altaProv.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e){	
					Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIAltaProveedor());
					}
			});
			
			bajaProv.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e){		
						
					Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIBajaProveedor());
					}
			});
			
			listarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					//este no usa el set Gui porque no tiene botones y directamente llama al controlador
					FactoriaPresentacion.getInstance().generaGUIListarProveedor();
			}
			});
			modificarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					
					Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIModificarProveedor());
				}
			});
			provPorID.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					
					Controlador.getInstancia().setGUI(FactoriaPresentacion.getInstance().generaGUIProveedorPorID());
				}
			});
			
			this.add(altaProv);
			this.add(bajaProv);
			this.add(listarProv);
			this.add(modificarProv);
			this.add(provPorID);
			this.setVisible(false);
		}
		

	}
	


