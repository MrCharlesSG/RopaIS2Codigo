// @author Dokt0r;

package Negocio.Producto;

import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;

import java.util.Collection;

import Negocio.ComprobadorSintactico;

public class SAProductoIMP implements SAProducto{
	
	private ComprobadorSintactico _CS;
	
	public SAProductoIMP(){
		this._CS = new ComprobadorSintactico();
	}
	

	@Override
	public int create(TProducto Tprod) {
		//TODO
		if(_CS.isName(Tprod.getNombre()) && _CS.isPositive(Tprod.getTalla()) && _CS.isName(Tprod.getCategoria())){
			//call dao Tprod = ReadByName(Tprod)
			if(Tprod.getIdProducto() == -1){
				//dao create()
			}else{
				return -1;
			}
		}else{
			return -1;
		}
		return 0;
	}

	@Override
	public int delete(TProducto Tprod) {
		//TODO
		if(_CS.isPositive(Tprod.getIdProducto())){
			//new TProd tprod = read(idProd)
			if(!Tprod.getNombre().equals(null)){
				//dao delete()
			}else{
				return -1;
			}
		}else{
			return -1;
		}
		return 0;
	}

	@Override
	public TProducto read(TProducto Tprod) {
		//TODO
		if(_CS.isPositive(Tprod.getIdProducto())){
			//new TProd tprod = read(idProd)
		}
		return Tprod;
	}

	@Override
	public Collection<TProducto> readAll() {
		//TODO
		//dao readAll
		return null;
	}

	@Override
	public TProducto readByName(TProducto Tprod) {
		// TODO
		if(_CS.isName(Tprod.getNombre())){
			//Tprod = dao read
			//TProd
		}
		return Tprod;
	}

	@Override
	public int update(TProducto Tprod) {
		// TODO Auto-generated method stub
		if(_CS.isName(Tprod.getNombre())){
			// dao readByName
			//if() exist then dao update()
			//else -1
		}
		return 0;
	}

}
