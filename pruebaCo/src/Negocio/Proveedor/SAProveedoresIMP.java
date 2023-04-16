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
			
			if(leido == null)
				id = daoProveedor.create(tProv); //TODO gestionar el hecho de estar activo en el create
			
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
		
		if(ComprobadorSintactico.isPositive(id))
			return FactoriaIntegracion.getInstance().generaDAOProveedor().read(id);
		
		return null;
		
	};
	public Collection<TProveedor> readAll(){
		DAOProveedores daoProv = FactoriaIntegracion.getInstance().generaDAOProveedor();
		return daoProv.readAll();
		
	};
	public int update(TProveedor tProv){
		int id=-1;
		
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		if(tProv!=null &&daoProveedor.read(tProv.getId())!=null&& ComprobadorSintactico.isName(tProv.getNombre()) && Controlador.getInstancia().marcasExisten(tProv.getMarca())){//Mirar si hay que comprobar que la marca existe o no
			TProveedor leido = daoProveedor.readByName(tProv.getNombre());
			
			if(leido == null)
				id = daoProveedor.update(tProv); //TODO gestionar el hecho de estar activo en el create
			
		}
		return id;
		
	};
	
	public TProveedor readByName(String nombre){
		DAOProveedores daoProv = FactoriaIntegracion.getInstance().generaDAOProveedor();	
		TProveedor prov=null;
		
		if(ComprobadorSintactico.isName(nombre))
			prov= daoProv.readByName(nombre);
		return prov;
		
	}
}
