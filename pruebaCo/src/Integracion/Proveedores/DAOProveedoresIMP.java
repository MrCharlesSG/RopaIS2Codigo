package Integracion.Proveedores;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Negocio.Proveedor.TProveedor;
import Negocio.ProveedorMarca.TProveedorMarca;

public class DAOProveedoresIMP implements DAOProveedores{

	/*
	 * (non-Javadoc)
	 * @see Integracion.Proveedores.DAOProveedores#create(Negocio.Proveedor.TProveedor)
	 * 
	 * EL formato ser: id, nombre, activo, id marcas 
	 */
	@Override
	public int create(TProveedor tProv) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=1;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			//al dao le deeria de dar igual si esta escribiendo algo mal de eso ya se encargan otros
			//recojo los antiguos datos
			while(scanner.hasNext()) {
				
			
				datos=scanner.nextLine().split(":");
				buffer.append(datos[0]+":"+datos[1]+":"+datos[2]);
				for(int i=3; i<datos.length; i++){
					buffer.append(":"+datos[i]);
				}
				id=Integer.parseInt(datos[0])+1;
				buffer.append(System.lineSeparator());
			}
			
			//anado a los antiguos datos los nuevos
			buffer.append((id)+":"+tProv.getNombre()+ ":"+ tProv.getActivo());
			
				
		}catch (IOException | NumberFormatException e) {
			return -1;
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

	@Override
	public int delete(int id) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false, activo=true;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			
			//recojo los datos sin el que tenga el id
			while(scanner.hasNext()) {
				
				datos=scanner.nextLine().split(":");
				if(id!=Integer.parseInt(datos[0])){
					buffer.append(datos[0]+":"+datos[1]+":"+ datos[2]);
					for(int i=3; i<datos.length; i++){
						buffer.append(":"+datos[i]);
					}
					buffer.append(System.lineSeparator());
				}else{
					buffer.append(datos[0]+":"+datos[1]+":"+ false);
					/*for(int i=3; i<datos.length; i++){
						buffer.append(":"+datos[i]);
					}*///deveria eliminar tambien sus marcas
					buffer.append(System.lineSeparator());
					encontrado=true;
					activo=Boolean.parseBoolean(datos[2]);
				}
			}
			
		
		}catch (IOException e) {
			return -1;
		}
		
		if(encontrado){
			try(Writer w=new BufferedWriter(
										new OutputStreamWriter(
										new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				if(activo){
					return id;
				}else{
					return -1;
				}
			}catch (IOException e) {
				return -1;
			}
		}else{
			return -1;
		}
	}

	@Override
	public TProveedor read(int id) {
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		TProveedor prov=null;
		
		if(file.length()==0){
			return null;
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				
				//recojo los datos sin el que tenga el id
				while(scanner.hasNext() && !encontrado) {
					
					datos=scanner.nextLine().split(":");
					if(id==Integer.parseInt(datos[0])){
						encontrado=true;

						prov= new TProveedor(datos[1], Integer.parseInt(datos[0]),Boolean.parseBoolean(datos[2]));
					}
				}
				return prov;
				
			}catch (IOException e) {
				return null;
			}
		}
	}

	@Override
	public Collection<TProveedor> readAll() {
		File file=new File(ARCHIVO);
		Collection<TProveedor> provs=new ArrayList<TProveedor>();
		String datos[];
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			
			while(scanner.hasNext()) {
				
				datos=scanner.nextLine().split(":");
				provs.add( new TProveedor(datos[1], Integer.parseInt(datos[0]),Boolean.parseBoolean(datos[2] )));
			}
			return provs;
			
		}catch (IOException e) {
			return null;
		}
	}

	@Override
	public int update(TProveedor tProv) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		
		
		try(Scanner scanner=new Scanner(file)) {
			//recojo los antiguos datos
			while(scanner.hasNext()) {
				
			
				datos=scanner.nextLine().split(":");
				if(Integer.parseInt(datos[0])==tProv.getId())
					buffer.append(tProv.getId()+":"+tProv.getNombre()+ ":"+ tProv.getActivo());
				else
					buffer.append(datos[0]+":"+datos[1]+ ":"+ datos[2]);
					
				
				for(int i=3; i<datos.length; i++){
					buffer.append(":"+datos[i]);
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
		return 1;
	}

	@Override
	public TProveedor readByName(String nombre) {
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		TProveedor prov=null;
		
		if(file.length()==0){
			return null;
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				
				//recojo los datos sin el que tenga el id
				while(scanner.hasNext() && !encontrado) {
					
					datos=scanner.nextLine().split(":");
					if(nombre.equals(datos[1])){
						encontrado=true;
						prov= new TProveedor(datos[1], Integer.parseInt(datos[0]),Boolean.parseBoolean(datos[2]));
					}
				}
				return prov;
			}catch (IOException e) {
				return null;
			}
		}
	}

	@Override
	public Collection<TProveedor> readByMarca(int id) {
		File file=new File(ARCHIVO);
		Collection<TProveedor> provs=new ArrayList<TProveedor>();
		String datos[];
		boolean found = false;
		try(Scanner scanner=new Scanner(file)) {
			
			while(scanner.hasNext()) {
				
				datos=scanner.nextLine().split(":");
				int i=3;
				//Miro si entre sus marcas esta
				while(i<datos.length && !found) {
					if(Integer.parseInt(datos[i]) == id)
						found = true;
				}
				if(found){
					provs.add( new TProveedor(datos[1], Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[2] )));
					found = false;
				}
			}
			return provs;
			
		}catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public int addMarca(TProveedorMarca pm) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[], linea;
		int idFinal=-1;
		
		
		try(Scanner scanner=new Scanner(file)) {
			//recojo los antiguos datos
			while(scanner.hasNext()) {
				
				linea = scanner.nextLine();
				datos=linea.split(":");
				buffer.append(linea);
				if(Integer.parseInt(datos[0])==pm.getIdProveedor()) {
					buffer.append(":"+pm.getIdMarca());
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
	//EL formato ser: id, nombre, activo, id marcas 
	@Override
	public int deleteMarca(TProveedorMarca pm) {
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
				//Añado todas las marcas menos la marca pm.getIdM del provedor pm.getIdP
				for(int i=3; i< datos.length; i++) {
					if(idAct!=pm.getIdProveedor() && Integer.parseInt(datos[i])!=pm.getIdMarca()) 
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
