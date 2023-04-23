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
import Integracion.Clientes.DAOClientes;
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
		TCliente res=dcliente.read(-1);
		
		assertEquals(null, res);
		
		TClienteNormal tcliente=new TClienteNormal(false, "AAA", "BBB", "12345689A", 1, "MrCharles", 666777888, false);
		int id=dcliente.create(tcliente);
		res=dcliente.read(1);
		
		assertEquals("MrCharles", res.getNombre());
		
		int delete=dcliente.delete(tcliente);
		
		assertEquals(1, delete);
		
		Collection<TCliente> clientes2=new ArrayList<TCliente>();
		clientes2=dcliente.readAll();
		
		assertEquals(clientes2.size(),1);
		
		Collection<TCliente> clientes=new ArrayList<TCliente>();
		TCliente cliente1=new TClientePremium(true, "CCC", "DDD", "98765432A", 2, "EEE",111222333, true);
		TCliente cliente2=new TClienteNormal(true, "FFF", "GGG", "51342143B", 3, "HHH",222333444, false);
		TCliente cliente3=new TClientePremium(true, "III", "JJJ", "24387344G", 4, "LLL", 555666777, true);
		
		dcliente.create(cliente1);
		dcliente.create(cliente2);
		dcliente.create(cliente3);
		clientes=dcliente.readAll();
		
		Collection<TCliente> aux=new ArrayList<TCliente>();
		aux.add(tcliente);
		aux.add(cliente1);
		aux.add(cliente2);
		aux.add(cliente3);
		
		assertEquals(true, equalsCollection(clientes, aux));

		
		vaciarBaseDatos();
		
		
		
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
				if(elem1.getActivo()!=elem2.getActivo()|| 
						!elem1.getApellido1().equalsIgnoreCase(elem2.getApellido1())||
						!elem1.getApellido2().equalsIgnoreCase(elem2.getApellido2())||
						!elem1.getDNI().equalsIgnoreCase(elem2.getDNI())|| 
						elem1.getID()!=elem2.getID()||
						!elem1.getNombre().equalsIgnoreCase(elem2.getNombre())||
						elem1.getTelefono()!=elem2.getTelefono()||
						elem1.getPremium()!=elem2.getPremium()){
							iguales=false;
				}
			}
			return iguales;
		}
	}
	
	
}

