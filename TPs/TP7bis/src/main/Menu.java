package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import paneles.UsuarioPanelAlta;
import paneles.UsuarioPanelBaja;
import paneles.UsuarioPanelModificacion;
import paneles.UsuarioPanelMostrar;

public class Menu {
	public Menu()
	{
		
	}
	public void startMenu(final JFrame frame)
	{
		final JMenuBar menuBar=new JMenuBar();
		final JPanel panel=new JPanel(new BorderLayout());
		
		
		//####################################### USUARIO ##################################
		JMenu menuUsuario=new JMenu("Usuario");
		
		//////////CREAR USUARIO /////////////
		JMenuItem itemCrearUsuario=new JMenuItem("Crear Usuario");
		itemCrearUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UsuarioPanelAlta usuarioPanelAlta=new UsuarioPanelAlta(frame);						
				panel.add(BorderLayout.CENTER,usuarioPanelAlta);
				panel.add(menuBar,BorderLayout.NORTH);
				
				frame.removeAll();
				frame.repaint();
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});			
		menuUsuario.add(itemCrearUsuario);
		
		//////////MODIFICAR USUARIO /////////////
		JMenuItem itemModificarUsuario=new JMenuItem("Modificar Usuario");
		itemModificarUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.removeAll();
				frame.repaint();
				
				UsuarioPanelModificacion usuarioPanelModificacion=new UsuarioPanelModificacion(frame);				
				panel.add(BorderLayout.CENTER,usuarioPanelModificacion);
				panel.add(menuBar,BorderLayout.NORTH);
				
				frame.removeAll();
				frame.repaint();
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});			
		menuUsuario.add(itemModificarUsuario);
			
		//////////BAJA USUARIO /////////////
		JMenuItem itemBajaUsuario=new JMenuItem("Baja Usuario");
		itemBajaUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.removeAll();
				frame.repaint();
				
				UsuarioPanelBaja usuarioPanelBaja=new UsuarioPanelBaja(frame);
				panel.add(BorderLayout.CENTER,usuarioPanelBaja);
				panel.add(menuBar,BorderLayout.NORTH);					
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});			
		menuUsuario.add(itemBajaUsuario);
				
		//////////MOSTRAR USUARIO /////////////
		JMenuItem itemMostrarUsuario=new JMenuItem("Mostrar Usuarios");
		itemMostrarUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.removeAll();
				frame.repaint();					
				
				UsuarioPanelMostrar usuarioPanelMostrar=new UsuarioPanelMostrar(frame);
				panel.add(BorderLayout.CENTER,usuarioPanelMostrar);
				panel.add(menuBar,BorderLayout.NORTH);					
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});			
		menuUsuario.add(itemMostrarUsuario);
		
		menuBar.add(menuUsuario);
		
		panel.add(menuBar,BorderLayout.NORTH);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
