package bo;

import java.util.Collection;

import dao.UsuarioDAO;
import entidades.Usuario;

public class UsuarioBO {

	private UsuarioDAO dao;
	
	public void insertarUsuario(Usuario u) {
		dao.insertarUsuario(u);
	}
	public void deleteUsuarioById(String user) {
		dao.deleteUsuarioByUsername(user);
	}
	public void updateUsuario(Usuario u) {
		updateUsuario(u);
	}
	public Usuario getUsuarioByUsername(String user) {
		return getUsuarioByUsername(user);
	}
	public Collection getAllUsuarios() {
		return getAllUsuarios();
	}
	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}
	
	
}
