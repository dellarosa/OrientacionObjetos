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

	public Usuario buscarUsuarioPorApodo(String userToFind) throws SQLException, MiException
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
				query="SELECT * FROM Usuarios WHERE user='"+userToFind+"'";
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
	
	public boolean eliminarUsuario(Usuario user) throws SQLException,MiException {
		
		boolean deleted=false;
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
				query="DELETE FROM Usuario WHERE user='"+user.getUsername()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[main] "+query);
				deleted=true;
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				deleted=false;
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
		return deleted;
	}
	//####################################################################################################
	public void cargaClientes(Cliente[] clientes) throws SQLException,MiException {
		
		String query;
		Connection conn=null;
		Statement stmt=null;
		int x=0;
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Cliente";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					System.out.print("\n[cargaClientes] HAY CLIENTES ");		//DEBUG
				}else
				{	
					System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
				}
				do {										
					
					clientes[x].setNombre(rs.getString("nombre"));
					clientes[x].setId(rs.getInt("usuario_ID"));
					clientes[x].setAuto(rs.getString("auto"));
					clientes[x].setMail(rs.getString("mail"));
					
					
				}while(rs.next());
							
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}		
	}
	public void cargaAutopartes(Autoparte[][] autopartes) {
		String query;
		Connection conn=null;
		Statement stmt=null;
		int x=0;
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Autoparte";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					System.out.print("\n[cargaClientes] HAY CLIENTES ");		//DEBUG
				}else
				{	
					System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
				}
				do {										
					
					Autoparte[x].setNombre(rs.getString("nombre"));
					Autoparte[x].setId(rs.getInt("id"));
					Autoparte[x].setAuto(rs.getString("auto"));
					Autoparte[x].setMail(rs.getString("mail"));
					
					
				}while(rs.next());
							
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		
	}
	public void cargarUsuarios(Usuario[] usuarios) throws SQLException,MiException {
		String query;
		Connection conn=null;
		Statement stmt=null;
		int x=0;
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Usuario";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					System.out.print("\n[cargaClientes] HAY CLIENTES ");		//DEBUG
				}else
				{	
					System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
				}
				do {										
					
					usuarios[x].setName(rs.getString("nombre"));
					usuarios[x].setId(rs.getInt("usuario_ID"));
					usuarios[x].setEmail(rs.getString("mail"));
					usuarios[x].setJerarquia(rs.getString("jerarquia"));
					usuarios[x].setPassword(rs.getString("pass"));
					usuarios[x].setUsername(rs.getString("user"));
					
					
				}while(rs.next());
							
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}	
		
	}
	public void cargaReparaciones(Reparacion[] reparaciones) {
		String query;
		Connection conn=null;
		Statement stmt=null;
		int x=0;
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Reparacion";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					System.out.print("\n[cargaClientes] HAY CLIENTES ");		//DEBUG
				}else
				{	
					System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
				}
				do {										
					
					reparaciones[x].setId(rs.getInt("reparacion_ID"));					
					reparaciones[x].setCosto(rs.getDouble("costo"));
					reparaciones[x].setEntregado(rs.getInt("entregado"));
					
					//######################CLIENTE###############					
					query="SELECT * FROM Cliente WHERE cliente_ID='"+rs.getInt("cliente_ID")+"'";
					ResultSet rsclient=stmt.executeQuery(query);			
					conn.commit();
				
						Cliente client=new Cliente();
						while(rsclient.next())
						{
							client.setId(rs.getInt("cliente_ID"));
							client.setMail(rsclient.getString("mail"));
							client.setAuto(rsclient.getString("auto"));
							client.setNombre(rsclient.getString("nombre"));
						}
						reparaciones[x].setCliente(client);
						
					
					//######################AUTOPARTE###############					
					query="SELECT * FROM 'Autoparte'";
					ResultSet rsAutopart=stmt.executeQuery(query);			
					conn.commit();
						
						Autoparte[] Autoparte=new Autoparte[];
						Filtro filtro[]=new Filtro[];
						Aceite[] aceite=new Aceite[];
						Lampara[] lampara=new Lampara[];
						while(rsclient.next())
						{
							client.setId(rs.getInt("cliente_ID"));
							client.setMail(rsuser.getString("mail"));
							client.setAuto(rsuser.getString("auto"));
							client.setNombre(rsuser.getString("nombre"));
						}
						reparaciones[x].setCliente(client);
				}while(rs.next());
							
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}	
		
	}
	
	//#####################################################################################################
	public Cliente buscarClientePorId(int id)
	{
		
	}
	//#####################################################################################################
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
		return x;
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
}
