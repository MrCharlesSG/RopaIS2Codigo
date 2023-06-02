package Negocio.Proveedor;


import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.Proveedores.DAOProveedores;
import Negocio.ComprobadorSintactico;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;
import Negocio.ProveedorMarca.TProveedorMarca;
import Presentacion.Controlador.Controlador;



public class SAProveedoresIMP implements SAProveedores{

	private SAMarca saMarca =FactoriaNegocio.getInstance().generaSAMarca();
	
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
					id=daoProveedor.update(tProv);
				}
			}
		}
		return id;
		
	};
	public int delete(int id){
		int res = -1;
		DAOProveedores daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		
		if(ComprobadorSintactico.isPositive(id) && daoProveedor.read(id)!=null){
			//Habla con dao pq asi elimina en los que están inactivos también
			
			//Elimina la conexion de todas las marcas (estén o no activas) que tengan al proveedor
			//Esta parte no puede tener fallos por eso no recojo ningun resultado
			DAOMarca daoMarca= FactoriaIntegracion.getInstance().generaDAOMarca();
			Collection<TMarca> marcasConProveedor = daoMarca.readMarcaByProveedor(id);
			for(TMarca m: marcasConProveedor) {
				daoMarca.deleteProveedor(new TProveedorMarca(id, m.getID()));
			}
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
	
	//En update ahora solo se cambia el nombre
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
	
	public TProveedor readByName(String nombre){	
		TProveedor prov=null;
		if(ComprobadorSintactico.isName(nombre)) {
			TProveedor leido= FactoriaIntegracion.getInstance().generaDAOProveedor().readByName(nombre);
			
			if(leido!=null && leido.getActivo())
				prov=leido;

		}	
		return prov;
	}
	
	public Collection<TProveedor> readByMarca(int id){
		Collection<TProveedor> prov = new ArrayList<TProveedor>();
		TMarca marca = null;
		if(ComprobadorSintactico.isPositive(id))
			marca = saMarca.read(id);
		if(marca != null && marca.getActivo()){
			prov=FactoriaIntegracion.getInstance().generaDAOProveedor().readByMarca(id);
		}
		return prov;
	}
	@Override
	public int addMarcaToProveedor(TProveedorMarca pm) {
		int res=-1;
		DAOProveedores daoProv=FactoriaIntegracion.getInstance().generaDAOProveedor();
		DAOMarca daoMarca =FactoriaIntegracion.getInstance().generaDAOMarca();
		TProveedor prov =daoProv.read(pm.getIdProveedor());
		TMarca marca = daoMarca.read(pm.getIdMarca());
		//Si las marcas y proveedores existen y estan activas
		if(marca!=null && prov!=null && prov.getActivo() && marca.getActivo()) {
			//Si las marcas no tienen ya ese proveedor o viceversa, en principio si uno no lo contiene el otro tampoco debería
			//No me fio del metodo contains de las Collections, he modificado el equals para q solo mire nombre y id
			if(!daoProv.readByMarca(marca.getID()).contains(prov) && !daoMarca.readMarcaByProveedor(prov.getId()).contains(marca)) {
				res = daoProv.addMarca(pm);
				daoMarca.addProveedor(pm);
			}
			
		}
		return res;
	}
	@Override
	public int deleteMarcaOfProveedor(TProveedorMarca pm) {
		int res=-1;
		DAOProveedores daoProv=FactoriaIntegracion.getInstance().generaDAOProveedor();
		DAOMarca daoMarca =FactoriaIntegracion.getInstance().generaDAOMarca();
		TProveedor prov =daoProv.read(pm.getIdProveedor());
		TMarca marca = daoMarca.read(pm.getIdMarca());
		//Si las marcas y proveedores existen y estan activas
		if(marca!=null && prov!=null && prov.getActivo() && marca.getActivo()) {
			//Si las marcas no tienen ya ese proveedor o viceversa, en principio si uno no lo contiene el otro tampoco debería
			//No me fio del metodo contains de las Collections, he modificado el equals para q solo mire nombre y id
			if(!daoProv.readByMarca(marca.getID()).contains(prov) && !daoMarca.readMarcaByProveedor(prov.getId()).contains(marca)) {
				res = daoProv.deleteMarca(pm);
				daoMarca.deleteProveedor(pm);
			}
			
		}
		return res;
	}
}
