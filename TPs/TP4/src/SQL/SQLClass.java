package SQL;

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
	
////################################################# GET CONNECTION ##################################################################
	public static Connection getConnection() throws MiException
	{
		try
		{
			Class.forName(Definiciones.DB_DRIVER);
			Connection c=DriverManager.getConnection(Definiciones.DB_URL, Definiciones.DB_USERNAME, Definiciones.DB_PASSWORD);
			return c;
		}catch(SQLException | ClassNotFoundException e)
		{
			throw new MiException("[getConnection]SQL Exception: "+e);
		}
	}
	
////################################################# CREAR TABLAS ##################################################################
	public static void crearTablas() throws MiException, SQLException
	{
		SQLInserts sqlinserts=new SQLInserts();
		SQLCreate sqlcreate=new SQLCreate();
		SQLCreateIndex sqlCreateIndex=new SQLCreateIndex();
		try
		{
		
			//########################## USUARIOS #################################
			
			sqlcreate.crearTablaUsuario();
			sqlinserts.insertarUsuario(1,"pepe","pepearrobamail.com", "admin","admin",0,"administrador");
			
			//########################## AUTOPARTES #################################

			sqlcreate.crearTablaAutoparte();
			sqlinserts.insertarAutoparte(1,"filtro","Filtros2000","F2000",30,4);
			sqlinserts.insertarAutoparte(2,"aceite","aceites2000","A2000",68,5);
			sqlinserts.insertarAutoparte(3,"lampara","lamparas","L2000",20,3);
			
			//########################## FILTROS #################################
			sqlcreate.crearTablaFiltro();
			sqlCreateIndex.crearIndex("autoparteFiltro_ID","Filtro","autoparte_ID");
			sqlinserts.insertarFiltro(1,1,"grande","plastico");				
			
			//########################## ACEITES #################################
			
			sqlcreate.crearTablaAceite();
			sqlCreateIndex.crearIndex("autoparteAceite_ID","Aceite","autoparte_ID");								
			
			sqlinserts.insertarAceite(1,2,4,"sintetico");
		

			//########################## LAMPARAS #################################
			
			sqlcreate.crearTablaLampara();
			sqlCreateIndex.crearIndex("autoparteLampara_ID","Lampara","autoparte_ID");						
			sqlinserts.insertarLampara(1,3,"blanca","mediana");		
			
			//########################## CLIENTE #################################
			sqlcreate.crearTablaCliente();
			
			sqlinserts.insertarCliente(1, "PEPe", "GOL POWER","pepearrobapepe.com");
			
			//########################## REPARACIONES AUTOPARTES #################################
			sqlcreate.crearTablaReparacionAutoparte();
			sqlCreateIndex.crearIndex("reparaciones_ID","ReparacionAutoparte","reparacion_ID");
						
			sqlCreateIndex.crearIndex("autoparte_ID","ReparacionAutoparte","autoparte_ID");			

			//########################## REPARACIONES #################################
			sqlcreate.crearTablaReparacion();
			sqlCreateIndex.crearIndex("reparacionAutoparte_ID","Reparacion","reparacionAutoparte_ID");
			sqlCreateIndex.crearIndex("cliente_ID","Reparacion","cliente_ID");
							
			
		}catch(SQLException e)
		{
			System.out.print("\n[SQLClass] SQLException CREANDO TABLAS: "+e);		//DEBUG					
			throw new MiException("SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
				
			throw new MiException("ERROR AL CREAR TABLAS",e);
		}	
	}
}
