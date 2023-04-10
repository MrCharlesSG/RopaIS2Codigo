/**
 * 
 */
package Presentacion.FactoriaPresentacion;

import javax.swing.JComponent;

import Presentacion.MarcaPresentacion.GUIAltaMarca;
import Presentacion.MarcaPresentacion.GUIBajaMarca;
import Presentacion.MarcaPresentacion.GUIListarMarcas;
import Presentacion.MarcaPresentacion.GUIMarcaPorID;
import Presentacion.MarcaPresentacion.GUIModificarMarca;
import Presentacion.Producto.GUIAltaProducto;
import Presentacion.Producto.GUIBajaProducto;
import Presentacion.Producto.GUIListarProductos;
import Presentacion.Producto.GUIModificarProducto;
import Presentacion.Producto.GUIProductoPorID;
import ProveedorPresentacion.GUIAltaProv;
import ProveedorPresentacion.GUIBajaProv;
import ProveedorPresentacion.GUIListarProv;
import ProveedorPresentacion.GUIModificarProv;
import ProveedorPresentacion.GUIProvPorID;

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
	
	/*
	 * PROVEEDOR
	 */
	@Override
	public GUIAltaProv generaGUIAltaProveedor() {
		return new GUIAltaProv();
	}

	@Override
	public GUIBajaProv generaGUIBajaProveedor() {
		return new GUIBajaProv();
	}

	@Override
	public GUIListarProv generaGUIListarProveedor() {
		return new GUIListarProv();
	}

	@Override
	public GUIProvPorID generaGUIProveedorPorID() {
		return new GUIProvPorID();
	}

	@Override
	public GUIModificarProv generaGUIModificarProveedor() {
		return new GUIModificarProv();
	}
}