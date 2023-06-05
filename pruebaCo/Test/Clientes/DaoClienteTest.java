package Clientes;
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
import Integracion.Clientes.DAOClientesIMP;
import Negocio.Clientes.TClienteNormal;
import Negocio.Clientes.TClientePremium;
import Negocio.Clientes.TCliente;
public class DaoClienteTest {

	@Test
	public void test(){
		//vaciar txt
		this.vaciarBaseDatos();
		DAOClientesIMP dcliente=new DAOClientesIMP();
		//Prueba del read mal
		TCliente res=dcliente.read(-1);
		
		assertEquals(null, res);
		
		
		TClienteNormal tcliente=new TClienteNormal(false, "AAA", "BBB", "12345689A", 1, "MrCharles", 666777888, false,"Albacete");
		//Prueba create y read bien
		int id=dcliente.create(tcliente);
		res=dcliente.read(1);
		
		assertEquals("MrCharles", res.getNombre());
		
		//Prueba delete
		int delete=dcliente.delete(tcliente.getID());
		
		assertEquals(1, delete);
		
		//Prueba readAll
		
		Collection<TCliente> clientes2=new ArrayList<TCliente>();
		clientes2=dcliente.readAll();
		
		assertEquals(clientes2.size(),1);
		
		Collection<TCliente> clientes=new ArrayList<TCliente>();
		TCliente cliente1=new TClientePremium(true, "CCC", "DDD", "98765432A", 2, "EEE",111222333, true,28019);
		TCliente cliente2=new TClienteNormal(true, "FFF", "GGG", "51342143B", 3, "HHH",222333444, false,"Madrid");
		TCliente cliente3=new TClientePremium(true, "III", "JJJ", "24387344G", 4, "LLL", 555666777, true,28050);
		
		dcliente.create(cliente1);
		dcliente.create(cliente2);
		dcliente.create(cliente3);
		clientes=dcliente.readAll();
		
		Collection<TCliente> aux=new ArrayList<TCliente>();
		aux.add(tcliente);
		aux.add(cliente1);
		aux.add(cliente2);
		aux.add(cliente3);
		
		assert(aux.equals(clientes));
		
		//Prueba update
		
		
		//vaciarBaseDatos();
		TCliente cliente5=new TClientePremium(true, "CCC", "DDD", "98765432S", 5, "EEE",111222333, true,28019);
		dcliente.create(cliente5);
		TCliente aux_5= new TClientePremium(true, "Hola", "que", "9755432S", 5, "EEE",111222333, true,28019);
		TCliente aux_6;
		dcliente.update(aux_5);
		aux_6=dcliente.read(5);
		
		assert(aux_6.equals(aux_5));
		
		
	}
	private void vaciarBaseDatos() {
		try(Writer w=new BufferedWriter(
				new OutputStreamWriter(
				new FileOutputStream("Clientes.txt")))){
			w.write("");
		
	}catch (IOException e) {
		
	}
	}
	
	
	
	
}

