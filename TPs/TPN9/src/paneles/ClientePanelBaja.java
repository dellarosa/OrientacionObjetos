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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bo.ClienteBO;
import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import entities.Cliente;


public class ClientePanelBaja extends JPanel{

	private Handler handler;
	private JTextField tfIngreso;
	private static final long serialVersionUID = 1L;
	public ClientePanelBaja(){}
	public ClientePanelBaja(final Handler handler)
	{			
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor= new PanelGestor();					
		
		tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.gray,new Dimension(400,400),null);
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		
		try
		{
			panelResto.add(panelGestor.cargarClientesEnTabla(handler.cargaClientes()));
			panelResto.add(panelGestor.crearPanelOpcion("Ingrese Cliente a eliminar", tfIngreso));
		}catch (MiException e1) {
			JOptionPane.showMessageDialog(null, "Error al crear Panel", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
			
		JButton btSubmit=new JButton();
		btSubmit.setText("SUBMIT");								
		btSubmit.addActionListener(new ActionListener() {												
        public void actionPerformed(ActionEvent evt) {
        	if(validarCampos())
        	{
				JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
        	}else
        	{
        		clienteBaja();
        	}
        }
        });
		
		this.add(BorderLayout.NORTH,panelTitulo);
		this.add(BorderLayout.CENTER,panelResto);
		this.add(BorderLayout.SOUTH,btSubmit);
	}
	private void clienteBaja() {
		try
         {
    		final Cliente clienteEncontrado=handler.buscarClientePorApodo(tfIngreso.getText());
			if(clienteEncontrado==null)
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO CLIENTE, INGRESE NUEVAMENTE");
    		}else
    		{
    		
        		JOptionPane optionPane= new JOptionPane();
        		int response=JOptionPane.showConfirmDialog(handler.getFrame(), "ELIMINAR CLIENTE "+clienteEncontrado.getNombre()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if(optionPane.YES_OPTION==response)
        		{	
        			if(handler.eliminarCliente(clienteEncontrado))
					{
        				JOptionPane.showMessageDialog(handler.getFrame(), "CLIENTE ELIMINADO CORRECTAMENTE");
					}else
					{
						JOptionPane.showMessageDialog(handler.getFrame(), "FALLO BAJA CLIENTE");
					}        			
        		}else if(optionPane.NO_OPTION==response)
        		{	
        			//TODO
        		}else
        		{	
        			JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO USUARIO");
        		}
        		handler.backToPrincipal();
    		}
        }catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al eliminar cliente", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}
	private boolean validarCampos() {
		return (tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null);
	}
}
