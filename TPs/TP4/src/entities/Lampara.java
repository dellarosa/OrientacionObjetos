package entities;

public class Lampara extends Autoparte{

	private String tama�o;
	private String color;
	private int lampara_ID;
	private int autoparteID;
	//float voltaje;
	

	public int getAutoparteID() {
		return autoparteID;
	}
	public int getLampara_ID() {
		return lampara_ID;
	}
	public void setLampara_ID(int lampara_ID) {
		this.lampara_ID = lampara_ID;
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
		return super.toString()+" - LAMPARA_ID: "+this.getLampara_ID()+" - Tama�o: "+
				this.getTama�o()+" - Color: "+this.getColor();
	}
}
