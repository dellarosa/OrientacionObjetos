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

import bo.UsuarioBO;

import utils.Definiciones;
import utils.MiException;
import entities.Usuario;

public class UsuarioPanelBaja extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsuarioPanelBaja()
	{
		
	}
	public UsuarioPanelBaja(final JFrame frame)
	{			
		try
		{
			final UsuarioBO usuarioBo=new UsuarioBO();
			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			
			PanelGestor panelGestor= new PanelGestor();
			
			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.gray,new Dimension(400,400),null);
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			
			panelResto.add(panelGestor.cargarUsuariosEnTabla(usuarioBo.cargarUsuarios()));
			final JTextField tfUsuario=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			panelResto.add(panelGestor.crearPanelOpcion("Ingrese UserName de usuario a eliminar", tfUsuario));
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addMouseListener(new MouseAdapter() {												
            @Override
            public void mouseReleased(MouseEvent evt) {
            	try
            	{
	            	if(tfUsuario.getText().equals(""))
	            	{	
	            		JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
	            	}else
	            	{
	            		Usuario usuarioBaja=usuarioBo.buscarUsuarioPorApodo(tfUsuario.getText());
	            		if(usuarioBaja==null)
						{	
	            			JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO USUARIO");
	            		}
	            		
	            		JOptionPane optionPane= new JOptionPane();
	            		int response=JOptionPane.showConfirmDialog(frame, "ELIMINAR USUARIO "+tfUsuario.getText()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	            		
	            		if(optionPane.YES_OPTION==response)
	            		{	
		            		if(usuarioBo.eliminarUsuario(usuarioBaja))
							{
		            			JOptionPane.showMessageDialog(frame, "USUARIO ELIMINADO CORRECTAMENTE");
							}else
							{															
								JOptionPane.showMessageDialog(frame, "FALLO BAJA USUARIO");
							}
	            		}else if(optionPane.NO_OPTION==response)
	            		{	
	            			//TODO
	            		}else
	            		{
	            			JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO USUARIO");
	            		}
	            	}
            	}
            	catch(MiException e)
            	{
            		throw e;
            	}catch(Exception e)
            	{
            		throw new MiException("[BAJA USUARIO] MI EXCEPTION : "+e);
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
			throw new MiException("\n[BAJA USUARIO] BAJA USUARIO Exception : "+e);
		}
	}
}
