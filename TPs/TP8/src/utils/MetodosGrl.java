package utils;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import entities.Usuario;

public class MetodosGrl {

	
	
	public Usuario obtenerUsuarioActual()
	{
		Usuario usuario = new Usuario();
		return usuario;
	}
	
	
	public Usuario loginUser(String user, String pass) throws MiException, SQLException
	{		
		Usuario usuario=new Usuario();
		String query;
		boolean login=false;
		Connection conn=null;
		Statement stmt=null;
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Usuarios WHERE user='"+user+"'AND pass='"+pass+"'";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {										
					login=true;//LOGUEADO
					usuario.setName(rs.getString("name"));
					usuario.setEmail(rs.getString("mail"));
					usuario.setPassword(rs.getString("pass"));
					usuario.setUsername(rs.getString("user"));
					usuario.setId(rs.getInt("ususario_ID"));
					usuario.setJerarquia(rs.getString("jerarquia"));
					
				}else
				{	
					//System.out.print("\n[loginUser] NO HAY COINCIDENCIAS");	//DEBUG
					usuario=null;
					login=false;
				}			
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				login=false;
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return usuario;
	}
	
	public Usuario buscarUsuarioPorApodo(String nombre, List<Usuario> usuarios) {
		// TODO Auto-generated method stub
		for(Usuario user:usuarios)
		{
			if(user.getUsername().equals(nombre))
			{
				return user;
			}
		}		
		return null;
	}
	
	public List<Usuario> eliminarUsuarioDeLista(List<Usuario> usuarios,Usuario usuarioModif) {
		
		int i=0;
		for(Usuario user:usuarios)
		{
			if(usuarioModif.getName().equals(user.getName()))
			{
				break;
			}
			i++;
		}
		usuarios.remove(i);
		return usuarios;
	}
	
}
