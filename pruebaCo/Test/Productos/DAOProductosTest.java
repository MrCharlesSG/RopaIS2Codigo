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
			
			TProducto tVaqueros=new TProducto("vaqueros", 0, 38, 1, "pantalones",0, 10);
			
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
			TProducto prod1= new TProducto("vaqueros", 1, 38, 2, "pantalones", 0, 10);
			TProducto prod2= new TProducto("chandal", 1, 38, 3, "pantalones", 1, 20);
			TProducto prod3= new TProducto("cargos", 1, 38, 4, "pantalones", 1, 30);
			
			dproducto.create(prod1);
			dproducto.create(prod2);
			dproducto.create(prod3);
			
			Collection<TProducto> aux=new ArrayList<TProducto>();
			aux.add(tVaqueros);
			aux.add(prod1);
			aux.add(prod2);
			aux.add(prod3);
			
			productos=dproducto.readAll();
			
			assert( aux.equals(productos));
			
			productos = dproducto.readByMarca(1);
			aux.clear();
			aux.add(prod2);
			aux.add(prod3);
			assert(aux.equals(productos));
			
			int updatee=dproducto.update(prod3);
			assertEquals(4, updatee);

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
		
}
