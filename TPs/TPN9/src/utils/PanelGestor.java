package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import entities.Aceite;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;
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
		}		            
        catch (Exception e) {
			throw new MiException("\n[crearPanelBoxGral] EXCEPTION : "+e);												
		}
		
		return panel;
	}
	public JPanel crearPanelGrid(GridLayout grid,Border border,Color colorBack,Dimension prefDimension, Dimension maxDimension)
	{	
		JPanel panelGrid=new JPanel();
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
			
		return panelGrid;
	}
	public JPanel crearPanelBorderLayout(BorderLayout layout,Border border,Color color,Dimension dimension)
	{
		
		JPanel panelTitulo=new JPanel();
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

        return panelTitulo;
        
	}
	public JPanel crearPanelBorderConTitulo(BorderLayout layout,Border border,Color color,Dimension dimension,String titulo,int align,Font font,Color colorFont)
	{
		JPanel panelTitulo=new JPanel();
		
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
		}			            
        catch (Exception e) {
			throw new MiException("\n[crearPanelBox] PANELS EXCEPTION : "+e);												
		}
		
		
		return panel;
		
	}
	public JLabel crearTitulo(String titulo, int align, Font font) 
	{
		return crearTitulo(titulo, align, null, font);
	}
	
	public JLabel crearTitulo(String titulo, int align, Color color, Font font) 
	{
		JLabel label=new JLabel(titulo,align);
		
			if(color!=null)
			label.setForeground(color);
			if(font!=null)
			label.setFont(font);
		
		return label;
	}
	public JPanel cargarLabelsMostrarDatos() throws MiException {
		
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
	public JPanel crearPanelOpcion(String titulo,JTextField fieldOpcion){
		 
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
	public JPanel cargarUsuariosEnPanel(List<Usuario> usuarios) throws MiException {
		
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
		model.addColumn("ID");
		model.addColumn("NOMBRE");
		model.addColumn("EMAIL");
		model.addColumn("USERNAME");
		model.addColumn("PASSWORD");
		model.addColumn("JERARQUIA");
		model.addColumn("LOGUEADO");
		
		table.getTableHeader().setBackground(Color.red);
		table.enable(false);
		for (Usuario usuario : usuarios)
		{										
			//model.addRow(new Object[]{usuario.toString()});
			String strRow[]={String.valueOf(usuario.getId()),usuario.getName(),usuario.getEmail(),usuario.getUsername(),
					usuario.getPassword(),usuario.getJerarquia(),String.valueOf(usuario.getLogueado())};
			model.addRow(strRow);
			
		}
		return table;
	}
	public JTable cargarClientesEnTabla(List<Cliente> clientes) {
		DefaultTableModel model=new DefaultTableModel();
		JTable table=new JTable(model);
		model.addColumn("ID");
		model.addColumn("NOMBRE");
		model.addColumn("MAIL");
		model.addColumn("AUTO");
		model.addColumn("PATENTE");
		table.getTableHeader().setBackground(Color.magenta);
		table.setBackground(Color.blue);
		table.setForeground(Color.white);
		table.enable(false);
		
		for (Cliente cliente : clientes)
		{										
			//model.addRow(new Object[]{cliente.toString()});
			String[] strRow={String.valueOf(cliente.getId()),cliente.getNombre(),cliente.getMail(),cliente.getAuto(),cliente.getPatente()};
			model.addRow(strRow);
		}
		return table;
	}
	public JTable cargarAceiteEnTabla(List<Aceite> aceites) {
		DefaultTableModel model=new DefaultTableModel();
		JTable table=new JTable(model);
		
		model.addColumn("ID Autoparte");
		model.addColumn("ID Aceite");
		model.addColumn("MARCA");
		model.addColumn("MODELO");
		model.addColumn("CANT. DISP.");
		model.addColumn("COSTO");
		model.addColumn("CANT.LITROS");
		model.addColumn("TIPO ACEITE");		
		
		table.getTableHeader().setBackground(Color.green);
		table.enable(false);
		for (Aceite aceite : aceites)
		{										
			String[] strVector={String.valueOf(aceite.getId()),String.valueOf(aceite.getAceite_ID()),aceite.getMarca(),aceite.getModelo(),
			String.valueOf(aceite.getCantDisponible()),String.valueOf(aceite.getCosto()),String.valueOf(aceite.getCantidadlitros()),aceite.getTipoAceite()};
			model.addRow(strVector);
		
		}
		return table;
	}
	public JTable cargarFiltroEnTabla(List<Filtro> filtros) {
		DefaultTableModel model=new DefaultTableModel();
		
		model.addColumn("ID Autoparte");
		model.addColumn("ID Filtro");
		model.addColumn("MARCA");
		model.addColumn("MODELO");
		model.addColumn("CANT. DISP.");
		model.addColumn("COSTO");
		model.addColumn("MATERIAL");
		model.addColumn("TAMANIO");		
		JTable table=new JTable(model);
		//table.setPreferredSize(new Dimension(800,200));
		table.getTableHeader().setBackground(Color.green);
		
		table.enable(false);
		for (Filtro filtro : filtros)
		{							
			String[] strVector={String.valueOf(filtro.getId()),String.valueOf(filtro.getFiltro_ID()),filtro.getMarca(),filtro.getModelo(),
			String.valueOf(filtro.getCantDisponible()),String.valueOf(filtro.getCosto()),filtro.getMaterial(),filtro.getTamanio()};
			model.addRow(strVector);
		}
		return table;
	}
	public JTable cargarLamparaEnTabla(List<Lampara> lamparas) {
		//Object[][] data = new Object[lamparas.size()][9];
		//String[] strColumnas={"**TIPO","ID Autoparte","ID Filtro","MARCA","MODELO","CANT.DISP.","COSTO","MATERIAL","TAMANIO"};
		//Object[] strColumnas={"**TIPO","ID Autoparte","ID Filtro","MARCA","MODELO","CANT.DISP.","COSTO","MATERIAL","TAMANIO"};
		DefaultTableModel model=new DefaultTableModel();
		model.addColumn("ID Autoparte");
		model.addColumn("ID Lampara");
		model.addColumn("MARCA");
		model.addColumn("MODELO");
		model.addColumn("CANT. DISP.");
		model.addColumn("COSTO");
		model.addColumn("COLOR");
		model.addColumn("TAMANIO");	
		
		JTable table=new JTable(model);		
		table.getTableHeader().setBackground(Color.green);
		table.enable(false);
		
		for(Lampara lampara : lamparas)
		{	
			String[] strVector={String.valueOf(lampara.getId()),String.valueOf(lampara.getLampara_ID()),lampara.getMarca(),lampara.getModelo(),
			String.valueOf(lampara.getCantDisponible()),String.valueOf(lampara.getCosto()),lampara.getColor(),lampara.getTamanio()};
			model.addRow(strVector);
		
		}
		
		return table;
	}
	public JPanel cargarAutopartesEnPanel(List<Filtro> lstFiltros,List<Aceite> lstAceites, List<Lampara> lstLamparas) {
		
		JPanel panelGrid=crearPanelGrid(new GridLayout(3,1),null,Color.gray,new Dimension(800,300),null);
		JScrollPane pane = new JScrollPane(cargarFiltroEnTabla(lstFiltros));
		//pane.setPreferredSize(new Dimension(800,300));
		JScrollPane pane2 = new JScrollPane(cargarAceiteEnTabla(lstAceites));
		JScrollPane pane3 = new JScrollPane(cargarLamparaEnTabla(lstLamparas));
		panelGrid.add(pane);
		panelGrid.add(pane2);
		panelGrid.add(pane3);
		
		return panelGrid;
	}
	public JTable cargarReparacionesEnTabla(List<Reparacion> lstReparacion) {
		DefaultTableModel model=new DefaultTableModel();
		model.addColumn("ID Rep.");
		model.addColumn("FECHA I");
		model.addColumn("FECHA E");
		model.addColumn("COSTO");
		model.addColumn("ID Cliente");
		model.addColumn("AUTO");
		model.addColumn("PATENTE");
		model.addColumn("NOMBRE");	
	
		JTable table=new JTable(model);		
		table.getTableHeader().setBackground(Color.ORANGE);
		table.enable(false);
		
		for(Reparacion repara : lstReparacion)
		{
			String[] strVector={String.valueOf(repara.getId()),repara.getFechainicio(),repara.getFechaentrega(),String.valueOf(repara.getCosto()),
			String.valueOf(repara.getCliente().getId()),repara.getCliente().getAuto(),repara.getCliente().getPatente(),repara.getCliente().getNombre()};
			model.addRow(strVector);
		}
		return table;
	}
	public JPanel armarPanelConImagen() {
		ImageIcon image = new ImageIcon("image/taller.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add( label, BorderLayout.CENTER );
        return panel;
	}		
	
}
