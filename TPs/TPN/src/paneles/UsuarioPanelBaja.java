package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;
import entities.Usuario;

public class UsuarioPanelBaja extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public UsuarioPanelBaja(final Handler handler)
	{			
			PanelGestor panelGestor= new PanelGestor();

			JPanel panelResto = null;
			JPanel panelTitulo = null;
			JPanel panelCenter = null;
			try {
				panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,50),null);			
				panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,400),"BAJA USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
				panelCenter=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));
			} catch(MiException e) {
				JOptionPane.showMessageDialog(null, "Error al mostrar Baja de Usuarios", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			
			final JTextField tfUsuario=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			
			List<Usuario> cargarUsuarios = null;
			try {
				cargarUsuarios = handler.cargarUsuarios();
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al cargar el listado de Usuarios", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			
			panelCenter.add(BorderLayout.NORTH,panelTitulo);
			panelCenter.add(BorderLayout.CENTER,panelGestor.cargarUsuariosEnTabla(cargarUsuarios));
			try{
				panelCenter.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese UserName de usuario a eliminar", tfUsuario));
			} catch (MiException e1) {
				JOptionPane.showMessageDialog(null, "Error al crear Textfield", "Error", JOptionPane.ERROR_MESSAGE);
				handler.backToPrincipal();
			}
			
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addMouseListener(new MouseAdapter() {												
            @Override
            public void mouseReleased(MouseEvent evt) {
	            	if(tfUsuario.getText().equals(""))
	            	{	
	            		JOptionPane.showMessageDialog(null, "DATOS VACIOS");
	            	} else
						try {
							{
								Usuario usuarioBaja=handler.buscarUsuarioPorApodo(tfUsuario.getText());
								
								if(usuarioBaja == null)
								{	
									JOptionPane.showMessageDialog(null, "NO SE ENCONTRO USUARIO");
								}else
								{
								
									JOptionPane optionPane= new JOptionPane();
									int response=JOptionPane.showConfirmDialog(null, "ELIMINAR USUARIO "+tfUsuario.getText()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
									
									if(optionPane.YES_OPTION==response)
									{	
										try {
											handler.eliminarUsuario(usuarioBaja);
											//handler.backToPrincipal();
										} catch (MiException e) {
											JOptionPane.showMessageDialog(null, e.getMessage(), "FALLO BAJA USUARIO", JOptionPane.ERROR_MESSAGE);
										}
										
									}else if(optionPane.NO_OPTION==response)
									{	
										//handler.backToPrincipal();
									}else
									{
										JOptionPane.showMessageDialog(null, "NO SE ENCONTRO USUARIO");
									}
									handler.backToPrincipal();
								}
							}
						} catch (MiException e) {
							JOptionPane.showMessageDialog(null, "Error al buscar Usuario por apodo", "Error", JOptionPane.ERROR_MESSAGE);
							handler.backToPrincipal();
						}
            	
            }
			});
			panelResto.add(btSubmit);
			//this.add(BorderLayout.NORTH,panelResto);
			this.add(BorderLayout.CENTER,panelCenter);
			this.add(BorderLayout.SOUTH,panelResto);
			
		}
}
