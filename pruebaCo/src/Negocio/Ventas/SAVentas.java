package Negocio.Ventas;

import java.util.Collection;
import java.util.List;

import Negocio.ProductosDeVenta.TCarrito;

public interface SAVentas {
	public int abrir(TVenta venta);
	
	public Collection<TVenta> readAll();

	public TOAVenta read(int id);
	
	public Collection<TVenta> readByEmpleado(int idEmpleado);
	
	public Collection<TVenta> readByCliente(int idCliente);
	
	public int cerrar(TCarrito datos);

	public int devolucionVenta(List<Integer> datos);
}
