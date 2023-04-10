/**
 * 
 */
package Integracion.FactoriaIntegracion;

import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaIntegracion.DAOMarcaImp;
import Integracion.Producto.DAOProducto;
import Integracion.Producto.DAOProductoIMP;
import Integracion.Proveedores.DAOProveedores;
import Integracion.Proveedores.DAOProveedoresIMP;


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
}