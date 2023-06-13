
package Negocio.Ventas;




public class TVenta {
	private int id;
	private int id_empleado;
	private int id_cliente;
	private double precio;
	private boolean activo;
	
	public TVenta(int id, int id_emp, int id_cl, double prec, boolean activo){
		this.id=id;
		this.id_empleado=id_emp;
		this.id_cliente=id_cl;
		this.precio=prec;
		this.activo=activo;
	}
	
	public void set_id(int id){
		this.id=id;
	}
	
	public void set_id_cliente(int id_client){
		this.id_cliente=id_client;
	}
	
	public void set_id_empl(int val){
		this.id_empleado=val;
	}
	
	public void set_prec(double pre){
		this.precio=pre;
	}
	
	public void aumentar_precio(double aumento){
		this.precio+=aumento;
	}
	public void set_activo(boolean es){
		this.activo=es;
	}
	
	public int get_id(){
		return this.id;
	}
	
	public int get_id_cliente(){
		return this.id_cliente;
	}
	
	public int get_id_empleado(){
		return this.id_empleado;
	}
	
	public double get_precio(){
		return this.precio;
	}

	public boolean get_activo(){
		return this.activo;
	}
	
	@Override
	public boolean equals(Object anObject ) {
		TVenta a = (TVenta) anObject;
		return this.id==a.get_id() &&
				this.id_cliente==a.get_id_cliente() &&
				this.id_empleado == a.get_id_empleado()&&
				this.precio == a.get_precio()&&
				this.activo == a.get_activo()
				;
	}
}