package Negocio.Ventas;

import java.util.Collection;

public interface DAOVentas {
	public int create(TVenta venta);

	
	public Collection<TVenta> readAll();

	
	public TVenta read(int id);

	
	public int update(TVenta venta);

	
	public int delete(int ID);
}
