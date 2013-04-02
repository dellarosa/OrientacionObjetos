package entities;

import utils.Dentre;
import utils.MetodosGenerales;

public class Ejecutivo extends Empleado{

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
	
	

	public Ejecutivo altaDeEmpleado(Ejecutivo ejecutivo)
	{ 
		try
		{
			MetodosGenerales metodosg=new MetodosGenerales();
			System.out.print("\n**INGRESE LOS DATOS DE ALTA DEL NUEVO EMPLEADO**\n");
			
			//TODO AGREGAR VALIDACIONES
			ejecutivo.setDni(Dentre.entero("\nINGRESE DNI (sin puntos): "));		//VALIDAR QUE NO EXISTA UN DNI IGUAL
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
			return null;
		}
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
	

	public Ejecutivo modificarEmpleado(Ejecutivo ejecutivo)
	{
		try
		{		
			boolean sigo = true;
			int opcion = 0;			
			while(sigo)
			{
				System.out.print("1-Modificar Apellido \n");
				System.out.print("2-Modificar Nombre \n");
				System.out.print("3-Modificar DNI \n");
				System.out.print("4-Modificar EDAD \n");
				System.out.print("5-Modificar SEXO \n");
				System.out.print("6-Modificar LEGAJO \n");
				
				System.out.print("99- Salir\n");
				try
				{
					opcion=Dentre.entero("OPCIÓN: ");
				}catch(Exception ex)
				{
					System.out.print("Datos ingresados incorrectamente\n");
				}
				switch(opcion)
				{
					case 1:
						ejecutivo.setApellido(Dentre.texto("\nINGRESE APELLIDO NUEVO: "));														
						break;
					case 2:
						ejecutivo.setNombre(Dentre.texto("\nINGRESE NOMBRE NUEVO: "));
						break;
					case 3:
						ejecutivo.setDni(Dentre.entero("\nINGRESE DNI NUEVO: "));
						break;
					case 4:
						ejecutivo.setDni(Dentre.entero("\nINGRESE EDAD A CAMBIAR: "));
						break;
					case 5:
						ejecutivo.setDni(Dentre.entero("\nINGRESE SEXO A CAMBIAR: "));
						break;
					case 6:
						ejecutivo.setLegajo(Dentre.texto("\nINGRESE LEGAJO NUEVO: "));
						break;
					case 99:
						sigo=false;
						break;
					default:
						break;
				}
				
				try
				{
					if(sigo!=false)
					{
						System.out.print("\nDATOS MODIFICADOS CORRECTAMENTE\n");
						Thread.sleep(2000);
					}
				}catch(Exception ex)
				{
					
				}
			}	
			
			System.out.print("\n[menu] ");
			Thread.sleep(2000);
		}catch(Exception e)
		{
			
		}			
		return ejecutivo;
	}
	
	public String toString()
	{
		return super.toString()+"Edad: "+this.getEdad();
		
	}
	
	/*public void disminuirHorasTrabajo(int cantHorasMenos)
	{		
		this.setCantHorasTrabajoDiarias(this.canthorastrabajodiarias-cantHorasMenos);
	}*/	
}
