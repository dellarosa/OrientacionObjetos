package entities;

import utils.Dentre;

import utils.MetodosGenerales;

public class Ejecutivo extends Empleado 		
{

	public Ejecutivo()
	{
		setCantHorasTrabajoDiarias(8);
	}
	
	public Ejecutivo(double sueldoejecutiv)
	{
		setSueldo(sueldoejecutiv);
		setCantHorasTrabajoDiarias(8);
		if(this.getLegajo()==null)
		{
			this.setLegajo("1");
		}else
		{		
			this.setLegajo(String.valueOf(Integer.valueOf(this.getLegajo())+1));
		}
	}

	//un día y medio de vacaciones por cada 6

	
	public int getCantidadDiasVacaciones()
	{	
		return this.cantidaddiastrabajados/4;		
	}
	
	


	public void bajarCantHorasTrabajoDiarias(int canthorastrabajodiarias)
	{
		if(this.canthorastrabajodiarias>canthorastrabajodiarias)
		{
			this.canthorastrabajodiarias=canthorastrabajodiarias;
		}else
		{
			System.out.print("\n*LAS HORAS QUE DESEA INGRESAR SON MAYORES A LAS QUE YA TRABAJA*\n");
		}
	}
	public int getCantHorasTrabajoDiarias()
	{
		return this.canthorastrabajodiarias;
	}
	

	
	
	public String toString()
	{
		return super.toString()+"Edad: "+this.getEdad();
		
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

	
	/*public void disminuirHorasTrabajo(int cantHorasMenos)
	{		
		this.setCantHorasTrabajoDiarias(this.canthorastrabajodiarias-cantHorasMenos);
	}*/	
}
