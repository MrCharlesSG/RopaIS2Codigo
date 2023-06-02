//Dokt0r
package Integracion.Producto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.lang.StringBuilder;

import Negocio.Producto.TProducto;

public class DAOProductoIMP implements DAOProducto{

	static final String ARCHIVO= "Productos.txt";
	
	/*
	 * (non-Javadoc)
	 * @see Integracion.Producto.DAOProducto#create(Negocio.Producto.TProducto)
	 * 
	 * Create: creates new product entry with the given data and if the entry does not exist
	 */
	@Override
	public int create(TProducto Tprod) {
		String[] stringArray = new String[7];
		//  nombre:  id: cantidad: talla: categoria: idMarca:
		stringArray[0] = Tprod.getNombre();
		stringArray[1] = Integer.toString(Tprod.getIdProducto());
		stringArray[2] = Integer.toString(Tprod.getCantidad());
		stringArray[3] = Integer.toString(Tprod.getTalla());
		stringArray[4] = Tprod.getCategoria();
		stringArray[5] = Integer.toString(Tprod.getIdMarca());
		stringArray[6] = Double.toString(Tprod.getPrecio());
		
		File f =new File(ARCHIVO);
		
		String[] splitArray = new String[6];
		splitArray[1] = "-1";
		StringBuilder sb =new StringBuilder();
		int id=-1;
		boolean found =false;
		//When no entries exist simply adds the ne entry
		if(f.length()==0){
			sb.append(stringArray[0] + ":" + 1 + ":" + stringArray[2] + ":" 
			        + stringArray[3] + ":" + stringArray[4] + ":" + stringArray[5] + ":"
			        + stringArray[6] + System.lineSeparator());
			id=1;
			found =true;
		}
		/*
		 * ELSE: Start to iterate through the info and if no entry is found with same data as input adds it at the end
		 */
		else{
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		
		    String line;
		    while ((line = br.readLine()) != null && found == false) {
		    	if(!line.equalsIgnoreCase("")){
		        splitArray = line.split(":");

		        	if(splitArray[1].equals(stringArray[1]) && splitArray[5].equals(stringArray[5])&&stringArray[2].equalsIgnoreCase("0")){

		        		found = true;
		        		splitArray[2]=stringArray[2] ;
		        	}
		        	//nombre: id: cantidad: talla: categoria: idMarca:
		        	sb.append(splitArray[0] + ":" + splitArray[1] + ":" + splitArray[2] + ":" 
		        			+ splitArray[3] + ":" + splitArray[4] + ":" + splitArray[5] + ":" 
		        			+ splitArray[6] + System.lineSeparator());
		        	id=(Integer.parseInt(splitArray[1]) + 1);
		    	}
		    }
		    br.close();
		    
		    if(!found){
		    	sb.append(stringArray[0] + ":" + id + ":" + stringArray[2] + ":" 
				        + stringArray[3] + ":" + stringArray[4] + ":" + stringArray[5] + ":"
		    			+ splitArray[6] + System.lineSeparator());
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

	/*
	 * (non-Javadoc)
	 * @see Integracion.Producto.DAOProducto#delete(int)
	 * 
	 * Delete: to "Delete" an entry, makes the variable "cantidad" = 0
	 */
	@Override
	public int delete(int id) {
		File f = new File(ARCHIVO);
		int ID=-1;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			StringBuilder sb = new StringBuilder();
			
			while((line = br.readLine()) !=null){
				splitArray = line.split(":");
				if(!splitArray[1].equals(Integer.toString(id)) ){
					//nombre: id: cantidad: talla: categoria: idMarca: precio
					sb.append(splitArray[0] + ":" + splitArray[1] + ":" + splitArray[2] + ":" 
							+ splitArray[3] + ":" + splitArray[4] + ":" + splitArray[5] + ":" 
							+ splitArray[6] + System.lineSeparator());
				}
				// turns the quantity to 0
				else {
					sb.append(splitArray[0] + ":" + splitArray[1] + ":" + 0 + ":" 
							+ splitArray[3] + ":" + splitArray[4] + ":" + splitArray[5] + ":"
							+ splitArray[6] + System.lineSeparator());
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

	/*
	 * (non-Javadoc)
	 * @see Integracion.Producto.DAOProducto#read(int)
	 * 
	 * READ: returns an entry based on the given number ID
	 */
	@Override
	public TProducto read(int id) {
		
		File f = new File(ARCHIVO);
		TProducto Tprod = null;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			boolean found = false;
			
			while((line = br.readLine()) !=null && !found){
				splitArray = line.split(":", 6);
				if(splitArray[1].equals(Integer.toString(id))){
					//nombre: id: cantidad: talla: categoria: idMarca:
					Tprod = new TProducto(splitArray[0], Integer.parseInt(splitArray[2]), 
							Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[1]), 
							splitArray[4], Integer.parseInt(splitArray[5]), Double.parseDouble(splitArray[6]));
					found = true;
				}
				
			}
			br.close();
		}catch(Exception e){
			return null;
		}
		return Tprod;
	}
/*
 * (non-Javadoc)
 * @see Integracion.Producto.DAOProducto#readAll()
 * 
 * READALL; returns a collection with every entry
 */
	@Override
	public Collection<TProducto> readAll() {

		File f = new File(ARCHIVO);
		ArrayList<TProducto> list = new ArrayList<TProducto>();
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			
			//loop: iterates across all entries
			while((line = br.readLine()) !=null){
				if(!line.equalsIgnoreCase("")){
				splitArray = line.split(":");
				list.add(new TProducto(splitArray[0], Integer.parseInt(splitArray[2]),
						Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[1]), splitArray[4], 
						Integer.parseInt(splitArray[5]), Double.parseDouble(splitArray[6])));	
			
				}
			}
			br.close();
		}catch(Exception e){
			return null;
		}
		return list;
	}
/*
 * (non-Javadoc)
 * @see Integracion.Producto.DAOProducto#readByName(java.lang.String)
 * 
 * READBYNAME: returns a given entry based on the given name ID
 */
	@Override
	public TProducto readByName(String name) {
		File f = new File(ARCHIVO);
		TProducto Tprod =null;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			boolean found = false;
			
			while((line = br.readLine()) !=null && !found){
				splitArray = line.split(":", 6);
				if(splitArray[0].equals(name)){
					//nombre: id: cantidad: talla: categoria: idMarca:
					Tprod = new TProducto(splitArray[0], Integer.parseInt(splitArray[2]), 
							Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[1]), 
							splitArray[4], Integer.parseInt(splitArray[5]), Double.parseDouble(splitArray[6]));
					found = true;
				}
			}
			br.close();
		}catch(Exception e){
			return null;
		}
		return Tprod;
	}

	/*
	 * (non-Javadoc)
	 * @see Integracion.Producto.DAOProducto#update(Negocio.Producto.TProducto)
	 * 
	 * UPDATE: updates the entry corresponding to the entry ID with the given information
	 */
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
		stringArray[6] = Double.toString(Tprod.getPrecio());
		
		File file =new File(ARCHIVO);
		int id=-1;
		String[] splitArray = null;
		StringBuilder buffer =new StringBuilder();
		
		try(Scanner scanner=new Scanner(file)) {//bufferreader
			//al dao le deeria de dar igual si esta escribiendo algo mal de eso ya se encargan otros
			//recojo los antiguos datos
			while(scanner.hasNext()) {
		        splitArray = scanner.nextLine().split(":");
		        //nombre: id: cantidad: talla: categoria: idMarca:
		        	
		        if(splitArray[1].equals(stringArray[1])){
		        	buffer.append(stringArray[0] + ":" + splitArray[1] + ":" + stringArray[2]+ ":" + stringArray[3]
		        			 + ":" + stringArray[4] + ":" + splitArray[5] + ":"
		        			 + stringArray[6] + System.lineSeparator());
		        	 id=Tprod.getIdProducto();
		        }else{
		        	buffer.append(splitArray[0] + ":" + splitArray[1] + ":" + splitArray[2] + ":" 
		       		        + splitArray[3] + ":" + splitArray[4] + ":" + splitArray[5] + ":"
		        			+ splitArray[6] + System.lineSeparator());
		       		        	
		        }
		    }
		}catch (IOException | NumberFormatException e) {
				return -1;
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

	/*
	 * (non-Javadoc)
	 * @see Integracion.Producto.DAOProducto#restarCantidad(int, int)
	 * 
	 * RESTARCANTIDAD: deducts a given quantity from a given entry
	 */
	
	@Override
	public Collection<TProducto> readByMarca(int iD) {
		File f = new File(ARCHIVO);
		ArrayList<TProducto> list = new ArrayList<TProducto>();
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			String line;
			String[] splitArray;
			
			while((line = br.readLine()) !=null){
				if(!line.equalsIgnoreCase("")){
				splitArray = line.split(":");
			
				TProducto producto=new TProducto(splitArray[0], Integer.parseInt(splitArray[2]),
						Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[1]), splitArray[4], Integer.parseInt(splitArray[5]),Integer.parseInt(splitArray[6]));	
					if(producto.getIdMarca()==iD){
						list.add(producto);
					}
				}
			}
		
		}catch(Exception e){
			return null;
		}
		return list;
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
		        splitArray = line.split(":", 6);
		        if(Integer.parseInt(splitArray[1]) == id){
		        	sb.append(splitArray[0] + ":" + splitArray[1] + ":" + (Integer.parseInt(splitArray[2]) - cant) + ":"
		        		+ splitArray[3] + ":" + splitArray[4] + ":" +splitArray[5] + ":"
		        		+ splitArray[6] + System.lineSeparator());
		        	found = true;
		        }else{
		        	sb.append(splitArray[0] + ":" + splitArray[1] + ":" + splitArray[2] + ":"
		    		        + splitArray[3] + ":" + splitArray[4] + ":" +splitArray[5]+ ":"
		    		        + splitArray[6] + System.lineSeparator());
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
