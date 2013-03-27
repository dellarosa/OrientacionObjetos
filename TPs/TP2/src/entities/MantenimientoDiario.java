package entities;

import entities.Persona;

public class MantenimientoDiario extends Thread {
	Persona[] personaspresent;
    public MantenimientoDiario(Persona[] personas) {
    	this.personaspresent=personas;
    }
    public void run() 
    {
    	if(personaspresent!=null)
    	{
    		int indice=0;    	
    		while(indice<personaspresent.length)
    		{
    			if(personaspresent[indice]!=null)
    			{
			    	setAumentarDiaTrabajado(personaspresent[indice]);
			    	//System.out.println("\nAumente un día\n");
					try {
						MantenimientoDiario.sleep(2000);
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			
    		}
			indice++;
    	}
    	
    	try {
			MantenimientoDiario.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    public void setAumentarDiaTrabajado(Persona persona)
    {
    	
    		if(persona instanceof Empleado)
    		{
    			Empleado empleado=(Empleado)persona;
    			empleado.setCantidadDiasTrabajados(empleado.getCantidadDiasTrabajados()+1);
    		}else if(persona instanceof Ejecutivo)
    		{
    			Ejecutivo ejecutivo=(Ejecutivo)persona;
    			ejecutivo.setCantidadDiasTrabajados(ejecutivo.getCantidadDiasTrabajados()+1);
    		}    		
    	
    	
    	try
    	{
    		//System.out.println("\nSE REALIZO CORRECTAMENTE EL MANTENIMIENTO DIARIO\n");
    		MantenimientoDiario.sleep(2000);
    	}catch(Exception ex)
    	{
    		
    	}
    }    
}