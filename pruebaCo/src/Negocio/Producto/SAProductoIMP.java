// @author Dokt0r;

package Negocio.Producto;

import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;

import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.MarcaIntegracion.DAOMarca;
import Integracion.Producto.DAOProducto;
import Negocio.ComprobadorSintactico;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;

public class SAProductoIMP implements SAProducto{
	
	private DAOProducto dao;
	private SAMarca saMarca;
	public SAProductoIMP(){
	 dao = FactoriaIntegracion.getInstance().generaDAOProducto();
	 saMarca=FactoriaNegocio.getInstance().generaSAMarca();
	}
	

	@Override
	public int create(TProducto Tprod) {
		TProducto TprodAux;
		if(ComprobadorSintactico.isName(Tprod.getNombre()) && ComprobadorSintactico.isPositive(Tprod.getTalla()) && ComprobadorSintactico.isName(Tprod.getCategoria())){
			TprodAux = dao.readByName(Tprod.getNombre());
			
			TMarca tMarca = saMarca.read(Tprod.getIdMarca());
			if(TprodAux != null && tMarca != null){
				if(TprodAux.getIdMarca() != Tprod.getIdMarca()){
				dao.create(Tprod);
				saMarca.actualizarCantidad(Tprod.getIdMarca(),true);
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
				
				//TMarca tMarca = saMarca.read(Tprod.getIdMarca());
				//if(tMarca.getCantidad() !=  0)
				saMarca.actualizarCantidad(Tprod.getIdMarca(),false);
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
			Tprod2 = dao.read(Tprod.getIdProducto());
			if(!(Tprod2.getIdProducto() == -1)){
				dao.update(new TProducto(Tprod.getNombre(), Tprod.getCantidad(), Tprod.getTalla(), Tprod.getIdProducto(), Tprod.getCategoria(), Tprod.getIdMarca()));
			}else{
				return -1;
			}
		}
		return 0;
	}

}
