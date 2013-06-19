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
import entities.Filtro;

public class FiltroPanelAlta extends JPanel{

	private static final long serialVersionUID = 1L;
	public FiltroPanelAlta()
	{}
	public FiltroPanelAlta(final Handler handler)
	{
		this.setLayout(new BorderLayout());
			PanelGestor panelGestor=new PanelGestor();			
			
			final JTextField areaMarca=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaModelo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaMaterial=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaTamanio=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			final JTextField areaCosto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			final JTextField areaCant=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			
			JLabel label1=panelGestor.crearLabel("Marca",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label2=panelGestor.crearLabel("Modelo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label3=panelGestor.crearLabel("Material",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label4=panelGestor.crearLabel("Tamanio",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label5=panelGestor.crearLabel("Costo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel label6=panelGestor.crearLabel("Cantidad Disp.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
						
			JTextField[] textFields=new JTextField[]{areaMarca,areaModelo,areaMaterial,areaTamanio,areaCosto,areaCant};
			JLabel[] labels=new JLabel[]{label1,label2,label3,label4,label5,label6};
			
			JPanel panelGrid=null;
			JPanel panelTitulo = null;
			JPanel panelResto=null;
			try
			{
				panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE FILTRO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);			
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
	        		if(areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaMaterial.getText().equals("")||areaTamanio.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals(""))
	        		{
	        			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
	        		}else
	        		{	
	        			//En el futuro podria buscar si ya existe el filtro
		        			Filtro filtro=new Filtro();
		        			
		        			filtro.setId(handler.buscarUltimaAutoparteId());
		        					        			
		        			filtro.setFiltro_ID(handler.buscarUltimoFiltroId());
		        			filtro.setCantDisponible(Integer.valueOf(areaCant.getText()));
		        			filtro.setCosto(Double.valueOf(areaCosto.getText()));
		        			filtro.setMarca(areaMarca.getText());
		        			filtro.setModelo(areaModelo.getText());
		        			filtro.setMaterial(areaMaterial.getText());
		        			filtro.setTamanio(areaTamanio.getText());
		        			filtro.setTipoAutoparte("filtro");
						
							if(handler.insertarFiltro(filtro))
							{
								if(handler.insertarAutoparte(filtro))
									JOptionPane.showMessageDialog(handler.getFrame(), "FILTRO CREADO CORRECTAMENTE");
								else
									JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION FILTRO");
							}else
							{
								JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION FILTRO");
							}
	        			handler.backToPrincipal();
	        		}
	        		
		        }catch (MiException e1) {
					JOptionPane.showMessageDialog(handler.getFrame(), "Error interno Filtro", "Error", JOptionPane.ERROR_MESSAGE);
					handler.backToPrincipal();
				} 
	        }
			});
			
			this.add(BorderLayout.NORTH,panelTitulo);			
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);

	}
}
