package Empleados;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

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
		DAOEmpleado dao = FactoriaIntegracion.getInstance().generaDAOEmpleado();
		//TEmpleado String nombre, String apellido1, String apellido2, String DNI, int tfno, int ID, boolean activo
		//Leer na
		assert(dao.read(1)==null);
		//crear1 empleado
		TEmpleado emp1=new TEmpleadoTC("Juan", "Lucas", "Malaquito", "5151Y", 515151, 1, true, 1200, 100 );
		lista.add(emp1);
		int res = dao.create(emp1);
		assertEquals("No se ha creado la marca correctamente",res,1);
		assert(dao.read(2)==null);
		
		//Crear 2 empleados
		TEmpleado emp2=new TEmpleadoTP("Manuel", "Antonio", "Manguito", "2Y", 5151251, 2, true,50, 10 );
		lista.add(emp2);
		res = dao.create(emp2);
		assertEquals("No se ha creado la marca correctamente",res,2);
		
		//Leer los dos empleados
		listaAux= dao.readAll();
		assert(lista.equals( listaAux));
		
		//leer por nombre
		TEmpleado name= dao.readByName("5151Y");
		assert(name.equals(emp1));
		name = dao.readByName("2Y");
		assert(name.equals(emp2));
		
		//Delete
		int aux = dao.delete(1);
		assertEquals(aux, 1);
		name= dao.readByName("5151Y");
		assert(!name.isActivo());
		
		
		
		//modificar
		//modificar apellido1
		emp2.setApellido1("Julian");
		aux = dao.update(emp2);
		assert(aux==2);
		TEmpleado a1=dao.readByName("2Y");
		TEmpleado a2 = dao.read(2);
		assert(a1.equals(emp2) && a2.equals(emp2));
		//modificar apellido2
		emp2.setApellido2("Manuel");
		aux = dao.update(emp2);
		assert(aux==2);
		assert(dao.readByName("2Y").equals(emp2) && dao.read(2).equals(emp2));
		
		//modificar todo
		emp2= new TEmpleadoTP("Lamban", "Joper", "Lunin", "2Y", 33, 2, true,20, 12 ); 
		aux=dao.update(emp2);
		assert(dao.readByName("2Y").equals(emp2) && dao.read(2).equals(emp2));
		TEmpleado la = dao.read(emp2.getID());
		assert(emp2.equals(la));	
		
		//Delete all
		aux = dao.delete(2);
		assertEquals(aux, 2);
		lista.clear();
		listaAux = dao.readAll();
		for(TEmpleado e: listaAux) {
			assert(!e.isActivo());
		}
		
		//vaciarBaseDatos();
	}
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Empleados.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
	}	
}

