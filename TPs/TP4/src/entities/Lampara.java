package entities;

public class Lampara extends Autoparte{

	int tamaño;
	float intensidad;
	float voltaje;
	
	public int getTamaño() {
		return tamaño;
	}
	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
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
		return Lampara.class.getCanonicalName()+"\n"+super.toString()+"\n Tamaño: "+
				this.getTamaño()+"\n Intensidad: "+this.getIntensidad()+"\n Voltaje: "+this.getVoltaje();
	}
}
