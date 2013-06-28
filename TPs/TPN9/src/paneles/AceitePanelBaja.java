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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import entities.Aceite;
import entities.Autoparte;


public class AceitePanelBaja extends JPanel{

	private JTextField tfIngreso;
	private Handler handler;	
	private static final long serialVersionUID = 1L;
	public AceitePanelBaja(){}
	public AceitePanelBaja(final Handler handler)
	{
		this.handler=handler;		
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor= new PanelGestor();			
		
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE ACEITE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));
		
		tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);					
		List<Aceite> lstAceite = listarAceites(handler);			
		
		JScrollPane pane = new JScrollPane(panelGestor.cargarAceiteEnTabla(lstAceite));		
		panelCentro.add(BorderLayout.CENTER,pane);
		panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte del Aceiten a eliminar", tfIngreso));
	
		JButton btSubmit=new JButton("SUBMIT");											
		btSubmit.addActionListener(new ActionListener() {												
        public void actionPerformed(ActionEvent evt) {
        	if(validarIngreso())
    		{
    			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
    		}else
    		{
    			aceiteBaja();
    		}
        }

		});
		this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelCentro);
		this.add(BorderLayout.SOUTH,btSubmit);		
	}
	
	private void aceiteBaja() {		
		try
		{
			Aceite aceite =new Aceite();				
			aceite=handler.buscarAceitePorIdAutoParte(Integer.valueOf(tfIngreso.getText()));				
			if(aceite==null)
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO EL ACEITE");
			}else
			{
				Autoparte autoparteToDel=handler.buscarAutopartePorId(aceite.getId());
				JOptionPane optionPane= new JOptionPane();
	    		int response=JOptionPane.showConfirmDialog(handler.getFrame(), "ELIMINAR ACEITE Marca: "+autoparteToDel.getMarca()+" - Modelo: "+autoparteToDel.getModelo()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    		
	    		if(optionPane.YES_OPTION==response)
	    		{	
	    			if(handler.eliminarAutoparte(handler.buscarAutopartePorId(aceite.getId())))
	    			{
	    				if(handler.eliminarAceite(aceite))
	    				{
	    					JOptionPane.showMessageDialog(handler.getFrame(), "ACEITE ELIMINADO CORRECTAMENTE");			
	    				}
	        			else
						{
							JOptionPane.showMessageDialog(handler.getFrame(), "FALLO BAJA ACEITE");
						}
	    			}			        		
	    		}else if(optionPane.NO_OPTION==response)
	    		{	
	    			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ELIMINO ACEITE");
	    		}else
	    		{	
	    			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE SELECCIONO OPCION");
	    		}	
	    		handler.backToPrincipal();
			}
		} catch (MiException e) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al buscar aceite por ID autoparte", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		
	}
	
	private boolean validarIngreso() {
		return ((tfIngreso.getText()==null)||(tfIngreso.getText().equals(""))||(tfIngreso==null));
	}
	
	private List<Aceite> listarAceites(final Handler handler) {
		List<Autoparte> lstAutopartes=null;
		try
		{
			lstAutopartes= handler.cargaAutopartes();
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		List<Aceite> lstAceite=new ArrayList<Aceite>();
		for(Autoparte autop:lstAutopartes)
		{
			if(autop instanceof Aceite)
			{
				
				lstAceite.add((Aceite)autop);
			}
		}
		return lstAceite;
	}
}
