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
import javax.swing.JTextField;

import entities.Autoparte;
import entities.Reparacion;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;


public class ReparacionPanelMostrar extends JPanel{
	private Handler handler;
	private static final long serialVersionUID = 1L;
	private JTextField tfIngreso;
	public ReparacionPanelMostrar(){}
	
	public ReparacionPanelMostrar(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor=new PanelGestor();
		
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.white,new Dimension(800,400),null);
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR REPARACIONES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.gray,null,null);
		
		JScrollPane pane = armarTablaReparaciones(panelGestor);
		panelResto.add(pane);
		
		tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		panelGrid.add(panelGestor.crearPanelOpcion("Ingrese id Autoparte a agregar", tfIngreso));
		
		menuBotones(panelGrid);
		
        this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelResto);
		this.add(BorderLayout.SOUTH,panelGrid);		
	}
	
	private void menuBotones(JPanel panelGrid) {
		JButton btVerAutopartes=new JButton("VER AUTOPARTES UTILIZADAS");							
		btVerAutopartes.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				if(validarCamposIngreso())
				{
					JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
				}else
				{
					verAutopartesUtilizadas();
					tfIngreso.setText("");
				}
		}});
		panelGrid.add(btVerAutopartes);
		
		
		JButton btVolver=new JButton("VOLVER");							
		btVolver.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
					handler.backToPrincipal();
		}});
		panelGrid.add(btVolver);
	}
	
	private JScrollPane armarTablaReparaciones(PanelGestor panelGestor) {
		List<Reparacion> lstReparacion = null;
		try {
			lstReparacion = handler.cargaReparaciones();
		} catch (MiException e) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar Reparaciones", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		
		JScrollPane pane = new JScrollPane(panelGestor.cargarReparacionesEnTabla(lstReparacion));
		return pane;
	}
	
	private void verAutopartesCargadas(Reparacion reparacion) {
		if(reparacion.getAutopartes().size()==0)
		{
			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE HAN UTILIZADO AUTOPARTES EN ESTA REPARACION");
		}else
		{
			String strAutopartes = new String();
			for(Autoparte au: reparacion.getAutopartes())
			{
				strAutopartes=strAutopartes+"\n"+au.toStringSemi();							
			}
			JOptionPane.showMessageDialog(handler.getFrame(), "AUTOPARTES CARGADAS\n"+strAutopartes);
		}
	}
	private boolean validarCamposIngreso() {
		return (tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null);
	}
	
	private void verAutopartesUtilizadas() {
		Reparacion reparacion = null;
		try{
			reparacion = handler.buscarReparacionPorId(Integer.valueOf(tfIngreso.getText()));					
		} catch(MiException e) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar Reparaciones", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}					
		verAutopartesCargadas(reparacion);
	}
}
