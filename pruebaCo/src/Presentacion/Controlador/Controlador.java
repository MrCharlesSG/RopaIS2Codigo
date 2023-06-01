package Presentacion.Controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import Negocio.Clientes.SAClientes;
import Negocio.Clientes.TCliente;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Presentacion.GUI.GUI;
import Presentacion.Controlador.Evento;
import Negocio.Producto.TProducto;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
import Negocio.Ventas.SAVentas;
import Negocio.Ventas.TVenta;
import Negocio.Producto.SAProducto;
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
	public abstract void setGUI(GUI gui);
}