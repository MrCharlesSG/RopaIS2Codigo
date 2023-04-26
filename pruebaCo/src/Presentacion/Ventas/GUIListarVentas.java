package Presentacion.Ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Ventas.TVenta;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;

public class GUIListarVentas extends JFrame implements GUI{
	public GUIListarVentas() {
		setTitle("Listar ventas");
		JPanel panel=new JPanel();
		
		JButton aceptar=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		
		aceptar.addActionListener(new ActionListener()
			{ public void actionPerformed(ActionEvent e)
				{		
					setVisible(false);
					Controlador.getInstancia().setGUI(GUIListarVentas.this);
					Controlador.getInstancia().accion(Evento.LISTAR_VENTAS,null);
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
	if( Evento.LISTAR_VENTAS==evento)
	{
		Collection<TVenta>ventas=(Collection<TVenta>) datos;
		StringBuilder str=new StringBuilder();
		str.append("ID: ID EMPLEADO: ID CLIENTE: PRECIO: UNIDADES: ACTIVO: PRODUCTOS ").append(System.lineSeparator());
		for(TVenta v: ventas){
			str.append(v.get_id()+":      "+v.get_id_empleado()+":      "+v.get_id_cliente()+":      "+v.get_precio()+":      "+v.get_contador()+":      "+v.get_activo()+":      ");
			Map<Integer,Integer>vm=v.get_map();
			for(Integer id : v.get_map().keySet()){
				str.append(id+" "+vm.get(id)+ " ");
			}
			str.append(System.lineSeparator());
		}
		JOptionPane.showMessageDialog(null, str.toString());
	}
		
	}
}
