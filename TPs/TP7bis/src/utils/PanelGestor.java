package utils;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import entities.Usuario;

public class PanelGestor {
	MetodosGrl metgral;
	public PanelGestor()
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
	
	public JPanel crearPanelBox(int AXIS, Color color, Dimension dimension) throws MiException
	{
		JPanel panel=new JPanel();					
		try{
			panel.setLayout(new BoxLayout(panel,AXIS));
			
			if(color!=null)
				panel.setBackground(color);
			if(dimension!=null)
				panel.setPreferredSize(dimension);
			
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
	
	public JTextField crearTextField(String text, int col,Border border, Font font, Color color, float alignX) {

		JTextField textField=new JTextField();
		
		if((text!=null)&&(text!=""))
		{
			textField.setText(text);
		}
		if(col>0)
		{
			textField.setColumns(col);
		}
		if(border!=null)
		{
			textField.setBorder(border);
		}
		if(font!=null)
		{
			textField.setFont(font);
		}
		if(color!=null)
		{
			textField.setBackground(color);
		}
		if(alignX>0)
		{
			textField.setAlignmentX(alignX);
		}
		return textField;
	}
	public JLabel crearLabel(String text, Font font, float alignX, Dimension dimension, Border border) {
		JLabel label=new JLabel();
		if((text!="")&&(text!=null))
		{
			label.setText(text);
		}
		if(font!=null)
		{
			label.setFont(font);
		}
		if(alignX>0)
			
		{
			label.setAlignmentX(alignX);
		}
		if(dimension!=null)
			label.setPreferredSize(dimension);
		if(border!=null)
			label.setBorder(border);
		
		return label;
	}
	public JPanel crearPanelConFieldsEnForm(JTextField[] textFields,JLabel[] labels) {
		
		JPanel panelGrid=crearPanelGrid(new GridLayout(labels.length,2),null,Color.gray,null,null);

		int x=0;
		while(x<labels.length)
		{
			panelGrid.add(labels[x]);
			panelGrid.add(textFields[x]);
			x++;
		}
		
		panelGrid.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		return panelGrid;
	}
	public JPanel cargarUsuariosEnPanel(List<Usuario> usuarios) {
		
		JPanel panelGridLabel=crearPanelGrid(new GridLayout(usuarios.size(), 1), Definiciones.line_blackline,null,null,null);
		for (Usuario usuario : usuarios)
		{										
			JLabel label=new JLabel(usuario.toString(),JLabel.LEFT);
			label.setFont(new Font(Font.SERIF,-1,9));
			label.setBorder(Definiciones.line_blackline);
			panelGridLabel.add(label);
		
		}
		
		return panelGridLabel;
	}
	public JTable cargarUsuariosEnTabla(List<Usuario> usuarios) {
		
		DefaultTableModel model=new DefaultTableModel();
		JTable table=new JTable(model);
		model.addColumn("USUARIOS");
		
		for (Usuario usuario : usuarios)
		{										
			model.addRow(new Object[]{usuario.toString()});
		
		}
		return table;
	}	
}
