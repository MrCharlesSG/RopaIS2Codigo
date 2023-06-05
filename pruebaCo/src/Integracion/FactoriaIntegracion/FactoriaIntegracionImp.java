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
import Integracion.MarcaProveedores.DAOProveedorMarca;
import Integracion.MarcaProveedores.DAOProveedorMarcaIMP;
import Integracion.Producto.DAOProducto;
import Integracion.Producto.DAOProductoIMP;
import Integracion.Proveedores.DAOProveedores;
import Integracion.Proveedores.DAOProveedoresIMP;
import Integracion.Ventas.DAOVentas;
import Integracion.Ventas.DAOVentasIMP;


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

	@Override
	public DAOProveedorMarca generaDAOProveedorMarca() {
		return new DAOProveedorMarcaIMP();
	}
}