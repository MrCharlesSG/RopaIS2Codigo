package Integracion.MarcaProveedores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Scanner;

import Negocio.ProveedorMarca.TProveedorMarca;

public class DAOMarcaProveedorIMP implements DAOMarcaProveedores {
	static final String ARCHIVO="MarcaProveedor.txt";
	@Override
	public int create(TProveedorMarca pm) {
		StringBuilder buffer=new StringBuilder();
		File file=new File(ARCHIVO);
		String datos[];
		int id=-1;
		if(file.length()==0){
			id=1;
			buffer.append(pm.getIdMarca()+":"+pm.getIdProveedor()+":"+true);
		}
		else{
			try(Scanner scanner=new Scanner(file)) {//bufferreader
				while(scanner.hasNext()) {
					datos=scanner.nextLine().split(":");
					buffer.append(datos[0]+":"+datos[1]+":"+datos[2]);
					buffer.append(System.lineSeparator());
					id=Integer.parseInt(datos[1])+1;
				}
				buffer.append(pm.getIdMarca()+":"+pm.getIdProveedor()+":"+true);
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

	@Override
	public Collection<TProveedorMarca> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TProveedorMarca> readProveedorMarcaPorProveedor(int proveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TProveedorMarca> readProveedorMarcaPorMarca(int marca) {
		// TODO Auto-generated method stub
		return null;
	}

}
