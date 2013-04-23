package dao;

import java.util.Collection;

import entidades.Usuario;

public interface UsuarioDAO {

	public void insertarUsuario(Usuario u);
	public void deleteUsuarioByUsername(String username);
	public void updateUsuario(Usuario u);
	public Usuario getUsuarioByUsername(String username);
	public Collection getAllUsuarios();
	
}
