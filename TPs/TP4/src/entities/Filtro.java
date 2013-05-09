package entities;

public class Filtro extends Autoparte{

	private String tamaño;
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
	public String getTamaño() {
		return tamaño;
	}
	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
}
