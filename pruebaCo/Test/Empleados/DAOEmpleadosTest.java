package Empleados;
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

import Integracion.Empleados.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoTC;
import Negocio.Empleado.TEmpleadoTP;

public class DAOEmpleadosTest {
	
	private Collection<TEmpleado> lista=new ArrayList<TEmpleado>();
	private Collection<TEmpleado> listaAux;

	@Test
	public void test(){
		//vaciar txt
		this.vaciarBaseDatos();
		//TEmpleado String nombre, String apellido1, String apellido2, String DNI, int tfno, int ID, boolean activo
		//crear1 empleado
		DAOEmpleado dao = FactoriaIntegracion.getInstance().generaDAOEmpleado();
		TEmpleado emp1=new TEmpleadoTC("Juan", "Antonio", "Manguito", "5151Y", 515151, 1, true );
		lista.add(emp1);
		int res = dao.create(emp1);
		assertEquals("No se ha creado la marca correctamente",res,1);
		
		//Crear 2 empleados
		TEmpleado emp2=new TEmpleadoTP("Manuel", "Antonio", "Manguito", "51511Y", 5151251, 2, true );
		lista.add(emp2);
		int res2 = dao.create(emp2);
		assertEquals("No se ha creado la marca correctamente",res2,2);
		
		//Leer los dos empleados
		listaAux= dao.readAll();
		assert(equalsCollection(lista, listaAux));
		
		//leer
		listaAux= dao.readAll();
		assert(equalsCollection(lista, listaAux));
		
		//leer por nombre
		TEmpleado name= dao.readByName("5151Y");
		assert(name.equals(emp1));
		
		//Delete
		int aux = dao.delete(1);
		assertEquals(aux, 1);
		name= dao.readByName("5151Y");
		assert(name==null);
		
		//Delete all
		aux = dao.delete(2);
		assertEquals(aux, 2);
		lista.clear();
		assert(equalsCollection(lista, dao.readAll()));
		
		emp2.setNombre("Manuel");
		aux=dao.create(emp2);
		lista.add(emp2);
		assertEquals(aux, 2);
		assert(equalsCollection(lista, dao.readAll()));
		
		//modificar
		emp2.setApellido1("Julian");
		aux = dao.update(emp2);
		assert(aux==emp2.getID());
		
		emp2.setApellido2("Manuel");
		aux = dao.update(emp2);
		assert(aux==emp2.getID());
		
		TEmpleado la = dao.read(emp2.getID());
		assert(emp2.equals(la));
		
		la=dao.readByName("51511Y");
		assert(emp2.equals(la));
		
		
		
		vaciarBaseDatos();
	}
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Empleados.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
	}
	
	boolean equalsCollection(Collection <TEmpleado>a, Collection <TEmpleado> b){
		if (a.size()!=b.size())
			return false;
		else{
			Iterator<TEmpleado> it1=a.iterator();
			Iterator<TEmpleado> it2=b.iterator();
			boolean iguales=true;
			while (it1.hasNext()&& it2.hasNext()&& iguales){
				TEmpleado elem1=it1.next();
				TEmpleado elem2=it2.next();
				if(!elem1.equals(elem2)){
					iguales=false;
				}
			}
			return iguales;
		}
	}
	
	
}

