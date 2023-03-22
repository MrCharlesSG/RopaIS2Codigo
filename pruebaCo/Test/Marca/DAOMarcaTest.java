package Marca;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaIntegracion.DAOMarcaImp;
import Negocio.MarcaNegocio.TMarca;

public class DAOMarcaTest{

    @Test
    public void test() {
        DAOMarcaImp dmarca =new DAOMarcaImp();
        TMarca res = dmarca.read(15);

        assertEquals("",res.getNombre());

        TMarca tmarca= new TMarca("coca-cola",15,true);

        dmarca.create(tmarca);
        res = dmarca.read(15);

        assertNotEquals("",res.getNombre());
        
        int deletee = dmarca.delete(tmarca);

        assertEquals(,deletee);

        Collection<TMarca> marcas2 =new ArrayList<TMarca>();
        marcas2 = dmarca.readAll();

        assertEquals(marcas2.toString(),"coca-cola 15 inactivo");

        res = dmarca.read(15);

        assertNotEquals("coca-cola",res.getNombre());

        Collection<TMarca> marcas =new ArrayList<TMarca>();
        TMarca tmarca1= new TMarca("coca-cola",15,true);
        TMarca tmarca2= new TMarca("pepsi",16,true);
        TMarca tmarca3= new TMarca("cola",17,true);

        dmarca.create(tmarca1);
        dmarca.create(tmarca2);
        dmarca.create(tmarca3);

        marcas = dmarca.readAll();

        assertEquals(marcas.toString(),"coca-cola 15 activo \n pepsi 16 activo \n cola 17 activo");

        TMarca tmarca3 = new TMarca("pepsicambio",16,true);

        int updateee=dmarca.update(tmarca3);

        assertNotEquals(-1,updateee);

        marcas = dmarca.readAll();

        assertEquals(marcas.toString(),"coca-cola 15 activo \n pepsicambio 16 activo \n cola 17 activo");


    }

}
