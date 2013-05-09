package entities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utils.MiException;

public class SQLInserts {

	//###################################### INSERTAR AUTOPARTE #############################################################################
	public boolean insertarCliente(int clienteID,String nombre,String auto,String mail) throws MiException, SQLException
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
				query="INSERT INTO Cliente (cliente_ID,nombre,auto,mail) VALUES ('"+clienteID+"','"+nombre+"','"+auto+"','"+mail+")";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarCliente] "+query);
				
			}catch(SQLException e)
			{
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarCliente]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarCliente]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	public boolean insertarCliente(Cliente cliente) throws MiException, SQLException
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
				query="INSERT INTO Cliente (cliente_ID,nombre,auto,mail) VALUES ('"+cliente.getId()+"','"+cliente.getNombre()+"','"+cliente.getAuto()+"','"+cliente.getMail()+")";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarCliente] "+query);
				
			}catch(SQLException e)
			{
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarCliente]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarCliente]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	//###################################### INSERTAR AUTOPARTE #############################################################################
			public boolean insertarAutoparte(int autoparte_ID,String tipo,String marca,String modelo,double costo,int cantDisponible) throws MiException, SQLException
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
						query="INSERT INTO Autoparte (autoparte_ID,tipoAutoparte,marca,modelo,costo,cantidadDisponible) VALUES ('"+autoparte_ID+"','"+tipo+"','"+marca+"','"+modelo+"','"+costo+"','"+cantDisponible+")";
						stmt.executeUpdate(query);
						conn.commit();
						System.out.print("\n[insertarAutoparte] "+query);
						
					}catch(SQLException e)
					{
						conn.rollback();	
					}
				
				}catch(SQLException e)
				{
					throw new MiException("[insertarAutoparte]SQL Connection EXCEPTION "+e.getMessage());
				}catch(Exception e)
				{
				
					throw new MiException("[insertarAutoparte]ERROR AL CREAR TABLAS",e);
				}
				finally
				{
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD			
				}	
				return true;
			}
		
		//###################################### INSERTAR FILTRO #############################################################################
		
			public boolean insertarFiltro(int id,int autoparte_ID,String tamaño,String material) throws MiException, SQLException
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
						query="INSERT INTO Filtro (filtro_ID,autoparte_ID,tamaño,material) VALUES ('"+id+"','"+autoparte_ID+"','"+tamaño+"','"+material+"')";
						stmt.executeUpdate(query);
						conn.commit();
						System.out.print("\n[insertarFiltro] "+query);
						
					}catch(SQLException e)
					{
						conn.rollback();	
					}
				
				}catch(SQLException e)
				{
					throw new MiException("[insertarFiltro]SQL Connection EXCEPTION "+e.getMessage());
				}catch(Exception e)
				{
				
					throw new MiException("[insertarFiltro]ERROR AL CREAR TABLAS",e);
				}
				finally
				{
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD			
				}	
				return true;
			}
		
			public boolean insertarFiltro(Filtro filtro) throws MiException, SQLException
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
						query="INSERT INTO Filtro (filtro_ID,autoparte_ID,tamaño,material) VALUES ('"+filtro.getId()+"','"+filtro.getAutoparteID()+"','"+filtro.getTamaño()+"','"+filtro.getMaterial()+"')";
						stmt.executeUpdate(query);
						conn.commit();
						System.out.print("\n[insertarFiltro] "+query);
						
					}catch(SQLException e)
					{
						conn.rollback();	
					}
				
				}catch(SQLException e)
				{
					throw new MiException("[insertarFiltro]SQL Connection EXCEPTION "+e.getMessage());
				}catch(Exception e)
				{
				
					throw new MiException("[insertarFiltro]ERROR AL CREAR TABLAS",e);
				}
				finally
				{
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD			
				}	
				return true;
			}
		
		//###################################### INSERTAR ACEITE #############################################################################
				
		public boolean insertarAceite(int id,int autoparte_ID,int cantLitros,String tipo) throws MiException, SQLException
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
					query="INSERT INTO Aceite (aceite_ID,autoparte_ID,litros,tipo) VALUES ('"+id+"','"+autoparte_ID+"','"+cantLitros+"','"+tipo+"')";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[insertarAceite] "+query);
				}catch(SQLException e)
				{
					conn.rollback();	
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[insertarAceite]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[insertarAceite]ERROR AL CREAR TABLAS",e);
			}
			finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD			
			}	
			return true;
		}
		public boolean insertarAceite(Aceite aceite) throws MiException, SQLException
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
					query="INSERT INTO Aceite (aceite_ID,autoparte_ID,litros,tipo) VALUES ('"+aceite.getId()+"','"+aceite.getAutoparteID()+"','"+aceite.getCantidadlitros()+"','"+aceite.getTipoAceite()+"')";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[insertarAceite] "+query);
				}catch(SQLException e)
				{
					conn.rollback();	
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[insertarAceite]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[insertarAceite]ERROR AL CREAR TABLAS",e);
			}
			finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD			
			}	
			return true;
		}
		//############################## INSERTAR LAMPARA #####################################################################################
		
		public boolean insertarLampara(int id,int autoparte_ID,String color,String tamaño) throws MiException, SQLException
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
					query="INSERT INTO Lampara (lampara_ID,autoparte_ID,color,tamaño) VALUES ('"+id+"','"+autoparte_ID+"','"+color+"','"+tamaño+"')";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[insertarLampara] "+query);
				}catch(SQLException e)
				{
					conn.rollback();	
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[insertarLampara]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[insertarLampara]ERROR AL CREAR TABLAS",e);
			}
			finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD			
			}	
			return true;
		}
		public boolean insertarLampara(Lampara lampara) throws MiException, SQLException
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
					query="INSERT INTO Lampara (lampara_ID,autoparte_ID,color,tamaño) VALUES ('"+lampara.getId()+"','"+lampara.getAutoparteID()+"','"+lampara.getColor()+"','"+lampara.getTamaño()+"')";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[insertarLampara] "+query);
				}catch(SQLException e)
				{
					conn.rollback();	
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[insertarLampara]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[insertarLampara]ERROR AL CREAR TABLAS",e);
			}
			finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD			
			}	
			return true;
		}
		
}
