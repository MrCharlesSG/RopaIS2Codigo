package Negocio.Ventas;

import java.util.Collection;
import java.util.List;

public interface SAVentas {
	public int create(TVenta venta);
	
	public Collection<TVenta> readAll();

	public TVenta read(int id);
	
	public Collection<TVenta> readByEmpleado(int idEmpleado);
	
	public Collection<TVenta> readByCliente(int idCliente);
	
	public int update(TVenta venta);

	public int devolucionVenta(List<Integer> datos);
}
