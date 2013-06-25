package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

////################################################# GET CONNECTION ##################################################################
	public static Connection getConnection() throws MiException
	{
		try
		{
			Class.forName(Definiciones.DB_DRIVER);
			Connection c=DriverManager.getConnection(Definiciones.DB_URL, Definiciones.DB_USERNAME, Definiciones.DB_PASSWORD);
			return c;
		}catch(Exception  e)
		{
			throw new MiException("[getConnection]SQL Exception: "+e);
		}
	}
}
