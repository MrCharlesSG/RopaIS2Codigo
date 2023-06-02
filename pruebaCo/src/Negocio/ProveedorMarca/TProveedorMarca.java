package Negocio.ProveedorMarca;

public class TProveedorMarca {
	private int idMarca;
	private int idProveedor;
	
	public TProveedorMarca() {
		idMarca=-1;
		idProveedor=-1;
	}
	
	public TProveedorMarca( int idProveedor,int idMarca) {
		this.idMarca=idMarca;
		this.idProveedor=idProveedor;
	}
	
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
}
