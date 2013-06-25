package paneles;

import java.awt.BorderLayout;

import handler.Handler;

import javax.swing.JPanel;

public class AceitePanelModificacion extends JPanel{
	private Handler handler;
	public AceitePanelModificacion()
	{}
	public AceitePanelModificacion(final Handler handler)
	{
		this.setLayout(new BorderLayout());
		this.handler=handler;
	}
}
