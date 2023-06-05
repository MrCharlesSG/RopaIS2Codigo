/**
 * 
 */
package Negocio.MarcaNegocio;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.MarcaProveedores.DAOProveedorMarca;
import Integracion.Producto.DAOProducto;
import Integracion.Proveedores.DAOProveedores;
import Negocio.ComprobadorSintactico;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.TProducto;
import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class SAMarcaImp implements SAMarca {
	/** 
	* (non-Javadoc)
	* @see SAMarca#create(TMarca marca)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TMarca marca) {//reglas de negocio
		int id=-1;
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();
		if(marca!=null&&ComprobadorSintactico.isName(marca.getNombre())){
			TMarca leido=daoMarca.readByName(marca.getNombre());
			
			if(leido==null)
				id=daoMarca.create(marca);
			else {
				if(!leido.getActivo()) {
				
					marca.setID(leido.getID());
					id=daoMarca.update(marca);
				}
			}
		}
		return id;
	}

	/** 
	* (non-Javadoc)
	* @see SAMarca#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TMarca> readAll() {
		
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();
		Collection<TMarca> lista = new ArrayList<TMarca>();
		Collection<TMarca> listaAux = daoMarca.readAll();
		for(TMarca m:listaAux) {
			if(m.getActivo()) {
				lista.add(m);
			}
		}
		return lista;
	}

	/** 
	* (non-Javadoc)
	* @see SAMarca#read(int id)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca read(int id) {
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();
		TMarca marca=null;
		if(ComprobadorSintactico.isPositive(id))
			marca=daoMarca.read(id);
		
		return	(marca!=null&&marca.getActivo())? marca:null;
		 
	}

	/** 
	* (non-Javadoc)
	* @see SAMarca#update(TMarca marca)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update( TMarca marca) {
		int id=-1;
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();
		if(ComprobadorSintactico.isPositive(marca.getID())){
			TMarca leido=daoMarca.read(marca.getID());	
			if(leido!=null)
				id=daoMarca.update(marca);
			}
		return id;
		
	}

	/** 
	* (non-Javadoc)
	* @see SAMarca#delete(int id)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int ID) {
		int id=-1;
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();
		if(ComprobadorSintactico.isPositive(ID)){
			TMarca leido=daoMarca.read(ID);	
			
			if(leido!=null&&leido.getActivo()){
				// si la marca no es nula y esta activa 			
				//Revisamos que no queden productos
				DAOProducto daoprod= FactoriaIntegracion.getInstance().generaDAOProducto();
				Collection<TProducto> productos= daoprod.readByMarca(ID);
				boolean inactivos=true;
				for(TProducto p:productos){//si todos sus productos estan a 0(no estan activos) se puede borrar la marca
					if(p.getCantidad()>0){
						inactivos=false;
					}
				}
				if(inactivos) {
					DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
					Collection<TProveedorMarca> lpm = dpm.readProveedorMarcaPorMarca(id);
					//Elimino todos los provvedorMarca con dicha marca
					for(TProveedorMarca pm: lpm) {
						if(pm.isActivo()) {
							dpm.delete(pm);
						}
					}
					//elimino de daoMarca			
					id=daoMarca.delete(ID);
					
				}
			}
		}
		return id;
	}

	/** 
	* (non-Javadoc)
	* @see SAMarca#readByName(String nombre)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca readByName(String nombre) {
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();	
		TMarca marca=null;
		if(ComprobadorSintactico.isName(nombre))
			marca=daoMarca.readByName(nombre);
		return (marca!=null && marca.getActivo())? marca:null;
		// end-user-code
	}

	@Override
	public Collection<TMarca> readByProveedor(int idProv) {
		Collection<TMarca> marcas =new ArrayList<TMarca>();
		TProveedor prov = null;
		if(ComprobadorSintactico.isPositive(idProv))
			prov = FactoriaNegocio.getInstance().generaSAProveedor().read(idProv);
		if(prov != null && prov.getActivo()){
			DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
			DAOMarca dm = FactoriaIntegracion.getInstance().generaDAOMarca();
			Collection<TProveedorMarca> lpm = dpm.readProveedorMarcaPorMarca(idProv);
			for(TProveedorMarca pm: lpm) {
				if(pm.isActivo()) {
					TMarca auxp = dm.read(pm.getIdMarca());
					if(auxp!=null && auxp.getActivo()) {
						marcas.add(auxp);
					}
				}
			}
		}
		return marcas.size()>0? marcas:null;
	}

	@Override
	public int addProveedorToMarca(TProveedorMarca pm) {
		int res=-1;
		DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
		TProveedor prov =FactoriaIntegracion.getInstance().generaDAOProveedor().read(pm.getIdProveedor());
		TMarca marca = FactoriaIntegracion.getInstance().generaDAOMarca().read(pm.getIdMarca());
		//Si las marcas y proveedores existen y estan activas
		if(marca!=null && prov!=null && prov.getActivo() && marca.getActivo()) {
			
			if(dpm.read(pm)!=null) {
				pm.setActivo(true);
				res=dpm.update(pm);
			}else if(dpm.read(pm).isActivo()) {
				res=-1;	
			}
			else {
				res=dpm.create(pm);
			}
		}
		return res;
	}

	@Override
	public int deleteProveedorOfMarca(TProveedorMarca pm) {
		int res=-1;
		DAOProveedorMarca dpm = FactoriaIntegracion.getInstance().generaDAOProveedorMarca();
		TProveedorMarca pmaux=dpm.read(pm);
		if(pmaux!=null && pmaux.isActivo()) {
			res=dpm.delete(pm);
		}
		return res;
	}

}