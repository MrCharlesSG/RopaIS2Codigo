/**
 * 
 */
package Negocio.FactoriaNegocio;

import Negocio.Clientes.SAClientes;
import Negocio.Clientes.SAClientesIMP;
import Negocio.Clientes.TCliente;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.SAEmpleadoImp;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoTC;
import Negocio.Empleado.TEmpleadoTP;
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

	@Override
	public TCliente generaTCliente(String[] datos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TEmpleado generaTEmpleado(String[] datos) {//String nombre , String apellido1, String apellido2, String DNI, int tfno, int ID, boolean tiempoCompleto
		TEmpleado empl= null;
		try{
			if(datos.length==7){
				boolean tiempo = Boolean.parseBoolean(datos[6]);
				if(tiempo){
					empl= new TEmpleadoTC(datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), true);
				}else{
					empl= new TEmpleadoTP(datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), true);
				}
			}
		}catch(NumberFormatException e){
			return null;
		}
		return empl;
	}

	@Override
	public SAClientes generaSAClientes() {
		
		return new SAClientesIMP() ;
	}

	@Override
	public SAEmpleado generaSAEmpleado() {
		return new SAEmpleadoImp();
	}
}