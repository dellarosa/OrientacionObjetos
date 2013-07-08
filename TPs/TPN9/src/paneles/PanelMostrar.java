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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utils.FuncionesAutopartes;
import utils.MiException;
import utils.PanelGestor;
import entities.Lampara;

public abstract class PanelMostrar extends JPanel{
	private static final long serialVersionUID = 1L;
	private FuncionesAutopartes funcionesAutopartes=null;
	
	public PanelMostrar(final Handler handler,String strTitulo)
	{
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor=new PanelGestor();
		
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(600,400),null);
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),strTitulo,JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		try
		{				
			panelResto.add(llenarPanelAMostrar(handler, panelGestor));
		}catch(MiException e)
		{
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al llenar panel", "Error", JOptionPane.ERROR_MESSAGE);
		}
		final JButton btSubmit=new JButton("VOLVER");										
		btSubmit.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				handler.backToPrincipal();				
		}});
		
        this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelResto);
		this.add(BorderLayout.SOUTH,btSubmit);		
	}

	abstract public JScrollPane llenarPanelAMostrar(final Handler handler,PanelGestor panelGestor) throws MiException;
}
