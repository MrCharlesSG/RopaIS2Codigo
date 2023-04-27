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

        assertEquals(marcas2.size(),1);

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
        assert(aux.equals(marcas));

      TMarca tmarca4 = new TMarca("pepsicambio",4,1,0);

        int updateee=dmarca.update(tmarca4);

        assertEquals(-1,updateee);
        marcas = dmarca.readAll();
        
        assert(aux.equals(marcas));
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
