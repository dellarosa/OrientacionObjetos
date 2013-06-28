package paneles;

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
import javax.swing.JTextField;

import utils.Definiciones;
import utils.FuncionesAutopartes;
import utils.MiException;
import utils.PanelGestor;
import entities.Autoparte;
import entities.Lampara;

import handler.Handler;

public class LamparaPanelModificacion extends JPanel {
	private static final long serialVersionUID = 1L;
	private Handler handler;	
	private Lampara lampara=null;
	private Autoparte autoparte=null;
	private JTextField tfIngreso;
	private JTextField tfMarca;
	private JTextField tfModelo;
	private JTextField tfCosto;
	private JTextField tfCantDisp;
	
	private JTextField tfTamanio;
	private JTextField tfColor;
	PanelGestor panelGestor=null;
	private FuncionesAutopartes funcionesAutopartes=null;
	public LamparaPanelModificacion(){};
	public LamparaPanelModificacion(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		panelGestor=new PanelGestor();
		
		JPanel panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));			
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE LAMPARA",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);

		tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		funcionesAutopartes=new FuncionesAutopartes();
		List<Lampara> lstLampara = funcionesAutopartes.listarLamparas(handler);
		
		JScrollPane pane = new JScrollPane(panelGestor.cargarLamparaEnTabla(lstLampara));
		panelCentro.add(BorderLayout.CENTER,pane);
		panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte de la Lampara a modificar", tfIngreso));
		
		JButton btSubmit=new JButton("SUBMIT");											
		btSubmit.addActionListener(new ActionListener() {												
        public void actionPerformed(ActionEvent evt) {
    		if(validarCamposIngreso())
        	{
    			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
        	}else
        	{
        		if((lampara=buscarLampara())!=null)
        		{
        			modificarAutoparte();
        		}else
        		{        			
        		}
        	}
    	}});
		
		this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelCentro);
		this.add(BorderLayout.SOUTH,btSubmit);
	}
	
	private Lampara buscarLampara() {
		try
		{	
			lampara=handler.buscarLamparaPorIdAutoParte(Integer.valueOf(tfIngreso.getText()));
    		if(lampara==null)
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO LA LAMPARA");
    		}else
    		{	    			    			
    		}
		} catch (MiException e) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al buscar Lampara ID autoparte", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}    	
		return lampara;
	}
	
	private JPanel crearFormularioModificacion() {
		tfMarca=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		tfModelo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		tfCosto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		tfCantDisp=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		tfTamanio=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		tfColor=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel labelMarca=panelGestor.crearLabel("Marca ("+autoparte.getMarca()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelModelo=panelGestor.crearLabel("Modelo("+autoparte.getModelo()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelCosto=panelGestor.crearLabel("Costo("+autoparte.getCosto()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelCantDisp=panelGestor.crearLabel("Cantidad Disp.("+autoparte.getCantDisponible()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelTamanio=panelGestor.crearLabel("Tamanio("+lampara.getTamanio()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelColor=panelGestor.crearLabel("Material("+lampara.getColor()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		
		JTextField[] textFields=new JTextField[]{tfMarca,tfModelo,tfCosto,tfCantDisp,tfTamanio,tfColor};
		JLabel[] labels=new JLabel[]{labelMarca,labelModelo,labelCosto,labelCantDisp,labelTamanio,labelColor};
		JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
		return panelGrid;
	}
	
	private void modificarAutoparte() {
		//System.out.print("\n[modificarautoparte] pase\n");	//DEBUG
		this.setLayout(new BorderLayout());					
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(600,50),"MODIFICACION DE LAMPARA",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);		
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(600,400),null);
		
		llenarAutoparte();				
		JPanel panelGrid= crearFormularioModificacion();		
		panelResto.add(panelGrid);
		
		JButton btSubmit2=new JButton("SUBMIT");											
		btSubmit2.addActionListener(new ActionListener() {												
        public void actionPerformed(ActionEvent evt) {
        	try
    		{
    			guardarModificaciones();
    			
    			if(handler.updateLampara(lampara))
    			{	
    				if(handler.updateAutoparte(autoparte))
    				{
    					JOptionPane.showMessageDialog(handler.getFrame(), "LAMPARA MODIFICADA CORRECTAMENTE");
    				}else
    				{
    					JOptionPane.showMessageDialog(handler.getFrame(), "FALLO MODIFICACION AUTOPARTE NO ENCONTRADO EN DB");
    				}
    			}else
    			{
    				JOptionPane.showMessageDialog(handler.getFrame(), "FALLO MODIFICACION LAMPARA NO ENCONTRADO EN DB");
    			}				
    			handler.backToPrincipal();
    		}catch (MiException e1) {
    			JOptionPane.showMessageDialog(handler.getFrame(), "Error al modificar lampara", "Error", JOptionPane.ERROR_MESSAGE);
    			handler.backToPrincipal();
    		}
    	}});
		
		this.removeAll();
		this.repaint();
		this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelResto);
		this.add(BorderLayout.SOUTH,btSubmit2);
		
		handler.getFrame().switchPanel(this);
	}
		
	private void guardarModificaciones() {
		autoparte.setCantDisponible(!tfCantDisp.getText().equals("") ? Integer.valueOf(tfCantDisp.getText()):autoparte.getCantDisponible());							            	
		autoparte.setCosto(!tfCosto.getText().equals("")?Double.valueOf(tfCosto.getText()):autoparte.getCosto());
		autoparte.setMarca(!tfMarca.getText().equals("")?tfMarca.getText():autoparte.getMarca());
		autoparte.setModelo(!tfModelo.getText().equals("")?tfModelo.getText():autoparte.getModelo());
		lampara.setTamanio(!tfTamanio.getText().equals("")?tfTamanio.getText():lampara.getTamanio());
		lampara.setColor(!tfColor.getText().equals("")?tfColor.getText():lampara.getColor());
	}
	private void llenarAutoparte(){
		try
		{
			autoparte=handler.buscarAutopartePorId(lampara.getId());
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar Autoparte", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}

	private boolean validarCamposIngreso() {
		return (tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null);
	}
}
