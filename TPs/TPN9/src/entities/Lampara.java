package entities;

public class Lampara extends Autoparte{

	private String tamanio;
	private String color;
	private int lampara_ID;
	private int autoparteID;
	//float voltaje;
	
	public int getLampara_ID() {
		return lampara_ID;
	}
	public void setLampara_ID(int lampara_ID) {
		this.lampara_ID = lampara_ID;
	}
	
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
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
		return super.toString()+" - LAMPARA_ID: "+this.getLampara_ID()+" - TAMANIO: "+
				this.getTamanio()+" - Color: "+this.getColor();
	}
}
