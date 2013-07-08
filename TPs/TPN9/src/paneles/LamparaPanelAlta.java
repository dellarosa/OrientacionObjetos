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
import entities.Lampara;

public class LamparaPanelAlta extends PanelAlta{

	private JTextField areaMarca;				
	private JTextField areaModelo;				
	private JTextField areaColor;				
	private JTextField areaTamanio;
	private JTextField areaCosto;
	private JTextField areaCant;

	private static final long serialVersionUID = 1L;
	
	public LamparaPanelAlta(){}
	public LamparaPanelAlta(final Handler handler)
	{
		super(handler,"CREACION DE LAMPARA");
	}
	public JPanel crearFormulario(PanelGestor panelGestor) {
		areaMarca=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaModelo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaColor=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaTamanio=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		areaCosto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		areaCant=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel label1=panelGestor.crearLabel("Marca",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label2=panelGestor.crearLabel("Modelo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label3=panelGestor.crearLabel("Color",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label4=panelGestor.crearLabel("Tamanio",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label5=panelGestor.crearLabel("Costo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label6=panelGestor.crearLabel("Cantidad Disp.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					
		JTextField[] textFields=new JTextField[]{areaMarca,areaModelo,areaColor,areaTamanio,areaCosto,areaCant};
		JLabel[] labels=new JLabel[]{label1,label2,label3,label4,label5,label6};
		JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
		return panelGrid;
	}
	public void alta(Handler handler) {
		try {					            		
    		if(validarCamposVacios())
    		{
    			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
    		}else
    		{	
    			Lampara lampara=new Lampara();
    			if(Integer.valueOf(areaCant.getText())>0)
    			{
	    			llenarObjetoLampara(handler, lampara);    			
					if(handler.insertarLampara(lampara))
					{
						if(handler.insertarAutoparte(lampara))
							JOptionPane.showMessageDialog(handler.getFrame(), "LAMPARA CREADO CORRECTAMENTE");
						else
							JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION LAMPARA");							
					}else
					{
						JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION LAMPARA");
					}					
    			}else
    			{
    				JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION ACEITE");        				
    			}
    			handler.backToPrincipal();
    		}
		}catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error interno Lampara", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}
	private void llenarObjetoLampara(Handler handler, Lampara lampara)
			throws MiException {
		lampara.setId(handler.buscarUltimaAutoparteId());
		lampara.setLampara_ID(handler.buscarUltimoLamparaId());
		lampara.setCantDisponible(Integer.valueOf(areaCant.getText()));
		lampara.setCosto(Double.valueOf(areaCosto.getText()));
		lampara.setMarca(areaMarca.getText());
		lampara.setModelo(areaModelo.getText());
		lampara.setColor(areaColor.getText());
		lampara.setTamanio(areaTamanio.getText());
		lampara.setTipoAutoparte("lampara");
	}
	public boolean validarCamposVacios() {
		return areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaColor.getText().equals("")||areaTamanio.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals("");
	}
}
