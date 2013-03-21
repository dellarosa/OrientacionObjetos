package main;

import entities.*;
import utils.Dentre;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Persona[] personas=new Persona[10];
		//personas=null;
		
		Menu menu=new Menu();
		Menu.MenuPrincipal menuprincipal= menu.getMenuPrincipal();
		
		menuprincipal.comenzarmenuprincipal(personas);
		
		
		
		//personas=crearpersonasrandom(personas);
		
		
		
	}

	public static Persona[] crearpersonasrandom(Persona[] personas)
	{
		return personas;
	}

	
	
}

