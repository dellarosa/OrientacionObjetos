package entities;

public class Lampara extends Autoparte{

	int tama�o;
	float intensidad;
	float voltaje;
	
	public int getTama�o() {
		return tama�o;
	}
	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}
	public float getIntensidad() {
		return intensidad;
	}
	public void setIntensidad(float intensidad) {
		this.intensidad = intensidad;
	}
	public float getVoltaje() {
		return voltaje;
	}
	public void setVoltaje(float voltaje) {
		this.voltaje = voltaje;
	}

	public String toString()
	{
		return Lampara.class.getCanonicalName()+"\n"+super.toString()+"\n Tama�o: "+
				this.getTama�o()+"\n Intensidad: "+this.getIntensidad()+"\n Voltaje: "+this.getVoltaje();
	}
}
