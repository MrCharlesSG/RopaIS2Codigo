package Integracion.Empleados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoTC;
//0: 1:::::2::::::::::3:::::::::4:::5::::6:::::::::::::::7
//ID:Nombre:Apellido1:Apellido2:TFNO:DNI:tiempoCompleto:Activo
import Negocio.Empleado.TEmpleadoTP;

public class DAOEmpleadoImp implements DAOEmpleado {

	static final String ARCHIVO="Empleados.txt";

	@Override
	public int create(TEmpleado empleado) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		
		if(file.length()==0){
			id=1;
			buffer.append(id+":"+empleado.getNombre());
			buffer.append(":"+empleado.getApellido1());
			buffer.append(":"+empleado.getApellido2());
			buffer.append(":"+empleado.getTfno());
			buffer.append(":"+empleado.getDNI());
			buffer.append(":"+empleado.isTiempoCompleto());			
			buffer.append(":"+empleado.isActivo());
			
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
				buffer.append(id+":"+empleado.getNombre());
				buffer.append(":"+empleado.getApellido1());
				buffer.append(":"+empleado.getApellido2());
				buffer.append(":"+empleado.getTfno());
				buffer.append(":"+empleado.getDNI());
				buffer.append(":"+empleado.isTiempoCompleto());			
				buffer.append(":"+empleado.isActivo());
				
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
	public Collection<TEmpleado> readAll() {
		File file=new File(ARCHIVO);
		Collection<TEmpleado> lista = new ArrayList<TEmpleado>();
		String datos[];
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			//al dao le deeria de dar igual si esta escribiendo algo mal de eso ya se encargan otros
			//recojo los antiguos datos
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
				TEmpleado aux;
				//0: 1:::::2::::::::::3:::::::::4:::5::::6:::::::::::::::7
				//ID:Nombre:Apellido1:Apellido2:TFNO:DNI:tiempoCompleto:Activo
				
				if(Boolean.parseBoolean(datos[6])){
					aux= new TEmpleadoTC(datos[1], datos[2], datos[3], datos[5], Integer.parseInt(datos[4]),Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[7]));
				}else{
					aux= new TEmpleadoTP(datos[1], datos[2], datos[3], datos[5], Integer.parseInt(datos[4]),Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[7]));
				}
				lista.add(aux);
			}
			return lista;
		}catch (IOException | NumberFormatException e) {
			return null;
		}
	}

	@Override
	public TEmpleado read(int id) {
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			TEmpleado aux=null;
			while(scanner.hasNext()&&!encontrado) {
				datos=scanner.nextLine().split(":");
				
				//0: 1:::::2::::::::::3:::::::::4:::5::::6:::::::::::::::7
				//ID:Nombre:Apellido1:Apellido2:TFNO:DNI:tiempoCompleto:Activo
				encontrado=Integer.parseInt(datos[0])==id;
				if(encontrado){
					if(Boolean.parseBoolean(datos[6])){
						aux= new TEmpleadoTC(datos[1], datos[2], datos[3], datos[5], Integer.parseInt(datos[4]),Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[7]));
					}else{
						aux= new TEmpleadoTP(datos[1], datos[2], datos[3], datos[5], Integer.parseInt(datos[4]),Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[7]));
					}
				}
				
			}
			return aux;
		}catch (IOException | NumberFormatException e) {
			return null;
		}
	}

	@Override
	public int update(TEmpleado empleado) {
		File file=new File(ARCHIVO);
		StringBuilder buffer=new StringBuilder();
		String datos[];
		boolean encontrado=false;
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			TEmpleado aux=null;
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
				
				//0: 1:::::2::::::::::3:::::::::4:::5::::6:::::::::::::::7
				//ID:Nombre:Apellido1:Apellido2:TFNO:DNI:tiempoCompleto:Activo
				
				if(Integer.parseInt(datos[0])!=empleado.getID()){
					datos=scanner.nextLine().split(":");
					buffer.append(datos[0]+":"+datos[1]);
					for(int i=2; i<datos.length; i++){
						buffer.append(":"+datos[i]);
					}
					buffer.append(System.lineSeparator());
				}else{
					encontrado=true;
					buffer.append(datos[0]+":"+empleado.getNombre());
					buffer.append(":"+empleado.getApellido1());
					buffer.append(":"+empleado.getApellido2());
					buffer.append(":"+empleado.getTfno());
					buffer.append(":"+empleado.getDNI());
					buffer.append(":"+empleado.isTiempoCompleto());			
					buffer.append(":"+empleado.isActivo());
				}
				
			}
			if(encontrado){
				return empleado.getID();
			}else{
				return -1;
			}
		}catch (IOException | NumberFormatException e) {
			return -1;
		}
	}

	
	//TODO
	@Override
	public int delete(int id) {
		File file=new File(ARCHIVO);
		StringBuilder buffer=new StringBuilder();
		String datos[];
		boolean encontrado=false;
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			TEmpleado aux=null;
			while(scanner.hasNext() && !encontrado) {
				datos=scanner.nextLine().split(":");
				
				//0: 1:::::2::::::::::3:::::::::4:::5::::6:::::::::::::::7
				//ID:Nombre:Apellido1:Apellido2:TFNO:DNI:tiempoCompleto:Activo
				
				if(Integer.parseInt(datos[0])!=id){
					datos=scanner.nextLine().split(":");
					buffer.append(datos[0]+":"+datos[1]);
					for(int i=2; i<datos.length; i++){
						buffer.append(":"+datos[i]);
					}
					buffer.append(System.lineSeparator());
				}else{
					datos=scanner.nextLine().split(":");
					buffer.append(datos[0]+":"+datos[1]);
					for(int i=2; i<datos.length; i++){
						if(i==6){
							datos[i]="false";
						}
						buffer.append(":"+datos[i]);
					}
					buffer.append(System.lineSeparator());
				}
				
			}
			
		} catch (FileNotFoundException e) {
			return -1;
		}
		if(encontrado){
			return id;
		}else{
			return -1;
		}
	}

	@Override
	public TEmpleado readByName(String dni) {
		File file=new File(ARCHIVO);
		String datos[];
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			TEmpleado aux=null;
			boolean encontrado=false;
			while(scanner.hasNext()&&!encontrado) {
				datos=scanner.nextLine().split(":");
				
				//0: 1:::::2::::::::::3:::::::::4:::5::::6:::::::::::::::7
				//ID:Nombre:Apellido1:Apellido2:TFNO:DNI:tiempoCompleto:Activo
				encontrado=datos[5].equals(dni);
				if(encontrado){
					if(Boolean.parseBoolean(datos[6])){
						aux= new TEmpleadoTC(datos[1], datos[2], datos[3], datos[5], Integer.parseInt(datos[4]),Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[7]));
					}else{
						aux= new TEmpleadoTP(datos[1], datos[2], datos[3], datos[5], Integer.parseInt(datos[4]),Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[7]));
					}								
				}				
			}
			return aux;
		}catch (IOException | NumberFormatException e) {
			return null;
		}
	}

}
