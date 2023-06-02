package Negocio.Proveedor;

import java.util.ArrayList;

public class TProveedor {

	private boolean activo;
	private int idProv;
	private String nombre;
	
	public TProveedor(String nombre, int idProv, boolean activo){
		this.nombre = nombre;
		this.idProv = idProv;
		this.activo = activo;
	}
	
	public boolean getActivo(){
		return this.activo;
	}
	public int getId(){
		return this.idProv;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	public void setActivo(boolean a){
		this.activo = a;
	}
	public void setId(int id){
		this.idProv = id;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	@Override
	public String toString() {
	    StringBuilder buffer = new StringBuilder();
	    buffer.append(" ID: "+ idProv+ "|| Nombre: "+nombre);

	    
	    return buffer.toString();
	}
	
	@Override
	public boolean equals(Object anObject ) {
		TProveedor a = (TProveedor) anObject;
		return this.nombre.equals(a.getNombre()) &&
				
				this.getId()==a.getId() &&
				this.getActivo()==a.getActivo() ;
	
	}
	
}
