package MarcaProveedor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaProveedores.DAOProveedorMarca;
import Integracion.Proveedores.DAOProveedores;
import Negocio.FactoriaNegocio.FactoriaNegocioImp;
import Negocio.MarcaNegocio.TMarca;
import Negocio.Proveedor.SAProveedores;
import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;

@RunWith(JUnit4.class)
public class DAOProveedorMarcaTest {
	
	@Test
	public void Test(){
		this.vaciarBaseDatos();
		
		TProveedorMarca pm = new TProveedorMarca(1,1, true), aux, pm2 = new TProveedorMarca(1, 2, true);
		DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
		
		int res = dpm.create(pm);
		assertEquals("No crea", res, 1);
		
		aux = dpm.read(pm);
		assert(aux.getIdMarca()==1 && aux.getIdProveedor()==1);
		
		res= dpm.delete(pm);
		assert(res==1);
		
		aux = dpm.read(pm);
		assert(!aux.isActivo());
		
		res = dpm.update(pm);
		assertEquals("No crea", res, 1);
		
		Collection<TProveedorMarca> lista = dpm.readAll();
		assert(lista.size()==1);
		assert(lista.contains(pm));
		
		res = dpm.create(pm2);
		assertEquals("No crea", res, 1);
		
		aux = dpm.read(pm);
		assert(aux.getIdMarca()==1 && aux.getIdProveedor()==1);		
		aux = dpm.read(pm2);
		assert(aux.getIdMarca()==2 && aux.getIdProveedor()==1);
		
		lista = dpm.readAll();
		assert(lista.size()==2);
		assert(lista.contains(pm) && lista.contains(pm2));
		
		lista= dpm.readProveedorMarcaPorMarca(1);
		assert(lista.size()==1);
		assert(lista.contains(pm));
		
		lista= dpm.readProveedorMarcaPorMarca(2);
		assert(lista.size()==1);
		assert(lista.contains(pm2));
		
		lista = dpm.readProveedorMarcaPorProveedor(1);
		assert(lista.size()==2);
		assert(lista.contains(pm) && lista.contains(pm2));
		
		pm.setIdProveedor(3);
		pm2.setIdMarca(4);
		dpm.create(pm);
		dpm.create(pm2);
		lista = dpm.readProveedorMarcaPorProveedor(3);
		assert(lista.size()==1);
		assert(lista.contains(pm));
		
		lista= dpm.readProveedorMarcaPorMarca(8);
		assert(lista.size()==0);

		this.vaciarBaseDatos();
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
