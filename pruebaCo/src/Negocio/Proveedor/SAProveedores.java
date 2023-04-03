package Negocio.Proveedor;

import java.util.ArrayList;

import Negocio.Proveedor.TProveedor;

public interface SAProveedores {
	public int create(TProveedor tProv);
	public int delete(int id);
	public int read(int id);
	public ArrayList<Integer> readAll();
	public int update(TProveedor tProv);
}
