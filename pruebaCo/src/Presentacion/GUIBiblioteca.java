package Presentacion;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Negocio.MarcaNegocio.TMarca;
//import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.IGUIMarca;

public class GUIBiblioteca extends JFrame implements GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GUIBiblioteca guiBiblioteca;
//	private IGUIProducto guiProducto;
//	private IGUIVenta guiVenta;
//	private Controlador controlador;
	
	private GUIBiblioteca(){
	//	controlador=Controlador.getInstancia();
		//igual este es el menu principal donde eliges o marca o producto o etc..
	}	
	public static GUIBiblioteca getInstancia(){
		if(guiBiblioteca==null){
			guiBiblioteca=new GUIBiblioteca();
		}
		return guiBiblioteca;
	}
	@Override
	public void update(int evento, Object datos) {
		switch (evento)
		{ 
		case Evento.MOSTRAR_GUI_BIBLIOTECA: { setVisible(true); break; }
		case Evento.OCULTAR_GUI_BIBLIOTECA: { setVisible(false); break;}
		case Evento.Mostrar_GUI_MARCA:{
			//guiMarca.algo
		}
		case Evento.RES_ALTA_MARCA_OK: 
		{
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Alta de marca con ID: "+id.intValue());
			setVisible(true);
			break; 
		}
		case Evento.RES_ALTA_MARCA_KO: 
		{ 
			JOptionPane.showMessageDialog(null, "No se pudo dar de alta la marca");
			setVisible(true);
			break; 
		}
		case Evento.RES_MODIFICAR_MARCA_OK: 
		{
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Se ha modificado correctamente la marca con ID: "+id.intValue());
			setVisible(true);
			break; 
		}
		case Evento.RES_MODIFICAR_MARCA_KO: 
		{ 
			JOptionPane.showMessageDialog(null, "No se pudo modificar la marca");
			setVisible(true);
			break; 
		}
		case Evento.RES_BAJA_MARCA_OK: 
		{
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Se ha dado de baja correctamente la marca con ID: "+id.intValue());
			setVisible(true);
			break; 
		}
		case Evento.RES_BAJA_MARCA_KO: 
		{ 
			JOptionPane.showMessageDialog(null, "No se pudo dar de baja la marca");
			setVisible(true);
			break; 
		}
		case Evento.LISTAR_MARCAS:
		{
			Collection<TMarca>marcas=(Collection<TMarca>) datos;
			StringBuilder str=new StringBuilder();
			str.append("ID: NOMBRE: CANTIDAD: ACTIVO").append(System.lineSeparator());
			for(TMarca m: marcas){
				str.append(m.getID()+":      "+m.getNombre()+":      "+m.getCantidad()+":      "+m.getActivo()).append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(null, str.toString());
			setVisible(true);
			break;
		}
	case Evento.RES_MARCA_PORID_OK:
	{
	TMarca m=(TMarca) datos;
		StringBuilder str=new StringBuilder();
		str.append("ID: NOMBRE: CANTIDAD: ACTIVO").append(System.lineSeparator());
		str.append(m.getID()+":      "+m.getNombre()+":      "+m.getCantidad()+":      "+m.getActivo()).append(System.lineSeparator());
		JOptionPane.showMessageDialog(null, str.toString());
		setVisible(true);
		break;
	}
	case Evento.RES_MARCA_PORID_KO:
	{
		JOptionPane.showMessageDialog(null, "No se pudo encontrar la marca con ese ID");
		setVisible(true);
		break; 
	}
	}
}
}
