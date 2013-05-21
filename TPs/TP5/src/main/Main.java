package main;

import utils.Definiciones;
import utils.MetodosGrl;
import utils.MiException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import SQL.SQLCarga;
import SQL.SQLClass;

import entities.Usuario;
import main.Menu;
import main.Menu.MenuInicio;
import main.Menu.MenuTecnico;

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
		
		List<Usuario> usuariosL=new ArrayList<Usuario>();		
		Menu menu= new Menu();
		
		try
		{				
			usuariosL=sqlcarga.cargarUsuarios();		
			Menu.MenuInicio menuInicio=menu.new MenuInicio(frame,usuariosL);
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
