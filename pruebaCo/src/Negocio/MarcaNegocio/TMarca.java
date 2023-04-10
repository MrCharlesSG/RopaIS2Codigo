/**
 * 
 */
package Negocio.MarcaNegocio;

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
	private int cantidad;

	private boolean activo;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @param Activo 
	 * @param ID 
	 * @param nombre2 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca(String nombre, int ID, int cantidad, int activo) {
		this.Nombre=nombre;
		this.IDMarca=ID;
		this.activo=false;
		this.cantidad = cantidad;
		if(activo==1)
			this.activo=true;
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
	
	public int getCantidad(){
		return this.cantidad;
	}
}