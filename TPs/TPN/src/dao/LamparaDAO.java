package dao;

import java.util.List;

import entities.Lampara;

import utils.MiException;



public interface LamparaDAO {

	public int buscarUltimoLamparaId() throws  MiException;
	public Lampara buscarLamparaPorIdAutoParte(int id) throws MiException;
	
	public boolean updateLampara(Lampara lampara) throws MiException;
	public boolean insertarLampara(Lampara lampara) throws MiException;
	public boolean insertarLampara(int id,int autoparte_ID,String color,String tamanio) throws MiException;
	public List<Lampara> cargarLamparas() throws MiException;
	public boolean crearTablaLampara() throws MiException;
	public boolean eliminarLampara(Lampara lampara) throws MiException;
	
}
