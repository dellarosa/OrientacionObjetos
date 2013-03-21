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
		if(i>personas.length)
		{
			return -1;
		}
		return i;
	}
}
