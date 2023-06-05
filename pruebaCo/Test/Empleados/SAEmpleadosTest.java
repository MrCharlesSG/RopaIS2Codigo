package Empleados;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoTC;
import Negocio.Empleado.TEmpleadoTP;
import Negocio.FactoriaNegocio.FactoriaNegocio;

public class SAEmpleadosTest {
	private SAEmpleado sa=FactoriaNegocio.getInstance().generaSAEmpleado();
	private String d1="51541411Y", d2="22222222Y";
	private Collection<TEmpleado> lista=new ArrayList<TEmpleado>();
	private TEmpleado emp1=new TEmpleadoTC("Juan", "Lucas", "Malaquito", d1, 515151, 1, true, 1300, 200);
	private TEmpleado emp2=new TEmpleadoTP("Manuel", "Antonio", "Manguito", d2, 5151251, 2, true, 50, 12 );
	
	@Test
	public void test(){
		this.vaciarBaseDatos();
		//Crear
		int a= sa.create(emp1);
		lista.add(emp1);
		assert(a==1);
		TEmpleado aux = sa.read(1);
		assert(aux.equals(emp1));
		aux = sa.read(2);
		assert(aux==null);
		
		a = sa.create(emp1);
		assert(a==-1);
		
		
		a=sa.create(emp2);
		lista.add(emp2);
		assert(a==2);
		a = sa.create(emp2);
		assert(a==-1);
		aux = sa.read(2);
		assert(aux.equals(emp2));

		aux=sa.readByName(d2);
		assert(aux.equals(emp2));
		
		aux=sa.readByName(d1);
		assert(aux.equals(emp1));
		
		Collection<TEmpleado> listaAux = sa.readAll();
		assert(lista.equals(listaAux));
		
		//Update
		emp1=new TEmpleadoTC("Iñaqui", "Wito", "Maquina", d1, 515151, 1, true,  1300, 250 );
		a=sa.update(emp1);
		assert(a==1);
		emp2= new TEmpleadoTP("Lamban", "Pretorico", "Manguito", d2, 5151251, 2, true, 40, 28 );
		a=sa.update(emp2);
		assert(a==2);
		lista.clear();
		lista.add(emp1);
		lista.add(emp2);
		listaAux = sa.readAll();
		assert(lista.equals(listaAux));
		
		//delete
		a=sa.delete(2);
		assert(2==a);
		a=sa.delete(1);
		assert(1==a);
		listaAux=sa.readAll();
		assert(listaAux.size()==2);
		a=sa.create(emp1);
		assert(1==a);
		//this.vaciarBaseDatos();
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