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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import utils.MiException;
import bo.AceiteBO;
import bo.AutoparteBO;
import bo.FiltroBO;
import entities.Aceite;
import entities.Autoparte;
import entities.Filtro;

public class FiltroPanelMostrar extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FiltroPanelMostrar()
	{
		
	}
	public FiltroPanelMostrar(JFrame frame )
	{
		FiltroBO filtroBo=new FiltroBO();
		AutoparteBO autoparteBo=new AutoparteBO();
		try
    	{			
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			
			this.setLayout(new BorderLayout());
			
			PanelGestor panelGestor=new PanelGestor();
			
			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(400,400),null);
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR FILTROS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			 			     
	        //JPanel panelGrid=panelGestor.cargarClientesEnPanel(getClientesG());
			
			List<Autoparte> lstAutopartes= autoparteBo.cargaAutopartes();
			List<Filtro> lstFiltro=new ArrayList<Filtro>();
			for(Autoparte autop:lstAutopartes)
			{
				if(autop instanceof Filtro)
				{
					
					lstFiltro.add((Filtro)autop);
				}
			}
			
	    	JTable table=panelGestor.cargarFiltroEnTabla(lstFiltro);										
			panelResto.add(table);
	        
			final JButton btSubmit=new JButton();
			btSubmit.setText("VOLVER");								
			btSubmit.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try
					{
						
					}catch(Exception e)
					{
						
					}
			}});
			
	        this.add(BorderLayout.NORTH,panelTitulo);
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);
			
			frame.setContentPane(this);
			frame.setVisible(true);
			
    	}catch(MiException e)
		{
			throw e;
		}catch(Exception e)
		{
			throw new MiException("\n[FILTRO MOSTRAR]MOSTRAR DATOS FILTROS Exception : "+e);
		}
	}
}
