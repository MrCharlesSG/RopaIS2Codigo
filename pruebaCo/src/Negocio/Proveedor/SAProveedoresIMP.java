package Negocio.Proveedor;


import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

import Integracion.Proveedores.DAOProveedores;
import Negocio.ComprobadorSintactico;



public class SAProveedoresIMP implements SAProveedores{

	public int create(TProveedor tProv){
		int id=-1;
		
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		if(tProv!=null && ComprobadorSintactico.isName(tProv.getNombre())){
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
			TProveedor prov = daoProveedor.read(id);	
			
			if(prov!=null)
				/* TODO llamar a delete para borrar las marcas y poner el activo
					a false
				  id=daoProveedor.delete(prov);
				 * 
				 */
			}
		
		return res;
		
	};
	public TProveedor read(int id){
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		
		TProveedor prov = null;
		if(ComprobadorSintactico.isPositive(id))
			prov = daoProveedor.read(id);
		return prov;
		
	};
	public Collection<TProveedor> readAll(){
		DAOProveedores daoProv = FactoriaIntegracion.getInstance().generaDAOProveedor();
		return daoProv.readAll();
		
	};
	public int update(TProveedor tProv){
		
		//TODO mirar los parametros
		return 0;
	};
	
	public TProveedor readByName(String nombre){
		DAOProveedores daoProv = FactoriaIntegracion.getInstance().generaDAOProveedor();	
		TProveedor prov=null;
		
		if(ComprobadorSintactico.isName(nombre))
			prov= daoProv.readByName(nombre);
		return prov;
		
	}
}
