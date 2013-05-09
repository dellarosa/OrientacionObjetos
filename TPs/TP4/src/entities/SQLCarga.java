package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Definiciones;
import utils.MiException;

public class SQLCarga {

	SQLSelects sqlselects=new SQLSelects();
	
	//################################### CARGA ################################################################
		public void cargaClientes(Cliente[] clientes) throws SQLException,MiException {
			
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
			
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
					}else
					{	
						System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
					}
					do {										
						
						clientes[x].setNombre(rs.getString("nombre"));
						clientes[x].setId(rs.getInt("usuario_ID"));
						clientes[x].setAuto(rs.getString("auto"));
						clientes[x].setMail(rs.getString("mail"));
						
						
					}while(rs.next());
								
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
		}
		public void cargaAutopartes(Autoparte[][] autopartes) throws SQLException, MiException {
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
			
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
						
						Autoparte[][] Autoparte=new Autoparte[][]{};
						Filtro[] filtros=new Filtro[]{};
						Aceite[] aceites=new Aceite[]{};
						Lampara[] lamparas=new Lampara[]{};
						
												//PODRIA GUARDAR AL BANBAN y usar instanceof pero como es un sistema chico, uso matriz para que quede mas ordenado.
						while(rsAutopart.next())
						{
							if(rsAutopart.getString("tipoAutoparte")=="filtro")
							{
								cargarFiltros(filtros);									
								Autoparte[Definiciones.FILTRO_INDICE]=filtros;
								
							}else if(rsAutopart.getString("tipoAutoparte")=="aceite")
							{
								cargarAceites(aceites);
								Autoparte[Definiciones.ACEITE_INDICE]=aceites;
								//cargarAceites(aceites);
							}else if(rsAutopart.getString("tipoAutoparte")=="lampara")
							{
								cargarLamparas(lamparas);
								Autoparte[Definiciones.LAMPARA_INDICE]=lamparas;
							}else
							{}
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
			
		}
		public void cargarUsuarios(Usuario[] usuarios) throws SQLException,MiException {
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
			
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
						System.out.print("\n[cargaClientes] HAY CLIENTES ");		//DEBUG
					}else
					{	
						System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
					}
					do {										
						
						usuarios[x].setName(rs.getString("nombre"));
						usuarios[x].setId(rs.getInt("usuario_ID"));
						usuarios[x].setEmail(rs.getString("mail"));
						usuarios[x].setJerarquia(rs.getString("jerarquia"));
						usuarios[x].setPassword(rs.getString("pass"));
						usuarios[x].setUsername(rs.getString("user"));
						
						
					}while(rs.next());
								
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
			
		}
		public void cargaReparaciones(Reparacion[] reparaciones) throws SQLException, MiException {
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
			
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
						System.out.print("\n[cargaClientes] HAY CLIENTES ");		//DEBUG
					}else
					{	
						System.out.print("\n[cargaClientes]NO HAY CLIENTES CARGADOS : ");		//DEBUG
					}
					do {										
						
						reparaciones[x].setId(rs.getInt("reparacion_ID"));					
						reparaciones[x].setCosto(rs.getDouble("costo"));
						reparaciones[x].setEntregado(rs.getInt("entregado"));
						
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
							reparaciones[x].setCliente(client);
							
						
						//######################AUTOPARTE###############					
						
						Autoparte[][] autopartes=new Autoparte[][]{};
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
							int w=0,y=0,z=0;
							while(indiceAutoPart<=i)
							{
								query="SELECT * FROM 'Autoparte' WHERE autoparte_ID='"+inAutopartes[indiceAutoPart]+"'";
								ResultSet rsAutopart=stmt.executeQuery(query);			
								conn.commit();		
								
								if(rsAutopart.getString("tipoAutoparte")==Definiciones.FILTRO_STRING)							{
									filtro=sqlselects.buscarFiltroPorIdAutoParte(inAutopartes[indiceAutoPart]);
									if(filtro!=null)
									{
										autopartes[Definiciones.FILTRO_INDICE][w]=filtro;
										autopartes[Definiciones.FILTRO_INDICE][w].setMarca(rsAutopart.getString("marca"));
										autopartes[Definiciones.FILTRO_INDICE][w].setModelo(rsAutopart.getString("modelo"));
										w++;
									}
							
								}else if(rsAutopart.getString("tipoAutoparte")==Definiciones.ACEITE_STRING)
								{
									aceite=sqlselects.buscarAceitePorIdAutoParte(inAutopartes[indiceAutoPart]);
									if(aceite!=null)
									{
										autopartes[Definiciones.ACEITE_INDICE][y]=aceite;
										autopartes[Definiciones.ACEITE_INDICE][y].setMarca(rsAutopart.getString("marca"));
										autopartes[Definiciones.ACEITE_INDICE][y].setModelo(rsAutopart.getString("modelo"));
										y++;
									}
									
								}else if(rsAutopart.getString("tipoAutoparte")==Definiciones.LAMPARA_STRING)
								{
									lampara=sqlselects.buscarLamparaPorIdAutoParte(inAutopartes[indiceAutoPart]);
									if(lampara!=null)
									{
										autopartes[Definiciones.LAMPARA_INDICE][z]=lampara;
										autopartes[Definiciones.LAMPARA_INDICE][z].setMarca(rsAutopart.getString("marca"));
										autopartes[Definiciones.LAMPARA_INDICE][z].setModelo(rsAutopart.getString("modelo"));
										z++;
									}	
								}else
								{}
								indiceAutoPart++;
							}
							reparaciones[x].setAutopartes(autopartes);
							
					}while(rs.next());
					
								
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
			
		}
		
			
		//##################################### CARGAR FILTROS ACEITES LAMPARAS######################################################
		public void cargarFiltros(Filtro[] filtros) throws SQLException,MiException
		{
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
			
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
							
							filtros[x].setId(rs.getInt("filtro_ID"));					
							filtros[x].setAutoparteID(rs.getInt("autoparte_ID"));
							filtros[x].setMaterial(rs.getString("material"));
							filtros[x].setTamaño(rs.getString("tamaño"));
							
						}while(rs.next());
					}else
					{	
						System.out.print("\n[cargaFiltros]NO HAY Filtros CARGADOS : ");		//DEBUG
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
		}
		public void cargarAceites(Aceite[] aceites) throws SQLException,MiException
		{
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
			
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
							
							aceites[x].setId(rs.getInt("filtro_ID"));					
							aceites[x].setAutoparteID(rs.getInt("autoparte_ID"));
							aceites[x].setCantidadlitros(rs.getInt("litros"));
							aceites[x].setTipoAceite(rs.getString("tipo"));
							
						}while(rs.next());
					}else
					{	
						System.out.print("\n[cargarAceites]NO HAY Aceite CARGADOS : ");		//DEBUG
					}
					
								
				}catch(SQLException e)
				{
					//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[cargarAceites] EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[cargarAceites] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			}
		}
		public void cargarLamparas(Lampara[] lamparas) throws SQLException,MiException
		{
			String query;
			Connection conn=null;
			Statement stmt=null;
			int x=0;
			
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
							
							lamparas[x].setId(rs.getInt("filtro_ID"));					
							lamparas[x].setAutoparteID(rs.getInt("autoparte_ID"));
							lamparas[x].setColor(rs.getString("color"));
							lamparas[x].setTamaño(rs.getString("tamaño"));
							
						}while(rs.next());
						
					}else
					{	
						System.out.print("\n[cargarLamparas]NO HAY Lampara CARGADOS : ");		//DEBUG
					}
					
								
				}catch(SQLException e)
				{
					//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
					conn.rollback();
					//throw new MiException("[Login] SQL Exception: "+e);
				}						
			}catch(SQLException e)
			{
				throw new MiException("[cargarLamparas] EXCEPTION AL CONECTAR: "+e);
			}
			catch(Exception e)
			{
				throw new MiException("[cargarLamparas] EXCEPTION AL CONECTAR: "+e);
			}finally
			{
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD
			}
		}
}
