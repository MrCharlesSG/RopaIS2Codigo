package Presentacion.ProveedorPresentacion;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JPanel;
	import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

	public class IGUIProv extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel j;
		
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
						setVisibility(false);	
						FactoriaPresentacion.getInstance().generaGUIAltaProveedor();
					}
			});
			
			bajaProv.addActionListener(new ActionListener()
				{ 
				public void actionPerformed(ActionEvent e){		
						setVisibility(false);
						FactoriaPresentacion.getInstance().generaGUIBajaProveedor();
					}
			});
			
			listarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					setVisibility(false);
					FactoriaPresentacion.getInstance().generaGUIListarProveedor();
			}
			});
			modificarProv.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					setVisibility(false);
					FactoriaPresentacion.getInstance().generaGUIModificarProveedor();
				}
			});
			provPorID.addActionListener(new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e){		
					setVisibility(false);
					FactoriaPresentacion.getInstance().generaGUIProveedorPorID();
				}
			});
			
			this.add(altaProv);
			this.add(bajaProv);
			this.add(listarProv);
			this.add(modificarProv);
			this.add(provPorID);
			this.setVisibility(true);
		}
		
		private void setVisibility(boolean vis){
			this.setVisible(vis);
		}

	}
	


