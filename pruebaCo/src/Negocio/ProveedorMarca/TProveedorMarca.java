package Negocio.ProveedorMarca;

public class TProveedorMarca {
	private int idMarca;
	private int idProveedor;
	private boolean activo;
	
	public TProveedorMarca() {
		idMarca=-1;
		idProveedor=-1;
	}
	
	public TProveedorMarca( int idProveedor,int idMarca, boolean activo) {
		this.idMarca=idMarca;
		this.idProveedor=idProveedor;
		this.activo=activo;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
