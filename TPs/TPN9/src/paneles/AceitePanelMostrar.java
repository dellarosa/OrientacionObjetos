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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Aceite;
import entities.Autoparte;

import utils.FuncionesAutopartes;
import utils.MiException;
import utils.PanelGestor;


public class AceitePanelMostrar extends JPanel{
	
	private Handler handler;
	private static final long serialVersionUID = 1L;
	private FuncionesAutopartes funcionesAutopartes=null;
	public AceitePanelMostrar(){}
	public AceitePanelMostrar(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		
		PanelGestor panelGestor=new PanelGestor();
		
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(800,50),"MOSTRAR ACEITES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(800,400),null);
				     
        //JPanel panelGrid=panelGestor.cargarClientesEnPanel(getClientesG());
		funcionesAutopartes=new FuncionesAutopartes();
		List<Aceite> lstAceite = funcionesAutopartes.listarAceites(handler);
		
    	JTable table=panelGestor.cargarAceiteEnTabla(lstAceite);										
    	JScrollPane pane = new JScrollPane(table);
    	panelResto.add(pane);
        
		final JButton btSubmit=new JButton("VOLVER");						
		btSubmit.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				handler.backToPrincipal();				
		}});
		
        this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelResto);
		this.add(BorderLayout.SOUTH,btSubmit);
	
	}
	
}
