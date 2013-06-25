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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import entities.Autoparte;
import entities.Filtro;

public class FiltroPanelBaja extends JPanel{
	
	private Handler handler;
	private JTextField tfIngreso;
	private static final long serialVersionUID = 1L;
	public FiltroPanelBaja(){}
	public FiltroPanelBaja(final Handler handler)
	{
		this.setLayout(new BorderLayout());	
		this.handler=handler;
			
		PanelGestor panelGestor= new PanelGestor();
		JPanel panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));			
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE FILTRO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);

		tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		List<Filtro> lstFiltro = listarFiltros();
		panelCentro.add(BorderLayout.CENTER,panelGestor.cargarFiltroEnTabla(lstFiltro));
		panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte del Filtro a eliminar", tfIngreso));
		
		JButton btSubmit=new JButton("SUBMIT");											
		btSubmit.addActionListener(new ActionListener() {												
        public void actionPerformed(ActionEvent evt) {
    		if(validarCampos())
        	{
    			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
        	}else
        	{
        		filtroBaja();
        	}
    	}});
		
		this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelCentro);
		this.add(BorderLayout.SOUTH,btSubmit);

	}
	
	private List<Filtro> listarFiltros() {
		List<Autoparte> lstAutopartes=null;
		
		try
		{
			lstAutopartes= handler.cargaAutopartes();
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		List<Filtro> lstFiltro=new ArrayList<Filtro>();
		for(Autoparte autop:lstAutopartes)
		{
			if(autop instanceof Filtro)
			{
				
				lstFiltro.add((Filtro)autop);
			}
		}
		return lstFiltro;
	}
	
	private void filtroBaja() {
		Filtro filtro =new Filtro();
		try
		{
			filtro=handler.buscarFiltroPorIdAutoParte(Integer.valueOf(tfIngreso.getText()));
    		if(filtro==null)
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO EL FILTRO");
    		}else
    		{	
    			Autoparte autoparteToDel=handler.buscarAutopartePorId(filtro.getId());
    			JOptionPane optionPane= new JOptionPane();
        		int response=JOptionPane.showConfirmDialog(handler.getFrame(), "ELIMINAR FILTRO Marca: "+autoparteToDel.getMarca()+" - Modelo: "+autoparteToDel.getModelo()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		if(optionPane.YES_OPTION==response)
        		{	
        			if(handler.eliminarAutoparte(autoparteToDel))
        			{	
        				if(handler.eliminarFiltro(filtro))
        				{
        					JOptionPane.showMessageDialog(handler.getFrame(), "FILTRO ELIMINADO CORRECTAMENTE");			
        				}
	        			else
						{
							JOptionPane.showMessageDialog(handler.getFrame(), "FALLO BAJA FILTRO");
						}
        			}			        		
        		}else if(optionPane.NO_OPTION==response)
        		{	
        			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ELIMINO FILTRO");
        		}else
        		{	
        			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE SELECCIONO OPCION");
        		}		
        		handler.backToPrincipal();
    		}
		} catch (MiException e) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al buscar filtro ID autoparte", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}    	
	}
	private boolean validarCampos() {
		return (tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null);
	}
}
