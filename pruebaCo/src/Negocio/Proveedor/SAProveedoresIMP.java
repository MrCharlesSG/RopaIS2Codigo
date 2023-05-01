package Negocio.Proveedor;


import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.Proveedores.DAOProveedores;
import Negocio.ComprobadorSintactico;
import Presentacion.Controlador.Controlador;



public class SAProveedoresIMP implements SAProveedores{

	public int create(TProveedor tProv){
		int id=-1;
		
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		if(tProv!=null && ComprobadorSintactico.isName(tProv.getNombre()) && Controlador.getInstancia().marcasExisten(tProv.getMarca())){//Mirar si hay que comprobar que la marca existe o no
			TProveedor leido = daoProveedor.readByName(tProv.getNombre());
			
			if(leido == null){
				tProv.setActivo(true);
				id = daoProveedor.create(tProv); 
			}else{
				if(!leido.getActivo()){
					id=daoProveedor.update(tProv);
				}
			}
		}
		return id;
		
	};
	public int delete(int id){
		int res = -1;
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		
		if(ComprobadorSintactico.isPositive(id)){
			res= daoProveedor.delete(id);
		}
		
		return res;
		
	};
	public TProveedor read(int id){
		
		if(ComprobadorSintactico.isPositive(id)){
			TProveedor tp=FactoriaIntegracion.getInstance().generaDAOProveedor().read(id);
			if(tp!=null && tp.getActivo()){
				return tp;
			}
		}
		return null;
		
	};
	public Collection<TProveedor> readAll(){
		Collection<TProveedor> lista= new ArrayList<TProveedor>();
		Collection<TProveedor> aux = FactoriaIntegracion.getInstance().generaDAOProveedor().readAll();
		if(aux!=null){
			for(TProveedor p:aux){
				if(p.getActivo()){
					lista.add(p);
				}
			}
		}
		return lista;
	};
	public int update(TProveedor tProv){
		int id=-1;
		
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		if(tProv!=null &&daoProveedor.read(tProv.getId())!=null&& ComprobadorSintactico.isName(tProv.getNombre()) && Controlador.getInstancia().marcasExisten(tProv.getMarca())){//Mirar si hay que comprobar que la marca existe o no
			TProveedor leido = daoProveedor.readByName(tProv.getNombre());
			
			if(leido == null || leido.getId()==tProv.getId()) 
				id = daoProveedor.update(tProv); 
			
		}
		return id;
		
	};
	
	public TProveedor readByName(String nombre){	
		TProveedor prov=null;
		if(ComprobadorSintactico.isName(nombre))
			prov= FactoriaIntegracion.getInstance().generaDAOProveedor().readByName(nombre);
		
		if(prov!=null && prov.getActivo()){
			return prov;
		}else{
			return null;
		}
		
		
	}
}
