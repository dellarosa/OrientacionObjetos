package entities;

public class Aceite extends Autoparte {

	private int id;
	private int autoparteID;
	private int cantidadlitros;
	private String tipoAceite;
	
	public int getCantidadlitros() {
		return cantidadlitros;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAutoparteID() {
		return autoparteID;
	}
	public void setAutoparteID(int autoparteID) {
		this.autoparteID = autoparteID;
	}
	public void setCantidadlitros(int cantidadlitros) {
		this.cantidadlitros = cantidadlitros;
	}
	public String getTipoAceite() {
		return tipoAceite;
	}
	public void setTipoAceite(String tipoAceite) {
		this.tipoAceite = tipoAceite;
	}
	
	public String toString()
	{
		return super.toString()+"Cant. Litros: "+this.getCantidadlitros()+"\n Aceite: "+this.getTipoAceite();
	}
}
