package main;

import java.sql.Connection;
import utils.Definiciones;
import utils.Dentre;
import utils.MiException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.SQLClass;
import main.Menu;;

public class Main {
	 
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		boolean login=false;		
		String user;
		String pass;
		boolean salir=false;
		try
		{	while(!salir) //GENERAL
			{
				SQLClass.crearTablas();		//INICIAL
				
				System.out.print("\n\nINGRESAR AL SISTEMA TALLER 2013 (\'salir\' para finalizar)");
				do
				{				
					user=Dentre.texto("\n INGRESE SU USUARIO: ");				
					if(user=="salir")
					{
						System.out.print("\n\nADIOS");
						System.exit(0);
					}
					pass=Dentre.texto("\nINGRESE SU CONTRASEÑA: ");				
					if(pass=="salir")
					{
						System.out.print("\n\nADIOS");
						System.exit(0);					
					}
					
					if(loginUser(user,pass)!=true)
					{
						System.out.print("\nUSUARIO O CONTRASEÑA INCORRECTA, VUELVA A INGRESAR");
						Thread.sleep(2000);
					}else
					{
						System.out.print("\nUSUARIO LOGUEADO");
						Thread.sleep(2000);
						login=true;
					}
					
				}while(!login);
					
				while(login)
				{
				
					Menu menu= new Menu();
					Menu.MenuPrincipal menuprinc=menu.getMenuPrincipal();
					String strresponse=menuprinc.empezarMenu();
					
					/*query="DROP jdbc:hsqldb:file:C:\\Android3\\P1\\UP\\filedb";			
					System.out.print("[main] DROP DB: "+query);
					stmt.executeUpdate(query);
					conn.commit();
					*/
					if(strresponse=="salir")
					{
						System.out.print("\n\nADIOS");
						salir=true;
						System.exit(0);
					}
					
				}
			}
		}catch(SQLException e)
		{
			System.out.print("\n**ERROR SQL: "+e);
		}
		catch(MiException e)
		{
			System.out.print("\n**ERROR MiException: "+e);
		}
		
	}
	
	
	public static boolean loginUser(String user, String pass) throws MiException, SQLException
	{		
		String query;
		boolean login=false;
		Connection conn=null;
		Statement stmt=null;
		try
		{
			Class.forName(Definiciones.DB_DRIVER);
			conn = SQLClass.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Usuarios WHERE user='"+user+"'AND pass='"+pass+"'";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {										
					login=true;//LOGUEADO
				}else
				{	
					//System.out.print("\n[loginUser] NO HAY COINCIDENCIAS");	//DEBUG
					login=false;
				}			
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				login=false;
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
		return login;
	}


}
