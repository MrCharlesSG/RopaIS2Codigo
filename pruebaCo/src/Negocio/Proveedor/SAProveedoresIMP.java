package Negocio.Proveedor;


import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaProveedores.DAOProveedorMarca;
import Integracion.Proveedores.DAOProveedores;
import Negocio.ComprobadorSintactico;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Negocio.ProveedorMarca.TProveedorMarca;
import Presentacion.Controlador.Controlador;



public class SAProveedoresIMP implements SAProveedores{
	
	@Override
	public int create(TProveedor tProv){
		int id=-1;
		
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		if(tProv!=null && ComprobadorSintactico.isName(tProv.getNombre())){//Mirar si hay que comprobar que la marca existe o no
			TProveedor leido = daoProveedor.readByName(tProv.getNombre());
			
			if(leido == null){
				tProv.setActivo(true);
				id = daoProveedor.create(tProv); 
			}else{
				if(!leido.getActivo()){
					tProv.setId(leido.getId());
					id=daoProveedor.update(tProv);
				}
			}
		}
		return id;
		
	};
	
	@Override
	public int delete(int id){
		int res = -1;
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		TProveedor prov=daoProveedor.read(id);
		if(ComprobadorSintactico.isPositive(id) && prov!=null && prov.getActivo() ){
			DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
			Collection<TProveedorMarca> lpm = dpm.readProveedorMarcaPorProveedor(id);
			//Elimino todos los provvedorMarca con dicho proveedor
			for(TProveedorMarca pm: lpm) {
				if(pm.isActivo()) {
					dpm.delete(pm);
				}
			}
			//elimino de daoProvedores
			res=daoProveedor.delete(id);			
		}
		
		return res;
		
	};
	
	@Override
	public TProveedor read(int id){
		
		if(ComprobadorSintactico.isPositive(id)){
			TProveedor tp=FactoriaIntegracion.getInstance().generaDAOProveedor().read(id);
			if(tp!=null && tp.getActivo()){
				return tp;
			}
		}
		return null;
		
	};
	@Override
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
	
	//En update ahora solo se cambia el nombre
	@Override
	public int update(TProveedor tProv){
		int id=-1;
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		if(tProv!=null &&daoProveedor.read(tProv.getId())!=null&& ComprobadorSintactico.isName(tProv.getNombre())){//Mirar si hay que comprobar que la marca existe o no
			TProveedor leido = daoProveedor.readByName(tProv.getNombre());
			
			if(leido == null || leido.getId()==tProv.getId()) 
				id = daoProveedor.update(tProv); 
			
		}
		return id;
		
	};
	@Override
	public TProveedor readByName(String nombre){	
		TProveedor prov=null;
		if(ComprobadorSintactico.isName(nombre)) {
			TProveedor leido= FactoriaIntegracion.getInstance().generaDAOProveedor().readByName(nombre);
			
			if(leido!=null && leido.getActivo())
				prov=leido;

		}	
		return prov;
	}
	@Override
	public Collection<TProveedor> readByMarca(int id){
		Collection<TProveedor> prov = new ArrayList<TProveedor>();
		TMarca marca = null;
		if(ComprobadorSintactico.isPositive(id))
			marca = FactoriaNegocio.getInstance().generaSAMarca().read(id);
		if(marca != null && marca.getActivo()){
			DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
			DAOProveedores dp = FactoriaIntegracion.getInstance().generaDAOProveedor();
			Collection<TProveedorMarca> lpm = dpm.readProveedorMarcaPorMarca(id);
			for(TProveedorMarca pm: lpm) {
				if(pm.isActivo()) {
					TProveedor auxp = dp.read(pm.getIdProveedor());
					if(auxp!=null && auxp.getActivo()) {
						prov.add(auxp);
					}
				}
			}
		}
		return prov.size()>0? prov:null;
	}
	@Override
	public int addMarcaToProveedor(TProveedorMarca pm) {
		int res=-1;
		DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
		TProveedor prov =FactoriaIntegracion.getInstance().generaDAOProveedor().read(pm.getIdProveedor());
		TMarca marca = FactoriaIntegracion.getInstance().generaDAOMarca().read(pm.getIdMarca());
		//Si las marcas y proveedores existen y estan activas
		if(marca!=null && prov!=null && prov.getActivo() && marca.getActivo()) {
			
			if(dpm.read(pm)!=null && !dpm.read(pm).isActivo()) {
				pm.setActivo(true);
				res=dpm.update(pm);
			}else if(dpm.read(pm)!=null && dpm.read(pm).isActivo()) {
				res=-1;	
			}
			else {
				res=dpm.create(pm);
			}			
		}
		return res;
	}
	@Override
	public int deleteMarcaOfProveedor(TProveedorMarca pm) {
		int res=-1;
		DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
		TProveedorMarca pmaux=dpm.read(pm);
		if(pmaux!=null && pmaux.isActivo()) {
			res=dpm.delete(pm);
		}
		
		return res;
	}
}
