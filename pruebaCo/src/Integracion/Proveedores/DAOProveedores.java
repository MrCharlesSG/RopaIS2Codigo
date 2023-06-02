package Integracion.Proveedores;


import java.util.Collection;

import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;

public interface DAOProveedores {
	

	static final String ARCHIVO="Proveedores.txt";
	
    public int create(TProveedor tProv);
    public int delete(int id);
    public TProveedor read(int id);
    public Collection<TProveedor> readAll();
    public int update(TProveedor tProv);
    public TProveedor readByName(String nombre);
	public Collection<TProveedor> readByMarca(int idMarca);
	public int addMarca(TProveedorMarca pm);
	public int deleteMarca(TProveedorMarca pm);
}
