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
							cliente.setId(rs.getInt("usuario_ID"));
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
				throw new MiException("[cargaClientes] EXCEPTION AL CONECTAR: "+e);
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
					query="SELECT * FROM 'Autoparte'";
					ResultSet rsAutopart=stmt.executeQuery(query);			
					conn.commit();												
						while(rsAutopart.next())
						{
							if(rsAutopart.getString("tipoAutoparte")=="filtro")
							{										
								List<Filtro> filtros=cargarFiltros();
								for(Filtro filtro : filtros)
								{
									autopartes.add(filtro);
								}
								//Autoparte[Definiciones.FILTRO_INDICE]=cargarFiltros();
								
							}else if(rsAutopart.getString("tipoAutoparte")=="aceite")
							{								
								List<Aceite> aceites=cargarAceites();
								for(Aceite aceite : aceites)
								{
									autopartes.add(aceite);
								}
								//cargarAceites(aceites);
							}else if(rsAutopart.getString("tipoAutoparte")=="lampara")
							{	
								List<Lampara> lamparas=cargarLamparas();
								for(Lampara lampara : lamparas)
								{
									autopartes.add(lampara);
								}
								//Autoparte[Definiciones.LAMPARA_INDICE]=cargarLamparas();
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
				throw new MiException("[cargaAutopartes] EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[cargaAutopartes] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
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
		public List<Reparacion> cargaReparaciones() throws SQLException, MiException {
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
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
					
					if(rs.next())
					{
						System.out.print("\n[cargaReparaciones] HAY REPARACIONES ");		//DEBUG
						do {										
							Reparacion reparacion = new Reparacion();
							reparacion.setId(rs.getInt("reparacion_ID"));					
							reparacion.setCosto(rs.getDouble("costo"));
							reparacion.setEntregado(rs.getInt("entregado"));
							
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
								reparacion.setCliente(client);
								
							
							//######################AUTOPARTE###############					
							
							List<Autoparte> autopartes=new ArrayList<Autoparte>();
							Filtro filtro=new Filtro();
							Aceite aceite=new Aceite();
							Lampara lampara=new Lampara();
							
							query="SELECT * FROM 'ReparacionAutoparte' WHERE ReparacionAutoparte_ID='"+rs.getInt("reparacion_ID")+"'";
							ResultSet rsRepAuto=stmt.executeQuery(query);			
							conn.commit();
							int inAutopartes[] = new int[]{};
							int i=0;
							while(rsRepAuto.next())
							{
								inAutopartes[i]=rsRepAuto.getInt("autoparte_ID");
								i++;
							}
							if(inAutopartes==null)
							{}
													
								int indiceAutoPart=0;								
								while(indiceAutoPart<=i)
								{
									query="SELECT * FROM 'Autoparte' WHERE autoparte_ID='"+inAutopartes[indiceAutoPart]+"'";
									ResultSet rsAutopart=stmt.executeQuery(query);			
									conn.commit();		
									
									if(rsAutopart.getString("tipoAutoparte")==Definiciones.FILTRO_STRING)							{
										filtro=sqlselects.buscarFiltroPorIdAutoParte(inAutopartes[indiceAutoPart]);
										if(filtro!=null)
										{
											filtro.setMarca(rsAutopart.getString("marca"));
											filtro.setModelo(rsAutopart.getString("modelo"));
											autopartes.add(filtro);											
											
											
										}
								
									}else if(rsAutopart.getString("tipoAutoparte")==Definiciones.ACEITE_STRING)
									{
										aceite=sqlselects.buscarAceitePorIdAutoParte(inAutopartes[indiceAutoPart]);
										if(aceite!=null)
										{
											aceite.setMarca(rsAutopart.getString("marca"));
											aceite.setModelo(rsAutopart.getString("modelo"));
											autopartes.add(aceite);											
										}
										
									}else if(rsAutopart.getString("tipoAutoparte")==Definiciones.LAMPARA_STRING)
									{
										lampara=sqlselects.buscarLamparaPorIdAutoParte(inAutopartes[indiceAutoPart]);
										if(lampara!=null)
										{
											autopartes.add(lampara);
											lampara.setMarca(rsAutopart.getString("marca"));
											lampara.setModelo(rsAutopart.getString("modelo"));											
										}	
									}else
									{}
									indiceAutoPart++;
								}
								reparacion.setAutopartes(autopartes);
								
								x++;
						}while(rs.next());
					}else
					{	
						System.out.print("\n[cargaReparaciones]NO HAY REPARACIONES CARGADOS : ");		//DEBUG
					}
					
					
								
				}catch(SQLException e)
				{
					System.out.print("\n[cargaReparaciones] SQL Exception al cargar: "+e);		//DEBUG
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
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
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
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
						filtro.setTamaño(rs.getString("tamaño"));
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
				//throw new MiException("[Login] SQL Exception: "+e);
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
						aceite.setAceite_ID(rs.getInt("filtro_ID"));					
						aceite.setAutoparteID(rs.getInt("autoparte_ID"));
						aceite.setCantidadlitros(rs.getInt("litros"));
						aceite.setTipoAceite(rs.getString("tipo"));
												
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
						lampara.setLampara_ID(rs.getInt("filtro_ID"));					
						lampara.setAutoparteID(rs.getInt("autoparte_ID"));
						lampara.setColor(rs.getString("color"));
						lampara.setTamaño(rs.getString("tamaño"));
						
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
