package paneles;
import java.util.List;
import javax.swing.JScrollPane;
import utils.FuncionesAutopartes;
import utils.PanelGestor;
import entities.Lampara;
import handler.Handler;

public class LamparaPanelMostrar extends PanelMostrar{
	
	private static final long serialVersionUID = 1L;
	private FuncionesAutopartes funcionesAutopartes=null;
	
	public LamparaPanelMostrar(final Handler handler)
	{
		super(handler,"MOSTRAR LAMPARAS");
	}

	public JScrollPane llenarPanelAMostrar(final Handler handler,PanelGestor panelGestor) {
		funcionesAutopartes=new FuncionesAutopartes();
		List<Lampara> lstLamparas = funcionesAutopartes.listarLamparas(handler);		
    	
		JScrollPane pane = new JScrollPane(panelGestor.cargarLamparaEnTabla(lstLamparas));
		return pane;
	}

	
}
