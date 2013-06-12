package dao;

import java.util.List;

import entities.Aceite;

import utils.MiException;



public interface AceiteDAO {

	public List<Aceite> cargarAceites() throws MiException;
	
	public boolean crearTablaAceite() throws MiException;
	
	public boolean eliminarAceite(Aceite aceite) throws MiException;
	
	public boolean insertarAceite(int id,int autoparte_ID,int cantLitros,String tipo) throws MiException;
	
	public boolean insertarAceite(Aceite aceite) throws MiException;
	
	public boolean updateAceite(Aceite aceite) throws MiException;
	
	public Aceite buscarAceitePorIdAutoParte(int id) throws MiException;
	
	public int buscarUltimoAceiteId() throws MiException;
}
