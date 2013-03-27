package utils;

import entities.Ejecutivo;
import entities.Empleado;
import entities.Persona;

public class MetodosGenerales {

	public MetodosGenerales()
	{}
			
	public char validarSexo(char chsex)
	{
		
		if((chsex=='m')||(chsex=='M'))
		{
			return 'm';
		}else if((chsex=='f')||(chsex=='F'))
		{
			return 'f';
		}else
		{
			System.out.print("\nNO SE INGRESO UN SEXO CORRECTO. SE SETEARA 'm' POR DEFECTO\n");
			return 'm';
		}
	}
	public int verificarPosicionyCupoPersonas(Persona[] personas)
	{
		int i=0;
		//System.out.print("CantidadPersonas: "+personas.length);
		while(i<personas.length)
		{
			if(personas[i]==null)
			{
				break;
			}
			i++;
		}		
		if(i>=personas.length)
		{
			return -1;
		}
		return i;
	}
	

	public Empleado buscarEmpleadoPorDNI(Persona[] personas,int dnipersona)			//Podr�a hacer un metodo solo...
	{
		int x=0;
		try
		{
			while(x<personas.length)
			{	
				if(personas[x] instanceof Empleado)							//!!ATENCION: PODRIA DAR DE BAJA EMPLEADOS Y EJECUTIVOS - PERO LA LOGICA ESTA BIEN, SINO EMPLEADO Y EJECUTIVO SERIAN HERMANOS. 
				{
					if(personas[x].getDni()==dnipersona)
					{					
						//System.out.print("\n[buscarEmpleadoPorDNI] Encontre empleado: "+personas[x].getApellido());		//DEBUG
						//Thread.sleep(3000);
						return (Empleado)personas[x];					
					}				
				}
				x++;
			}		
			return null;
		}catch(Exception ex)
		{
			return null;
		}
		
	}
	
	public Ejecutivo buscarEjecutivoPorDNI(Persona[] personas,int dnipersona)
	{
		int x=0;		
		while(x<personas.length)
		{			
			if(personas[x] instanceof Ejecutivo)
			{
				if(personas[x].getDni()==dnipersona)
				{	
					return (Ejecutivo)personas[x];
				}
			
			}
			x++;
		}		
		return null;
		
	}
	
	public Persona buscarPersonaPorDNI(Persona[] personas,int dnipersona)
	{
		int x=0;		
		while(x<personas.length)
		{			
			if(personas[x] instanceof Ejecutivo)
			{
				if(personas[x].getDni()==dnipersona)
				{	
					return (Ejecutivo)personas[x];
				}
			
			}
			if(personas[x] instanceof Empleado)
			{
				if(personas[x].getDni()==dnipersona)
				{	
					return (Empleado)personas[x];
				}
			
			}
			x++;
		}		
		return null;
		
	}
	public int buscarIndiceEnPersonas(Persona[] personas,Persona persona)
	{
		int indice=0;
		while(personas[indice]!=persona)
		{
			indice++;
		}
		return indice;
	}
}
