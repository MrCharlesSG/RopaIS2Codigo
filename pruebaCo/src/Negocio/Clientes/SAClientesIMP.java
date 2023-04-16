package Negocio.Clientes;

import java.util.Collection;

import Integracion.Clientes.DAOClientes;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.ComprobadorSintactico;


public class SAClientesIMP implements SAClientes {
	private DAOClientes daoClientes;
	public SAClientesIMP(){
		daoClientes=FactoriaIntegracion.getInstance().generaDAOClientes();
	}
	@Override
	public int create(TCliente cliente) {
		int id=-1;
		if(esValido(cliente)){
			TCliente leido=daoClientes.readByName(cliente.getNombre(),cliente.getApellido1(),cliente.getApellido2());
			if(leido==null||!leido.getActivo())
				id=daoClientes.create(cliente);
			}
		return id;
	}

	@Override
	public Collection<TCliente> readAll() {
	
		return daoClientes.readAll();
	}

	@Override
	public TCliente read(int id) {
		TCliente cliente=null;
		if(ComprobadorSintactico.isPositive(id))
			cliente=daoClientes.read(id);
		return cliente;
	}

	@Override
	public int update(TCliente cliente) {
		int id=-1;
		if(esValido(cliente))
			id=daoClientes.update(cliente);
		
		return id;
	}


	@Override
	public int delete(int ID) {
		int id=-1;
		if(ComprobadorSintactico.isPositive(ID))){
			TCliente cliente=daoClientes.read(id);
			if(cliente!=null&&cliente.getActivo())
				id=daoClientes.delete(cliente);
		}
		return id;
	}

	@Override
	public TCliente readByName(String nombre,String apellido1,String apellido2) {	
		TCliente cliente=null;
		if(ComprobadorSintactico.isName(nombre)&&ComprobadorSintactico.isName(apellido1)&&ComprobadorSintactico.isName(apellido2))
			cliente=daoClientes.readByName(nombre,apellido1,apellido2);
		return cliente;
	}
	
	private boolean esValido(TCliente cliente){
	return cliente!=null&&ComprobadorSintactico.isName(cliente.getNombre())&&ComprobadorSintactico.isName(cliente.getApellido1())
			&&ComprobadorSintactico.isName(cliente.getApellido2())&&ComprobadorSintactico.isDNI(cliente.getDNI());
			
}
}