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
		TProducto TprodAux;
		if(ComprobadorSintactico.isName(Tprod.getNombre()) && ComprobadorSintactico.isPositive(Tprod.getTalla()) && ComprobadorSintactico.isName(Tprod.getCategoria())){
			TprodAux = dao.readByName(Tprod.getNombre());
			DAOMarca daoM = FactoriaIntegracion.getInstance().generaDAOMarca();
			TMarca tMarca = daoM.read(Tprod.getIdMarca());
			if(TprodAux != null && tMarca != null){
				if(TprodAux.getIdMarca() != Tprod.getIdMarca()){
				dao.create(Tprod);
				daoM.actualizarCantidad(Tprod.getIdMarca(),true);
				}else{
					return -1;
				}
			}else{
				return -1;
			}
		}else{
			return -1;
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		if(ComprobadorSintactico.isPositive(id)){
			TProducto Tprod = read(id);
			if(!Tprod.getNombre().equals(null) && Tprod.getCantidad() == 0){
				dao.delete(Tprod.getIdProducto());
				DAOMarca daoM = FactoriaIntegracion.getInstance().generaDAOMarca();
				TMarca tMarca = daoM.read(Tprod.getIdMarca());
				if(tMarca.getCantidad() !=  0)
					daoM.actualizarCantidad(Tprod.getIdMarca(),false);
			}else{
				return -1;
			}
		}else{
			return -1;
		}
		return 0;
	}

	@Override
	public TProducto read(int id) {
		TProducto Tprod = null;
		if(ComprobadorSintactico.isPositive(id)){
			Tprod = dao.read(id);
		}
		return Tprod;
	}

	@Override
	public Collection<TProducto> readAll() {
		return dao.readAll();
	}

	@Override
	public TProducto readByName(String name) {
		TProducto Tprod = null;
		if(ComprobadorSintactico.isName(name)){
			Tprod = dao.readByName(name);
		}
		return Tprod;
	}

	@Override
	public int update(TProducto Tprod) {
		if(ComprobadorSintactico.isName(Tprod.getNombre())){
			TProducto Tprod2;
			Tprod2 = dao.readByName(Tprod.getNombre());
			if(Tprod.getIdProducto() == -1){
				dao.update(new TProducto(Tprod2.getNombre(), Tprod.getIdProducto(), Tprod.getCantidad(), Tprod.getTalla(), Tprod.getCategoria(), Tprod.getIdMarca()));
			}else{
				return -1;
			}
		}
		return 0;
	}

}
