package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Filtro;

import utils.MiException;



public interface FiltroDAO {

	public boolean crearTablaFiltro() throws MiException;
	
	public List<Filtro> cargarFiltros() throws MiException;
	
	public Filtro buscarFiltroPorIdAutoParte(int id) throws MiException;
	
	public int buscarUltimoFiltroId() throws MiException;
	
	public boolean updateFiltro(Filtro filtro) throws MiException;
	
	public boolean insertarFiltro(Filtro filtro) throws MiException;

	public boolean insertarFiltro(int id,int autoparte_ID,String tamanio,String material) throws MiException;
	
	public boolean eliminarFiltro(Filtro filtro) throws MiException ;
	
}
