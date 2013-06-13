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

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;
import entities.Usuario;

public class UsuarioPanelModificacion extends JPanel{

	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	private static final long serialVersionUID = 1L;
	public UsuarioPanelModificacion()
	{
		
	}
	public UsuarioPanelModificacion(final JFrame frame)
	{
		try
		{
			final UsuarioBO usuarioBo=new UsuarioBO();
			
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
	
			this.setLayout(new BorderLayout());
			
			String seleccion=null;
			seleccion = JOptionPane.showInputDialog(frame,"","INGRESE USUARIO A MODIFICAR",JOptionPane.QUESTION_MESSAGE);  
			
			
			if((seleccion==null)||(seleccion.equals("")))
	    	{	
	    		JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
	    	}
			else
	    	{
	    		
	    		setUsuario(usuarioBo.buscarUsuarioPorApodo(seleccion));
	    		
	    		if(getUsuario()==null)
				{
	    			JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO SELECCION");
				}else
				{
					System.out.print("\nUSUARIO ENCONTRADO");	
					PanelGestor panelGestor= new PanelGestor();
					
					JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
					
					final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaUserName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaPassword=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaJerarquia=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
					
					JLabel labelName=panelGestor.crearLabel("Nombre ("+getUsuario().getName()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelEmail=panelGestor.crearLabel("Email ("+getUsuario().getEmail()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelUserName=panelGestor.crearLabel("UserName ("+getUsuario().getUsername()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelPassword=panelGestor.crearLabel("Password ("+getUsuario().getPassword()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelJerarquia=panelGestor.crearLabel("Jerarquia ("+getUsuario().getJerarquia()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.LEFT_ALIGNMENT,null,Definiciones.line_blackline);
	
					JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaUserName,areaPassword,areaJerarquia};
					JLabel[] labels=new JLabel[]{labelName,labelEmail,labelUserName,labelPassword,labelJerarquia};
					
					JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
				
					panelResto.add(panelGrid);
					
					JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					
					final JButton btSubmit=new JButton();
					btSubmit.setText("SUBMIT");								
					btSubmit.addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try
							{
								getUsuario().setName(areaName.getText());							            	
								getUsuario().setEmail(areaEmail.getText());							
								getUsuario().setUsername(areaUserName.getText());							
								getUsuario().setPassword(areaPassword.getText());							
								getUsuario().setJerarquia(areaJerarquia.getText());
								getUsuario().setLogueado(0);									
								
								if(usuarioBo.updateUsuario(getUsuario()))
								{	
									JOptionPane.showMessageDialog(frame, "USUARIO MODIFICADO CORRECTAMENTE");
								}else
								{
									JOptionPane.showMessageDialog(frame, "FALLO MODIFICACION USUARIO");
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
		}catch(MiException e)
		{
			throw e;
		}catch(Exception e)
		{
			throw new MiException("\n[MODIFICACION USUARIO]USUARIO MODIFICACION Exception : "+e);
		}
	}
	
}
