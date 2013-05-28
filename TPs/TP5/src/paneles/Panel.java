package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;

import utils.Definiciones;
import utils.MetodosGrl;
import utils.MiException;

public class Panel {
	MetodosGrl metgral;
	public Panel()
	{
		metgral=new MetodosGrl();
	}
	public JPanel crearPanelBoxGral(JFrame frame,Color colorBack,int AXIS) throws MiException
	{
		JPanel panel=new JPanel();
		try
		{					
			panel =(JPanel) frame.getContentPane();						
			panel.setBackground(Color.lightGray);
			panel.setLayout(new BoxLayout(panel,AXIS));
		}catch (MiException e) {
			throw e;
		}			            
        catch (Exception e) {
			throw new MiException("\n[crearPanelBoxGral] EXCEPTION : "+e);												
		}
		
		return panel;
	}
	public JPanel crearPanelGrid(GridLayout grid,Border border,Color colorBack,Dimension prefDimension, Dimension maxDimension)throws MiException
	{	
		JPanel panelGrid=new JPanel();
		try
		{	
			panelGrid.setLayout(grid);
			if(border!=null)
			{
				panelGrid.setBorder(border);
			}
			if(colorBack!=null)
			{
				panelGrid.setBackground(colorBack);
			}
			if(prefDimension!=null)			
				panelGrid.setPreferredSize(prefDimension);
			
			if(maxDimension!=null)
				panelGrid.setMaximumSize(maxDimension);
			
		}catch (MiException e) {
			throw e;
		}			            
        catch (Exception e) {
			throw new MiException("\n[crearPanelGrid] PANELS EXCEPTION : "+e);												
		}
		return panelGrid;
	}
	public JPanel crearPanelBorderLayout(BorderLayout layout,Border border,Color color,Dimension dimension) throws MiException
	{
		
		JPanel panelTitulo=new JPanel();
		try{	
			if(layout!=null)
			{
				panelTitulo.setLayout(layout);
			}
	        if(color!=null)
	        {
	        	panelTitulo.setBackground(color);
	        }
	        if(dimension!=null)
	        {
	        	panelTitulo.setMaximumSize(dimension);	
	        }
		}catch (MiException e) {
			throw e;
		}			            
	    catch (Exception e) {
			throw new MiException("\n[crearPanelBorderLayout] PANELS EXCEPTION : "+e);												
		}
        return panelTitulo;
        
	}
	public JPanel crearPanelBorderConTitulo(BorderLayout layout,Border border,Color color,Dimension dimension,String titulo,int align,Font font,Color colorFont)throws MiException
	{
		JPanel panelTitulo=new JPanel();
		try{
			if(layout!=null)
			{
				panelTitulo.setLayout(layout);
			}
	        if(color!=null)
	        {
	        	panelTitulo.setBackground(color);
	        }
	        if(dimension!=null)
	        {
	        	panelTitulo.setMaximumSize(dimension);	
	        }
	        
	        
	        JLabel labelTitulo=this.crearTitulo(titulo,align,colorFont,font);
	        
	        panelTitulo.add(labelTitulo);
		}catch (MiException e) {
			throw e;
		}			            
        catch (Exception e) {
			throw new MiException("\n[crearPanelBorderConTitulo] PANELS EXCEPTION : "+e);												
		}	        
		return panelTitulo;
	}
	
	public JPanel labelsTiposAutopartes(JPanel panelGridLabel) throws MiException
	{
		
		try{
			JLabel labelFiltro=new JLabel(Definiciones.FILTRO_INDICE+" - "+Definiciones.FILTRO_STRING,JLabel.CENTER);
			labelFiltro.setBorder(Definiciones.line_blackline);
			JLabel labelAceite=new JLabel(Definiciones.ACEITE_INDICE+" - "+Definiciones.ACEITE_STRING,JLabel.CENTER);
			labelAceite.setBorder(Definiciones.line_blackline);
			JLabel labelLampara=new JLabel(Definiciones.LAMPARA_INDICE+" - "+Definiciones.LAMPARA_STRING,JLabel.CENTER);
			labelLampara.setBorder(Definiciones.line_blackline);
			
			panelGridLabel.add(labelFiltro);
			panelGridLabel.add(labelAceite);
			panelGridLabel.add(labelLampara);
		}catch (MiException e) {
			throw e;
		}			            
        catch (Exception e) {
			throw new MiException("\n[labelsTiposAutopartes] PANELS EXCEPTION : "+e);												
		}
		
		return panelGridLabel;
	}
	
	public JPanel crearPanelBox(int AXIS, Color color) throws MiException
	{
		JPanel panel=new JPanel();					
		try{
			panel.setLayout(new BoxLayout(panel,AXIS));
			
			if(color!=null)
				panel.setBackground(color);
			
		}catch (MiException e) {
			throw e;
		}			            
        catch (Exception e) {
			throw new MiException("\n[crearPanelBox] PANELS EXCEPTION : "+e);												
		}
		
		
		return panel;
		
	}
	public JLabel crearTitulo(String titulo, int align, Color color, Font font)throws MiException 
	{
		JLabel label=new JLabel(titulo,align);
		try{
			
			if(color!=null)
			label.setForeground(color);
			if(font!=null)
			label.setFont(font);
		
		}catch (MiException e) {
			throw e;
		}			            
        catch (Exception e) {
			throw new MiException("\n[crearTitulo] PANELS EXCEPTION : "+e);												
		}	
		return label;
	}
	public JPanel cargarFiltrosEnPanel(List<Autoparte> autopartesG) {
		
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(metgral.obtenerCantidadFiltros(autopartesG),1), Definiciones.line_blackline,null,null,null);	//PODRIA PREGUNTAR SI POR L OMENOS HAY ALGUN FILTRO
		for (Autoparte autoparte : autopartesG)
		{										
			if(autoparte instanceof Filtro)
			{
				Filtro filtro=new Filtro();
				filtro=(Filtro)autoparte;
				JLabel label=new JLabel(filtro.toString(),JLabel.CENTER);
				label.setFont(new Font(Font.SERIF,-1,9));
				panelGridLabel.add(label);
			}
		}
		
		return panelGridLabel;
		
	}
	public JPanel cargarAceitesEnPanel(List<Autoparte> autopartesG) {
		
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(metgral.obtenerCantidadAceites(autopartesG),1), Definiciones.line_blackline,null,null,null);	//PODRIA PREGUNTAR SI POR L OMENOS HAY ALGUN FILTRO
		for (Autoparte autoparte : autopartesG)
		{										
			if(autoparte instanceof Aceite)
			{
				Aceite aceite=new Aceite();
				aceite=(Aceite)autoparte;
				JLabel label=new JLabel(aceite.toString(),JLabel.CENTER);
				label.setFont(new Font(Font.SERIF,-1,9));
				panelGridLabel.add(label);
			}
		}
		
		return panelGridLabel;
		
	}
	public JPanel cargarLamparasEnPanel(List<Autoparte> autopartesG) {
	
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(metgral.obtenerCantidadLamparas(autopartesG),1), Definiciones.line_blackline,null,null,null);	//PODRIA PREGUNTAR SI POR L OMENOS HAY ALGUN FILTRO
	for (Autoparte autoparte : autopartesG)
	{										
		if(autoparte instanceof Lampara)
		{
			Lampara lampara=new Lampara();
			lampara=(Lampara)autoparte;
			JLabel label=new JLabel(lampara.toString(),JLabel.CENTER);
			label.setFont(new Font(Font.SERIF,-1,9));
			panelGridLabel.add(label);
		}
	}
	
	return panelGridLabel;
	
	}
	public JPanel cargarLabelsMostrarDatos() {
		
		JLabel labelClientes=new JLabel("1-Clientes",JLabel.CENTER);
		labelClientes.setBorder(Definiciones.line_blackline);
		JLabel labelAutopartes=new JLabel("2-Autopartes",JLabel.CENTER);
		labelAutopartes.setBorder(Definiciones.line_blackline);
		JLabel labelReparaciones=new JLabel("3-Reparaciones",JLabel.CENTER);
		labelReparaciones.setBorder(Definiciones.line_blackline);
		
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(3, 1), Definiciones.line_blackline,null,null,null);
		
		panelGridLabel.add(labelClientes);
		panelGridLabel.add(labelAutopartes);
		panelGridLabel.add(labelReparaciones);
		
		return panelGridLabel;
	}
	public JPanel crearPanelOpcion(String titulo,JTextField fieldOpcion) {
		 
		JPanel panelGridOpcion=crearPanelGrid(new GridLayout(1, 2), Definiciones.line_blackline,null,null,null);
		fieldOpcion.setBorder(Definiciones.line_blackline);	
		JLabel labelOpcion=new JLabel(titulo,JLabel.CENTER);
		labelOpcion.setBorder(Definiciones.line_blackline);						
		
		panelGridOpcion.add(labelOpcion);
		panelGridOpcion.add(fieldOpcion);
		
		return panelGridOpcion;
	}
	public JPanel cargarClientesEnPanel(List<Cliente> clientesG) {
		
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(clientesG.size(), 1), Definiciones.line_blackline,null,null,null);
		for (Cliente client : clientesG)
		{										
			JLabel label=new JLabel(client.toString(),JLabel.LEFT);
			label.setFont(new Font(Font.SERIF,-1,9));
			label.setBorder(Definiciones.line_blackline);
			panelGridLabel.add(label);
		
		}
		
		return panelGridLabel;
	}
	public JPanel cargarPanelReparacionesEnReparacion(List<Reparacion> reparacionesG) {
		
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(metgral.obtenerCantidadReparacionesEnReparacion(reparacionesG), 1), Definiciones.line_blackline,null,null,null);
		for(Reparacion reparacion : reparacionesG)
		{				
			if(reparacion.getEntregado()==0)
			{
				//JLabel label=new JLabel(reparacion.toString(),JLabel.LEFT);
				JLabel label=new JLabel("\nID: "+reparacion.getId()+" - FECHA I: "+reparacion.getFechainicio()+" NOMBRE CLIENTE: "+reparacion.getCliente().getNombre(),JLabel.LEFT);
				label.setFont(new Font(Font.SERIF,-1,9));
				label.setBorder(Definiciones.line_blackline);
				panelGridLabel.add(label);		
				//System.out.print("\nID: "+reparacion.getId()+" - FECHA I: "+reparacion.getFechainicio()+" NOMBRE CLIENTE: "+reparacion.getCliente().getNombre());
			}
		}
		return panelGridLabel;
		
	}
	public JPanel cargarPanelReparacionesEntregadas(List<Reparacion> reparacionesG) {
		
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(metgral.obtenerCantReparacionesFinalizadasYAutopartes(reparacionesG), 1), Definiciones.line_blackline,null,null,null);
		System.out.print("\nCANT :"+metgral.obtenerCantReparacionesFinalizadasYAutopartes(reparacionesG));
		
		for(Reparacion reparacion : reparacionesG)
		{				
			if(reparacion.getEntregado()==1)
			{	
				JLabel label=new JLabel(reparacion.toString(),JLabel.LEFT);
				label.setFont(new Font(Font.SERIF,-1,9));
				label.setBorder(Definiciones.line_blackline);
				panelGridLabel.add(label);		
				
				System.out.print("\nCANT A:"+reparacion.getAutopartes().size());
				
				JPanel panelGridAutopart=crearPanelGrid(new GridLayout(reparacion.getAutopartes().size(), 1), Definiciones.line_blackline,null,null,null);
				for(Autoparte autoparte: reparacion.getAutopartes()){
					JLabel labelAutop=new JLabel("  A: "+ autoparte.toString(),JLabel.LEFT);
					labelAutop.setFont(new Font(Font.SERIF,-1,9));
					panelGridAutopart.add(labelAutop);		
				}
				panelGridLabel.add(panelGridAutopart);
			}
		}
		return panelGridLabel;
	}

}
