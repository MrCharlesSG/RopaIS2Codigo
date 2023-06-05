package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.GUI.GUI;

public class IGUIMarca extends JPanel implements GUI{//probablemente sea mejor hacerlo singleton o algo
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IGUIMarca(){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton altaMarca=new JButton("Alta de marca");
		JButton bajaMarca=new JButton("Baja de marca");
		JButton listarMarca=new JButton("Listar marcas");
		JButton modificarMarca=new JButton("Modificar marca");
		JButton marcaPorID=new JButton("Marca por ID");
		JButton listarPorProveedor = new JButton("Marca por Proveedor");
		JButton addProvToMarca=new JButton("Add Proveedor to Marca");
		JButton deleteProvToMarca=new JButton("Delete Proveedor to Marca");

		
		this.add(altaMarca);
		this.add(bajaMarca);
		this.add(listarMarca);
		this.add(modificarMarca);
		this.add(marcaPorID);
		this.add(addProvToMarca);
		this.add(listarPorProveedor);
		this.add(deleteProvToMarca);
		this.setVisible(false);
		
		deleteProvToMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.DELETE_PROVEEDOR_OF_MARCA);
			gui.setGUIVisible(true);
			}
		});
		listarPorProveedor.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_MARCA_POR_PROVEEDORES);
			gui.setGUIVisible(true);
			}
		});
		altaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_MARCA);
				gui.setGUIVisible(true);
				}
		});
		bajaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_MARCA);
				gui.setGUIVisible(true);
			}
		});
		listarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_MARCAS);
			gui.setGUIVisible(true);
		}
		});
		modificarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_MARCA);
			gui.setGUIVisible(true);
		}
		});
		marcaPorID.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.MARCA_PORID);
			gui.setGUIVisible(true);
		}
		});
		
		addProvToMarca.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e){
				GUI gui=FactoriaPresentacion.getInstance().generaGUI(Evento.ADD_PROVEEDOR_TO_MARCA);
				gui.setGUIVisible(true);
			}
		});
	}

	@Override
	public void update(int evento, Object datos){};
	
}