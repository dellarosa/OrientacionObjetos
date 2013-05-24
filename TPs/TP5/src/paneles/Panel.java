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
import entities.Filtro;
import entities.Lampara;

import utils.Definiciones;
import utils.MiException;

public class Panel {
	public Panel()
	{
		
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
	public JPanel cargarFiltrosEnPanel(JPanel panelGridLabel,List<Autoparte> autopartesG) {
		
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
	public JPanel cargarAceitesEnPanel(JPanel panelGridLabel,List<Autoparte> autopartesG) {
		
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
	public JPanel cargarLamparasEnPanel(JPanel panelGridLabel,List<Autoparte> autopartesG) {
	
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

}
