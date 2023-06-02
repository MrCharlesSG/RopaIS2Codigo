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
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;
import Negocio.Ventas.SAVentas;
import Negocio.Ventas.TVenta;
import Presentacion.GUI.GUI;

public class ControladorIMP extends Controlador {
	
	
	
	
	
	private GUI gui;
	
	
	public ControladorIMP(){
		
		
	}
	
	public void accion(int evento,Object datos) {
		switch(evento){
			/*
			 * MARCA
			 */
			case Evento.ALTA_MARCA:{
				SAMarca saMarca = FactoriaNegocio.getInstance().generaSAMarca();
				TMarca tMarca=(TMarca)datos;
				int res=saMarca.create(tMarca);
				
				if(res>0)
					gui.update(Evento.RES_ALTA_MARCA_OK, res);
				else
					gui.update(Evento.RES_ALTA_MARCA_KO, null);
				break;
			}
			case Evento.BAJA_MARCA:{
				SAMarca saMarca = FactoriaNegocio.getInstance().generaSAMarca();
				int idMarca=(int) datos;
				int res=saMarca.delete(idMarca);
				
				if(res>0)
					gui.update(Evento.RES_BAJA_MARCA_OK, res);
				else
					gui.update(Evento.RES_BAJA_MARCA_KO, null);
				break;
			}
			case Evento.LISTAR_MARCAS:{
				SAMarca saMarca = FactoriaNegocio.getInstance().generaSAMarca();
				Collection<TMarca>marcas=saMarca.readAll();
				gui.update(Evento.LISTAR_MARCAS, marcas);
				break;
			}
			case Evento.MARCA_PORID:{
				SAMarca saMarca = FactoriaNegocio.getInstance().generaSAMarca();
				TMarca marca=saMarca.read((int)datos);
				if(marca==null){
					gui.update(Evento.KO, null);
				}
				else{
					gui.update(Evento.OK, marca);
				}
			
				break;
			}
			case Evento.MODIFICAR_MARCA:{
				SAMarca saMarca = FactoriaNegocio.getInstance().generaSAMarca();
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
				SAProducto saProducto= FactoriaNegocio.getInstance().generaSAProducto();
				int res=saProducto.create((TProducto) datos);
				if(res>=0){
					gui.update(Evento.OK,res);
				}
				else{
					gui.update(Evento.KO, null);
				}
				break;
			}
			case Evento.BAJA_PRODUCTO:{
				SAProducto saProducto= FactoriaNegocio.getInstance().generaSAProducto();
				int res=saProducto.delete(((TProducto) datos).getIdProducto());
				if(res>=0){
					gui.update(Evento.OK,res);
				}
				else{
					gui.update(Evento.KO, null);
				}
				break;
			}
			case Evento.LISTAR_PRODUCTOS:{
				SAProducto saProducto= FactoriaNegocio.getInstance().generaSAProducto();
				Collection<TProducto>productos=saProducto.readAll();
				gui.update(Evento.LISTAR_PRODUCTOS, productos);
				break;
			}
			case Evento.MODIFICAR_PRODUCTO:{
				SAProducto saProducto= FactoriaNegocio.getInstance().generaSAProducto();
				int res=saProducto.update((TProducto)datos);
				if(res>=0){
					gui.update(Evento.OK,res);
				}
				else{
					gui.update(Evento.KO, null);
				}
				break;
			}
			case Evento.PRODUCTO_POR_ID:{
				SAProducto saProducto= FactoriaNegocio.getInstance().generaSAProducto();
				TProducto producto=saProducto.read((Integer)datos);
				if(producto.getIdMarca() != -1)
					gui.update(Evento.OK, producto);
				else
					gui.update(Evento.KO, producto);
				break;
			}
			
			/*
			 * PROVEEDORES
			 */
			case Evento.ALTA_PROVEEDOR:{
				SAProveedores saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
				int res=saProveedor.create((TProveedor)datos);
				if(res>0){
					gui.update(Evento.OK, res);
				}
				else{
					gui.update(Evento.KO, null);
				}
				break;
            }
            case Evento.BAJA_PROVEEDOR:{
            	SAProveedores saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
                int res = saProveedor.delete(((TProveedor)datos).getId());

                if(res > 0)
                    gui.update(Evento.OK, res);
                else
                    gui.update(Evento.KO, null);
                break;
            }
            case Evento.LISTAR_PROVEEDORES:{
            	SAProveedores saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
                Collection<TProveedor>proveedores = saProveedor.readAll();
                if(proveedores!= null && proveedores instanceof ArrayList){
                	gui.update(Evento.OK, proveedores);
                }else{
                	gui.update(Evento.KO, null);
                }
                break;
            }
            case Evento.PROVEEDOR_POR_ID:{
            	SAProveedores saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
            	    TProveedor proveedor = saProveedor.read((int)datos);
            	    int res= proveedor==null ? Evento.KO : Evento.OK;
            	    gui.update(res, proveedor);
                break;
            }
            case Evento.MODIFICAR_PROVEEDOR:{
            	SAProveedores saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
                	int res = saProveedor.update((TProveedor)datos);
                	if( res>0){
                		gui.update(Evento.OK, datos);
                	}else{
                		gui.update(Evento.KO, null);
                	}
                break;
            }
            case Evento.LISTA_MARCAS_DE_PROVEEDOR:{
            	SAMarca saMarca = FactoriaNegocio.getInstance().generaSAMarca();
            	Collection<TMarca> res=saMarca.readByProveedor((int)datos);
            	if(res.size()>0) {
            		gui.update(Evento.OK, res);
            	}else {
            		gui.update(Evento.KO, res);
            	}
            	break;
            }
            case Evento.ADD_MARCA_TO_PROVEEDOR:{
            	SAProveedores saProveedor=FactoriaNegocio.getInstance().generaSAProveedor();
            	int res=saProveedor.addMarcaToProveedor((TProveedorMarca)datos);
            	if(res>0) {
            		gui.update(Evento.OK, res);
            	}else{
            		gui.update(Evento.KO, res);
            	}
            	break;
            }
            /*
			 * CLIENTES
			 */
			case Evento.ALTA_CLIENTE:{
				SAClientes saCliente = FactoriaNegocio.getInstance().generaSAClientes();
				 int res= saCliente.create((TCliente)datos);
				 if(res>0){
					gui.update(Evento.RES_ALTA_CLIENTE_OK, new Integer(res));
				}
				else{
					gui.update(Evento.RES_ALTA_CLIENTE_KO, null);
				}
			
				break;
            }
            case Evento.BAJA_CLIENTE:{
            	SAClientes saCliente = FactoriaNegocio.getInstance().generaSAClientes();
                  int idcliente=(int) datos;
                  int res = saCliente.delete(idcliente);
                  if(res > 0)
                    gui.update(Evento.RES_BAJA_CLIENTE_OK, new Integer(res));
                else
                    gui.update(Evento.RES_BAJA_CLIENTE_KO, null);
                break;
            }
            case Evento.LISTAR_CLIENTES:{
              SAClientes saCliente = FactoriaNegocio.getInstance().generaSAClientes();
              Collection<TCliente>clientes= saCliente.readAll();
              gui.update(Evento.LISTAR_CLIENTES, clientes);
               break;
            }
            case Evento.CLIENTE_POR_ID:{
            	   SAClientes saCliente = FactoriaNegocio.getInstance().generaSAClientes();
            	   TCliente cliente = saCliente.read((int)datos);
            	   int res= cliente==null ? Evento.RES_CLIENTE_POR_ID_KO : Evento.RES_CLIENTE_POR_ID_OK;
            	   gui.update(res, cliente);
                break;
            }
            case Evento.MODIFICAR_CLIENTE:{
            	    SAClientes saCliente = FactoriaNegocio.getInstance().generaSAClientes();
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
            	SAEmpleado saEmpleado= FactoriaNegocio.getInstance().generaSAEmpleado();
            	int res = saEmpleado.create((TEmpleado)datos);
            	if(res>0) {
            		gui.update(Evento.OK, res);
            	}else {
            		gui.update(Evento.KO, null);
            	}
            	break;
            }
            case Evento.BAJA_EMPLEADO:{
            	SAEmpleado saEmpleado= FactoriaNegocio.getInstance().generaSAEmpleado();
            	int res = saEmpleado.delete((int)datos);
            	if(res==(int)datos) {
            		gui.update(Evento.OK, res);
            	}else {
            		gui.update(Evento.KO, null);
            	}
            	break;
            }
            case Evento.MODIFICAR_EMPLEADO:{
            	SAEmpleado saEmpleado= FactoriaNegocio.getInstance().generaSAEmpleado();
            	int res = saEmpleado.update((TEmpleado)datos);
            	if(res>0) {
            		gui.update(Evento.OK, (TEmpleado)datos);
            	}else {
            		gui.update(Evento.KO, null);
            	}
            	break;
            }
            case Evento.EMPLEADO_POR_ID:{
            	SAEmpleado saEmpleado= FactoriaNegocio.getInstance().generaSAEmpleado();
            	TEmpleado empl = saEmpleado.read((int)datos);
	         	int res= empl==null ? Evento.KO : Evento.OK;
	         	gui.update(res, empl);
	        	break;
            }
            case Evento.LISTAR_EMPLEADO:{
            	SAEmpleado saEmpleado= FactoriaNegocio.getInstance().generaSAEmpleado();
            	Collection<TEmpleado> lista = saEmpleado.readAll();
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
            	SAVentas saVenta= FactoriaNegocio.getInstance().generaSAVentas();
            	int res=saVenta.create((TVenta)datos);
            	if(res>0){
            		gui.update(Evento.RES_ABRIR_VENTA_OK, saVenta.read(res));
            	}
            	else{
            		gui.update(Evento.RES_ABRIR_VENTA_KO, null);
            	}
            	break;
            }
            case Evento.CERRAR_VENTA:{
            	SAVentas saVenta= FactoriaNegocio.getInstance().generaSAVentas();
            	int res=saVenta.update((TVenta)datos,false);
            	if(res>0){
            		gui.update(Evento.RES_CERRAR_VENTA_OK, null);
            	}
            	else{
            		gui.update(Evento.RES_CERRAR_VENTA_KO, null);
            	}
            	break;
            }
            case Evento.VENTA_POR_ID:{
            	SAVentas saVenta= FactoriaNegocio.getInstance().generaSAVentas();
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
            	SAVentas saVenta= FactoriaNegocio.getInstance().generaSAVentas();
            	Collection<TVenta>ventas=saVenta.readAll();
				gui.update(Evento.LISTAR_VENTAS, ventas);
            	break;
            }
            case Evento.DEVOLUCION_VENTA:{
            	SAVentas saVenta= FactoriaNegocio.getInstance().generaSAVentas();
            	int res=saVenta.devolucionVenta((List<Integer>)datos);
            	if(res>0){
            		gui.update(Evento.RES_DEVOLUCION_VENTA_OK,res);
            	}
            	else{
            		gui.update(Evento.RES_DEVOLUCION_VENTA_KO,null);
            	}
            	break;
            }
            case Evento.VENTAS_DE_UN_CLIENTE:{
            	SAVentas saVenta= FactoriaNegocio.getInstance().generaSAVentas();
            	Collection<TVenta>ventas=saVenta.readByCliente((Integer)datos);
            	gui.update(Evento.VENTAS_DE_UN_CLIENTE, ventas);
            	break;
            }
            case Evento.VENTAS_DE_UN_EMPLEADO:{
            	SAVentas saVenta= FactoriaNegocio.getInstance().generaSAVentas();
            	Collection<TVenta>ventas=saVenta.readByEmpleado((Integer)datos);
            	gui.update(Evento.VENTAS_DE_UN_EMPLEADO, ventas);
            	break;
            }

		}
	}
	// esto hay que quitarlo
	public void setGUI(GUI gui){
		this.gui=gui;
	}/* aun no lo borro pero es que no se puede usar
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
	public boolean devolverProd(Integer id, Integer cant) {
		return this.saProducto.devolverCantidad(id, cant);
	}
	public boolean restarProd(Integer id, Integer cant) {
		return this.saProducto.restarCantidad(id, cant);
	}*/
}
