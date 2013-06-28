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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;

public class UsuarioPanelMostrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private Handler handler;
	
	public UsuarioPanelMostrar(final Handler handler) 
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		
		PanelGestor panelGestor=new PanelGestor();
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR USUARIOS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(400,400),null);
		
		JTable table = cargarUsuariosEnTabla(panelGestor);
		JScrollPane pane=new JScrollPane(table);
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

	private JTable cargarUsuariosEnTabla(PanelGestor panelGestor) {
		JTable table=null;
		try{
			table=panelGestor.cargarUsuariosEnTabla(handler.cargarUsuarios());										
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(null, "Error al cargar Usuarios", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		return table;
	}
}
