package entities;

public class Lampara extends Autoparte{

	private String tamaño;
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
	public String getTamaño() {
		return tamaño;
	}
	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
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
		return Lampara.class.getCanonicalName()+"\n"+super.toString()+"\n Tamaño: "+
				this.getTamaño()+"\n Color: "+this.getColor()+"\n";
	}
}
