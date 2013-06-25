package bo;

import java.util.List;

import utils.MiException;
import dao.ReparacionDAO;
import daoImplementaciones.ReparacionDAOSQLImpl;
import entities.Reparacion;

public class ReparacionBO {
	public ReparacionDAO reparacionDao;

	public ReparacionBO()
	{
		reparacionDao=new ReparacionDAOSQLImpl();
	}
	public boolean crearTablaReparacionAutoparte() throws MiException {
		return reparacionDao.crearTablaReparacionAutoparte();
	}

	public boolean crearTablaReparacion() throws MiException {
		return reparacionDao.crearTablaReparacion();
	}

	public List<Reparacion> cargaReparaciones() throws MiException {
		return reparacionDao.cargaReparaciones();
	}

	public boolean insertarupdateReparacionFinal(Reparacion reparacion,
			int inLastIdRA) throws MiException {
		return reparacionDao.insertarupdateReparacionFinal(reparacion,
				inLastIdRA);
	}

	public boolean insertarReparacionInicio(Reparacion nuevareparacion)
			throws MiException {
		return reparacionDao.insertarReparacionInicio(nuevareparacion);
	}

	public int buscarUltimaReparacionAutoparteId() throws MiException {
		return reparacionDao.buscarUltimaReparacionAutoparteId();
	}

	public int buscarUltimaReparacionId() throws MiException {
		return reparacionDao.buscarUltimaReparacionId();
	}

	
}
