/**
 * 
 */
package Negocio.FactoriaNegocio;

import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.SAMarcaImp;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.SAProducto;
import Negocio.Producto.SAProductoIMP;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class FactoriaNegocioImp extends FactoriaNegocio {
	/** 
	* (non-Javadoc)
	* @see FactoriaNegocio#generaSAMarca()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public SAMarca generaSAMarca() {
		return new SAMarcaImp();
	}

	/** 
	* (non-Javadoc)
	* @see FactoriaNegocio#generaTMarca()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca generaTMarca() {// porque???
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	@Override
	public SAProducto generaSAProducto() {
		return new SAProductoIMP();
	}
}