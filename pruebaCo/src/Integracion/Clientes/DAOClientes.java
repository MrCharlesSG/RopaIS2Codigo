
package Integracion.Clientes;

import java.util.Collection;

import Negocio.Clientes.TCliente;


public interface DAOClientes {
	 
	public int create(TCliente cliente);

	 
	public Collection<TCliente> readAll();

 
	public TCliente read(int id);

	
	public int update(TCliente cliente);

 
	public int delete(int id);

	 
	public TCliente readByName(String dni);

	 
}