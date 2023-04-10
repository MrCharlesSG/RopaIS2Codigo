package Presentacion.Controlador;

import java.util.ArrayList;
import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.GUIBiblioteca;
import Presentacion.GUI.GUI;
import Presentacion.Controlador.Evento;
import Negocio.Producto.TProducto;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
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
	private SAProveedores saProveedor;
	private GUI gui;
	
	private Controlador(GUI gui){
		this.gui=gui;
		this.saMarca=FactoriaNegocio.getInstance().generaSAMarca();
		this.saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
		this.saProducto= FactoriaNegocio.getInstance().generaSAProducto();
	}
	public static Controlador getInstancia() {
		if(controlador==null){
			controlador= new Controlador(GUIBiblioteca.getInstancia());
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
			/*
			 * MARCA
			 */
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
				if(marca==null){
					gui.update(Evento.RES_MARCA_PORID_KO, null);
				}
				else{
					gui.update(Evento.RES_MARCA_PORID_OK, marca);
				}
			
				break;
			}
			case Evento.MODIFICAR_MARCA:{
				int res=saMarca.update((TMarca) datos);
				
				if(res>0)
					gui.update(Evento.RES_MODIFICAR_MARCA_OK, new Integer(res));
				else
					gui.update(Evento.RES_MODIFICAR_MARCA_KO, null);
			
				break;
			}
			
			/*
			 * PRODUCTO
			 */
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
			
			/*
			 * PROVEEDORES
			 */
			case Evento.ALTA_PROVEEDOR:{
				int res=this.saProveedor.create((TProveedor)datos);
				if(res>0){
					gui.update(Evento.RES_ALTA_PROVEEDOR_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_ALTA_PROVEEDOR_KO, null);
				}
				break;
            }
            case Evento.BAJA_PROVEEDOR:{
                int idProveedor=(int) datos;
                int res = saProveedor.delete(idProveedor);

                if(res > 0)
                    gui.update(Evento.RES_BAJA_PROVEEDOR_OK, new Integer(res));
                else
                    gui.update(Evento.RES_BAJA_PROVEEDOR_KO, null);
                break;
            }
            case Evento.LISTAR_PROVEEDORES:{
                Collection<TProveedor>proveedores = saProveedor.readAll();
                if(proveedores!= null && proveedores instanceof ArrayList){
                	gui.update(Evento.RES_LISTAR_PROVEEDOR_OK, datos);
                }else{
                	gui.update(Evento.RES_LISTAR_PROVEEDOR_KO, null);
                }
                break;
            }
            case Evento.PROVEEDOR_POR_ID:{
            	    TProveedor proveedor = saProveedor.read((int)datos);
            	    int res= proveedor==null ? Evento.RES_PROVEEDOR_POR_ID_KO : Evento.RES_PROVEEDOR_POR_ID_OK;
            	    gui.update(res, proveedor);
                break;
            }
            case Evento.MODIFICAR_PROVEEDOR:{
                	TProveedor proveedor = FactoriaNegocio.getInstance().generaTProveedor((String[])datos);
                	if(proveedor!=null && saProveedor.update(proveedor)>0){
                		gui.update(Evento.RES_MODIFICAR_PRODUCTO_OK, proveedor);
                	}else{
                		gui.update(Evento.RES_MODIFICAR_PROVEEDOR_KO, null);
                	}
                break;
            }
		}
	}
}