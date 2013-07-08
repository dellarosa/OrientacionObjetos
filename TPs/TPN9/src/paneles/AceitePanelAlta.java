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


public class AceitePanelAlta extends PanelAlta{

	private JTextField areaMarca;				
	private JTextField areaModelo;				
	private JTextField areaTipo;				
	private JTextField areaCantLts;
	private JTextField areaCosto;
	private JTextField areaCant;

	private static final long serialVersionUID = 1L;
	public AceitePanelAlta(){}
	public AceitePanelAlta(final Handler handler)
	{
		super(handler,"CREACION DE ACEITE");
		
	}
	public JPanel crearFormulario(PanelGestor panelGestor) {
		areaMarca=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaModelo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaTipo=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaCantLts=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		areaCosto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		areaCant=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel label1=panelGestor.crearLabel("Marca",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label2=panelGestor.crearLabel("Modelo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label3=panelGestor.crearLabel("Cantidad Lts.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label4=panelGestor.crearLabel("Tipo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label5=panelGestor.crearLabel("Costo",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel label6=panelGestor.crearLabel("Cantidad Disp.",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					
		JTextField[] textFields=new JTextField[]{areaMarca,areaModelo,areaCantLts,areaTipo,areaCosto,areaCant};
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
    			Aceite aceite=new Aceite();
    			
    			if(Integer.valueOf(areaCant.getText())>0)
    			{
	    			llenarObjetoAceite(handler, aceite);
    			
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
    			else
    			{
    				JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION ACEITE");
    				handler.backToPrincipal();
    			}
    		}
        }catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error interno Aceite", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}
	private void llenarObjetoAceite(Handler handler, Aceite aceite)	throws MiException {
		aceite.setId(handler.buscarUltimaAutoparteId());	        			
		aceite.setAceite_ID(handler.buscarUltimoAceiteId());
		aceite.setCantDisponible(Integer.valueOf(areaCant.getText()));
		aceite.setCosto(Double.valueOf(areaCosto.getText()));
		aceite.setMarca(areaMarca.getText());
		aceite.setModelo(areaModelo.getText());
		aceite.setCantidadlitros(Integer.valueOf(areaCantLts.getText()));
		aceite.setTipoAceite(areaTipo.getText());
		aceite.setTipoAutoparte("aceite");
	}
	public boolean validarCamposVacios() {
		return areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaTipo.getText().equals("")||areaCantLts.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals("");
	}
}
