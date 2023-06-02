/**
 * 
 */
package Presentacion.FactoriaPresentacion;

import java.util.HashMap;
import java.util.Map;

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
import Presentacion.ProveedorPresentacion.GUIAddMarcaToProveedor;
import Presentacion.ProveedorPresentacion.GUIAltaProv;
import Presentacion.ProveedorPresentacion.GUIBajaProv;
import Presentacion.ProveedorPresentacion.GUIDeleteMarcaOfProveedor;
import Presentacion.ProveedorPresentacion.GUIListarMarcasDeProveedor;
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
	
	private static final Map<Integer, GUI> map = new HashMap<Integer, GUI>();
	
	FactoriaPresentacionImp(){
		/*
		 * MARCAS
		 */
		map.put(Evento.Mostrar_GUI_MARCA, new IGUIMarca());
		map.put(Evento.ALTA_MARCA, new GUIAltaMarca());
		map.put(Evento.BAJA_MARCA, new GUIBajaMarca());
		map.put(Evento.LISTAR_MARCAS, new GUIListarMarcas());
		map.put(Evento.MODIFICAR_MARCA, new GUIModificarMarca());
		map.put(Evento.MARCA_PORID, new GUIMarcaPorID());
		
		/*
		 * PRODUCTOS
		 */
		map.put(Evento.Mostrar_GUI_PRODUCTOS, new IGUIProducto());
		map.put(Evento.ALTA_PRODUCTO, new GUIAltaProducto());
		map.put(Evento.BAJA_PRODUCTO, new GUIBajaProducto());
		map.put(Evento.LISTAR_PRODUCTOS, new GUIListarProductos());
		map.put(Evento.MODIFICAR_PRODUCTO, new GUIModificarProducto());
		map.put(Evento.PRODUCTO_POR_ID, new GUIProductoPorID());
		
		/*
		 * PROVEEDORES
		 */
		
		map.put(Evento.Mostrar_GUI_PROVEEDORES, new IGUIProv());
		map.put(Evento.ALTA_PROVEEDOR, new GUIAltaProv());
		map.put(Evento.BAJA_PROVEEDOR, new GUIBajaProv());
		map.put(Evento.LISTAR_PROVEEDORES, new GUIListarProv());
		map.put(Evento.MODIFICAR_PROVEEDOR, new GUIModificarProv());
		map.put(Evento.PROVEEDOR_POR_ID, new GUIProvPorID());
		map.put(Evento.ADD_MARCA_TO_PROVEEDOR, new GUIAddMarcaToProveedor());
		map.put(Evento.DELETE_MARCA_OF_PROVEEDOR, new GUIDeleteMarcaOfProveedor());
		map.put(Evento.LISTA_MARCAS_DE_PROVEEDOR, new GUIListarMarcasDeProveedor());
		map.put(Evento.ADD_MARCA_TO_PROVEEDOR, new GUIAddMarcaToProveedor());
		
		/*
		 * CLIENTES
		 */
		
		map.put(Evento.Mostrar_GUI_CLIENTES, new IGUICliente());
		map.put(Evento.ALTA_CLIENTE, new GUIAltaCliente());
		map.put(Evento.BAJA_CLIENTE, new GUIBajaCliente());
		map.put(Evento.LISTAR_CLIENTES, new GUIListarClientes());
		map.put(Evento.MODIFICAR_CLIENTE, new GUIModificarCliente());
		map.put(Evento.CLIENTE_POR_ID, new GUIClientePorID());
		
		/*
		 * EMPLEADOS
		 */
		
		map.put(Evento.Mostrar_GUI_EMPLEADOS, new IGUIEmpleado());
		map.put(Evento.ALTA_EMPLEADO, new GUIAltaEmpleado());
		map.put(Evento.BAJA_EMPLEADO, new GUIBajaEmpleado());
		map.put(Evento.LISTAR_EMPLEADO, new GUIListarEmpleados());
		map.put(Evento.MODIFICAR_EMPLEADO, new GUIModificarEmpleado());
		map.put(Evento.EMPLEADO_POR_ID, new GUIEmpleadoPorID());
		
		/*
		 * VENTAS
		 */
		
		map.put(Evento.Mostrar_GUI_VENTAS, new IGUIVentas());
		map.put(Evento.ABRIR_VENTA, new GUIAltaVenta());
		map.put(Evento.CERRAR_VENTA, new GUIDevolverVenta());
		map.put(Evento.LISTAR_VENTAS, new GUIListarVentas());
		map.put(Evento.VENTAS_DE_UN_CLIENTE, new GUIVenta_Cliente());
		map.put(Evento.VENTAS_DE_UN_EMPLEADO, new GUIVenta_Empleado());
		map.put(Evento.VENTA_POR_ID, new GUIVentaPorID());
	}
	
	@Override
	public GUI generaGUI(Integer num) {
		if(map.containsKey(num)){
			GUI window = map.get(num);
			window.setGUIVisible(true);
			return window;
		}
		else
			return null;
	}
}