package main;


import utils.DBManager;
import utils.MiException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import bo.GeneralBO;

import dao.GeneralDAO;
import daoImplementaciones.GeneralDAO_SQL_Impl;
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
		
        GeneralBO generalBo=new GeneralBO();
        generalBo.crearTablas();		//INICIAL
		
		try
		{				
			Swing swing =new Swing();
			swing.pantallaPrincipal(frame);
		}
		catch(MiException e)
		{
			System.out.print("\n[main]**ERROR MiException: "+e);
		}
		
	}
	
	
	

}
