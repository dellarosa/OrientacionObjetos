package entities;

public class Filtro extends Autoparte{

	private String tamanio;
	private String material;
	private int filtro_ID;
	private int autoparteID;
	
	
	public int getFiltro_ID() {
		return filtro_ID;
	}
	public void setFiltro_ID(int filtro_ID) { 
		this.filtro_ID = filtro_ID;
	}
	public int getAutoparteID() {
		return autoparteID;
	}
	public void setAutoparteID(int autoparteID) {
		this.autoparteID = autoparteID;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String toString()
	{
		return super.toString()+" - ID_FILTRO: "+this.getFiltro_ID()+" - TAMA�O: "+this.getTamanio()+" - MATERIAL: "+
	    this.getMaterial();
	}
}
