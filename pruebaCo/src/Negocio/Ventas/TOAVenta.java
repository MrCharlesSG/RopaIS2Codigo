package Negocio.Ventas;

import java.util.ArrayList;
import java.util.Collection;

import Negocio.ProductosDeVenta.TProductosDeVenta;

public class TOAVenta {
	private TVenta venta;
	private Collection<TProductosDeVenta> productos;
	public TOAVenta(TVenta venta,Collection<TProductosDeVenta> productos) {
		this.venta=venta;
		this.productos=new ArrayList<TProductosDeVenta>();
		this.setProductos(productos);
	}
	public TVenta getVenta() {
		return venta;
	}
	public void setVenta(TVenta venta) {
		this.venta = venta;
	}
	public Collection<TProductosDeVenta> getProductos() {
		return productos;
	}
	public void setProductos(Collection<TProductosDeVenta> productos) {
		 for(TProductosDeVenta tpv:productos) {
			 productos.add(tpv);
		 }
	}
}
