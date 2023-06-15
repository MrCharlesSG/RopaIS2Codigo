package Negocio.Ventas;

import java.util.Collection;
import java.util.List;
 

import Integracion.Clientes.DAOClientes;
import Integracion.Empleados.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Producto.DAOProducto;
import Integracion.ProductosDeVenta.DAOProductosDeVenta;
import Integracion.Ventas.DAOVentas;
import Negocio.ComprobadorSintactico;
import Negocio.Clientes.TCliente;
import Negocio.Empleado.TEmpleado;
import Negocio.Producto.TProducto;
import Negocio.ProductosDeVenta.TCarrito;
import Negocio.ProductosDeVenta.TProductosDeVenta;


public class SAVentasIMP implements SAVentas {
	
	public SAVentasIMP(){
		
	}
	@Override
	public int abrir(TVenta venta) {
		// solo da de alta la venta sin donde aun no hay productos
		DAOVentas daoVentas;
		DAOClientes daoCliente;
		DAOEmpleado daoEmpleado;
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		daoCliente=FactoriaIntegracion.getInstance().generaDAOClientes();
		daoEmpleado=FactoriaIntegracion.getInstance().generaDAOEmpleado();
		int id=-1;
		
		TCliente cliente;
		cliente=daoCliente.read(venta.get_id_cliente());
		TEmpleado empleado;
		empleado=daoEmpleado.read(venta.get_id_empleado());
		
		if(cliente!=null&&cliente.getActivo()&&empleado!=null&&empleado.isActivo()){

				id=daoVentas.create(venta);
		}
		return id;
	}


	@Override
	public Collection<TVenta> readAll() {
		DAOVentas daoVentas;
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		return daoVentas.readAll();
	}

	@Override// la unica forma de acceder a toda la informacion de unaventa es con este read que devuelve el toa
	public TOAVenta read(int id) {
		TVenta venta=null;
		TOAVenta res=null;
		if(ComprobadorSintactico.isPositive(id)){
			DAOVentas daoVentas;
			daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
			venta=daoVentas.read(id);
			DAOProductosDeVenta daoPDV=FactoriaIntegracion.getInstance().generaDAOProductosDeVenta();
			Collection<TProductosDeVenta> productos;
			productos=daoPDV.productosVenta(id);
			res=new TOAVenta(venta,productos);
		}
		return res;
	}

	@Override
	public Collection<TVenta> readByEmpleado(int idEmpleado) {
		DAOVentas daoVentas;
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		if(ComprobadorSintactico.isPositive(idEmpleado))
			return daoVentas.readByEmpleado(idEmpleado);
		return null;
	}
	@Override
	public Collection<TVenta> readByCliente(int idCliente) {
		DAOVentas daoVentas;
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		if(ComprobadorSintactico.isPositive(idCliente))
			return daoVentas.readByCliente(idCliente);
		return null;
	}
	
	
// tengo que crear un update que me añade proctos o me los registra en la base de datos 
	// se le pasa un id de venta y un mapa con <id_prod,cantidad>

	@Override//lista con id venta 0 id prod 1 cantidad 2
	public int devolucionVenta(List<Integer> datos) {
		int id=-1;
		DAOProductosDeVenta daoPDV=FactoriaIntegracion.getInstance().generaDAOProductosDeVenta();
		Collection<TProductosDeVenta>productos=daoPDV.productosVenta(datos.get(0));
		TVenta venta;
		DAOVentas daoVentas;
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		venta=daoVentas.read(datos.get(0));
		if(venta==null)
			return -1;
		for(TProductosDeVenta tvp:productos) {
			if(tvp.getVenta()==datos.get(0)&&tvp.getProducto()==datos.get(1)&&datos.get(2)<=tvp.getCantidad()) {
				daoPDV.update(new TProductosDeVenta(datos.get(0),datos.get(1),tvp.getPrecio(),tvp.getCantidad()-datos.get(2)));
				//actualizo el precio total de la venta 
				double preciototal=0;
				
				preciototal=venta.get_precio()-datos.get(2)*tvp.getPrecio();
				venta.set_prec(preciototal);
				daoVentas.update(venta);
				//actualizo la cantidad restante de unidades 
				DAOProducto daoProd=FactoriaIntegracion.getInstance().generaDAOProducto();
				TProducto producto;
				producto=daoProd.read(datos.get(1));
				producto.setCantidad(producto.getCantidad()+datos.get(2));
				daoProd.update(producto);
			}
		}
		return id;
	}
	@Override
	public int cerrar(TCarrito datos) {
		DAOProducto daoProd;
		daoProd=FactoriaIntegracion.getInstance().generaDAOProducto();
		TProducto producto;
		int id=-1;
		TVenta venta;
		DAOVentas daoVentas;
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		venta=daoVentas.read(datos.getVenta());
		if(ComprobadorSintactico.isPositive(datos.getVenta())&&venta!=null){
				boolean cerrar=true;
				double preciototal=0;
				for(Integer ID: datos.getProductos().keySet()) {
					producto=daoProd.read(ID);
					if(producto==null||producto.getCantidad()< datos.getProductos().get(ID)) {
						// si no existe el producto o si no hay suficientes unidades
						cerrar=false;
						//no se puede cerrar la venta
					}
					if(producto !=null)preciototal+=producto.getPrecio()* datos.getProductos().get(ID);
				}
					if(cerrar) {
						// si todos lo sproductos son validos se añaden uno a uno
						
						DAOProductosDeVenta daoProdVenta;
						daoProdVenta=FactoriaIntegracion.getInstance().generaDAOProductosDeVenta();
						TProductosDeVenta Tpv;
						for(Integer ID: datos.getProductos().keySet()) {
							producto=daoProd.read(ID);
							producto.setCantidad(producto.getCantidad()-datos.getProductos().get(ID));
							daoProd.update(producto);
							Tpv=new TProductosDeVenta(datos.getVenta(),ID,producto.getPrecio(),datos.getProductos().get(ID));
							id=daoProdVenta.create(Tpv);
						}
						//Actualizo el precio total de la compra en la venta
						venta.set_prec(preciototal);
						daoVentas.update(venta);
						
						
					}
					else {
						daoVentas.cancelar(datos.getVenta());
					}
			}

			return id;
	}

}
