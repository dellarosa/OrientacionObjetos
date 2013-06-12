package dao;

import java.util.List;

import utils.MiException;

import entities.Usuario;


public interface UsuarioDAO {

	public List<Usuario> cargarUsuarios() throws MiException;
	public boolean crearTablaUsuario() throws MiException;
	
	public boolean updateUsuario(Usuario usuario) throws MiException;
	public boolean insertarUsuario(int id,String name,String mail,String user,String pass,int logueado,String jerarquia) throws MiException;
	
	public boolean insertarUsuario(Usuario usuario) throws MiException;
	
	public boolean eliminarUsuario(Usuario user) throws MiException ;
	
	public Usuario buscarUsuarioPorApodo(String userToFind) throws MiException;
	
	public int buscarUltimoUsuarioId() throws MiException;
	
}
