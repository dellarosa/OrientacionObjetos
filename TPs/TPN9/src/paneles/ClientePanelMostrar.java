package paneles;

import handler.Handler;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import bo.ClienteBO;

import utils.MiException;
import utils.PanelGestor;

public class ClientePanelMostrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private Handler handler;
	public ClientePanelMostrar(){}
	public ClientePanelMostrar(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor=new PanelGestor();
		
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(600,400),null);
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR CLIENTES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		
		JTable table = cargarClientesEnTabla(panelGestor);    	
    	panelResto.add(table);
        
		final JButton btSubmit=new JButton("VOLVER");					
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
	private JTable cargarClientesEnTabla(PanelGestor panelGestor) {
		JTable table=null;
		try
		{
			table=panelGestor.cargarClientesEnTabla(handler.cargaClientes());
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al crear paneles", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		return table;
	}	
}
