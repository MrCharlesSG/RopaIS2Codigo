package Integracion.MarcaProveedores;

import java.util.Collection;

import Negocio.ProveedorMarca.TProveedorMarca;

public interface DAOMarcaProveedores {

	public int create(TProveedorMarca pm);
	
	public Collection<TProveedorMarca> readAll();
	 
	public Collection<TProveedorMarca> readProveedorMarcaPorProveedor(int proveedor);
	
	public Collection<TProveedorMarca> readProveedorMarcaPorMarca(int marca);
}
