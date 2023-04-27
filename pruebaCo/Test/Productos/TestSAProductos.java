package Productos;


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
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;


	@RunWith(JUnit4.class)
	public class TestSAProductos {

		private SAProducto saProducto = FactoriaNegocioImp.getInstance().generaSAProducto();//teneis que creaar este metodo 
		private TProducto tPant = new TProducto("Pantalones azules", 10, 1,1,"pantalones",1);
		private TProducto tCami = new TProducto("Camiseta blanca", 5, 1,2,"Camiseta",1);
		
		private Collection<TProducto> mockCollec = new ArrayList<TProducto>();
		
		@Test
		public void basicBehaviour_Test(){
			int aux1, aux2, aux3;
			this.vaciarBaseDatos();
			//crear un producto y lo lee por id y por nombre
			SAMarca saMarca=FactoriaNegocio.getInstance().generaSAMarca();
			TMarca marca=new TMarca("Santi",0,0,0);
			saMarca.create(marca);
			aux1=saProducto.create(tPant);
			assertEquals("No se ha creado un producto correctamente",1, aux1);
			this.mockCollec.add(tPant);
			TProducto h=this.saProducto.read(1);
			// siguiendo las diapositivas yo creo que read y readByName no se les pasa un TProducto por eso lo he dejado asi
			assertEquals("No se ha leido correctamente por id de producto", this.tPant.getNombre(), h.getNombre());
			assertEquals("No se ha leido correctamente por id de producto", this.tPant.getIdProducto(), h.getIdProducto());
			TProducto tPAux = this.saProducto.readByName("Pantalones azules");
			assertEquals("No se ha leido correctamente por id de producto", tPAux.getNombre(), this.tPant.getNombre());
			assertEquals("No se ha leido correctamente por id de producto", tPAux.getIdProducto(), this.tPant.getIdProducto());
			
			//crear un Producto con el mismo nombre y mismo id de marca
			tPAux= new TProducto("Pantalones azules", 1, 1,0,"M",0);
			aux3 = saProducto.create(tPAux);
			assertEquals("Se ha creado un producto con el mismo nombre", -1, aux3);
			//id marca diferente por lo que no deberia dar error
			/*marca=new TMarca("paco",0,0,1);
			saMarca.create(marca);
			tPAux= new TProducto("Pantalones azules", 0, 1,2,"M",1);
			aux3 = saProducto.create(tPAux);
			assertEquals("Ha fallado algo al crear un producto con el mismo nombre pero con id de marca diff", aux3, 2);
			*/
			
			//crear un producto existente
			aux1=saProducto.create(tPant);
			assertEquals("Se ha creado un producto ya existente",aux1 ,-1);
			
			//crear un segunda producto y lo leer por id y por nombre
			aux2=saProducto.create(tCami);
			assertEquals("No se ha podido crear una segunda marca", 2, aux2);
			h= this.saProducto.read(2);
			assertEquals("No se ha leido correctamente el 2do producto",h.getNombre(), this.tCami.getNombre());
			assertEquals("No se ha leido correctamente el 2do producto",h.getIdProducto(), this.tCami.getIdProducto());
		
			h= this.saProducto.readByName("Camiseta blanca");
			assertEquals("No se ha leido correctamente el 2do producto",h.getNombre(), this.tCami.getNombre());
			assertEquals("No se ha leido correctamente el 2do producto",h.getIdProducto(), this.tCami.getIdProducto());
			
			//Comprobamos el read all de los dos productos añadidos
			this.mockCollec.add(tCami);
			Collection<TProducto> readAllCollec = saProducto.readAll();

			assert(mockCollec.equals(readAllCollec));
			
			//eliminar las dos marcas creadas
			aux1= saProducto.delete(1);
			assertEquals("No se ha eliminado correctamente",1, aux1);
			aux2 = saProducto.delete(2);
			assertEquals("No se ha eliminado correctamente", 2, aux2);
			
			//readAll de 0 marcas
			assertEquals("Read all no funciona para 1 productos", this.saProducto.readAll().size(), 2);
			
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

			//crear Producto sin nombre
			assertEquals("Se ha creado una marca sin nombre", this.saProducto.create(new TProducto("", 2, 1,4,"M",0)), -1);
			//crear producto sin talla
			assertEquals("Se ha creado una marca sin nombre", this.saProducto.create(new TProducto("sudadera", 2, 1,4,"",0)), -1);
			//crear producto con id de marca no existente
			assertEquals("Se ha creado una marca sin nombre", this.saProducto.create(new TProducto("sudadera", 2, 1,4,"M",50)), -1);
			
			//read de un id nulo
			assertEquals("Se ha leido un id invalido",null, this.saProducto.read(100));
			vaciarBaseDatos();
			
		}
		
		private void vaciarBaseDatos() {
			try(Writer w=new BufferedWriter(
						new OutputStreamWriter(
						new FileOutputStream("Marcas.txt")))){
				
					w.write("");
				}
		catch (IOException e1) {
			
		}
		try(Writer s=new BufferedWriter(
							new OutputStreamWriter(
							new FileOutputStream("Productos.txt")))){
			s.write("");
							
					}catch (IOException e) {
						
					}
				
			
			}

	}
