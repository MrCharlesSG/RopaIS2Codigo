package Proveedores;

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
import Integracion.FactoriaIntegracion.FactoriaIntegracionImp;
import Integracion.Proveedores.DAOProveedores;
import Negocio.Proveedor.TProveedor;

public class DAOProveedoresTest {

	private DAOProveedores dao =FactoriaIntegracionImp.getInstance().generaDAOProveedor();
	private TProveedor tProveedor=new TProveedor("Xyon",1,true);
	private TProveedor tProveedor2=new TProveedor("Tiberias",1,true);
	private Collection<TProveedor>proveedorColeccion=new ArrayList<TProveedor>();
	
	@Test
	public void test(){

		int test;
		this.vaciarBaseDatos();
		/*por ID y nombre*/
		test=dao.create(tProveedor);
		assertEquals("Error al crear un proveedor",1, test);
		
		this.proveedorColeccion.add(tProveedor);
		TProveedor TP=this.dao.read(1);
		assertEquals("Error al leer un proveedor",TP.getNombre(),this.tProveedor.getNombre());
		assertEquals("Error al crear un proveedor",TP.getId(),this.tProveedor.getId());

			/*crear otro proveedor y leerlo por ID y nombre*/
		int test3=this.dao.create(tProveedor2);
		assertEquals("No se ha podido crear el segundo proveedor",test3,2);
		TP=this.dao.read(2);
		assertEquals("No se ha podido leer el segundo proveedor",TP.getNombre(),this.tProveedor2.getNombre());
		
		TP=this.dao.readByName("Tiberias");
		assertEquals("No se ha podido leer el segundo proveedor",TP.getNombre(),this.tProveedor2.getNombre());

		/*Comprobamos el read all de los proveedores anadidos*/
		this.proveedorColeccion.add(tProveedor2);
		Collection<TProveedor>coll=this.dao.readAll();
		
		if(this.equalsCollection(coll,this.proveedorColeccion)){
			fail("No funciona para dos proveedores");
		}

		/*Eliminar proveedores creados*/
		test=this.dao.delete(1);
		assertEquals("No se ha podido eliminar",test,1);
		
		int test2=this.dao.delete(2);
		assertEquals("No se ha podido eliminar",test2,2);

		/*Para 0 proveedores*/
		assertEquals("No funciona para 0 proveedores",this.dao.readAll().size(),2);
	}

	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Proveedores.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
	}

	private boolean equalsCollection(Collection<TProveedor> coll, Collection<TProveedor> proveedorColeccion2) {
		if(coll.size()!=proveedorColeccion2.size()){
			return false;
		}
		else{
			Iterator<TProveedor>it1=coll.iterator();
			Iterator<TProveedor>it2=proveedorColeccion2.iterator();
			
			while(it1.hasNext()&&true){
				TProveedor prov1=it1.next();
				TProveedor prov2=it2.next();
				
				if(prov1!=prov2){
					return false;
				}
			}
		}
		return false;
	}
}

