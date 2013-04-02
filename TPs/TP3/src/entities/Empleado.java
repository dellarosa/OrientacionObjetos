package entities;

import auto.Auto;
import utils.Dentre;
import utils.InterfazEmpleado;
import utils.MetodosGenerales;

public class Empleado extends Persona implements InterfazEmpleado
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
	
	public Empleado altaDeEmpleado(Empleado empleado)
	{		
		try
		{			
			MetodosGenerales metodosg=new MetodosGenerales();		
			
			//personas[p];
			System.out.print("\n**INGRESE LOS DATOS DE ALTA DEL NUEVO EMPLEADO**\n");
			
			//TODO AGREGAR VALIDACIONES

			empleado.setDni(Dentre.entero("\nINGRESE DNI (sin puntos): "));			//VALIDAR QUE NO EXISTA UN DNI IGUAL
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
				
			}catch(Exception e)
			{
				System.out.print("\n**Exception en Thread sleep **"+e+"\n");
			}
			return null;
		}		
		
	}
	
	public Persona[] bajaDeEmpleado(Persona personas[],Empleado empleado)
	{		
		int x=0;
		Persona[] auxpersonas=new Persona[10];
		while(x<personas.length)
		{			
			if(personas[x]==empleado)
			{	
			}else
			{
				auxpersonas[x]=personas[x];
			}
			x++;
		}
		auxpersonas[x-1]=null;
		return auxpersonas; 	
	}
	
	public Empleado modificarEmpleado(Empleado empleado)
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
				System.out.print("4-Modificar SEXO \n");
				System.out.print("5-Modificar LEGAJO \n");
				
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
						empleado.setApellido(Dentre.texto("\nINGRESE APELLIDO NUEVO: "));														
						break;
					case 2:
						empleado.setNombre(Dentre.texto("\nINGRESE NOMBRE NUEVO: "));
						break;
					case 3:
						empleado.setDni(Dentre.entero("\nINGRESE DNI NUEVO: "));
						break;
					case 4:
						empleado.setDni(Dentre.entero("\nINGRESE SEXO A CAMBIAR: "));
						break;
					case 5:
						empleado.setLegajo(Dentre.texto("\nINGRESE LEGAJO NUEVO: "));
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
		return empleado;
	}
	
	public String toString()
	{
		return super.toString()+"\nLegajo: "+this.getLegajo()+"\nSueldo: "+this.getSueldo()+				
		
		"\nDias Trabajados: "+this.getCantidadDiasTrabajados()+"\n"
		;
	}
	@Override
	public boolean subirAlAuto() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean manejarAuto() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean bajarAuto() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean irACasaYDescansar() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean salirOficinaHaciaAuto() {
		// TODO Auto-generated method stub
		return false;
	}
}
