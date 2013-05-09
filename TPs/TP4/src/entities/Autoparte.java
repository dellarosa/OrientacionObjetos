package entities;

public class Autoparte {
	private String marca;
	private String modelo;
	private int id;
	private double costo;
	private int cantDisponible;
	private String tipoAutoparte; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public int getCantDisponible() {
		return cantDisponible;
	}
	public void setCantDisponible(int cantDisponible) {
		this.cantDisponible = cantDisponible;
	}
	public String getTipoAutoparte() {
		return tipoAutoparte;
	}
	public void setTipoAutoparte(String tipoAutoparte) {
		this.tipoAutoparte = tipoAutoparte;
	}
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
