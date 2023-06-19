package MarcaProveedor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaProveedores.DAOProveedorMarca;
import Integracion.Proveedores.DAOProveedores;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;

public class SAProveedorMarcaTest {
	@Test
	public void Test(){
		this.vaciarBaseDatos();	
		
		TProveedor p1 = new TProveedor("Juan", 1, true);
		TProveedor p2 = new TProveedor("Mateo", 2, true);
		
		
		TMarca m1 = new TMarca("Adidas", 1, true);
		TMarca m2 = new TMarca("Nike", 2, true);
		
		SAMarca sam = FactoriaNegocio.getInstance().generaSAMarca();
		SAProveedores sap = FactoriaNegocio.getInstance().generaSAProveedor();
		
		TProveedorMarca pm = new TProveedorMarca(1, 1, true);
		
		//Compruebo que no valga hacer una relacion de marcas y prov inexistentes
		int res= sam.addProveedorToMarca(pm);
		assert(res==-1);
		
		//Creo los prov y las marcas
		sam.create(m1);
		
		//Compruebo q no funciona con un prov inventao
		res= sam.addProveedorToMarca(pm);
		assert(res==-1);
		
		sam.create(m2);
		sap.create(p1);
		
		//Compruebo q no funciona con una marca inve
		res= sam.addProveedorToMarca(new TProveedorMarca(1, 3, true));
		assert(res==-1);
		
		sap.create(p2);
		
		//Añado relacion 1-1
		res= sam.addProveedorToMarca(pm);
		assert(res==1);
		//Vale una sola marca
		Collection<TMarca> lista = sam.readByProveedor(1);		
		assert(lista.size()==1 && lista.contains(m1));
		
		//VAle un solo prov
		Collection<TProveedor> lp = sap.readByMarca(1);		
		assert(lp.size()==1 && lista.contains(m1));
		
		//Añado relacion 2-1
		pm.setIdProveedor(2);
		res = sam.addProveedorToMarca(pm);
		assert(res==1);
		lista = sam.readByProveedor(2);		
		assert(lista.size()==1 && lista.contains(m1));
		
		//Leo por marca 1
		lp = sap.readByMarca(1);		
		assert(lp.size()==2 && lp.contains(p1) && lp.contains(p2));
		
		//Elimino marca y compruebo que ya no existen sus relaciones
		res=sam.delete(1);
		lp = sap.readByMarca(1);		
		assert(lp==null && res==1);		
		
		//Añado relacion de marca eliminada
		res = sap.addMarcaToProveedor(new TProveedorMarca(1, 1, true));
		assert(res==-1);
		
		//Añado marca y compruebo q sus relaciones anteriores estan eliminadas
		res = sam.create(m1);
		lp = sap.readByMarca(1);
		assert(res==1 && lp==null);
		
		//Elimino Proveedor y compruebo que ya no existen sus relaciones
		res= sap.delete(1);
		lista = sam.readByProveedor(1);
		assert(lista==null && res==1);
		
		//Añado relacion de prov eliminado
		res = sap.addMarcaToProveedor(new TProveedorMarca(1, 1, true));
		assert(res==-1);
		
		//Añado proveedor y compruebo q sus relaciones anterires eestan eliminadas
		res = sap.create(p1);
		lista = sam.readByProveedor(1);
		assert(res==1 && lista==null);
		
		//Añado relacion existente 2-1
		res = sam.addProveedorToMarca(pm);
		lista= sam.readByProveedor(2);
		assert(res==1 && lista.contains(m1));
		
		//Añado dos marcas
		pm.setIdMarca(2);
		res = sap.addMarcaToProveedor(pm);
		lista = sam.readByProveedor(2);
		assert(res==1 && lista.contains(m1) && lista.contains(m2));
		
		//Añado relacion existente
		res= sap.addMarcaToProveedor(pm);
		assert(res==-1);
		
	}

	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Proveedores.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
		
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Marca.txt")))){
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
