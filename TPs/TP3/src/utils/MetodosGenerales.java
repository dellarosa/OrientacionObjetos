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
	
	public Persona[] bajaDeEjecutivo(Persona personas[],Ejecutivo empleado)
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
	
	
}
