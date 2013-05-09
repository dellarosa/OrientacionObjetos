package entities;

public class Reparacion {
	private double costo;	
	private String fechainicio;
	private String fechaentrega;
	private Cliente cliente;
	private Autoparte autopartes[][];
	//private Usuario	usuario;		//POR AHORA NO
	private int id;
	private int entregado;
	
	public int getEntregado() {
		return entregado;
	}
	public void setEntregado(int entregado) {
		this.entregado = entregado;
	}
	public Reparacion()
	{
		
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
	public Autoparte[][] getAutopartes() {
		return autopartes;
	}
	public void setAutopartes(Autoparte[][] autopartes) {
		this.autopartes = autopartes;
	}
	/*public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/
	
}
