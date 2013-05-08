package utils;

import entities.Autoparte;
import entities.Cliente;
import entities.Reparacion;
import entities.Usuario;

public class MetodosGrl {

	public Usuario obtenerUsuarioActual()
	{
		Usuario usuario = new Usuario();
		return usuario;
	}
	public Cliente obtenerCliente()
	{
		Cliente cliente = new Cliente();
		
		return cliente;
	}
	public Reparacion obtenerReparacion()
	{
		Reparacion reparacion = new Reparacion();
		
		return reparacion;
	}
	public Autoparte[] obtenerAutopartesEnSistema()
	{
		Autoparte[] autoparte = null;
		
		return autoparte;
	}
	public double obtenerCostoAutopartes(Autoparte[] autopartes)
	{
		return 1.22;
	}
}
