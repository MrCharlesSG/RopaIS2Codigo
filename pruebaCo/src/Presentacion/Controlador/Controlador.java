package Presentacion.Controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import Negocio.Clientes.SAClientes;
import Negocio.Clientes.TCliente;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.GUIBiblioteca;
import Presentacion.GUI.GUI;
import Presentacion.Controlador.Evento;
import Negocio.Producto.TProducto;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
import Negocio.Ventas.SAVentas;
import Negocio.Ventas.TVenta;
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
	private SAClientes saCliente;
	private SAEmpleado saEmpleado;
	private GUI gui;
	private SAVentas saVenta;
	
	private Controlador(){
		this.saMarca=FactoriaNegocio.getInstance().generaSAMarca();
		this.saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
		this.saProducto= FactoriaNegocio.getInstance().generaSAProducto();
		this.saEmpleado = FactoriaNegocio.getInstance().generaSAEmpleado();
		this.saCliente=FactoriaNegocio.getInstance().generaSAClientes();
		this.saVenta = FactoriaNegocio.getInstance().generaSAVentas();
	}
	public static Controlador getInstancia() {
		if(controlador==null){
			controlador= new Controlador();
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
				if(res>=0){
					gui.update(Evento.RES_ALTA_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_ALTA_PRODUCTO_KO, null);
				}
				break;
			}
			case Evento.BAJA_PRODUCTO:{
				int res=saProducto.delete(((TProducto) datos).getIdProducto());
				if(res>=0){
					gui.update(Evento.RES_BAJA_PRODUCTO_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_BAJA_PRODUCTO_KO, null);
				}
				break;
			}
			case Evento.LISTAR_PRODUCTOS:{
				Collection<TProducto>productos=saProducto.readAll();
				gui.update(Evento.LISTAR_PRODUCTOS, productos);
				break;
			}
			case Evento.MODIFICAR_PRODUCTO:{
				int res=saProducto.update((TProducto)datos);
				if(res>=0){
					gui.update(Evento.RES_MODIFICAR_PRODUCTO_OK,  new Integer(res));
				}
				else{
					gui.update(Evento.RES_MODIFICAR_PRODUCTO_KO, null);
				}
				break;
			}
			case Evento.PRODUCTO_POR_ID:{
				TProducto producto=saProducto.read((Integer)datos);
				if(producto.getIdMarca() == -1)
					gui.update(Evento.RES_PRODUCTO_POR_ID_OK, producto);
				else
					gui.update(Evento.RES_PRODUCTO_POR_ID_KO, producto);
				break;
			}
			
			/*
			 * PROVEEDORES
			 */
			case Evento.ALTA_PROVEEDOR:{
				int res=this.saProveedor.create((TProveedor)datos);
				if(res>0){
					gui.update(Evento.OK, res);
				}
				else{
					gui.update(Evento.KO, null);
				}
				break;
            }
            case Evento.BAJA_PROVEEDOR:{
                int res = saProveedor.delete(((TProveedor)datos).getId());

                if(res > 0)
                    gui.update(Evento.OK, res);
                else
                    gui.update(Evento.KO, null);
                break;
            }
            case Evento.LISTAR_PROVEEDORES:{
                Collection<TProveedor>proveedores = saProveedor.readAll();
                if(proveedores!= null && proveedores instanceof ArrayList){
                	gui.update(Evento.OK, proveedores);
                }else{
                	gui.update(Evento.KO, null);
                }
                break;
            }
            case Evento.PROVEEDOR_POR_ID:{
            	    TProveedor proveedor = saProveedor.read((int)datos);
            	    int res= proveedor==null ? Evento.KO : Evento.OK;
            	    gui.update(res, proveedor);
                break;
            }
            case Evento.MODIFICAR_PROVEEDOR:{
                	int res = this.saProveedor.update((TProveedor)datos);
                	if( res>0){
                		gui.update(Evento.OK, datos);
                	}else{
                		gui.update(Evento.KO, null);
                	}
                break;
            }
            /*
			 * CLIENTES
			 */
			case Evento.ALTA_CLIENTE:{
				
				 int res=this.saCliente.create((TCliente)datos);
				 if(res>0){
					gui.update(Evento.RES_ALTA_CLIENTE_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_ALTA_CLIENTE_KO, null);
				}
			
				break;
            }
            case Evento.BAJA_CLIENTE:{
                  int idcliente=(int) datos;
                  int res = saCliente.delete(idcliente);
                  if(res > 0)
                    gui.update(Evento.RES_BAJA_CLIENTE_OK, new Integer(res));
                else
                    gui.update(Evento.RES_BAJA_CLIENTE_KO, null);
                break;
            }
            case Evento.LISTAR_CLIENTES:{
              Collection<TCliente>clientes= saCliente.readAll();
              gui.update(Evento.LISTAR_CLIENTES, clientes);
               break;
            }
            case Evento.CLIENTE_POR_ID:{
            	    TCliente cliente = saCliente.read((int)datos);
            	   int res= cliente==null ? Evento.RES_CLIENTE_POR_ID_KO : Evento.RES_CLIENTE_POR_ID_OK;
            	   gui.update(res, cliente);
                break;
            }
            case Evento.MODIFICAR_CLIENTE:{
                	int res=saCliente.update((TCliente)datos);
                	if(res>0)
                		gui.update(Evento.RES_MODIFICAR_CLIENTE_OK, res);
                	else
                		gui.update(Evento.RES_MODIFICAR_CLIENTE_KO, null);
                break;
            }
            /*
             * EMPLEADOS
             */
            case Evento.ALTA_EMPLEADO:{
            	int res = this.saEmpleado.create((TEmpleado)datos);
            	if(res>0) {
            		gui.update(Evento.OK, res);
            	}else {
            		gui.update(Evento.KO, null);
            	}
            	break;
            }
            case Evento.BAJA_EMPLEADO:{
            	int res = this.saEmpleado.delete((int)datos);
            	if(res==(int)datos) {
            		gui.update(Evento.OK, res);
            	}else {
            		gui.update(Evento.KO, null);
            	}
            	break;
            }
            case Evento.MODIFICAR_EMPLEADO:{
            	int res = this.saEmpleado.update((TEmpleado)datos);
            	if(res>0) {
            		gui.update(Evento.OK, (TEmpleado)datos);
            	}else {
            		gui.update(Evento.KO, null);
            	}
            	break;
            }
            case Evento.EMPLEADO_POR_ID:{
            	TEmpleado empl = saEmpleado.read((int)datos);
	         	int res= empl==null ? Evento.RES_CLIENTE_POR_ID_KO : Evento.RES_CLIENTE_POR_ID_OK;
	         	gui.update(res, empl);
	        	break;
            }
            case Evento.LISTAR_EMPLEADO:{
            	Collection<TEmpleado> lista = this.saEmpleado.readAll();
            	if(lista!= null && lista instanceof ArrayList){
                	gui.update(Evento.OK, lista);
                }else{
                	gui.update(Evento.KO, null);
                }
                break;
            }
            /*
             * VENTAS
             */
            case Evento.ABRIR_VENTA:{
            	int res=saVenta.create((TVenta)datos);
            	if(res>0){
            		gui.update(Evento.RES_ABRIR_VENTA_OK, saVenta.read(res));
            	}
            	else{
            		gui.update(Evento.RES_ABRIR_VENTA_OK, null);
            	}
            	break;
            }
            case Evento.CERRAR_VENTA:{
            	int res=saVenta.update((TVenta)datos);
            	if(res>0){
            		gui.update(Evento.RES_CERRAR_VENTA_OK, null);
            	}
            	else{
            		gui.update(Evento.RES_CERRAR_VENTA_KO, null);
            	}
            	break;
            }
            case Evento.VENTA_POR_ID:{
            	TVenta venta=saVenta.read((int)datos);
				if(venta==null){
					gui.update(Evento.RES_VENTA_POR_ID_KO, null);
				}
				else{
					gui.update(Evento.RES_VENTA_POR_ID_OK, venta);
				}
            	break;
            }
            case Evento.LISTAR_VENTAS:{
            	Collection<TVenta>ventas=saVenta.readAll();
				gui.update(Evento.LISTAR_VENTAS, ventas);
            	break;
            }
            case Evento.DEVOLUCION_VENTA:{
            	int res=saVenta.devolucionVenta((List<Integer>)datos);
            	if(res>0){
            		
            	}
            	else{
            		
            	}
            	break;
            }
            case Evento.VENTAS_DE_UN_CLIENTE:{
            	Collection<TVenta>ventas=saVenta.readByCliente((Integer)datos);
            	gui.update(Evento.VENTAS_DE_UN_CLIENTE, ventas);
            	break;
            }
            case Evento.VENTAS_DE_UN_EMPLEADO:{
            	Collection<TVenta>ventas=saVenta.readByEmpleado((Integer)datos);
            	gui.update(Evento.VENTAS_DE_UN_EMPLEADO, ventas);
            	break;
            }

		}
	}
	
	public void setGUI(GUI gui){
		this.gui=gui;
	}
	public boolean marcasExisten(ArrayList<Integer> marcas) {
		boolean existe=true;
		if(marcas.size()!=0){
			//SAMarca saMarca = FactoriaNegocio.getInstance().generaSAMarca();
			Collection<TMarca> listam = saMarca.readAll();
			int i=0;
			while(existe && i<marcas.size() ){
				existe=false;
				for(TMarca t: listam){
					if(t.getID()==marcas.get(i)){
						existe=true;
					}
				}
				i++;
			}
		}
		return existe;
	}
	public boolean clienteExiste(int id_cliente) {
		boolean existe=true;
		
		if(saCliente.read(id_cliente)==null)
			existe=false;
		return existe;
	}
	public boolean empleadoExiste(int id_empleado) {
		boolean existe=true;
		if(saEmpleado.read(id_empleado)==null)
			existe=false;
		return existe;
	}
	public boolean productosExisten(Map<Integer, Integer> productos) {
		boolean existe=true;
		if(productos.keySet().size()!=0){
			Collection<TProducto> listap = saProducto.readAll();
			int i=0;
			for(int id: productos.keySet()){
			existe=false;
				for(TProducto p:listap){
					if(p.getIdProducto()==id&&p.getCantidad()<productos.get(id))
						return false;//existe el producto pero se ha seleccionado demasiada cantidad
					if(p.getIdProducto()==id){
						existe=true;
					}
				}
				if(!existe)//si sale del for sin que cambie existe a true es q no existe ese prod
					return false;
			}
			
		}
		return existe;
	}
}