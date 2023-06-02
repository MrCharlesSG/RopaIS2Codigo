package Negocio.Empleado;

public class TEmpleadoTC extends TEmpleado {
	int salario;
	int bonus;

	public TEmpleadoTC(String nombre, String apellido1, String apellido2, String DNI, int tfno, int ID, boolean activo, int salario, int bonus) {
		super(nombre, apellido1, apellido2, DNI, tfno, ID, true, activo);
		this.salario=salario;
		this.bonus=bonus;
	}
	
	@Override
	public int get_salario(){
		return this.salario;
	}
	
	@Override
	public int get_bonus(){
		return this.bonus;
	}
}
