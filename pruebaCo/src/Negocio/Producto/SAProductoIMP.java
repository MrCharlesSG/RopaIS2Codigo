// @author Dokt0r;

package Negocio.Producto;

import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;

import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Producto.DAOProducto;
import Negocio.ComprobadorSintactico;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaNegocio.SAMarca;
import Negocio.MarcaNegocio.TMarca;

public class SAProductoIMP implements SAProducto{
	
	@Override
	public int create(TProducto Tprod) {
		TProducto aux;
		int id=-1;
		if(this.esValida(Tprod)){
			DAOProducto dao = FactoriaIntegracion.getInstance().generaDAOProducto();
			SAMarca saMarca=FactoriaNegocio.getInstance().generaSAMarca();
			
			aux = dao.readByName(Tprod.getNombre());
			TMarca tMarca = saMarca.read(Tprod.getIdMarca());
			if(tMarca!=null&&tMarca.getActivo()) {
				if(aux==null) {
					id=dao.create(Tprod);
				}
				else if(aux.getCantidad()==0){
					Tprod.setIdProducto(aux.getIdProducto());
					id=dao.update(Tprod);
				}
			}
		}
		return id;
	}


	@Override
	public int delete(int id) {
		int ID=-1;
		if(ComprobadorSintactico.isPositive(id)){
			DAOProducto dao = FactoriaIntegracion.getInstance().generaDAOProducto();
			TProducto Tprod = read(id);
			if(Tprod!=null){
				ID=dao.delete(Tprod.getIdProducto());
			}
		}
		return ID;
	}
	
	

	@Override
	public TProducto read(int id) {
		TProducto Tprod = null;
		if(ComprobadorSintactico.isPositive(id)){
			DAOProducto dao = FactoriaIntegracion.getInstance().generaDAOProducto();
			Tprod = dao.read(id);
		}
		return Tprod;
	}

	@Override
	public Collection<TProducto> readAll() {
		DAOProducto dao = FactoriaIntegracion.getInstance().generaDAOProducto();
		return dao.readAll();
	}

	@Override
	public TProducto readByName(String name) {
		TProducto Tprod = null;
		if(ComprobadorSintactico.isName(name)){
			DAOProducto dao = FactoriaIntegracion.getInstance().generaDAOProducto();
			Tprod = dao.readByName(name);
		}
		return Tprod;
	}

	@Override
	public int update(TProducto Tprod) {
		int id=-1;
		if(ComprobadorSintactico.isName(Tprod.getNombre())){
			DAOProducto dao = FactoriaIntegracion.getInstance().generaDAOProducto();
			TProducto Tprod2;
			Tprod2 = dao.read(Tprod.getIdProducto());
			if(Tprod2!=null)
				id=dao.update(Tprod);
			
		}
		return id;
	}
	@Override
	public boolean restarCantidad(int id, int cant) {
		DAOProducto dao = FactoriaIntegracion.getInstance().generaDAOProducto();
		TProducto tProd = dao.read(id);
		if(tProd!=null && (tProd.getCantidad() - cant) >= 0){
			return dao.restarCantidad(id, cant);
		}
		return false;
	}
	private boolean esValida(TProducto Tprod){
		return ComprobadorSintactico.isName(Tprod.getNombre()) && ComprobadorSintactico.isPositive(Tprod.getTalla()) && ComprobadorSintactico.isName(Tprod.getCategoria());
	}
}
