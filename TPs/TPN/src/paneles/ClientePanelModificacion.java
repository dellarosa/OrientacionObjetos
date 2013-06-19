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
import bo.UsuarioBO;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import entities.Cliente;

public class ClientePanelModificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ClientePanelModificacion()
	{
		
	}
	public ClientePanelModificacion(final Handler handler)
	{
		this.setLayout(new BorderLayout());
			PanelGestor panelGestor= new PanelGestor();
			String seleccion=null;
			seleccion = JOptionPane.showInputDialog(handler.getFrame(),"","Ingrese Cliente a modificar",JOptionPane.QUESTION_MESSAGE);  
			
			if((seleccion==null)||(seleccion.equals("")))
        	{
				JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
        	}else
        	{
        		try
        		{
        			setCliente(handler.buscarClientePorApodo(seleccion));	
	        	}catch (MiException e1) {
	    			JOptionPane.showMessageDialog(null, "Error al crear buscar cliente", "Error", JOptionPane.ERROR_MESSAGE);
	    			handler.backToPrincipal();
	    		}
        		System.out.print("\n[ClientePanelModificacion] ENCONTRE cliente\n");			//DEBUG
				if(getCliente()==null)
				{
					JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO CLIENTE");
        		}
				else
				{	
					final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaAuto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
					final JTextField areaPatente=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
					
					
					JLabel labelName=panelGestor.crearLabel("Nombre ("+getCliente().getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelEmail=panelGestor.crearLabel("Email("+getCliente().getMail()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelAuto=panelGestor.crearLabel("Auto("+getCliente().getAuto()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelPatente=panelGestor.crearLabel("Patente("+getCliente().getPatente()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					
					JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaAuto,areaPatente};
					JLabel[] labels=new JLabel[]{labelName,labelEmail,labelAuto,labelPatente};
					
					JPanel panelResto=null;
					JPanel panelGrid=null;
					JPanel panelTitulo=null;
					
					try{
												
						panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(600,400),null);
						
						panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
						
						panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					}catch (MiException e1) {
		    			JOptionPane.showMessageDialog(handler.getFrame(), "Error al crear buscar cliente o crear panel", "Error", JOptionPane.ERROR_MESSAGE);
		    			handler.backToPrincipal();
		    		}
					panelResto.add(panelGrid);
					
					System.out.print("\n[ClientePanelModificacion] paneles LISTOS\n");			//DEBUG
					
					final JButton btSubmit=new JButton();
					btSubmit.setText("SUBMIT");								
					btSubmit.addActionListener(new ActionListener() {												
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try
							{
								if(areaName.getText().equals("")&&areaEmail.getText().equals("")&&areaAuto.getText().equals(""))
			            		{	
									JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
			            		}else
			            		{
			            			getCliente().setNombre(!areaName.getText().equals("") ? areaName.getText() : getCliente().getNombre());							            	
			            			getCliente().setMail(!areaEmail.getText().equals("") ? areaEmail.getText(): getCliente().getMail());							
			            			getCliente().setAuto(!areaAuto.getText().equals("")? areaAuto.getText():getCliente().getAuto());
        						    getCliente().setPatente(!areaPatente.getText().equals("") ? areaPatente.getText():getCliente().getPatente());
        						    getCliente().setId(getCliente().getId());
        						    
									if(handler.updateCliente(getCliente()))
									{	
										JOptionPane.showMessageDialog(handler.getFrame(), "CLIENTE MODIFICADO CORRECTAMENTE");
									}else
									{
										JOptionPane.showMessageDialog(handler.getFrame(), "FALLO MODIFICACION CLIENTE NO ENCONTRADO EN DB");
									}
			            		}		
								handler.backToPrincipal();
							}catch (MiException e1) {
								JOptionPane.showMessageDialog(handler.getFrame(), "Error al modificar cliente", "Error", JOptionPane.ERROR_MESSAGE);
								handler.backToPrincipal();
							}
		            	
		            	}
					});
					
					this.add(BorderLayout.NORTH,panelTitulo);
					this.add(BorderLayout.CENTER,panelResto);
					this.add(BorderLayout.SOUTH,btSubmit);
				}
        	}	
	}
}
