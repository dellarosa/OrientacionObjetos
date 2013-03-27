package entities;

import utils.Definiciones;

public class MetodosGenerales 
{

	public MetodosGenerales()
	{
		
	}
	
	@SuppressWarnings("null")
	public Auto[] crearAutosRandom()
	{
		 int j=0;
		 int cantdef=0;
		 
		 ///////CREACION RANDOM DE 6 AUTOS	//////////////////////////////////////////////////////
		int cantautos=6;

		Auto[] autos = new Auto[cantautos];
		 CajaVelocidades[] cajasvelocidad=new CajaVelocidades[cantautos];

			 while(j<cantautos)
			 {				
				////////////////////////////////////////////// MOTOR ////////////////////////////////////////
				 
				 Motor motorrandom=new Motor();
				 
				 	cantdef=4;
					switch((int)(Math.random()*cantdef))
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
					switch((int)(Math.random()*cantdef))
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
					switch((int)(Math.random()*cantdef))
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
					cajasvelocidad[j] = new CajaVelocidades();
					
					cantdef=3;				
					switch((int)(Math.random()*cantdef))
					{
						case 0:
							cajasvelocidad[j].setCantidadMarchas(Definiciones.CantidadMarchasCaja.cantmarchascaja_4);
							break;
						case 1:
							cajasvelocidad[j].setCantidadMarchas(Definiciones.CantidadMarchasCaja.cantmarchascaja_5);
							break;
						case 2:
							cajasvelocidad[j].setCantidadMarchas(Definiciones.CantidadMarchasCaja.cantmarchascaja_6);
							break;
						default:
							break;
					} 
					
					cantdef=3;				
					switch((int)(Math.random()*cantdef))
					{
						case 0:
							cajasvelocidad[j].setTipoDeCaja(Definiciones.RelacionMarchas.relacionmarcha_cajacorta);
							break;
						case 1:
							cajasvelocidad[j].setTipoDeCaja(Definiciones.RelacionMarchas.relacionmarcha_cajamediana);
							break;
						case 2:
							cajasvelocidad[j].setTipoDeCaja(Definiciones.RelacionMarchas.relacionmarcha_cajalarga);
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
					switch((int)(Math.random()*cantdef))
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
					switch((int)(Math.random()*cantdef))
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
					switch((int)(Math.random()*cantdef))
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
					if((int)(Math.random()*10)<5)
					{
						aireflag=false;
					}else
					{
						aireflag=true;
					}
					int cantpuertas=1+(int)Math.random()*5;					
									
					////////////////////////////////////// CREACION DE AUTOS /////////////////
					autos[j]=new Auto(motorrandom,cajasvelocidad[j],ruedascar,cantpuertas,aireflag);
				
					
					j++;
			 }

		 j=0;
		 while(j<autos.length)
		 {
			 System.out.print("\n[crearautorandom]*******AUTO CREADO Nº "+(j+1)+"***********\n");
			 
			 System.out.print("[crearautorandom]MOTOR: "+autos[j].getMotor().getMarcaMotor()+"\n");
			 System.out.print("[crearautorandom]AIRE: "+String.valueOf(autos[j].getFlagAireAcondicionado())+"\n");
			 System.out.print("[crearautorandom]MARCHAS: "+autos[j].getCajadeVelocidades().getCantidadDeMarchas()+"\n");
			 j++;	 		
			 		
		 }
		 
		 return autos;
	}
}
