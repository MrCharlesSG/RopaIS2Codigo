/**
 * 
 */
package Negocio.MarcaNegocio;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.Producto.DAOProducto;
import Negocio.ComprobadorSintactico;
import Negocio.Producto.TProducto;

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
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();//yo lo dejaria como atributo de sa
		if(ComprobadorSintactico.isPositive(marca.getID())){
			TMarca leido=daoMarca.read(marca.getID());	
			if(leido!=null && leido.getActivo())
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
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();//yo lo dejaria como atributo de sa
		if(ComprobadorSintactico.isPositive(ID)){
			TMarca leido=daoMarca.read(ID);	
			
			if(leido!=null&&leido.getActivo()){
				
				DAOProducto daoprod= FactoriaIntegracion.getInstance().generaDAOProducto();
				Collection<TProducto> productos= daoprod.readByMarca(ID);
				if(productos.isEmpty())
					id=daoMarca.delete(ID);// hay hay que mirar los argumentos ...
				else{
					boolean inactivos=true;
					for(TProducto p:productos){
						if(p.getCantidad()>0){
							inactivos=false;
						}
					}
					
					if(inactivos)// si resulta que todos estan inactivos se puede borrar la marca
						daoMarca.delete(ID);
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
	
	
	public int actualizarCantidad(int ID,boolean aumento){
		int id=-1;
		DAOMarca daoMarca = FactoriaIntegracion.getInstance().generaDAOMarca();	
		
		if(ComprobadorSintactico.isPositive(ID)){
			TMarca leido=daoMarca.read(ID);
			if(leido!=null)
				id=daoMarca.actualizarCantidad(ID,aumento);
		}
			
		return id;
	}

}