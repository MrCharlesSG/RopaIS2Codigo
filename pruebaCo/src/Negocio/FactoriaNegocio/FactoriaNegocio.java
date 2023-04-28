/**
 * 
 */
package Presentacion.FactoriaPresentacion;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import Presentacion.Clientes.GUIAltaCliente;
import Presentacion.Clientes.GUIBajaCliente;
import Presentacion.Clientes.GUIClientePorID;
import Presentacion.Clientes.GUIListarClientes;
import Presentacion.Clientes.GUIModificarCliente;
import Presentacion.Clientes.IGUICliente;
import Presentacion.Controlador.Evento;
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
import Presentacion.ProveedorPresentacion.IGUIProv;
import Presentacion.Ventas.GUIAltaVenta;
import Presentacion.Ventas.IGUIVentas;

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
	
	/*
	 * Lista de IGUIS
	 */
	private static List<JPanel> listaIGuis = Arrays.asList(
			getInstance().generaIGUIMarca(),
			getInstance().generaIGUIProducto(),
			getInstance().generaIGUIProveedores(),
			getInstance().generaIGUICliente(),
			getInstance().generaIGUIEmpleado(),
			getInstance().generaIGUIVenta()
	);
	
	public List<JPanel> getIGUIs() {
		return Collections.unmodifiableList(listaIGuis);
	}
	
	private static Map<Integer, GUI> mapaGuis;
	
	public Map<Integer, GUI> getGUIs(){
		return Collections.unmodifiableMap(mapaGuis);
	}
			
	
	/*
	 * MARCAS
	 */
	public abstract IGUIMarca generaIGUIMarca();
	public abstract GUIAltaMarca generaGUIAltaMarca();
	public abstract GUIBajaMarca generaGUIBajaMarca() ;
	public  abstract GUIListarMarcas generaGUIListarMarcas();
	public abstract GUIMarcaPorID generaGUIMarcaPorID();
	public abstract GUIModificarMarca generaGUIModificarMarca();

	/*
	 * PROVEEDORES
	 */
	public abstract IGUIProv generaIGUIProveedores();
	public abstract GUI generaGUIAltaProveedor();
	public abstract GUI generaGUIBajaProveedor();
	public  abstract GUI generaGUIListarProveedor();
	public abstract GUI generaGUIProveedorPorID();
	public abstract GUI generaGUIModificarProveedor();
	
	public void generaGUIProv() {
		mapaGuis.clear();
		mapaGuis.put(Evento.ALTA_PROVEEDOR,getInstance().generaGUIAltaProveedor());
		mapaGuis.put(Evento.BAJA_PROVEEDOR,getInstance().generaGUIBajaProveedor());
		mapaGuis.put(Evento.MODIFICAR_PROVEEDOR,getInstance().generaGUIModificarProveedor());
	}
	
	/*
	 * PRODUCTO
	 */
	public abstract IGUIProducto generaIGUIProducto();
	public abstract GUIAltaProducto generaGUIAltaProducto();
	public abstract GUIBajaProducto generaGUIBajaProducto();
	public  abstract GUIListarProductos generaGUIListarProducto();
	public abstract GUIProductoPorID generaGUIProductoPorID();
	public abstract GUIModificarProducto generaGUIModificarProducto();
	
	/*
	 * CLIENTES
	 */
	public abstract IGUICliente generaIGUICliente();
	public abstract GUIAltaCliente generaGUIAltaCliente();
	public abstract GUIBajaCliente generaGUIBajaCliente();
	public  abstract GUIListarClientes generaGUIListarCliente();
	public abstract GUIClientePorID generaGUIClientePorID();
	public abstract GUIModificarCliente generaGUIModificarCliente();
	
	/*
	 * EMPLEADOS
	 */
	public abstract IGUIEmpleado generaIGUIEmpleado();
	public abstract GUIAltaEmpleado generaGUIAltaEmpleado();
	public abstract GUIBajaEmpleado generaGUIBajaEmpleado();
	public abstract GUIListarEmpleados generaGUIListarEmpleado();
	public abstract GUIEmpleadoPorID generaGUIEmpleadoPorID();
	public abstract GUIModificarEmpleado generaGUIModificarEmpleado();
	
	/*
	 * VENTAS
	 */
	public abstract IGUIVentas generaIGUIVenta() ;

	public abstract GUIAltaVenta generaIGUIAltaVenta();
}