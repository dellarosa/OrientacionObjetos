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

public class FiltroPanelAlta extends PanelAlta{

	private JTextField areaMarca;				
	private JTextField areaModelo;				
	private JTextField areaMaterial;				
	private JTextField areaTamanio;
	private JTextField areaCosto;
	private JTextField areaCant;

	private static final long serialVersionUID = 1L;
	public FiltroPanelAlta(){}
	public FiltroPanelAlta(final Handler handler)
	{
		super(handler,"CREACION DE FILTRO");

	}
	public JPanel crearFormulario(PanelGestor panelGestor) {
		areaMarca=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaModelo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaMaterial=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaTamanio=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		areaCosto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		areaCant=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel label1=panelGestor.crearLabel("Marca",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label2=panelGestor.crearLabel("Modelo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label3=panelGestor.crearLabel("Material",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label4=panelGestor.crearLabel("Tamanio",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label5=panelGestor.crearLabel("Costo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label6=panelGestor.crearLabel("Cantidad Disp.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					
		JTextField[] textFields=new JTextField[]{areaMarca,areaModelo,areaMaterial,areaTamanio,areaCosto,areaCant};
		JLabel[] labels=new JLabel[]{label1,label2,label3,label4,label5,label6};
		
		JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
		return panelGrid;
	}
	public void alta(Handler handler) {
		try
    	{
    		if(validarCamposVacios())
    		{
    			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
    		}else
    		{	
    			//En el futuro podria buscar si ya existe el filtro
        			Filtro filtro=new Filtro();
        			if(Integer.valueOf(areaCant.getText())>0)
        			{
	        			llenarObjetoFiltro(handler, filtro);
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
						
        			}else
        			{
        				JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION ACEITE");        				
        			}
        			handler.backToPrincipal();
    		}
    		
        }catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error interno Filtro", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}
	private void llenarObjetoFiltro(Handler handler, Filtro filtro)
			throws MiException {
		filtro.setId(handler.buscarUltimaAutoparteId());
				        			
		filtro.setFiltro_ID(handler.buscarUltimoFiltroId());
		filtro.setCantDisponible(Integer.valueOf(areaCant.getText()));
		filtro.setCosto(Double.valueOf(areaCosto.getText()));
		filtro.setMarca(areaMarca.getText());
		filtro.setModelo(areaModelo.getText());
		filtro.setMaterial(areaMaterial.getText());
		filtro.setTamanio(areaTamanio.getText());
		filtro.setTipoAutoparte("filtro");
	}
	public boolean validarCamposVacios() {
		return areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaMaterial.getText().equals("")||areaTamanio.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals("");
	}
	
}
