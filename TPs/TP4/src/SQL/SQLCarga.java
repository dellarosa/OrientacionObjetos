package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;
import entities.Usuario;

import utils.Definiciones;
import utils.MiException;

public class SQLCarga {

	SQLSelects sqlselects=new SQLSelects();
	
	//################################### CARGA CLIENTES ################################################################
		public List<Cliente> cargaClientes() throws SQLException,MiException {
			
			String query;
			Connection conn=null;
			Statement stmt=null;
			
			List<Cliente> clientes=new ArrayList<Cliente>();
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
						do {										
							Cliente cliente=new Cliente();
							cliente.setNombre(rs.getString("nombre"));
							cliente.setId(rs.getInt("cliente_ID"));
							cliente.setAuto(rs.getString("auto"));
							cliente.setMail(rs.getString("mail"));
							clientes.add(cliente);
							
						}while(rs.next());
					}else
					{	
						System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
					}
					
								
				}catch(SQLException e)
				{
					System.out.print("\n[cargaClientes] SQL Exception: "+e);		//DEBUG
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[cargaClientes] SQL EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[cargaClientes] EXCEPTION: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			}		
			return clientes;
		}
		//########################################## CARGAR AUTOPARTES ##############################################
		
		public List<Autoparte> cargaAutopartes() throws SQLException, MiException {
			String query;
			Connection conn=null;
			Statement stmt=null;	
			List<Autoparte> autopartes=new ArrayList<Autoparte>();
			
			try
			{			
				conn = SQLClass.getConnection();
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				try
				{				
					query="SELECT * FROM Autoparte";
					ResultSet rsAutopart=stmt.executeQuery(query);			
					conn.commit();					
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();
					List<Filtro> filtros=cargarFiltros();
					List<Aceite> aceites=cargarAceites();
					List<Lampara> lamparas=cargarLamparas();
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
					//throw new MiException("[Login] SQL Exception: "+e);
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
		//####################################################### CARGAR USUARIOS ################################
		public List<Usuario> cargarUsuarios() throws SQLException,MiException {
			String query;
			Connection conn=null;
			Statement stmt=null;			
			List<Usuario> usuarios=new ArrayList<Usuario>();
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
					System.out.print("\n[cargarUsuarios] SQL Exception al cargar: "+e);		//DEBUG
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[cargarUsuarios] EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[cargarUsuarios] EXCEPTION: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
				
			}	
			return usuarios;
		}
		
	//####################################################### CARGAR REPARACIONES ################################
		public List<Reparacion> cargaReparaciones() throws SQLException, MiException {
			String query;
			Connection conn=null;
			Statement stmt=null;
			
			List<Reparacion> reparaciones=new ArrayList<Reparacion>();
			
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
					
					stmt.execute("SHUTDOWN");							
					conn.close();
					
					//System.out.print("\n[cargaReparaciones] QUERY: "+query);	//DEBUG
					if(rs.next())
					{
						
						//	System.out.print("\n[cargaReparaciones] HAY REPARACIONES ");		//DEBUG
							do {
								
								
								conn = SQLClass.getConnection();
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
										
									conn = SQLClass.getConnection();
									conn.setAutoCommit(false);
									stmt = conn.createStatement();
																		
									query="SELECT * FROM ReparacionAutoparte WHERE ReparacionAutoparte_ID="+rs.getInt("reparacion_ID");
									ResultSet rsRepAuto=stmt.executeQuery(query);			
									conn.commit();
									
									//System.out.print("\n[cargaReparaciones] QUERY: "+query);	//DEBUG									
										
									stmt.execute("SHUTDOWN");							
									conn.close();	
									
									
									while(rsRepAuto.next())
									{
										conn = SQLClass.getConnection();
										conn.setAutoCommit(false);
										stmt = conn.createStatement();
										
										query="SELECT * FROM Autoparte WHERE autoparte_ID="+rsRepAuto.getInt("autoparte_ID");	//VA a traer uno solo
										ResultSet rsAutopart=stmt.executeQuery(query);			
										conn.commit();		
										
									//	System.out.print("\n[cargaReparaciones] QUERY: "+query);	//DEBUG
										
										stmt.execute("SHUTDOWN");							
										conn.close();	
										if(rsAutopart.next())
										{
											if(rsAutopart.getString("tipoAutoparte").equals(Definiciones.FILTRO_STRING))							{
												filtro=sqlselects.buscarFiltroPorIdAutoParte(rsAutopart.getInt("autoparte_ID"));		//POdria usar la lista de filtros
												if(filtro!=null)
												{	
													filtro.setCosto(rsAutopart.getDouble("costo"));
													filtro.setId(rsAutopart.getInt("autoparte_ID"));
													filtro.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
													filtro.setMarca(rsAutopart.getString("marca"));
													filtro.setModelo(rsAutopart.getString("modelo"));
													filtro.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
													autopartes.add(filtro);											
													
													
												}
										
											}else if(rsAutopart.getString("tipoAutoparte").equals(Definiciones.ACEITE_STRING))
											{
												aceite=sqlselects.buscarAceitePorIdAutoParte(rsAutopart.getInt("autoparte_ID"));
												if(aceite!=null)
												{
													aceite.setCosto(rsAutopart.getDouble("costo"));
													aceite.setId(rsAutopart.getInt("autoparte_ID"));
													aceite.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
													aceite.setMarca(rsAutopart.getString("marca"));
													aceite.setModelo(rsAutopart.getString("modelo"));
													aceite.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
													autopartes.add(aceite);											
												}
												
											}else if(rsAutopart.getString("tipoAutoparte").equals(Definiciones.LAMPARA_STRING))
											{
												lampara=sqlselects.buscarLamparaPorIdAutoParte(rsAutopart.getInt("autoparte_ID"));
												if(lampara!=null)
												{
													
													lampara.setCosto(rsAutopart.getDouble("costo"));
													lampara.setId(rsAutopart.getInt("autoparte_ID"));
													lampara.setCantDisponible(rsAutopart.getInt("cantidadDisponible"));
													lampara.setMarca(rsAutopart.getString("marca"));
													lampara.setModelo(rsAutopart.getString("modelo"));
													lampara.setTipoAutoparte(rsAutopart.getString("tipoAutoparte"));
													
													autopartes.add(lampara);
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
			}finally
			{
				
			}	
			return reparaciones;
		}
		
			
	//##################################### CARGAR FILTROS ACEITES LAMPARAS######################################################
	public List<Filtro> cargarFiltros() throws SQLException,MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;		
		List<Filtro> filtros=new ArrayList<Filtro>();
		
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Filtro";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					System.out.print("\n[cargaFiltros] HAY Filtros ");		//DEBUG
					do {										
						Filtro filtro=new Filtro();
						filtro.setFiltro_ID(rs.getInt("filtro_ID"));					
						filtro.setAutoparteID(rs.getInt("autoparte_ID"));
						filtro.setMaterial(rs.getString("material"));
						filtro.setTama�o(rs.getString("tama�o"));
						filtros.add(filtro);
						
					}while(rs.next());
				}else
				{	
					System.out.print("\n[cargaFiltros]NO HAY Filtros CARGADOS : ");		//DEBUG
				}
				
							
			}catch(SQLException e)
			{
				System.out.print("\n[cargaFiltros] SQL Exception al cargar: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[cargaFiltros] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargaFiltros] SQL EXCEPTION: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaFiltros] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return filtros;
	}
	public List<Aceite> cargarAceites() throws SQLException,MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		
		List<Aceite> aceites=new ArrayList<Aceite>();
		try
		{			
			conn = SQLClass.getConnection();
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
				
							
			}catch(SQLException e)
			{
				System.out.print("\n[cargarAceites] SQL Exceptiona al cargar: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargarAceites] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargarAceites] EXCEPTION: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return aceites;
	}
	public List<Lampara> cargarLamparas() throws SQLException,MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		
		List<Lampara> lamparas=new ArrayList<Lampara>();
		try
		{			
			conn = SQLClass.getConnection();
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
						lampara.setTama�o(rs.getString("tama�o"));
						
						lamparas.add(lampara);
					}while(rs.next());
					
				}else
				{	
					System.out.print("\n[cargarLamparas]NO HAY Lampara CARGADOS : ");		//DEBUG
				}
				
							
			}catch(SQLException e)
			{
				System.out.print("\n[cargarLamparas] SQL Exception al cargar: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargarLamparas] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargarLamparas] EXCEPTION: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return lamparas;
	}
}
