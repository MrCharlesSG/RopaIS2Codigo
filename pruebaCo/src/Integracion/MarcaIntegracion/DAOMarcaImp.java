/**
 * 
 */
package Integracion.MarcaIntegracion;

import Negocio.MarcaNegocio.TMarca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sagog
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class DAOMarcaImp implements DAOMarca {
	/** 
	* (non-Javadoc)
	* @see DAOMarca#create(TMarca marca, Boolean found)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TMarca marca, Boolean found) {
		int id=-1;
		 try (Writer w=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("outputfile.txt")))){
			 StringBuilder str=new StringBuilder();
			 id=marca.getID();
			 str.append(marca.getNombre()+": "+id+": 1").append(System.lineSeparator());
			 return id;
		 }
	        catch (IOException e) {
	 
	            System.out.println(e);
	            return id;
	     }
	 
		
		
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TMarca> readAll() {
		Collection<TMarca> marcas=new ArrayList<TMarca>();
		//usando un bucle con read(id) pero no hay forma de saber cuales id valen
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#read(int id)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca read(int id) {//no me convence para nada
		File file=new File(String.valueOf(id));
		String datos[];
		TMarca marca=null;
		try(Scanner sacanner=new Scanner(file)){
			datos=sacanner.nextLine().split(": ");
			marca=new TMarca(datos[1]);
			marca.setID(Integer.parseInt(datos[0]));
			return marca;	
		}catch (IOException e) {
			return null;	//ns si esto se hace asi
		}
		
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#update(TMarca marca)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TMarca marca) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(String.valueOf(marca.getID()));
		buffer.append(marca.getID()+": "+marca.getNombre()+": 1")
		
			try(Writer w=new BufferedWriter(
										new OutputStreamWriter(
										new FileOutputStream(String.valueOf(marca.getID()))))){
				
			w.write(buffer.toString());
			}
		catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#delete(TMarca marca)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(TMarca marca) {
		
		return 0;
		
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#readByName(String nombre)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca readByName(String nombre) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}