package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;
import entities.SQLClass;
import entities.SQLSelects;
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

	public boolean crearUsuario(Usuario user) throws SQLException,MiException {
		
		boolean created=false;
		String query;
		Connection conn=null;
		Statement stmt = null;
		try
		{			
			conn=SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		
			try
			{
				query="INSERT INTO Usuario(usuario_ID,name,mail,user,pass,logueado,jerarquia) VALUES ("+user.getId()+","+user.getName()+
						","+user.getEmail()+","+user.getUsername()+","+user.getPassword()+","+"N,"+user.getJerarquia()+")";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[main] "+query);
				created=true;
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				created=false;
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
		return created;
	}
	
	
	
	//################################## BUSCAR ULTIMO ... ###################################################################
	public int buscarUltimoUsuario(Usuario[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	public int buscarUltimoCliente(Cliente[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x+1;
	}
	public int buscarUltimaReparacion(Reparacion[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	
	
	public int buscarUltimaAutoparteId(Autoparte[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x+1;
	}
	/*public int buscarUltimoIdFiltro(Autoparte[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x+1;
	}
	public int buscarUltimoIdAceite(Autoparte[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	public int buscarUltimoIdLampara(Autoparte[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}*/
	public int buscarUltimaAutoparte(Autoparte[][] obj)
	{
		int x=0;
		int y=0;
		int c=0;
		while(x<obj[x].length)
		{	
			y=0;
			while(obj[x][y]!=null)
			{
				y++;
				c++;
			}
			x++;
		}
		return c+1;
	}
	
	
}
