package entities;

public class Filtro extends Autoparte{

	private String tama�o;
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
	public String getTama�o() {
		return tama�o;
	}
	public void setTama�o(String tama�o) {
		this.tama�o = tama�o;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String toString()
	{
		return super.toString()+" - ID_FILTRO: "+this.getFiltro_ID()+" - TAMA�O: "+this.getTama�o()+" - MATERIAL: "+
	    this.getMaterial();
	}
}
