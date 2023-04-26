package Negocio.Empleado;

import java.util.Collection;

public interface SAEmpleado {

	 public int create(TEmpleado empl);
	 public int delete(int id);
	 public TEmpleado read(int id);
	 public Collection<TEmpleado> readAll();
	 public int update(TEmpleado empl); //TODO parametros
	 public TEmpleado readByName(String dni);
}
