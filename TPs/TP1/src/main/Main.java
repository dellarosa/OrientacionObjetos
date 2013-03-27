package main;
import entities.*;
import utils.Definiciones;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Declaración de variables

		// Inicio de metodos
		/////////////////////////////////////////////// CREAR AUTO /////////////////////////////////////
		Motor motoraudi=new Motor("Audi",Definiciones.CaballosdeFuerza.cantcaballosfuerza_250,Definiciones.CilindradaMotor.cilindradamotor_2000);
		//Rueda[] ruedas = null;
		Rueda[] ruedas=new Rueda[4];
		
			 int x=0;
			 while(x<(int)ruedas.length)
			 {				
				 try
				 {
					 ruedas[x]=new Rueda();
					 ruedas[x].setColor("verde");
					 ruedas[x].setRadio(Definiciones.PulgadasRueda.pulgadarueda17_5);
					 ruedas[x].setMaterialRueda(Definiciones.MaterialRueda.materiarueda_aleacion);
					 
				 }catch(Exception e)
				 {
					 System.out.print("[main] EXCEPTION AL FORMATEAR RUEDAS: "+e.getMessage()+"\n");			//DEBUG
				 }
				 x++;				 
			 }
			 
			 CajaVelocidades cajavelocidad=new CajaVelocidades(Definiciones.CantidadMarchasCaja.cantmarchascaja_6,Definiciones.RelacionMarchas.relacionmarcha_cajacorta);
			 
			 Auto auto=new Auto(motoraudi,cajavelocidad,ruedas,3,true);
			 System.out.print("[main] "+ auto.toString());

		 /*System.out.print("[main] ACABO DE CREAR UN AUTO\n");
		 System.out.print("[main] Mi auto tiene motor: "+motoraudi.getMarcaMotor()+ "\n");
		 System.out.print("[main] Mi auto tiene "+ruedas.length+" ruedas.\n");
		 */
		 
		 
		 ////////////////////////////////////////////////////////////////////////////////////////////////
		 //////////////////////////////////////////////// CREAR AUTO RANDOM //////////////////////////////////
		 
		 MetodosGenerales metodos=new MetodosGenerales();		 
		Auto autosrandom[]=metodos.crearAutosRandom();
		 
			 System.out.print("\n[main] SE HAN CREADO "+autosrandom.length+" AUTOS RANDOMICAMENTE\n");
		 
		
		 
	}

}
