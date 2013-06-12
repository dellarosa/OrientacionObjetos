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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
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
	public AceitePanelBaja(final JFrame frame)
	{
		try{
			this.setLayout(new BorderLayout());
			
			final AceiteBO aceiteBo=new AceiteBO();			
			final AutoparteBO autoparteBo=new AutoparteBO();
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			
			PanelGestor panelGestor= new PanelGestor();
			
			JPanel panelCentro=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));
			
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA DE ACEITE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			
			final JTextField tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			//panelResto.add(panelGestor.crearPanelOpcion("Ingrese id Autoparte a eliminar", tfIngreso));
			
			
			//panelCentro.add(BorderLayout.CENTER,panelGestor.cargarAceiteEnTabla(aceiteBo.cargarAceites()));
			
			List<Autoparte> lstAutopartes= autoparteBo.cargaAutopartes();
			List<Aceite> lstAceite=new ArrayList<Aceite>();
			for(Autoparte autop:lstAutopartes)
			{
				if(autop instanceof Aceite)
				{
					
					lstAceite.add((Aceite)autop);
				}
			}
			
			
			panelCentro.add(BorderLayout.CENTER,panelGestor.cargarAceiteEnTabla(lstAceite));
			panelCentro.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese id Autoparte del Aceiten a eliminar", tfIngreso));
			
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
		        		Aceite aceite =new Aceite();
		        		
		        		aceite=aceiteBo.buscarAceitePorIdAutoParte(Integer.valueOf(tfIngreso.getText()));
		        		
		        		if(aceite==null)
						{
							JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO EL ACEITE");
		        		}else
		        		{
		        			
		        			JOptionPane optionPane= new JOptionPane();
			        		int response=JOptionPane.showConfirmDialog(frame, "ELIMINAR ACEITE Marca: "+aceite.getMarca()+" - Modelo: "+aceite.getModelo()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			        		
			        		if(optionPane.YES_OPTION==response)
			        		{	
			        			if(autoparteBo.eliminarAutoparte(aceite))
			        			{
			        				if(aceiteBo.eliminarAceite(aceite))
			        				{
			        					JOptionPane.showMessageDialog(frame, "ACEITE ELIMINADO CORRECTAMENTE");			
			        				}
				        			else
									{
										JOptionPane.showMessageDialog(frame, "FALLO BAJA ACEITE");
									}
			        			}			        		
			        		}else if(optionPane.NO_OPTION==response)
			        		{	
			        			//TODO
			        		}else
			        		{	
			        			JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO ACEITE");
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
