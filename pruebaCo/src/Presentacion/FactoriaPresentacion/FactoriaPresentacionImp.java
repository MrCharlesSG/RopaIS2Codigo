/**
 * 
 */
package Presentacion.FactoriaPresentacion;

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
public class FactoriaPresentacionImp extends FactoriaPresentacion {
	/** 
	* (non-Javadoc)
	* @see FactoriaPresentacion#generaGUI()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	//lo más probable es que no sea así pero bueno lo dejo hecho
	public GUIAltaMarca generaGUIAltaMarca() {
		return new GUIAltaMarca();
	}
	public GUIBajaMarca generaGUIBajaMarca() {
		return new GUIBajaMarca();
	}
	public GUIListarMarcas generaGUIListarMarcas() {
		return new GUIListarMarcas();
	}
	public GUIMarcaPorID generaGUIMarcaPorID() {
		return new GUIMarcaPorID();
	}
	public GUIModificarMarca generaGUIModificarMarca() {
		return new GUIModificarMarca();
	}

	@Override
	public void generaGUI() {
		// TODO Auto-generated method stub
		
	}
}