package Ventas;


import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

import Integracion.Ventas.DAOVentas;
import Integracion.Ventas.DAOVentasIMP;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Ventas.TVenta;

public class DaoVentaTest {

	
	@Test
	public void test(){
		this.vaciarBaseDatos();
		DAOVentasIMP dventa=new DAOVentasIMP();
		TVenta res=dventa.read(20);
		
		assertEquals(res, null);
		
		TVenta tventa=new TVenta (1, 1, 1, 10, 2, true);
		
		int id=dventa.create(tventa);
		assertEquals(1, id);
		TVenta venta1=dventa.read(1);
		
		assertEquals(venta1, tventa);
		
		Collection<TVenta> ventas2 =new ArrayList<TVenta>();
        ventas2 = dventa.readAll();

        assertEquals(ventas2.size(),1);
        
        
        Collection<TVenta> ventas=new ArrayList<TVenta>();
        TVenta tventa1=new TVenta(2, 1, 2, 40, 5, true);
        TVenta tventa2=new TVenta (3, 2, 3, 10, 1, true);
        TVenta tventa3=new TVenta(4, 1, 2, 80, 9, true);
        
        //comprobar el readAll
        dventa.create(tventa1);
        dventa.create(tventa2);
        dventa.create(tventa3);
        Collection<TVenta> aux=new ArrayList<TVenta>();
        aux.add(tventa1);
        aux.add(tventa2);
        aux.add(tventa3);
        ventas=dventa.readAll();
        
        assertEquals(true, equalsCollection(aux, ventas));
        
        
        this.vaciarBaseDatos();
        

		}
	
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Marcas.txt")))){
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
		
		        if (elem1.get_id()!=elem2.get_id()|| elem1.get_id_empleado()!=elem2.get_id_empleado()||
		        	elem1.get_id_cliente()!=elem2.get_id_cliente()|| elem1.get_precio()!=elem2.get_precio()||
		        	elem1.get_contador()!=elem2.get_contador()|| elem1.get_activo()!=elem2.get_activo()) {
		            iguales = false;
		        }
		    }
		    return iguales;
		}
		
		
	}
}

