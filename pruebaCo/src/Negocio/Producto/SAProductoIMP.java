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
		int id=-1;
		if(ComprobadorSintactico.isName(Tprod.getNombre()) && ComprobadorSintactico.isPositive(Tprod.getTalla()) && ComprobadorSintactico.isName(Tprod.getCategoria())){
			TprodAux = dao.readByName(Tprod.getNombre());

			TMarca tMarca = saMarca.read(Tprod.getIdMarca());
			if(((TprodAux == null)||(coinciden(Tprod,TprodAux)&&TprodAux.getCantidad()==0))&& tMarca != null){	
				id=dao.create(Tprod);
				saMarca.actualizarCantidad(Tprod.getIdMarca(),true);
			}
		}
		return id;
	}
	
	

	private boolean coinciden(TProducto tprod, TProducto tprodAux) {
		return tprod.getCategoria().equalsIgnoreCase(tprodAux.getCategoria())&&tprod.getTalla()==tprodAux.getTalla();
	}


	@Override
	public int delete(int id) {
		int ID=-1;
		if(ComprobadorSintactico.isPositive(id)){
			TProducto Tprod = read(id);
			if(Tprod!=null){
				ID=dao.delete(Tprod.getIdProducto());
				saMarca.actualizarCantidad(Tprod.getIdMarca(),false);
			}
		}
		return ID;
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
