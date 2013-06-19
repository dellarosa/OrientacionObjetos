package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import entities.Aceite;


public class AceitePanelAlta extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AceitePanelAlta()
	{
		
	}
	public AceitePanelAlta(final Handler handler)
	{
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor=new PanelGestor();
	
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
						
			JTextField[] textFields=new JTextField[]{areaMarca,areaModelo,areaCantLts,areaTipo,areaCosto,areaCant};
			JLabel[] labels=new JLabel[]{label1,label2,label3,label4,label5,label6};
			
			JPanel panelGrid=null;
			JPanel panelTitulo = null;
			JPanel panelResto=null;
			
			try
			{
				panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
				panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE ACEITE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			
			panelResto.add(panelGrid);
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener(){												
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	        		
	        	try
	        	{
	        		if(areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaTipo.getText().equals("")||areaCantLts.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals(""))
	        		{
	        			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
	        		}else
	        		{	
	        			Aceite aceite=new Aceite();
	        			
	        			aceite.setId(handler.buscarUltimaAutoparteId());	        			
	        			aceite.setAceite_ID(handler.buscarUltimoAceiteId());
	        			aceite.setCantDisponible(Integer.valueOf(areaCant.getText()));
	        			aceite.setCosto(Double.valueOf(areaCosto.getText()));
	        			aceite.setMarca(areaMarca.getText());
	        			aceite.setModelo(areaModelo.getText());
	        			aceite.setCantidadlitros(Integer.valueOf(areaCantLts.getText()));
	        			aceite.setTipoAceite(areaTipo.getText());
	        			aceite.setTipoAutoparte("aceite");
					
						if(handler.insertarAceite(aceite))
						{
							if(handler.insertarAutoparte(aceite))
								JOptionPane.showMessageDialog(handler.getFrame(), "ACEITE CREADO CORRECTAMENTE");
							else
								JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION ACEITE");
						}else
						{
							JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION ACEITE");
						}
						handler.backToPrincipal();
	        		}
		        }catch (MiException e1) {
					JOptionPane.showMessageDialog(handler.getFrame(), "Error interno Aceite", "Error", JOptionPane.ERROR_MESSAGE);
					handler.backToPrincipal();
				} 
	        }
			});
			
			this.add(BorderLayout.NORTH,panelTitulo);			
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);
		
	}
}
