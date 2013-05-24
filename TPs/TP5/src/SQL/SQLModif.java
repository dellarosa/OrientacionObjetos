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

public class SQLModif {
	
	
	//###################################### MODIFICAR USUARIO #############################################################################
	
		public boolean updateUsuario(Usuario usuario) throws MiException, SQLException
		{
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
					query="UPDATE Usuario SET usuario_ID="+usuario.getId()+",name='"+usuario.getName()+"',mail='"+usuario.getEmail()+"',user='"+usuario.getUsername()+"',pass='"+usuario.getPassword()+"' WHERE usuario_ID='"+usuario.getId()+"'";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[updateUsuario] "+query);						//DEBUG
					
				}catch(SQLException e)
				{
					System.out.print("\n[updateUsuario] Exception SQL: "+e);					//DEBUG
					conn.rollback();	
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[updateUsuario]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[updateUsuario]ERROR AL CREAR TABLAS",e);
			}
			finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD			
			}	
			return true;
		}
	//###################################### MODIFICAR FILTRO #############################################################################
	
	public boolean updateFiltro(Filtro filtro) throws MiException, SQLException
	{
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
				query="UPDATE Filtro SET filtro_ID='"+filtro.getFiltro_ID()+"',autoparte_ID='"+filtro.getAutoparteID()+"',tamaño='"+filtro.getTamaño()+"',material='"+filtro.getMaterial()+"' WHERE filtro_ID='"+filtro.getFiltro_ID()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateFiltro] "+query);						//DEBUG
				
			}catch(SQLException e)
			{
				System.out.print("\n[updateFiltro] Exception SQL: "+e);					//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateFiltro]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateFiltro]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	//###################################### MODIFICAR ACEITE #############################################################################
	
	public boolean updateAceite(Aceite aceite) throws MiException, SQLException
	{
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
				query="UPDATE Aceite SET aceite_ID='"+aceite.getAceite_ID()+"',autoparte_ID='"+aceite.getAutoparteID()+"',litros='"+aceite.getCantidadlitros()+"',tipo='"+aceite.getTipoAceite()+"' WHERE aceite_ID="+aceite.getAceite_ID();
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateAceite] "+query);					//DEBUG
			}catch(SQLException e)
			{
				System.out.print("\n[updateAceite] Exception SQL: "+e);					//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateAceite]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateAceite]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	//###################################### MODIFICAR LAMPARA #############################################################################
	public boolean updateLampara(Lampara lampara) throws MiException, SQLException
	{
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
				query="UPDATE Lampara SET lampara_ID='"+lampara.getLampara_ID()+"',autoparte_ID='"+lampara.getAutoparteID()+"',color='"+lampara.getColor()+"',tamaño='"+lampara.getTamaño()+"' WHERE lampara_ID='"+lampara.getLampara_ID()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateLampara] "+query);		//DEBUG
			}catch(SQLException e)
			{
				System.out.print("\n[updateLampara] Exception SQL: "+e);					//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateLampara]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateLampara]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	//###################################### MODIFICAR AUTOPARTE #############################################################################
	public boolean updateAutoparte(Autoparte autoparte) throws MiException, SQLException
	{
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
				query="UPDATE Autoparte  SET autoparte_ID="+autoparte.getId()+",tipoAutoparte='"+autoparte.getTipoAutoparte()+"',marca='"+autoparte.getMarca()+"',modelo='"+autoparte.getModelo()+"',costo='"+autoparte.getCosto()+"',cantidadDisponible="+autoparte.getCantDisponible()+" WHERE autoparte_ID="+autoparte.getId();
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateAutoparte] "+query);					//DEBUG
				
			}catch(SQLException e)
			{
				System.out.print("\n[updateAutoparte] Exception SQL: "+e);					//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateAutoparte]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateAutoparte]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}

	//###################################### MODIFICAR CLIENTE #############################################################################
	public boolean updateCliente(Cliente cliente) throws MiException, SQLException {

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
					query="UPDATE Cliente SET cliente_ID="+cliente.getId()+",nombre='"+cliente.getNombre()+"',auto='"+cliente.getAuto()+"',mail='"+cliente.getMail()+"' WHERE cliente_ID="+cliente.getId();
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[updateCliente] "+query);					//DEBUG
					
				}catch(SQLException e)
				{
					System.out.print("\n[updateCliente] Exception SQL: "+e);					//DEBUG
					conn.rollback();	
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[updateCliente]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[updateCliente]ERROR AL CREAR TABLAS",e);
			}
			finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD			
			}	
			return true;		
	}
}
