/**
 * 
 */
package Presentacion.FactoriaPresentacion;

import java.util.Set;

import Presentacion.MarcaPresentacion.GUIAltaMarca;
import Presentacion.MarcaPresentacion.GUIBajaMarca;
import Presentacion.MarcaPresentacion.GUIListarMarcas;
import Presentacion.MarcaPresentacion.GUIMarcaPorID;
import Presentacion.MarcaPresentacion.GUIModificarMarca;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public abstract class FactoriaPresentacion {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private static FactoriaPresentacion instance;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract void generaGUI();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static FactoriaPresentacion getInstance() {
		if(instance==null)
			instance=new FactoriaPresentacionImp();
		
		return instance;
	}
	public abstract GUIAltaMarca generaGUIAltaMarca();
	public abstract GUIBajaMarca generaGUIBajaMarca() ;
	public  abstract GUIListarMarcas generaGUIListarMarcas();
	public abstract GUIMarcaPorID generaGUIMarcaPorID();
	public abstract GUIModificarMarca generaGUIModificarMarca();
}