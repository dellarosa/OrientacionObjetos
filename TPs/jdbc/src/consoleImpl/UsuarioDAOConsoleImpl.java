package consoleImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import basics.DBManager;

import dao.UsuarioDAO;
import entidades.Usuario;

public class UsuarioDAOConsoleImpl implements UsuarioDAO{

	@Override
	public void deleteUsuarioByUsername(String username) {
		String sql = "DELETE FROM usuarios WHERE user = '" + username + "'";
		System.out.println("Deberias tirar la siguiente consulta a la DB:" + sql);
	}

	@Override
	public Collection getAllUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getUsuarioByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarUsuario(Usuario u) {
		String sql = "INSERT INTO usuarios (user, email, pass) VALUES ('" + u.getUsername() + "', '" + u.getEmail() + "', '" + u.getPassword() + "')";
		System.out.println("Deberias tirar la siguiente consulta a la DB:" + sql);
		
	}

	@Override
	public void updateUsuario(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	
	
}
