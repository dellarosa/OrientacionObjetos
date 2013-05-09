package entities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utils.MiException;

public class SQLDelete {

	public boolean eliminarUsuario(Usuario user) throws SQLException,MiException {
		
		boolean deleted=false;
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
				query="DELETE FROM Usuario WHERE user='"+user.getUsername()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[main] "+query);
				deleted=true;
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return deleted;
	}
	
	public boolean eliminarCliente(Cliente cliente) throws SQLException,MiException {
		
		boolean deleted=false;
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
				query="DELETE FROM Cliente WHERE user='"+cliente.getNombre()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[main] "+query);
				deleted=true;
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[login] EXCEPTION AL CONECTAR: "+e);
		}finally
		{
			stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
			conn.close();										//CIERRO BD
		}
		return deleted;
	}
}
