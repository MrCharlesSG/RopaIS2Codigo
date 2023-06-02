package Negocio.Empleado;

public class TEmpleadoTP extends TEmpleado {
	int horas;
	int precio_hora;

	public TEmpleadoTP(String nombre, String apellido1, String apellido2, String DNI, int tfno, int ID, boolean activo, int horas, int precio_hora) {
		super(nombre, apellido1, apellido2, DNI, tfno, ID, false, activo);
		this.horas=horas;
		this.precio_hora=precio_hora;
	}
	
	
	@Override
	public int get_horas(){
		return this.horas;
	}
	
	@Override
	public int get_precio_hora(){
		return this.precio_hora;
	}
}
