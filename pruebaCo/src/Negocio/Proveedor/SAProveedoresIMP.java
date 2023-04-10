package Negocio.Proveedor;


import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
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
		DAOMarca daoMarca= FactoriaIntegracion.getInstance().generaDAOMarca();
		boolean resB=true;
		
		if(ComprobadorSintactico.isPositive(id)){
			TProveedor prov = daoProveedor.read(id);	
			
			if(prov!=null){
				//alomejor la marca tiene otro prov
				ArrayList<Integer> lista= prov.getMarca();
				for(Integer i: lista){
					if(resB&&daoMarca.delete(daoMarca.read(i))==-1){
						resB=false;
					}
				}
				if(resB){
					res=daoProveedor.delete(id);
					//como se desactiva
				}else{
					res=-1;
				}
			}
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
		return FactoriaIntegracion.getInstance().generaDAOProveedor().update(tProv);
	};
	
	public TProveedor readByName(String nombre){
		DAOProveedores daoProv = FactoriaIntegracion.getInstance().generaDAOProveedor();	
		TProveedor prov=null;
		
		if(ComprobadorSintactico.isName(nombre))
			prov= daoProv.readByName(nombre);
		return prov;
		
	}
}
