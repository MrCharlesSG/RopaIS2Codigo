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
	
	private DAOProducto dao;
	private SAMarca saMarca;
	public SAProductoIMP(){
	 dao = FactoriaIntegracion.getInstance().generaDAOProducto();
	 saMarca=FactoriaNegocio.getInstance().generaSAMarca();
	}
	

	@Override
	public int create(TProducto Tprod) {
		TProducto aux;
		int id=-1;
		if(ComprobadorSintactico.isName(Tprod.getNombre()) && ComprobadorSintactico.isPositive(Tprod.getTalla()) && ComprobadorSintactico.isName(Tprod.getCategoria())){
			aux = dao.readByName(Tprod.getNombre());

			TMarca tMarca = saMarca.read(Tprod.getIdMarca());
			if(tMarca!=null) {
				if(aux==null) {
					id=dao.create(Tprod);
					saMarca.actualizarCantidad(Tprod.getIdMarca(),true);
				}else if(aux.getCantidad()==0 && aux.getTalla()==Tprod.getTalla() && 
						Tprod.getCategoria().equals(aux.getCategoria())){
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
				dao.update(new TProducto(Tprod.getNombre(), Tprod.getCantidad(), Tprod.getTalla(), Tprod.getIdProducto(), Tprod.getCategoria(), Tprod.getIdMarca(), Tprod.getPrecio()));
			}else{
				return -1;
			}
		}
		return 0;
	}


	@Override
	public boolean restarCantidad(int id, int cant) {
		TProducto tProd = dao.read(id);
		if(tProd!=null && (tProd.getCantidad() - cant) >= 0){
			return dao.restarCantidad(id, cant);
		}
		return false;
	}
	@Override
	public boolean devolverCantidad(int id, int cant){
		TProducto tProd = dao.read(id);
		if(tProd!=null){
			return dao.restarCantidad(id, cant*-1);
		}
		return false;
	}

}
