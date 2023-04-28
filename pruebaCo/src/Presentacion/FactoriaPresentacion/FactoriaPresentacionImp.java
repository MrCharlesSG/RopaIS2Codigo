/**
 * 
 */
package Presentacion.FactoriaPresentacion;

import javax.swing.JComponent;

import Presentacion.Clientes.GUIAltaCliente;
import Presentacion.Clientes.GUIBajaCliente;
import Presentacion.Clientes.GUIClientePorID;
import Presentacion.Clientes.GUIListarClientes;
import Presentacion.Clientes.GUIModificarCliente;
import Presentacion.Clientes.IGUICliente;
import Presentacion.Empleado.GUIAltaEmpleado;
import Presentacion.Empleado.GUIBajaEmpleado;
import Presentacion.Empleado.GUIEmpleadoPorID;
import Presentacion.Empleado.GUIListarEmpleados;
import Presentacion.Empleado.GUIModificarEmpleado;
import Presentacion.Empleado.IGUIEmpleado;
import Presentacion.GUI.GUI;
import Presentacion.MarcaPresentacion.GUIAltaMarca;
import Presentacion.MarcaPresentacion.GUIBajaMarca;
import Presentacion.MarcaPresentacion.GUIListarMarcas;
import Presentacion.MarcaPresentacion.GUIMarcaPorID;
import Presentacion.MarcaPresentacion.GUIModificarMarca;
import Presentacion.MarcaPresentacion.IGUIMarca;
import Presentacion.Producto.GUIAltaProducto;
import Presentacion.Producto.GUIBajaProducto;
import Presentacion.Producto.GUIListarProductos;
import Presentacion.Producto.GUIModificarProducto;
import Presentacion.Producto.GUIProductoPorID;
import Presentacion.Producto.IGUIProducto;
import Presentacion.ProveedorPresentacion.GUIAltaProv;
import Presentacion.ProveedorPresentacion.GUIBajaProv;
import Presentacion.ProveedorPresentacion.GUIListarProv;
import Presentacion.ProveedorPresentacion.GUIModificarProv;
import Presentacion.ProveedorPresentacion.GUIProvPorID;
import Presentacion.ProveedorPresentacion.IGUIProv;
import Presentacion.Ventas.GUIAltaVenta;
import Presentacion.Ventas.GUIDevolverVenta;
import Presentacion.Ventas.GUIListarVentas;
import Presentacion.Ventas.GUIVentaPorID;
import Presentacion.Ventas.GUIVenta_Cliente;
import Presentacion.Ventas.GUIVenta_Empleado;
import Presentacion.Ventas.IGUIVentas;

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
	
	/*
	 * GUI MARCA 
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
	public IGUIMarca generaIGUIMarca() {
		return new IGUIMarca();
	}
	@Override
	public void generaGUI() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * GUI PRODUCTOS
	*/
	@Override
	public GUIAltaProducto generaGUIAltaProducto() {
		return new GUIAltaProducto();
	}
	@Override
	public GUIBajaProducto generaGUIBajaProducto() {
		return new GUIBajaProducto();
	}
	@Override
	public GUIListarProductos generaGUIListarProducto() {
		return new GUIListarProductos();
	}
	@Override
	public GUIProductoPorID generaGUIProductoPorID() {
		return new GUIProductoPorID();
	}
	@Override
	public GUIModificarProducto generaGUIModificarProducto() {
		return new GUIModificarProducto();
	}
	
	@Override
	public IGUIProducto generaIGUIProducto() {
		return new IGUIProducto();
	}

	/*
	 * PROVEEDOR
	 */
	@Override
	public IGUIProv generaIGUIProveedores() {
		return new IGUIProv();
	}
	
	@Override
	public GUIAltaProv generaGUIAltaProveedor() {
		return new GUIAltaProv();
	}

	@Override
	public GUI generaGUIBajaProveedor() {
		return new GUIBajaProv();
	}

	@Override
	public GUI generaGUIListarProveedor() {
		return new GUIListarProv();
	}

	@Override
	public GUI generaGUIProveedorPorID() {
		return new GUIProvPorID();
	}

	@Override
	public GUI generaGUIModificarProveedor() {
		return new GUIModificarProv();
	}
	
	/*
	 * CLIENTES
	 */
	@Override
	public GUIAltaCliente generaGUIAltaCliente() {
		return new GUIAltaCliente();
	}
	@Override
	public GUIBajaCliente generaGUIBajaCliente() {
		return new GUIBajaCliente();
	}
	@Override
	public GUIListarClientes generaGUIListarCliente() {
		return new GUIListarClientes();
	}
	@Override
	public GUIClientePorID generaGUIClientePorID() {
		return new GUIClientePorID();
	}
	@Override
	public GUIModificarCliente generaGUIModificarCliente() {
		return new GUIModificarCliente();
	}
	@Override
	public IGUICliente generaIGUICliente() {
		return new IGUICliente();
	}
	/*
	 * EMPLEADOS
	 */
	@Override
	public IGUIEmpleado generaIGUIEmpleado() {
		return new IGUIEmpleado();
	}
	@Override
	public GUIAltaEmpleado generaGUIAltaEmpleado() {
		// TODO Auto-generated method stub
		return new GUIAltaEmpleado();
	}
	@Override
	public GUIBajaEmpleado generaGUIBajaEmpleado() {
		return new GUIBajaEmpleado();
	}
	@Override
	public GUIListarEmpleados generaGUIListarEmpleado() {
		return new GUIListarEmpleados();
	}
	@Override
	public GUIEmpleadoPorID generaGUIEmpleadoPorID() {
		return new GUIEmpleadoPorID();
	}
	@Override
	public GUIModificarEmpleado generaGUIModificarEmpleado() {
		return new GUIModificarEmpleado();
	}
	/*
	 * VENTAS
	 */
	@Override
	public IGUIVentas generaIGUIVenta() {
		return new IGUIVentas();
	}
	@Override
	public GUIVenta_Empleado generaGUIVenta_Empleado() {
		return new GUIVenta_Empleado();
	}
	@Override
	public GUIVenta_Cliente generaGUIVenta_Cliente() {
		return new GUIVenta_Cliente();
	}
	@Override
	public GUIAltaVenta generaGUIAltaVenta() {
		return new GUIAltaVenta();
	}
	@Override
	public GUIDevolverVenta generaGUIDevolverVenta() {
		return new GUIDevolverVenta();
	}
	@Override
	public GUIListarVentas generaGUIListarVentas() {
		return new GUIListarVentas();
	}
	@Override
	public GUIVentaPorID genraGUIVentaPorID() {
		return new GUIVentaPorID();
	}


}