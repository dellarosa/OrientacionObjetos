package main;

import handler.Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import utils.MiException;

public class MiFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MiFrame(Handler handler) {
		setSize(600, 600);
        setTitle("TALLER MECANICO 2013");
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addMenuBar(handler);
	}
	
	private void addMenuBar(final Handler handler) {
		JMenuBar menuBar=new JMenuBar();
		JMenu menuUsuario=new JMenu("Usuario");
		
		//////////CREAR USUARIO /////////////
		JMenuItem itemCrearUsuario=new JMenuItem("Crear Usuario");
		itemCrearUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new UsuarioPanelAlta(handler));
			}
		});			
		menuUsuario.add(itemCrearUsuario);
		
		//////////MODIFICAR USUARIO /////////////
		JMenuItem itemModificarUsuario=new JMenuItem("Modificar Usuario");
		itemModificarUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new UsuarioPanelModificacion(handler));
			}
		});			
		menuUsuario.add(itemModificarUsuario);
			
		//////////BAJA USUARIO /////////////
		JMenuItem itemBajaUsuario=new JMenuItem("Baja Usuario");
		itemBajaUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new UsuarioPanelBaja(handler));
			}
		});			
		menuUsuario.add(itemBajaUsuario);
				
		//////////MOSTRAR USUARIO /////////////
		JMenuItem itemMostrarUsuario=new JMenuItem("Mostrar Usuarios");
		itemMostrarUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new UsuarioPanelMostrar(handler));
			}
		});			
		menuUsuario.add(itemMostrarUsuario);
		
		menuBar.add(menuUsuario);
		
		setJMenuBar(menuBar);
	}

	public void switchPanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().validate();
	}	
}
