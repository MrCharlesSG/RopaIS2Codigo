package Presentacion.Controlador;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.GUIBiblioteca;
import Presentacion.GUI.GUI;
import Presentacion.Controlador.Evento;
import Negocio.Producto.TProducto;
import Negocio.Producto.SAProducto;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author sagog
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Controlador {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private static Controlador controlador;
	private SAMarca saMarca; 
	private SAProducto saProducto;
	private GUI gui;
	
	private Controlador(SAMarca saMarca,GUI gui){
		this.gui=gui;
		this.saMarca=saMarca;
		this.saProducto=saProducto;
	}
	public static Controlador getInstancia() {
		if(controlador==null){
			controlador= new Controlador(FactoriaNegocio.getInstance().generaSAMarca(), GUIBiblioteca.getInstancia());
		}
		return controlador;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void accion(int evento,Object datos) {
		switch(evento){
			case Evento.ALTA_MARCA:{
				TMarca tMarca=(TMarca)datos;
				int res=saMarca.create(tMarca);
				
				if(res>0)
					gui.update(Evento.RES_ALTA_MARCA_OK, new Integer(res));
				else
					gui.update(Evento.RES_ALTA_MARCA_KO, null);
				break;
			}
			case Evento.BAJA_MARCA:{
				int idMarca=(int) datos;
				int res=saMarca.delete(idMarca);
				
				if(res>0)
					gui.update(Evento.RES_BAJA_MARCA_OK, new Integer(res));
				else
					gui.update(Evento.RES_BAJA_MARCA_KO, null);
				break;
			}
			case Evento.LISTAR_MARCAS:{
				Collection<TMarca>marcas=saMarca.readAll();
				gui.update(Evento.LISTAR_MARCAS, marcas);
				break;
			}
			case Evento.MARCA_PORID:{
				TMarca marca=saMarca.read((int)datos);
				gui.update(Evento.MARCA_PORID, marca);
				break;
			}
			case Evento.MODIFICAR_MARCA:{
				int res=saMarca.update((int) datos);
				
				if(res>0)
					gui.update(Evento.RES_MODIFICAR_MARCA_OK, new Integer(res));
				else
					gui.update(Evento.RES_MODIFICAR_MARCA_KO, null);
			
				break;
			}
			case Evento.ALTA_PRODUCTO:{
				int res=saProducto.create((TProducto) datos);
				if(res>0){
					gui.update(Evento.RES_ALTA_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_ALTA_PRODUCTO_KO, null);
				}
				break;
			}
			case Evento.BAJA_PRODUCTO:{
				int res=saProducto.delete((int) datos);
				if(res>0){
					gui.update(Evento.RES_BAJA_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_BAJA_PRODUCTO_KO, null);
				}
				break;
			}
			case Evento.LISTAR_PRODUCTOS:{
				Collection<TProducto>productos=saProducto.readAll();
				gui.update(Evento.PRODUCTO_POR_ID, productos);
				break;
			}
			case Evento.MARCA_POR_PRODUCTO:{
				TProducto producto=saProducto.read((int) datos);
				gui.update(Evento.MARCA_POR_PRODUCTO, producto);
				break;
			}
			case Evento.MODIFICAR_PRODUCTO:{
				int res=saProducto.update((TProducto)datos);
				if(res>0){
					gui.update(Evento.RES_MODIFICAR_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_MODIFICAR_PRODUCTO_KO, null);
				}
				break;
			}
			case Evento.PRODUCTO_POR_ID:{
				TProducto producto=saProducto.read((int) datos);
				gui.update(Evento.PRODUCTO_POR_ID, producto);
				break;
			}
			case Evento.VENTA_POR_PRODUCTO:{
				TProducto producto=saProducto.read((int) datos);
				gui.update(Evento.VENTA_POR_PRODUCTO, producto);
				break;
			}
			case Evento.ALTA_PROVEEDOR:{
				break;
			}
			case Evento.BAJA_PROVEEDOR:{
				break;
			}
			case Evento.LISTAR_PROVEEDORES:{
				break;
			}
			case Evento.MARCAS_PROVEEDOR: {
				break;
			}
			case Evento.PROVEEDOR_POR_ID:{
				break;
			}
			
		}
	}
}