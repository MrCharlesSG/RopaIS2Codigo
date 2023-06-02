
package Negocio.Clientes;

public class TClientePremium extends TCliente{
	private int codigo_postal;
	private double descuento=0.9;

	public TClientePremium(boolean Active, String apellido1, String apellido2, String dni, int id, String nombre,
			int tlf, boolean es_premium, int codigo) {
		super(Active, apellido1, apellido2, dni, id, nombre, tlf, es_premium);
		this.codigo_postal=codigo;

	}

	public TClientePremium(int id, String nombre_apellidos, String dni, int tlf, String premium, int activo, int codigo) {
		super(id,nombre_apellidos,dni,tlf,premium,activo);
		this.codigo_postal=codigo;
	}

	@Override
	public double calcularPrecio(double precio) {
		
		return precio*descuento;
	}
	
	
	@Override
	public int get_codigo(){
		return this.codigo_postal;
	}
	@Override
	public int get_descuento(){
		double porc=this.descuento*100;
		return 100-(int)porc;
	}
	
}
