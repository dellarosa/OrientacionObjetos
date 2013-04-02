package auto;

import java.util.Random;


public class MetodosGeneralesAuto 
{

	public MetodosGeneralesAuto()
	{
		
	}
	
	public Auto crearAutosRandom(String patente)
	{
		 int j=0;
		 int cantdef=0;
		 
		 ///////CREACION RANDOM DE  AUTO	//////////////////////////////////////////////////////
		
		Random random=new Random();
		Auto auto = new Auto();
		 CajaVelocidades cajasvelocidad=new CajaVelocidades();		
				////////////////////////////////////////////// MOTOR ////////////////////////////////////////
				 
				 Motor motorrandom=new Motor();
				 
				 	cantdef=4;
					//switch((int)(Math.random()*cantdef))
				 	switch(random.nextInt(cantdef))
					{
						case 0:
							motorrandom.setCaballosFuerza(Definiciones.CaballosdeFuerza.cantcaballosfuerza_250);
							break;
						case 1:
							motorrandom.setCaballosFuerza(Definiciones.CaballosdeFuerza.cantcaballosfuerza_100);
							break;
						case 2:
							motorrandom.setCaballosFuerza(Definiciones.CaballosdeFuerza.cantcaballosfuerza_140);
							break;
						case 3:
							motorrandom.setCaballosFuerza(Definiciones.CaballosdeFuerza.cantcaballosfuerza_120);
							break;
						default:
							break;
					}
										
					cantdef=3;
					//switch((int)(Math.random()*cantdef))
					switch(random.nextInt(cantdef))
					{
						case 0:
							motorrandom.setCilindrada(Definiciones.CilindradaMotor.cilindradamotor_1600);
							break;
						case 1:
							motorrandom.setCilindrada(Definiciones.CilindradaMotor.cilindradamotor_1800);
							break;
						case 2:
							motorrandom.setCilindrada(Definiciones.CilindradaMotor.cilindradamotor_2000);
							break;
						default:
							break;
					} 
					
					cantdef=3;					
					//switch((int)(Math.random()*cantdef))
					switch(random.nextInt(cantdef))
					{
						case 0:
							motorrandom.setMarcaMotor(Definiciones.MarcasMotor.marcaAudi);
							break;
						case 1:
							motorrandom.setMarcaMotor(Definiciones.MarcasMotor.marcaFord);
							break;
						case 2:
							motorrandom.setMarcaMotor(Definiciones.MarcasMotor.marcaKia);
							break;
						default:
							break;
					} 
					
					///////////////////////////////////CAJA DE VELOCIDADES//////////////////////////////////////////////
					cajasvelocidad = new CajaVelocidades();
					
					cantdef=3;				
					//switch((int)(Math.random()*cantdef))
					switch(random.nextInt(cantdef))
					{
						case 0:
							cajasvelocidad.setCantidadMarchas(Definiciones.CantidadMarchasCaja.cantmarchascaja_4);
							break;
						case 1:
							cajasvelocidad.setCantidadMarchas(Definiciones.CantidadMarchasCaja.cantmarchascaja_5);
							break;
						case 2:
							cajasvelocidad.setCantidadMarchas(Definiciones.CantidadMarchasCaja.cantmarchascaja_6);
							break;
						default:
							break;
					} 
					
					cantdef=3;				
					//switch((int)(Math.random()*cantdef))
					switch(random.nextInt(cantdef))
					{
						case 0:
							cajasvelocidad.setTipoDeCaja(Definiciones.RelacionMarchas.relacionmarcha_cajacorta);
							break;
						case 1:
							cajasvelocidad.setTipoDeCaja(Definiciones.RelacionMarchas.relacionmarcha_cajamediana);
							break;
						case 2:
							cajasvelocidad.setTipoDeCaja(Definiciones.RelacionMarchas.relacionmarcha_cajalarga);
							break;
						default:
							break;
					} 
					//////////////////////////////////////////// RUEDAS ///////////////////////////////////
					Rueda[] ruedascar=new Rueda[4];
					
					
					int z=0;
					while(z<4)
					{
						ruedascar[z]=new Rueda();
						z++;
					}
					
					cantdef=3;				
					//switch((int)(Math.random()*cantdef))
					switch(random.nextInt(cantdef))
					{
						case 0:
							
							while(z<4)
							{
								ruedascar[z].setColor(Definiciones.Color_string.colornegro);
								z++;
							}
							break;
						case 1:
							while(z<4)
							{
								ruedascar[z].setColor(Definiciones.Color_string.colorblanco);
								z++;
							}
							
							break;
						case 2:
							while(z<4)
							{
								ruedascar[z].setColor(Definiciones.Color_string.colorrojo);
								z++;
							}
							
							break;
						default:
							break;
					} 
					z=0;
					cantdef=3;				
					//switch((int)(Math.random()*cantdef))
					switch(random.nextInt(cantdef))
					{
						case 0:
							while(z<4)
							{
								ruedascar[z].setMaterialRueda(Definiciones.MaterialRueda.materiarueda_acero);
								z++;
							}
							break;
						case 1:
							while(z<4)
							{
								ruedascar[z].setMaterialRueda(Definiciones.MaterialRueda.materiarueda_aleacion);
								z++;
							}
							
							break;
						case 2:
							while(z<4)
							{
								ruedascar[z].setMaterialRueda(Definiciones.MaterialRueda.materiarueda_fibracarbono);
								z++;
							}
							
							break;
						default:
							break;
					}
					z=0;
					cantdef=3;				
					//switch((int)(Math.random()*cantdef))
					switch(random.nextInt(cantdef))
					{
						case 0:
							while(z<4)
							{
								ruedascar[z].setRadio(Definiciones.PulgadasRueda.pulgadarueda15);
								z++;
							}
							
							break;
						case 1:
							while(z<4)
							{
								ruedascar[z].setRadio(Definiciones.PulgadasRueda.pulgadarueda16);
								z++;
							}
							
							break;
						case 2:
							while(z<4)
							{
								ruedascar[z].setRadio(Definiciones.PulgadasRueda.pulgadarueda17);
								z++;
							}						
							break;
						default:
							break;
					}
					
					
					/////////////////////////////////
					boolean aireflag=false;
					//if((int)(Math.random()*10)<5)
					if(random.nextInt(10)<5)
					{
						aireflag=false;
					}else
					{
						aireflag=true;
					}
					//int cantpuertas=1+(int)Math.random()*5;					
					int cantpuertas=1+random.nextInt(5);
					////////////////////////////////////// CREACION DE AUTOS /////////////////
					
					auto=new Auto(motorrandom,cajasvelocidad,ruedascar,cantpuertas,aireflag,patente);
 

			 System.out.print("\n[crearautorandom]*******AUTO CREADO Nº "+(j+1)+"***********\n");
			 
			 //System.out.print("[crearautorandom]"+autos[j].getMotor().toString()+"\n");
			 //System.out.print("[crearautorandom]AIRE: "+String.valueOf(autos[j].getFlagAireAcondicionado())+"\n");
			 //System.out.print("[crearautorandom]"+autos[j].getCajadeVelocidades().toString()+"\n");
			 System.out.print("[crearautorandom] "+auto.toString()+"\n");
			 	
		 
		 return auto;
	}
	
	public String[] patenteRandom(int cantpatentes,int numpatente)
	{
		String[] patentes=new String[cantpatentes];
		int i=0;
		while(i<cantpatentes)
		{	
			patentes[i]= "AAA "+String.valueOf(numpatente+i);
			i++;
		}
		return patentes;
	}
}
