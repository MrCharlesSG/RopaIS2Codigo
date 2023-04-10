/**
 * 
 */
package Negocio.FactoriaNegocio;

import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.SAMarcaImp;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.SAProducto;
import Negocio.Producto.SAProductoIMP;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.SAProveedoresIMP;
import Negocio.Proveedor.TProveedor;

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
	
	@Override
	public SAProveedores generaSAProveedor() {
		return new SAProveedoresIMP();
	}

	@Override
	public TProveedor generaTProveedor(String[] datos) {
		if(datos.length==2){
			try{
				return new TProveedor(datos[1], Integer.parseInt(datos[0]), null, true);
			}catch(NumberFormatException e1){
				return null;
			}
		}
		return null;
	}
}