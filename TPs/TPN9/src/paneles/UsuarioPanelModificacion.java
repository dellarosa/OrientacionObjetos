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
	private JTextField areaName;				
	private JTextField areaEmail;
	private JTextField areaPassword;				
	private JTextField areaJerarquia;
	private Handler handler;
	PanelGestor panelGestor;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	private static final long serialVersionUID = 1L;
	public UsuarioPanelModificacion(){}
	
	public UsuarioPanelModificacion(final Handler handler)
	{		
		panelGestor=new PanelGestor();
		this.handler=handler;
		this.setLayout(new BorderLayout());		
		String seleccion = JOptionPane.showInputDialog(null,"","INGRESE USUARIO A MODIFICAR",JOptionPane.QUESTION_MESSAGE);
		if((seleccion==null)||(seleccion.equals("")))
    	{	
    		JOptionPane.showMessageDialog(null, "DATOS VACIOS");
    		
			this.add(panelGestor.armarPanelConImagen());
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
    			this.add(panelGestor.armarPanelConImagen());
			}else
			{	
				JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
				JPanel panelTitulo= panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				
				JPanel panelGrid = crearFormularioModificacion(panelGestor);
				panelResto.add(panelGrid);
				
				final JButton btSubmit=new JButton("SUBMIT");													
				btSubmit.addActionListener(new ActionListener() {	
					public void actionPerformed(ActionEvent arg0) {
						usuarioModificacion();
					}
				});
				
				this.add(BorderLayout.NORTH,panelTitulo);
				this.add(BorderLayout.CENTER,panelResto);
				this.add(BorderLayout.SOUTH,btSubmit);				
			}
    	}
	}
	
	private void usuarioModificacion(){
		try
		{
			getUsuario().setName(!areaName.getText().equals("") ? areaName.getText() : getUsuario().getName());							            	
			getUsuario().setEmail(!areaEmail.getText().equals("")?areaEmail.getText():getUsuario().getEmail());							
			getUsuario().setUsername(!areaUserName.getText().equals("") ? areaUserName.getText() : getUsuario().getUsername());							
			getUsuario().setPassword(!areaPassword.getText().equals("")?areaPassword.getText():getUsuario().getPassword());							
			getUsuario().setJerarquia(!areaJerarquia.getText().equals("")?areaJerarquia.getText():getUsuario().getJerarquia());
			getUsuario().setLogueado(0);
			
			getUsuario().setId(getUsuario().getId());
			if(handler.updateUsuario(getUsuario()))
			{	
				JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO CORRECTAMENTE");
			}else
			{
				JOptionPane.showMessageDialog(null, "FALLO MODIFICACION USUARIO");
			}
			
			handler.backToPrincipal();
			
		} catch (MiException e) {
			JOptionPane.showMessageDialog(null, "Error al modificar Usuario", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}
	
	private JPanel crearFormularioModificacion(PanelGestor panelGestor) {
		areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaUserName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaPassword=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaJerarquia=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel labelName=panelGestor.crearLabel("Nombre ("+getUsuario().getName()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelEmail=panelGestor.crearLabel("Email ("+getUsuario().getEmail()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelUserName=panelGestor.crearLabel("UserName ("+getUsuario().getUsername()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelPassword=panelGestor.crearLabel("Password ("+getUsuario().getPassword()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelJerarquia=panelGestor.crearLabel("Jerarquia ("+getUsuario().getJerarquia()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.LEFT_ALIGNMENT,null,Definiciones.line_blackline);

		JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaUserName,areaPassword,areaJerarquia};
		JLabel[] labels=new JLabel[]{labelName,labelEmail,labelUserName,labelPassword,labelJerarquia};
		
		JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
		return panelGrid;
	}
	
}
