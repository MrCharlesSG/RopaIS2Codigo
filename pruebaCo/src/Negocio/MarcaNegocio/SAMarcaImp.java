/**
 * 
 */
package Negocio.MarcaNegocio;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Negocio.ComprobadorSintactico;

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
				id=daoMarca.create(marca);//hay que quitar el boolean
			else {
				if(leido.getActivo()) {
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
			
			if(leido!=null&&leido.getCantidad()==0&&leido.getActivo())
				id=daoMarca.delete(id);// hay hay que mirar los argumentos ...S
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
