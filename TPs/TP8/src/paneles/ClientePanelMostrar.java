package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import bo.ClienteBO;

import utils.MiException;

public class ClientePanelMostrar extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientePanelMostrar()
	{
		
	}
	public ClientePanelMostrar(JFrame frame)
	{
		try
		{	
			ClienteBO clienteBo=new ClienteBO();
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();			
			this.setLayout(new BorderLayout());			
			PanelGestor panelGestor=new PanelGestor();
			
			
			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(400,400),null);
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR CLIENTES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			 			     
	    	JTable table=panelGestor.cargarClientesEnTabla(clienteBo.cargaClientes());										
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
			throw new MiException("\n[MOSTRAR CLIENTES]MOSTRAR DATOS CLIENTE Exception : "+e);
		}
	}	
}
