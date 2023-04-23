/**
 * 
 */
package Integracion.FactoriaIntegracion;

import Integracion.Clientes.DAOClientes;
import Integracion.Clientes.DAOClientesIMP;
import Integracion.Empleados.DAOEmpleado;
import Integracion.Empleados.DAOEmpleadoImp;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaIntegracion.DAOMarcaImp;
import Integracion.Producto.DAOProducto;
import Integracion.Producto.DAOProductoIMP;
import Integracion.Proveedores.DAOProveedores;
import Integracion.Proveedores.DAOProveedoresIMP;
import Negocio.Ventas.DAOVentas;
import Negocio.Ventas.DAOVentasIMP;


public class FactoriaIntegracionImp extends FactoriaIntegracion {

	@Override
	public DAOMarca generaDAOMarca() {
		return new DAOMarcaImp();
	}

	@Override
	public DAOProducto generaDAOProducto() {
		return new DAOProductoIMP();
	}

	@Override
	public DAOProveedores generaDAOProveedor() {
		return new DAOProveedoresIMP();
	}

	@Override
	public DAOClientes generaDAOClientes() {
		return new DAOClientesIMP();
	}

	@Override
	public DAOEmpleado generaDAOEmpleado() {
		return new DAOEmpleadoImp();
	}

	@Override
	public DAOVentas generaDAOVentas() {
		
		return new DAOVentasIMP();
	}
}