package Negocio.Empleado;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.Empleados.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Ventas.DAOVentas;
import Negocio.ComprobadorSintactico;
import Negocio.Ventas.TVenta;

public class SAEmpleadoImp implements SAEmpleado {

	@Override
	public int create(TEmpleado empl) {
		int id=-1;
		
		DAOEmpleado daoEmpleado = FactoriaIntegracion.getInstance().generaDAOEmpleado();
		if(this.comprobadorTEmpleado(empl)){
			TEmpleado leido = daoEmpleado.readByName(empl.getDNI());
			
			if(leido == null){
				id = daoEmpleado.create(empl); 
			}else{
				if(!leido.isActivo()){
					empl.setID(leido.getID());
					id=daoEmpleado.update(empl);
				}
			}
		}
		return id;
	}

	@Override
	public int delete(int id) {
		if(ComprobadorSintactico.isPositive(id)){
			DAOEmpleado daoEmpleado = FactoriaIntegracion.getInstance().generaDAOEmpleado();
			DAOVentas daoVenta=FactoriaIntegracion.getInstance().generaDAOVentas();
			TEmpleado leido=daoEmpleado.read(id);
			
			if(leido!=null&&leido.isActivo()) {
				Collection<TVenta> ventas=daoVenta.readByEmpleado(id);
				boolean inactivas=true;
				
				for(TVenta v:ventas) {
					if(v.get_activo())inactivas=false;
				}
				
				if(inactivas)
					return daoEmpleado.delete(id);
			}
		}
		return -1;
	}

	@Override
	public TEmpleado read(int id) {
		TEmpleado te=null;
		if(ComprobadorSintactico.isPositive(id)) {
			te = FactoriaIntegracion.getInstance().generaDAOEmpleado().read(id);
			if(te!=null && te.isActivo()) {
				return te;
			}
		}
		return te;
	}

	@Override
	public Collection<TEmpleado> readAll() {
		Collection<TEmpleado> lista= new ArrayList<TEmpleado>();
		Collection<TEmpleado> aux = FactoriaIntegracion.getInstance().generaDAOEmpleado().readAll();
		if(aux!=null){
			for(TEmpleado p:aux){
				if(p.isActivo()){
					lista.add(p);
				}
			}
		}
		return lista;
	}

	@Override
	public int update(TEmpleado empl) {
		int id=-1;
		
		DAOEmpleado daoEmpl = FactoriaIntegracion.getInstance().generaDAOEmpleado();
		if(this.comprobadorTEmpleado(empl)&&daoEmpl.read(empl.getID())!=null){
			TEmpleado leido = daoEmpl.readByName(empl.getDNI());
			
			if(leido != null)
				id = daoEmpl.update(empl); 
		}
		return id;
	}

	@Override
	public TEmpleado readByName(String dni) {
		TEmpleado empl= null;
		if(ComprobadorSintactico.isDNI(dni))
			empl = FactoriaIntegracion.getInstance().generaDAOEmpleado().readByName(dni);
		if(empl!=null && empl.isActivo() ) {
			return empl;
		}
		return null;
	}
	
	private boolean comprobadorTEmpleado(TEmpleado empl) {
		if(empl.isTiempoCompleto()){
			return empl!=null && ComprobadorSintactico.isName(empl.getNombre()) && ComprobadorSintactico.isName(empl.getApellido1())
					&& ComprobadorSintactico.isName(empl.getApellido2()) && ComprobadorSintactico.isDNI(empl.getDNI()) && ComprobadorSintactico.isPositive(empl.get_salario())
					&& ComprobadorSintactico.isPositive(empl.get_bonus());
		}
		else{
			return empl!=null && ComprobadorSintactico.isName(empl.getNombre()) && ComprobadorSintactico.isName(empl.getApellido1())
					&& ComprobadorSintactico.isName(empl.getApellido2()) && ComprobadorSintactico.isDNI(empl.getDNI()) && ComprobadorSintactico.isPositive(empl.get_horas())
					&& ComprobadorSintactico.isPositive(empl.get_precio_hora());
		}
		
	}

}
