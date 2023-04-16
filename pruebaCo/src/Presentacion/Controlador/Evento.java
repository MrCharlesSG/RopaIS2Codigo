package Presentacion.Controlador;

public class Evento {
	//GUI_Modulo
	public static final int Mostrar_GUI_MARCA = 0;
	public static final int Mostrar_GUI_PRODUCTOS = 1;
	public static final int Mostrar_GUI_PROVEEDORES = 3;
	
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
	
	public static final int RES_ALTA_PRODUCTO_OK= 801;
	public static final int RES_ALTA_PRODUCTO_KO= 802;
	public static final int RES_MODIFICAR_PRODUCTO_OK = 803;
	public static final int RES_MODIFICAR_PRODUCTO_KO = 804;
	public static final int RES_BAJA_PRODUCTO_OK = 805;
	public static final int RES_BAJA_PRODUCTO_KO = 806;
	public static final int RES_PRODUCTO_POR_ID_OK = 807;
	public static final int RES_PRODUCTO_POR_ID_KO = 808;
	
		//Proveedor
		public static final int ALTA_PROVEEDOR = 901;
		public static final int BAJA_PROVEEDOR = 902;
		public static final int MODIFICAR_PROVEEDOR = 904;
		public static final int PROVEEDOR_POR_ID = 905;
		public static final int LISTAR_PROVEEDORES = 906;
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
}
