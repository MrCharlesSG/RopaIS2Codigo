package Ventas;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotEquals;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import Negocio.Clientes.SAClientes;
import Negocio.Clientes.TCliente;
import Negocio.Clientes.TClienteNormal;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoTC;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocioImp;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Negocio.ProductosDeVenta.TCarrito;
import Negocio.Ventas.SAVentas;
import Negocio.Ventas.TOAVenta;
import Negocio.Ventas.TVenta;

public class SAVentaTest {

	private SAVentas saVenta=FactoriaNegocioImp.getInstance().generaSAVentas();
	//int id, id_emp, id_cliente, double precio, boolean activo
	private TVenta venta1=new TVenta(1, 1, 1, 10, true);
	private TVenta venta2=new TVenta(2, 1, 1, 15, true);
	private TVenta venta3=new TVenta(3, 3, 1, 15, true);
	private TVenta venta4=new TVenta(4, 3, 4, 15, true);
	private Collection<TVenta> mockCollec=new ArrayList<TVenta>();
	private SAClientes sac=FactoriaNegocioImp.getInstance().generaSAClientes();
	private SAEmpleado sae=FactoriaNegocioImp.getInstance().generaSAEmpleado();
	private SAProducto saProd = FactoriaNegocioImp.getInstance().generaSAProducto();
	
	@Test
	public void basicBehaviour_Test(){
		this.vaciarBaseDatos();
		//nombre, cantidad, talla, idProducto, categoria, marca, precio
		TProducto tPant = new TProducto("Pantalones azules", 30, 1,1,"pantalones",1, 10);
		TProducto tCamiseta = new TProducto("Pantalones azules", 30, 1,2,"pantalones",1, 15);
		TEmpleado emp1=new TEmpleadoTC("Juan", "Lucas", "Malaquito", "06655387s", 515151, 1, true, 1300, 200);
		TCliente cl1=new TClienteNormal(true, "Diaz", "Diaz", "12234324A", 1, "Dani", 613456987, false,"Madrid");;
		int idEmp=sae.create(emp1);
		int idCl=sac.create(cl1);
		
		int aux1, aux2, aux3;
		
		
	//comprobar el abrir
		aux1=saVenta.abrir(venta1);
		assertNotEquals(aux1, 2);
		assertEquals(aux1, 1);
		this.mockCollec.add(venta1);
		//comprobacion del TaoVenta read
		TOAVenta v=this.saVenta.read(1);
		assertEquals(v.getVenta(), venta1);
		//ahora comprobando que el TaoVenta falle bien
		TOAVenta v2=this.saVenta.read(4);
		assertEquals(v2.getVenta(), null);
		
		
		
		aux1=saVenta.abrir(venta2);
		assertEquals(aux1, 2);
		v=this.saVenta.read(2);
		assertEquals(v.getVenta(), venta2);
		
		//venta con empleado que no existe
		aux2=saVenta.abrir(venta3);
		assertEquals(aux2, -1);
		//venta con cliente que no existe
		aux3=saVenta.abrir(venta3);
		assertEquals(aux3, -1);
		
		//crear una venta existente
		//aux3=saVenta.abrir(venta1);
		//assertEquals(aux3, -1);
		
		//compruebo el readByEmpleado
		Collection<TVenta> empleado1=new ArrayList<TVenta>();
		empleado1.add(venta1);
		empleado1.add(venta2);
		assertEquals(equalsCollection(this.saVenta.readByEmpleado(1), empleado1), true);
		//fallo de el readByEmpleado
		assertEquals(equalsCollection(this.saVenta.readByEmpleado(6), empleado1), false);

		
		//compruebo el readByCliente
		Collection<TVenta> cliente1=new ArrayList<TVenta>();
		cliente1.add(venta1);
		cliente1.add(venta2);
		assertEquals(equalsCollection(this.saVenta.readByCliente(1), cliente1), true);
		//fallo del readByCliente
		assertEquals(equalsCollection(this.saVenta.readByCliente(5), cliente1), false);
		
		
		
		
		//comprobar el readAll
		this.mockCollec.add(venta2);		
		assert(mockCollec.equals(this.saVenta.readAll()));
		
		saProd.create(tCamiseta);
		saProd.create(tPant);
		TCarrito carroV1=new TCarrito(1);
		carroV1.add(1, 1, 10);
		//carro vacio
		TCarrito carroV2=new TCarrito(2);
		
		
		
		int auxCerrar2=saVenta.cerrar(carroV2);
		assertEquals(auxCerrar2, -1);

		
	}
	
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Ventas.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Empleados.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Clientes.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Productos.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
		
	}
	
	private boolean equalsCollection(Collection<TVenta> a, Collection<TVenta> b){

		if (a.size() != b.size()) {
			return false;
		} else {
		    Iterator<TVenta> it1 = a.iterator();
		    Iterator<TVenta> it2 = b.iterator();
		    boolean iguales = true;
		
		    while (it1.hasNext() && iguales) {
		    	TVenta elem1 = it1.next();
		    	TVenta elem2 = it2.next();
		
		        if (!elem1.equals(elem2)) {
		        	 iguales = false;
		        }
		       
		    }
		    return iguales;
		}
		
		
	}
}

