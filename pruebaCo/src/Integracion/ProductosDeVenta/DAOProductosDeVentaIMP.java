package Integracion.ProductosDeVenta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Negocio.ProductosDeVenta.TProductosDeVenta;

public class DAOProductosDeVentaIMP implements DAOProductosDeVenta {
	static final String ARCHIVO="ProductosDeVentas.txt";
	//ID->Venta : ID->Producto : Cantidad : precio
	@Override
	public int create(TProductosDeVenta TProdVenta) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		if(file.length()==0){
			id=1;
			buffer.append(TProdVenta.getVenta()+":"+TProdVenta.getProducto()+":"+TProdVenta.getCantidad()+":"+TProdVenta.getPrecio()).append(System.lineSeparator());
			
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				while(scanner.hasNext()) {
					String line=scanner.nextLine();
					buffer.append(line);
					buffer.append(System.lineSeparator());
					id=1;
				}
				buffer.append(TProdVenta.getVenta()+":"+TProdVenta.getProducto()+":"+TProdVenta.getCantidad()+":"+TProdVenta.getPrecio()).append(System.lineSeparator());
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

	@Override
	public int delete(TProductosDeVenta Tpv) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
			
				if (Integer.parseInt(datos[0])==Tpv.getVenta()&&Integer.parseInt(datos[1])==Tpv.getProducto()) {
					encontrado=true;
				}
				else 
					buffer.append(datos[0]+":"+datos[1]+":"+datos[2]+":"+datos[3]).append(System.lineSeparator());
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return encontrado? 1:-1;
			}
		}
		catch (IOException e) {
			return -1;
		}
		
	}

	@Override
	public Collection<TProductosDeVenta> productosVenta(int id) {
		Collection<TProductosDeVenta>productos=new ArrayList<TProductosDeVenta> ();
		File file=new File(ARCHIVO);
		String datos[];
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				while(scanner.hasNext()) {
					String line=scanner.nextLine();
					datos=line.split(":");
					if(Integer.parseInt(datos[0])==id) {
						productos.add(new TProductosDeVenta(Integer.parseInt(datos[0]),Integer.parseInt(datos[1])
								,Double.parseDouble(datos[3]),Integer.parseInt(datos[2])));
					}
				}
				
			}catch (IOException e) {
				return productos;
			}
		return productos;
	}

	@Override
	public int update(TProductosDeVenta Tpv) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		boolean encontrado=false;
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			while(scanner.hasNext()) {
				datos=scanner.nextLine().split(":");
			
				if (Integer.parseInt(datos[0])==Tpv.getVenta()&&Integer.parseInt(datos[1])==Tpv.getProducto()) {
					encontrado=true;
					if(Tpv.getCantidad()>0)buffer.append(Tpv.getVenta()+":"+Tpv.getProducto()+":"+Tpv.getCantidad()+":"+Tpv.getPrecio()).append(System.lineSeparator());
				}
				else 
					buffer.append(datos[0]+":"+datos[1]+":"+datos[2]+":"+datos[3]).append(System.lineSeparator());
			}
			try(Writer w=new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(ARCHIVO)))){
				w.write(buffer.toString());
				return encontrado? 1:-1;
			}
		}
		catch (IOException e) {
			return -1;
		}
		
	}

	

}
