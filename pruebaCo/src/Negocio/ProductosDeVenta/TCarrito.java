package Negocio.ProductosDeVenta;

import java.util.Map;
import java.util.TreeMap;

public class TCarrito {
	private int idVenta;
	private Map<Integer,Integer> prod_unidades;//producto, nro Unidades

	public TCarrito(){
		this.prod_unidades=new TreeMap<Integer,Integer>();
		 
	}
	
	public TCarrito(int id){
		this.prod_unidades=new TreeMap<Integer,Integer>();
	 
		this.idVenta=id;
	}
	public void add(int ID,int cantidad,int precio) {
	
		this.prod_unidades.put(ID,cantidad);
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
