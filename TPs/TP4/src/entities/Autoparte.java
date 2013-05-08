package entities;

public class Autoparte {
	String marca;
	String modelo;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String toString()
	{
		return "Marca: "+this.getMarca()+"\n Modelo: "+this.getModelo();
	}
}
