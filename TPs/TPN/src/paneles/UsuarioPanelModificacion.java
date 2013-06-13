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

import main.MiFrame;
import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;
import entities.Usuario;

public class UsuarioPanelModificacion extends JPanel{

	private Usuario usuario;
	private JTextField areaUserName;
	
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
	public UsuarioPanelModificacion(final Handler handler)
	{
			this.setLayout(new BorderLayout());
			
			String seleccion=null;
			seleccion = JOptionPane.showInputDialog(null,"","INGRESE USUARIO A MODIFICAR",JOptionPane.QUESTION_MESSAGE);  
			
			
			if((seleccion==null)||(seleccion.equals("")))
	    	{	
	    		JOptionPane.showMessageDialog(null, "DATOS VACIOS");
	    	}
			else
	    	{
	    		try
	    		{
					setUsuario(handler.buscarUsuarioPorApodo(seleccion));
	    		
		    	} catch (MiException e1) {
					JOptionPane.showMessageDialog(null, "Error al buscar Usuario", "Error", JOptionPane.ERROR_MESSAGE);
					handler.backToPrincipal();
				}
	    		if(getUsuario()==null)
				{
	    			JOptionPane.showMessageDialog(null, "NO SE ENCONTRO SELECCION");
				}else
				{
					//System.out.print("\nUSUARIO ENCONTRADO");	
					PanelGestor panelGestor= new PanelGestor();
					JPanel panelResto=null;
					JPanel panelTitulo =null;
					try
					{
						panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
						panelTitulo= panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					} catch (MiException e1) {
						JOptionPane.showMessageDialog(null, "Error al crear paneles", "Error", JOptionPane.ERROR_MESSAGE);
						handler.backToPrincipal();
					}
					
					final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					areaUserName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaPassword=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaJerarquia=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
					
					JLabel labelName=panelGestor.crearLabel("Nombre ("+getUsuario().getName()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelEmail=panelGestor.crearLabel("Email ("+getUsuario().getEmail()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelUserName=panelGestor.crearLabel("UserName ("+getUsuario().getUsername()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelPassword=panelGestor.crearLabel("Password ("+getUsuario().getPassword()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelJerarquia=panelGestor.crearLabel("Jerarquia ("+getUsuario().getJerarquia()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.LEFT_ALIGNMENT,null,Definiciones.line_blackline);
	
					JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaUserName,areaPassword,areaJerarquia};
					JLabel[] labels=new JLabel[]{labelName,labelEmail,labelUserName,labelPassword,labelJerarquia};
					
					JPanel panelGrid=null;
					try
					{
						panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
					} catch (MiException e1) {
						JOptionPane.showMessageDialog(null, "Error al crear formulario", "Error", JOptionPane.ERROR_MESSAGE);
						handler.backToPrincipal();
					}
				
					panelResto.add(panelGrid);
					
					
					final JButton btSubmit=new JButton();
					btSubmit.setText("SUBMIT");								
					btSubmit.addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try
							{
								getUsuario().setName(!areaName.getText().equals("") ? areaName.getText() : getUsuario().getName());							            	
								getUsuario().setEmail(!areaEmail.getText().equals("")?areaEmail.getText():getUsuario().getEmail());							
								getUsuario().setUsername(!areaUserName.getText().equals("") ? areaUserName.getText() : getUsuario().getUsername());							
								getUsuario().setPassword(!areaPassword.getText().equals("")?areaPassword.getText():getUsuario().getPassword());							
								getUsuario().setJerarquia(!areaJerarquia.getText().equals("")?areaJerarquia.getText():getUsuario().getJerarquia());
								getUsuario().setLogueado(0);
								
								getUsuario().setId(getUsuario().getId());
								//System.out.print("\nUSUARIO UPDATE: "+ getUsuario().toString());
								
								if(handler.updateUsuario(getUsuario()))
								{	
									JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO CORRECTAMENTE");
									//frame.switchPanel(new JPanel());
									//handler.backToPrincipal();
								}else
								{
									JOptionPane.showMessageDialog(null, "FALLO MODIFICACION USUARIO");
								}
								
								handler.backToPrincipal();
								
							} catch (MiException e) {
								JOptionPane.showMessageDialog(null, "Error al buscar Usuario por apodo", "Error", JOptionPane.ERROR_MESSAGE);
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
