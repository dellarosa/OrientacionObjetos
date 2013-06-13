package main;


import utils.DBManager;
import utils.MiException;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import paneles.UsuarioPanelAlta;
import paneles.UsuarioPanelBaja;
import paneles.UsuarioPanelModificacion;
import paneles.UsuarioPanelMostrar;

import bo.GeneralBO;

import entities.Usuario;

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
			//Menu menu=new Menu();
			//menu.startMenu(frame);
			
			//#############################################			
				
			UsuarioPanelAlta usuarioPanelAlta=new UsuarioPanelAlta(frame);
			
			//UsuarioPanelModificacion usuarioPanelModificacion=new UsuarioPanelModificacion(frame);
			
			//UsuarioPanelBaja usuarioPanelBaja=new UsuarioPanelBaja(frame);
			
			//UsuarioPanelMostrar usuarioPanelMostrar=new UsuarioPanelMostrar(frame);
			
		}
		catch(MiException e)
		{
			System.out.print("\n[main]**ERROR MiException: "+e);
		}
		
	}
	
	
	

}
