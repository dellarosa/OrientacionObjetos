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
import javax.swing.JTable;

import utils.MiException;
import utils.PanelGestor;
import bo.AceiteBO;
import bo.AutoparteBO;
import bo.FiltroBO;
import entities.Aceite;
import entities.Autoparte;
import entities.Filtro;

public class FiltroPanelMostrar extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public FiltroPanelMostrar()
	{
		
	}
	public FiltroPanelMostrar(final Handler handler)
	{
		this.setLayout(new BorderLayout());
			PanelGestor panelGestor=new PanelGestor();
			
			JPanel panelResto=null;
			JPanel panelTitulo =null;
			
			try
			{
				panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(600,400),null);
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR FILTROS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(handler.getFrame(), "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}	     
	        //JPanel panelGrid=panelGestor.cargarClientesEnPanel(getClientesG());
			List<Autoparte> lstAutopartes=new ArrayList<Autoparte>();
			
			try
			{
				lstAutopartes= handler.cargaAutopartes();
				
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			List<Filtro> lstFiltro=new ArrayList<Filtro>();
			for(Autoparte autop:lstAutopartes)
			{
				if(autop instanceof Filtro)
				{
					lstFiltro.add((Filtro)autop);
				}
			}
			JTable table=null;
			
			if((lstFiltro!=null)&&(lstFiltro.size()>0))
	    	{
				table=panelGestor.cargarFiltroEnTabla(lstFiltro);										
	    	}else
	    	{
	    		JOptionPane.showMessageDialog(handler.getFrame(), "NO HAY FILTROS", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
	    	}
			panelResto.add(table);
	        
			final JButton btSubmit=new JButton();
			btSubmit.setText("VOLVER");								
			btSubmit.addActionListener(new ActionListener() {	
				@Override
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
}
