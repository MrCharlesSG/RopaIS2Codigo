package Negocio.Ventas;

import java.util.HashMap;
import java.util.Map;


public class TVenta {
	private int id;
	private int id_empleado;
	private int id_cliente;
	private double precio;
	private int contador_productos;
	private Map<Integer,Integer> prod_unidades;
	private boolean activo;
	
	public TVenta(int id, int id_emp, int id_cl, double prec, int cont, boolean activo){
		this.id=id;
		this.id_empleado=id_emp;
		this.id_cliente=id_cl;
		this.precio=prec;
		this.contador_productos=cont;
		this.activo=activo;
		this.prod_unidades= new HashMap<Integer,Integer>();
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
	
	public void set_contador_productos(int val){
		this.contador_productos=val;
	}
	
	public void aumentar_contador_productos(int val){
		this.contador_productos+=val;
	}
	
	public void aniadir_prod_ud(int id, int ud){
		this.prod_unidades.put(id, ud);
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
	
	public int get_contador(){
		return this.contador_productos;
	}
	
	public Map<Integer,Integer> get_map(){
		return this.prod_unidades;
	}
	
	public boolean get_activo(){
		return this.activo;
	}
	
}
