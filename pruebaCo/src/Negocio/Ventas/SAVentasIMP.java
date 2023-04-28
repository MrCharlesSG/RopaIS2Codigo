package Negocio.Ventas;

import java.util.Collection;

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

		return Controlador.getInstancia().clienteExiste(venta.get_id_cliente())&&Controlador.getInstancia().empleadoExiste(venta.get_id_empleado())&&
				ComprobadorSintactico.isPositive(venta.get_id())&&ComprobadorSintactico.isPositive(venta.get_id_cliente())&&
				ComprobadorSintactico.isPositive(venta.get_id_empleado());
	}
// tengo que crear un update que me añade proctos o me los registra en la base de datos 
	// se le pasa un id de venta y un mapa con <id_prod,cantidad>
	@Override
	public int update(TVenta venta) {
		int id=-1;
		if(esValida(venta)&&Controlador.getInstancia().productosExisten(venta.get_map())){
			id=daoVentas.update(venta);	
		}
		return id;
	}

}
