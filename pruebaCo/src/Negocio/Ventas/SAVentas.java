package Negocio.Ventas;

import java.util.Collection;
import java.util.List;

import Negocio.ProductosDeVenta.TProductosDeVenta;

public interface SAVentas {
	public int create(TVenta venta);
	
	public Collection<TVenta> readAll();

	public TVenta read(int id);
	
	public Collection<TVenta> readByEmpleado(int idEmpleado);
	
	public Collection<TVenta> readByCliente(int idCliente);
	
	public int update(TProductosDeVenta datos,boolean devol);

	public int devolucionVenta(List<Integer> datos);
}
