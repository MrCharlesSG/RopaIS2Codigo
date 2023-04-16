<<<<<<< HEAD
package Negocio.Clientes;

import java.util.Collection;

public interface SAClientes {
	public int create(TCliente cliente);

	
	public Collection<TCliente> readAll();

	
	public TCliente read(int id);

	
	public int update(TCliente cliente);

	
	public int delete(int ID);

	public TCliente readByName(String dni);
	
}
=======
package Negocio.Clientes;

import java.util.Collection;

public interface SAClientes {
	public int create(TCliente cliente);

	
	public Collection<TCliente> readAll();

	
	public TCliente read(int id);

	
	public int update(TCliente cliente);

	
	public int delete(int ID);

	public TCliente readByName(String dni);
	
}
>>>>>>> branch 'main' of https://github.com/MrCharlesSG/RopaIS2Codigo.git
