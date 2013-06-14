package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.MiFrame;
import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;
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
	public UsuarioPanelAlta(final Handler handler)
	{
			this.setLayout(new BorderLayout());

			PanelGestor panelGestor=new PanelGestor();
			JPanel panelResto=null;
			JPanel panelTitulo=null;
			
			try
			{
				panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
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
			
			JPanel panelGrid=null;
			try
			{
				panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);			
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}			
			
			panelResto.add(panelGrid);
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addMouseListener(new MouseAdapter() {												
            @Override
            public void mouseReleased(MouseEvent evt) {
            	Usuario userCreate=new Usuario();
				            		
            		if(areaName.getText().equals("")||areaPassword.getText().equals("")||areaUserName.getText().equals("")||areaEmail.getText().equals("")||areaJerarquia.getText().equals(""))
            		{	
            			JOptionPane.showMessageDialog(null, "DATOS VACIOS");
            		}else
            		{	
	            			try
	    					{
	            				if(handler.buscarUsuarioPorApodo(areaUserName.getText())==null)
	                			{
		            				userCreate.setName(areaName.getText());							            	
		        					userCreate.setEmail(areaEmail.getText());							
		        					userCreate.setUsername(areaUserName.getText());							
		        					userCreate.setPassword(areaPassword.getText());							
		        					userCreate.setJerarquia(areaJerarquia.getText());
		        					userCreate.setLogueado(0);
		            				userCreate.setId(handler.buscarUltimoUsuarioId());			//podria buscar en la lista, pero ...
		            				
		            				if(handler.insertarUsuario(userCreate))
		    						{
		    							JOptionPane.showMessageDialog(null, "USUARIO CREADO CORRECTAMENTE");
		    							
		    							//frame.switchPanel(new JPanel());
		    						}else
		    						{
		    							JOptionPane.showMessageDialog(null, "FALLO CREACION USUARIO");
		    						}
		            				handler.backToPrincipal();
	                			}else
	    						{
	    							JOptionPane.showMessageDialog(null, "EL USUARIO YA EXISTE, VUELVA A INGRESAR LOS DATOS");
	    						}
	    					} catch (MiException e1) {
	    						JOptionPane.showMessageDialog(null, "Error interno Usuario", "Error", JOptionPane.ERROR_MESSAGE);
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
