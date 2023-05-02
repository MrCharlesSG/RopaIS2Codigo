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
		String[] stringArray = new String[6];
		//  nombre:  id: cantidad: talla: categoria: idMarca:
		stringArray[0] = Tprod.getNombre();
		stringArray[1] = Integer.toString(Tprod.getIdProducto());
		stringArray[2] = Integer.toString(Tprod.getCantidad());
		stringArray[3] = Integer.toString(Tprod.getTalla());
		stringArray[4] = Tprod.getCategoria();
		stringArray[5] = Integer.toString(Tprod.getIdMarca());
		
		File f =new File(ARCHIVO);
		
		String[] splitArray = new String[6];
		splitArray[1] = "-1";
		StringBuilder sb =new StringBuilder();
		int id=-1;
		boolean found =false;
		if(f.length()==0){
			sb.append(stringArray[0] + ": " + 1 + ": " + stringArray[2] + ": " 
			        + stringArray[3] + ": " + stringArray[4] + ": " + stringArray[5] + System.lineSeparator());
			id=1;
			found =true;
		}

		else{
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		
		    String line;
		    while ((line = br.readLine()) != null && found == false) {
		    	if(!line.equalsIgnoreCase("")){
		        splitArray = line.split(": ");

		        	if(splitArray[1].equals(stringArray[1]) && splitArray[5].equals(stringArray[5])&&stringArray[2].equalsIgnoreCase("0")){

		        		found = true;
		        		splitArray[2]=stringArray[2] ;
		        	}
		        	//nombre: id: cantidad: talla: categoria: idMarca:
		        	sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": " 
		        			+ splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5] + System.lineSeparator());
		        	id=(Integer.parseInt(splitArray[1]) + 1);
		    	}
		    }
		    br.close();
		    if(!found){
		    	sb.append(stringArray[0] + ": " + id + ": " + stringArray[2] + ": " 
				        + stringArray[3] + ": " + stringArray[4] + ": " + stringArray[5] + System.lineSeparator());
		    }
			}catch (Exception e) {
				   return -1;
				}
		}
		    try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
		    bw.append(sb);
		   	bw.flush();
		   	bw.close();
		   	}catch (Exception e) {
		   		return -1;
		    	}
		return id;
	}

	@Override
	public int delete(int id) {
		File f = new File(ARCHIVO);
		int ID=-1;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			StringBuilder sb = new StringBuilder();
			
			while((line = br.readLine()) !=null){
				splitArray = line.split(": ");
				if(!splitArray[1].equals(Integer.toString(id)) ){
					//nombre: id: cantidad: talla: categoria: idMarca:
					sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": " 
							+ splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5] + System.lineSeparator());
				}
				else if(splitArray[1].equals(Integer.toString(id))){
					sb.append(splitArray[0] + ": " + splitArray[1] + ": " + 0 + ": " 
							+ splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5] + System.lineSeparator());
				ID=id;
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
		return ID;
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
					Tprod = new TProducto(splitArray[0], Integer.parseInt(splitArray[2]), Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[1]), splitArray[4], Integer.parseInt(splitArray[5]));
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
		ArrayList<TProducto> list = new ArrayList<TProducto>();
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			
			while((line = br.readLine()) !=null){
				if(!line.equalsIgnoreCase("")){
				splitArray = line.split(": ", 6);
				list.add(new TProducto(splitArray[0], Integer.parseInt(splitArray[2]), Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[1]), splitArray[4], Integer.parseInt(splitArray[5])));	
			
				}
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
		TProducto Tprod =null;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			boolean found = false;
			
			while((line = br.readLine()) !=null && !found){
				splitArray = line.split(": ", 6);
				if(splitArray[0].equals(name)){
					//nombre: id: cantidad: talla: categoria: idMarca:
					Tprod = new TProducto(splitArray[0], Integer.parseInt(splitArray[2]), Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[1]), splitArray[4], Integer.parseInt(splitArray[5]));
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
		String[] stringArray = new String[6];
		//nombre: id: cantidad: talla: categoria: idMarca:
		stringArray[0] = Tprod.getNombre();
		stringArray[1] = Integer.toString(Tprod.getIdProducto());
		stringArray[2] = Integer.toString(Tprod.getCantidad());
		stringArray[3] = Integer.toString(Tprod.getTalla());
		stringArray[4] = Tprod.getCategoria();
		stringArray[5] = Integer.toString(Tprod.getIdMarca());
		
		File f =new File(ARCHIVO);
		int id=-1;
		String[] splitArray = null;
		StringBuilder sb =new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null && !line.equals("")) {
		        splitArray = line.split(": ", 6);
		        //nombre: id: cantidad: talla: categoria: idMarca:
		        	
		        if(splitArray[1].equals(stringArray[1])){
		        	 sb.append(stringArray[0] + ": " + splitArray[1] + ": " + (Integer.parseInt(splitArray[2]) + Integer.parseInt(stringArray[2])) + ": " + stringArray[3]
		        			 + ": " + stringArray[4] + ": " + splitArray[5] + System.lineSeparator());
		        	 id=Tprod.getIdProducto();
		        }else{
		        	   sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": " 
		       		        + splitArray[3] + ": " + splitArray[4] + ": " + splitArray[5] + System.lineSeparator());
		       		        	
		        }
		    }
		    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		    bw.append(sb);
	    	bw.flush();
	    	bw.close();
		    br.close();
		}
		catch (Exception e) {
		   return id;
		}
		return id;
	}

	@Override
	public boolean restarCantidad(int id, int cant) {
		File f =new File(ARCHIVO);
		
		String[] splitArray = new String[6];
		StringBuilder sb =new StringBuilder();
		
		boolean found =false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null && !line.equals("")) {
		        splitArray = line.split(": ", 6);
		        if(Integer.parseInt(splitArray[1]) == id){
		        	sb.append(splitArray[0] + ": " + splitArray[1] + ": " + (Integer.parseInt(splitArray[2]) - cant) + ": "
		        + splitArray[3] + ": " + splitArray[4] + ": " +splitArray[5]+ System.lineSeparator());
		        	found = true;
		        }else{
		        	sb.append(splitArray[0] + ": " + splitArray[1] + ": " + splitArray[2] + ": "
		    		        + splitArray[3] + ": " + splitArray[4] + ": " +splitArray[5]+ System.lineSeparator());
		        }
		    }
		    try(    BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
		        bw.append(sb);
		        bw.flush();
		    }
		}catch(Exception e){
			return false;
		};
		return found;
	}

}
