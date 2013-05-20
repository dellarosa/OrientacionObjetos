package SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utils.MiException;

public class SQLCreateIndex {

	public boolean crearIndex(String index,String tabla,String indextabla) {
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
