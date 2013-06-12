package bo;

import java.util.List;

import utils.MiException;
import dao.AceiteDAO;
import daoImplementaciones.AceiteDAOSQLImpl;
import entities.Aceite;

public class AceiteBO {
	private AceiteDAO aceiteDao;

	public AceiteBO()
	{
		aceiteDao=new AceiteDAOSQLImpl();
	}
	public List<Aceite> cargarAceites() throws MiException {
		return aceiteDao.cargarAceites();
	}

	public boolean crearTablaAceite() throws MiException {
		return aceiteDao.crearTablaAceite();
	}

	public boolean eliminarAceite(Aceite aceite) throws MiException {
		return aceiteDao.eliminarAceite(aceite);
	}

	public boolean insertarAceite(int id, int autoparte_ID, int cantLitros,
			String tipo) throws MiException {
		return aceiteDao.insertarAceite(id, autoparte_ID, cantLitros, tipo);
	}

	public boolean insertarAceite(Aceite aceite) throws MiException {
		return aceiteDao.insertarAceite(aceite);
	}

	public boolean updateAceite(Aceite aceite) throws MiException {
		return aceiteDao.updateAceite(aceite);
	}

	public Aceite buscarAceitePorIdAutoParte(int id) throws MiException {
		return aceiteDao.buscarAceitePorIdAutoParte(id);
	}

	public int buscarUltimoAceiteId() throws MiException {
		return aceiteDao.buscarUltimoAceiteId();
	}
	
}
