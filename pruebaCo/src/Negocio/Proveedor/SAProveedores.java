package Negocio.Proveedor;

import java.util.Collection;

import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;

public interface SAProveedores {
    public int create(TProveedor tProv);
    public int delete(int id);
    public TProveedor read(int id);
    public Collection<TProveedor> readAll();
    public int update(TProveedor tProv); //TODO parametros
    public TProveedor readByName(String nombre);
    public Collection<TProveedor> readByMarca(int id);
    public int addMarcaToProveedor(TProveedorMarca pm);
    public int deleteMarcaOfProveedor(TProveedorMarca pm);
}
