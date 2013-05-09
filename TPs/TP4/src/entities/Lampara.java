package entities;

public class Lampara extends Autoparte{

	private String tama�o;
	private String color;
	private int id;
	private int autoparteID;
	//float voltaje;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAutoparteID() {
		return autoparteID;
	}
	public void setAutoparteID(int autoparte_ID) {
		this.autoparteID = autoparte_ID;
	}
	public String getTama�o() {
		return tama�o;
	}
	public void setTama�o(String tama�o) {
		this.tama�o = tama�o;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	/*
	public float getVoltaje() {
		return voltaje;
	}
	public void setVoltaje(float voltaje) {
		this.voltaje = voltaje;
	}
	 */
	public String toString()
	{
		return Lampara.class.getCanonicalName()+"\n"+super.toString()+"\n Tama�o: "+
				this.getTama�o()+"\n Color: "+this.getColor()+"\n";
	}
}
