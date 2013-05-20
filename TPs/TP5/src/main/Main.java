package main;

import utils.Definiciones;
import utils.MiException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SQL.SQLCarga;
import SQL.SQLClass;

import entities.Usuario;
import main.Menu;;

public class Main {
	 
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		boolean salir=false;
		SQLCarga sqlcarga=new SQLCarga();
		SQLClass.crearTablas();		//INICIAL
		
		List<Usuario> usuariosL=new ArrayList<Usuario>();
		
		Menu menu= new Menu();
		Menu.MenuPrincipal menuprinc=menu.getMenuPrincipal();
		Menu.MenuInicio menuInicio=menu.getMenuInicio();
		try
		{
			while(!salir)
			{	
				
				usuariosL=sqlcarga.cargarUsuarios();		//Podria solo cargarlo una vez //TODO
						
				switch(menuInicio.menuTipoSistema())
				{
					case Definiciones.MODO_TECNICO:
						if(menuInicio.validarSenha())
						{
							menuInicio.empezarMenuTecnico(usuariosL);
						}else
						{
							
						}	
					break;
					case Definiciones.MODO_SISTEMA:
						Usuario usuarioLogueado=menuInicio.empezarLogginUsuario(usuariosL);
						
						if(usuarioLogueado==null)
						{							
						}else
						{
							if(menuprinc.empezarMenu(usuarioLogueado)=="salir")
							{							
							}
						}
					break;
					case Definiciones.MODO_SALIR:
						salir=true;
						System.exit(0);
					break;
					default:
						
						break;
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
