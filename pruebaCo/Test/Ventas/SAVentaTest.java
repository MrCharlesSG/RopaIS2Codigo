package Ventas;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

import Negocio.FactoriaNegocio.FactoriaNegocioImp;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Ventas.SAVentas;
import Negocio.Ventas.SAVentasIMP;
import Negocio.Ventas.TVenta;

public class SAVentaTest {

	private SAVentas saVenta=FactoriaNegocioImp.getInstance().generaSAVentas();
	private TVenta venta1=new TVenta(1, 1, 1, 10, 2, true);
	private TVenta venta2=new TVenta(2, 1, 1, 15, 3, true);
	private Collection<TVenta> mockCollec=new ArrayList<TVenta>();
	
	
	@Test
	public void basicBehaviour_Test(){
		int aux1, aux2, aux3;
		this.vaciarBaseDatos();
		//crear marca y leer por id
		
		aux1=saVenta.create(venta1);
		assertEquals(aux1, 1);
		this.mockCollec.add(venta1);
		TVenta v=this.saVenta.read(1);
		assertEquals(v, venta1);
		
		aux1=saVenta.create(venta2);
		assertEquals(aux1, 2);
		v=this.saVenta.read(2);
		assertEquals(v, venta2);
		
		//compruebo el readByEmpleado
		Collection<TVenta> empleado1=new ArrayList<TVenta>();
		empleado1.add(venta1);
		empleado1.add(venta2);
		assertEquals(equalsCollection(this.saVenta.readByEmpleado(1), empleado1), true);
		
		//compruebo el readByCliente
		Collection<TVenta> cliente1=new ArrayList<TVenta>();
		cliente1.add(venta1);
		cliente1.add(venta2);
		assertEquals(equalsCollection(this.saVenta.readByCliente(1), cliente1), true);
		
		
		//crear una venta existente
		aux3=saVenta.create(venta1);
		assertEquals(aux3, -1);
		
		//comprobar el readAll
		this.mockCollec.add(venta2);		
		assert(mockCollec.equals(this.saVenta.readAll()));

		
	}
	
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Ventas.txt")))){
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

