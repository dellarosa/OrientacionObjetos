package bo;

import java.util.List;

import utils.MiException;
import dao.FiltroDAO;
import daoImplementaciones.FiltroDAO_SQL_Impl;
import entities.Filtro;

public class FiltroBO {
	private FiltroDAO filtroDao;

	public FiltroBO()
	{
		filtroDao=new FiltroDAO_SQL_Impl();
	}
	public boolean crearTablaFiltro() throws MiException {
		return filtroDao.crearTablaFiltro();
	}

	public List<Filtro> cargarFiltros() throws MiException {
		return filtroDao.cargarFiltros();
	}

	public Filtro buscarFiltroPorIdAutoParte(int id) throws MiException {
		return filtroDao.buscarFiltroPorIdAutoParte(id);
	}

	public int buscarUltimoFiltroId() throws MiException {
		return filtroDao.buscarUltimoFiltroId();
	}

	public boolean updateFiltro(Filtro filtro) throws MiException {
		return filtroDao.updateFiltro(filtro);
	}

	public boolean insertarFiltro(Filtro filtro) throws MiException {
		return filtroDao.insertarFiltro(filtro);
	}

	public boolean insertarFiltro(int id, int autoparte_ID, String tamanio,
			String material) throws MiException {
		return filtroDao.insertarFiltro(id, autoparte_ID, tamanio, material);
	}

	public boolean eliminarFiltro(Filtro filtro) throws MiException {
		return filtroDao.eliminarFiltro(filtro);
	}
	
}
