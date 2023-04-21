package Clientes;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

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

import Negocio.FactoriaNegocio.FactoriaNegocioImp;
import Negocio.Clientes.SAClientes;
import Negocio.Clientes.SAClientesIMP;
import Negocio.Clientes.TCliente;
import Negocio.Clientes.TClienteNormal;
import Negocio.Clientes.TClientePremium;

public class SaClienteTest {
	private SAClientes saCliente=FactoriaNegocioImp.getInstance().generaSAClientes();
    private TCliente tDani=new TClienteNormal(true, "Diaz", "Diaz", "12234324423A", 1, "Dani", 613456987, false);;
    private TCliente tPablo= new TClientePremium(true, "Gonzalez", "Gonzalez", "432678987E", 2, "Pablo", 654798654, true);
    private Collection<TCliente> mockCollec=new ArrayList<TCliente>();
	@Test
    public void basicBehaviuor(){
		int aux1, aux2, aux3;
		this.vaciarBaseDatos();
        aux1=saCliente.create(tDani);							
        assertEquals(aux1, 1);
        this.mockCollec.add(tDani);
        
        //leo por ID el cliente
        TCliente cliente1=this.saCliente.read(1);
        assertEquals(cliente1.getNombre(), this.tDani.getNombre());
        assertEquals(cliente1.getID(), this.tDani.getID());
       
        //leo por nombre el cliente
        TCliente cliente2=this.saCliente.readByName("12234324423A");
        assertEquals(cliente2.getNombre(), "Dani");
        assertEquals(cliente2.getID(), this.tDani.getID());

        //intento crear un cliente ya existente
        aux1=saCliente.create(tDani);
        assertNotEquals(aux1, this.tDani.getID());
        
        
        aux2=saCliente.create(tPablo);
        assertEquals(aux2, this.tPablo.getID());
        cliente1=this.saCliente.read(2);
        assertEquals(cliente1.getNombre(), this.tPablo.getNombre());
        assertEquals(cliente1.getID(), this.tPablo.getID());
        
        cliente2=this.saCliente.readByName("432678987E");
        assertEquals(cliente2.getNombre(), this.tPablo.getNombre());
        assertEquals(cliente2.getID(), this.tPablo.getID());

        //comprobacion del readAll
        this.mockCollec.add(tPablo);
        Collection<TCliente> readAllCollection=saCliente.readAll();
        assertEquals(equalsCollection(mockCollec, readAllCollection), true);
      
        //elimino los dos clientes
        aux1=saCliente.delete(1);
        assertEquals(aux1, this.tDani.getID());
        aux2=saCliente.delete(2);
        assertEquals(aux2, this.tPablo.getID());
        
        //0 clientes activos
		assertEquals(this.saCliente.readAll().size(), 2);

        
        

	}
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Clientes.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
	}
	
	boolean equalsCollection(Collection <TCliente>a, Collection <TCliente> b){
		if (a.size()!=b.size())
			return false;
		else{
			Iterator<TCliente> it1=a.iterator();
			Iterator<TCliente> it2=b.iterator();
			boolean iguales=true;
			while (it1.hasNext()&& it2.hasNext()){
				TCliente elem1=it1.next();
				TCliente elem2=it2.next();
				if(elem1.getActivo()!=elem2.getActivo()|| !elem1.getApellido1().equals(elem2.getApellido2())
				|| !elem1.getDNI().equals(elem2.getDNI())|| elem1.getID()!=elem2.getID()
				||!elem1.getNombre().equals(elem2.getNombre())
				||elem1.getTelefono()!=elem2.getTelefono()||elem1.getPremium()!=elem2.getPremium()){
					iguales=false;
				}
			}
			return iguales;
		}
	}
	
}

