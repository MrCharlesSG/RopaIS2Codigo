package Negocio.ProductosDeVenta;

import java.util.Map;
import java.util.TreeMap;

public class TProductosDeVenta {
	private int idVenta;
	private Map<Integer,Integer> prod_unidades;//producto, nro Unidades
	private int precio;
	public TProductosDeVenta(){
		this.prod_unidades=new TreeMap<Integer,Integer>();
		precio=0;
	}
	
	public TProductosDeVenta(int id){
		this.prod_unidades=new TreeMap<Integer,Integer>();
	
		precio=0;
		this.idVenta=id;
	}
	public void add(int ID,int cantidad,int precio) {
	this.precio+=cantidad*precio;
		this.prod_unidades.put(ID,cantidad);
	}
	public void remuve(int ID,int cantidad,int precio) {
		this.precio-=cantidad*precio;
			//this.prod_unidades.re(ID,cantidad);
		}
	public int getVenta() {
		return this.idVenta;
	}
	public void setVenta(int id) {
		 this.idVenta=id;
	}

	public void setProductos(Map<Integer, Integer> carro) {
		for(Integer i:carro.keySet()) {
			this.prod_unidades.put(i,carro.get(i));
		}
		
	}
	public  Map<Integer,Integer> getProductos(){
		return this.prod_unidades;
	}
}
