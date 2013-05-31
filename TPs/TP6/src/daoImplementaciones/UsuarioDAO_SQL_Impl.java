package daoImplementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.DBManager;
import utils.MiException;
import dao.UsuarioDAO;
import entities.Usuario;

public class UsuarioDAO_SQL_Impl implements UsuarioDAO{

	//############################## CREATE USUARIO #####################################################################################
	public boolean crearTablaUsuario() throws MiException
	{
		String query;
		Connection conn=null;
		Statement stmt = null;
		try
		{
			
			conn=DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		
			try
			{					
				query="CREATE TABLE Usuario(usuario_ID int NOT NULL PRIMARY KEY,name varchar(40) NOT NULL,mail varchar(40) NOT NULL,user varchar(12) NOT NULL,pass varchar(8) NOT NULL,logueado int NOT NULL,jerarquia varchar(20) NOT NULL)";									
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[crearTablaUsuario] "+query);				//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaUsuario] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				//throw new MiException("[crearTablaUsuario] SQL Exception: "+e);
			}
		
			
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaUsuario]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaUsuario]ERROR AL CREAR TABLAS",e);
		}	
		return true;
	}
	
	//####################################################### CARGAR USUARIOS ################################
	public List<Usuario> cargarUsuarios() throws MiException {
		String query;
		Connection conn=null;
		Statement stmt=null;			
		List<Usuario> usuarios=new ArrayList<Usuario>();
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Usuario";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					
					System.out.print("\n[cargarUsuarios] HAY USUARIOS: ");		//DEBUG
					do {		
						Usuario usuario=new Usuario();
						
						usuario.setId(rs.getInt("usuario_ID"));
						
						usuario.setName(rs.getString("name"));							
						usuario.setEmail(rs.getString("mail"));
						usuario.setJerarquia(rs.getString("jerarquia"));
						usuario.setPassword(rs.getString("pass"));
						usuario.setUsername(rs.getString("user"));
						usuario.setLogueado(rs.getInt("logueado"));
						usuarios.add(usuario);
					}while(rs.next());
					
				}else
				{	
					System.out.print("\n[cargarUsuarios]NO HAY USUARIOS CARGADOS : ");		//DEBUG
				}				
				
			}catch(SQLException e)
			{
				//System.out.print("\n[cargarUsuarios] SQL Exception al cargar: "+e);		//DEBUG
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				throw new MiException("[cargarUsuarios] SQL Exception: "+e);
			}		
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();
		}catch(SQLException e)
		{
			throw new MiException("[cargarUsuarios] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargarUsuarios] EXCEPTION: "+e);
		}
		
		return usuarios;
	}
	

	
	//############################## DELETE USUARIO #####################################################################################
	public boolean eliminarUsuario(Usuario user) throws MiException {
		
		boolean deleted=false;
		String query;
		Connection conn=null;
		Statement stmt = null;
		try
		{			
			conn=DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		
			try
			{
				query="DELETE FROM Usuario WHERE user='"+user.getUsername()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[eliminarUsuario] "+query);				//DEBUG
				deleted=true;
			
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			
			}catch(SQLException e)
			{
				System.out.print("\n[eliminarUsuario] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			
				//throw new MiException("[eliminarUsuario] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[eliminarUsuario] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[eliminarUsuario] EXCEPTION AL CONECTAR: "+e);
		}
			
		return deleted;
	}
	
	//############################## INSERTAR USUARIO #####################################################################################
		public boolean insertarUsuario(Usuario usuario) throws MiException
		{
			String query;
			Connection conn=null;
			Statement stmt = null;
			try
			{
				
				conn=DBManager.getConnection();
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
			
				try
				{
					query="INSERT INTO Usuario(usuario_ID,name,mail,user,pass,logueado,jerarquia) VALUES ("+usuario.getId()+",'"+usuario.getName()+"','"+usuario.getEmail()+"','"+usuario.getUsername()+"','"+usuario.getPassword()+"','"+usuario.getLogueado()+"','"+usuario.getJerarquia()+"')";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[insertarUsuario] "+query);				//DEBUG
					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD			
				
				}catch(SQLException e)
				{
					System.out.print("\n[insertarUsuario] SQL Exception AL INSERTAR: "+e);					//DEBUG
					conn.rollback();
					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD			
					
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[insertarUsuario]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[insertarUsuario]ERROR AL CREAR TABLAS",e);
			}
			
				
			return true;
		}
		
		public boolean insertarUsuario(int id,String name,String mail,String user,String pass,int logueado,String jerarquia) throws MiException
		{
			String query;
			Connection conn=null;
			Statement stmt = null;
			try
			{
				
				conn=DBManager.getConnection();
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
			
				try
				{
					query="INSERT INTO Usuario(usuario_ID,name,mail,user,pass,logueado,jerarquia) VALUES ("+id+",'"+name+"','"+mail+"','"+user+"','"+pass+"',"+logueado+",'"+jerarquia+"')";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[insertarUsuario] "+query);						//DEBUG
					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();	
				}catch(SQLException e)
				{
					System.out.print("\n[insertarUsuario] SQL Exception AL INSERTAR: "+e);					//DEBUG
					conn.rollback();
					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();	
					
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[insertarUsuario]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[insertarUsuario]ERROR AL CREAR TABLAS",e);
			}
				
			return true;
		}
		
	//###################################### MODIFICAR USUARIO #############################################################################
	
		public boolean updateUsuario(Usuario usuario) throws MiException
		{
			String query;
			Connection conn=null;
			Statement stmt = null;
			try
			{
				
				conn=DBManager.getConnection();
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
			
				try
				{					
					query="UPDATE Usuario SET usuario_ID="+usuario.getId()+",name='"+usuario.getName()+"',mail='"+usuario.getEmail()+"',user='"+usuario.getUsername()+"',pass='"+usuario.getPassword()+"' WHERE usuario_ID='"+usuario.getId()+"'";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[updateUsuario] "+query);						//DEBUG
					
					stmt.execute("SHUTDOWN");					
					conn.close();											
					
				}catch(SQLException e)
				{
					System.out.print("\n[updateUsuario] Exception SQL: "+e);					//DEBUG
					conn.rollback();	
					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD			
					
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[updateUsuario]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[updateUsuario]ERROR AL CREAR TABLAS",e);
			}			
			
			return true;
		}

	//################################### BUSCAR POR APODO#############################################################
	public Usuario buscarUsuarioPorApodo(String userToFind) throws MiException
	{
		Usuario usuario=new Usuario();
		String query;					
		Connection conn=null;
		Statement stmt=null;
		
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Usuarios WHERE user='"+userToFind+"'";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {										
					
					usuario.setName(rs.getString("name"));
					usuario.setEmail(rs.getString("mail"));
					usuario.setPassword(rs.getString("pass"));
					usuario.setUsername(rs.getString("user"));
					usuario.setId(rs.getInt("ususario_ID"));
					usuario.setJerarquia(rs.getString("jerarquia"));
					
					
				}else
				{	
					//System.out.print("\n[buscarUsuarioPorApodo] NO HAY COINCIDENCIAS");	//DEBUG
					usuario=null;							
				}		
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				
			}catch(SQLException e)
			{
				//System.out.print("\n[buscarUsuarioPorApodo] SQL Exception: "+e);		//DEBUG						
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				//throw new MiException("[buscarUsuarioPorApodo] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUsuarioPorApodo] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUsuarioPorApodo] EXCEPTION AL CONECTAR: "+e);
		}
		
		return usuario;
	}
	
	//##################################### BUSCAR ULTIMO USUARIO ID ##########################################
		public int buscarUltimoUsuarioId() throws MiException
		{
			int inLastID = 0;
			String query;						
			Connection conn=null;
			Statement stmt=null;
			
			
			try
			{			
				conn = DBManager.getConnection();
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				try
				{				
					//query="SELECT MAX('usuario_ID') FROM Usuario";
					query="SELECT usuario_ID FROM Usuario ORDER BY usuario_ID DESC LIMIT 1";
					ResultSet rs=stmt.executeQuery(query);			
					conn.commit();
					if(rs.next()) {									
						inLastID=rs.getInt("usuario_ID");					
						inLastID++;
						//System.out.print("\n[buscarUltimUsuarioId] LAST ID: "+inLastID);	//DEBUG
					}else
					{	
						//System.out.print("\n[buscarUltimUsuarioId] NO HAY COINCIDENCIAS");	//DEBUG
						//inLastID=-1;
						inLastID=1;
					}	
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();	
				}catch(SQLException e)
				{
					System.out.print("\n[buscarUltimUsuarioId] SQL Exception: "+e);		//DEBUG				
					conn.rollback();
					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();
					//throw new MiException("[buscarUltimUsuarioId] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[buscarUltimUsuarioId] SQL EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[buscarUltimUsuarioId] EXCEPTION AL CONECTAR: "+e);
			}
			
			return inLastID;
		}
}
