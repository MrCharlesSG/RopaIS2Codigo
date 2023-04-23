package Negocio.Ventas;

import java.util.Collection;

public interface SAVentas {
	public int create(TVenta venta);

	
	public Collection<TVenta> readAll();

	
	public TVenta read(int id);

	
	public int update(TVenta venta);

	
	public int delete(int ID);

	
}
