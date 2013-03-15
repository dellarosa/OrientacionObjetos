package auto;

public class Motor {
	
	private String marca;
	private double caballosfuerza;
	private double cilindrada;
	
	
	public Motor()
	{}
	
	public Motor(String marca,double caballosfuerza,double cilindrada)
	{
		this.marca=marca;
		this.caballosfuerza=caballosfuerza;
		this.cilindrada=cilindrada;
		
	}
	
	public String getmarcamotor()
	{
		return this.marca;
	}
	public void setmarcamotor(String marcamotor)
	{
		this.marca=marcamotor;
	}
	
	public double getcaballosfuerza()
	{
		return this.caballosfuerza;
	}
	
	public void setcaballosfuerza(double cantcaballosfuerza)
	{
		this.caballosfuerza=cantcaballosfuerza;
	}
	
	public void setcilindrada(double cilindrosmotor)
	{
		this.cilindrada=cilindrosmotor;
	}
	public double cilindrada()
	{
		return this.cilindrada;
	}
	
}
