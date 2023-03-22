package Marca;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import Negocio.FactoriaNegocio.FactoriaNegocioImp;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.SAMarcaImp;
import Negocio.MarcaNegocio.TMarca;

@RunWith(JUnit4.class)
public class SAMarcaTest {
	private SAMarca saMarca = FactoriaNegocioImp.getInstance().generaSAMarca();
	private TMarca tMarca = new TMarca("Julian", 2, 1);
	
	@Test
	public void create_Test(){
		int a =saMarca.create(tMarca);
		assertEquals(a,2);
		
		a= saMarca.create(tMarca);
		assertEquals(a,-1);
		
		a= saMarca.create(tMarca);
		assertEquals(a,-1);
	}
	
	@Test
	public void read_Test(){
		//lectura de la primera marca
		int a = saMarca.create(tMarca);
		assertEquals(a, 2);
		TMarca aux = saMarca.read(2);
		assertEquals(aux,tMarca);
		//lectura de una marca inexistente
		aux = saMarca.read(3);
		assertEquals(aux, null);
		//lectura de un sa recien eliminado
		a= saMarca.delete(2);
		assertEquals(a, 2);
		aux = saMarca.read(2);
		assertEquals(aux, null);
	}
	
	@Test
	public void readAll_Test(){
		int a = saMarca.create(tMarca);
		TMarca almendra=new TMarca("Almendra", 3, 1);
		int b = saMarca.create(almendra);
		assertEquals(a, 2);
		assertEquals(b, 3);
		Collection<TMarca> todos = saMarca.readAll();
		Collection<TMarca> real = new ArrayList<TMarca>();
		real.add(tMarca);
		real.add(almendra);
		for (TMarca tMarca : real) {
			
		}
		
	}
	
	@Test
	public void update_Test(){
		
	}
	
	@Test
	public void delete_Test(){
		
	}
	
	@Test
	public void readByName_Test(){
		
	}

}
