package Presentacion.Producto;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Presentacion.GUIBiblioteca;

/*----------hay un error al importar el SA de Producto-------------*/

//import Negocio.ProductoNegocio;
import Presentacion.GUI.GUI;

public class Controlador {
	
	private static Controlador ctrl;
	private SAProducto saProducto;
	private GUI gui;
	
	private Controlador(SAProducto saProducto,GUI gui){
		this.gui=gui;
		this.saProducto=saProducto;
	}
	
	public static Controlador getInstancia(){
		if(ctrl==null){
			ctrl=new Controlador(FactoriaNegocio.getInstance().generaSAProducto(),GUIBiblioteca.getInstancia());
		}
		return ctrl;
	}
	
	public void accion(int evento,Object datos){
		int res;
		switch(evento){
			case Evento.ALTA_PRODUCTO:
				TProducto tProducto=(TProducto)datos;
				res =saProducto.create(tProducto);
				if(res>0){
					gui.update(Evento.RES_ALTA_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_ALTA_PRODUCTO_KO, null);
				}
				break;
			case Evento.BAJA_PRODUCTO:
				res =saProducto.delete((TProducto) datos);
				if(res>0){
					gui.update(Evento.RES_BAJA_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_BAJA_PRODUCTO_KO, null);
				}
				break;
			case Evento.LISTAR_PRODUCTOS:
				Collection<TProducto>productos=saProducto.readAll();
				gui.update(Evento.PRODUCTO_POR_ID, productos);
				break;
			case Evento.MARCA_POR_PRODUCTO:
				TProducto producto=saProducto.read((TProducto) datos);
				gui.update(Evento.MARCA_POR_PRODUCTO, producto);
				break;
			case Evento.MODIFICAR_PRODUCTO:
				res=saProducto.update((TProducto)datos);
				if(res>0){
					gui.update(Evento.RES_MODIFICAR_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_MODIFICAR_PRODUCTO_KO, null);
				}
				break;
			case Evento.PRODUCTO_POR_ID:
				producto=saProducto.read((TProducto)datos);
				gui.update(Evento.PRODUCTO_POR_ID, producto);
				break;
			case Evento.VENTA_POR_PRODUCTO:
				producto=saProducto.read((TProducto)datos);
				gui.update(Evento.VENTA_POR_PRODUCTO, producto);
				break;
		}
	}
}
