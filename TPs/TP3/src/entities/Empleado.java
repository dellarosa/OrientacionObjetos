package entities;

import auto.Auto;
import utils.Dentre;
import utils.MetodosGenerales;

public class Empleado extends Persona  
{
	//DECLARACION DE VARIABLES/////////////////////////////////////////////////////////////////////////////////////////////////
	private String legajo;
	protected double sueldo;
	protected int canthorastrabajodiarias;
	protected int cantidaddiasvacaciones;
	protected int cantidaddiastrabajados;			
	private Auto auto;
	//INICIO DEL CUERPO DEL PROGRAMA//////////////////////////////////////////////////////////////////////////////////////////
	public Empleado()
	{
		setCantHorasTrabajoDiarias(8);
	}
	public Empleado(double sueldo)
	{
		setCantHorasTrabajoDiarias(8);
		if(sueldo!=0)
		{
			this.setSueldo(sueldo);
		}else
		{
			this.setSueldo(0);
		}
		if(this.getLegajo()==null)
		{
			this.setLegajo("1");
		}else
		{		
			this.setLegajo(String.valueOf(Integer.valueOf(this.getLegajo())+1));
		}
	}
	public Empleado(String legajo)
	{
		
	}
	public void setAuto(Auto auto)
	{
		this.auto=auto;
	}
	public Auto getAuto()
	{
		return this.auto;
	}
	public String getLegajo()
	{
		return legajo;
	}
	public void setLegajo(String legajo)
	{
		this.legajo=legajo;
	}
	public double getSueldo()
	{
		return this.sueldo;
	}
	public void setSueldo(double sueldo)
	{
		this.sueldo=sueldo;
	}
	
	/////////////////////////////////////////////////////////
	public boolean setDisminuirHoras(int canthorasadisminuir)
	{
		if(this.canthorastrabajodiarias>canthorasadisminuir)			//AGREGAR MEJOR VALIDACION
		{
			this.canthorastrabajodiarias=this.canthorastrabajodiarias-canthorasadisminuir;
			return true;
		}else
		{
			return false;
		}
	}
	public void setCantHorasTrabajoDiarias(int canthorastrabajodiarias)
	{
		this.canthorastrabajodiarias=canthorastrabajodiarias;		
	}
	public int getCantHorasTrabajoDiarias()
	{
		return this.canthorastrabajodiarias;
	}
	//////////////////////////////////////////////////////////
	public int getCantidadDiasVacaciones()
	{
		return this.cantidaddiastrabajados/6;		
	}
	public void setCantidadDiasVacaciones()								//NO DEBERIA USARSE
	{
		this.cantidaddiasvacaciones=this.cantidaddiastrabajados/6;;
	}
	public int getCantidadDiasTrabajados()
	{
		return this.cantidaddiastrabajados;
	}
	public void setCantidadDiasTrabajados(int cantdiastrabajados)
	{
		this.cantidaddiastrabajados=cantdiastrabajados;		
	}
	///////////////////////////////////////////////////////////
	
	
	
	public String toString()
	{
		return super.toString()+"\nLegajo: "+this.getLegajo()+"\nSueldo: "+this.getSueldo()+				
		
		"\nDias Trabajados: "+this.getCantidadDiasTrabajados()+"\n"
		;
	}

		////////////////////////////////////////////////////////METODOS SIMULACION ////////////////////
		public boolean simSubirAlAuto() {
		// TODO Auto-generated method stub
		return true;
		}
		
		public boolean simManejarAuto() {
		// TODO Auto-generated method stub
		return true;
		}
		
		public boolean simBajarAuto() {
		// TODO Auto-generated method stub
		return true;
		}
		
		public boolean simIrACasaYDescansar() {
		// TODO Auto-generated method stub
		return true;
		}
		
		public boolean simSalirOficinaHaciaAuto() {
		// TODO Auto-generated method stub
		return true;
		}

	
}
