package daoImplementaciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import utils.DBManager;
import utils.MiException;
import dao.GeneralDAO;
import dao.UsuarioDAO;

public class GeneralDAOSQLImpl implements GeneralDAO 
{


	
////################################################# CREAR TABLAS ##################################################################
	public void crearTablas() throws MiException
	{
		
		
		try
		{
		
			//########################## USUARIOS #################################
			UsuarioDAO usuarioDao=new UsuarioDAOSQLImpl();
			usuarioDao.crearTablaUsuario();
			usuarioDao.insertarUsuario(1,"pepe","pepearrobamail.com", "admin","admin",0,"administrador");
			
			//########################## AUTOPARTES #################################
			
		}catch(MiException e)
		{
			System.out.print("\n[GENERAL_DAO][crearTablas] SQLException CREANDO TABLAS: "+e);		//DEBUG					
			throw new MiException("[crearTablas]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
				
			throw new MiException("[crearTablas]ERROR AL CREAR TABLAS",e);
		}	
	}
	//####################################### CREAR INDEX ############################################
	public boolean crearIndex(String index,String tabla,String indextabla) {
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
				query="CREATE INDEX "+index+" ON "+tabla+" ("+indextabla+")";							
				stmt.executeUpdate(query);			
				conn.commit();				
				System.out.print("\n[crearIndex] "+query);				//DEBUG
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();
				
			}catch(SQLException e)
			{	
				System.out.print("\n[crearIndex]SQLCrearIndex Exception: "+e);		//DEBUG
				conn.rollback();	
			}
		
			
		}catch(SQLException e)
		{
			throw new MiException("[crearIndex]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearIndex]ERROR AL CREAR INDEX",e);
		}	
		return true;
		
	}
}
