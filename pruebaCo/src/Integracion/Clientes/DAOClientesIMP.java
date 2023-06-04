
package Integracion.Clientes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Negocio.Clientes.TCliente;
import Negocio.Clientes.TClienteNormal;
import Negocio.Clientes.TClientePremium;


public class DAOClientesIMP implements DAOClientes {
	static final String ARCHIVO="Clientes.txt";
	@Override
	public int create(TCliente cliente) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		int id=-1;
		if(file.length()==0){
			buffer.append((1)+":"+cliente.getNombre()+" "+cliente.getApellido1()+" "+cliente.getApellido2()+":"+cliente.getDNI()+":"+cliente.getTelefono()+":"+cliente.getPremium()+":1");
			if(cliente.getPremium()){
				buffer.append((":"+cliente.get_codigo()));
			}
			else
				buffer.append((":")+cliente.get_poblacion());
			id=1;
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				while(scanner.hasNext()) {
					
				
					datos=scanner.nextLine().split(":");
					if(datos[2].equalsIgnoreCase(cliente.getDNI())){
						encontrado=true;
						id=Integer.parseInt(datos[0]);
						datos[5]="1";
					}
					buffer.append(datos[0]+":"+datos[1]+":"+datos[2]+":"+datos[3]+":"+datos[4]+":"+datos[5]+":"+datos[6]).append(System.lineSeparator());
					
					if(!encontrado)id=Integer.parseInt(datos[0])+1;//no me deja ponerlo fuera pero bueno
				}
				if(!encontrado){
					buffer.append(id+":"+cliente.getNombre()+" "+cliente.getApellido1()+" "+cliente.getApellido2()+":"+cliente.getDNI()+":"+cliente.getTelefono()+":"+cliente.getPremium()+":1");
					if(cliente.getPremium()){
						buffer.append(":"+cliente.get_codigo());
					}
					else{
						buffer.append(":" +cliente.get_poblacion());
					}
				}	
			}catch (IOException e) {
				return id;
			}
		}
			try(Writer w=new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id; 
			}catch (IOException e) {
				return id;
			}
		
	}

	@Override 
	public Collection<TCliente> readAll() {
		
		Collection<TCliente> clientes=new ArrayList<TCliente>();
		File file=new File(ARCHIVO);
		String datos[];
		TCliente cliente=null;
		try(Scanner sacanner=new Scanner(file)){
			while(sacanner.hasNextLine()){
				datos=sacanner.nextLine().split(":");
				
					
				if(datos[4].equalsIgnoreCase("true")){
					cliente=new TClientePremium(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]), datos[4],Integer.parseInt(datos[5]),Integer.parseInt(datos[6]));
					clientes.add(cliente);
				}
				else{
					cliente=new TClienteNormal(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]),datos[4],Integer.parseInt(datos[5]),datos[6]);
					clientes.add(cliente);			
				}
			}	
			return clientes;
		}catch (IOException e) {
			return null;
		}
	
	}

	@Override
	public TCliente read(int id) {
		File file=new File(ARCHIVO);
		boolean encontrado=false;
		TCliente cliente=null;
		try(Scanner scanner= new Scanner(file)){
			
			while(scanner.hasNext() &&!encontrado) {
				
				String datos[]=scanner.nextLine().split(":");

				int ID=Integer.parseInt(datos[0]);
				if (ID==id) {
					if(datos[4].equalsIgnoreCase("true")){
						cliente=new TClientePremium(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]), datos[4],Integer.parseInt(datos[5]), Integer.parseInt(datos[6]));
					}
					else{
						cliente=new TClienteNormal(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]), datos[4],Integer.parseInt(datos[5]), datos[6]);
					}
					encontrado=true;
				}
			
			}
		}catch(IOException e){
			return cliente;
		}
		return cliente;
	}
	@Override
	public int update(TCliente cliente) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
	
		try(Scanner scanner=new Scanner(file)) {//bufferreader
		
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
				if (cliente.getID()== Integer.parseInt(datos[0])) {	
					id=Integer.parseInt(datos[0]);
					buffer.append(id+":"+cliente.getNombre()+" "+cliente.getApellido1()+" "+cliente.getApellido2()+ ":"+cliente.getDNI()+":"+cliente.getTelefono()+":"+cliente.getPremium()+":"+datos[5]);
					if(cliente.getPremium()){
						buffer.append(":"+ cliente.get_codigo());
					}
					else{
						buffer.append(":"+cliente.get_poblacion());
					}
					buffer.append(System.lineSeparator());
				}
				else{
					buffer.append(datos[0]+":"+datos[1]+":"+datos[2]+":"+datos[3]+":"+datos[4]+":"+datos[5]+":"+datos[6]).append(System.lineSeparator());
				}		
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return id;
				
			}
		}
		catch (IOException e) {
			return id;
		}
	}

	@Override
	public int delete( int id) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int ID=-1;
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
			
				if (Integer.parseInt(datos[0])==id) {
				
					datos[5]="0";
					ID=Integer.parseInt(datos[0]);
				}
				buffer.append(datos[0]+":"+datos[1]+":"+datos[2]+":"+datos[3]+":"+datos[4]+":"+datos[5]+":"+datos[6]).append(System.lineSeparator());
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return ID;
			}
		}
		catch (IOException e) {
			return ID;
		}
	}

	@Override
	public TCliente readByName(String dni) {
		File file=new File(ARCHIVO);
		boolean encontrado=false;
		TCliente cliente=null;
		try(Scanner scanner= new Scanner(file)){
			while(scanner.hasNext() &&!encontrado) {
				String datos[]=scanner.nextLine().split(":");
				if (datos[2].equalsIgnoreCase(dni)) {
					if(datos[4].equalsIgnoreCase("true"))
						cliente=new TClientePremium(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]), datos[4],Integer.parseInt(datos[5]),Integer.parseInt(datos[6]));
					else
						cliente=new TClienteNormal(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]), datos[4],Integer.parseInt(datos[5]),datos[6]);
						encontrado=true;
				}
			}
			return cliente;
		}catch(IOException e){
			return cliente;
		}
	}

}
