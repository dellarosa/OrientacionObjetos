package bo;

import utils.MiException;
import dao.GeneralDAO;
import daoImplementaciones.GeneralDAO_SQL_Impl;

public class GeneralBO {
	
	private GeneralDAO generalDao;

	public GeneralBO()
	{
		generalDao=new GeneralDAO_SQL_Impl();
	}
	
	public boolean crearIndex(String index, String tabla, String indextabla) {
		//generalDao=new GeneralDAO_SQL_Impl();
		return generalDao.crearIndex(index, tabla, indextabla);
	}

	public void crearTablas() throws MiException {
		
		generalDao.crearTablas();
	}
	
}
