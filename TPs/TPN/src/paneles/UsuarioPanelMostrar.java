package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;

public class UsuarioPanelMostrar extends JPanel {

	public UsuarioPanelMostrar(Handler handler) 
	{
		
			this.setLayout(new BorderLayout());
			
			PanelGestor panelGestor=new PanelGestor();
			JPanel panelTitulo=null;
			JPanel panelResto=null;
			
			try
			{
				panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(400,400),null);
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR USUARIOS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				 		
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al crear paneles", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
	        //JPanel panelGrid=panelGestor.cargarClientesEnPanel(getClientesG());
			
			JTable table=null;
			try{
	    		table=panelGestor.cargarUsuariosEnTabla(handler.cargarUsuarios());										
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al cargar Usuarios", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
	    	panelResto.add(table);
			
			//handler.backToPrincipal();
			
	        this.add(BorderLayout.NORTH,panelTitulo);
			this.add(BorderLayout.CENTER,panelResto);
			

	}
}
