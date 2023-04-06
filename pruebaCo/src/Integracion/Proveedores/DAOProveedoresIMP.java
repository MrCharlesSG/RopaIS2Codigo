package Integracion.Proveedores;


import java.util.Collection;

import Negocio.Proveedor.TProveedor;

public class DAOProveedoresIMP implements DAOProveedores{
    public int create(TProveedor tProv){
        return 0;};
    public int delete(int id){
        return id;};
    public TProveedor read(int id){
        return null;};
    public Collection<TProveedor> readAll(){
        return null;};
    public int update(TProveedor tProv){
        return 0;};
    public TProveedor readByName(String nombre){
            return null;
    }
}
