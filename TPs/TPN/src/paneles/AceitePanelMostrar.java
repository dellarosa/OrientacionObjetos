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

import entities.Aceite;
import entities.Autoparte;

import utils.MiException;
import bo.AceiteBO;
import bo.AutoparteBO;


public class AceitePanelMostrar extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AceitePanelMostrar()
	{
		
	}
	public AceitePanelMostrar(JFrame frame)
	{
		AceiteBO aceiteBo=new AceiteBO();
		AutoparteBO autoparteBo=new AutoparteBO();
		try
    	{			
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			
			this.setLayout(new BorderLayout());
			
			PanelGestor panelGestor=new PanelGestor();
			
			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(400,400),null);
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR ACEITES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			 			     
	        //JPanel panelGrid=panelGestor.cargarClientesEnPanel(getClientesG());
			
			List<Autoparte> lstAutopartes= autoparteBo.cargaAutopartes();
			List<Aceite> lstAceite=new ArrayList<Aceite>();
			for(Autoparte autop:lstAutopartes)
			{
				if(autop instanceof Aceite)
				{
					
					lstAceite.add((Aceite)autop);
				}
			}
			
	    	JTable table=panelGestor.cargarAceiteEnTabla(lstAceite);										
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
			throw new MiException("\n[ACEITE MOSTRAR]MOSTRAR DATOS ACEITES Exception : "+e);
		}
	}
}
