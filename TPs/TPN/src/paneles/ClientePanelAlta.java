package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bo.ClienteBO;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import entities.Cliente;

public class ClientePanelAlta extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClientePanelAlta()
	{
		
	}
	public ClientePanelAlta(final Handler handler)
	{			
			PanelGestor panelGestor=new PanelGestor();
			
			final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaAuto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaPatente=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			
			JLabel labelName=panelGestor.crearLabel("Nombre",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel labelEmail=panelGestor.crearLabel("Email",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel labelAuto=panelGestor.crearLabel("Auto",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel labelPatente=panelGestor.crearLabel("Patente",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			
			JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaAuto,areaPatente};
			JLabel[] labels=new JLabel[]{labelName,labelEmail,labelAuto,labelPatente};
			
			JPanel panelGrid = null;
			JPanel panelTitulo=null;
			JPanel panelResto=null;
			
			try
			{
				panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
			}catch (MiException e1) {
				JOptionPane.showMessageDialog(handler.getFrame(), "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			
			panelResto.add(panelGrid);
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener(){												
	        @Override
	        public void actionPerformed(ActionEvent evt) {
				            		
	        		if(areaName.getText().equals("")||areaEmail.getText().equals("")||areaAuto.getText().equals("")||areaPatente.getText().equals(""))
	        		{
	        			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
	        		}else
	        		{	
	        			try
	        			{
		        			Cliente cliente=new Cliente();
		        			
		        			cliente.setNombre(areaName.getText());							            	
		        			cliente.setMail(areaEmail.getText());							
		        			cliente.setAuto(areaAuto.getText());
							cliente.setId(handler.buscarUltimoClienteId());			//podria buscar en la lista, pero ...
							cliente.setPatente(areaPatente.getText());
							
							if(handler.insertarCliente(cliente))
							{
								JOptionPane.showMessageDialog(handler.getFrame(), "CLIENTE CREADO CORRECTAMENTE");
								
							}else
							{
								JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION CLIENTE");
							}
							handler.backToPrincipal();
	        			}catch (MiException e1) {
	        				JOptionPane.showMessageDialog(handler.getFrame(), "Error al crear cliente", "Error", JOptionPane.ERROR_MESSAGE);
	        				handler.backToPrincipal();
	        			}
	        		}

		    }
			});
			
			this.add(BorderLayout.NORTH,panelTitulo);			
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);
	}
	
}
