// @author Dokt0r;

package Negocio.Producto;

import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;

import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.Producto.DAOProducto;
import Negocio.ComprobadorSintactico;
import Negocio.MarcaNegocio.TMarca;

public class SAProductoIMP implements SAProducto{
	
	DAOProducto dao;
	
	public SAProductoIMP(){
	 dao = FactoriaIntegracion.getInstance().generaDaoProducto();
	}
	

	@Override
	public int create(TProducto Tprod) {
		if(ComprobadorSintactico.isName(Tprod.getNombre()) && ComprobadorSintactico.isPositive(Tprod.getTalla()) && ComprobadorSintactico.isName(Tprod.getCategoria())){
			Tprod = dao.readByName(Tprod.getNombre());
			if(Tprod.getIdProducto() == -1){
				dao.create(Tprod);
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
		if(ComprobadorSintactico.isPositive(Tprod.getIdProducto())){
			Tprod = read(Tprod);
			if(!Tprod.getNombre().equals(null) && Tprod.getCantidad() == 0){
				dao.delete(Tprod.getIdProducto());
				DAOMarca daoM = FactoriaIntegracion.getInstance().generaDAOMarca();
				TMarca tMarca = daoM.read(Tprod.getIdMarca());
				//if(tMarca.getCant !=  0)
				// daoM.update
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
		if(ComprobadorSintactico.isPositive(Tprod.getIdProducto())){
			Tprod = dao.read(Tprod.getIdProducto());
		}
		return Tprod;
	}

	@Override
	public Collection<TProducto> readAll() {
		dao.readAll();
		return null;
	}

	@Override
	public TProducto readByName(TProducto Tprod) {
		// TODO
		if(ComprobadorSintactico.isName(Tprod.getNombre())){
			//Tprod = dao read
			//TProd
		}
		return Tprod;
	}

	@Override
	public int update(TProducto Tprod) {
		// TODO Auto-generated method stub
		if(ComprobadorSintactico.isName(Tprod.getNombre())){
			// dao readByName
			//if() exist then dao update()
			//else -1
		}
		return 0;
	}

}
