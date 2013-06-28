package utils;

import handler.Handler;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entities.Aceite;
import entities.Autoparte;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;

public class FuncionesAutopartes {

	private PanelGestor panelGestor;
	public List<Filtro> listarFiltros(Handler handler) {
		List<Autoparte> lstAutopartes=new ArrayList<Autoparte>();
		
		try
		{
			lstAutopartes= handler.cargaAutopartes();
			
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		List<Filtro> lstFiltro=new ArrayList<Filtro>();
		for(Autoparte autop:lstAutopartes)
		{
			if(autop.getCantDisponible()>0)
			{
				if(autop instanceof Filtro)			
				{
					lstFiltro.add((Filtro)autop);
				}
			}
		}
		return lstFiltro;
	}	
	
	public List<Aceite> listarAceites(Handler handler) {
		List<Autoparte> lstAutopartes=null;
		List<Aceite> lstAceite;
		try
		{
			lstAutopartes= handler.cargaAutopartes();
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		lstAceite=new ArrayList<Aceite>();
		
		for(Autoparte autop:lstAutopartes)
		{
			if(autop.getCantDisponible()>0)
			{
				if(autop instanceof Aceite)
				{
					
					lstAceite.add((Aceite)autop);
				}
			}
		}
		return lstAceite;
	}
	
	public List<Lampara> listarLamparas(Handler handler) {
		List<Autoparte> lstAutopartes=null;
		
		try
		{
			lstAutopartes= handler.cargaAutopartes();
		} catch (MiException e1) {
			JOptionPane.showMessageDialog(handler.getFrame(), "Error al cargar el listado de Autopartes", "Error", JOptionPane.ERROR_MESSAGE);
			handler.backToPrincipal();
		}
		List<Lampara> lstLamparas=new ArrayList<Lampara>();
		for(Autoparte autop:lstAutopartes)
		{
			if(autop.getCantDisponible()>0)
			{
				if(autop instanceof Lampara)
				{
					
					lstLamparas.add((Lampara)autop);
				}
			}
		}
		return lstLamparas;
	}

	public Filtro transmutarFiltroAutoparte(Filtro filtro, Autoparte autoparte) {

		filtro.setCantDisponible(autoparte.getCantDisponible());
		filtro.setCosto(autoparte.getCosto());
		filtro.setMarca(autoparte.getMarca());
		filtro.setModelo(autoparte.getModelo());
		filtro.setTipoAutoparte("filtro");
		return filtro;
	}

	public Aceite transmutarAceiteAutoparte(Aceite aceite, Autoparte autoparte) {
		aceite.setCantDisponible(autoparte.getCantDisponible());
		aceite.setCosto(autoparte.getCosto());
		aceite.setMarca(autoparte.getMarca());
		aceite.setModelo(autoparte.getModelo());
		aceite.setTipoAutoparte("aceite");
		return aceite;
	}

	public Lampara transmutarLamparaAutoparte(Lampara lampara,Autoparte autoparte) {
		lampara.setCantDisponible(autoparte.getCantDisponible());
		lampara.setCosto(autoparte.getCosto());
		lampara.setMarca(autoparte.getMarca());
		lampara.setModelo(autoparte.getModelo());
		lampara.setTipoAutoparte("lampara");
		return lampara;
	}

	public JPanel obtenerPanelAutopartes(Handler handler) {
		
		List<Filtro> lstFiltros=listarFiltros(handler);				
		List<Aceite> lstAceites=listarAceites(handler);				
		List<Lampara> lstLamparas=listarLamparas(handler);
		
		panelGestor=new PanelGestor();
		JPanel panelAutopartes=panelGestor.cargarAutopartesEnPanel(lstFiltros,lstAceites,lstLamparas);
		
		return panelAutopartes;
	}
}
