package entities;

import utils.Dentre;
import utils.MetodosGenerales;

public class Empleado extends Persona 
{
	//DECLARACION DE VARIABLES/////////////////////////////////////////////////////////////////////////////////////////////////
	private String legajo;
	protected double sueldo;
	protected int canthorastrabajodiarias;
	private int cantidaddiasvacaciones;
	protected int cantidaddiastrabajados;
	
	//INICIO DEL CUERPO DEL PROGRAMA//////////////////////////////////////////////////////////////////////////////////////////
	public Empleado()
	{
		
	}
	public Empleado(String legajo)
	{
		
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
	

	public void setCantHorasTrabajoDiarias(int canthorastrabajodiarias)
	{
		this.canthorastrabajodiarias=canthorastrabajodiarias;
	}
	public int getCantHorasTrabajoDiarias()
	{
		return this.canthorastrabajodiarias;
	}
	

	public int getCantidadDiasVacaciones()
	{
		return this.cantidaddiastrabajados/6;		
	}
	public void setCantidadDiasVacaciones(int cantidaddiasvacas)
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
	
	public Empleado altaDeNuevaPersonaEnEmpresa()
	{		
		try
		{			
			MetodosGenerales metodosg=new MetodosGenerales();		
			Empleado empleado=new Empleado();
			//personas[p];
			System.out.print("\n**INGRESE LOS DATOS DE ALTA DEL NUEVO EMPLEADO**\n");
			
			//TODO AGREGAR VALIDACIONES

			empleado.setDni(Dentre.entero("\nINGRESE DNI (sin puntos): "));
			empleado.setNombre(Dentre.texto("\nINGRESE NOMBRE: "));
			empleado.setApellido(Dentre.texto("\nINGRESE APELLIDO: "));
			char sex=metodosg.validarSexo(Dentre.caracter("\nINGRESE SEXO (m-f): "));
			empleado.setSexo(sex);
			empleado.setSueldo(Dentre.doble("\nINGRESE EL SUELDO: "));
			return empleado;
		}catch(Exception ex)
		{	
			try
			{
				System.out.print("\n**Exception en ingreso de datos - VERIFICAR INGRESO - EXCEPTION: **"+ex+"\n");
				Thread.sleep(2000);				
				throw ex;
			}catch(Exception e)
			{
				System.out.print("\n**Exception en Thread sleep **"+e+"\n");
			}
			throw ex;
		}		
		
	}
	
}
