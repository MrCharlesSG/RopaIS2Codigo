package Negocio.Ventas;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import Integracion.Clientes.DAOClientes;
import Integracion.Empleados.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.Ventas.DAOVentas;
import Negocio.ComprobadorSintactico;
import Negocio.Clientes.TCliente;
import Negocio.Empleado.TEmpleado;
import Presentacion.Controlador.Controlador;

public class SAVentasIMP implements SAVentas {
	private DAOVentas daoVentas;
	
	public SAVentasIMP(){
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
	}
	@Override
	public int create(TVenta venta) {
		// solo da de alta la venta sin donde aun no hay productos
		int id=-1;
		if(esValida(venta)){
			TVenta leido=daoVentas.read(venta.get_id());
			if(leido==null)
				id=daoVentas.create(venta);
			}
		return id;
	}


	@Override
	public Collection<TVenta> readAll() {
		return daoVentas.readAll();
	}

	@Override
	public TVenta read(int id) {
		TVenta venta=null;
		if(ComprobadorSintactico.isPositive(id)){
			venta=daoVentas.read(id);
		}
		return venta;
	}

	@Override
	public Collection<TVenta> readByEmpleado(int idEmpleado) {
		if(ComprobadorSintactico.isPositive(idEmpleado))
			return daoVentas.readByEmpleado(idEmpleado);
		return null;
	}
	@Override
	public Collection<TVenta> readByCliente(int idCliente) {
		if(ComprobadorSintactico.isPositive(idCliente))
			return daoVentas.readByCliente(idCliente);
		return null;
	}
	
	private boolean esValida(TVenta venta) {

		return Controlador.getInstancia().clienteExiste(venta.get_id_cliente())&&Controlador.getInstancia().empleadoExiste(venta.get_id_empleado());
	}
// tengo que crear un update que me añade proctos o me los registra en la base de datos 
	// se le pasa un id de venta y un mapa con <id_prod,cantidad>
	@Override
	public int update(TVenta venta) {
		int id=-1;
		if(esValida(venta)&&Controlador.getInstancia().productosExisten(venta.get_map())){
			int contador=0;
			for(Integer i:venta.get_map().values()){
				contador+=i;
			}
			venta.set_contador_productos(contador);
			id=daoVentas.update(venta);	
		}
		return id;
	}
	@Override
	public int devolucionVenta(List<Integer> datos) {
		int id=-1;
		TVenta venta=read(datos.get(0));
		Map<Integer, Integer> mapa = venta.get_map();
		if(mapa.containsKey(datos.get(1))&&mapa.get(datos.get(1))>=datos.get(2)){
			mapa.put(datos.get(1),mapa.get(datos.get(1))- datos.get(2));
			if(mapa.get(datos.get(1))==0)
				mapa.remove(datos.get(1));
			venta.setProductos(mapa);
			this.update(venta);
			id=venta.get_id();
		}
		return id;
	}

}
