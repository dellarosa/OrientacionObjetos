package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Definiciones;
import utils.MiException;

public class SQLClass {

	public SQLClass()
	{
		
	}
	
	public static void crearTablas() throws MiException, SQLException
	{
		String query;
		Connection conn=null;
		Statement stmt = null;
		try
		{
			Class.forName(Definiciones.DB_DRIVER);
			conn=getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		
			try
			{
			
				//########################## USUARIOS #################################
				query="CREATE TABLE Usuarios(usuario_ID int NOT NULL PRIMARY KEY,name varchar(40) NOT NULL,mail varchar(40) NOT NULL,user varchar(12) NOT NULL,pass varchar(8) NOT NULL,logueado char NOT NULL)";									
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				
				query="INSERT INTO Usuarios (usuario_ID,name,mail,user,pass) VALUES (1,'Pepe','pepearrobagmail.com','admin','admin')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[main] "+query);
				
				//########################## AUTOPARTES #################################
				query="CREATE TABLE Autopartes(autoparte_ID int NOT NULL PRIMARY KEY,indiceTipo int NOT NULL,tipoAutoparte varchar(40) NOT NULL,descripcion varchar(100),marca varchar(25) NOT NULL,modelo varchar(25) NOT NULL,costo float NOT NULL,tamaño varchar(20) NOT NULL, cantidadDisponible int,cantidadMinima int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				//########################## CLIENTE #################################
				query="CREATE TABLE Cliente(cliente_ID int NOT NULL PRIMARY KEY,nombre varchar(40) NOT NULL,modeloAuto varchar(40) NOT NULL,mail varchar(40) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				
				//########################## REPARACIONES AUTOPARTES #################################
				query="CREATE TABLE ReparacionAutopartes(reparacionAutopartes_ID int NOT NULL PRIMARY KEY,reparacion_ID int NOT NULL ,autoparte_ID int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				
				query="CREATE INDEX reparaciones_ID ON ReparacionAutopartes(reparacion_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				
				query="CREATE INDEX autoparte_ID ON ReparacionAutopartes(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				//########################## REPARACIONES #################################
				query="CREATE TABLE Reparaciones(reparacion_ID int NOT NULL PRIMARY KEY,reparacionAutopartes_ID int NOT NULL ,cliente_ID int NOT NULL,costo float NOT NULL,fechaInicio varchar(12) NOT NULL, fechaEntrega varchar(12) NOT NULL,entregado CHAR NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				
				query="CREATE INDEX reparacionAutopartes_ID ON Reparaciones(reparacionAutopartes_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[main] "+query);
				
				query="CREATE INDEX cliente_ID ON Reparaciones(cliente_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();			
				System.out.print("\n[main] "+query);		
			}catch(SQLException e)
			{
				conn.rollback();	
			}
			
		}catch(SQLException e)
		{
			throw new MiException("SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
			
			throw new MiException("ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD			
		}
		
	}
	
	public static Connection getConnection() throws MiException
	{
		try
		{
			Connection c=DriverManager.getConnection(Definiciones.DB_URL, Definiciones.DB_USERNAME, Definiciones.DB_PASSWORD);
			return c;
		}catch(SQLException e)
		{
			throw new MiException("[getConnection]SQL Exception: "+e);
		}
	}
}
