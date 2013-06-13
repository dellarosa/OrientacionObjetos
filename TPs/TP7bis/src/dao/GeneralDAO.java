package dao;

import utils.MiException;

public interface GeneralDAO {

	public boolean crearIndex(String index,String tabla,String indextabla)throws MiException;;
	public void crearTablas() throws MiException;
	
}
