package Integracion.Proveedores;

import java.util.ArrayList;

import Negocio.Proveedor.TProveedor;

public interface DAOProveedores {
	public int create(TProveedor tProv);
	public int delete(int id);
	public int read(int id);
	public ArrayList<Integer> readAll();
	public int update(TProveedor tProv);
}
