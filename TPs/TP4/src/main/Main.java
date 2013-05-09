package main;

import utils.MiException;
import java.sql.SQLException;

import entities.SQLCarga;
import entities.SQLClass;
import entities.Usuario;
import main.Menu;;

public class Main {
	 
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		boolean salir=false;
		SQLCarga sqlcarga=new SQLCarga();		
		
		SQLClass.crearTablas();		//INICIAL
		
		Menu menu= new Menu();
		Menu.MenuPrincipal menuprinc=menu.getMenuPrincipal();
		Menu.MenuInicio menuInicio=menu.getMenuInicio();
		try
		{
			while(!salir)
			{
								
				Usuario[] usuarios=new Usuario[]{};
				
				sqlcarga.cargarUsuarios(usuarios);
						
				
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
			System.out.print("\n[main]**ERROR SQL: "+e);
		}
		catch(MiException e)
		{
			System.out.print("\n[main]**ERROR MiException: "+e);
		}
		
	}
	
	
	

}
