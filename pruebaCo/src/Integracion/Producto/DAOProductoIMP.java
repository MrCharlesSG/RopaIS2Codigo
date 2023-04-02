//Dokt0r
package Integracion.Producto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.lang.StringBuilder;

import Negocio.Producto.TProducto;

public class DAOProductoIMP implements DAOProducto{

	static final String ARCHIVO= "Productos.txt";
	
	@Override
	public int create(TProducto Tprod) {
		String[] stringArray = null;
		//nombre: id: cantidad: talla: categoria: idMarca:
		stringArray[0] = Tprod.getNombre();
		stringArray[1] = Integer.toString(Tprod.getIdProducto());
		stringArray[2] = Integer.toString(Tprod.getCantidad());
		stringArray[3] = Integer.toString(Tprod.getTalla());
		stringArray[4] = Tprod.getCategoria();
		stringArray[5] = Integer.toString(Tprod.getIdMarca());
		
		File f =new File(ARCHIVO);
		
		String[] splitArray = null;
		StringBuilder sb =new StringBuilder();
		
		boolean found =false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null && found == false) {
		        splitArray = line.split(": ", 6);
		        //nombre: id: cantidad: talla: categoria: idMarca:
		        sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": " 
		        + splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5]);
		        	
		        if(splitArray[0].equals(stringArray[0]) && splitArray[5].equals(stringArray[5])){
		        		found = true;
		        }
		    }
		    
		    if(!found){
		    	BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		    	sb.append(stringArray[0] + ": " + (splitArray[1] + 1) + ": " + stringArray[2] + ": " 
		        + stringArray[3] + ": " + stringArray[4] + ": " + stringArray[5]);
		    	
		    	bw.append(sb);
		    	bw.flush();
		    	bw.close();
		    }
		    br.close();
		}
		catch (Exception e) {
		   return -1;
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		File f = new File(ARCHIVO);
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			StringBuilder sb = new StringBuilder();
			
			while((line = br.readLine()) !=null){
				splitArray = line.split(": ", 6);
				if(!splitArray[1].equals(Integer.toString(id)) && splitArray[2].equals("0")){
					//nombre: id: cantidad: talla: categoria: idMarca:
					sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": " 
							+ splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5]);
				}
				
			}
			br.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.append(sb);
			bw.flush();
			bw.close();
			
		}catch (Exception e){
			return -1;
		}
		return 0;
	}

	@Override
	public TProducto read(int id) {
		
		File f = new File(ARCHIVO);
		TProducto Tprod = null;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			boolean found = false;
			
			while((line = br.readLine()) !=null && !found){
				splitArray = line.split(": ", 6);
				if(splitArray[1].equals(Integer.toString(id))){
					//nombre: id: cantidad: talla: categoria: idMarca:
					Tprod = new TProducto(splitArray[0], Integer.parseInt(splitArray[1]), Integer.parseInt(splitArray[2]), Integer.parseInt(splitArray[3]), splitArray[4], Integer.parseInt(splitArray[5]));
					found = true;
				}
				
			}
			br.close();
		}catch(Exception e){
			return null;
		}
		return Tprod;
	}

	@Override
	public Collection<TProducto> readAll() {

		File f = new File(ARCHIVO);
		TProducto Tprod = null;
		ArrayList<TProducto> list = new ArrayList<TProducto>();
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			boolean found = false;
			
			while((line = br.readLine()) !=null){
				splitArray = line.split(": ", 6);
				Tprod.setNombre(splitArray[0]);
				Tprod.setIdProducto(Integer.parseInt(splitArray[1]));
				Tprod.setCantidad(Integer.parseInt(splitArray[2]));
				Tprod.setTalla(Integer.parseInt(splitArray[3]));
				Tprod.setCategoria(splitArray[4]);
				Tprod.setIdMarca(Integer.parseInt(splitArray[5]));
				list.add(Tprod);	
			}
			br.close();
		}catch(Exception e){
			return null;
		}
		return list;
	}

	@Override
	public TProducto readByName(String name) {
		File f = new File(ARCHIVO);
		TProducto Tprod = null;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			boolean found = false;
			
			while((line = br.readLine()) !=null && !found){
				splitArray = line.split(": ", 6);
				if(splitArray[0].equals(name)){
					//nombre: id: cantidad: talla: categoria: idMarca:
					Tprod = new TProducto(splitArray[0], Integer.parseInt(splitArray[1]), Integer.parseInt(splitArray[2]), Integer.parseInt(splitArray[3]), splitArray[4], Integer.parseInt(splitArray[5]));
					found = true;
				}
				
			}
			br.close();
		}catch(Exception e){
			return null;
		}
		return Tprod;
	}

	@Override
	public int update(TProducto Tprod) {
		String[] stringArray = null;
		//nombre: id: cantidad: talla: categoria: idMarca:
		stringArray[0] = Tprod.getNombre();
		stringArray[1] = Integer.toString(Tprod.getIdProducto());
		stringArray[2] = Integer.toString(Tprod.getCantidad());
		stringArray[3] = Integer.toString(Tprod.getTalla());
		stringArray[4] = Tprod.getCategoria();
		stringArray[5] = Integer.toString(Tprod.getIdMarca());
		
		File f =new File(ARCHIVO);
		
		String[] splitArray = null;
		StringBuilder sb =new StringBuilder();
		
		boolean found =false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        splitArray = line.split(": ", 6);
		        //nombre: id: cantidad: talla: categoria: idMarca:
		        sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": " 
		        + splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5]);
		        	
		        if(splitArray[1] == stringArray[1]){
		        	 sb.append(stringArray[0] + ": " + splitArray[1] + ": " + (splitArray[2] + stringArray[2]) + ": " 
			       		        + stringArray[3] + ": " + stringArray[4] + ": " + splitArray[5]);
		        }else{
		        	   sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": " 
		       		        + splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5]);
		       		        	
		        }
		    }
		    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		    bw.append(sb);
	    	bw.flush();
	    	bw.close();
		    br.close();
		}
		catch (Exception e) {
		   return -1;
		}
		return 0;
	}

}
