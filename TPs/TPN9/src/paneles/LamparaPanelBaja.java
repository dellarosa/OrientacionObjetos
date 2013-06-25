package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import bo.AutoparteBO;

import bo.LamparaBO;
import entities.Autoparte;

import entities.Lampara;

public class LamparaPanelBaja extends JPanel{
	
	private Handler handler;
	private JTextField tfIngreso;
	private static final long serialVersionUID = 1L;
	public LamparaPanelBaja(){}
	public LamparaPanelBaja(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor= new PanelGestor();
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE LAMPARA",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));
		
		tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);		
		List<Lampara> lstLampara = listarLamparas(handler);		
		panelCentro.add(BorderLayout.CENTER,panelGestor.cargarLamparaEnTabla(lstLampara));
		panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte de la Lampara a eliminar", tfIngreso));
	
		JButton btSubmit=new JButton("SUBMIT");											
		btSubmit.addActionListener(new ActionListener() {												
        public void actionPerformed(ActionEvent evt) {
        	if(validarCampos())
    		{
    			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
    		}else
    		{
        		lamparaBaja();
    		}
        }});
		
		this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelCentro);
		this.add(BorderLayout.SOUTH,btSubmit);		
	}
	private List<Lampara> listarLamparas(final Handler handler) {
		List<Autoparte> lstAutopartes=null;
		try
		{
			lstAutopartes= handler.cargaAutopartes();
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		List<Lampara> lstLampara=new ArrayList<Lampara>();
		for(Autoparte autop:lstAutopartes)
		{
			if(autop instanceof Lampara)
			{	
				lstLampara.add((Lampara)autop);
			}
		}
		return lstLampara;
	}
	
	private void lamparaBaja() {		
		Lampara lampara =new Lampara();
		Autoparte autoparteToDel=new Autoparte();
		try
		{
			lampara=handler.buscarLamparaPorIdAutoParte(Integer.valueOf(tfIngreso.getText()));
			
			if(lampara==null||autoparteToDel==null)
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO EL LAMPARA");
			}else
			{
				autoparteToDel=handler.buscarAutopartePorId(lampara.getId());
				JOptionPane optionPane= new JOptionPane();
	    		int response=JOptionPane.showConfirmDialog(handler.getFrame(), "ELIMINAR LAMPARA Marca: "+autoparteToDel.getMarca()+" - Modelo: "+autoparteToDel.getModelo()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    		
	    		if(optionPane.YES_OPTION==response)
	    		{	
	    			try{
		    			if(handler.eliminarAutoparte(handler.buscarAutopartePorId(lampara.getId())))
		    			{
		    				if(handler.eliminarLampara(lampara))
		    				{
		    					JOptionPane.showMessageDialog(handler.getFrame(), "LAMPARA ELIMINADA CORRECTAMENTE");			
		    				}
		        			else
							{
								JOptionPane.showMessageDialog(handler.getFrame(), "FALLO BAJA LAMPARA");
							}
		    			}	
		    		}catch (MiException e) {
		    			JOptionPane.showMessageDialog(handler.getFrame(), "Error al eliminar lampara por ID autoparte", "Error", JOptionPane.ERROR_MESSAGE);
		    			handler.backToPrincipal();
		    		}
	    		}else if(optionPane.NO_OPTION==response)
	    		{	
	    			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ELIMINO LAMPARA");
	    		}else
	    		{	
	    			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE SELECCIONO OPCION");
	    		}
	    		handler.backToPrincipal();
			}
		} catch (MiException e) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al eliminar Lampara", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}
	private boolean validarCampos() {
		return (tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null);
	}
}
