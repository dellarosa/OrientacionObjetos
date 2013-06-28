package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;

import utils.Definiciones;
import utils.FuncionesAutopartes;
import utils.MiException;
import utils.PanelGestor;

public class ReparacionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Handler handler;
	private PanelGestor panelGestor;
	private Cliente cliente;
	private JTextField tfIngreso;
	FuncionesAutopartes funcionesAutopartes=null;
	private Reparacion reparacion=null;
	private List<Autoparte> lstAutopartes=null;
	
	public ReparacionPanel(final Handler handler)
	{
		this.setLayout(new BorderLayout());
		this.handler=handler;
		panelGestor=new PanelGestor();
		funcionesAutopartes=new FuncionesAutopartes();
		reparacion=new Reparacion();
		lstAutopartes=new ArrayList<Autoparte>();
				
		String seleccion = JOptionPane.showInputDialog(handler.getFrame(),"","Ingrese patente del auto a finalizar",JOptionPane.QUESTION_MESSAGE);  
		if((seleccion==null)||(seleccion.equals("")))
    	{
			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS - CANCELADO ");
			//this.handler.backToPrincipal();
			this.add(panelGestor.armarPanelConImagen());
    	}else
    	{
			if(!busquedaCliente(seleccion))
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO CLIENTE");
				//this.handler.backToPrincipal();
				this.add(panelGestor.armarPanelConImagen());
			}else
			{	
				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(800,50),"CARGA DE REPARACION",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				JPanel panelGrid=panelGestor.crearPanelGrid(new GridLayout(2,2),null,Color.gray,null,null);
				JPanel panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(800,250));
				
				tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
				
				panelCentro.add(BorderLayout.NORTH,funcionesAutopartes.obtenerPanelAutopartes(handler));		
				panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte a agregar", tfIngreso));
						
				menuBotones(panelGrid);				
				
				this.add(BorderLayout.NORTH,panelTitulo);
				this.add(BorderLayout.CENTER,panelCentro);
				this.add(BorderLayout.SOUTH,panelGrid);
			}
    	}
		
	}

	private void menuBotones(JPanel panelGrid) {
		
		JButton btCargarAutoparte=new JButton("CARGAR AUTOPARTE");											
		btCargarAutoparte.addActionListener(new ActionListener() {												
		public void actionPerformed(ActionEvent evt) {
			cargarAutoparteEnReparacion();	
			tfIngreso.setText("");
		}});
		panelGrid.add(btCargarAutoparte);
		
		JButton btVerAutopartes=new JButton("VER AUTOPARTES CARGADAS");									
		btVerAutopartes.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				verAutopartesCargadas();
		}});
		panelGrid.add(btVerAutopartes);
		
		JButton btSubmit=new JButton("FINALIZAR REPARACION");											
		btSubmit.addActionListener(new ActionListener() {												
		public void actionPerformed(ActionEvent evt) {
			finalizarReparacion();	        		
		}});

		panelGrid.add(btSubmit);
		
		JButton btVolver=new JButton("VOLVER");									
		btVolver.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {				
				handler.backToPrincipal();				
		}});
		panelGrid.add(btVolver);
	}

	private void verAutopartesCargadas() {
		if(lstAutopartes.size()==0)
		{
			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE HAN INGRESADO AUTOPARTES, \nEL COSTO AL MOMENTO ES EL DE LA REVISION");
		}else
		{
			String strAutopartes = new String();
			for(Autoparte au:lstAutopartes)
			{
				strAutopartes=strAutopartes+"\n"+au.toStringSemi();							
			}
			JOptionPane.showMessageDialog(handler.getFrame(), "AUTOPARTES CARGADAS\n"+strAutopartes);
		}
	}
	
	private void finalizarReparacion() {
		try
		{
			reparacion.setAutopartes(lstAutopartes);
			reparacion.setCliente(cliente);
			reparacion.setEntregado(1);
			Calendar c = new GregorianCalendar();
		    Date d1 = c.getTime(); 
			reparacion.setFechaentrega(d1.toString());
			reparacion.setFechainicio(d1.toString());
			reparacion.setId(handler.buscarUltimaReparacionId());
			
			if(lstAutopartes.size()==0)
	    	{
				JOptionPane.showMessageDialog(handler.getFrame(), "No se ingreso ninguna autoparte. Se cobrará unicamente el costo del servicio.");
				reparacion.setCosto(145);			    			
				handler.insertarReparacion(reparacion,handler.buscarUltimaReparacionAutoparteId());
				JOptionPane.showMessageDialog(handler.getFrame(), "REPARACION CARGADA CORRECTAMENTE\n-DATOS REPARACION\n"+reparacion.toString());
	    	}else
	    	{
	    		reparacion.setCosto(145);
	    		for(Autoparte autop:lstAutopartes)
	    		{		        			
	    			reparacion.setCosto(reparacion.getCosto()+autop.getCosto());
	    		}
	    		handler.insertarReparacion(reparacion,handler.buscarUltimaReparacionAutoparteId());		     
	    		disminuirAutopartes();
	    		JOptionPane.showMessageDialog(handler.getFrame(), "REPARACION CARGADA CORRECTAMENTE\n-DATOS REPARACION\n"+reparacion.toString()+"\nDATOS AUTOPARTES UTILIZADAS\n"+reparacion.toStringAutopartes());
	    	}		        		
			handler.backToPrincipal();
		}catch(MiException e)
		{
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al finalizar Reparacion", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
			//this.add(panelGestor.armarPanelConImagen());
		}
	}
	private boolean busquedaCliente(String seleccion) {
		
		cliente=new Cliente();
		cliente=buscarCliente(seleccion);
		if(cliente==null)
		{
			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO AUTO DEL CLIENTE");
			return false;
		}
		else
		{
			return true;
		}
	}
	private void disminuirAutopartes()
			throws MiException {
		for(Autoparte autoparte:lstAutopartes)
		{
			handler.disminuirAutoparte(autoparte);
		}
	}
				
	private boolean validarCamposIngreso() {
		return (tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null);
	}

	private void cargarAutoparteEnReparacion() {
		if(validarCamposIngreso())
		{
			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
		}else
		{	
			try {
				Autoparte autoparte=handler.buscarAutopartePorId(Integer.valueOf(tfIngreso.getText()));			
				if(autoparte!=null)
				{	
					if(autoparte.getTipoAutoparte().equals("filtro"))
					{
						Filtro filtro=handler.buscarFiltroPorIdAutoParte(autoparte.getId());
						filtro=funcionesAutopartes.transmutarFiltroAutoparte(filtro,autoparte);
						lstAutopartes.add(filtro);
					}else if(autoparte.getTipoAutoparte().equals("aceite"))
					{
						Aceite aceite=handler.buscarAceitePorIdAutoParte(autoparte.getId());
						aceite=funcionesAutopartes.transmutarAceiteAutoparte(aceite,autoparte);
						lstAutopartes.add(aceite);
					}else if(autoparte.getTipoAutoparte().equals("lampara"))
					{
						Lampara lampara=handler.buscarLamparaPorIdAutoParte(autoparte.getId());
						lampara=funcionesAutopartes.transmutarLamparaAutoparte(lampara,autoparte);
						lstAutopartes.add(lampara);
					}
					else{}		
					
					JOptionPane.showMessageDialog(handler.getFrame(), "Autoparte: "+autoparte.getTipoAutoparte()+" Nº "+autoparte.getId()+". Agregada correctamente. Puede seguir ingresando autopartes.");					
				}else
				{
					JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO AUTOPARTE");
				}
			} catch (MiException e) {
				JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar Autoparte", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
				//this.add(panelGestor.armarPanelConImagen());
			}
		}
	}
	
	private Cliente buscarCliente(String seleccion) {
		try
		{
			//cliente=handler.buscarClientePorApodo(seleccion);
			cliente=handler.buscarClientePorPatente(seleccion);
		}catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al crear buscar cliente", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
			//this.add(panelGestor.armarPanelConImagen());
		}
		return cliente;
	}
}
