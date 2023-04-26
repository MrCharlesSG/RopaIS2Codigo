
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
	
		boolean valido=false;
		if(dni.length()==9){
			//066 553 87s
			valido=true;
			for(int i=0; i<dni.length()-1&& valido; i++){
				valido= Character.isDigit(dni.charAt(i));
			}
			if(valido)// si despues del bucle sigue siendo valido comprobamossi su ultimo caracter es una letra
				valido=!Character.isDigit(dni.charAt(dni.length()-1));
		}
		return valido;
		
	}
	}