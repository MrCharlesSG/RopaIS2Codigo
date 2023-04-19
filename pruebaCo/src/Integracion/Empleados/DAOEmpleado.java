package Integracion.Empleados;

import java.util.Collection;

import Negocio.Empleado.TEmpleado;

public interface DAOEmpleado {
	 
	public int create(TEmpleado empleado);

	 
	public Collection<TEmpleado> readAll();

 
	public TEmpleado read(int id);

	
	public int update(TEmpleado empleado);

 
	public int delete(TEmpleado empleado);

	 
	public TEmpleado readByName(String dni);

	 
}

