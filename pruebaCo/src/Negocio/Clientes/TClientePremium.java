package Negocio.Clientes;

public class TClientePremium extends TCliente{

	public TClientePremium(boolean Active, String apellido1, String apellido2, String dni, int id, String nombre,
			int tlf, boolean es_premium) {
		super(Active, apellido1, apellido2, dni, id, nombre, tlf, es_premium);

	}

	public TClientePremium(int id, String nombre_apellidos, String dni, int tlf, int premium, int activo) {
		super(id,nombre_apellidos,dni,tlf,premium,activo);
	}

	@Override
	public double calcularPrecio(double precio) {
		
		return precio*0.9;
	}

}