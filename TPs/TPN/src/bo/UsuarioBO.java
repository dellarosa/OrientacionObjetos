package bo;

import java.util.List;

import utils.MiException;

import dao.UsuarioDAO;
import daoImplementaciones.UsuarioDAOSQLImpl;
import entities.Usuario;

public class UsuarioBO {
	private UsuarioDAO usuarioDao;
	public UsuarioBO()
	{
		usuarioDao=new UsuarioDAOSQLImpl();
	}
	public boolean insertarUsuario(Usuario usuario)throws MiException{
	
		return(usuarioDao.insertarUsuario(usuario));
	}
	
	public int buscarUltimoUsuarioId()throws MiException
	{
		return(usuarioDao.buscarUltimoUsuarioId());
	}
	public Usuario buscarUsuarioPorApodo(String userName)throws MiException
	{	
		return(usuarioDao.buscarUsuarioPorApodo(userName));
	}
	public List<Usuario> cargarUsuarios()throws MiException
	{
		return (usuarioDao.cargarUsuarios()); 
	}
	public boolean crearTablaUsuario()throws MiException
	{
		return (usuarioDao.crearTablaUsuario());
		
	}
	public boolean eliminarUsuario(Usuario usuario) throws MiException
	{
		return(	usuarioDao.eliminarUsuario(usuario)); 
	}
	public boolean insertarUsuario(int id, String name, String mail, String user, String pass, int logueado, String jerarquia)throws MiException
	{
		return(usuarioDao.insertarUsuario(id, name, mail, user, pass, logueado, jerarquia));
	}
	
	public boolean updateUsuario(Usuario usuario)throws MiException
	{
		return(usuarioDao.updateUsuario(usuario));
	}
	
}
