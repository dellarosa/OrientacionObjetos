package paneles;

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
import entities.Cliente;


public class ClientePanelBaja extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClientePanelBaja()
	{
		
	}
	public ClientePanelBaja(final JFrame frame)
	{
		try{
			this.setLayout(new BorderLayout());
			
			final ClienteBO clienteBo=new ClienteBO();			
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			
			PanelGestor panelGestor= new PanelGestor();
						

			JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(3,1),null,Color.gray,new Dimension(400,400),null);
			JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"BAJA USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
			
			panelResto.add(panelGestor.cargarClientesEnTabla(clienteBo.cargaClientes()));
			final JTextField tfIngreso=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
			panelResto.add(panelGestor.crearPanelOpcion("Ingrese Cliente a eliminar", tfIngreso));
			
			JButton btSubmit=new JButton();
			btSubmit.setText("SUBMIT");								
			btSubmit.addActionListener(new ActionListener() {												
            @Override
            public void actionPerformed(ActionEvent evt) {
            	
		            
					if((tfIngreso.getText()==null)||(tfIngreso.getText().equals("")||tfIngreso==null))
		        	{
						JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
		        	}else
		        	{
		        		final Cliente clienteEncontrado=clienteBo.buscarClientePorApodo(tfIngreso.getText());
						if(clienteEncontrado==null)
						{
							JOptionPane.showMessageDialog(frame, "DATOS VACIOS");
		        		}
		        		
		        		JOptionPane optionPane= new JOptionPane();
		        		int response=JOptionPane.showConfirmDialog(frame, "ELIMINAR CLIENTE "+clienteEncontrado.getNombre()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		        		
		        		if(optionPane.YES_OPTION==response)
		        		{	
		        			if(clienteBo.eliminarCliente(clienteEncontrado))
							{
		        				JOptionPane.showMessageDialog(frame, "CLIENTE ELIMINADO CORRECTAMENTE");
							}else
							{
								JOptionPane.showMessageDialog(frame, "FALLO BAJA CLIENTE");
							}        			
		        		}else if(optionPane.NO_OPTION==response)
		        		{	
		        			//TODO
		        		}else
		        		{	
		        			JOptionPane.showMessageDialog(frame, "NO SE ENCONTRO USUARIO");
		        		}
					}	
            }});
			
			this.add(BorderLayout.NORTH,panelTitulo);
			this.add(BorderLayout.CENTER,panelResto);
			this.add(BorderLayout.SOUTH,btSubmit);
						
			frame.setContentPane(this);
			frame.setVisible(true);
			
		} catch (MiException e) {
			throw e;					
        } catch (Exception e) {
			throw new MiException("[BAJA CLIENTE]EXCEPTION : "+e);												
		}
	}
}
