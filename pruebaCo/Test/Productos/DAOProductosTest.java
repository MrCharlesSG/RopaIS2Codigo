package Productos;

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
	import org.junit.runner.RunWith;
	import org.junit.runners.JUnit4;
	
	import Integracion.Producto.DAOProducto;
	import Integracion.Producto.DAOProductoIMP;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Producto.TProducto;
	
	@RunWith(JUnit4.class)
public class DAOProductosTest {
		@Test
		public void test(){
			this.vaciarBaseDatos();
			DAOProductoIMP dproducto= new DAOProductoIMP();
			TProducto res=dproducto.read(-1);
			
			assertEquals(null, res);
			
			TProducto tVaqueros=new TProducto("vaqueros", 1, 38, 2, "pantalones",0 );
			
			int id= dproducto.create(tVaqueros);
			assertEquals(1, id);
			res=dproducto.read(1);
			
			assertEquals("vaqueros", res.getNombre());
			
			int deletee=dproducto.delete(1);
			assertEquals(1, deletee);
			
			Collection<TProducto> productos2=new ArrayList<TProducto>();
			productos2=dproducto.readAll();
			
			assertEquals(productos2.size(), 1);
			
			
			Collection<TProducto> productos=new ArrayList<TProducto>();
			TProducto prod1= new TProducto("vaqueros", 1, 38, 0, "pantalones", 0);
			TProducto prod2= new TProducto("chandal", 1, 38, 1, "pantalones", 0);
			TProducto prod3= new TProducto("cargos", 1, 38, 2, "pantalones", 0);
			
			dproducto.create(prod1);
			dproducto.create(prod2);
			dproducto.create(prod3);
			
			Collection<TProducto> aux=new ArrayList<TProducto>();
			aux.add(prod1);
			aux.add(prod2);
			aux.add(prod3);
			
			productos=dproducto.readAll();
			
			assertEquals(true, equalsCollection(productos, aux));
			
			int updatee=dproducto.update(prod3);
			assertNotEquals(-1, updatee);
			
			productos=dproducto.readAll();
			assertEquals(true, equalsCollection(productos, aux));
			
			this.vaciarBaseDatos();
			

		}
		
		private void vaciarBaseDatos() {
			try(Writer w=new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream("Productos.txt")))){
				w.write("");
			
		}catch (IOException e) {
			
		}
		}
		
		private boolean equalsCollection(Collection<TProducto> a, Collection<TProducto> b){

			if (a.size() != b.size()) {
				return false;
			} else {
			    Iterator<TProducto> it1 = a.iterator();
			    Iterator<TProducto> it2 = b.iterator();
			    boolean iguales = true;
			
			    while (it1.hasNext() && iguales) {
			        TProducto elem1 = it1.next();
			        TProducto elem2 = it2.next();
			
			        if (!elem1.getNombre().equals(elem2.getNombre()) || elem1.getCantidad()!= elem2.getCantidad()  
			        	||elem1.getTalla()!= elem2.getTalla() || elem1.getIdProducto()!=elem2.getIdProducto()
			        	||elem1.getCategoria().equalsIgnoreCase(elem2.getCategoria())||elem1.getIdMarca()!=elem2.getIdMarca()) {
	                    iguales = false;
	                }
			    }
			    return iguales;
			}
	    }
		
}
