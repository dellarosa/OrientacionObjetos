package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;
import entities.SQLClass;
import entities.Usuario;

public class MetodosGrl {

	public Usuario obtenerUsuarioActual()
	{
		Usuario usuario = new Usuario();
		return usuario;
	}
	public Cliente obtenerCliente()
	{
		Cliente cliente = new Cliente();
		
		return cliente;
	}
	public Reparacion obtenerReparacion()
	{
		Reparacion reparacion = new Reparacion();
		
		return reparacion;
	}
	public Autoparte[] obtenerAutopartesEnSistema()
	{
		Autoparte[] autoparte = null;
		
		return autoparte;
	}
	public double obtenerCostoAutopartes(Autoparte[] autopartes)
	{
		return 1.22;
	}

	
	public Usuario loginUser(String user, String pass) throws MiException, SQLException
	{		
		Usuario usuario=new Usuario();
		String query;
		boolean login=false;
		Connection conn=null;
		Statement stmt=null;
		try
		{			
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Usuarios WHERE user='"+user+"'AND pass='"+pass+"'";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {										
					login=true;//LOGUEADO
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
					login=false;
				}			
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				login=false;
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

	public Usuario buscarUsuarioPorApodo(String userToFind) throws SQLException, MiException
	{
		Usuario usuario=new Usuario();
		String query;
		boolean login=false;		
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
					login=true;//LOGUEADO
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
					login=false;
				}			
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				login=false;
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
	public boolean crearUsuario(Usuario user) throws SQLException,MiException {
		
		boolean created=false;
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
				query="INSERT INTO Usuario(usuario_ID,name,mail,user,pass,logueado,jerarquia) VALUES ("+user.getId()+","+user.getName()+
						","+user.getEmail()+","+user.getUsername()+","+user.getPassword()+","+"N,"+user.getJerarquia()+")";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[main] "+query);
				created=true;
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				created=false;
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
		return created;
	}
	
	public boolean eliminarUsuario(Usuario user) throws SQLException,MiException {
		
		boolean deleted=false;
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
				query="DELETE FROM Usuario WHERE user='"+user.getUsername()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[main] "+query);
				deleted=true;
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				deleted=false;
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
		return deleted;
	}
	//####################################################################################################
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
					int w=0,y=0,z=0;
					
					while(rsAutopart.next())
					{
						if(rsAutopart.getString("tipoAutoparte")=="filtro")
						{
							cargarFiltros(filtros);
							Autoparte[Definiciones.FILTRO_INDICE]=filtros;
							
						}else if(rsAutopart.getString("tipoAutoparte")=="aceite")
						{
							//cargarAceites(aceites);
						}else if(rsAutopart.getString("tipoAutoparte")=="lampara")
						{
							//cargarLamparas(lamparas);
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
							
							if(rsAutopart.getString("tipoAutoparte")=="filtro")							{
								filtro=buscarFiltroPorIdAutoParte(inAutopartes[indiceAutoPart]);
								if(filtro!=null)
								{
									autopartes[Definiciones.FILTRO_INDICE][w]=filtro;
									autopartes[Definiciones.FILTRO_INDICE][w].setMarca(rsAutopart.getString("marca"));
									autopartes[Definiciones.FILTRO_INDICE][w].setModelo(rsAutopart.getString("modelo"));
									w++;
								}
						
							}else if(rsAutopart.getString("tipoAutoparte")=="aceite")
							{
								aceite=buscarAceitePorIdAutoParte(inAutopartes[indiceAutoPart]);
								if(aceite!=null)
								{
									autopartes[Definiciones.ACEITE_INDICE][y]=aceite;
									autopartes[Definiciones.ACEITE_INDICE][y].setMarca(rsAutopart.getString("marca"));
									autopartes[Definiciones.ACEITE_INDICE][y].setModelo(rsAutopart.getString("modelo"));
									y++;
								}
								
							}else if(rsAutopart.getString("tipoAutoparte")=="lampara")
							{
								lampara=buscarLamparaPorIdAutoParte(inAutopartes[indiceAutoPart]);
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
				}else
				{	
					System.out.print("\n[cargaFiltros]NO HAY Filtros CARGADOS : ");		//DEBUG
				}
				do {										
					
					filtros[x].setId(rs.getInt("filtro_ID"));					
					filtros[x].setAutoparteID(rs.getInt("autoparte_ID"));
					filtros[x].setMaterial(rs.getString("material"));
					filtros[x].setTamaño(rs.getString("tamaño"));
					
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
				}else
				{	
					System.out.print("\n[cargarAceites]NO HAY Aceite CARGADOS : ");		//DEBUG
				}
				do {										
					
					aceites[x].setId(rs.getInt("filtro_ID"));					
					aceites[x].setAutoparteID(rs.getInt("autoparte_ID"));
					aceites[x].setCantidadlitros(rs.getInt("litros"));
					aceites[x].setTipoAceite(rs.getString("tipo"));
					
				}while(rs.next());
							
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
					System.out.print("\n[cargarLamparas] HAY Aceite ");		//DEBUG
				}else
				{	
					System.out.print("\n[cargarLamparas]NO HAY Aceite CARGADOS : ");		//DEBUG
				}
				do {										
					
					lamparas[x].setId(rs.getInt("filtro_ID"));					
					lamparas[x].setAutoparteID(rs.getInt("autoparte_ID"));
					lamparas[x].setColor(rs.getString("color"));
					lamparas[x].setTamaño(rs.getString("tamaño"));
					
				}while(rs.next());
							
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
	//################################## BUSCAR ULTIMO ... ###################################################################
	public int buscarUltimoUsuario(Usuario[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	public int buscarUltimoCliente(Cliente[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	public int buscarUltimaReparacion(Reparacion[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	public int buscarUltimoIdFiltro(Filtro[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x+1;
	}
	public int buscarUltimoIdAceite(Aceite[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	public int buscarUltimoIdLampara(Lampara[] obj)
	{
		int x=0;
		while(obj[x]!=null)
		{
			x++;
		}
		return x;
	}
	public int buscarUltimaAutoparte(Autoparte[][] obj)
	{
		int x=0;
		int y=0;
		int c=0;
		while(x<obj[x].length)
		{	
			y=0;
			while(obj[x][y]!=null)
			{
				y++;
				c++;
			}
			x++;
		}
		return c+1;
	}
	
	
}
