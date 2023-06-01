//@author Dokt0r

package Integracion.Producto;

import java.util.Collection;

import Negocio.Producto.TProducto;

public interface DAOProducto {
	public int create(TProducto Tprod);
	public int delete(int id);
	public TProducto read(int id);
	public Collection<TProducto> readAll();
	public TProducto readByName(String name);
	public int update(TProducto Tprod);
	Collection<TProducto> readByMarca(int iD);
}
