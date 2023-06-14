package Marca;

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

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocioImp;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.SAMarcaImp;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;

@RunWith(JUnit4.class)
public class SAMarcaTest {
		
	private SAMarca saMarca = FactoriaNegocioImp.getInstance().generaSAMarca();
	//String nombre, int ID, int cantidad, boolean activo
	private TMarca tMJuli = new TMarca("Julian", 1,true);
	private TMarca tMAlmd = new TMarca("Almendra", 2,true);
	private Collection<TMarca> mockCollec = new ArrayList<TMarca>();
	
	@Test
	public void basicBehaviour_Test(){
		int aux1, aux2, aux3;
		this.vaciarBaseDatos();
		//crear una marca y leerla por id y por nombre
		aux1=saMarca.create(tMJuli);
		assertEquals("No se ha creado la marca correctamente",aux1,1);
		
		this.mockCollec.add(tMJuli);
		TMarca h=this.saMarca.read(1);
		assert(h.equals(tMJuli));
		TMarca tMAux = this.saMarca.readByName("Julian");
		assert(tMAux.equals(tMJuli));
		
		//crear una marca con el mismo nombre
		tMAux= new TMarca("Julian", 2, true);
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
		assert(mockCollec.equals(readAllCollec));
		// comprobar si puedo dar de baja una marca asociada a productos
		
		 TProducto tPant = new TProducto("Pantalones azules", 30, 1,1,"pantalones",1, 10);
		 SAProducto saProducto = FactoriaNegocioImp.getInstance().generaSAProducto();
		 int idp=saProducto.create(tPant);
		 
		 aux1= saMarca.delete(1);
		 assertEquals("Se ha eliminado la marca cuando aun tenia productos asociados",aux1, -1);
		 saProducto.delete(idp);
		 
		 //eliminar las dos marcas creadas que no tienen productos asociados
		 
		aux1= saMarca.delete(1);
		assertEquals("No se ha eliminado correctamente",aux1, 1);
		aux2 = saMarca.delete(2);
		assertEquals("No se ha eliminado correctamente", aux2, 2);
		//crear marca sin nombre
		assertEquals("Se ha creado una marca sin nombre", this.saMarca.create(new TMarca("", 2, false)), -1);
		
		//read de un id nulo
		assertEquals("Se ha leido un id invalido", this.saMarca.read(100), null);
		vaciarBaseDatos();
	}
	
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Marcas.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Productos.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("ProveedorMarca.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
	}

}
