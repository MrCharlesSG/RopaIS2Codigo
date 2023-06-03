package Negocio.Clientes;

import java.util.Collection;

import Integracion.Clientes.DAOClientes;
import Integracion.Empleados.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Ventas.DAOVentas;
import Negocio.ComprobadorSintactico;
import Negocio.Empleado.TEmpleado;
import Negocio.Ventas.TVenta;


public class SAClientesIMP implements SAClientes {
	private DAOClientes daoClientes;
	public SAClientesIMP(){
		daoClientes=FactoriaIntegracion.getInstance().generaDAOClientes();
	}
	@Override
	public int create(TCliente cliente) {
		int id=-1;
		if(esValido(cliente)){
		TCliente leido=daoClientes.readByName(cliente.getDNI());
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
		TCliente leido=daoClientes.readByName(cliente.getDNI());
		if(esValido(cliente)&&leido!=null)
			id=daoClientes.update(leido);
		return id;
	}


	@Override
	public int delete(int ID) {
		if(ComprobadorSintactico.isPositive(ID)){
			DAOClientes daocliente = FactoriaIntegracion.getInstance().generaDAOClientes();
			DAOVentas daoVenta=FactoriaIntegracion.getInstance().generaDAOVentas();
			TCliente leido=daocliente.read(ID);
			
			if(leido!=null&&leido.getActivo()) {
				Collection<TVenta> ventas=daoVenta.readByEmpleado(ID);
				boolean inactivas=true;
				
				for(TVenta v:ventas) {
					if(v.get_activo())inactivas=false;
				}
				
				if(inactivas)
					return daocliente.delete(ID);
			}
		}
		return -1;
	}

	@Override
	public TCliente readByName(String dni) {	
		TCliente cliente=null;
		if(ComprobadorSintactico.isDNI(dni))
			cliente=daoClientes.readByName(dni);
		return cliente;
	}
	
	private boolean esValido(TCliente cliente){
		if(cliente.getPremium())
			return cliente!=null&&ComprobadorSintactico.isName(cliente.getNombre())&&ComprobadorSintactico.isName(cliente.getApellido1())
				&&ComprobadorSintactico.isName(cliente.getApellido2())&&ComprobadorSintactico.isDNI(cliente.getDNI()) && ComprobadorSintactico.isPositive(cliente.get_codigo());
		else{
			return cliente!=null&&ComprobadorSintactico.isName(cliente.getNombre())&&ComprobadorSintactico.isName(cliente.getApellido1())
					&&ComprobadorSintactico.isName(cliente.getApellido2())&&ComprobadorSintactico.isDNI(cliente.getDNI()) && ComprobadorSintactico.isName(cliente.get_poblacion());
		}
	}
}