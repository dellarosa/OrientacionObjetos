package daoImplementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBManager;
import utils.MiException;

import dao.ClienteDAO;
import entities.Cliente;



public class ClienteDAOSQLImpl implements ClienteDAO{
	
	//############################## CREATE TABLA CLIENTE #####################################################################################
	public boolean crearTablaCliente() throws MiException
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
				query="CREATE TABLE Cliente(cliente_ID int NOT NULL PRIMARY KEY,nombre varchar(40) NOT NULL,auto varchar(40) NOT NULL,mail varchar(40) NOT NULL,patente varchar(40) NOT NULL)";				
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[crearTablaCliente] "+query);				//DEBUG
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaCliente] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();
				//throw new MiException("[crearTablaCliente] SQL Exception: "+e);
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaCliente]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaCliente]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}
		return true;
	}
	//################################### CARGA CLIENTES ################################################################
	public List<Cliente> cargaClientes() throws MiException {
		
		String query;
		Connection conn=null;
		Statement stmt=null;
		
		List<Cliente> clientes=new ArrayList<Cliente>();
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Cliente";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					//System.out.print("\n[cargaClientes] HAY CLIENTES ");		//DEBUG
					do {										
						Cliente cliente=new Cliente();
						cliente.setNombre(rs.getString("nombre"));
						cliente.setId(rs.getInt("cliente_ID"));
						cliente.setAuto(rs.getString("auto"));
						cliente.setMail(rs.getString("mail"));
						cliente.setPatente(rs.getString("patente"));
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
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}	
		return clientes;
	}
	
	
	

	//############################## DELETE CLIENTE ###################################################
	public boolean eliminarCliente(Cliente cliente) throws MiException {
		
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
				query="DELETE FROM Cliente WHERE nombre='"+cliente.getNombre()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[eliminarCliente] "+query);			//DEBUG
				deleted=true;
				
			}catch(SQLException e)
			{
				System.out.print("\n[eliminarCliente] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				//throw new MiException("[eliminarCliente] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[eliminarCliente] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[eliminarCliente] EXCEPTION AL CONECTAR: "+e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}
		return deleted;
	}
	

	//###################################### INSERTAR CLIENTE #############################################################################
	public boolean insertarCliente(int clienteID,String nombre,String auto,String mail,String patente) throws MiException
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
				query="INSERT INTO Cliente (cliente_ID,nombre,auto,mail,patente) VALUES ("+clienteID+",'"+nombre+"','"+auto+"','"+mail+"','"+patente+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarCliente] "+query);									//DEBUG
				
			}catch(SQLException e)
			{
				System.out.print("\n[insertarCliente] SQL Exception AL INSERTAR: "+e);					//DEBUG
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
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}
		return true;
	}
	
	public void insertarCliente(Cliente cliente) throws MiException
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
				query="INSERT INTO Cliente (cliente_ID,nombre,auto,mail,patente) VALUES ('"+cliente.getId()+"','"+cliente.getNombre()+"','"+cliente.getAuto()+"','"+cliente.getMail()+"','"+cliente.getPatente()+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarCliente] "+query);					//DEBUG
				
			}catch(SQLException e)
			{
				System.out.print("\n[insertarCliente] SQL Exception AL INSERTAR: "+e);					//DEBUG
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
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}
	}
	
	//###################################### MODIFICAR CLIENTE #############################################################################
	public boolean updateCliente(Cliente cliente) throws MiException {

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
					query="UPDATE Cliente SET cliente_ID="+cliente.getId()+",nombre='"+cliente.getNombre()+"',auto='"+cliente.getAuto()+"',mail='"+cliente.getMail()+"',patente='"+cliente.getPatente()+"' WHERE cliente_ID="+cliente.getId();
					stmt.executeUpdate(query);
					conn.commit();
					System.out.print("\n[updateCliente] "+query);					//DEBUG
					
				}catch(SQLException e)
				{
					System.out.print("\n[updateCliente] Exception SQL: "+e);					//DEBUG
					conn.rollback();					
				}
			
			}catch(SQLException e)
			{
				throw new MiException("[updateCliente]SQL Connection EXCEPTION "+e.getMessage());
			}catch(Exception e)
			{
			
				throw new MiException("[updateCliente]ERROR AL CREAR TABLAS",e);
			}
			finally
			{
				try {
					stmt.execute("SHUTDOWN");
					conn.close();
				} catch (SQLException e) {
					//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
					//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
				}						
				
			}
			return true;		
	}

	//###################################### BUSCAR CLIENTE POR APODO #############################################################################	
	public Cliente buscarClientePorApodo(String clientToFind) throws MiException
	{
		Cliente cliente=null;
		
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
				
				query="SELECT * FROM Cliente WHERE nombre='"+clientToFind+"'";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				//System.out.print("\n[buscarClientePorApodo] QUERY: "+query);	//DEBUG
				if(rs.next()) {									
					
					//System.out.print("\n[buscarClientePorApodo] LO ENCONTRE \n");	//DEBUG
					
					cliente=new Cliente();
					cliente.setAuto(rs.getString("auto"));
					cliente.setMail(rs.getString("mail"));
					cliente.setNombre(rs.getString("nombre"));							
					cliente.setId(rs.getInt("cliente_ID"));
					cliente.setPatente(rs.getString("patente"));
				}else
				{	
					//System.out.print("\n[buscarClientePorApodo] NO HAY COINCIDENCIAS");	//DEBUG					
					
				}
			}catch(SQLException e)
			{
				System.out.print("\n[buscarClientePorApodo] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				
				//throw new MiException("[buscarClientePorApodo] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}
		
		return cliente;
	}
	
	//##################################### BUSCAR ULTIMO CLIENTE ID ##########################################
	public int buscarUltimoClienteId() throws MiException {
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
				//query="SELECT MAX('usuario_ID') FROM Usuario";
				query="SELECT cliente_ID FROM Cliente ORDER BY cliente_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("cliente_ID");					
					inLastID++;
					//System.out.print("\n[buscarUltimoClienteId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					//System.out.print("\n[buscarUltimoClienteId] NO HAY COINCIDENCIAS");	//DEBUG
					//inLastID=-1;
					inLastID=1;
				}
				
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimoClienteId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				
				//throw new MiException("[buscarUltimoClienteId] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimoClienteId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimoClienteId] EXCEPTION AL CONECTAR: "+e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}
		return inLastID;
		
	}
	@Override
	public Cliente buscarClientePorPatente(String patente) throws MiException {

		Cliente cliente=null;
		
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
				
				query="SELECT * FROM Cliente WHERE patente='"+patente+"'";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				//System.out.print("\n[buscarClientePorApodo] QUERY: "+query);	//DEBUG
				if(rs.next()) {									
					
					//System.out.print("\n[buscarClientePorApodo] LO ENCONTRE \n");	//DEBUG
					
					cliente=new Cliente();
					cliente.setAuto(rs.getString("auto"));
					cliente.setMail(rs.getString("mail"));
					cliente.setNombre(rs.getString("nombre"));							
					cliente.setId(rs.getInt("cliente_ID"));
					cliente.setPatente(rs.getString("patente"));
				}else
				{	
					//System.out.print("\n[buscarClientePorApodo] NO HAY COINCIDENCIAS");	//DEBUG					
					
				}
			}catch(SQLException e)
			{
				System.out.print("\n[buscarClientePorApodo] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				
				//throw new MiException("[buscarClientePorApodo] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarClientePorApodo] EXCEPTION AL CONECTAR: "+e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				//throw new MiException("[crearTablaUsuario] Exception Finally: "+e);
				//System.out.print("\n[crearTablaUsuario] SQL Finally Exception: "+e);			//DEBUG
			}						
			
		}
		
		return cliente;
	}
}
