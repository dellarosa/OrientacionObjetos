package entities;

public class Aceite extends Autoparte {

	int cantidadlitros;
	String TipoAceite;
	
	public int getCantidadlitros() {
		return cantidadlitros;
	}
	public void setCantidadlitros(int cantidadlitros) {
		this.cantidadlitros = cantidadlitros;
	}
	public String getTipoAceite() {
		return TipoAceite;
	}
	public void setTipoAceite(String tipoAceite) {
		TipoAceite = tipoAceite;
	}
	
	public String toString()
	{
		return super.toString()+"Cant. Litros: "+this.getCantidadlitros()+"\n Aceite: "+this.getTipoAceite();
	}
}
