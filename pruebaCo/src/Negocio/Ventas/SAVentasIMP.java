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

public class SAVentasIMP implements SAVentas {
	private DAOVentas daoVentas;
	private DAOEmpleado daoEmpleados;
	private DAOClientes daoClientes;
	public SAVentasIMP(){
		daoVentas=FactoriaIntegracion.getInstance().generaDAOVentas();
		daoEmpleados=FactoriaIntegracion.getInstance().generaDAOEmpleado();
		daoClientes=FactoriaIntegracion.getInstance().generaDAOClientes();
	}
	@Override
	public int create(TVenta venta) {
		int id=-1;
		if(esValida(venta)){
			TVenta leido=daoVentas.read(venta.get_id());
			if(leido==null||!leido.get_activo())
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
		TCliente cliente=daoClientes.read(venta.get_id_cliente());
		TEmpleado empleado=daoEmpleados.read(venta.get_id_empleado());
		
		return ComprobadorSintactico.isPositive(venta.get_id())&&ComprobadorSintactico.isPositive(venta.get_id_cliente())&&
				ComprobadorSintactico.isPositive(venta.get_id_empleado())&&empleado!=null&&cliente!=null;
	}


}
