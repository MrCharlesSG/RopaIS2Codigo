package Integracion.Ventas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Negocio.Ventas.TVenta;

public class DAOVentasIMP implements DAOVentas {
	
	static final String ARCHIVO="Ventas.txt";

	/*
	 * int id, int id_emp, int id_cl, double prec, int cont, boolean activo
	 * 
	 * id:emp:cliente:precio:contProductos:activo:idprodi-unidadesi
	 */
	
	@Override
	public int create(TVenta venta) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		
		if(file.length()==0){
			id=1;
			this.ventaABuffer(venta, buffer, id);
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				//al dao le deberia de dar igual si esta escribiendo algo mal de eso ya se encargan otros
				//recojo los antiguos datos
				while(scanner.hasNext()) {

					datos=scanner.nextLine().split(":");
					id=Integer.parseInt(datos[0])+1;
					this.datosABuffer(datos, buffer);
					buffer.append(System.lineSeparator());
				}
				this.ventaABuffer(venta, buffer, id);
				buffer.append(System.lineSeparator());
			}catch (IOException | NumberFormatException e) {
				id=-1;
			}
		}
		if(id!=-1) {
			try(Writer w=new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(ARCHIVO)))){
					w.write(buffer.toString());

			}catch (IOException e) {
				id=-1;
			}
		}
		return id;
	}
	@Override
	public Collection<TVenta> readAll() {
		File file=new File(ARCHIVO);
		Collection<TVenta> lista = new ArrayList<TVenta>();
		String datos[];
		
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
				lista.add(this.readVenta(datos));
			}
			return lista;
		}catch (IOException | NumberFormatException e) {
			return null;
		}

	}

	@Override
	public TVenta read(int id) {
		File file=new File(ARCHIVO);
		String datos[];
		TVenta venta=null;
		boolean encontrado= false;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext() && !encontrado) {
				datos=scanner.nextLine().split(":");
				venta=this.readVenta(datos);
				if(venta.get_id()==id)
					encontrado=true;
				else
					venta=null;
			}
			return venta;
		}catch (IOException | NumberFormatException e) {
			return null;
		}
	}

	@Override
	public Collection<TVenta> readByEmpleado(int idEmpleado) {
		File file=new File(ARCHIVO);
		String datos[];
		TVenta venta=null;
		boolean encontrado= false;
		Collection<TVenta> lista = new ArrayList<TVenta>();
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext() && !encontrado) {
				datos=scanner.nextLine().split(":");
				venta=this.readVenta(datos);
				if(venta.get_id_empleado()==idEmpleado)
					lista.add(venta);
				
			}
			return lista;
		}catch (IOException | NumberFormatException e) {
			return null;
		}
	}

	@Override
	public Collection<TVenta> readByCliente(int idCliente) {
		File file=new File(ARCHIVO);
		String datos[];
		TVenta venta=null;
		boolean encontrado= false;
		Collection<TVenta> lista = new ArrayList<TVenta>();
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext() && !encontrado) {
				datos=scanner.nextLine().split(":");
				venta=this.readVenta(datos);
				if(venta.get_id_empleado()==idCliente)
					lista.add(venta);
				
			}
			return lista;
		}catch (IOException | NumberFormatException e) {
			return null;
		}
	}
	
	
	private void datosABuffer(String[] datos, StringBuilder buffer) {
		/*
		 *
		 * 0   1     2      3       4           
		 * id:emp:cliente:precio:activo:
		 */
		buffer.append(datos[0]+":"+datos[1]);
		buffer.append(":"+datos[2]);
		buffer.append(":"+datos[3]);
		buffer.append(":"+datos[4]);
		
	}
	
	private void ventaABuffer(TVenta venta, StringBuilder buffer, int id) {
		buffer.append(id+":"+venta.get_id_empleado());
		buffer.append(":"+venta.get_id_cliente());
		buffer.append(":"+venta.get_precio());
		buffer.append(":"+venta.get_activo());
	}
	
	private TVenta readVenta(String[] datos) {
		/*
		 *
		 * 0   1     2      3       4           
		 * id:emp:cliente:precio:activo:
		 */
		TVenta venta;
		venta=new TVenta(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]),
				Integer.parseInt(datos[2]),Double.parseDouble(datos[3]),
				Boolean.parseBoolean(datos[4]));
		return venta;
		
	}
	@Override
	public int update(TVenta venta) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				//al dao le deeria de dar igual si esta escribiendo algo mal de eso ya se encargan otros
				//recojo los antiguos datos
				while(scanner.hasNext()) {
					datos=scanner.nextLine().split(":");
					id=Integer.parseInt(datos[0]);
					if(id==venta.get_id()){
						this.ventaABuffer(venta, buffer, id);
					}
					else{
						this.datosABuffer(datos, buffer);
					}
					buffer.append(System.lineSeparator());
				}
			}catch (IOException | NumberFormatException e) {
				id=-1;
			}
		if(id!=-1) {
			try(Writer w=new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(ARCHIVO)))){
					w.write(buffer.toString());

			}catch (IOException e) {
				id=-1;
			}
		}
		return id;
	}
	@Override
	public void cancelar(int iD) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				//al dao le deeria de dar igual si esta escribiendo algo mal de eso ya se encargan otros
				//recojo los antiguos datos
				while(scanner.hasNext()) {
					datos=scanner.nextLine().split(":");
					id=Integer.parseInt(datos[0]);
					if(id!=iD){
						this.datosABuffer(datos, buffer);
						buffer.append(System.lineSeparator());
					}	
				}
			}catch (IOException | NumberFormatException e) {
				return;
			}
		if(id!=-1) {
			try(Writer w=new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(ARCHIVO)))){
					w.write(buffer.toString());

			}catch (IOException e) {
				id=-1;
			}
		}
		
	}
}
