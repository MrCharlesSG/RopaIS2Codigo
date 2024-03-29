package Marca;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Negocio.MarcaNegocio.TMarca;

public class DAOMarcaTest{
	
	private TMarca m1= new TMarca("Juan", 1, true);
	private DAOMarca dao = FactoriaIntegracion.getInstance().generaDAOMarca();
	private Collection<TMarca> lista= new ArrayList<TMarca>();

	//String nombre, int ID, int cantidad, boolean activo)
    @Test
    public void test() {
    	this.vaciarBaseDatos();
    	
    	int res=dao.create(m1);
    	assert(res==1);
    	TMarca maux=dao.read(1);
    	assert(maux.equals(m1));
   
    	
    	maux=dao.read(1);
    	maux=dao.readByName("Juan");
    	m1=new TMarca("Juan", 1, true);
    	assert(maux.equals(m1 ));
    	
    	res=dao.delete(m1.getID());
    	assert(res==1);
    	
    	maux=new TMarca("Monica", 2, true);
    	res=dao.create(maux);
    	assert(res==2);
    	
    	m1.setActivo(false);
    	lista.add(m1);
    	lista.add(maux);
    	Collection<TMarca> listaAux=dao.readAll();
    	assert(listaAux.equals(lista));
    	
    	res=dao.delete(maux.getID());
    	assert(res==2);
    	maux=new TMarca("Monica", 2, false);
    	
    	
    	
    	lista.clear();
    	lista.add(m1);
    	lista.add(maux);
    	maux=new TMarca("Juani", 3, false);
    	res=dao.create(maux);
    	assert(res==3);
    	lista.add(maux);
    	
    	res=dao.delete(maux.getID());
    	
    	listaAux=dao.readAll();   	
    	assert(lista.equals(listaAux));
    	
    	maux= new TMarca("Malaq", 3, true);
    	res= dao.update(maux);
    	assert(res==3);
    	
        vaciarBaseDatos();
      
    }

	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Marcas.txt")))){
			w.write("");
		
 	}catch (IOException e) {
		
	}
	}
}
