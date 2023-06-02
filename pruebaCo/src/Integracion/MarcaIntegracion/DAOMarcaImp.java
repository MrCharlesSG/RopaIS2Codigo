
package Integracion.MarcaIntegracion;

import Negocio.MarcaNegocio.TMarca;
import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
	
	/*
	 * String nombre, int ID, boolean activo, proveedores)
	 * 0      1     2       3                     4
	 * nombre:id:activo:proveedores
	 */
	
	public int create(TMarca marca) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		if(file.length()==0){
			id=1;
			buffer.append(marca.getNombre()+":"+id+":"+true);
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				while(scanner.hasNext()) {
					datos=scanner.nextLine().split(":");
					buffer.append(datos[0]+":"+datos[1]+":"+datos[2]);
					buffer.append(System.lineSeparator());
					id=Integer.parseInt(datos[1])+1;
				}
				buffer.append(marca.getNombre()+":"+id+":"+true);
				buffer.append(System.lineSeparator());
			}catch (IOException e) {
				return -1;
			}
		}
			try(Writer w=new BufferedWriter(
										new OutputStreamWriter(
										new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id; 
			}catch (IOException e) {
				return -1;						
			}
		
	}
	/** 
	* (non-Javadoc)
	* @see DAOMarca#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	/*
	 * String nombre, int ID, boolean activo, proveedores)
	 * 0      1     2       3                     4
	 * nombre:id:cantidad:activo:proveedores
	 */
	public Collection<TMarca> readAll() {
		Collection<TMarca> marcas=new ArrayList<TMarca>();
		File file=new File(ARCHIVO);
		String datos[];
		TMarca marca=null;
		try(Scanner sacanner=new Scanner(file)){
			while(sacanner.hasNextLine()){
				datos=sacanner.nextLine().split(":");
				marca=new TMarca(datos[0],Integer.parseInt(datos[1]), Boolean.parseBoolean(datos[2]));
				marcas.add(marca);
			}
			return marcas;
		}catch (IOException e) {
			return null;	//ns si esto se hace asi
		}
	}

	/** 
	* (non-Javadoc)
	* @see DAOMarca#read(int id)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TMarca read(int id) {
		File file=new File(ARCHIVO);
		boolean encontrado=false;
		TMarca marca=null;
		try(Scanner scanner= new Scanner(file)){
			
			while(scanner.hasNext() &&!encontrado) {
				String tokens[]=scanner.nextLine().split(":");
				
				int ID=Integer.parseInt(tokens[1]);
				if (ID==id) {
					marca=new TMarca(tokens[0],Integer.parseInt(tokens[1]), Boolean.parseBoolean(tokens[2]));
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
	
		try(Scanner scanner=new Scanner(file)) {//bufferreader
		
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
				if (marca.getID()==Integer.parseInt(datos[1])) {	
					id=Integer.parseInt(datos[1]);
					buffer.append(marca.getNombre()+":"+id+ ":"+marca.getActivo());
				}
				else
					buffer.append(datos[0]+":"+datos[1]+":"+marca.getActivo());
				
				buffer.append(System.lineSeparator());
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id;
				
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
	public int delete(int id) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
			
				if (Integer.parseInt(datos[1])==id) {
					datos[3]="false";
					encontrado=true;
				}
				buffer.append(datos[0]+":"+datos[1]+":"+datos[2]+":"+datos[3]).append(System.lineSeparator());
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return encontrado? id:-1;
			}
		}
		catch (IOException e) {
			return -1;
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
				
				String tokens[]=scanner.nextLine().split(":");
				
				if (tokens[0].equalsIgnoreCase(nombre)) {
					marca=new TMarca(tokens[0],Integer.parseInt(tokens[1]), Boolean.parseBoolean(tokens[2]));
					encontrado=true;
				}
			}
			return marca;
		}catch(IOException e){
			return null;
		}
	}
	@Override
	public Collection<TMarca> readMarcaByProveedor(int idProveedor) {
		File file=new File(ARCHIVO);
		Collection<TMarca> marcas=new ArrayList<TMarca>();
		String datos[];
		boolean found = false;
		try(Scanner scanner=new Scanner(file)) {
			
			while(scanner.hasNext()) {
				
				datos=scanner.nextLine().split(":");
				int i=3;
				//Miro si entre sus proveedores esta
				while(i<datos.length && !found) {
					if(Integer.parseInt(datos[i]) == idProveedor)
						found = true;
				}
				if(found){
					marcas.add( new TMarca(datos[0], Integer.parseInt(datos[1]), Boolean.parseBoolean(datos[2] )));
					found = false;
				}
			}
			return marcas;
			
		}catch (IOException e) {
			return null;
		}
	}
	

	@Override
	public int addProveedor(TProveedorMarca pm) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[], linea;
		int idFinal=-1;
		
		
		try(Scanner scanner=new Scanner(file)) {
			//recojo los antiguos datos
			while(scanner.hasNext()) {
				
				linea=scanner.nextLine();
				datos=linea.split(":");
				buffer.append(linea);
				if(Integer.parseInt(datos[0])==pm.getIdMarca()) {
					buffer.append(":"+pm.getIdProveedor());
					idFinal=Integer.parseInt(datos[0]);
				}					
				buffer.append(System.lineSeparator());
			}
		}catch (IOException e) {
			return -1;
		}
		
			
		try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
			w.write(buffer.toString());
		}catch (IOException e) {
			return -1;
		}
		return idFinal;
	}
	
	@Override
	public int deleteProveedor(TProveedorMarca pm) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int idFinal=-1, idAct;
		
		
		try(Scanner scanner=new Scanner(file)) {
			//recojo los antiguos datos
			while(scanner.hasNext()) {
				
			
				datos=scanner.nextLine().split(":");
				idAct=Integer.parseInt(datos[0]);
				//Añado datos de siempre
				for(int i=0; i<3; i++ )  {
					buffer.append(datos[i]);
					if(i!=2)
						buffer.append(":");
					
				}
				//Añado todas las marcas menos el provedor pm.getId de la Pmarca pm.getIdM
				for(int i=3; i< datos.length; i++) {
					if(idAct!=pm.getIdMarca() && Integer.parseInt(datos[i])!=pm.getIdProveedor()) 
						buffer.append(":"+datos[i]);
					else
						idFinal=idAct;
				}
				
				buffer.append(System.lineSeparator());
			}
		}catch (IOException e) {
			return -1;
		}
		
			
		try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
			w.write(buffer.toString());
		}catch (IOException e) {
			return -1;
		}
		return idFinal;
	}
}