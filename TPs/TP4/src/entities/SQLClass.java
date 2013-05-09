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
			
			conn=getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		
			try
			{
			
				//########################## USUARIOS #################################
				query="CREATE TABLE Usuarios(usuario_ID int NOT NULL PRIMARY KEY,name varchar(40) NOT NULL,mail varchar(40) NOT NULL,user varchar(12) NOT NULL,pass varchar(8) NOT NULL,logueado char NOT NULL,jerarquian varchar(15) NOT NULL)";									
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="INSERT INTO Usuarios (usuario_ID,name,mail,user,pass) VALUES (1,'Pepe','pepearrobagmail.com','admin','admin')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				//########################## AUTOPARTES #################################
				//query="CREATE TABLE Autopartes(autoparte_ID int NOT NULL PRIMARY KEY,indiceTipoAutoparte int NOT NULL,TipoAutoparte varchar(30),descripcion varchar(100),marca varchar(25) NOT NULL,modelo varchar(25) NOT NULL,costo float NOT NULL,cantidadDisponible int)
				query="CREATE TABLE Autoparte(autoparte_ID int NOT NULL PRIMARY KEY,tipoAutoparte varchar(50),marca varchar(25) NOT NULL,modelo varchar(25) NOT NULL,costo float NOT NULL,cantidadDisponible int)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				//query="INSERT INTO Autopartes (autoparte_ID,indiceTipo,tipoAutoparte,descripcion,marca,modelo,costo,tamaño) VALUES (1,1,1,'filtro aire','filtros para autos','Filtros2000','F2000',30,'grande',4)";
				query="INSERT INTO Autoparte (autoparte_ID,tipoAutoparte,marca,modelo,costo,cantidadDisponible) VALUES (1,'filtro','Filtros2000','F2000',30,4)";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				
				//########################## FILTROS #################################
				query="CREATE TABLE Filtro (filtro_ID int NOT NULL PRIMARY KEY, autoparte_ID,tamaño varchar(20) NOT NULL, material varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON Autoparte(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);

				query="INSERT INTO Filtro (filtro_ID,autoparte_ID,tamaño,material) VALUES (1,1,'grande','plastico')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				//########################## ACEITES #################################
				query="CREATE TABLE Aceite (aceite_ID int NOT NULL PRIMARY KEY, autoparte_ID,litros varchar(20) NOT NULL, tipo varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON Autoparte(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);

				query="INSERT INTO Aceite (aceite_ID,autoparte_ID,litros,tipo) VALUES (1,1,'4','sintetico')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[SQLClass] "+query);

				//########################## LAMPARAS #################################
				query="CREATE TABLE Lampara (lampara_ID int NOT NULL PRIMARY KEY, autoparte_ID,luminosidad varchar(20) NOT NULL, tamaño varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON Autoparte(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);

				query="INSERT INTO Aceite (lampara_ID,autoparte_ID,luminosidad,tamaño) VALUES (1,1,'130','mediana')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				
				//########################## CLIENTE #################################
				query="CREATE TABLE Cliente(cliente_ID int NOT NULL PRIMARY KEY,nombre varchar(40) NOT NULL,auto varchar(40) NOT NULL,mail varchar(40) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				//########################## REPARACIONES AUTOPARTES #################################
				query="CREATE TABLE ReparacionAutopartes(reparacionAutopartes_ID int NOT NULL PRIMARY KEY,reparacion_ID int NOT NULL ,autoparte_ID int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX reparaciones_ID ON ReparacionAutopartes(reparacion_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON ReparacionAutopartes(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				//########################## REPARACIONES #################################
				query="CREATE TABLE Reparacion(reparacion_ID int NOT NULL PRIMARY KEY,reparacionAutopartes_ID int NOT NULL ,cliente_ID int NOT NULL,costo double NOT NULL,fechaInicio varchar(12) NOT NULL, fechaEntrega varchar(12) NOT NULL,entregado int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX reparacionAutopartes_ID ON Reparaciones(reparacionAutopartes_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX cliente_ID ON Reparaciones(cliente_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();			
				System.out.print("\n[SQLClass] "+query);		
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
