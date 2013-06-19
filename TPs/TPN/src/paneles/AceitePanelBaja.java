package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import bo.AceiteBO;
import bo.AutoparteBO;
import entities.Aceite;
import entities.Autoparte;


public class AceitePanelBaja extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AceitePanelBaja()
	{
		
	}
	public AceitePanelBaja(final Handler handler)
	{
		this.setLayout(new BorderLayout());
			PanelGestor panelGestor= new PanelGestor();			
			JPanel panelTitulo=null;
			JPanel panelCentro=null;
			
			try
			{
				panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));			
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE ACEITE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);			
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			final JTextField tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			//panelResto.add(panelGestor.crearPanelOpcion("Ingrese id Autoparte a eliminar", tfIngreso));
			
			
			//panelCentro.add(BorderLayout.CENTER,panelGestor.cargarAceiteEnTabla(aceiteBo.cargarAceites()));
			
			List<Autoparte> lstAutopartes=null;
			try
			{
				lstAutopartes= handler.cargaAutopartes();
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
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
			
			try
			{
				panelCentro.add(BorderLayout.CENTER,panelGestor.cargarAceiteEnTabla(lstAceite));
				panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte del Aceiten a eliminar", tfIngreso));
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener() {												
            @Override
            public void actionPerformed(ActionEvent evt) {
            	
		            
					if((tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null))
		        	{
						JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
		        	}else
		        	{
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
				        			//TODO
				        			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ELIMINO ACEITE");
				        		}else
				        		{	
				        			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE SELECCIONO OPCION");
				        		}	
				        		handler.backToPrincipal();
			        		}
		        		} catch (MiException e) {
							JOptionPane.showMessageDialog(null, "Error al buscar aceite por ID autoparte", "Error", JOptionPane.ERROR_MESSAGE);
							handler.backToPrincipal();
						}
					}	
            }});
			
			
			this.add(BorderLayout.NORTH,panelTitulo);
			this.add(BorderLayout.CENTER,panelCentro);
			this.add(BorderLayout.SOUTH,btSubmit);		
	}
}
