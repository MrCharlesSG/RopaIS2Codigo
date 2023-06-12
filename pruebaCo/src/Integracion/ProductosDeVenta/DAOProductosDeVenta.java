package Integracion.ProductosDeVenta;

import java.util.Collection;

import Negocio.ProductosDeVenta.TProductosDeVenta;

public interface DAOProductosDeVenta {
	public int create(TProductosDeVenta TProdVenta);
	public int delete(TProductosDeVenta Tpv);
	public int update(TProductosDeVenta Tpv);
	public Collection<TProductosDeVenta> productosVenta(int id);
}
