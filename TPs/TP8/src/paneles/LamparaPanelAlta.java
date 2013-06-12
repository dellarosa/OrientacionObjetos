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
import bo.AutoparteBO;
import bo.LamparaBO;
import entities.Lampara;

public class LamparaPanelAlta extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LamparaPanelAlta()
	{
		
	}
	public LamparaPanelAlta(final JFrame frame)
	{
		final LamparaBO lamparaBo=new LamparaBO();
		final AutoparteBO autoparteBo=new AutoparteBO();
		
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		this.setLayout(new BorderLayout());
		
		PanelGestor panelGestor=new PanelGestor();
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);			
	
			final JTextField areaMarca=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaModelo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaColor=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaTamanio=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			final JTextField areaCosto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			final JTextField areaCant=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			
			JLabel label1=panelGestor.crearLabel("Marca",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label2=panelGestor.crearLabel("Modelo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label3=panelGestor.crearLabel("Color",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label4=panelGestor.crearLabel("Tamanio",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label5=panelGestor.crearLabel("Costo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label6=panelGestor.crearLabel("Cantidad Disp.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
						
			JTextField[] textFields=new JTextField[]{areaMarca,areaModelo,areaColor,areaTamanio,areaCosto,areaCant};
			JLabel[] labels=new JLabel[]{label1,label2,label3,label4,label5,label6};
			
			JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE LAMPARA",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			panelResto.add(panelGrid);
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener(){												
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	        	try {					            		
	        		if(areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaColor.getText().equals("")||areaTamanio.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals(""))
	        		{
	        			JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
	        		}else
	        		{	
	        			Lampara lampara=new Lampara();
	        			
	        			lampara.setAutoparteID(autoparteBo.buscarUltimaAutoparteId());
	        			lampara.setLampara_ID(lamparaBo.buscarUltimoLamparaId());
	        			lampara.setCantDisponible(Integer.valueOf(areaCant.getText()));
	        			lampara.setCosto(Double.valueOf(areaCosto.getText()));
	        			lampara.setMarca(areaMarca.getText());
	        			lampara.setModelo(areaModelo.getText());
	        			lampara.setColor(areaColor.getText());
	        			lampara.setTamanio(areaTamanio.getText());
	        			lampara.setTipoAutoparte("lampara");
					
						if(lamparaBo.insertarLampara(lampara))
						{
							JOptionPane.showMessageDialog(frame, "LAMPARA CREADO CORRECTAMENTE");
							
						}else
						{
							JOptionPane.showMessageDialog(frame, "FALLO CREACION LAMPARA");
						}
	        		}
				} catch (MiException e) {
						throw e;					
	            } catch (Exception e) {
					throw new MiException("[CREATE LAMPARA]EXCEPTION : "+e);												
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
