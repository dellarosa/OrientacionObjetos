package auto;
import auto.*;
public class Auto {

	public Motor motor;
	public CajaVelocidades cajavelocidad;
	public Rueda[] ruedas;
	private int cantidadpuertas; 
	private boolean flagaireacond;
	
	public Auto()
	{
		
	}
	public Auto(Motor motor,CajaVelocidades cajadevelocidad,Rueda[] ruedas,int cantpuertas,boolean flagaire)
	{
		this.setmotor(motor);
		this.setcajavelocidaddes(cajadevelocidad);
		this.setruedas(ruedas);
		this.setcantidadpuertas(cantpuertas);
		this.setflagaireacondicionado(flagaire);
		
	}
	public void setmotor(Motor motor)
	{
		this.motor=motor;
	}
	public Motor getmotor()
	{
		return this.motor;
	}
	
	public void setcajavelocidaddes(CajaVelocidades cajavelocidad)
	{
		this.cajavelocidad=cajavelocidad;
	}
	public CajaVelocidades getcajadevelocidades()
	{
		return this.cajavelocidad;
	}
	
	public void setruedas(Rueda[] ruedas)
	{
		this.ruedas=ruedas;
	}
	public Rueda[] getruedas()
	{
		return this.ruedas;
	}

	public int getcantidadpuertas()
	{
		return this.cantidadpuertas;
	}
	public void setcantidadpuertas(int cantpuertas)
	{
		this.cantidadpuertas=cantpuertas;		
	}
	public boolean getflagaireacondicionado()
	{
		return this.flagaireacond;
	}
	public void setflagaireacondicionado(boolean flagaireac)
	{
		this.flagaireacond=flagaireac;
	}
}

