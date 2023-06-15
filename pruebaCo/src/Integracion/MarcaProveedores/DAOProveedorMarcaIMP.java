package Integracion.MarcaProveedores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Negocio.ProveedorMarca.TProveedorMarca;
//Proveedor: marca: activo
public class DAOProveedorMarcaIMP implements DAOProveedorMarca {
	static final String ARCHIVO="ProveedorMarca.txt";
	@Override
	public int create(TProveedorMarca pm) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		if(file.length()==0){
			buffer.append(pm.getIdProveedor()+":"+pm.getIdMarca()+":"+true);
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				while(scanner.hasNext()) {
					datos=scanner.nextLine().split(":");
					buffer.append(datos[0]+":"+datos[1]+":"+datos[2]);
					buffer.append(System.lineSeparator());
				}
				buffer.append(pm.getIdProveedor()+":"+pm.getIdMarca()+":"+true);
				buffer.append(System.lineSeparator());
			}catch (IOException e) {
				return -1;
			}
		}
			try(Writer w=new BufferedWriter(
										new OutputStreamWriter(
										new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return 1; 
			}catch (IOException e) {
				return -1;						
			}
		
	}
	@Override
	public TProveedorMarca read(TProveedorMarca pm) {
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		TProveedorMarca pmr=null;
		
		if(file.length()==0){
			return null;
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				
				//recojo los datos sin el que tenga el id
				while(scanner.hasNext() && !encontrado) {
					
					datos=scanner.nextLine().split(":");
					if(Integer.parseInt(datos[0])==pm.getIdProveedor() && Integer.parseInt(datos[1])==pm.getIdMarca() ){
						encontrado=true;
						pmr = new TProveedorMarca(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Boolean.parseBoolean(datos[2]));
					}
				}
				return pmr;
				
			}catch (IOException e) {
				return null;
			}
		}
	}

	@Override
	public int delete(TProveedorMarca pm) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[], line;
		boolean encontrado=false, activo=true;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			
			//recojo los datos sin el que tenga el id
			while(scanner.hasNext()) {
				line=scanner.nextLine();
				datos=line.split(":");
				if(!(Integer.parseInt(datos[0])==pm.getIdProveedor() && Integer.parseInt(datos[1])==pm.getIdMarca())) {
					buffer.append(line);
				}else {
					buffer.append(datos[0]+":"+datos[1]+":"+false);
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
				return 1;
			}catch (IOException e) {
				return -1;
		}
		
	}
	@Override
	public Collection<TProveedorMarca> readAll() {
		File file=new File(ARCHIVO);
		Collection<TProveedorMarca> pms=new ArrayList<TProveedorMarca>();
		String datos[];
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			
			while(scanner.hasNext()) {
				
				datos=scanner.nextLine().split(":");
				pms.add( new TProveedorMarca(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Boolean.parseBoolean(datos[2])));
			}
			return pms;
			
		}catch (IOException e) {
			return null;
		}
	}
	@Override
	public Collection<TProveedorMarca> readProveedorMarcaPorProveedor(int proveedor) {
		File file=new File(ARCHIVO);
		Collection<TProveedorMarca> pms=new ArrayList<TProveedorMarca>();
		String datos[];
		try(Scanner scanner=new Scanner(file)) {
			
			while(scanner.hasNext()) {
				
				datos=scanner.nextLine().split(":");
				if(Integer.parseInt(datos[0])==proveedor) {
					pms.add( new TProveedorMarca(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Boolean.parseBoolean(datos[2])));
				}
				
			}
			return pms;
			
		}catch (IOException e) {
			return null;
		}
	}
	@Override
	public Collection<TProveedorMarca> readProveedorMarcaPorMarca(int marca) {
		File file=new File(ARCHIVO);
		Collection<TProveedorMarca> pms=new ArrayList<TProveedorMarca>();
		String datos[];
		try(Scanner scanner=new Scanner(file)) {
			
			while(scanner.hasNext()) {
				
				datos=scanner.nextLine().split(":");
				if(Integer.parseInt(datos[1])==marca) {
					pms.add( new TProveedorMarca(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Boolean.parseBoolean(datos[2])));
				}
				
			}
			return pms;
			
		}catch (IOException e) {
			return null;
		}
	}
	@Override
	public int update(TProveedorMarca pm) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[], line;
		boolean encontrado=false, activo=true;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			
			//recojo los datos sin el que tenga el id
			while(scanner.hasNext()) {
				line=scanner.nextLine();
				datos=line.split(":");
				if(!(Integer.parseInt(datos[0])==pm.getIdProveedor() && Integer.parseInt(datos[1])==pm.getIdMarca())) {
					buffer.append(line);
				}else {
					buffer.append(pm.getIdProveedor()+":"+pm.getIdMarca()+":"+pm.isActivo());
					encontrado=true;
				}
				buffer.append(System.lineSeparator());
			}
			
		
		}catch (IOException e) {
			return -1;
		}
		
		if(encontrado){
			try(Writer w=new BufferedWriter(
										new OutputStreamWriter(
										new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return 1;
			}catch (IOException e) {
				return -1;
			}
		}else{
			return -1;
		}
	}

	

}
