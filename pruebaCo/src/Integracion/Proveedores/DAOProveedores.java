package Integracion.Proveedores;


import java.util.Collection;

import Negocio.Proveedor.TProveedor;

public interface DAOProveedores {
    public int create(TProveedor tProv);
    public int delete(int id);
    public TProveedor read(int id);
    public Collection<TProveedor> readAll();
    public int update(TProveedor tProv);
    public TProveedor readByName(String nombre);
}
