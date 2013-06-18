package paneles;

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
	public LamparaPanelBaja(final JFrame frame)
	{
		try{
			this.setLayout(new BorderLayout());
			
			final LamparaBO lamparaBo=new LamparaBO();			
			final AutoparteBO autoparteBo=new AutoparteBO();
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			
			PanelGestor panelGestor= new PanelGestor();
			
			JPanel panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));
			
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE FILTRO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			
			final JTextField tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			//panelResto.add(panelGestor.crearPanelOpcion("Ingrese id Autoparte a eliminar", tfIngreso));
			
			
			//panelCentro.add(BorderLayout.CENTER,panelGestor.cargarAceiteEnTabla(aceiteBo.cargarAceites()));
			
			List<Autoparte> lstAutopartes= autoparteBo.cargaAutopartes();
			List<Lampara> lstLampara=new ArrayList<Lampara>();
			for(Autoparte autop:lstAutopartes)
			{
				if(autop instanceof Lampara)
				{
					
					lstLampara.add((Lampara)autop);
				}
			}
			
			
			panelCentro.add(BorderLayout.CENTER,panelGestor.cargarLamparaEnTabla(lstLampara));
			panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte del Filtro a eliminar", tfIngreso));
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener() {												
            @Override
            public void actionPerformed(ActionEvent evt) {
            	
		            
					if((tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null))
		        	{
						JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
		        	}else
		        	{
		        		Lampara lampara =new Lampara();
		        		
		        		lampara=lamparaBo.buscarLamparaPorIdAutoParte(Integer.valueOf(tfIngreso.getText()));
		        		
		        		if(lampara==null)
						{
							JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO EL FILTRO");
		        		}else
		        		{
		        			
		        			JOptionPane optionPane= new JOptionPane();
			        		int response=JOptionPane.showConfirmDialog(frame, "ELIMINAR FILTRO Marca: "+lampara.getMarca()+" - Modelo: "+lampara.getModelo()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			        		
			        		if(optionPane.YES_OPTION==response)
			        		{	
			        			if(autoparteBo.eliminarAutoparte(lampara))
			        			{
			        				if(lamparaBo.eliminarLampara(lampara))
			        				{
			        					JOptionPane.showMessageDialog(frame, "FILTRO ELIMINADO CORRECTAMENTE");			
			        				}
				        			else
									{
										JOptionPane.showMessageDialog(frame, "FALLO BAJA FILTRO");
									}
			        			}			        		
			        		}else if(optionPane.NO_OPTION==response)
			        		{	
			        			//TODO
			        		}else
			        		{	
			        			JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO FILTRO");
			        		}							
		        		}
		        		
					}	
            }});
			
			
			this.add(BorderLayout.NORTH,panelTitulo);
			this.add(BorderLayout.CENTER,panelCentro);
			this.add(BorderLayout.SOUTH,btSubmit);
						
			frame.setContentPane(this);
			frame.setVisible(true);
			
		} catch (MiException e) {
			throw e;					
        } catch (Exception e) {
			throw new MiException("[BAJA ACEITE]EXCEPTION : "+e);												
		}
	}
}
