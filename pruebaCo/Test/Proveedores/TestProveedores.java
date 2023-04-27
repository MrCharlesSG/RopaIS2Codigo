package Proveedores;

import org.junit.runners.JUnit4;

import Negocio.FactoriaNegocio.FactoriaNegocioImp;

import org.junit.runner.RunWith;

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

import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;

@RunWith(JUnit4.class)
public class TestProveedores {
	
	private SAProveedores saProveedores=FactoriaNegocioImp.getInstance().generaSAProveedor();
	private TProveedor tProveedor=new TProveedor("Xyon",1,new ArrayList<Integer>(),false);
	private TProveedor tProveedor2=new TProveedor("Tiberias",1,new ArrayList<Integer>(),false);
	private Collection<TProveedor>proveedorColeccion=new ArrayList<TProveedor>();
	
	@Test
	public void Test(){
		int test;
		this.vaciarBaseDatos();
		/*por ID y nombre*/
		test=saProveedores.create(tProveedor);
		assertEquals("Error al crear un proveedor",1, test);
		
		this.proveedorColeccion.add(tProveedor);
		TProveedor TP=this.saProveedores.read(1);
		assertEquals("Error al leer un proveedor",TP.getNombre(),this.tProveedor.getNombre());
		assertEquals("Error al crear un proveedor",TP.getId(),this.tProveedor.getId());
		
		TProveedor TP2=this.saProveedores.readByName("Xyon");
		assertEquals("Error al leer un proveedor",TP2.getNombre(),this.tProveedor.getNombre());
		assertEquals("Error al crear un proveedor",TP2.getId(),this.tProveedor.getId());
		
		/*con mismo nombre*/
		TP2=new TProveedor("Xyon", 1, new ArrayList<Integer>(), false);
		int test2=this.saProveedores.create(TP2);
		assertEquals("Se ha creado un proveedor con el mismo nombre",test2,-1);
		
		/*proveedor ya existente*/
		test=this.saProveedores.create(tProveedor);
		assertEquals("Se ha creado un proveedor que ya existe",test,-1);
		
		/*crear otro proveedor y leerlo por ID y nombre*/
		int test3=this.saProveedores.create(tProveedor2);
		assertEquals("No se ha podido crear el segundo proveedor",test3,2);
		TP=this.saProveedores.read(2);
		assertEquals("No se ha podido leer el segundo proveedor",TP.getNombre(),this.tProveedor2.getNombre());
		
		TP=this.saProveedores.readByName("Tiberias");
		assertEquals("No se ha podido leer el segundo proveedor",TP.getNombre(),this.tProveedor2.getNombre());
		
		/*Comprobamos el read all de los proveedores anadidos*/
		this.proveedorColeccion.add(tProveedor2);
		Collection<TProveedor>coll=this.saProveedores.readAll();
		
		if(this.proveedorColeccion.equals(coll)){
			fail("No funciona para dos proveedores");
		}
		
		/*Eliminar proveedores creados*/
		test=this.saProveedores.delete(1);
		assertEquals("No se ha podido eliminar",test,1);
		
		test2=this.saProveedores.delete(2);
		assertEquals("No se ha podido eliminar",test2,2);
/*
		test3=this.saProveedores.delete(3);
		assertEquals("No se ha podido eliminar",test3,3);
	*/	
		/*Para 0 proveedores*/
		assertEquals("No funciona para 0 proveedores",this.saProveedores.readAll().size(),0);
		
		/*Crear proveedor sin nombre*/
		assertEquals("Se ha creado un proveedor sin nombre",this.saProveedores.create(new TProveedor("", 1, new ArrayList<Integer>(), false)),-1);
		
		/*Read de un ID nulo*/
		assertEquals("Se ha leido un ID invalido",this.saProveedores.read(1000),null);
	}

	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Proveedores.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
	}
	
}
