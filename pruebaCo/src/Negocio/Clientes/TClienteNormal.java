
package Negocio.Clientes;

public class TClienteNormal extends TCliente{
	String poblacion;
	
	public TClienteNormal(boolean Active, String apellido1, String apellido2, String dni, int id, String nombre,
			int tlf, boolean es_premium, String poblacion) {
		super(Active, apellido1, apellido2, dni, id, nombre, tlf, es_premium);
		this.poblacion=poblacion;

	}

	public TClienteNormal(int id, String nombre_apellidos, String dni, int tlf, String premium, int activo, String poblacion) {
		super(id,nombre_apellidos,dni,tlf,premium,activo);
		this.poblacion=poblacion;
	}
	
	

	@Override
	public double calcularPrecio(double precio) {
		return precio;
	}
	
	@Override
	public String get_poblacion(){
		return this.poblacion;
	}
}