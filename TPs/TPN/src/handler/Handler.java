package handler;

import java.util.List;

import javax.swing.JPanel;

import utils.MiException;

import entities.Usuario;

import bo.UsuarioBO;
import main.MiFrame;

public class Handler {

	private MiFrame frame;
	private UsuarioBO usuarioBO;
	
	public Handler() {
		this.usuarioBO = new UsuarioBO();
	}
	
	public void setFrame(MiFrame frame) {
		this.frame = frame;
	}
	
	public void backToPrincipal() {
		frame.switchPanel(new JPanel());
	}
	
	public void eliminarUsuario(Usuario usuario) throws MiException {
		usuarioBO.eliminarUsuario(usuario);		
	}

	public List<Usuario> cargarUsuarios() throws MiException {
		return usuarioBO.cargarUsuarios();
	}

	public Usuario buscarUsuarioPorApodo(String userName) throws MiException {
		return usuarioBO.buscarUsuarioPorApodo(userName);
	}

	public int buscarUltimoUsuarioId() throws MiException{
		
		return usuarioBO.buscarUltimoUsuarioId();
	}

	public boolean updateUsuario(Usuario usuario) throws MiException {
		
		return usuarioBO.updateUsuario(usuario);
	}

	public boolean insertarUsuario(Usuario usuario) throws MiException {
		
		return usuarioBO.insertarUsuario(usuario);
	}
}
