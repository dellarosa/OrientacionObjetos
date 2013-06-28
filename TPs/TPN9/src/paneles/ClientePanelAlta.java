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

public class ClientePanelAlta extends JPanel{

	private static final long serialVersionUID = 1L;

	private JTextField areaName;
	private JTextField areaEmail;
	private JTextField areaAuto;
	private JTextField areaPatente;
	
	private Handler handler;
	
	public ClientePanelAlta() {}
	
	public ClientePanelAlta(final Handler handler)
	{			
		this.handler = handler;		
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor = new PanelGestor();
		
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelGrid = crearFormulario(panelGestor);
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
		
		panelResto.add(panelGrid);
		
		JButton btSubmit = new JButton("SUBMIT");								
		btSubmit.addActionListener(new ActionListener(){												
	        public void actionPerformed(ActionEvent evt) {
	        		altaCliente();
		    }
		});
		
		this.add(BorderLayout.NORTH, panelTitulo);			
		this.add(BorderLayout.CENTER, panelResto);
		this.add(BorderLayout.SOUTH, btSubmit);
	}

	private JPanel crearFormulario(PanelGestor panelGestor) {
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

	private void altaCliente() {
		if(validarCamposVacios()) {
			
			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
		
		} else {	
			try {
				Cliente cliente=new Cliente();
				if(handler.buscarClientePorPatente(areaPatente.getText())==null)		//PUEDE/DEBE ESTAR DENTRO DEL INSERTAR CLIENTE EN EL BO
				{
					crearClienteNuevo(cliente);
					
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

	private void crearClienteNuevo(Cliente cliente) throws MiException {
		cliente.setNombre(areaName.getText());							            	
		cliente.setMail(areaEmail.getText());							
		cliente.setAuto(areaAuto.getText());
		cliente.setId(handler.buscarUltimoClienteId());			
		cliente.setPatente(areaPatente.getText());
	}

	private boolean validarCamposVacios() {
		return areaName.getText().equals("") ||
				areaEmail.getText().equals("") ||
				areaAuto.getText().equals("")||
				areaPatente.getText().equals("");
	}
}
