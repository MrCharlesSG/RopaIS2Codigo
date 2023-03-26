//@author Dokt0r

package Negocio.Producto;

import java.util.Collection;

import Negocio.Producto.TProducto;

public interface SAProducto {
	public int create(TProducto Tprod);
	public int delete(TProducto Tprod);
	public TProducto read(TProducto Tprod);
	public Collection<TProducto> readAll();
	public TProducto readByName(TProducto Tprod);
	public int update(TProducto Tprod);
}
