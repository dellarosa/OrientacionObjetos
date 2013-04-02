package main;

import auto.Auto;
import auto.MetodosGeneralesAuto;
import entities.*;
import utils.Dentre;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Persona[] personas=new Persona[10];
		Auto[] autos=new Auto[5];
		Menu menu=new Menu();
		
		MantenimientoDiario mantdiario=new MantenimientoDiario(personas);
		mantdiario.start();		
		
		
		autos=crearAutosEmpresa(5);
		
		
		
		Menu.MenuPrincipal menuprincipal= menu.getMenuPrincipal();
		
		menuprincipal.comenzarmenuprincipal(personas,autos);		
		//personas=crearpersonasrandom(personas);
		
	}
	
	public static Auto[] crearAutosEmpresa(int cantAutos)
	{
		Auto[] autos=new Auto[cantAutos];
		MetodosGeneralesAuto metodosauto=new MetodosGeneralesAuto();
		int i=0;
		int inpatente=123;
		
		String[] patentes= metodosauto.patenteRandom(cantAutos, inpatente);
		
		while(i<cantAutos)
		{
			autos[0]=metodosauto.crearAutosRandom(patentes[i]);
			i++;
		}
		return autos;
	}

	public static Persona[] crearpersonasrandom(Persona[] personas)
	{
		return personas;
	}

	
	
}

