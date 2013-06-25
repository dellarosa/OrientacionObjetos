package entities;

public class Cliente {
	private String nombre;
	private String auto;
	private String mail;
	private String patente;
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPatente() {
		return this.patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String toString()
	{
		return "CLIENTE_ID: "+this.getId()+" - NOMBRE: "+this.getNombre()+" - AUTO: "+this.getAuto()+" - MAIL: "+this.getMail()+" - PATENTE: "+this.getPatente();
	}
	
}
