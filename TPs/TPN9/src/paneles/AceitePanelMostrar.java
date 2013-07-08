package paneles;

import handler.Handler;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Aceite;
import utils.FuncionesAutopartes;
import utils.PanelGestor;


public class AceitePanelMostrar extends PanelMostrar{
	
	private static final long serialVersionUID = 1L;
	private FuncionesAutopartes funcionesAutopartes=null;
	
	public AceitePanelMostrar(final Handler handler)
	{
		super(handler,"MOSTRAR ACEITES");
	}
	
	public JScrollPane llenarPanelAMostrar(Handler handler,PanelGestor panelGestor) {
		funcionesAutopartes=new FuncionesAutopartes();
		List<Aceite> lstAceite = funcionesAutopartes.listarAceites(handler);
		
    	JTable table=panelGestor.cargarAceiteEnTabla(lstAceite);										
    	JScrollPane pane = new JScrollPane(table);
    	
		return pane;
	}
	
}
