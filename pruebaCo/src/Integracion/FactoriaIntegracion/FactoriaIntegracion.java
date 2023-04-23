/**
 * 
 */
package Integracion.FactoriaIntegracion;

import Integracion.Clientes.DAOClientes;
import Integracion.Empleados.DAOEmpleado;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.Producto.DAOProducto;
import Integracion.Proveedores.DAOProveedores;
import Integracion.Ventas.DAOVentas;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public abstract class FactoriaIntegracion {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private static FactoriaIntegracion instancia;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static FactoriaIntegracion getInstance() {
		if (instancia== null) 
			instancia = new FactoriaIntegracionImp(); 
		return instancia;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract DAOMarca generaDAOMarca();
	public abstract DAOProducto generaDAOProducto();
	public abstract DAOProveedores generaDAOProveedor();
	public abstract DAOEmpleado generaDAOEmpleado() ;	
	public abstract DAOClientes generaDAOClientes() ;
	public abstract DAOVentas generaDAOVentas() ;
}