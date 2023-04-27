package Integracion.Ventas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Negocio.Ventas.TVenta;

public class DAOVentasIMP implements DAOVentas {
	
	static final String ARCHIVO="Empleados.txt";

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
				//al dao le deeria de dar igual si esta escribiendo algo mal de eso ya se encargan otros
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
	
	private void mapaABuffer(Map<Integer, Integer> map, StringBuilder buffer) {
		for(Integer i: map.keySet()) {
			buffer.append(":"+i+"-"+map.get(i));
		}
	}
	
	private void datosABuffer(String[] datos, StringBuilder buffer) {
		/*
		 * int id, int id_emp, int id_cl, double prec, int cont, boolean activo
		 * 0   1     2      3       4            5      6
		 * id:emp:cliente:precio:contProductos:activo:idprodi-unidadesi
		 */
		buffer.append(datos[0]+":"+datos[1]);
		buffer.append(":"+datos[2]);
		buffer.append(":"+datos[3]);
		buffer.append(":"+datos[4]);
		buffer.append(":"+datos[5]);
		
		for(int i=6; i<datos.length; i++) {
			buffer.append(":"+datos[i]);
		}
		
	}
	
	private void ventaABuffer(TVenta venta, StringBuilder buffer, int id) {
		buffer.append(id+":"+venta.get_id_empleado());
		buffer.append(":"+venta.get_id_cliente());
		buffer.append(":"+venta.get_precio());
		buffer.append(":"+venta.get_contador());
		buffer.append(":"+venta.get_activo());
		mapaABuffer(venta.get_map(), buffer);
	}
	
	private TVenta readVenta(String[] datos) {
		/*
		 * int id, int id_emp, int id_cl, double prec, int cont, boolean activo
		 * 0   1     2      3       4            5      6
		 * id:emp:cliente:precio:contProductos:activo:idprodi-unidadesi
		 */
		TVenta venta;
		String[] d;
		venta=new TVenta(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]),
				Integer.parseInt(datos[2]),Double.parseDouble(datos[3]), Integer.parseInt(datos[4]),
				Boolean.parseBoolean(datos[5]));
		for(int i=6; i<datos.length;i++) {
			d=datos[i].split("-");
			venta.aniadir_prod_ud(Integer.parseInt(d[0]), Integer.parseInt(d[1]));
		}
		return venta;
		
	}
}
