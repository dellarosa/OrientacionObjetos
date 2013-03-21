package entities;

import utils.Dentre;
import utils.MetodosGenerales;

public class Ejecutivo extends Empleado{

	public Ejecutivo()
	{}
	
	public Ejecutivo(double sueldoejecutiv)
	{
		setSueldo(sueldoejecutiv);
	}

	//un día y medio de vacaciones por cada 6
	public void disminuirHorasTrabajo(int cantHorasMenos)
	{		
		this.setCantHorasTrabajoDiarias(this.canthorastrabajodiarias-cantHorasMenos);
	}	
	
	public void setCantHorasTrabajoDiarias(int canthorastrabajodiarias)
	{	
		this.canthorastrabajodiarias=canthorastrabajodiarias;
	}

	public int getCantidadDiasVacaciones()
	{	
		return this.cantidaddiastrabajados/4;		
	}
	
	public void setCantidadDiasVacaciones()
	{
		this.setCantidadDiasVacaciones(this.cantidaddiastrabajados/4);
	}

	public Ejecutivo altaDeNuevaPersonaEnEmpresa()
	{ 
		try
		{
			MetodosGenerales metodosg=new MetodosGenerales();
			Ejecutivo ejecutivo=new Ejecutivo();
			System.out.print("\n**INGRESE LOS DATOS DE ALTA DEL NUEVO EMPLEADO**\n");
			
			//TODO AGREGAR VALIDACIONES
			ejecutivo.setDni(Dentre.entero("\nINGRESE DNI (sin puntos): "));
			ejecutivo.setNombre(Dentre.texto("\nINGRESE NOMBRE: "));
			ejecutivo.setApellido(Dentre.texto("\nINGRESE APELLIDO: "));
			try
			{
				char sex=metodosg.validarSexo(Dentre.caracter("\nINGRESE SEXO (m-f): "));
				ejecutivo.setSexo(sex);
			}catch(Exception ex)
			{
				
			}		
			ejecutivo.setEdad(Dentre.entero("\nINGRESE EDAD: "));
			
			return ejecutivo;
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
