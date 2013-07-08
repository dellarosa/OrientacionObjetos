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

public class UsuarioPanelAlta extends PanelAlta 
{
	private JTextField areaName;				
	private JTextField areaEmail;				
	private JTextField areaUserName;				
	private JTextField areaPassword;				
	private JTextField areaJerarquia;

	private static final long serialVersionUID = 1L;
	public UsuarioPanelAlta(){}
	
	public UsuarioPanelAlta(final Handler handler)
	{
		super(handler,"CREACION DE USUARIO");	
	}
	
	public JPanel crearFormulario(PanelGestor panelGestor) {
		areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaUserName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaPassword=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaJerarquia=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel labelName=panelGestor.crearLabel("Nombre",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelEmail=panelGestor.crearLabel("Email",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelUserName=panelGestor.crearLabel("UserName",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelPassword=panelGestor.crearLabel("Password",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelJerarquia=panelGestor.crearLabel("Jerarquia",new Font(Font.SERIF,Font.BOLD,14),JLabel.LEFT_ALIGNMENT,null,Definiciones.line_blackline);

		JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaUserName,areaPassword,areaJerarquia};
		JLabel[] labels=new JLabel[]{labelName,labelEmail,labelUserName,labelPassword,labelJerarquia};
		
		JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
		return panelGrid;
	}
	
	public void alta(Handler handler) {
		Usuario userCreate=new Usuario();
		            		
		if(validarCamposVacios())
		{	
			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
		}else
		{	
    			try
				{
    				if(handler.buscarUsuarioPorApodo(areaUserName.getText())==null)
        			{
        				llenarObjetoUsuario(userCreate,handler);			
        				
        				if(handler.insertarUsuario(userCreate))
						{
							JOptionPane.showMessageDialog(handler.getFrame(), "USUARIO CREADO CORRECTAMENTE");
						}else
						{
							JOptionPane.showMessageDialog(handler.getFrame(), "FALLO CREACION USUARIO");
						}
        				handler.backToPrincipal();
        			}else
					{
						JOptionPane.showMessageDialog(handler.getFrame(), "EL USUARIO YA EXISTE, VUELVA A INGRESAR LOS DATOS");
					}
				} catch (MiException e1) {
					JOptionPane.showMessageDialog(handler.getFrame(), "Error interno Usuario", "Error", JOptionPane.ERROR_MESSAGE);
					handler.backToPrincipal();
				}            			
		}
	}
	private void llenarObjetoUsuario(Usuario userCreate,Handler handler) throws MiException {
		userCreate.setName(areaName.getText());							            	
		userCreate.setEmail(areaEmail.getText());							
		userCreate.setUsername(areaUserName.getText());							
		userCreate.setPassword(areaPassword.getText());							
		userCreate.setJerarquia(areaJerarquia.getText());
		userCreate.setLogueado(0);
		userCreate.setId(handler.buscarUltimoUsuarioId());
	}
	
	public boolean validarCamposVacios() {
		return areaName.getText().equals("")||areaPassword.getText().equals("")||areaUserName.getText().equals("")||areaEmail.getText().equals("")||areaJerarquia.getText().equals("");
	}
}
