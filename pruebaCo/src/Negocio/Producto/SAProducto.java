//@author Dokt0r

package Negocio.Producto;

import java.util.Collection;


public interface SAProducto {
	public int create(TProducto Tprod);
	public int delete(int i);
	public TProducto read(int i);
	public Collection<TProducto> readAll();
	public TProducto readByName(String string);
	public int update(TProducto Tprod);
	boolean restarCantidad(int id, int cant);
	public Collection<TProducto> productosPorMarca(int id);
}
