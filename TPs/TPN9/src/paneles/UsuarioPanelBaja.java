package paneles;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import bo.UsuarioBO;
import entities.Usuario;

public class UsuarioPanelBaja extends JPanel{

	private Handler handler;
	private JTextField tfUsuario;
	private static final long serialVersionUID = 1L;
	
	public UsuarioPanelBaja(final Handler handler)
	{			
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor= new PanelGestor();

		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,50),null);
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,400),"BAJA USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelCenter=panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.gray,new Dimension(400,400));

		tfUsuario=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		panelCenter.add(BorderLayout.NORTH,panelTitulo);
		
		List<Usuario> cargaUsuarios = listarUsuarios();
		
		JScrollPane pane=new JScrollPane(panelGestor.cargarUsuariosEnTabla(cargaUsuarios));
		panelCenter.add(BorderLayout.CENTER,pane);
		panelCenter.add(BorderLayout.SOUTH,panelGestor.crearPanelOpcion("Ingrese UserName de usuario a eliminar", tfUsuario));
		
		JButton btSubmit=new JButton("SUBMIT");								
		btSubmit.addActionListener(new ActionListener() {												
        
		@Override
		public void actionPerformed(ActionEvent e) {
			if(tfUsuario.getText().equals(""))
    		{	
    			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
    		} else
    		{
    			usuarioBaja();
    		}			
		}});
		
		panelResto.add(btSubmit);
		this.add(BorderLayout.CENTER,panelCenter);
		this.add(BorderLayout.SOUTH,panelResto);		
	}

	private List<Usuario> listarUsuarios() {
		List<Usuario> cargarUsuarios = null;
		try {
			cargarUsuarios = handler.cargarUsuarios();
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Usuarios", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		return cargarUsuarios;
	}
	
	private void usuarioBaja() {
		Usuario usuarioBaja=null;
		try{			
			usuarioBaja=handler.buscarUsuarioPorApodo(tfUsuario.getText());
		} catch (MiException e) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al buscar Usuario por apodo", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		
		if(usuarioBaja == null)
		{	
			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO USUARIO");
		}else
		{
			JOptionPane optionPane= new JOptionPane();
			int response=JOptionPane.showConfirmDialog(handler.getFrame(), "ELIMINAR USUARIO "+tfUsuario.getText()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(optionPane.YES_OPTION==response)
			{	
				try {
					handler.eliminarUsuario(usuarioBaja);
					//handler.backToPrincipal();
					JOptionPane.showMessageDialog(handler.getFrame(), "USUARIO ELIMINADO");
				} catch (MiException e) {
					JOptionPane.showMessageDialog(handler.getFrame(), e.getMessage(), "FALLO BAJA USUARIO", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(optionPane.NO_OPTION==response)
			{JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO USUARIO");
			}else
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO USUARIO");
			}
			handler.backToPrincipal();
		}
	}
}
