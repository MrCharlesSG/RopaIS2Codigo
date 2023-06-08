package Integracion.ProductosDeVenta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import Negocio.ProductosDeVenta.TProductosDeVenta;

public class DAOProductosDeVentaIMP implements DAOProductosDeVenta {
	static final String ARCHIVO="ProductosDeVentas.txt";
	//ID->Venta : ID->Producto : Cantidad
	@Override
	public int create(TProductosDeVenta TProdVenta) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		if(file.length()==0){
			id=1;
			for(Integer ID: TProdVenta.getProductos().keySet()) {
			buffer.append(TProdVenta.getVenta()+":"+ID+":"+TProdVenta.getProductos().get(ID)).append(System.lineSeparator());
			}
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				while(scanner.hasNext()) {
					String line=scanner.nextLine();
					buffer.append(line);
					buffer.append(System.lineSeparator());
					id=1;
				}
				for(Integer ID: TProdVenta.getProductos().keySet()) {
					buffer.append(TProdVenta.getVenta()+":"+ID+":"+TProdVenta.getProductos().get(ID)).append(System.lineSeparator());
					}
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
	public int delete(TProductosDeVenta TProdVenta) {
		
		return 0;
	}

	@Override
	public TProductosDeVenta productosVenta(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
