package Negocio.Empleado;

public abstract class TEmpleado {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String DNI;
	private int tfno;
	private int ID;
	private boolean activo;
	private boolean tiempoCompleto; 
	
	TEmpleado(String nombre, String apellido1, String apellido2, String DNI, int tfno, int ID, boolean tiempoCompleto, boolean activo){
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.DNI=DNI;
		this.tfno=tfno;
		this.ID=ID;
		this.activo=activo;
		this.tiempoCompleto=tiempoCompleto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public int getTfno() {
		return tfno;
	}

	public void setTfno(int tfno) {
		this.tfno = tfno;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isTiempoCompleto() {
		return tiempoCompleto;
	}

	public void setTiempoCompleto(boolean tiempoCompleto) {
		this.tiempoCompleto = tiempoCompleto;
	}
	
	@Override
	public String toString() {
	    StringBuilder buffer = new StringBuilder();
	    buffer.append("ID: "+ ID+" || Nombre: "+ nombre+ " || ");
	    if(tiempoCompleto) {
	    	buffer.append("Tiempo Completo");
	    }else {
	    	buffer.append("Tiempo Parcial");
	    }
	    return buffer.toString();
	}
	
	@Override
	public boolean equals(Object anObject ) {
		TEmpleado a = (TEmpleado) anObject;
		return this.apellido1.equals(a.getApellido1()) &&
				this.apellido2.equals(a.getApellido2()) &&
				this.activo==a.isActivo() &&
				this.tiempoCompleto==a.isTiempoCompleto() &&
				this.nombre.equals(a.getNombre()) &&
				this.DNI.equals(a.getDNI()) &&
				this.tfno==a.getTfno() &&
				this.ID == a.getID();
	
	}
	
}
