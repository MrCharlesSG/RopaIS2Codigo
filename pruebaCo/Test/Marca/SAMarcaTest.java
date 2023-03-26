package Marca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
	private TMarca tMJuli = new TMarca("Julian", 1, 1);
	private TMarca tMAlmd = new TMarca("Almendra", 2, 1);
	private Collection<TMarca> mockCollec = new ArrayList<TMarca>();
	
	@Test
	public void basicBehaviour_Test(){
		int aux1, aux2, aux3;

		//crear una marca y leerla por id y por nombre
		aux1=saMarca.create(tMJuli);
		assertEquals("No se ha creado la marca correctamente",aux1,1);
		this.mockCollec.add(tMJuli);
		TMarca h=this.saMarca.read(1);
		assertEquals("No se ha leido correctamente por id de marca", h.getNombre(), this.tMJuli.getNombre());
		assertEquals("No se ha leido correctamente por id de marca", h.getID(), this.tMJuli.getID());
		TMarca tMAux = this.saMarca.readByName("Julian");
		assertEquals("No se ha leido correctamente por id de marca", tMAux.getNombre(), this.tMJuli.getNombre());
		assertEquals("No se ha leido correctamente por id de marca", tMAux.getID(), this.tMJuli.getID());
		
		//crear una marca con el mismo nombre
		tMAux= new TMarca("Julian", 2, 1);
		aux3 = saMarca.create(tMAux);
		assertEquals("Se ha creado una marca con el mismo nombre", aux3, -1);
		
		//crear una marca existente
		aux1=saMarca.create(tMJuli);
		assertEquals("Se ha creado una marca ya existente",aux1 ,-1);
		
		//crear una segunda marca y leerla por id y por nombre
		aux2=saMarca.create(tMAlmd);
		assertEquals("No se ha podido crear una segunda marca", aux2, 2);
		h= this.saMarca.read(2);
		assertEquals("No se ha leido correctamente la 2da marca",h.getNombre(), this.tMAlmd.getNombre());
		assertEquals("No se ha leido correctamente la 2da marca",h.getNombre(), this.tMAlmd.getNombre());
	
		h= this.saMarca.readByName("Almendra");
		assertEquals("No se ha leido correctamente la 2da marca",h.getNombre(), this.tMAlmd.getNombre());
		assertEquals("No se ha leido correctamente la 2da marca",h.getNombre(), this.tMAlmd.getNombre());
		
		//Comprobamos el read all de las dos marcas añadidas
		this.mockCollec.add(tMAlmd);
		Collection<TMarca> readAllCollec = saMarca.readAll();
		if(this.equalsCollection(readAllCollec, mockCollec)){
			fail("El readAll no funciona correctamente para 2 marcas");
		}
		
		//eliminar las dos marcas creadas
		aux1= saMarca.delete(1);
		assertEquals("No se ha eliminado correctamente",aux1, 1);
		aux2 = saMarca.delete(2);
		assertEquals("No se ha eliminado correctamente", aux2, 2);
		
		//readAll de 0 marcas
		assertEquals("Read all no funciona para 0 marcas", this.saMarca.readAll().size(), 0);
		
		//Añadir 5 marcas
	/*	String nombre= "Manolo";
		this.mockCollec.clear();
		int i=1;
		while(i<6){
			TMarca abe, man= new TMarca(nombre, i, 1);
			aux1= saMarca.create(man);
			assertEquals("Ha fallado la creacion de la marca "+ i+ " .",aux1, i);
			abe = saMarca.read(i);
			assertEquals("No se ha podido leer la marca "+i, abe, man);
			this.mockCollec.add(man);
			nombre+="a";	
		}
		readAllCollec = saMarca.readAll();
		if(this.equalsCollection(readAllCollec, mockCollec)){
			fail("El readAll no funciona correctamente para 5 marcas");
		}*/

		//crear marca sin nombre
		assertEquals("Se ha creado una marca sin nombre", this.saMarca.create(new TMarca("", 2, 1)), -1);
		
		//read de un id nulo
		assertEquals("Se ha leido un id invalido", this.saMarca.read(100), null);
		
	}
	
	private boolean equalsCollection(Collection<TMarca> a, Collection<TMarca> b){

		if (a.size() != b.size()) {
			return false;
		} else {
		    Iterator<TMarca> it1 = a.iterator();
		    Iterator<TMarca> it2 = b.iterator();
		    boolean iguales = true;
		
		    while (it1.hasNext() && iguales) {
		        TMarca elem1 = it1.next();
		        TMarca elem2 = it2.next();
		
		        if (elem1!=elem2) {
		            iguales = false;
		        }
		    }
		    return iguales;
		}
		
		
	}

}
