package main;

import java.sql.Connection;
import utils.Definiciones;
import utils.Dentre;
import utils.MiException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import main.Menu;;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		try
		{
			String query=null;
			
			Class.forName(Definiciones.DB_DRIVER);
			Connection conn = DriverManager.getConnection(Definiciones.DB_URL, Definiciones.DB_USERNAME, Definiciones.DB_PASSWORD);
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			
			Menu menu= new Menu();
			Menu.MenuPrincipal menuprinc=menu.getMenuPrincipal();
			menuprinc.empezarMenu(stmt,conn);
			
			/*query="DROP jdbc:hsqldb:file:C:\\Android3\\P1\\UP\\filedb";			
			System.out.print("[main] DROP DB: "+query);
			stmt.executeUpdate(query);
			conn.commit();
			*/
			
			/////////////////////////////
			
			
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
			System.exit(0);
		}catch(SQLException e)
		{
			e.printStackTrace();
			throw e;
		}
		catch(MiException e)
		{
			//throw new MiException("ERROR ",e);
			e=new MiException("Error",e);
			e.getMsg();
			throw e;
		}
	}


}
