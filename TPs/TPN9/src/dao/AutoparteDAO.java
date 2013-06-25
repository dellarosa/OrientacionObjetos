package dao;

import java.util.List;

import entities.Autoparte;

import utils.MiException;



public interface AutoparteDAO {

	public int buscarUltimaAutoparteId() throws  MiException;
	
	public boolean updateAutoparte(Autoparte autoparte) throws MiException;
	public boolean insertarAutoparte(Autoparte autoparte) throws MiException;
	
	public boolean insertarAutoparte(int autoparte_ID,String tipo,String marca,String modelo,double costo,int cantDisponible) throws MiException;
	public boolean eliminarAutoparte(Autoparte autoparte) throws MiException;
	
	public List<Autoparte> cargaAutopartes() throws  MiException ;
	
	public boolean crearTablaAutoparte() throws MiException;
	public Autoparte buscarAutoPartePorId(int id) throws MiException;
	
}
