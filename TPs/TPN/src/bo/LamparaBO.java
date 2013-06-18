package bo;

import java.util.List;

import utils.MiException;
import dao.LamparaDAO;
import daoImplementaciones.LamparaDAOSQLImpl;
import entities.Lampara;

public class LamparaBO {
	private LamparaDAO lamparaDao;

	public LamparaBO()
	{
		lamparaDao=new LamparaDAOSQLImpl();
		
	}
	public int buscarUltimoLamparaId() throws MiException {
		return lamparaDao.buscarUltimoLamparaId();
	}

	public Lampara buscarLamparaPorIdAutoParte(int id) throws MiException {
		return lamparaDao.buscarLamparaPorIdAutoParte(id);
	}

	public boolean updateLampara(Lampara lampara) throws MiException {
		return lamparaDao.updateLampara(lampara);
	}

	public boolean insertarLampara(Lampara lampara) throws MiException {
		return lamparaDao.insertarLampara(lampara);
	}

	public boolean insertarLampara(int id, int autoparte_ID, String color,
			String tamanio) throws MiException {
		return lamparaDao.insertarLampara(id, autoparte_ID, color, tamanio);
	}

	public List<Lampara> cargarLamparas() throws MiException {
		return lamparaDao.cargarLamparas();
	}

	public boolean crearTablaLampara() throws MiException {
		return lamparaDao.crearTablaLampara();
	}

	public boolean eliminarLampara(Lampara lampara) throws MiException {
		return lamparaDao.eliminarLampara(lampara);
	}
	
}
