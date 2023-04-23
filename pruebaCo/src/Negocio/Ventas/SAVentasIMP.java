package Negocio.Ventas;

import java.util.Collection;

import Integracion.Clientes.DAOClientes;
import Integracion.Empleados.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Negocio.ComprobadorSintactico;
import Negocio.Clientes.TCliente;
import Negocio.MarcaNegocio.TMarca;

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
			TCliente cliente=
			if(leido==null||!leido.get_activo())
				id=daoVentas.create(venta);
			}
		return id;
	}


	@Override
	public Collection<TVenta> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TVenta read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(TVenta venta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}
	private boolean esValida(TVenta venta) {
		
		return ComprobadorSintactico.isPositive(venta.get_id())&&ComprobadorSintactico.isPositive(venta.get_id_cliente())&&
				ComprobadorSintactico.isPositive(venta.get_id_empleado());
	}

}
