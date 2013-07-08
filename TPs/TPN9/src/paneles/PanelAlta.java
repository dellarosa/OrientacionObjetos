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
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.PanelGestor;


public abstract class PanelAlta extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public PanelAlta() {}
	
	public PanelAlta(final Handler handler,String strTitulo)
	{			
		this.setLayout(new BorderLayout());
		PanelGestor panelGestor = new PanelGestor();
		
		JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),strTitulo,JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
		JPanel panelGrid = crearFormulario(panelGestor);
		JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,400),null);
		
		panelResto.add(panelGrid);
		
		JButton btSubmit = new JButton("SUBMIT");								
		btSubmit.addActionListener(new ActionListener(){												
	        public void actionPerformed(ActionEvent evt) {
	        		alta(handler);
		    }
		});
		
		this.add(BorderLayout.NORTH, panelTitulo);			
		this.add(BorderLayout.CENTER, panelResto);
		this.add(BorderLayout.SOUTH, btSubmit);
	}

	abstract public JPanel crearFormulario(PanelGestor panelGestor);

	abstract public void alta(Handler handler);	

	abstract public boolean validarCamposVacios();
}
