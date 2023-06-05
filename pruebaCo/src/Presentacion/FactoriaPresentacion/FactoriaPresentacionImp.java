/**
 * 
 */
package Presentacion.FactoriaPresentacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Negocio.Clientes.SAClientes;
import Negocio.Clientes.TCliente;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;
import Negocio.Ventas.SAVentas;
import Negocio.Ventas.TVenta;
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
import Presentacion.MarcaPresentacion.GUIListarMarcaPorProveedor;
import Presentacion.MarcaPresentacion.GUIListarMarcas;
import Presentacion.MarcaPresentacion.GUIMarcaPorID;
import Presentacion.MarcaPresentacion.GUIModificarMarca;
import Presentacion.MarcaPresentacion.GUIaddProveedorToMarca;
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
import Presentacion.ProveedorPresentacion.GUIListarProv;
import Presentacion.ProveedorPresentacion.GUIListarProveedoresPorMarca;
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
	
	FactoriaPresentacionImp(){
	}
	
	@Override
	public GUI generaGUI(Integer evento) {
		switch(evento) {
		case Evento.Mostrar_GUI_MARCA:{
			return new IGUIMarca();
		}
		case Evento.ALTA_MARCA:{
	
			return new GUIAltaMarca();
		}
		case Evento.BAJA_MARCA:{
			return new GUIBajaMarca();
		}
		case Evento.LISTAR_MARCAS:{
			return new GUIListarMarcas();
		}
		case Evento.MARCA_PORID:{
			return new GUIMarcaPorID();
		}
		case Evento.MODIFICAR_MARCA:{
			return new GUIModificarMarca();
		}
		case Evento.ADD_PROVEEDOR_TO_MARCA:{
			return new GUIaddProveedorToMarca();
		}
		case Evento.LISTAR_MARCA_POR_PROVEEDORES:{
			return new GUIListarProveedoresPorMarca();// esta es asi??
	    }
	              
	    case Evento.DELETE_PROVEEDOR_OF_MARCA:{
	    	return new GUIDeleteMarcaOfProveedor();
	    }
		
		/*
		 * PRODUCTO
		 */
	    case Evento.Mostrar_GUI_PRODUCTOS:{
	    	return new IGUIProducto();
	    }	
		case Evento.ALTA_PRODUCTO:{
			return new GUIAltaProducto();
		}
		case Evento.BAJA_PRODUCTO:{
			return new GUIBajaProducto();
		}
		case Evento.LISTAR_PRODUCTOS:{
			return new GUIListarProductos();
		}
		case Evento.MODIFICAR_PRODUCTO:{
			return new GUIModificarProducto();
		}
		case Evento.PRODUCTO_POR_ID:{
			return new GUIProductoPorID();
		}
		
		/*
		 * PROVEEDORES
		 */
		case Evento.Mostrar_GUI_PROVEEDORES:{
			return new IGUIProv();
		}
		case Evento.ALTA_PROVEEDOR:{
			return new GUIAltaProv();
	    }
	    case Evento.BAJA_PROVEEDOR:{
	    	return new GUIBajaProv();
	    }
	    case Evento.LISTAR_PROVEEDORES:{
	    	return new GUIListarProv();
	    }
	    case Evento.PROVEEDOR_POR_ID:{
	    	return new GUIProvPorID();
	    }
	    case Evento.MODIFICAR_PROVEEDOR:{
	    	return new GUIModificarProv();
	    }
	    case Evento.LISTAR_PROVEEDORES_POR_MARCA:{
	    	return new GUIListarProveedoresPorMarca();
	    }
	    case Evento.ADD_MARCA_TO_PROVEEDOR:{
	    	return new GUIAddMarcaToProveedor();
	    }            
	    case Evento.DELETE_MARCA_OF_PROVEEDOR:{
	    	return new GUIDeleteMarcaOfProveedor();
	    }
	    /*
		 * CLIENTES
		 */
	    case Evento.Mostrar_GUI_CLIENTES:{
	    	return new IGUICliente();
	    }
		case Evento.ALTA_CLIENTE:{
			return new GUIAltaCliente();
		
	    }
	    case Evento.BAJA_CLIENTE:{
	    	return new GUIBajaCliente();
	    }
	    case Evento.LISTAR_CLIENTES:{
	    	return new GUIListarClientes();
	    }
	    case Evento.CLIENTE_POR_ID:{
	    	return new GUIClientePorID();
	    }
	    case Evento.MODIFICAR_CLIENTE:{
	    	return new GUIModificarCliente();
	    }
	    /*
	     * EMPLEADOS
	     */
	    case Evento.Mostrar_GUI_EMPLEADOS:{
	    	return new IGUIEmpleado();
	    }
	    case Evento.ALTA_EMPLEADO:{
	    	return new GUIAltaEmpleado();
	    }
	    case Evento.BAJA_EMPLEADO:{
	    	return new GUIBajaEmpleado();
	    }
	    case Evento.MODIFICAR_EMPLEADO:{
	    	return new GUIModificarEmpleado();
	    }
	    case Evento.EMPLEADO_POR_ID:{
	    	return new GUIEmpleadoPorID();
	    }
	    case Evento.LISTAR_EMPLEADO:{
	    	return new GUIListarEmpleados();
	    }
	    /*
	     * VENTAS
	     */
	    case Evento.Mostrar_GUI_VENTAS:{
	    	return new IGUIVentas();
	    }
	    case Evento.ABRIR_VENTA:{
	    	return new GUIAltaVenta();
	    }
	    case Evento.CERRAR_VENTA:{
	    	return new GUIAltaVenta();// diria que este es asi 
	    }
	    case Evento.VENTA_POR_ID:{
	    	return new GUIVentaPorID();
	    }
	    case Evento.LISTAR_VENTAS:{
	    	return new GUIListarVentas();
	    }
	    case Evento.DEVOLUCION_VENTA:{
	    	return new GUIDevolverVenta();
	    }
	    case Evento.VENTAS_DE_UN_CLIENTE:{
	    	return new GUIVenta_Cliente();
	    }
	    case Evento.VENTAS_DE_UN_EMPLEADO:{
	    	return new GUIVenta_Empleado();
	    }

		}
		return null;
	

	}
}