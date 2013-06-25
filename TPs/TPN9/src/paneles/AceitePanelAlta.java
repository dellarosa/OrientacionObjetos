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

	private Handler handler;
	private JTextField areaMarca;				
	private JTextField areaModelo;				
	private JTextField areaTipo;				
	private JTextField areaCantLts;
	private JTextField areaCosto;
	private JTextField areaCant;
	
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	private static final long serialVersionUID = 1L;
	public AceitePanelAlta()
	{
		
	}
	public AceitePanelAlta(final Handler handler)
	{
		this.setHandler(handler);
		this.setLayout(new BorderLayout());
		
		PanelGestor panelGestor=new PanelGestor();
	
			JPanel panelGrid = crearFormularioAlta(panelGestor);			
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE ACEITE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
			
			panelResto.add(panelGrid);
			
			JButton btSubmit=new JButton("SUBMIT");											
			btSubmit.addActionListener(new ActionListener(){												
	        public void actionPerformed(ActionEvent evt) {
	        		
	        	aceiteAlta(); 
	        }
			});
			
			this.add(BorderLayout.NORTH,panelTitulo);			
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);
		
	}
	private JPanel crearFormularioAlta(PanelGestor panelGestor) {
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
	private void aceiteAlta() {
		try
    	{
    		if(validarCampos())
    		{
    			JOptionPane.showMessageDialog(getHandler().getFrame(), "DATOS VACIOS");
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
			
				if(getHandler().insertarAceite(aceite))
				{
					if(getHandler().insertarAutoparte(aceite))
						JOptionPane.showMessageDialog(getHandler().getFrame(), "ACEITE CREADO CORRECTAMENTE");
					else
						JOptionPane.showMessageDialog(getHandler().getFrame(), "FALLO CREACION ACEITE");
				}else
				{
					JOptionPane.showMessageDialog(getHandler().getFrame(), "FALLO CREACION ACEITE");
				}
				getHandler().backToPrincipal();
    		}
        }catch (MiException e1) {
			JOptionPane.showMessageDialog(getHandler().getFrame(), "Error interno Aceite", "Error", JOptionPane.ERROR_MESSAGE);
			getHandler().backToPrincipal();
		}
	}
	private boolean validarCampos() {
		return areaMarca.getText().equals("")||areaModelo.getText().equals("")||areaTipo.getText().equals("")||areaCantLts.getText().equals("")||areaCosto.getText().equals("")||areaCant.getText().equals("");
	}
}
