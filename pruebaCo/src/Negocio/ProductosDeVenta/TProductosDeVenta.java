package Negocio.ProductosDeVenta;

public class TProductosDeVenta {
	private int idVenta;
	private int idProducto;
	private double precio;
	private int cantidad;
	
	public TProductosDeVenta(int idVenta,int idProd,double precio,int cantidad){
		this.cantidad=cantidad;
		this.idProducto=idProd;
		this.precio=precio;
		this.idVenta=idVenta;
	}
	
	public int getProducto() {
		return this.idProducto;
	}
	public void setProducto(int id) {
		 this.idProducto=id;
	}
	public int getVenta() {
		return this.idVenta;
	}
	public void setVenta(int id) {
		 this.idVenta=id;
	}
	public double getPrecio() {
		return this.precio;
	}
	public void setPrecio(double precio) {
		 this.precio=precio;
	}
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
