<<<<<<< HEAD
package Negocio.Clientes;

public abstract class TCliente {
	private boolean Active;
	private String Apellido1;
	private String Apellido2;
	private String DNI;
	private int IDCliente;
	private String Nombre;
	private int Telefono;
	private boolean Premiumbool;
	
	public TCliente(boolean Active,String apellido1, String apellido2, String dni, int id,String nombre, int tlf, boolean es_premium){
		this.Active=Active;
		this.Apellido1=apellido1;
		this.Apellido2=apellido2;
		this.DNI=dni;
		this.IDCliente=id;
		this.Nombre=nombre;
		this.Telefono=tlf;
		this.Premiumbool=es_premium;
	}
	public TCliente(int id,String nombre_apellidos,String dni,int tlf,int es_premium,int activo){
		if(activo==1)
			this.Active=true;
		else
			this.Active=false;
		String datos[];
		datos=nombre_apellidos.split(" ");
		
		this.Apellido1=datos[1];
		this.Apellido2=datos[2];
		this.Nombre=datos[0];
		this.DNI=dni;
		this.IDCliente=id;
		this.Telefono=tlf;
		if(es_premium==1)
			this.Premiumbool=true;
		else
			this.Premiumbool=false;
	}
	
	public abstract double calcularPrecio(double precio);
	
	public boolean getActivo(){
		return this.Active;
	}
	
	public String getApellido1(){
		return this.Apellido1;
	}
	
	public String getApellido2(){
		return this.Apellido2;
	}
	
	public String getDNI(){
		return this.DNI;
	}
	
	public int getID(){
		return this.IDCliente;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public boolean getPremium(){
		return this.Premiumbool;
	}
	
	public int getTelefono(){
		return this.Telefono;
	}
	
	public void setActivo(boolean activo){
		this.Active=activo;
	}
	
	public void setApellido1(String apellido1){
		this.Apellido1=apellido1;
	}
	
	public void setApellido2(String apellido2){
		this.Apellido2=apellido2;
	}
	
	public void setDNI(String dni){
		this.DNI=dni;
	}
	
	public void setID(int id){
		this.IDCliente=id;
	}
	
	public void setNombre(String nombre){
		this.Nombre=nombre;
	}
	
	public void setPremium(boolean premium){
		this.Premiumbool=premium;
	}
	
	public void setTelefono(int tlf){
		this.Telefono=tlf;
	}
	
	
}
=======
package Negocio.Clientes;

public abstract class TCliente {
	private boolean Active;
	private String Apellido1;
	private String Apellido2;
	private String DNI;
	private int IDCliente;
	private String Nombre;
	private int Telefono;
	private boolean Premiumbool;
	
	public TCliente(boolean Active,String apellido1, String apellido2, String dni, int id,String nombre, int tlf, boolean es_premium){
		this.Active=Active;
		this.Apellido1=apellido1;
		this.Apellido2=apellido2;
		this.DNI=dni;
		this.IDCliente=id;
		this.Nombre=nombre;
		this.Telefono=tlf;
		this.Premiumbool=es_premium;
	}
	public TCliente(int id,String nombre_apellidos,String dni,int tlf,int es_premium,int activo){
		if(activo==1)
			this.Active=true;
		else
			this.Active=false;
		String datos[];
		datos=nombre_apellidos.split(" ");
		
		this.Apellido1=datos[1];
		this.Apellido2=datos[2];
		this.Nombre=datos[0];
		this.DNI=dni;
		this.IDCliente=id;
		this.Telefono=tlf;
		if(es_premium==1)
			this.Premiumbool=true;
		else
			this.Premiumbool=false;
	}
	
	public abstract double calcularPrecio(double precio);
	
	public boolean getActivo(){
		return this.Active;
	}
	
	public String getApellido1(){
		return this.Apellido1;
	}
	
	public String getApellido2(){
		return this.Apellido2;
	}
	
	public String getDNI(){
		return this.DNI;
	}
	
	public int getID(){
		return this.IDCliente;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public boolean getPremium(){
		return this.Premiumbool;
	}
	
	public int getTelefono(){
		return this.Telefono;
	}
	
	public void setActivo(boolean activo){
		this.Active=activo;
	}
	
	public void setApellido1(String apellido1){
		this.Apellido1=apellido1;
	}
	
	public void setApellido2(String apellido2){
		this.Apellido2=apellido2;
	}
	
	public void setDNI(String dni){
		this.DNI=dni;
	}
	
	public void setID(int id){
		this.IDCliente=id;
	}
	
	public void setNombre(String nombre){
		this.Nombre=nombre;
	}
	
	public void setPremium(boolean premium){
		this.Premiumbool=premium;
	}
	
	public void setTelefono(int tlf){
		this.Telefono=tlf;
	}
	
	
}
>>>>>>> branch 'main' of https://github.com/MrCharlesSG/RopaIS2Codigo.git
