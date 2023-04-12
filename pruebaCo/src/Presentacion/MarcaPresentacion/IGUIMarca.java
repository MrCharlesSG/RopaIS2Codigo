package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

public class IGUIMarca extends JPanel {//probablemente sea mejor hacerlo singleton o algo
	public IGUIMarca(){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton altaMarca=new JButton("Alta de marca");
		JButton bajaMarca=new JButton("Baja de marca");
		JButton listarMarca=new JButton("Listar marcas");
		JButton modificarMarca=new JButton("Modificar marca");
		JButton marcaPorID=new JButton("Marca por ID");
		
		this.add(altaMarca);
		this.add(bajaMarca);
		this.add(listarMarca);
		this.add(modificarMarca);
		this.add(marcaPorID);
		this.setVisible(false);
		
		altaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					
					FactoriaPresentacion.getInstance().generaGUIAltaMarca();
				}
		});
		bajaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					FactoriaPresentacion.getInstance().generaGUIBajaMarca();
			}
		});
		listarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				FactoriaPresentacion.getInstance().generaGUIListarMarcas();
		}
		});
		modificarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				FactoriaPresentacion.getInstance().generaGUIModificarMarca();
		}
		});
		marcaPorID.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				FactoriaPresentacion.getInstance().generaGUIMarcaPorID();
		}
		});
		
	}

}
