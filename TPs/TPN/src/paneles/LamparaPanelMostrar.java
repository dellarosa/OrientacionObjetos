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
	
	private static final long serialVersionUID = 1L;

	public LamparaPanelMostrar(final Handler handler)
	{
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor=new PanelGestor();
		
		JPanel panelResto=null;
		JPanel panelTitulo =null;
		
		try
		{
			panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(600,400),null);
			panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR LAMPARAS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(null, "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}	     
        //JPanel panelGrid=panelGestor.cargarClientesEnPanel(getClientesG());
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
		
    	JTable table=panelGestor.cargarLamparaEnTabla(lstLamparas);										
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
