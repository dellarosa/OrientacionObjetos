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

import utils.Definiciones;
import utils.MiException;
import utils.PanelGestor;
import entities.Cliente;

public class ClientePanelModificacion extends JPanel {

	private JTextField areaName;				
	private JTextField areaEmail;				
	private JTextField areaAuto;
	private JTextField areaPatente;
	private Handler handler;
	
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	
	public Cliente getCliente() {return cliente;}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ClientePanelModificacion(){}
	public ClientePanelModificacion(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor= new PanelGestor();
		String seleccion = JOptionPane.showInputDialog(handler.getFrame(),"","Ingrese Cliente a modificar",JOptionPane.QUESTION_MESSAGE);  
		
		if((seleccion==null)||(seleccion.equals("")))
    	{
			JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS - CANCELADO ");
			//handler.backToPrincipal();
			this.add(panelGestor.armarPanelConImagen());
    	}else
    	{
    		try
    		{
    			setCliente(handler.buscarClientePorApodo(seleccion));	
        	}catch (MiException e1) {
    			JOptionPane.showMessageDialog(null, "Error al crear buscar cliente", "Error", JOptionPane.ERROR_MESSAGE);
    			handler.backToPrincipal();
    		}
			if(getCliente()==null)
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "NO SE ENCONTRO CLIENTE");
				this.add(panelGestor.armarPanelConImagen());
    		}
			else
			{	
				JPanel panelGrid = crearFormularioModificacion(panelGestor);					
				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);					
				JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(600,400),null);
				panelResto.add(panelGrid);
				//System.out.print("\n[ClientePanelModificacion] paneles LISTOS\n");			//DEBUG					
				final JButton btSubmit=new JButton("SUBMIT");
				btSubmit.addActionListener(new ActionListener() {												
					public void actionPerformed(ActionEvent arg0) {
						if(validarCampos())
			    		{	
							JOptionPane.showMessageDialog(handler.getFrame(), "DATOS VACIOS");
			    		}else
			    		{
			    			clienteModificacion();
			    		}
	            	}
				});
				
				this.add(BorderLayout.NORTH,panelTitulo);
				this.add(BorderLayout.CENTER,panelResto);
				this.add(BorderLayout.SOUTH,btSubmit);
			}			
    	}	
		
	}
	private JPanel crearFormularioModificacion(PanelGestor panelGestor) {
		areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
		areaAuto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		areaPatente=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
		
		JLabel labelName=panelGestor.crearLabel("Nombre ("+getCliente().getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelEmail=panelGestor.crearLabel("Email("+getCliente().getMail()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelAuto=panelGestor.crearLabel("Auto("+getCliente().getAuto()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		JLabel labelPatente=panelGestor.crearLabel("Patente("+getCliente().getPatente()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
		
		JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaAuto,areaPatente};
		JLabel[] labels=new JLabel[]{labelName,labelEmail,labelAuto,labelPatente};
		JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
		return panelGrid;
	}
	
	private void clienteModificacion(){
		try
		{
			getCliente().setNombre(!areaName.getText().equals("") ? areaName.getText() : getCliente().getNombre());							            	
			getCliente().setMail(!areaEmail.getText().equals("") ? areaEmail.getText(): getCliente().getMail());							
			getCliente().setAuto(!areaAuto.getText().equals("")? areaAuto.getText():getCliente().getAuto());
		    getCliente().setPatente(!areaPatente.getText().equals("") ? areaPatente.getText():getCliente().getPatente());
		    getCliente().setId(getCliente().getId());
		    
			if(handler.updateCliente(getCliente()))
			{	
				JOptionPane.showMessageDialog(handler.getFrame(), "CLIENTE MODIFICADO CORRECTAMENTE");
			}else
			{
				JOptionPane.showMessageDialog(handler.getFrame(), "FALLO MODIFICACION CLIENTE NO ENCONTRADO EN DB");
			}				
			handler.backToPrincipal();
		}catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al modificar cliente", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
	}
	private boolean validarCampos() {
		return areaName.getText().equals("")&&areaEmail.getText().equals("")&&areaAuto.getText().equals("");
	}
}
