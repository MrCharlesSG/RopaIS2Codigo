/**
 * 
 */
package Negocio.FactoriaNegocio;

import Negocio.Clientes.SAClientes;
import Negocio.Clientes.TCliente;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.SAProducto;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
import Negocio.Ventas.SAVentas;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public abstract class FactoriaNegocio {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private static FactoriaNegocio instance;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static FactoriaNegocio getInstance() {
		if (instance== null) 
			instance = new FactoriaNegocioImp(); 
			return instance;
	
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract SAMarca generaSAMarca();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract TMarca generaTMarca();

	public abstract SAProducto generaSAProducto();
	
	public abstract SAProveedores generaSAProveedor();
	
	public abstract SAClientes generaSAClientes();
	
	public abstract TProveedor generaTProveedor(String[] datos);
	
	public abstract TCliente generaTCliente(String[] datos);
	
	public abstract TEmpleado generaTEmpleado(String[] datos);

	public abstract SAEmpleado generaSAEmpleado();
	public abstract SAVentas generaSAVentas();
	

}