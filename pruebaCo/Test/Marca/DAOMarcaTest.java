package Marca;

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

import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaIntegracion.DAOMarcaImp;
import Negocio.MarcaNegocio.TMarca;

public class DAOMarcaTest{

    @Test
    public void test() {
    	this.vaciarBaseDatos();
        DAOMarcaImp dmarca =new DAOMarcaImp();
        TMarca res = dmarca.read(15);

        assertEquals(null,res);

       TMarca tmarca= new TMarca("coca-cola",1,0,1);

        int id=dmarca.create(tmarca);
        assertEquals(1,id);
        res = dmarca.read(1);

        assertEquals("coca-cola",res.getNombre());
        
        int deletee = dmarca.delete(tmarca);

        assertEquals(1,deletee);

        Collection<TMarca> marcas2 =new ArrayList<TMarca>();
        marcas2 = dmarca.readAll();

        assertEquals(marcas2.size(),0);

        Collection<TMarca> marcas =new ArrayList<TMarca>();
        TMarca tmarca1= new TMarca("coca-cola",1,0,1);
        TMarca tmarca2= new TMarca("pepsi",2,0,1);
        TMarca tmarca3= new TMarca("cola",3,0,1);

        dmarca.create(tmarca1);
        dmarca.create(tmarca2);
        dmarca.create(tmarca3);
        Collection<TMarca> aux=new ArrayList<TMarca>();
        aux.add(tmarca1);
        aux.add(tmarca2);
        aux.add(tmarca3);
        marcas = dmarca.readAll();
       
        assertEquals(true,equalsCollection(marcas,aux));

      TMarca tmarca4 = new TMarca("pepsicambio",4,1,0);

        int updateee=dmarca.update(tmarca4);

        assertEquals(-1,updateee);
        marcas = dmarca.readAll();
        
        assertEquals(true,equalsCollection(marcas,aux));
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
		
		        if (!elem1.getNombre().equals(elem2.getNombre()) || elem1.getActivo()!= elem2.getActivo()  ||elem1.getID()!= elem2.getID() || elem1.getCantidad()!=elem2.getCantidad()) {
                    iguales = false;
                }
		    }
		    return iguales;
		}
    }
}
