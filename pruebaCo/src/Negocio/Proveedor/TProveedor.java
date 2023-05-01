package Negocio.Proveedor;

import java.util.ArrayList;

public class TProveedor {

	private boolean activo;
	private int idProv;
	private ArrayList<Integer> marcas;
	private String nombre;
	
	public TProveedor(String nombre, int idProv, ArrayList<Integer> marcas, boolean activo){
		this.nombre = nombre;
		this.idProv = idProv;
		this.marcas = marcas;
		this.activo = activo;
	}
	
	public boolean getActivo(){
		return this.activo;
	}
	public int getId(){
		return this.idProv;
	}
	public ArrayList<Integer> getMarca(){
		return this.marcas;
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
	public void setMarca(ArrayList<Integer> m){
		this.marcas = m;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	@Override
	public String toString() {
	    StringBuilder buffer = new StringBuilder();
	    buffer.append(" ID: "+ idProv+ "|| Nombre: "+nombre);
	    int i=1;
	    for(Integer m:marcas){
	    	buffer.append("|| Marca "+i+": "+m);
	    }
	    return buffer.toString();
	}
	
	@Override
	public boolean equals(Object anObject ) {
		TProveedor a = (TProveedor) anObject;
		return this.nombre.equals(a.getNombre()) &&
				
				this.getId()==a.getId() &&
				this.getActivo()==a.getActivo() &&
				this.marcas.equals(a.getMarca());
	
	}
	
}
