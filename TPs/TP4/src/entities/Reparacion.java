package entities;

public class Reparacion {
	double costo;
	String fechainicio;
	String fechaentrega;
	Cliente cliente;
	Autoparte autopartes[];
	Usuario	usuario;
	public Reparacion()
	{
		
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
	public Autoparte[] getAutopartes() {
		return autopartes;
	}
	public void setAutopartes(Autoparte[] autopartes) {
		this.autopartes = autopartes;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
