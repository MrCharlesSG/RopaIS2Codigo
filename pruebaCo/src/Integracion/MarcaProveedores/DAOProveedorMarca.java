package Integracion.MarcaProveedores;

import java.util.Collection;

import Negocio.ProveedorMarca.TProveedorMarca;

public interface DAOProveedorMarca {

	public int create(TProveedorMarca pm);
	
	public TProveedorMarca read(TProveedorMarca pm);
	
	public int update(TProveedorMarca pm);
	
	public int delete(TProveedorMarca pm);
	
	public Collection<TProveedorMarca> readAll();
	 
	public Collection<TProveedorMarca> readProveedorMarcaPorProveedor(int proveedor);
	
	public Collection<TProveedorMarca> readProveedorMarcaPorMarca(int marca);
}
