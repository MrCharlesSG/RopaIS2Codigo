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

public class DAOProveedoresIMP implements DAOProveedores{

	/*
	 * (non-Javadoc)
	 * @see Integracion.Proveedores.DAOProveedores#create(Negocio.Proveedor.TProveedor)
	 * 
	 * EL formato ser: id, nombre, id marcas 
	 */
	@Override
	public int create(TProveedor tProv) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		
		if(file.length()==0){
			buffer.append("1"+":"+tProv.getNombre());
			ArrayList<Integer> lista= tProv.getMarca();
			for(Integer i:lista){
				buffer.append(":"+i);
			}
			id=1;
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				//al dao le deeria de dar igual si esta escribiendo algo mal de eso ya se encargan otros
				//recojo los antiguos datos
				while(scanner.hasNext()) {
					
				
					datos=scanner.nextLine().split(":");
					buffer.append(datos[0]+":"+datos[1]);
					for(int i=2; i<datos.length; i++){
						buffer.append(":"+datos[i]);
					}
					id=Integer.parseInt(datos[0])+1;
					buffer.append(System.lineSeparator());
				}
				
				//anado a los antiguos datos los nuevos
				buffer.append((id)+":"+tProv.getNombre());
				ArrayList<Integer> lista= tProv.getMarca();
				for(int i=0; i<lista.size(); i++){
					buffer.append(":"+lista.get(i));
				}
				
			}catch (IOException | NumberFormatException e) {
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

	@Override
	public int delete(int id) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		
		if(file.length()==0){
			return -1;
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				
				//recojo los datos sin el que tenga el id
				while(scanner.hasNext()) {
					
					datos=scanner.nextLine().split(":");
					if(id!=Integer.parseInt(datos[0])){
						buffer.append(datos[0]+":"+datos[1]);
						for(int i=2; i<datos.length; i++){
							buffer.append(":"+datos[i]);
						}
						buffer.append(System.lineSeparator());
					}else{
						encontrado=true;
					}
				}
				
				
			}catch (IOException e) {
				return -1;
			}
		}
		
		if(encontrado){
			try(Writer w=new BufferedWriter(
										new OutputStreamWriter(
										new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id;
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
						ArrayList<Integer> lista= new ArrayList<Integer>();
						for(int i=2; i<datos.length; i++){
							lista.add(Integer.parseInt(datos[i]));
						}
						prov= new TProveedor(datos[1], Integer.parseInt(datos[0]), lista, true);
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
				ArrayList<Integer> lista= new ArrayList<Integer>();
				for(int i=2; i<datos.length; i++){
					lista.add(Integer.parseInt(datos[i]));
				}
				provs.add( new TProveedor(datos[1], Integer.parseInt(datos[0]), lista, true));
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
				if(Integer.parseInt(datos[0])==tProv.getId()){
					buffer.append(tProv.getId()+":"+tProv.getNombre());
					
					ArrayList<Integer> lista= tProv.getMarca();
					for(int i=0; i<lista.size(); i++){
						buffer.append(":"+lista.get(i));
					}
					buffer.append(System.lineSeparator());
				}else{
					buffer.append(datos[0]+":"+datos[1]);
					for(int i=2; i<datos.length; i++){
						buffer.append(":"+datos[i]);
					}
					buffer.append(System.lineSeparator());
				}
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
						ArrayList<Integer> lista= new ArrayList<Integer>();
						for(int i=2; i<datos.length; i++){
							lista.add(Integer.parseInt(datos[i]));
						}
						prov= new TProveedor(datos[1], Integer.parseInt(datos[0]), lista, true);
					}
				}
				return prov;
				
			}catch (IOException e) {
				return null;
			}
		}
	}
	

}
