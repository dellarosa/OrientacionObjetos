package paneles;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import handler.Handler;

public class LamparaPanelModificacion extends JPanel {
	private Handler handler;
	
	public LamparaPanelModificacion(){}
	public LamparaPanelModificacion(final Handler handler)
	{
		this.handler=handler;
		this.setLayout(new BorderLayout());
	}
}
