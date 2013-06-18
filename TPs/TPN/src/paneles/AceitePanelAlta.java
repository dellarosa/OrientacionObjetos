package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class AceitePanelAlta extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AceitePanelAlta()
	{
		
	}
	public AceitePanelAlta(final JFrame frame)
	{
		final AceiteBO aceiteBo=new AceiteBO();
		final AutoparteBO autoparteBo=new AutoparteBO();
		
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		this.setLayout(new BorderLayout());
		
		PanelGestor panelGestor=new PanelGestor();
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);			
	
			final JTextField areaMarca=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaModelo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaTipo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaCantLts=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			final JTextField areaCosto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			final JTextField areaCant=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			
			JLabel label1=panelGestor.crearLabel("Marca",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label2=panelGestor.crearLabel("Modelo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label3=panelGestor.crearLabel("Cantidad Lts.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label4=panelGestor.crearLabel("Tipo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label5=panelGestor.crearLabel("Costo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label6=panelGestor.crearLabel("Cantidad Disp.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
						
			JTextField[] textFields=new JTextField[]{areaMarca,areaModelo,areaTipo,areaCantLts,areaCosto,areaCant};
			JLabel[] labels=new JLabel[]{label1,label2,label3,label4,label5,label6};
			
			JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE ACEITE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			panelResto.add(panelGrid);
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener(){												
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	        	try {					            		
	        		if(areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaTipo.getText().equals("")||areaCantLts.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals(""))
	        		{
	        			JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
	        		}else
	        		{	
	        			Aceite aceite=new Aceite();
	        			
	        			aceite.setAutoparteID(autoparteBo.buscarUltimaAutoparteId());
	        			aceite.setAceite_ID(aceiteBo.buscarUltimoAceiteId());
	        			aceite.setCantDisponible(Integer.valueOf(areaCant.getText()));
	        			aceite.setCosto(Double.valueOf(areaCosto.getText()));
	        			aceite.setMarca(areaMarca.getText());
	        			aceite.setModelo(areaModelo.getText());
	        			aceite.setCantidadlitros(Integer.valueOf(areaCantLts.getText()));
	        			aceite.setTipoAceite(areaTipo.getText());
	        			aceite.setTipoAutoparte("aceite");
					
						if(aceiteBo.insertarAceite(aceite))
						{
							JOptionPane.showMessageDialog(frame, "ACEITE CREADO CORRECTAMENTE");
							
						}else
						{
							JOptionPane.showMessageDialog(frame, "FALLO CREACION ACEITE");
						}
	        		}
				} catch (MiException e) {
						throw e;					
	            } catch (Exception e) {
					throw new MiException("[CREATE ACEITE]EXCEPTION : "+e);												
				}
		        }
			});
			
			this.add(BorderLayout.NORTH,panelTitulo);			
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);
		
			frame.setContentPane(this);
			frame.setVisible(true);
	}
}
