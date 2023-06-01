//@author Dokt0r

package Negocio.Producto;

import java.util.Collection;

import Negocio.Producto.TProducto;

public interface SAProducto {
	public int create(TProducto Tprod);
	public int delete(int i);
	public TProducto read(int i);
	public Collection<TProducto> readAll();
	public TProducto readByName(String string);
	public int update(TProducto Tprod);
}
