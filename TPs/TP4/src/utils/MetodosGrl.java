package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import SQL.SQLClass;
import SQL.SQLSelects;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
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

	
	public Usuario loginUser(String user, String pass) throws MiException, SQLException
	{		
		Usuario usuario=new Usuario();
		String query;
		boolean login=false;
		Connection conn=null;
		Statement stmt=null;
		try
		{			
			conn = SQLClass.getConnection();
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
	public Cliente buscarClientePorApodo(String nombre, List<Cliente> clientesG) {
		
		for(Cliente cliente:clientesG)
		{
			if(cliente.getNombre().equals(nombre))
			{
				return cliente;
			}
		}		
		return null;
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
	public List<Cliente> eliminarClienteDeLista(List<Cliente> clientesG,Cliente cliente) {
		// TODO Auto-generated method stub
		int i=0;
		for(Cliente client: clientesG)
		{
			if(client.getNombre().equals(cliente.getNombre()))
			{
				break;
			}
			i++;
		}
		clientesG.remove(i);
		return clientesG;
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
