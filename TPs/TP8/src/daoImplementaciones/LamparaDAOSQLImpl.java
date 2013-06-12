package daoImplementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBManager;
import utils.MiException;


import dao.LamparaDAO;
import entities.Lampara;


public class LamparaDAOSQLImpl implements LamparaDAO{

	//############################## CREATE TABLA LAMPARA #####################################################################################
		public boolean crearTablaLampara() throws MiException
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
					query="CREATE TABLE Lampara (lampara_ID int NOT NULL PRIMARY KEY, autoparte_ID int,color varchar(20) NOT NULL, tamanio varchar(20) NOT NULL)";							
					stmt.executeUpdate(query);			
					conn.commit();
									
					System.out.print("\n[crearTablaLampara] "+query);			//DEBUG 
					
					stmt.execute("SHUTDOWN");							
					conn.close();
				}catch(SQLException e)
				{
					System.out.print("\n[crearTablaLampara] SQL Exception AL CREAR: "+e);			//DEBUG
					conn.rollback();
					stmt.execute("SHUTDOWN");							
					conn.close();	
					//throw new MiException("[crearTablaLampara] SQL Exception: "+e);
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[crearTablaLampara]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[crearTablaLampara]ERROR AL CREAR TABLAS",e);
			}
			
			return true;
		}
	//##################################### CARGAR  LAMPARAS######################################################
	public List<Lampara> cargarLamparas() throws MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		
		List<Lampara> lamparas=new ArrayList<Lampara>();
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Lampara";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					System.out.print("\n[cargarLamparas] HAY Lampara ");		//DEBUG
					do {										
						Lampara lampara=new Lampara();
						lampara.setLampara_ID(rs.getInt("lampara_ID"));					
						lampara.setAutoparteID(rs.getInt("autoparte_ID"));
						lampara.setColor(rs.getString("color"));
						lampara.setTamanio(rs.getString("tamanio"));
						
						lamparas.add(lampara);
					}while(rs.next());
					
				}else
				{	
					System.out.print("\n[cargarLamparas]NO HAY Lampara CARGADOS : ");		//DEBUG
				}
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				System.out.print("\n[cargarLamparas] SQL Exception al cargar: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[cargarLamparas] SQL Exception: "+e);
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargarLamparas] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargarLamparas] EXCEPTION: "+e);
		}
		return lamparas;
	}
	
	
	
	//############################## DELETE LAMPARA ###################################################
	public boolean eliminarLampara(Lampara lampara) throws MiException 
	{		
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
				query="DELETE FROM Lampara WHERE lampara_ID="+lampara.getLampara_ID();
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[eliminarLampara] "+query);			//DEBUG
				deleted=true;
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[eliminarLampara] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				//throw new MiException("[eliminarLampara] SQL Exception: "+e);
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}						
		}catch(SQLException e)
		{
			throw new MiException("[eliminarLampara] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[eliminarLampara] EXCEPTION AL CONECTAR: "+e);
		}
		return deleted;
	}
	
	//############################## INSERTAR LAMPARA #####################################################################################
	public boolean insertarLampara(int id,int autoparte_ID,String color,String tamanio) throws MiException
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
				query="INSERT INTO Lampara (lampara_ID,autoparte_ID,color,tamanio) VALUES ("+id+",'"+autoparte_ID+"','"+color+"','"+tamanio+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarLampara] "+query);				//DEBUG
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[insertarLampara] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarLampara]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarLampara]ERROR AL CREAR TABLAS",e);
		}
			
		return true;
	}
	
	public boolean insertarLampara(Lampara lampara) throws MiException
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
				query="INSERT INTO Lampara (lampara_ID,autoparte_ID,color,tamanio) VALUES ("+lampara.getLampara_ID()+",'"+lampara.getId()+"','"+lampara.getColor()+"','"+lampara.getTamanio()+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarLampara] "+query);				//DEBUG
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[insertarLampara] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarLampara]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarLampara]ERROR AL CREAR TABLAS",e);
		}
			
		return true;
	}
	
	//###################################### MODIFICAR LAMPARA #############################################################################
	public boolean updateLampara(Lampara lampara) throws MiException
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
				query="UPDATE Lampara SET lampara_ID='"+lampara.getLampara_ID()+"',autoparte_ID='"+lampara.getAutoparteID()+"',color='"+lampara.getColor()+"',tama�o='"+lampara.getTamanio()+"' WHERE lampara_ID='"+lampara.getLampara_ID()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateLampara] "+query);		//DEBUG
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[updateLampara] Exception SQL: "+e);					//DEBUG
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateLampara]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateLampara]ERROR AL CREAR TABLAS",e);
		}
			
		return true;
	}
	//###################################### BUSCAR LAMPARA POR ID AUTOPARTE #############################################################################
	public Lampara buscarLamparaPorIdAutoParte(int id) throws MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		Lampara lampara=new Lampara();
		
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Lampara WHERE autoparte_ID="+id;
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					//System.out.print("\n[buscarFiltroPorId] HAY lampara ");		//DEBUG
					do {										
						
						lampara.setId(rs.getInt("lampara_ID"));					
						lampara.setColor(rs.getString("color"));
						lampara.setTamanio(rs.getString("tama�o"));
						lampara.setAutoparteID(rs.getInt("autoparte_ID"));
						
					}while(rs.next());
				}else
				{	
					lampara=null;
					//System.out.print("\n[buscarLamparaPorIdAutoParte]NO HAY lampara CARGADOS : ");		//DEBUG
				}
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
							
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				
				//throw new MiException("[Login] SQL Exception: "+e);
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarLamparaPorIdAutoParte] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarLamparaPorIdAutoParte] EXCEPTION AL CONECTAR: "+e);
		}
		return lampara;
	}
	
	//##################################### BUSCAR ULTIMO Lampara ID ##########################################
	public int buscarUltimoLamparaId() throws  MiException
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
				query="SELECT lampara_ID FROM Lampara ORDER BY lampara_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("lampara_ID");
					inLastID++;
				//	System.out.print("\n[buscarUltimoLamparaId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					//System.out.print("\n[buscarUltimoLamparaId] NO HAY COINCIDENCIAS");	//DEBUG
					//inLastID=-1;
					inLastID=1;
				}			
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimoLamparaId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[buscarUltimoLamparaId] SQL Exception: "+e);
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimoLamparaId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimoLamparaId] EXCEPTION AL CONECTAR: "+e);
		}
		return inLastID;
	}
	
}
