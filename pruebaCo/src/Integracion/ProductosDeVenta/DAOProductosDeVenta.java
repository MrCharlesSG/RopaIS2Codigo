package Integracion.ProductosDeVenta;

import Negocio.ProductosDeVenta.TProductosDeVenta;

public interface DAOProductosDeVenta {
	public int create(TProductosDeVenta TProdVenta);
	public int delete(TProductosDeVenta TProdVenta);
	public TProductosDeVenta productosVenta(int id);
}
