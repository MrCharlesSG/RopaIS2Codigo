/**
 * 
 */
package Negocio.MarcaNegocio;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
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
					// si resulta que todos estan inactivos o no hay ninguno
					//Eliminar de los proveedores la marca
					DAOProveedores daoProv=FactoriaIntegracion.getInstance().generaDAOProveedor();
					Collection<TProveedor> proveedores = daoProv.readByMarca(id);
					for(TProveedor p: proveedores) {
						daoProv.deleteMarca(new TProveedorMarca(p.getId(), id));
					}
					//Eliminamos Marca					
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
		Collection<TMarca> marcas = new ArrayList<TMarca>();
		TProveedor prov = null;
		if(ComprobadorSintactico.isPositive(idProv))
			prov = FactoriaNegocio.getInstance().generaSAProveedor().read(idProv);
		if(prov != null && prov.getActivo()){
			marcas=FactoriaIntegracion.getInstance().generaDAOMarca().readMarcaByProveedor(idProv);
		}
		return marcas;
	}

	@Override
	public int addProveedorToMarca(TProveedorMarca pm) {
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
				daoProv.addMarca(pm);
				res = daoMarca.addProveedor(pm);
			}
			
		}
		
		
		return res;
	}

	@Override
	public int deleteProveedorOfMarca(TProveedorMarca pm) {
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
				daoProv.deleteMarca(pm);
				res = daoMarca.deleteProveedor(pm);
			}
			
		}
		return res;
	}

}