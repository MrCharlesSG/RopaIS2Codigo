/**
 * 
 */
package Negocio.MarcaNegocio;

import java.util.ArrayList;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author sagog
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TMarca {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private int IDMarca;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private String Nombre;
	private boolean activo;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @param Activo 
	 * @param ID 
	 * @param nombre2 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca(String nombre, int ID, int cantidad, boolean activo) {
		this.Nombre=nombre;
		this.IDMarca=ID;
		this.activo=false;
		this.activo=activo;
	}
	
	public TMarca(String nombre, int ID, boolean activo) {
		this.Nombre=nombre;
		this.IDMarca=ID;
		this.activo=false;
		this.activo=activo;
	}


	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public String getNombre() {
		return Nombre;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int getID() {
		return this.IDMarca;
	
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param nombre
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setNombre(String nombre) {
		this.Nombre=nombre;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param ID
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setID(int ID) {
		this.IDMarca=ID;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param activo
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setActivo(Boolean activo) {
		this.activo=activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Boolean getActivo() {
		return activo;
	}
	
	
	@Override
	public boolean equals(Object anObject ) {
		TMarca a = (TMarca) anObject;
		return this.activo==a.getActivo()&&
				this.IDMarca==a.getID()&&
				this.Nombre.equalsIgnoreCase(a.getNombre())
				;
	
	}
	
	@Override
	public String toString() {
	    StringBuilder buffer = new StringBuilder();
	    buffer.append(" ID: "+ IDMarca+ "|| Nombre: "+Nombre);

	    
	    return buffer.toString();
	}
}