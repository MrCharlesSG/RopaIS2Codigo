package Integracion.Ventas;

import java.util.Collection;

import Negocio.Ventas.TVenta;

public interface DAOVentas {
	public int create(TVenta venta);
	
	public Collection<TVenta> readAll();

	public TVenta read(int id);
	
	public Collection<TVenta> readByEmpleado(int idEmpleado);
	
	public Collection<TVenta> readByCliente(int idCliente);

	public int update(TVenta venta);
	
}
