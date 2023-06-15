package Presentacion.Controlador;
/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author sagog
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class Controlador {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private static Controlador controlador;
	
	public static Controlador getInstancia() {
		if(controlador==null){
			controlador= new ControladorIMP();
		}
		return controlador;
	}
	public abstract void accion(int evento,Object datos) ;
}