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
		SQLInserts sqlinserts=new SQLInserts();
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

				sqlinserts.insertarAutoparte(1,"filtro","Filtros2000","F2000",30,4);
				sqlinserts.insertarAutoparte(2,"aceite","aceites2000","A2000",68,5);
				sqlinserts.insertarAutoparte(3,"lampara","lamparas","L2000",20,3);
				
				//########################## FILTROS #################################
				query="CREATE TABLE Filtro (filtro_ID int NOT NULL PRIMARY KEY, autoparte_ID,tamaño varchar(20) NOT NULL, material varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON Autoparte(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);

				sqlinserts.insertarFiltro(1,1,"grande","plastico");				
				
				//########################## ACEITES #################################
				query="CREATE TABLE Aceite (aceite_ID int NOT NULL PRIMARY KEY, autoparte_ID,litros varchar(20) NOT NULL, tipo varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON Autoparte(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);

				sqlinserts.insertarAceite(1,2,4,"sintetico");
			

				//########################## LAMPARAS #################################
				query="CREATE TABLE Lampara (lampara_ID int NOT NULL PRIMARY KEY, autoparte_ID,color varchar(20) NOT NULL, tamaño varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON Autoparte(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
					
				sqlinserts.insertarLampara(1,3,"blanca","mediana");		
				
				
				
				//########################## CLIENTE #################################
				query="CREATE TABLE Cliente(cliente_ID int NOT NULL PRIMARY KEY,nombre varchar(40) NOT NULL,auto varchar(40) NOT NULL,mail varchar(40) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				sqlinserts.insertarCliente(1, "PEPe", "GOL POWER","pepearrobapepe.com");
				
				//########################## REPARACIONES AUTOPARTES #################################
				query="CREATE TABLE ReparacionAutoparte(reparacionAutoparte_ID int NOT NULL PRIMARY KEY,reparacion_ID int NOT NULL ,autoparte_ID int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX reparaciones_ID ON ReparacionAutopartes(reparacion_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX autoparte_ID ON ReparacionAutoparte(autoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				//########################## REPARACIONES #################################
				query="CREATE TABLE Reparacion(reparacion_ID int NOT NULL PRIMARY KEY,reparacionAutoparte_ID int NOT NULL ,cliente_ID int NOT NULL,costo double NOT NULL,fechaInicio varchar(12) NOT NULL, fechaEntrega varchar(12) NOT NULL,entregado int NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX reparacionAutoparte_ID ON Reparacion(reparacionAutoparte_ID)";							
				stmt.executeUpdate(query);			
				conn.commit();
				System.out.print("\n[SQLClass] "+query);
				
				query="CREATE INDEX cliente_ID ON Reparacion(cliente_ID)";							
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
	
////################################################# GET CONNECTION ##################################################################
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
