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

public class FiltroPanelMostrar extends JPanel{
	
	private Handler handler;
	FuncionesAutopartes funcionesAutopartes=null;
	private static final long serialVersionUID = 1L;
	public FiltroPanelMostrar(){}
	public FiltroPanelMostrar(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor=new PanelGestor();
		
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(800,400),null);
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR FILTROS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		
		funcionesAutopartes=new FuncionesAutopartes();
		List<Filtro> lstFiltro = funcionesAutopartes.listarFiltros(handler);
		JScrollPane pane = new JScrollPane(panelGestor.cargarFiltroEnTabla(lstFiltro));
		//pane.setPreferredSize(new Dimension(800,150));
		
		panelResto.add(pane);
		
		JButton btSubmit=new JButton("VOLVER");							
		btSubmit.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
					handler.backToPrincipal();
		}});
		
        this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelResto);
		this.add(BorderLayout.SOUTH,btSubmit);		
	}
	
}
