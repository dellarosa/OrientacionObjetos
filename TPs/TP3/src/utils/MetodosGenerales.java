package utils;

import auto.Auto;
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
	

	public Empleado buscarEmpleadoPorDNI(Persona[] personas,int dnipersona)			//Podría hacer un metodo solo...
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
	
	public Auto asignarAuto(Auto[] autos)
	{
		
		boolean salir=false;
		System.out.print("\nSe mostraran los autos disponibles.\n");
		System.out.print("\nCANTIDAD AUTOS: "+ autos.length+"\n"); 	//DEBUG
		int i=0;
		char opcion;
		while((!salir)&&(i<autos.length))
		{	System.out.print("\nI= "+i);	//DEBUG
			if(autos[i].getDisponibilidad()==true)
			{
				System.out.print("\n"+autos[i].toString());
				opcion=Dentre.caracter("\n \n'A' para asignar auto 'C' para continuar con proximo 'Q' para salir");
				switch(opcion)
				{
					case 'A':
					case 'a':
						System.out.print("\nPASE A \n");	//DEBUG
						return autos[i];
						
					case 'c':
					case 'C':
						System.out.print("\nPASE C\n");	//DEBUG
						break;
						
					case 'q':
					case 'Q':
						System.out.print("\nPASE Q \n");	//DEBUG
							salir=true;
							break;
					default:
						
						break;
				}
			}
			i++;
		}
		return null;
	}
	public void mostrarAutos(Auto[] autos)
	{
		System.out.println("\nCantidadAutos: "+autos.length+"\n");
		
		int i=0;
		while(i<autos.length)
		{
			if(autos[i]!=null)
			System.out.print("\n ******AUTO Nº: "+i+1+"*******\n"+autos[i].toString()+"\n");
			
			i++;
		}
		
	}
	
}
