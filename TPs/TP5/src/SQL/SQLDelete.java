package SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Usuario;

import utils.MiException;

public class SQLDelete {

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
				System.out.print("\n[eliminarUsuario] "+query);				//DEBUG
				deleted=true;
			}catch(SQLException e)
			{
				System.out.print("\n[eliminarUsuario] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[eliminarUsuario] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[eliminarUsuario] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return deleted;
	}
	//############################## DELETE CLIENTE ###################################################
	public boolean eliminarCliente(Cliente cliente) throws SQLException,MiException {
		
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
				query="DELETE FROM Cliente WHERE nombre='"+cliente.getNombre()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[eliminarCliente] "+query);			//DEBUG
				deleted=true;
			}catch(SQLException e)
			{
				System.out.print("\n[eliminarCliente] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[eliminarCliente] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[eliminarCliente] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return deleted;
	}
	
	//############################## DELETE AUTOPARTE ###################################################
		public boolean eliminarAutoparte(Autoparte autoparte) throws SQLException,MiException {
			
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
					query="DELETE FROM Autoparte WHERE autoparte_ID='"+autoparte.getId()+"'";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[eliminarAutoparte] "+query);			//DEBUG
					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();	
					if(autoparte instanceof Filtro)
					{
						eliminarFiltro((Filtro)autoparte);
					}else if(autoparte instanceof Aceite)
					{
						eliminarAceite((Aceite)autoparte);
					}
					else if(autoparte instanceof Lampara)
					{
						eliminarLampara((Lampara)autoparte);
					}else
					{}					
					deleted=true;
				}catch(SQLException e)
				{
					System.out.print("\n[eliminarAutoparte] SQL Exception: "+e);		//DEBUG
					deleted=false;
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[eliminarAutoparte] SQL EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[eliminarAutoparte] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
												
			}
			return deleted;
		}
		
		//############################## DELETE FILTRO ###################################################
		public boolean eliminarFiltro(Filtro filtro) throws SQLException,MiException {
			
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
					query="DELETE FROM Filtro WHERE filtro_ID="+filtro.getFiltro_ID();
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[eliminarFiltro] "+query);			//DEBUG
					deleted=true;
				}catch(SQLException e)
				{
					System.out.print("\n[eliminarFiltro] SQL Exception: "+e);		//DEBUG
					deleted=false;
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[eliminarFiltro] EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[eliminarFiltro] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			}
			return deleted;
		}
		//############################## DELETE ACEITE ###################################################
		public boolean eliminarAceite(Aceite aceite) throws SQLException,MiException {
			
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
					query="DELETE FROM Aceite WHERE aceite_ID="+aceite.getAceite_ID();
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[eliminarAceite] "+query);			//DEBUG
					deleted=true;
				}catch(SQLException e)
				{
					System.out.print("\n[eliminarFiltro] SQL Exception: "+e);		//DEBUG
					deleted=false;
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[eliminarFiltro] SQL EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[eliminarFiltro] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			}
			return deleted;
		}
		
		//############################## DELETE ACEITE ###################################################
		public boolean eliminarLampara(Lampara lampara) throws SQLException,MiException {
			
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
					query="DELETE FROM Lampara WHERE lampara_ID="+lampara.getLampara_ID();
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[eliminarLampara] "+query);			//DEBUG
					deleted=true;
				}catch(SQLException e)
				{
					System.out.print("\n[eliminarLampara] SQL Exception: "+e);		//DEBUG
					deleted=false;
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[eliminarLampara] SQL EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[eliminarLampara] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			}
			return deleted;
		}
}
