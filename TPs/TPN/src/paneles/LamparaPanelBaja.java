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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LamparaPanelBaja()
	{
		
	}
	public LamparaPanelBaja(final Handler handler)
	{
		this.setLayout(new BorderLayout());
			PanelGestor panelGestor= new PanelGestor();
			JPanel panelTitulo=null;
			JPanel panelCentro=null;
			try
			{
				panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));					
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE FILTRO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
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
			List<Lampara> lstLampara=new ArrayList<Lampara>();
			for(Autoparte autop:lstAutopartes)
			{
				if(autop instanceof Lampara)
				{
					
					lstLampara.add((Lampara)autop);
				}
			}
			
			try
			{
				panelCentro.add(BorderLayout.CENTER,panelGestor.cargarLamparaEnTabla(lstLampara));
				panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte del Filtro a eliminar", tfIngreso));
			}catch (MiException e1) {
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
		        		Lampara lampara =new Lampara();
		        		try
		        		{
			        		lampara=handler.buscarLamparaPorIdAutoParte(Integer.valueOf(tfIngreso.getText()));
			        		
			        		if(lampara==null)
							{
								JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO EL LAMPARA");
			        		}else
			        		{
			        			Autoparte autoparteToDel=handler.buscarAutopartePorId(lampara.getId());	
			        			JOptionPane optionPane= new JOptionPane();
				        		int response=JOptionPane.showConfirmDialog(handler.getFrame(), "ELIMINAR LAMPARA Marca: "+autoparteToDel.getMarca()+" - Modelo: "+autoparteToDel.getModelo()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				        		
				        		if(optionPane.YES_OPTION==response)
				        		{	
				        			if(handler.eliminarAutoparte(handler.buscarAutopartePorId(lampara.getId())))
				        			{
				        				if(handler.eliminarLampara(lampara))
				        				{
				        					JOptionPane.showMessageDialog(handler.getFrame(), "LAMPARA ELIMINADO CORRECTAMENTE");			
				        				}
					        			else
										{
											JOptionPane.showMessageDialog(handler.getFrame(), "FALLO BAJA LAMPARA");
										}
				        			}			        		
				        		}else if(optionPane.NO_OPTION==response)
				        		{	
				        			//
				        			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ELIMINO LAMPARA");
				        		}else
				        		{	
				        			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE SELECCIONO OPCION");
				        		}
				        		handler.backToPrincipal();
			        		}
		        		} catch (MiException e) {
							JOptionPane.showMessageDialog(null, "Error al buscar lampara por ID autoparte", "Error", JOptionPane.ERROR_MESSAGE);
							handler.backToPrincipal();
						}
					}	
            }});
			
			
			this.add(BorderLayout.NORTH,panelTitulo);
			this.add(BorderLayout.CENTER,panelCentro);
			this.add(BorderLayout.SOUTH,btSubmit);		
	}
}
