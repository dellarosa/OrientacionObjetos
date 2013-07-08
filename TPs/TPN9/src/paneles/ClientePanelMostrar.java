package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Lampara;

import bo.ClienteBO;

import utils.FuncionesAutopartes;
import utils.MiException;
import utils.PanelGestor;

public class ClientePanelMostrar extends PanelMostrar {

	private static final long serialVersionUID = 1L;
	private Handler handler;
	public ClientePanelMostrar(final Handler handler)
	{
		super(handler,"MOSTRAR CLIENTES");
		
	}

	public JScrollPane llenarPanelAMostrar(Handler handler,PanelGestor panelGestor) throws MiException {
		
		JTable table = panelGestor.cargarClientesEnTabla(handler.cargaClientes());
		JScrollPane pane=new JScrollPane(table);
		return pane;
	}	
}
