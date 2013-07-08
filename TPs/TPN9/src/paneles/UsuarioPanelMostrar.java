package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;

public class UsuarioPanelMostrar extends PanelMostrar {

	private static final long serialVersionUID = 1L;
	private Handler handler;
	
	public UsuarioPanelMostrar(final Handler handler) 
	{
		super(handler,"MOSTRAR USUARIOS");
	}
	
	public JScrollPane llenarPanelAMostrar(Handler handler,PanelGestor panelGestor) throws MiException {
		JTable table = panelGestor.cargarUsuariosEnTabla(handler.cargarUsuarios());
		
		JScrollPane pane=new JScrollPane(table);
		return pane;
	}
}
