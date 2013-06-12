package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import entities.Cliente;

public class ClientePanelModificacion extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClientePanelModificacion()
	{
		
	}
	public ClientePanelModificacion(final JFrame frame)
	{
		try
		{
			final ClienteBO clienteBo=new ClienteBO();
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
	
			this.setLayout(new BorderLayout());
			
			String seleccion=null;
			seleccion = JOptionPane.showInputDialog(frame,"","Ingrese Cliente a modificar",JOptionPane.QUESTION_MESSAGE);  
			
			if((seleccion==null)||(seleccion.equals("")))
        	{
				JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
        	}else
        	{
        		final Cliente clienteEncontrado=clienteBo.buscarClientePorApodo(seleccion);
				if(clienteEncontrado==null)
				{
					JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO CLIENTE");
        		}
				else
				{
					
					PanelGestor panelGestor= new PanelGestor();					
					JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
					
					final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaAuto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
					final JTextField areaPatente=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
					
					
					JLabel labelName=panelGestor.crearLabel("Nombre ("+clienteEncontrado.getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelEmail=panelGestor.crearLabel("Email("+clienteEncontrado.getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelAuto=panelGestor.crearLabel("Auto("+clienteEncontrado.getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelPatente=panelGestor.crearLabel("Patente("+clienteEncontrado.getPatente()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					
					JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaAuto};
					JLabel[] labels=new JLabel[]{labelName,labelEmail,labelAuto,labelPatente};
					
					JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
					JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					
					panelResto.add(panelGrid);
					
					final JButton btSubmit=new JButton();
					btSubmit.setText("SUBMIT");								
					btSubmit.addMouseListener(new MouseAdapter() {												
						@Override
						public void mouseReleased(MouseEvent evt) {
							try
							{
								if(areaName.getText().equals("")&&areaEmail.getText().equals("")&&areaAuto.getText().equals(""))
			            		{	
									JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
			            		}else
			            		{
			            			clienteEncontrado.setNombre(areaName.getText());							            	
			            			clienteEncontrado.setMail(areaEmail.getText());							
			            			clienteEncontrado.setAuto(areaAuto.getText());
			            			clienteEncontrado.setId(clienteBo.buscarUltimoClienteId());		
			            			clienteEncontrado.setPatente(areaPatente.getText());
			            			
									if(clienteBo.updateCliente(clienteEncontrado))
									{	
										JOptionPane.showMessageDialog(frame, "CLIENTE MODIFICADO CORRECTAMENTE");
									}else
									{
										JOptionPane.showMessageDialog(frame, "FALLO MODIFICACION CLIENTE NO ENCONTRADO EN DB");
									}
			            		}										
							}catch(MiException e)
							{
								throw e;
							}catch(Exception e)
							{
								throw new MiException("[MODIFICAR USER] EXCEPTION : "+e);
							}
		            	
		            	}
					});
					
					this.add(BorderLayout.NORTH,panelTitulo);
					this.add(BorderLayout.CENTER,panelResto);
					this.add(BorderLayout.SOUTH,btSubmit);
					
					frame.setContentPane(this);
					frame.setVisible(true);
					
				}
        	}	
		} catch (MiException e) {
			throw e;					
        } catch (Exception e) {
			throw new MiException("[MODIFICAR CLIENTE]EXCEPTION : "+e);												
		}	
	}
}
