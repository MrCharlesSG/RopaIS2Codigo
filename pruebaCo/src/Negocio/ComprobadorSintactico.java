<<<<<<< HEAD
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
		
		return !name.contains(":")&&!(name.length()==0);
	
	}

	public static boolean isDNI(String dni) {//yo quitaria lo de dni
		// TODO Auto-generated method stub
		return true;
	}
=======
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
		
		return !name.contains(":")&&!(name.length()==0);
	
	}

	public static boolean isDNI(String dni) {//yo quitaria lo de dni
		// TODO Auto-generated method stub
		return true;
	}
>>>>>>> branch 'main' of https://github.com/MrCharlesSG/RopaIS2Codigo.git
}