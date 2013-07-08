package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import utils.FuncionesAutopartes;
import utils.MiException;
import utils.PanelGestor;
import bo.AceiteBO;
import bo.AutoparteBO;
import bo.FiltroBO;
import entities.Aceite;
import entities.Autoparte;
import entities.Filtro;

public class FiltroPanelMostrar extends PanelMostrar{
	
	private Handler handler;
	FuncionesAutopartes funcionesAutopartes=null;
	private static final long serialVersionUID = 1L;
	
	public FiltroPanelMostrar(final Handler handler)
	{
		super(handler,"MOSTRAR FILTROS");		
	}

	@Override
	public JScrollPane llenarPanelAMostrar(Handler handler,PanelGestor panelGestor) {
		funcionesAutopartes=new FuncionesAutopartes();
		List<Filtro> lstFiltro = funcionesAutopartes.listarFiltros(handler);
		JScrollPane pane = new JScrollPane(panelGestor.cargarFiltroEnTabla(lstFiltro));
		return pane;
	}
	
	
}
