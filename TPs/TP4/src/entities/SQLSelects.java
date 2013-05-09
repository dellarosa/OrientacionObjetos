package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.MiException;

public class SQLSelects {

	//################################### BUSCAR POR ID AUTOPARTE ##################################################################
			public Filtro buscarFiltroPorIdAutoParte(int id) throws SQLException,MiException
			{
				String query;
				Connection conn=null;
				Statement stmt=null;
				Filtro filtro=new Filtro();
				
				try
				{			
					conn = SQLClass.getConnection();
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					try
					{				
						query="SELECT * FROM Filtro WHERE filtro_ID="+id;
						ResultSet rs=stmt.executeQuery(query);			
						conn.commit();
						
						if(rs.next())
						{
							System.out.print("\n[buscarFiltroPorId] HAY Filtro ");		//DEBUG
						}else
						{	
							filtro=null;
							System.out.print("\n[buscarFiltroPorId]NO HAY Filtros CARGADOS : ");		//DEBUG
						}
						do {										
							
							filtro.setId(rs.getInt("filtro_ID"));					
							filtro.setAutoparteID(rs.getInt("autoparte_ID"));
							filtro.setMaterial(rs.getString("material"));
							filtro.setTamaño(rs.getString("tamaño"));
							
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
				return filtro;
			}
			
			public Aceite buscarAceitePorIdAutoParte(int id) throws SQLException,MiException
			{
				String query;
				Connection conn=null;
				Statement stmt=null;
				Aceite aceite=new Aceite();
				
				try
				{			
					conn = SQLClass.getConnection();
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					try
					{				
						query="SELECT * FROM Aceite WHERE aceite_ID="+id;
						ResultSet rs=stmt.executeQuery(query);			
						conn.commit();
						
						if(rs.next())
						{
							System.out.print("\n[buscarFiltroPorId] HAY aceite ");		//DEBUG
						}else
						{	
							aceite=null;
							System.out.print("\n[buscarFiltroPorId]NO HAY aceite CARGADOS : ");		//DEBUG
						}
						do {										
							
							aceite.setId(rs.getInt("aceite_ID"));					
							aceite.setAutoparteID(rs.getInt("autoparte_ID"));
							aceite.setCantidadlitros(rs.getInt("litros"));
							aceite.setTipoAceite(rs.getString("tipo"));
							
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
				return aceite;
			}
			public Lampara buscarLamparaPorIdAutoParte(int id) throws SQLException,MiException
			{
				String query;
				Connection conn=null;
				Statement stmt=null;
				Lampara lampara=new Lampara();
				
				try
				{			
					conn = SQLClass.getConnection();
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					try
					{				
						query="SELECT * FROM Lampara WHERE lampara_ID="+id;
						ResultSet rs=stmt.executeQuery(query);			
						conn.commit();
						
						if(rs.next())
						{
							System.out.print("\n[buscarFiltroPorId] HAY lampara ");		//DEBUG
						}else
						{	
							lampara=null;
							System.out.print("\n[buscarFiltroPorId]NO HAY lampara CARGADOS : ");		//DEBUG
						}
						do {										
							
							lampara.setId(rs.getInt("lampara_ID"));					
							lampara.setColor(rs.getString("color"));
							lampara.setTamaño(rs.getString("tamaño"));
							lampara.setAutoparteID(rs.getInt("autoparte_ID"));
							
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
				return lampara;
			}
			
	//################################### BUSCAR POR APODO#############################################################
			public Usuario buscarUsuarioPorApodo(String userToFind) throws SQLException, MiException
			{
				Usuario usuario=new Usuario();
				String query;					
				Connection conn=null;
				Statement stmt=null;
				
				try
				{			
					conn = SQLClass.getConnection();
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					try
					{				
						query="SELECT * FROM Usuarios WHERE user='"+userToFind+"'";
						ResultSet rs=stmt.executeQuery(query);			
						conn.commit();
						if(rs.next()) {										
							
							usuario.setName(rs.getString("name"));
							usuario.setEmail(rs.getString("mail"));
							usuario.setPassword(rs.getString("pass"));
							usuario.setUsername(rs.getString("user"));
							usuario.setId(rs.getInt("ususario_ID"));
							usuario.setJerarquia(rs.getString("jerarquia"));
							
							
						}else
						{	
							//System.out.print("\n[loginUser] NO HAY COINCIDENCIAS");	//DEBUG
							usuario=null;							
						}			
					}catch(SQLException e)
					{
						//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG						
						conn.rollback();
						//throw new MiException("[Login] SQL Exception: "+e);
					}						
				}catch(SQLException e)
				{
					throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
				}
				catch(Exception e)
				{
					throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
				}finally
				{
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD
				}
				return usuario;
			}
			
			public Cliente buscarClientePorApodo(String userToFind) throws SQLException, MiException
			{
				Cliente cliente=new Cliente();
				String query;						
				Connection conn=null;
				Statement stmt=null;
				
				try
				{			
					conn = SQLClass.getConnection();
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					try
					{				
						query="SELECT * FROM Usuarios WHERE user='"+userToFind+"'";
						ResultSet rs=stmt.executeQuery(query);			
						conn.commit();
						if(rs.next()) {									
							
							cliente.setAuto(rs.getString("auto"));
							cliente.setMail(rs.getString("mail"));
							cliente.setNombre(rs.getString("nombre"));							
							cliente.setId(rs.getInt("ususario_ID"));							
						}else
						{	
							//System.out.print("\n[loginUser] NO HAY COINCIDENCIAS");	//DEBUG
							cliente=null;
							
						}			
					}catch(SQLException e)
					{
						//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
						
						conn.rollback();
						//throw new MiException("[Login] SQL Exception: "+e);
					}						
				}catch(SQLException e)
				{
					throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
				}
				catch(Exception e)
				{
					throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
				}finally
				{
					stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
					conn.close();										//CIERRO BD
				}
				return cliente;
			}
}
