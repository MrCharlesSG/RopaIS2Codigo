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

import Integracion.Ventas.DAOVentasIMP;
import Negocio.Clientes.TCliente;
import Negocio.Clientes.TClienteNormal;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoTC;
import Negocio.Ventas.TVenta;

public class DaoVentaTest {

	
	@Test
	public void test(){
		this.vaciarBaseDatos();
		TEmpleado emp1=new TEmpleadoTC("Juan", "Lucas", "Malaquito", "06655387s", 515151, 1, true, 1300, 200);
		TCliente cl1=new TClienteNormal(true, "Diaz", "Diaz", "12234324A", 1, "Dani", 613456987, false,"Madrid");;
		DAOVentasIMP dventa=new DAOVentasIMP();
		TVenta res=dventa.read(20);
		
		assertEquals(res, null);
		
		TVenta tventa=new TVenta (1, 1, 1, 10, true);
		
		int id=dventa.create(tventa);
		assertEquals(1, id);
		TVenta venta1=dventa.read(1);
		assertEquals(venta1, tventa);
		
		TVenta venta3=dventa.read(8);
		assertEquals(null, venta3);
		
		
		Collection<TVenta> ventas2 =new ArrayList<TVenta>();
        ventas2 = dventa.readAll();

        assertEquals(ventas2.size(),1);
        
        
        Collection<TVenta> ventas=new ArrayList<TVenta>();
        //int id, id_emp, id_cl, double prec, boolean activo
        TVenta tventa1=new TVenta(2, 1, 1, 40, true);
        TVenta tventa2=new TVenta (3, 2, 3, 10, true);
        TVenta tventa3=new TVenta(4, 1, 2, 80, true);
        
        //comprobar el readAll
        dventa.create(tventa1);
        dventa.create(tventa2);
        dventa.create(tventa3);
        Collection<TVenta> aux=new ArrayList<TVenta>();
        aux.add(tventa);
        aux.add(tventa1);
        aux.add(tventa2);
        aux.add(tventa3);
        ventas=dventa.readAll();
        assert(ventas.equals(aux));
        
        //comprobar el readByEmpleado
        Collection<TVenta> collectionEmp=dventa.readByEmpleado(1);
        Collection<TVenta> auxEmp=new ArrayList<TVenta>();
        auxEmp.add(tventa);
        auxEmp.add(tventa1);
        auxEmp.add(tventa3);
        assertEquals(collectionEmp.size(), 3);
        assert(collectionEmp.equals(auxEmp));
        auxEmp.add(tventa2);
        assertEquals(false, equalsCollection(auxEmp, collectionEmp));

        //comprobar el readByCliente
        Collection<TVenta> collectionCl=dventa.readByCliente(1);
        assertEquals(collectionCl.size(),2);
        Collection<TVenta> auxCl=new ArrayList<TVenta>();
        auxCl.add(tventa);
        auxCl.add(tventa1);
        assert(collectionCl.equals(auxCl));
        auxCl.add(tventa2);
        assertEquals(false, equalsCollection(auxCl, collectionCl));
        
      //  this.vaciarBaseDatos();
        

		}
	
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Clientes.txt")))){
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

