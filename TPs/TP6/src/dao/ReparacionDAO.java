package dao;

import java.util.List;

import entities.Aceite;
import entities.Reparacion;

import utils.MiException;



public interface ReparacionDAO {

	public boolean crearTablaReparacionAutoparte() throws MiException;
	public boolean crearTablaReparacion() throws MiException;
	public List<Reparacion> cargaReparaciones() throws MiException;
	public boolean insertarupdateReparacionFinal(Reparacion reparacion,int inLastIdRA) throws MiException;
	public boolean insertarReparacionInicio(Reparacion nuevareparacion) throws MiException;
	public int buscarUltimaReparacionAutoparteId() throws  MiException;
	public int buscarUltimaReparacionId() throws MiException;
	
	
}
