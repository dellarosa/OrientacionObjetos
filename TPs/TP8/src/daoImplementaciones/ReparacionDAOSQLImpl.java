package daoImplementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBManager;
import utils.Definiciones;
import utils.MiException;


import dao.AceiteDAO;
import dao.FiltroDAO;
import dao.LamparaDAO;
import dao.ReparacionDAO;
import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;


public class ReparacionDAOSQLImpl implements ReparacionDAO{

	//############################## CREATE TABLA REPARACION #####################################################################################
	public boolean crearTablaReparacion() throws MiException
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
				query="CREATE TABLE Reparacion(reparacion_ID int NOT NULL PRIMARY KEY,cliente_ID int NOT NULL,costo double ,fechaInicio varchar(40) NOT NULL, fechaEntrega varchar(40),entregado int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
								
				System.out.print("\n[crearTablaReparacion] "+query);			//DEBUG 
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaReparacion] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
				//throw new MiException("[crearTablaReparacion] SQL Exception: "+e);
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaReparacion]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaReparacion]ERROR AL CREAR TABLAS",e);
		}finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[crearTablaReparacion] Exception Finally"+e);
			}					
			
		}
			
		return true;
	}
	//####################################################### CARGAR REPARACIONES ################################
	public List<Reparacion> cargaReparaciones() throws MiException {
		String query;
		Connection conn=null;
		Statement stmt=null;
		
		AceiteDAO aceiteDao=new AceiteDAOSQLImpl();
		FiltroDAO filtroDao=new FiltroDAOSQLImpl();
		LamparaDAO lamparaDao=new LamparaDAOSQLImpl();
		
		List<Reparacion> reparaciones=new ArrayList<Reparacion>();
		
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Reparacion";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				stmt.execute("SHUTDOWN");							
				conn.close();
				
				//System.out.print("\n[cargaReparaciones] QUERY: "+query);	//DEBUG
				if(rs.next())
				{
					
					//	System.out.print("\n[cargaReparaciones] HAY REPARACIONES ");		//DEBUG
						do {
							
							
							conn = DBManager.getConnection();
							conn.setAutoCommit(false);
							stmt = conn.createStatement();
							
							Reparacion reparacion = new Reparacion();
							
							reparacion.setId(rs.getInt("reparacion_ID"));
							//######################CLIENTE###############					
							query="SELECT * FROM Cliente WHERE cliente_ID="+rs.getInt("cliente_ID");
							ResultSet rsclient=stmt.executeQuery(query);			
							conn.commit();
							
							stmt.execute("SHUTDOWN");							
							conn.close();
						
						//	System.out.print("\n[cargaReparaciones] QUERY: "+query);	//DEBUG
							
							Cliente client=new Cliente();
							while(rsclient.next())
							{
								client.setId(rs.getInt("cliente_ID"));
								client.setMail(rsclient.getString("mail"));
								client.setAuto(rsclient.getString("auto"));
								client.setNombre(rsclient.getString("nombre"));
							}
							reparacion.setCliente(client);
							//System.out.print("\n[cargaReparaciones] REPARACION CLIENTE: "+client.toString());	//DEBUG
							
							//#########################################
							reparacion.setFechainicio(rs.getString("fechaInicio"));
							
							
							if(rs.getInt("entregado")!=0)
							{
													
								reparacion.setCosto(rs.getDouble("costo"));
								reparacion.setEntregado(rs.getInt("entregado"));	
								reparacion.setFechaentrega(rs.getString("fechaEntrega"));
								
								//######################AUTOPARTE###############					
								
								List<Autoparte> autopartes=new ArrayList<Autoparte>();
								Filtro filtro=new Filtro();
								Aceite aceite=new Aceite();
								Lampara lampara=new Lampara();
									
								conn = DBManager.getConnection();
								conn.setAutoCommit(false);
								stmt = conn.createStatement();
																	
								//query="SELECT * FROM ReparacionAutoparte";
								query="SELECT * FROM ReparacionAutoparte WHERE reparacion_ID="+rs.getInt("reparacion_ID");
								ResultSet rsRepAuto=stmt.executeQuery(query);			
								conn.commit();
								
								//System.out.print("\n[cargaReparaciones] QUERY: "+query);	//DEBUG									
								
								stmt.execute("SHUTDOWN");							
								conn.close();	
								
								while(rsRepAuto.next())
								{
									conn = DBManager.getConnection();
									conn.setAutoCommit(false);
									stmt = conn.createStatement();
									
									query="SELECT * FROM Autoparte WHERE autoparte_ID="+rsRepAuto.getInt("autoparte_ID");	//VA a traer uno solo
									ResultSet rsAutopart=stmt.executeQuery(query);			
									conn.commit();		
									
									//System.out.print("\n[cargaReparaciones] QUERY: "+query);	//DEBUG
									
									stmt.execute("SHUTDOWN");							
									conn.close();	
									if(rsAutopart.next())
									{
										//System.out.print("\n[cargaReparaciones] TIPO AUTOPARTE: "+rsAutopart.getString("tipoAutoparte"));	//DEBUG
										if(rsAutopart.getString("tipoAutoparte").equals("filtro"))
										{	
											
											filtro=filtroDao.buscarFiltroPorIdAutoParte(rsAutopart.getInt("autoparte_ID"));		//POdria usar la lista de filtros
											if(filtro!=null)
											{	
												filtro.setCosto(rsAutopart.getDouble("costo"));
												filtro.setId(rsAutopart.getInt("autoparte_ID"));
												filtro.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
												filtro.setMarca(rsAutopart.getString("marca"));
												filtro.setModelo(rsAutopart.getString("modelo"));
												filtro.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
												autopartes.add(filtro);											
												
												//System.out.print("\n[cargaReparaciones] FILTRO EN REPARACION: "+filtro.toString());	//DEBUG
												
											}
									
										}else if(rsAutopart.getString("tipoAutoparte").equals("aceite"))
										{
											
											aceite=aceiteDao.buscarAceitePorIdAutoParte(rsAutopart.getInt("autoparte_ID"));
											if(aceite!=null)
											{
												aceite.setCosto(rsAutopart.getDouble("costo"));
												aceite.setId(rsAutopart.getInt("autoparte_ID"));
												aceite.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
												aceite.setMarca(rsAutopart.getString("marca"));
												aceite.setModelo(rsAutopart.getString("modelo"));
												aceite.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
												autopartes.add(aceite);		
												
												//System.out.print("\n[cargaReparaciones] ACEITE EN REPARACION: "+aceite.toString());	//DEBUG
											}
											
										}else if(rsAutopart.getString("tipoAutoparte").equals("lampara"))
										{
											lampara=lamparaDao.buscarLamparaPorIdAutoParte(rsAutopart.getInt("autoparte_ID"));
											if(lampara!=null)
											{
												
												lampara.setCosto(rsAutopart.getDouble("costo"));
												lampara.setId(rsAutopart.getInt("autoparte_ID"));
												lampara.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
												lampara.setMarca(rsAutopart.getString("marca"));
												lampara.setModelo(rsAutopart.getString("modelo"));
												lampara.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
												
												autopartes.add(lampara);
												
												//System.out.print("\n[cargaReparaciones] LAMPARA EN REPARACION: "+lampara.toString());	//DEBUG
											}	
										}else
										{}
									}										
								}
								reparacion.setAutopartes(autopartes);
							}else
							{
								
							}					
							reparaciones.add(reparacion);
						}while(rs.next());
						System.out.print("\n[cargaReparaciones]HAY REPARACIONES: ");		//DEBUG
				}else
				{	
					System.out.print("\n[cargaReparaciones]NO HAY REPARACIONES CARGADOS : ");		//DEBUG
				}
				
				
							
			}catch(SQLException e)
			{
				System.out.print("\n[cargaReparaciones] SQL Exception al cargar: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[cargaReparaciones] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargaReparaciones] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaReparaciones] EXCEPTION: "+e);
		}
		return reparaciones;
	}


		
	//############################## CREATE TABLA REPARACION AUTOPARTES #####################################################################################
	public boolean crearTablaReparacionAutoparte() throws MiException
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
				query="CREATE TABLE ReparacionAutoparte(reparacionAutoparte_ID int NOT NULL PRIMARY KEY,reparacion_ID int NOT NULL ,autoparte_ID int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				
				System.out.print("\n[crearTablaReparacionAutoparte] "+query);			//DEBUG 
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaReparacionAutoparte] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				//throw new MiException("[crearTablaReparacionAutoparte] SQL Exception: "+e);
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaReparacionAutoparte]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaReparacionAutoparte]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
	}

	//############################## INSERTAR REPARACION INICIO #####################################################################################
	public boolean insertarReparacionInicio(Reparacion nuevareparacion) throws MiException {
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
				query="INSERT INTO Reparacion (reparacion_ID,cliente_ID,fechaInicio,entregado) VALUES ("+
				nuevareparacion.getId()+","+nuevareparacion.getCliente().getId()+",'"+nuevareparacion.getFechainicio()+"',"+nuevareparacion.getEntregado()+")";
				
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarReparacionInicio] "+query);				//DEBUG
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}catch(SQLException e)
			{
				System.out.print("\n[insertarReparacionInicio] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarReparacionInicio]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarReparacionInicio]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
		
	}
	
	
	///MEZCLA CON UPDATE
	public boolean insertarupdateReparacionFinal(Reparacion reparacion,int inLastIdRA) throws MiException {
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
				for(Autoparte autoparte:reparacion.getAutopartes())
				{
					
					query="INSERT INTO ReparacionAutoparte (reparacionAutoparte_ID,reparacion_ID,autoparte_ID) VALUES ("+
					inLastIdRA+","+reparacion.getId()+","+autoparte.getId()+")";
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[insertarReparacionFinal] "+query);				//DEBUG									
					
					inLastIdRA++;
				}
									
				query="UPDATE Reparacion SET reparacion_ID="+reparacion.getId()+",costo='"+reparacion.getCosto()+"',fechaEntrega='"+reparacion.getFechaentrega()+"',entregado="+reparacion.getEntregado()+" WHERE reparacion_ID="+reparacion.getId();					
				stmt.executeUpdate(query);					
				conn.commit();
				System.out.print("\n[insertarReparacionFinal] "+query);				//DEBUG
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[insertarupdateReparacionFinal] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();	
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("\n[insertarupdateReparacionFinal]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("\n[insertarupdateReparacionFinal]ERROR AL CREAR TABLAS",e);
		}
		
		return true;
		
	}
	
	//##################################### BUSCAR ULTIMO REPARACION ID ##########################################
	public int buscarUltimaReparacionId() throws MiException
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
				query="SELECT reparacion_ID FROM Reparacion ORDER BY reparacion_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("reparacion_ID");
					inLastID++;
				//	System.out.print("\n[buscarUltimaReparacionId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					//System.out.print("\n[buscarUltimaReparacionId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=1;
					//inLastID=-1;
					
				}			
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimaReparacionId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				//throw new MiException("[buscarUltimaReparacionId] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimaReparacionId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimaReparacionId] EXCEPTION AL CONECTAR: "+e);
		}

		return inLastID;
	}
	//##################################### BUSCAR ULTIMO REPARACION AUTOPARTE ID ##########################################
	public int buscarUltimaReparacionAutoparteId() throws  MiException
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
				query="SELECT reparacionAutoparte_ID FROM ReparacionAutoparte ORDER BY reparacionAutoparte_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("reparacionAutoparte_ID");
					inLastID++;
					//System.out.print("\n[buscarUltimaReparacionAutoparteId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					//System.out.print("\n[buscarUltimaReparacionAutoparteId] NO HAY COINCIDENCIAS");	//DEBUG
					inLastID=1;
					//inLastID=-1;
					
				}	
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimaReparacionAutoparteId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[buscarUltimaReparacionAutoparteId] SQL Exception: "+e);
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimaReparacionAutoparteId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimaReparacionAutoparteId] EXCEPTION AL CONECTAR: "+e);
		}
		return inLastID;
	}
	
}
