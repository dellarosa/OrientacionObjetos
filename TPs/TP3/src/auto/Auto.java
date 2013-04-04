package auto;


public class Auto 
{

	private Motor motor;
	private CajaVelocidades cajavelocidad;
	private Rueda[] ruedas;
	private int cantidadpuertas; 
	private boolean flagaireacond;
	private String patente;
	private boolean disponible;		//Para saber si esta disponible para asignar...
	public Auto()
	{
		this.disponible=true;
	}
	public Auto(Motor motor,CajaVelocidades cajadevelocidad,Rueda[] ruedas,int cantpuertas,boolean flagaire,String patente)
	{
		this.setMotor(motor);
		this.setCajaVelocidades(cajadevelocidad);
		this.setRuedas(ruedas);
		this.setCantidadPuertas(cantpuertas);
		this.setFlagAireAcondicionado(flagaire);
		this.setPatente(patente);
		this.setDisponibilidad(true);
	}
	public void setDisponibilidad(boolean disponibilidad)
	{
		this.disponible=disponibilidad;
		
	}
	public boolean getDisponibilidad()
	{
		return this.disponible;
	}
	public void setPatente(String patente)
	{
		this.patente=patente;
	}
	public String getPatente()
	{
		return this.patente;
	}
	public void setMotor(Motor motor)
	{
		this.motor=motor;
	}
	public Motor getMotor()
	{
		return this.motor;
	}
	
	public void setCajaVelocidades(CajaVelocidades cajavelocidad)
	{
		this.cajavelocidad=cajavelocidad;
	}
	public CajaVelocidades getCajadeVelocidades()
	{
		return this.cajavelocidad;
	}
	
	public void setRuedas(Rueda[] ruedas)
	{
		this.ruedas=ruedas;
	}
	public Rueda[] getRuedas()
	{
		return this.ruedas;
	}

	public int getCantidadPuertas()
	{
		return this.cantidadpuertas;
	}
	public void setCantidadPuertas(int cantpuertas)
	{
		this.cantidadpuertas=cantpuertas;		
	}
	public boolean getFlagAireAcondicionado()
	{
		return this.flagaireacond;
	}
	public void setFlagAireAcondicionado(boolean flagaireac)
	{
		this.flagaireacond=flagaireac;
	}
	
	
	public String toString()
	{	
		return "El auto tiene las siguientes caracteristicas:\n"+		
		this.getCajadeVelocidades().toString()+"\n"+
		this.getMotor().toString()+"\n" +
		"Su patente es: "+this.getPatente()+"\n"+
		"Y tiene "+this.getCantidadPuertas()+" puertas.\n"+
		"Disponibilidad: "+this.getDisponibilidad();
		
	}

	public boolean simEncenderMotor() {
	//System.out.print("\nEl motor ha sido encendido");
	return true; 
	}
	public boolean simAbrirPuerta() {
	//System.out.print("\nLa puerta se ha abierto");
	return true;
	}
	public boolean simCerrarPuerta() {
	//System.out.print("\nLa puerta fue cerrada");
	return true;
	}
	public boolean simApagarMotor() {
	//System.out.print("\nLa puerta fue cerrada");
	return true;
	}
}

