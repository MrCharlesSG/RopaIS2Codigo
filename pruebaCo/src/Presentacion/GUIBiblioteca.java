package Presentacion;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Evento;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.IGUIMarca;

public class GUIBiblioteca extends JFrame implements GUI{
	private static GUIBiblioteca guiBiblioteca;
    private IGUIMarca guiMarca;
//	private IGUIPublicacion guiPublicacion;
//	private IGUIPrestamo guiPrestamo;
	private Controlador controlador;
	
	private GUIBiblioteca(){
		controlador=Controlador.getInstancia();
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
	}

}
}
