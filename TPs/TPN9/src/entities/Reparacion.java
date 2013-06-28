package entities;

import java.util.ArrayList;
import java.util.List;

public class Reparacion {
	private double costo;	
	private String fechainicio;
	private String fechaentrega;
	private Cliente cliente;
	
	private List<Autoparte> autopartes;
	//private Usuario	usuario;		//POR AHORA NO
	private int id;
	private int entregado;
	
	
	public Reparacion()
	{
		this.setCosto(0);
		this.setAutopartes(new ArrayList<Autoparte>());
	}
	
	
	public int getEntregado() {
		return entregado;
	}
	public void setEntregado(int entregado) {
		this.entregado = entregado;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public String getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}
	public String getFechaentrega() {
		return fechaentrega;
	}
	public void setFechaentrega(String fechaentrega) {
		this.fechaentrega = fechaentrega;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Autoparte> getAutopartes() {
		return autopartes;
	}
	public void setAutopartes(List<Autoparte> autopartes) {
		this.autopartes = autopartes;
	}
	/*public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/

	public String toString()
	{
		return "-ID: "+this.getId()+" -Fecha I: "+this.getFechainicio()+" - Costo: "+this.getCosto()+" -Entregado: "+this.getEntregado()+" - Fecha E: "+this.getFechaentrega()+
		"\n - Cliente: "+this.getCliente().toString();
				
	}
	public String toStringWithAutopartes()
	{
		String string="*ID: "+this.getId()+" -FECHA I: "+this.getFechainicio()+" - COSTO: "+this.getCosto()+" - ENTREGADO: "+this.getEntregado()+" - FECHA ENTREGA: "+this.getFechaentrega()+
				"\n - CLIENTE: "+this.getCliente().toString();
		
		int x=1;
		for(Autoparte au:this.getAutopartes())
		{
			string=string+"\n -AR "+x+" :"+au.toString();
			x++;
		}		
		return string;
	}
	public String toStringAutopartes()
	{
		String string=new String();
		int x=1;
		for(Autoparte au:this.getAutopartes())
		{
			string=string+"\n -AR "+x+" :"+au.toStringSemi();
			x++;
		}		
		return string;
	}
}
