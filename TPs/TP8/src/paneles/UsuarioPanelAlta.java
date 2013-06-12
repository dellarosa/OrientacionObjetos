package paneles;

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

import bo.UsuarioBO;


import utils.Definiciones;
import utils.MiException;
import entities.Usuario;

public class UsuarioPanelAlta extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsuarioPanelAlta()
	{
		
	}
	public UsuarioPanelAlta(final JFrame frame)
	{
		try
		{
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			
			final UsuarioBO usuarioBo=new UsuarioBO();		
			this.setLayout(new BorderLayout());

			PanelGestor panelGestor=new PanelGestor();
			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);			
			
			final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaUserName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaPassword=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
			final JTextField areaJerarquia=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			
			JLabel labelName=panelGestor.crearLabel("Nombre",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel labelEmail=panelGestor.crearLabel("Email",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel labelUserName=panelGestor.crearLabel("UserName",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel labelPassword=panelGestor.crearLabel("Password",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
			JLabel labelJerarquia=panelGestor.crearLabel("Jerarquia",new Font(Font.SERIF,Font.BOLD,14),JLabel.LEFT_ALIGNMENT,null,Definiciones.line_blackline);

			JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaUserName,areaPassword,areaJerarquia};
			JLabel[] labels=new JLabel[]{labelName,labelEmail,labelUserName,labelPassword,labelJerarquia};
			
			JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);			
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);			
			panelResto.add(panelGrid);
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener(){												
            @Override
            public void actionPerformed(ActionEvent evt) {
            	Usuario userCreate=new Usuario();
            	try {					            		
            		if(areaName.getText().equals("")||areaPassword.getText().equals("")||areaUserName.getText().equals(""))
            		{	
            			JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
            		}else
            		{	
            			userCreate.setName(areaName.getText());							            	
    					userCreate.setEmail(areaEmail.getText());							
    					userCreate.setUsername(areaUserName.getText());							
    					userCreate.setPassword(areaPassword.getText());							
    					userCreate.setJerarquia(areaJerarquia.getText());
    					userCreate.setLogueado(0);
    					userCreate.setId(usuarioBo.buscarUltimoUsuarioId());			//podria buscar en la lista, pero ...
    					
						if(usuarioBo.insertarUsuario(userCreate))
						{
							JOptionPane.showMessageDialog(frame, "USUARIO CREADO CORRECTAMENTE");
														
						}else
						{
							JOptionPane.showMessageDialog(frame, "FALLO CREACION USUARIO");
						}
            		}
				} catch (MiException e) {
						throw e;					
	            } catch (Exception e) {
					throw new MiException("[CREATE USER]EXCEPTION : "+e);												
				}
            }
			});
			
			
			this.add(BorderLayout.NORTH,panelTitulo);			
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);
		
			frame.setContentPane(this);
			frame.setVisible(true);
							
		}catch(MiException e)
		{
			throw e;
		}catch(Exception e)
		{
			throw new MiException("\n[ALTA USUARIO]ALTA USUARIO Exception : "+e);
		}
			
	}
	
	
}
