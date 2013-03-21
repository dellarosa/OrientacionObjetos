package entities;

import java.io.IOException;

import utils.*;

public class Menu {

	private MenuPrincipal menuprincipal;
	
	public Menu()
	{
		menuprincipal=new MenuPrincipal();
	}
	public MenuPrincipal getMenuPrincipal()
	{
		return this.menuprincipal;
	}
	
	public class MenuPrincipal
	{	
		public void comenzarmenuprincipal(Persona[] personas)
		{
			boolean sigo = true;
			int opcion = 0;
			int posicion=0;
			MetodosGenerales metodosgrles=new MetodosGenerales();
			while(sigo)
			{
				System.out.print("1- Alta Empleado\n");
				System.out.print("2- Alta Ejecutivo\n");
				System.out.print("3- Modificar Empleado\n");
				System.out.print("4- Modificar Ejecutivo\n");
				System.out.print("5- Baja Empleado\n");
				System.out.print("6- Baja Empleado\n");
				System.out.print("7- Calcular Vacaciones\n");
				System.out.print("8- Disminuir Horas\n");
				System.out.print("9- Aumentar Sueldo\n");
				System.out.print("10- Mostrar Datos\n");
				
				System.out.print("99- Salir\n");
				try
				{
					opcion=Dentre.entero("");
				}catch(Exception ex)
				{
					System.out.print("Datos ingresados incorrectamente\n");
				}
				switch(opcion)
				{
					case 1:
						try
						{
							posicion=metodosgrles.verificarPosicionyCupoPersonas(personas);
							Empleado empleado=new Empleado();	
							
								if(posicion>=0)
								{
									personas[posicion]=empleado.altaDeNuevaPersonaEnEmpresa();
										
								}else
								{
									System.out.print("\nNO QUEDAN CUPOS EN LA EMPRESA\n");
									break;
								}			
								
								try
								{
									System.out.print("EMPLEADO CREADO CORRECTAMENTE - SE REGRESARA AL MENU PRINCIPAL");
									Thread.sleep(3000);							
								}catch(Exception ex)
								{
									System.out.print("[menu] Thread Sleep Exception: "+ex);
								}
						}catch(Exception ex)
						{
							System.out.print("[menu] Exception GENERAL: "+ex);
						}
						
						break;
					case 2:
						try
						{
							posicion=metodosgrles.verificarPosicionyCupoPersonas(personas);
							//System.out.print("\n[MENU] SUELDO EJE: "+Definiciones.SueldoEjecutivos+"\n");
							Ejecutivo ejecutivo=new Ejecutivo(Definiciones.SueldoEjecutivos);	
							if(posicion>=0)
							{
								personas[posicion]=ejecutivo.altaDeNuevaPersonaEnEmpresa();
									
							}else
							{
								System.out.print("\nNO QUEDAN CUPOS EN LA EMPRESA\n");
								break;
							}	
							try
							{
								System.out.print("EJECUTIVO CREADO CORRECTAMENTE - SE REGRESARÁ AL MENU PRINCIPAL");
								Thread.sleep(3000);							
							}catch(Exception ex)
							{
								System.out.print("[menu] Thread Sleep Exception: "+ex);
							}
						}catch(Exception ex)
						{
							System.out.print("[menu] Exception GENERAL: "+ex);
						}	
							
						break;
					case 3:
							//modificarEmpleado();
						break;
					case 4:
							//modificarejecutivo();
						break;
					case 5:
							//bajaEmpleado();
						break;
					case 6:
						//	bajaEjecutivo();
						break;
					case 7:
						break;
					case 8:
						break;
					case 9:
						int j=0;
						boolean exit=false;
						boolean next;
						while((j<personas.length)&&(!exit))
						{
							if(personas[j] instanceof Empleado)
							{
								next=false;
								while(!next)
								{
									Empleado empleado_show=(Empleado) personas[j];								
									System.out.print("\n** EL EMPLEADO "+empleado_show.getApellido()+", "+empleado_show.getNombre()+" - DE DNI: "
									+empleado_show.getDni()+" TIENE UN SUELDO DE: $"+empleado_show.getSueldo()+"\n");
									switch(mostrarContinuaOSale())
									{
										case 'q':
											next=true;
											exit=true;
										break;
										case 'c':
											next=true;
										break;
									}
								}
								
							}else if(personas[j] instanceof Ejecutivo)
							{
								next=false;
								while(!next)
								{
									Ejecutivo ejecutivo_show=(Ejecutivo) personas[j];
									
									System.out.print("\nEL EJECUTIVO "+ejecutivo_show.getApellido()+", "+ejecutivo_show.getNombre()+" - DE DNI: "
									+ejecutivo_show.getDni()+" TIENE UN SUELDO DE: $"+ejecutivo_show.getSueldo()+" Y SU EDAD ES: "+ejecutivo_show.getEdad()+"\n");
									
									switch(mostrarContinuaOSale())
									{
										case 'q':
											next=true;
											exit=true;
										break;
										case 'c':
											next=true;
										break;
									}
								}
							}else
							{
								
							}						
								
							j++;
						}
						
						break;
					case 10:
						break;
					case 99:
						sigo=false;
					break;
					
					default:
						System.out.print("OPCION INCORRECTA, SE REGRESARÁ AL MENU PRINCIPAL");
						try
						{
							Thread.sleep(3000);
						}catch(Exception ex)
						{
							System.out.print("[menu] Thread Sleep Exception: "+ex);
						}
						break;
				}
			}
		}
	}
	public char mostrarContinuaOSale()
	{		
		try
		{			
			switch(Dentre.caracter("INGRESE C para continuar y Q para salir: \n"))
			{
				case 'Q':
				case 'q':
					return 'q';
				case 'C':
				case 'c':
					return 'c';
				default:
					return 'q';
				
			}
		}catch(Exception ex)
		{
			System.out.print("[mostrarContinuaOSale] Exception: "+ex);
			return 'q';
		}
		
		
	}

}
