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
import java.io.FileWriter;
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
	static final String ARCHIVO="Marcas.txt";
	
	public int create(TMarca marca) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		boolean encontrado=false;
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
				buffer.append(datos[0]+": "+datos[1]+": "+datos[2]).append(System.lineSeparator());
				id=Integer.parseInt(datos[0])+1;//no me deja ponerlo fuera pero bueno
			}
			
			buffer.append((id)+": "+marca.getNombre()+": 1");
			
			
			try(Writer w=new BufferedWriter(
										new OutputStreamWriter(
										new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id;//mm no me convence lo del writer
			}
		}
		catch (IOException e) {
			//cosas
			return id;
		}
		
	}//esto esta feo pero bueno 

	/** 
	* (non-Javadoc)
	* @see DAOMarca#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TMarca> readAll() {
		Collection<TMarca> marcas=new ArrayList<TMarca>();
		File file=new File(ARCHIVO);
		String datos[];
		TMarca marca=null;
		try(Scanner sacanner=new Scanner(file)){
			while(sacanner.hasNextLine()){
				datos=sacanner.nextLine().split(": ");
				if(datos[2].equalsIgnoreCase("1")){
					marca=new TMarca(datos[1],Integer.parseInt(datos[0]),Integer.parseInt(datos[2]));
					marcas.add(marca);
				}
				
			}
			return marcas;
		}catch (IOException e) {
			return null;	//ns si esto se hace asi
		}
	}
		// end-user-code
	

	/** 
	* (non-Javadoc)
	* @see DAOMarca#read(int id)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca read(int id) {//no me convence para nada
		File file=new File(ARCHIVO);
		boolean encontrado=false;
		TMarca marca=null;
		try(Scanner scanner= new Scanner(file)){
			
			while(scanner.hasNext() &&!encontrado) {
				
				String tokens[]=scanner.nextLine().split(": ");
				
			if (Integer.parseInt(tokens[1])==id&&tokens[2].equalsIgnoreCase("1")) {
				 marca=new TMarca(tokens[1],Integer.parseInt(tokens[0]),Integer.parseInt(tokens[2]));
				 encontrado=true;
				}
			}
		
			return marca;
		}catch(IOException e){
			//cosas
			return marca;
		}
	}
	/** 
	* (non-Javadoc)
	* @see DAOMarca#update(TMarca marca)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TMarca marca) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		boolean encontrado=false;
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
			
				if (datos[1].equalsIgnoreCase(marca.getNombre())) {
				
					
					id=Integer.parseInt(datos[0]);
					buffer.append(id+": "+marca.getNombre()+": "+marca.getActivo()).append(System.lineSeparator());
				}
				else
					buffer.append(datos[0]+": "+datos[1]+": "+datos[2]).append(System.lineSeparator());
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id;//mm no me convence lo del writer
			}
		}
		catch (IOException e) {
			//cosas
			return id;
		}
		
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#delete(TMarca marca)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(TMarca marca) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		boolean encontrado=false;
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
			
				if (datos[1].equalsIgnoreCase(marca.getNombre())) {
				// si la encuentro la desactivo
					datos[2]="0";
					id=Integer.parseInt(datos[0]);
				}
				buffer.append(datos[0]+": "+datos[1]+": "+datos[2]).append(System.lineSeparator());
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id;//mm no me convence lo del writer
			}
		}
		catch (IOException e) {
			//cosas
			return id;
		}
		
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#readByName(String nombre)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca readByName(String nombre) {
		File file=new File(ARCHIVO);
		boolean encontrado=false;
		TMarca marca=null;
		try(Scanner scanner= new Scanner(file)){
			
			while(scanner.hasNext() &&!encontrado) {
				
				String tokens[]=scanner.nextLine().split(": ");
				
				if (tokens[1].equalsIgnoreCase(nombre)&&tokens[2].equalsIgnoreCase("1")) {
					 marca=new TMarca(nombre,Integer.parseInt(tokens[0]),Integer.parseInt(tokens[2]));
					encontrado=true;
				}
			}
		
			return marca;
		}catch(IOException e){
			//cosas
			return marca;
		}
	}
}