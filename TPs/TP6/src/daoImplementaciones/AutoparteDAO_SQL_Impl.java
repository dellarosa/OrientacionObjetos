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
import dao.AutoparteDAO;
import dao.FiltroDAO;
import dao.LamparaDAO;
import entities.Aceite;
import entities.Autoparte;
import entities.Filtro;
import entities.Lampara;


public class AutoparteDAO_SQL_Impl implements AutoparteDAO{

	//############################## CREATE TABLA AUTOPARTES #####################################################################################
	public boolean crearTablaAutoparte() throws MiException
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
				//query="CREATE TABLE Autopartes(autoparte_ID int NOT NULL PRIMARY KEY,indiceTipoAutoparte int NOT NULL,TipoAutoparte varchar(30),descripcion varchar(100),marca varchar(25) NOT NULL,modelo varchar(25) NOT NULL,costo float NOT NULL,cantidadDisponible int)						
				//query="INSERT INTO Autopartes (autoparte_ID,indiceTipo,tipoAutoparte,descripcion,marca,modelo,costo,tama�o) VALUES (1,1,1,'filtro aire','filtros para autos','Filtros2000','F2000',30,'grande',4)";
				query="CREATE TABLE Autoparte(autoparte_ID int NOT NULL PRIMARY KEY,tipoAutoparte varchar(50),marca varchar(25) NOT NULL,modelo varchar(25) NOT NULL,costo float NOT NULL,cantidadDisponible int)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[crearTablaAutoparte] "+query);			//DEBUG 
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaAutoparte] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
				//throw new MiException("[crearTablaAutoparte] SQL Exception: "+e);
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaAutoparte]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaAutoparte]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
	}
	
	//########################################## CARGAR AUTOPARTES ##############################################
	public List<Autoparte> cargaAutopartes() throws  MiException {
		
		AceiteDAO aceiteDao=new AceiteDAO_SQL_Impl();
		FiltroDAO filtroDao=new FiltroDAO_SQL_Impl();
		LamparaDAO lamparaDao=new LamparaDAO_SQL_Impl();
		
		String query;
		Connection conn=null;
		Statement stmt=null;	
		List<Autoparte> autopartes=new ArrayList<Autoparte>();
		
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Autoparte";
				ResultSet rsAutopart=stmt.executeQuery(query);			
				conn.commit();					
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				List<Filtro> filtros=filtroDao.cargarFiltros();
				List<Aceite> aceites=aceiteDao.cargarAceites();
				List<Lampara> lamparas=lamparaDao.cargarLamparas();
					while(rsAutopart.next())
					{
						
						//System.out.print("\n[cargaAutopartes] TIPO AUTOPARTE:"+rsAutopart.getString("tipoAutoparte"));		//DEBUG
						String tipoAutoparte=rsAutopart.getString("tipoAutoparte");
						//PODRIA CARGAR TODAS LAS AUTOPARTES PRIMERO Y DESPUES UNIR pero, ahora esta es la solucion
						if(tipoAutoparte.equals("filtro"))		//Podr�a preguntar por ID tipo y tener otra tabla mas
						{
							
							for(Filtro filtro : filtros)
							{
								if(rsAutopart.getInt("autoparte_ID")==filtro.getAutoparteID())
								{
									filtro.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));									
									filtro.setCosto(rsAutopart.getFloat("costo"));
									filtro.setModelo(rsAutopart.getString("modelo"));
									filtro.setMarca(rsAutopart.getString("marca"));
									filtro.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
									//filtro.setAutoparteID(rsAutopart.getInt("autoparte_ID"));
									filtro.setId(rsAutopart.getInt("autoparte_ID"));
									autopartes.add(filtro);
									//System.out.print("\n[cargaAutopartes] CARGA FILTRO: "+filtro.toString());		//DEBUG
								}
							}								
						}
						
						if(tipoAutoparte.equals("aceite"))		//Podr�a preguntar por ID tipo
						{								
							
							for(Aceite aceite : aceites)
							{
								if(rsAutopart.getInt("autoparte_ID")==aceite.getAutoparteID())
								{
									aceite.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
									aceite.setCosto(rsAutopart.getFloat("costo"));
									aceite.setModelo(rsAutopart.getString("modelo"));
									aceite.setMarca(rsAutopart.getString("marca"));
									aceite.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
									//aceite.setAutoparteID(rsAutopart.getInt("autoparte_ID"));
									aceite.setId(rsAutopart.getInt("autoparte_ID"));
									autopartes.add(aceite);
									//System.out.print("\n[cargaAutopartes] CARGA ACEITE: "+aceite.toString());		//DEBUG
								}
							}
							//cargarAceites(aceites);
						}
						
						if(tipoAutoparte.equals("lampara"))
						{	
							
							for(Lampara lampara : lamparas)
							{	
								if(rsAutopart.getInt("autoparte_ID")==lampara.getAutoparteID())
								{
									lampara.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
									lampara.setCosto(rsAutopart.getFloat("costo"));
									lampara.setModelo(rsAutopart.getString("modelo"));
									lampara.setMarca(rsAutopart.getString("marca"));
									lampara.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
									//lampara.setAutoparteID(rsAutopart.getInt("autoparte_ID"));
									lampara.setId(rsAutopart.getInt("autoparte_ID"));
									autopartes.add(lampara);
									//System.out.print("\n[cargaAutopartes] CARGA LAMPARA: "+lampara.toString());		//DEBUG
								}
							}
							
						}else
						{}
					}
							
			}catch(SQLException e)
			{
				System.out.print("\n[cargaAutopartes] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[cargaAutopartes] SQL Exception: "+e);
			}						
			
		}catch(SQLException e)
		{
			throw new MiException("[cargaAutopartes]SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaAutopartes] EXCEPTION AL CONECTAR: "+e);
		}
		return autopartes;
		
	}

	
	
	//############################## DELETE AUTOPARTE ###################################################
	public boolean eliminarAutoparte(Autoparte autoparte) throws MiException {
		
		boolean deleted=false;
		String query;
		Connection conn=null;
		Statement stmt = null;
		AceiteDAO aceiteDao=new AceiteDAO_SQL_Impl();
		FiltroDAO filtroDao=new FiltroDAO_SQL_Impl();
		LamparaDAO lamparaDao=new LamparaDAO_SQL_Impl();
		
		try
		{			
			conn=DBManager.getConnection();
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
					filtroDao.eliminarFiltro((Filtro)autoparte);
				}else if(autoparte instanceof Aceite)
				{
					aceiteDao.eliminarAceite((Aceite)autoparte);
				}
				else if(autoparte instanceof Lampara)
				{
					lamparaDao.eliminarLampara((Lampara)autoparte);
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
	
	//###################################### INSERTAR AUTOPARTE #############################################################################
	public boolean insertarAutoparte(int autoparte_ID,String tipo,String marca,String modelo,double costo,int cantDisponible) throws MiException
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
				query="INSERT INTO Autoparte (autoparte_ID,tipoAutoparte,marca,modelo,costo,cantidadDisponible) VALUES ("+autoparte_ID+",'"+tipo+"','"+marca+"','"+modelo+"','"+costo+"',"+cantDisponible+")";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarAutoparte] "+query);					//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				
				System.out.print("\n[insertarAutoparte] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();	
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarAutoparte]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarAutoparte]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
	}
	
	public boolean insertarAutoparte(Autoparte autoparte) throws MiException
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
				query="INSERT INTO Autoparte (autoparte_ID,tipoAutoparte,marca,modelo,costo,cantidadDisponible) VALUES ("+autoparte.getId()+",'"+autoparte.getTipoAutoparte()+"','"+autoparte.getMarca()+"','"+autoparte.getModelo()+"','"+autoparte.getCosto()+"',"+autoparte.getCantDisponible()+")";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarAutoparte] "+query);					//DEBUG
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				System.out.print("\n[insertarAutoparte] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarAutoparte]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarAutoparte]ERROR AL CREAR TABLAS",e);
		}

		return true;
	}
	
	//###################################### MODIFICAR AUTOPARTE #############################################################################
	public boolean updateAutoparte(Autoparte autoparte) throws MiException
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
				query="UPDATE Autoparte  SET autoparte_ID="+autoparte.getId()+",tipoAutoparte='"+autoparte.getTipoAutoparte()+"',marca='"+autoparte.getMarca()+"',modelo='"+autoparte.getModelo()+"',costo='"+autoparte.getCosto()+"',cantidadDisponible="+autoparte.getCantDisponible()+" WHERE autoparte_ID="+autoparte.getId();
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateAutoparte] "+query);					//DEBUG

				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				
			}catch(SQLException e)
			{
				System.out.print("\n[updateAutoparte] Exception SQL: "+e);					//DEBUG
				conn.rollback();

				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateAutoparte]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateAutoparte]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
	}

	//##################################### BUSCAR ULTIMO AUTOPARTE ID ##########################################
		public int buscarUltimaAutoparteId() throws  MiException
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
					query="SELECT autoparte_ID FROM Autoparte ORDER BY autoparte_ID DESC LIMIT 1";
					ResultSet rs=stmt.executeQuery(query);			
					conn.commit();
					if(rs.next()) {									
						inLastID=rs.getInt("autoparte_ID");
						inLastID++;
					//	System.out.print("\n[buscarUltimaAutoparteId] LAST ID: "+inLastID);	//DEBUG
					}else
					{	
						//System.out.print("\n[buscarUltimaAutoparteId] NO HAY COINCIDENCIAS");	//DEBUG
						//inLastID=-1;
						inLastID=1;
						
					}			
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();
				}catch(SQLException e)
				{
					System.out.print("\n[buscarUltimaAutoparteId] SQL Exception: "+e);		//DEBUG				
					conn.rollback();					
					//throw new MiException("[buscarUltimaAutoparteId] SQL Exception: "+e);
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();
				}						
			}catch(SQLException e)
			{
				throw new MiException("[buscarUltimaAutoparteId] SQL EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[buscarUltimaAutoparteId] EXCEPTION AL CONECTAR: "+e);
			}
			
			return inLastID;
		}
	
}
