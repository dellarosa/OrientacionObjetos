
package bo;

import dao.GeneralDAO;
import daoImplementaciones.GeneralDAOSQLImpl;
import utils.MiException;

public class GeneralBO {
	
	private GeneralDAO generalDao;

	public GeneralBO()
	{
		generalDao=new GeneralDAOSQLImpl();
	}
	
	public boolean crearIndex(String index, String tabla, String indextabla) {
		//generalDao=new GeneralDAO_SQL_Impl();
		return generalDao.crearIndex(index, tabla, indextabla);
	}

	public void crearTablas() throws MiException {
		
		generalDao.crearTablas();
	}	
}
