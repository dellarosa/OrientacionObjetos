package entities;

public class Filtro extends Autoparte{

	private int tama�o;
	private String material;
	private int id;
	private int autoparteID;
	
	public int getAutoparteID() {
		return autoparteID;
	}
	public void setAutoparteID(int autoparteID) {
		this.autoparteID = autoparteID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTama�o() {
		return tama�o;
	}
	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
}
