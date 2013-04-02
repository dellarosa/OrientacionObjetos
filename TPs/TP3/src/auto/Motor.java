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
	
	public String getMarcaMotor()
	{
		return this.marca;
	}
	public void setMarcaMotor(String marcamotor)
	{
		this.marca=marcamotor;
	}
	
	public double getCaballosFuerza()
	{
		return this.caballosfuerza;
	}
	
	public void setCaballosFuerza(double cantcaballosfuerza)
	{
		this.caballosfuerza=cantcaballosfuerza;
	}
	
	public void setCilindrada(double cilindrosmotor)
	{
		this.cilindrada=cilindrosmotor;
	}
	public double getCilindrada()
	{
		return this.cilindrada;
	}
	
	public String toString()
	{
		return "MOTOR\nMarca: "+this.getMarcaMotor()+"\nCilindrada: "+this.getCilindrada()+"\nCaballos de Fuerza: "+this.getCaballosFuerza();
	}
}
