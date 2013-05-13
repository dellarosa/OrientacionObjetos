package entities;

public class Aceite extends Autoparte {

	private int aceite_ID;
	private int autoparteID;
	private int cantidadlitros;
	private String tipoAceite;
	
	public int getCantidadlitros() {
		return cantidadlitros;
	}

	public int getAceite_ID() {
		return aceite_ID;
	}

	public void setAceite_ID(int aceite_ID) {
		this.aceite_ID = aceite_ID;
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
