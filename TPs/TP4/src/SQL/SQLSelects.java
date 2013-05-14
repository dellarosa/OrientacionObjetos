package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Aceite;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Usuario;

import utils.MiException;

public class SQLSelects {

	//################################### BUSCAR POR ID AUTOPARTE ##################################################################
	public Filtro buscarFiltroPorIdAutoParte(int id) throws SQLException,MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		Filtro filtro=new Filtro();
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Filtro WHERE filtro_ID="+id;
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					//System.out.print("\n[buscarFiltroPorId] HAY Filtro ");		//DEBUG
					do {										
						
						filtro.setId(rs.getInt("filtro_ID"));					
						filtro.setAutoparteID(rs.getInt("autoparte_ID"));
						filtro.setMaterial(rs.getString("material"));
						filtro.setTamaño(rs.getString("tamaño"));
						
					}while(rs.next());
				}else
				{	
					filtro=null;
					System.out.print("\n[buscarFiltroPorId]NO HAY Filtros CARGADOS : ");		//DEBUG
				}
				
							
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
		return filtro;
	}
	
	public Aceite buscarAceitePorIdAutoParte(int id) throws SQLException,MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		Aceite aceite=new Aceite();
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Aceite WHERE aceite_ID="+id;
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					//System.out.print("\n[buscarFiltroPorId] HAY aceite ");		//DEBUG
					do {										
						
						aceite.setId(rs.getInt("aceite_ID"));					
						aceite.setAutoparteID(rs.getInt("autoparte_ID"));
						aceite.setCantidadlitros(rs.getInt("litros"));
						aceite.setTipoAceite(rs.getString("tipo"));
						
					}while(rs.next());

				}else
				{	
					aceite=null;
					System.out.print("\n[buscarFiltroPorId]NO HAY aceite CARGADOS : ");		//DEBUG
				}
											
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
		return aceite;
	}
	public Lampara buscarLamparaPorIdAutoParte(int id) throws SQLException,MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		Lampara lampara=new Lampara();
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Lampara WHERE lampara_ID="+id;
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					//System.out.print("\n[buscarFiltroPorId] HAY lampara ");		//DEBUG
					do {										
						
						lampara.setId(rs.getInt("lampara_ID"));					
						lampara.setColor(rs.getString("color"));
						lampara.setTamaño(rs.getString("tamaño"));
						lampara.setAutoparteID(rs.getInt("autoparte_ID"));
						
					}while(rs.next());
				}else
				{	
					lampara=null;
					System.out.print("\n[buscarFiltroPorId]NO HAY lampara CARGADOS : ");		//DEBUG
				}
				
							
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
		return lampara;
	}
			
	//################################### BUSCAR POR APODO#############################################################
	public Usuario buscarUsuarioPorApodo(String userToFind) throws SQLException, MiException
	{
		Usuario usuario=new Usuario();
		String query;					
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
				}			
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG						
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
	
	public Cliente buscarClientePorApodo(String userToFind) throws SQLException, MiException
	{
		Cliente cliente=new Cliente();
		String query;						
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
					
					cliente.setAuto(rs.getString("auto"));
					cliente.setMail(rs.getString("mail"));
					cliente.setNombre(rs.getString("nombre"));							
					cliente.setId(rs.getInt("ususario_ID"));							
				}else
				{	
					//System.out.print("\n[loginUser] NO HAY COINCIDENCIAS");	//DEBUG
					cliente=null;
					
				}			
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return cliente;
	}
	
	//##################################### BUSCAR ULTIMO AUTOPARTE ID ##########################################
	public int buscarUltimaAutoparteId() throws SQLException, MiException
	{
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
		
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{	
				query="SELECT autoparte_ID FROM Autoparte ORDER BY autoparte_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("autoparte_ID");
					inLastID++;
				//	System.out.print("\n[buscarUltimaAutoparteId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					System.out.print("\n[buscarUltimaAutoparteId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=-1;
					
				}			
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimaAutoparteId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimaAutoparteId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimaAutoparteId] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return inLastID;
	}
	
	//##################################### BUSCAR ULTIMO FILTRO ID ##########################################
	public int buscarUltimoFiltroId() throws SQLException, MiException
	{
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
		
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{	
				query="SELECT filtro_ID FROM Filtro ORDER BY filtro_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("filtro_ID");
					inLastID++;
				//	System.out.print("\n[buscarUltimoFiltroId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					System.out.print("\n[buscarUltimoFiltroId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=-1;
					
				}			
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimoFiltroId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimoFiltroId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimoFiltroId] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return inLastID;
	}
	//##################################### BUSCAR ULTIMO ACEITE ID ##########################################
		public int buscarUltimoAceiteId() throws SQLException, MiException
		{
			int inLastID = 0;
			String query;						
			Connection conn=null;
			Statement stmt=null;
			
			
			try
			{			
				conn = SQLClass.getConnection();
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				try
				{	
					query="SELECT aceite_ID FROM Aceite ORDER BY aceite_ID DESC LIMIT 1";
					ResultSet rs=stmt.executeQuery(query);			
					conn.commit();
					if(rs.next()) {									
						inLastID=rs.getInt("aceite_ID");
						inLastID++;
				//		System.out.print("\n[buscarUltimoAceiteId] LAST ID: "+inLastID);	//DEBUG
					}else
					{	
						System.out.print("\n[buscarUltimoAceiteId] NO HAY COINCIDENCIAS");	//DEBUG
						inLastID=-1;
						
					}			
				}catch(SQLException e)
				{
					System.out.print("\n[buscarUltimoAceiteId] SQL Exception: "+e);		//DEBUG				
					conn.rollback();
					
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[buscarUltimoAceiteId] SQL EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[buscarUltimoAceiteId] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			}
			return inLastID;
		}
	//##################################### BUSCAR ULTIMO Lampara ID ##########################################
	public int buscarUltimoLamparaId() throws SQLException, MiException
	{
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
		
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{	
				query="SELECT lampara_ID FROM Lampara ORDER BY lampara_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("lampara_ID");
					inLastID++;
				//	System.out.print("\n[buscarUltimoLamparaId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					System.out.print("\n[buscarUltimoLamparaId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=-1;
					
				}			
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimoLamparaId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimoLamparaId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimoLamparaId] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return inLastID;
	}
	
	//##################################### BUSCAR ULTIMO REPARACION ID ##########################################
	public int buscarUltimaReparacionId() throws SQLException, MiException
	{
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
		
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{	
				query="SELECT reparacion_ID FROM Reparacion ORDER BY reparacion_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("reparacion_ID");
					inLastID++;
				//	System.out.print("\n[buscarUltimaReparacionId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					System.out.print("\n[buscarUltimaReparacionId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=-1;
					
				}			
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimaReparacionId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimaReparacionId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimaReparacionId] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return inLastID;
	}
	//##################################### BUSCAR ULTIMO REPARACION AUTOPARTE ID ##########################################
	public int buscarUltimaReparacionAutoparteId() throws SQLException, MiException
	{
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
		
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{	
				query="SELECT reparacionAutoparte_ID FROM ReparacionAutoparte ORDER BY reparacionAutoparte_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("reparacionAutoparte_ID");
					inLastID++;
					//System.out.print("\n[buscarUltimaReparacionAutoparteId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					System.out.print("\n[buscarUltimaReparacionAutoparteId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=-1;
					
				}			
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimaReparacionAutoparteId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimaReparacionAutoparteId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimaReparacionAutoparteId] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return inLastID;
	}
	//##################################### BUSCAR ULTIMO USUARIO ID ##########################################
	public int buscarUltimoUsuarioId() throws SQLException, MiException
	{
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
		
		
		try
		{			
			conn = SQLClass.getConnection();
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
					System.out.print("\n[buscarUltimUsuarioId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=-1;
					
				}			
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimUsuarioId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimUsuarioId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimUsuarioId] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return inLastID;
	}
	//##################################### BUSCAR ULTIMO CLIENTE ID ##########################################
	public int buscarUltimoClienteId(int id) throws SQLException,MiException {
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
				
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				//query="SELECT MAX('usuario_ID') FROM Usuario";
				query="SELECT cliente_ID FROM Cliente ORDER BY cliente_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("cliente_ID");					
					inLastID++;
					//System.out.print("\n[buscarUltimoClienteId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					System.out.print("\n[buscarUltimoClienteId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=-1;
					
				}			
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimoClienteId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimoClienteId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimoClienteId] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return inLastID;
		
	}
		
}
