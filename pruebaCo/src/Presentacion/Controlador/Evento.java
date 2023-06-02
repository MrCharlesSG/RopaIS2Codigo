package Presentacion.Controlador;

public class Evento {
	//GUI_Modulo
	public static final int Mostrar_GUI_MARCA = 0;
	public static final int Mostrar_GUI_PRODUCTOS = 1;
	public static final int Mostrar_GUI_PROVEEDORES = 2;
	public static final int Mostrar_GUI_CLIENTES=3;
	public static final int Mostrar_GUI_EMPLEADOS = 4;
	public static final int Mostrar_GUI_VENTAS=5;
	
	//Respuestas
	public static final int KO = 10;
	public static final int OK = 11;
	
	//Marca
	public static final int ALTA_MARCA= 101;
	public static final int BAJA_MARCA= 102;
	public static final int MOSTRAR_MARCA= 103;
	public static final int LISTAR_MARCAS = 104;
	public static final int MARCA_PORID = 105;
	public static final int MODIFICAR_MARCA = 106;
	public static final int MOSTRAR_GUI_BIBLIOTECA = 107;
	public static final int OCULTAR_GUI_BIBLIOTECA = 108;
	
	public static final int RES_ALTA_MARCA_OK= 401;
	public static final int RES_ALTA_MARCA_KO= 402;
	public static final int RES_MODIFICAR_MARCA_OK = 403;
	public static final int RES_MODIFICAR_MARCA_KO = 404;
	public static final int RES_BAJA_MARCA_OK = 405;
	public static final int RES_BAJA_MARCA_KO = 406;
	public static final int RES_MARCA_PORID_OK = 407;
	public static final int RES_MARCA_PORID_KO = 408;
	//Producto
	public static final int ALTA_PRODUCTO = 701;
	public static final int BAJA_PRODUCTO = 702;
	public static final int LISTAR_PRODUCTOS = 703;
	public static final int MODIFICAR_PRODUCTO = 705;
	public static final int PRODUCTO_POR_ID = 706;
	public static final int VENTA_POR_PRODUCTO = 707;
	
		//Proveedor
		public static final int ALTA_PROVEEDOR = 901;
		public static final int BAJA_PROVEEDOR = 902;
		public static final int MODIFICAR_PROVEEDOR = 904;
		public static final int PROVEEDOR_POR_ID = 905;
		public static final int LISTAR_PROVEEDORES = 906;
		public static final int ADD_MARCA_TO_PROVEEDOR=913;
		public static final int DELETE_MARCA_OF_PROVEEDOR=914;
		public static final int LISTAR_PROVEEDORES_POR_MARCA=915;
		//respuestas Proveedor
		public static final int RES_ALTA_PROVEEDOR_OK = 907;
		public static final int RES_ALTA_PROVEEDOR_KO = 908;
		public static final int RES_BAJA_PROVEEDOR_OK = 909;
		public static final int RES_BAJA_PROVEEDOR_KO = 910;
		public static final int RES_MODIFICAR_PROVEEDOR_OK = 911;
		public static final int RES_MODIFICAR_PROVEEDOR_KO = 912;
		public static final int RES_LISTAR_PROVEEDOR_OK = 913;
		public static final int RES_LISTAR_PROVEEDOR_KO = 914;
		public static final int RES_PROVEEDOR_POR_ID_OK = 911;
		public static final int RES_PROVEEDOR_POR_ID_KO = 912;

		//clientes
		public static final int ALTA_CLIENTE = 950;
		public static final int BAJA_CLIENTE = 951;
		public static final int MODIFICAR_CLIENTE = 952;
		public static final int CLIENTE_POR_ID = 953;
		public static final int LISTAR_CLIENTES = 954;
				
		//respuestas clientes
		public static final int RES_ALTA_CLIENTE_OK = 955;
		public static final int RES_ALTA_CLIENTE_KO = 956;
		public static final int RES_BAJA_CLIENTE_OK = 957;
		public static final int RES_BAJA_CLIENTE_KO = 958;
		public static final int RES_MODIFICAR_CLIENTE_OK = 959;
		public static final int RES_MODIFICAR_CLIENTE_KO = 960;
		public static final int RES_LISTAR_CLIENTE_OK = 961;
		public static final int RES_LISTAR_CLIENTE_KO = 962;
		public static final int RES_CLIENTE_POR_ID_OK = 963;
		public static final int RES_CLIENTE_POR_ID_KO = 964;
		
		//clientes
		public static final int ALTA_EMPLEADO = 300;
		public static final int BAJA_EMPLEADO= 301;
		public static final int MODIFICAR_EMPLEADO = 302;
		public static final int EMPLEADO_POR_ID = 303;
		public static final int LISTAR_EMPLEADO = 304;
				
		//respuestas clientes
		public static final int RES_ALTA_EMPLEADO_OK = 305;
		public static final int RES_ALTA_EMPLEADO_KO = 306;
		public static final int RES_BAJA_EMPLEADO_OK = 307;
		public static final int RES_BAJA_EMPLEADO_KO = 308;
		public static final int RES_MODIFICAR_EMPLEADO_OK = 309;
		public static final int RES_MODIFICAR_EMPLEADO_KO = 310;
		public static final int RES_LISTAR_EMPLEADO_OK = 311;
		public static final int RES_LISTAR_EMPLEADO_KO = 312;
		public static final int RES_EMPLEADO_POR_ID_OK = 313;
		public static final int RES_EMPLEADO_POR_ID_KO = 314;
		
		//ventas
		public static final int ABRIR_VENTA= 1101;
		public static final int CAMBIAR_VENTA= 1102;
		public static final int CERRAR_VENTA= 1103;
		public static final int DEVOLUCION_VENTA=1104;
		public static final int VENTA_POR_ID=1105;
		public static final int LISTAR_VENTAS=1106;
		public static final int VENTAS_DE_UN_CLIENTE=1107;
		public static final int VENTAS_DE_UN_EMPLEADO=1108;
			
			
		//respuestas ventas
		public static final int RES_ABRIR_VENTA_OK= 1109;
		public static final int RES_ABRIR_VENTA_KO= 1110;
		public static final int RES_CAMBIAR_VENTA_OK= 1111;
		public static final int RES_CAMBIAR_VENTA_KO= 1112;
		public static final int RES_CERRAR_VENTA_OK= 1113;
		public static final int RES_CERRAR_VENTA_KO= 1114;
		public static final int RES_DEVOLUCION_VENTA_OK=1115;
		public static final int RES_DEVOLUCION_VENTA_KO=1116;
		public static final int RES_VENTA_POR_ID_OK=1117;
		public static final int RES_VENTA_POR_ID_KO=1118;
		public static final int RES_LISTAR_VENTAS_OK=1119;
		public static final int RES_LISTAR_VENTAS_KO=1120;
		public static final int RES_VENTAS_DE_UN_CLIENTE_OK=1121;
		public static final int RES_VENTAS_DE_UN_CLIENTE_KO=1122;
		public static final int RES_VENTAS_DE_UN_EMPLEADO_OK=1123;
		public static final int RES_VENTAS_DE_UN_EMPLEADO_KO=1124;

}
