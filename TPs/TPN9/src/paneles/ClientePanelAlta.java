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
import entities.Cliente;

public class ClientePanelAlta extends PanelAlta{

	private static final long serialVersionUID = 1L;

	private JTextField areaName;
	private JTextField areaEmail;
	private JTextField areaAuto;
	private JTextField areaPatente;
	
	public ClientePanelAlta() {}
	
	public ClientePanelAlta(final Handler handler)
	{	
		super(handler,"CREACION DE CLIENTE");
	}

	public JPanel crearFormulario(PanelGestor panelGestor) {
		areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaAuto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaPatente=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel labelName=panelGestor.crearLabel("Nombre",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelEmail=panelGestor.crearLabel("Email",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelAuto=panelGestor.crearLabel("Auto",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelPatente=panelGestor.crearLabel("Patente",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		
		JTextField[] textFields = new JTextField[]{areaName,areaEmail,areaAuto,areaPatente};
		JLabel[] labels = new JLabel[]{labelName,labelEmail,labelAuto,labelPatente};
		
		JPanel panelGrid = panelGestor.crearPanelConFieldsEnForm(textFields, labels);
		return panelGrid;
	}

	public void alta(Handler handler) {
		System.out.print("\n[alta] llegue \n");
		if(validarCamposVacios()) {
			
			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
		
		} else {	
			try {
				Cliente cliente=new Cliente();
				
				System.out.print("\n[alta]Por buscar:"+areaPatente.getText()+" \n");
				if(handler.buscarClientePorPatente(areaPatente.getText())==null)		//PUEDE/DEBE ESTAR DENTRO DEL INSERTAR CLIENTE EN EL BO
				{
					System.out.print("\n[alta] Por llenar \n");
					
					llenarObjetoCliente(cliente,handler);
					System.out.print("\n[alta] Por insertar \n");
					handler.insertarCliente(cliente);
					JOptionPane.showMessageDialog(handler.getFrame(), "CLIENTE CREADO CORRECTAMENTE");
				}else
				{
					JOptionPane.showMessageDialog(handler.getFrame(), "AUTO YA EXISTENTE - NO SE CREO CLIENTE");
				}

			}catch (MiException e1) {
				JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION CLIENTE");
			}

			handler.backToPrincipal();
		}
	}

	private void llenarObjetoCliente(Cliente cliente,Handler handler) throws MiException {
		System.out.print("\n[alta] llenando \n");
		
		cliente.setNombre(areaName.getText());							            	
		cliente.setMail(areaEmail.getText());							
		cliente.setAuto(areaAuto.getText());
		cliente.setId(handler.buscarUltimoClienteId());			
		cliente.setPatente(areaPatente.getText());
	}

	public boolean validarCamposVacios() {
		return areaName.getText().equals("") ||
				areaEmail.getText().equals("") ||
				areaAuto.getText().equals("")||
				areaPatente.getText().equals("");
	}

}
