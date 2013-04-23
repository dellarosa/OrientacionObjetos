package dbImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import basics.DBManager;

import dao.UsuarioDAO;
import entidades.Usuario;

public class UsuarioDAODBImpl implements UsuarioDAO{

	@Override
	public void deleteUsuarioByUsername(String username) {
		String sql = "DELETE FROM usuarios WHERE user = '" + username + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
	}

	@Override
	public Collection getAllUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getUsuarioByUsername(String username) {
		Usuario retorna = null;
		String sql = "SELECT * FROM usuarios WHERE user = '" + username + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next()) {
				String u = rs.getString("user");
				String e = rs.getString("email");
				String p = rs.getString("pass");
				retorna = new Usuario();
				retorna.setEmail(e);
				retorna.setPassword(p);
				retorna.setUsername(u);
			}
			
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
		return retorna;
	}

	@Override
	public void insertarUsuario(Usuario u) {
		String sql = "INSERT INTO usuarios (user, email, pass) VALUES ('" + u.getUsername() + "', '" + u.getEmail() + "', '" + u.getPassword() + "')";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void updateUsuario(Usuario u) {
		String sql = "UPDATE usuarios set email = '" + u.getEmail() + "', pass = '" + u.getPassword() + "' WHERE user = '" + u.getUsername() + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
		
	}

	
	
}
