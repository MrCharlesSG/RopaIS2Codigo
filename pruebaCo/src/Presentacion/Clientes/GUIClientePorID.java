package Presentacion.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Utils;
import Negocio.Clientes.TCliente;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIClientePorID extends JFrame implements GUI{
	public GUIClientePorID() {
		setTitle("Cliente por ID");
		JPanel panel=new JPanel();
		JLabel lID=new JLabel("Identificador:");
		final JTextField tID= new JTextField(5);
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		this.setLocationRelativeTo(null);
		
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					try{
						int id=Integer.parseInt(tID.getText());
						Controlador.getInstancia().setGUI(GUIClientePorID.this);
						Controlador.getInstancia().accion(Evento.CLIENTE_POR_ID, new Integer(id));
					}catch(Exception e1){
						Utils.showErrorMsg("Los parametros introducidos son incorrectos");
					}
				}
		});
		cancelar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
			}
		});
		setVisible(true);
	}

	@Override
	public void update(int evento, Object datos) {
	if( Evento.RES_CLIENTE_POR_ID_OK==evento){
	TCliente c=(TCliente) datos;
		StringBuilder str=new StringBuilder();
		str.append("ID: NOMBRE: APELLIDO1: APELLIDO2: DNI: TLF: PREMIUM: ACTIVO").append(System.lineSeparator());
		str.append(c.getID()+":      "+c.getNombre()+":      "+c.getApellido1()+":      "+c.getApellido2()+":      "+c.getDNI() +":      "+c.getTelefono()+":      "+c.getPremium()+":      "+c.getActivo()).append(System.lineSeparator());
		JOptionPane.showMessageDialog(null, str.toString());
	
	}
	if( Evento.RES_CLIENTE_POR_ID_KO==evento)
	{
		JOptionPane.showMessageDialog(null, "No se pudo encontrar el cliente con ese ID");

	}
	}
}
