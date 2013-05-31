package daoImplementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import utils.DBManager;
import utils.MiException;

import dao.AceiteDAO;
import entities.Aceite;

public class AceiteDAO_SQL_Impl implements AceiteDAO{

	//############################## CREATE TABLA ACIETE #####################################################################################
	public boolean crearTablaAceite() throws MiException
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
				query="CREATE TABLE Aceite (aceite_ID int NOT NULL PRIMARY KEY, autoparte_ID int,litros int NOT NULL, tipo varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				
				System.out.print("\n[crearTablaAceite] "+query);			//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaAceite] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaAceite]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaAceite]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
	}
	
	//################################## CARGAR ACEITES #############################################
	public List<Aceite> cargarAceites() throws MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		
		List<Aceite> aceites=new ArrayList<Aceite>();
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Aceite";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					System.out.print("\n[cargarAceites] HAY Aceite ");		//DEBUG
					do {										
						Aceite aceite=new Aceite();
						aceite.setAceite_ID(rs.getInt("aceite_ID"));					
						aceite.setAutoparteID(rs.getInt("autoparte_ID"));
						aceite.setCantidadlitros(rs.getInt("litros"));
						aceite.setTipoAceite(rs.getString("tipo"));
									
						aceites.add(aceite);
					}while(rs.next());
				}else
				{	
					System.out.print("\n[cargarAceites]NO HAY Aceite CARGADOS : ");		//DEBUG
				}
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
							
			}catch(SQLException e)
			{
				System.out.print("\n[cargarAceites] SQL Exceptiona al cargar: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargarAceites] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargarAceites] EXCEPTION: "+e);
		}
		return aceites;
	}
	

		
		
	//############################## DELETE ACEITE ###################################################
	public boolean eliminarAceite(Aceite aceite) throws MiException {
		
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
				query="DELETE FROM Aceite WHERE aceite_ID="+aceite.getAceite_ID();
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[eliminarAceite] "+query);			//DEBUG
				deleted=true;
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				System.out.print("\n[eliminarAceite] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
				//throw new MiException("[eliminarAceite] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[eliminarAceite] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[eliminarAceite] EXCEPTION AL CONECTAR: "+e);
		}
		
		return deleted;
	}
	
	//###################################### INSERTAR ACEITE #############################################################################
	public boolean insertarAceite(int id,int autoparte_ID,int cantLitros,String tipo) throws MiException
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
				query="INSERT INTO Aceite (aceite_ID,autoparte_ID,litros,tipo) VALUES ("+id+",'"+autoparte_ID+"',"+cantLitros+",'"+tipo+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarAceite] "+query);				//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				
			}catch(SQLException e)	
			{
				System.out.print("\n[insertarAceite] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();	
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarAceite]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarAceite]ERROR AL CREAR TABLAS",e);
		}
			
		return true;
	}
	
	public boolean insertarAceite(Aceite aceite) throws MiException
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
				query="INSERT INTO Aceite (aceite_ID,autoparte_ID,litros,tipo) VALUES ("+aceite.getAceite_ID()+",'"+aceite.getId()+"',"+aceite.getCantidadlitros()+",'"+aceite.getTipoAceite()+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarAceite] "+query);				//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[insertarAceite] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarAceite]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarAceite]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
	}
	
	//###################################### MODIFICAR ACEITE #############################################################################
	public boolean updateAceite(Aceite aceite) throws MiException
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
				query="UPDATE Aceite SET aceite_ID='"+aceite.getAceite_ID()+"',autoparte_ID='"+aceite.getAutoparteID()+"',litros='"+aceite.getCantidadlitros()+"',tipo='"+aceite.getTipoAceite()+"' WHERE aceite_ID="+aceite.getAceite_ID();
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateAceite] "+query);					//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				System.out.print("\n[updateAceite] Exception SQL: "+e);					//DEBUG
				conn.rollback();	
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateAceite]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateAceite]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
	}
	
	public Aceite buscarAceitePorIdAutoParte(int id) throws MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		Aceite aceite=new Aceite();
		
		try
		{			
			conn = DBManager.getConnection();
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
					//System.out.print("\n[buscarAceitePorIdAutoParte]NO HAY aceite CARGADOS : ");		//DEBUG
				}
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();						
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarAceitePorIdAutoParte] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarAceitePorIdAutoParte] EXCEPTION AL CONECTAR: "+e);
		}
		return aceite;
	}
	
	//##################################### BUSCAR ULTIMO ACEITE ID ##########################################
	public int buscarUltimoAceiteId() throws MiException
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
				query="SELECT aceite_ID FROM Aceite ORDER BY aceite_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("aceite_ID");
					inLastID++;
			//		System.out.print("\n[buscarUltimoAceiteId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					//System.out.print("\n[buscarUltimoAceiteId] NO HAY COINCIDENCIAS");	//DEBUG
					//
					//inLastID=-1;
					inLastID=1;
					
				}		
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimoAceiteId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[buscarUltimoAceiteId] SQL Exception: "+e);
			}
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimoAceiteId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimoAceiteId] EXCEPTION AL CONECTAR: "+e);
		}
		return inLastID;
	}
}
