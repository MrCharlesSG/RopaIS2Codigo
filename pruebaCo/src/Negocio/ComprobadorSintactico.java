
/**
 * 
 */
package Negocio;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author sagog
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ComprobadorSintactico {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param number
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean isPositive(int id) {
	
		return id>=0;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param name
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean isName(String name) {
		boolean nombre=false;
		if(name!=null && name.length()!=0){
			nombre=true;
			for(int i=0; i<name.length() && nombre; i++){
				nombre= !Character.isDigit(name.charAt(i));
			}
		}
		return nombre;
	
	}

	public static boolean isDNI(String dni) {
	
		return true;//dni.length()==9;
	}}