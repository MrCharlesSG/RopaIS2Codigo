package Marca;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

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

        assertEquals("",res.getNombre());

       TMarca tmarca= new TMarca("coca-cola",1,1,1);

        int id=dmarca.create(tmarca);
        assertEquals(1,id);
        res = dmarca.read(1);

        assertEquals("coca-cola",res.getNombre());
        
        int deletee = dmarca.delete(tmarca);

        assertEquals(1,deletee);

        Collection<TMarca> marcas2 =new ArrayList<TMarca>();
        marcas2 = dmarca.readAll();

        assertEquals(marcas2.toString(),"");

        Collection<TMarca> marcas =new ArrayList<TMarca>();
        TMarca tmarca1= new TMarca("coca-cola",1,1,0);
        TMarca tmarca2= new TMarca("pepsi",16,1,0);
        TMarca tmarca3= new TMarca("cola",17,1,0);

       dmarca.create(tmarca1);
        dmarca.create(tmarca2);
        dmarca.create(tmarca3);

        marcas = dmarca.readAll();
       
        assertEquals(marcas.toString(),"1:      coca-cola:      0:      true\n"
        		+"2:      pepsi:      0:      true\n" 
        		+"3:      cola:      0:      true\n");

      TMarca tmarca3 = new TMarca("pepsicambio",2,1,0);

        int updateee=dmarca.update(tmarca3);

        assertNotEquals(-1,updateee);

        marcas = dmarca.readAll();

        assertEquals(marcas.toString(),"coca-cola 15 activo \n pepsicambio 16 activo \n cola 17 activo");


    }

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
