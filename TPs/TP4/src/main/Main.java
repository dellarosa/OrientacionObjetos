package main;

import java.sql.Connection;
import utils.Definiciones;
import utils.Dentre;
import utils.MetodosGrl;
import utils.MiException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Autoparte;
import entities.Cliente;
import entities.Reparacion;
import entities.SQLClass;
import entities.Usuario;
import main.Menu;;

public class Main {
	 
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		boolean login=false;	
		boolean salir=false;
		MetodosGrl metgral=new MetodosGrl();
		
		SQLClass.crearTablas();		//INICIAL
		
		Menu menu= new Menu();
		Menu.MenuPrincipal menuprinc=menu.getMenuPrincipal();
		Menu.MenuInicio menuInicio=menu.getMenuInicio();
		try
		{
			while(!salir)
			{
								
				Usuario[] usuarios=new Usuario[]{};
				
				metgral.cargarUsuarios(usuarios);
						
				
				Usuario usuarioLogueado=menuInicio.empezarMenuInicio(usuarios);
			
				if(usuarioLogueado==null)
				{
					salir=true;
					System.exit(0);
				}
				
				if(menuprinc.empezarMenu(usuarioLogueado)=="salir")
				{
					salir=true;
					System.exit(0);	
				}
				/*query="DROP jdbc:hsqldb:file:C:\\Android3\\P1\\UP\\filedb";			
				System.out.print("[main] DROP DB: "+query);
				stmt.executeUpdate(query);
				conn.commit();
				*/	
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
	
	
	

}
