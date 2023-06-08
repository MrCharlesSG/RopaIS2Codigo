package Negocio.Ventas;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import Negocio.ProductosDeVenta.TProductosDeVenta;


public class SAVentasIMP implements SAVentas {
	
	public SAVentasIMP(){
		
	}
	@Override
	public int create(TVenta venta) {
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
			
			TVenta leido=daoVentas.read(venta.get_id());
			if(leido==null)
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

	@Override
	public TVenta read(int id) {
		TVenta venta=null;
		if(ComprobadorSintactico.isPositive(id)){
			DAOVentas daoVentas;
			daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
			venta=daoVentas.read(id);
		}
		return venta;
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

	@Override
	public int devolucionVenta(List<Integer> datos) {
		int id=-1;
		TVenta venta=read(datos.get(0));
		Map<Integer, Integer> mapa = venta.get_map();
		DAOVentas daoVentas;
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		if(mapa.containsKey(datos.get(1))&&mapa.get(datos.get(1))>=datos.get(2)){
			mapa.put(datos.get(1),mapa.get(datos.get(1))- datos.get(2));
			if(mapa.get(datos.get(1))==0)
				mapa.remove(datos.get(1));
			venta.setProductos(mapa);
			this.update(venta,true);
			id=venta.get_id();
		}
		return id;
	}
	@Override
	public int update(TProductosDeVenta datos, boolean devol) {
		DAOProducto daoProd;
		daoProd=FactoriaIntegracion.getInstance().generaDAOProducto();
		TProducto producto;
		int id=-1;
		if(ComprobadorSintactico.isPositive(datos.getVenta())){
			if(!devol) {
				
				boolean cerrar=true;
				for(Integer ID: datos.getProductos().keySet()) {
					producto=daoProd.read(ID);
					if(producto==null||producto.getCantidad()< datos.getProductos().get(ID)) {
						// si no existe el producto o si no hay suficientes unidades
						cerrar=false;
						//no se puede cerrar la venta
					}
				}
					if(cerrar) {
						DAOProductosDeVenta daoProdVenta;
						daoProdVenta=FactoriaIntegracion.getInstance().generaDAOProductosDeVenta();
						id=daoProdVenta.create(datos);
					}
					
				
			}
			
		}
			return id;
	}

}
