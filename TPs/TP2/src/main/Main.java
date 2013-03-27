package main;

import entities.*;
import utils.Dentre;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Persona[] personas=new Persona[10];
		
		Menu menu=new Menu();
		
		MantenimientoDiario mantdiario=new MantenimientoDiario(personas);
		mantdiario.start();		

		
		Menu.MenuPrincipal menuprincipal= menu.getMenuPrincipal();
		
		menuprincipal.comenzarmenuprincipal(personas);		
		//personas=crearpersonasrandom(personas);
		
	}

	public static Persona[] crearpersonasrandom(Persona[] personas)
	{
		return personas;
	}

	
	
}

