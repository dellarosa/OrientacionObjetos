package SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utils.MiException;

public class SQLCreate {

	//############################## CREATE USUARIO #####################################################################################

	public boolean crearTablaUsuario() throws MiException, SQLException
	{
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
				query="CREATE TABLE Usuario(usuario_ID int NOT NULL PRIMARY KEY,name varchar(40) NOT NULL,mail varchar(40) NOT NULL,user varchar(12) NOT NULL,pass varchar(8) NOT NULL,logueado int NOT NULL,jerarquia varchar(20) NOT NULL)";									
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[crearTablaUsuario] "+query);				//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaUsuario] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
			}
		
			
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaUsuario]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaUsuario]ERROR AL CREAR TABLAS",e);
		}	
		return true;
	}
	
	//############################## CREATE TABLA CLIENTE #####################################################################################

	public boolean crearTablaCliente() throws MiException, SQLException
	{
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
				query="CREATE TABLE Cliente(cliente_ID int NOT NULL PRIMARY KEY,nombre varchar(40) NOT NULL,auto varchar(40) NOT NULL,mail varchar(40) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[crearTablaCliente] "+query);				//DEBUG
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaCliente] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
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
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	
	//############################## CREATE TABLA REPARACION #####################################################################################
	
	public boolean crearTablaReparacion() throws MiException, SQLException
	{
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
				query="CREATE TABLE Reparacion(reparacion_ID int NOT NULL PRIMARY KEY,reparacionAutoparte_ID int NOT NULL ,cliente_ID int NOT NULL,costo double NOT NULL,fechaInicio varchar(12) NOT NULL, fechaEntrega varchar(12) NOT NULL,entregado int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
								
				System.out.print("\n[crearTablaReparacion] "+query);			//DEBUG 
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaReparacion] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaReparacion]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaReparacion]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	
	//############################## CREATE TABLA REPARACION AUTOPARTES #####################################################################################
	
	public boolean crearTablaReparacionAutoparte() throws MiException, SQLException
	{
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
				query="CREATE TABLE ReparacionAutoparte(reparacionAutoparte_ID int NOT NULL PRIMARY KEY,reparacion_ID int NOT NULL ,autoparte_ID int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				
				System.out.print("\n[crearTablaReparacionAutoparte] "+query);			//DEBUG 
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaReparacionAutoparte] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaReparacionAutoparte]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaReparacionAutoparte]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}	
		return true;
	}
	

	//############################## CREATE TABLA AUTOPARTES #####################################################################################
	
	public boolean crearTablaAutoparte() throws MiException, SQLException
	{
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
				//query="CREATE TABLE Autopartes(autoparte_ID int NOT NULL PRIMARY KEY,indiceTipoAutoparte int NOT NULL,TipoAutoparte varchar(30),descripcion varchar(100),marca varchar(25) NOT NULL,modelo varchar(25) NOT NULL,costo float NOT NULL,cantidadDisponible int)						
				//query="INSERT INTO Autopartes (autoparte_ID,indiceTipo,tipoAutoparte,descripcion,marca,modelo,costo,tamaño) VALUES (1,1,1,'filtro aire','filtros para autos','Filtros2000','F2000',30,'grande',4)";
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
	
	//############################## CREATE TABLA FILTRO #####################################################################################
	
	public boolean crearTablaFiltro() throws MiException, SQLException
	{
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

				query="CREATE TABLE Filtro (filtro_ID int NOT NULL PRIMARY KEY, autoparte_ID int NOT NULL,tamaño varchar(20) NOT NULL, material varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				
				System.out.print("\n[crearTablaFiltro] "+query);			//DEBUG 
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaFiltro] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaFiltro]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaFiltro]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							
			conn.close();												
		}	
		return true;
	}
	
	//############################## CREATE TABLA ACIETE #####################################################################################
	
	public boolean crearTablaAceite() throws MiException, SQLException
	{
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
				query="CREATE TABLE Aceite (aceite_ID int NOT NULL PRIMARY KEY, autoparte_ID int,litros int NOT NULL, tipo varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				
				System.out.print("\n[crearTablaAceite] "+query);			//DEBUG 
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaAceite] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaAceite]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaAceite]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							
			conn.close();												
		}	
		return true;
	}
	
	//############################## CREATE TABLA LAMPARA #####################################################################################
	
	public boolean crearTablaLampara() throws MiException, SQLException
	{
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

				query="CREATE TABLE Lampara (lampara_ID int NOT NULL PRIMARY KEY, autoparte_ID int,color varchar(20) NOT NULL, tamaño varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
								
				System.out.print("\n[crearTablaLampara] "+query);			//DEBUG 
				
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaLampara] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaLampara]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaLampara]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							
			conn.close();												
		}	
		return true;
	}
	
}
