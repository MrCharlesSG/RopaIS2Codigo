// @author Dokt0r

package Negocio.Producto;

public class TProducto {

	private int cantidad;
	private String categoria;
	private int idMarca;
	private int idProducto;
	private String nombre;
	private int talla;
	
	public TProducto(int cantidad, String categoria, int idMarca, int idProducto, String nombre, int talla){
		this.cantidad = cantidad;
		this.categoria =categoria;
		this.idMarca = idMarca;
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.talla = talla;
	}
	
	public int getCantidad(){
		return this.cantidad;
	}
	
	public void setCantidad(int cant){
		this.cantidad =cant;
	}
	
	public String getCategoria(){
		return this.categoria;
	}
	
	public void setCategoria(String cat){
		this.categoria =cat;
	}
	
	public int getIdMarca(){
		return this.idMarca;
	}
	
	public void setIdMarca(int id){
		this.idMarca =id;
	}
	
	public int getIdProducto(){
		return this.idProducto;
	}
	
	public void setIdProducto(int id){
		this.idProducto =id;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nom){
		this.nombre = nom;
	}
	
	public int getTalla(){
		return this.talla;
	}
	
	public void setTalla(int t){
		this.talla = t;
	}
}
