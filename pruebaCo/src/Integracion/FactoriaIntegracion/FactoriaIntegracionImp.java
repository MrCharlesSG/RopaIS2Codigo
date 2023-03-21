/**
 * 
 */
package Integracion.FactoriaIntegracion;

import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaIntegracion.DAOMarcaImp;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class FactoriaIntegracionImp extends FactoriaIntegracion {
	/** 
	* (non-Javadoc)
	* @see FactoriaIntegracion#generaDAOMarca()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public DAOMarca generaDAOMarca() {
		return new DAOMarcaImp();
	}
}