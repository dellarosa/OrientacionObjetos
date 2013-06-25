package paneles;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import utils.MiException;
import utils.PanelGestor;
import entities.Autoparte;
import entities.Lampara;

import handler.Handler;

public class LamparaPanelMostrar extends JPanel{
	
	private Handler handler;
	private static final long serialVersionUID = 1L;

	public LamparaPanelMostrar(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor=new PanelGestor();
		
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(600,400),null);
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR LAMPARAS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);

		List<Lampara> lstLamparas = listarLamparas();		
    	panelResto.add(panelGestor.cargarLamparaEnTabla(lstLamparas));
        
		final JButton btSubmit=new JButton("SUBMIT");										
		btSubmit.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					handler.backToPrincipal();
				}catch(Exception e)
				{
					
				}
		}});
		
        this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelResto);
		this.add(BorderLayout.SOUTH,btSubmit);		
	}

	private List<Lampara> listarLamparas() {
		List<Autoparte> lstAutopartes=null;
		
		try
		{
			lstAutopartes= handler.cargaAutopartes();
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(null, "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		List<Lampara> lstLamparas=new ArrayList<Lampara>();
		for(Autoparte autop:lstAutopartes)
		{
			if(autop instanceof Lampara)
			{
				
				lstLamparas.add((Lampara)autop);
			}
		}
		return lstLamparas;
	}

}
