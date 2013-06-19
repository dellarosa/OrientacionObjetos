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

import paneles.AceitePanelAlta;
import paneles.AceitePanelBaja;
import paneles.AceitePanelModificacion;
import paneles.AceitePanelMostrar;
import paneles.ClientePanelAlta;
import paneles.ClientePanelBaja;
import paneles.ClientePanelModificacion;
import paneles.ClientePanelMostrar;
import paneles.FiltroPanelAlta;
import paneles.FiltroPanelBaja;
import paneles.FiltroPanelModificacion;
import paneles.FiltroPanelMostrar;
import paneles.LamparaPanelAlta;
import paneles.LamparaPanelBaja;
import paneles.LamparaPanelModificacion;
import paneles.LamparaPanelMostrar;
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
		JMenu menuCliente=new JMenu("Cliente");
		JMenu menuAutopartes=new JMenu("Autoparte");
		JMenu menuReparacion=new JMenu("Reparacion");
		
		////////#######################// USUARIO  #####################################///////////// 
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
		
		////////###############################// CLIENTE  #########################################/////////////
		//////////CREAR CLIENTE /////////////
		JMenuItem itemCrearCliente=new JMenuItem("Crear Cliente");
		itemCrearCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new ClientePanelAlta(handler));
			}
		});			
		menuCliente.add(itemCrearCliente);
		
		//////////MODIFICAR CLIENTE /////////////
		JMenuItem itemModificarCliente=new JMenuItem("Modificar Cliente");
		itemModificarCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new ClientePanelModificacion(handler));
			}
		});			
		menuCliente.add(itemModificarCliente);
			
		//////////BAJA CLIENTE /////////////
		JMenuItem itemBajaCliente=new JMenuItem("Baja Cliente");
		itemBajaCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new ClientePanelBaja(handler));
			}
		});			
		menuCliente.add(itemBajaCliente);
				
		//////////MOSTRAR CLIENTE /////////////
		JMenuItem itemMostrarCliente=new JMenuItem("Mostrar Clientes");
		itemMostrarCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new ClientePanelMostrar(handler));
			}
		});			
		menuCliente.add(itemMostrarCliente);
		
		menuBar.add(menuCliente);

		//////###############################// AUTOPARTES  #########################################/////////////
		JMenu menuFiltro=new JMenu("Filtro");
		JMenu menuAceite=new JMenu("Aceite");
		JMenu menuLampara=new JMenu("Lampara");		
		//##################################### FILTROS ########################################
		//////////CREAR FILTRO /////////////
		JMenuItem itemCrearFiltro=new JMenuItem("Crear Filtro");
		itemCrearFiltro.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new FiltroPanelAlta(handler));
			}
		});			
		menuFiltro.add(itemCrearFiltro);
		
		//////////MODIFICAR FILTRO /////////////
		JMenuItem itemModificarFiltro=new JMenuItem("Modificar Filtro");
		itemModificarFiltro.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new FiltroPanelModificacion(handler));
			}
		});			
		menuFiltro.add(itemModificarFiltro);
			
		//////////BAJA FILTRO /////////////
		JMenuItem itemBajaFiltro=new JMenuItem("Baja Filtro");
		itemBajaFiltro.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new FiltroPanelBaja(handler));
			}
		});			
		menuFiltro.add(itemBajaFiltro);
				
		//////////MOSTRAR FILTRO /////////////
		JMenuItem itemMostrarFiltro=new JMenuItem("Mostrar Filtros");
		itemMostrarFiltro.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new FiltroPanelMostrar(handler));
			}
		});			
		menuFiltro.add(itemMostrarFiltro);
		
		menuAutopartes.add(menuFiltro);
		
		//##################################### ACEITES ########################################
		//////////CREAR ACEITE /////////////
		JMenuItem itemCrearAceite=new JMenuItem("Crear Aceite");
		itemCrearAceite.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new AceitePanelAlta(handler));
			}
		});			
		menuAceite.add(itemCrearAceite);
		
		//////////MODIFICAR FILTRO /////////////
		JMenuItem itemModificarAceite=new JMenuItem("Modificar Aceite");
		itemModificarAceite.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new AceitePanelModificacion(handler));
			}
		});			
		menuAceite.add(itemModificarAceite);
			
		//////////BAJA FILTRO /////////////
		JMenuItem itemBajaAceite=new JMenuItem("Baja Aceite");
		itemBajaAceite.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new AceitePanelBaja(handler));
			}
		});			
		menuAceite.add(itemBajaAceite);
				
		//////////MOSTRAR FILTRO /////////////
		JMenuItem itemMostrarAceite=new JMenuItem("Mostrar Aceites");
		itemMostrarAceite.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new AceitePanelMostrar(handler));
			}
		});			
		menuAceite.add(itemMostrarAceite);
		
		menuAutopartes.add(menuAceite);
		
		//##################################### LAMPARAS ########################################
		//////////CREAR LAMPARA /////////////
		JMenuItem itemCrearLampara=new JMenuItem("Crear Lampara");
		itemCrearLampara.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new LamparaPanelAlta(handler));
			}
		});			
		menuLampara.add(itemCrearLampara);
		
		//////////MODIFICAR LAMPARA /////////////
		JMenuItem itemModificarLampara=new JMenuItem("Modificar Lampara");
		itemModificarLampara.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					MiFrame.this.switchPanel(new LamparaPanelModificacion(handler));
			}
		});			
		menuLampara.add(itemModificarLampara);
			
		//////////BAJA LAMPARA /////////////
		JMenuItem itemBajaLampara=new JMenuItem("Baja Lampara");
		itemBajaLampara.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new LamparaPanelBaja(handler));
			}
		});			
		menuLampara.add(itemBajaLampara);
				
		//////////MOSTRAR LAMPARA /////////////
		JMenuItem itemMostrarLampara=new JMenuItem("Mostrar Lamparas");
		itemMostrarLampara.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiFrame.this.switchPanel(new LamparaPanelMostrar(handler));
			}
		});			
		menuLampara.add(itemMostrarLampara);
		
		menuAutopartes.add(menuLampara);
		
		menuBar.add(menuAutopartes);
		
		menuBar.add(menuReparacion);
		//##################################### REPARACION ########################################
		
		
		
		
		
		setJMenuBar(menuBar);
	}

	public void switchPanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().validate();
	}	
}
