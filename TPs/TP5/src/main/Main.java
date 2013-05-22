package main;


import utils.MiException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import SQL.SQLCarga;
import SQL.SQLClass;
import entities.Usuario;
import main.Menu;

public class Main {
	
	
			
	public static void main(String[] args) throws Exception {
		
		final JFrame frame = new JFrame();
		
		frame.setSize(600, 600);
        frame.setTitle("TALLER MECANICO 2013");
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
                
		////////////////////
		
		SQLCarga sqlcarga=new SQLCarga();
		SQLClass.crearTablas();		//INICIAL
		
				
		Menu menu= new Menu();
		
		try
		{				
					
			Menu.MenuInicio menuInicio=menu.new MenuInicio(frame);
			menuInicio.mostrar();

				
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
