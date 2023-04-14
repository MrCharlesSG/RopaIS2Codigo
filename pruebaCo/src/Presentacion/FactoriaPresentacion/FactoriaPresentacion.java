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
	public abstract GUIAltaProv generaGUIAltaProveedor();
	public abstract GUIBajaProv generaGUIBajaProveedor();
	public  abstract GUIListarProv generaGUIListarProveedor();
	public abstract GUIProvPorID generaGUIProveedorPorID();
	public abstract GUIModificarProv generaGUIModificarProveedor();
	
	
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


}