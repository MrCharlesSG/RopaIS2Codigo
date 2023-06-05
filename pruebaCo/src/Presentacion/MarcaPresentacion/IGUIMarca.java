package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Controlador.Evento;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.FactoriaPresentacion.FactoriaPresentacionImp;
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
		
		this.add(altaMarca);
		this.add(bajaMarca);
		this.add(listarMarca);
		this.add(modificarMarca);
		this.add(marcaPorID);
		this.add(addProvToMarca);
		this.add(listarPorProveedor);
		this.setVisible(false);
		
		altaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				
				FactoriaPresentacion.getInstance().generaGUI(Evento.ALTA_MARCA);
				}
		});
		bajaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
				FactoriaPresentacion.getInstance().generaGUI(Evento.BAJA_MARCA);
			}
		});
		listarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			FactoriaPresentacion.getInstance().generaGUI(Evento.LISTAR_MARCAS);
		}
		});
		modificarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			FactoriaPresentacion.getInstance().generaGUI(Evento.MODIFICAR_MARCA);
		}
		});
		marcaPorID.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
			FactoriaPresentacion.getInstance().generaGUI(Evento.MARCA_PORID);
		}
		});
		
		addProvToMarca.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e){
				FactoriaPresentacion.getInstance().generaGUI(Evento.ADD_PROVEEDOR_TO_MARCA);
			}
		});
	}

	@Override
	public void update(int evento, Object datos){};
	
	@Override
	public void setGUIVisible(boolean b) {
		this.setVisible(b);
	}
}
