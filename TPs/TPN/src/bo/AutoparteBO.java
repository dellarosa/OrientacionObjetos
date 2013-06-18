package bo;

import java.util.List;

import utils.MiException;
import dao.AutoparteDAO;
import daoImplementaciones.AutoparteDAOSQLImpl;
import entities.Autoparte;

public class AutoparteBO {
	
	private AutoparteDAO autoparteDao;
	
	public AutoparteBO()
	{
		autoparteDao=new AutoparteDAOSQLImpl();
	}
	
	
	public int buscarUltimaAutoparteId() throws MiException {
		return autoparteDao.buscarUltimaAutoparteId();
	}

	public boolean updateAutoparte(Autoparte autoparte) throws MiException {
		return autoparteDao.updateAutoparte(autoparte);
	}

	public boolean insertarAutoparte(Autoparte autoparte) throws MiException {
		return autoparteDao.insertarAutoparte(autoparte);
	}

	public boolean insertarAutoparte(int autoparte_ID, String tipo,
			String marca, String modelo, double costo, int cantDisponible)
			throws MiException {
		return autoparteDao.insertarAutoparte(autoparte_ID, tipo, marca,
				modelo, costo, cantDisponible);
	}

	public boolean eliminarAutoparte(Autoparte autoparte) throws MiException {
		return autoparteDao.eliminarAutoparte(autoparte);
	}

	public List<Autoparte> cargaAutopartes() throws MiException {
		return autoparteDao.cargaAutopartes();
	}

	public boolean crearTablaAutoparte() throws MiException {
		return autoparteDao.crearTablaAutoparte();
	}
	
}
