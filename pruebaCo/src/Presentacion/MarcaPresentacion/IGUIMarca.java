package Presentacion.MarcaPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

public class IGUIMarca extends JFrame {//probablemente sea mejor hacerlo singleton o algo
	public IGUIMarca(){
		setTitle("Menu de marcas");
		JPanel panel=new JPanel();

		JButton altaMarca=new JButton("Alta de marca");
		JButton bajaMarca=new JButton("Baja de marca");
		JButton listarMarca=new JButton("Listar marcas");
		JButton modificarMarca=new JButton("Modificar marca");
		JButton marcaPorID=new JButton("Marca por ID");
		
		panel.add(altaMarca);
		panel.add(bajaMarca);
		panel.add(listarMarca);
		panel.add(modificarMarca);
		panel.add(marcaPorID);
		
		getContentPane().add(panel);
		pack();
		
		altaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					FactoriaPresentacion.getInstance().generaGUIAltaMarca().setVisible(true);
				}
		});
		bajaMarca.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					FactoriaPresentacion.getInstance().generaGUIBajaMarca().setVisible(true);
			}
		});
		listarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
				FactoriaPresentacion.getInstance().generaGUIListarMarcas().setVisible(true);
		}
		});
		modificarMarca.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
				FactoriaPresentacion.getInstance().generaGUIModificarMarca().setVisible(true);
		}
		});
		marcaPorID.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{		
				setVisible(false);
				FactoriaPresentacion.getInstance().generaGUIMarcaPorID().setVisible(true);
		}
		});
		
	}

}
