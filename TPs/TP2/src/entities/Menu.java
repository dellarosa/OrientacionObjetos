package entities;

import java.io.IOException;

import auto.Auto;

import utils.*;

public class Menu {

	private MenuPrincipal menuprincipal;
	private int dnibuscado;
	private Empleado auxempleado;
	private Ejecutivo auxejecutivo;
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
		public void comenzarmenuprincipal(Persona[] personas,Auto[] autos)
		{
			boolean sigo = true;
			int opcion = 0;
			int posicion=0;
			MetodosGenerales metodosgrles=new MetodosGenerales();
			
			while(sigo)
			{
				System.out.print("\nMENU PRINCIPAL\n");
				System.out.print("\n1- Alta Empleado\n");
				System.out.print("2- Alta Ejecutivo\n");
				System.out.print("3- Modificar Empleado\n");
				System.out.print("4- Modificar Ejecutivo\n");
				System.out.print("5- Baja Empleado\n");
				System.out.print("6- Baja Ejecutivo\n");
				System.out.print("7- Calcular Vacaciones\n");
				System.out.print("8- Disminuir Horas\n");
				System.out.print("9- Aumentar Sueldo\n");
				System.out.print("10- Mostrar Datos\n");
				System.out.print("11- Asignar Auto\n");
				
				System.out.print("95- Simulacion\n");
				
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
							//metodosgrls.verificarVacantesEnEmpresa();
							posicion=metodosgrles.verificarPosicionyCupoPersonas(personas);
							//Empleado empleado=new Empleado(Definiciones.SueldoInicialGeneral);	
							Empleado empleado=new Empleado(0);
							
								if(posicion>=0)
								{
									personas[posicion]=empleado.altaDeEmpleado(empleado);
										
								}else
								{
									System.out.print("\nNO QUEDAN CUPOS EN LA EMPRESA\n");
									Thread.sleep(2000);
									break;
								}			
								
								try
								{
									System.out.print("EMPLEADO CREADO CORRECTAMENTE - SE REGRESARA AL MENU PRINCIPAL");
									Thread.sleep(2000);							
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
								personas[posicion]=ejecutivo.altaDeEmpleado(ejecutivo);
									
							}else
							{
								System.out.print("\nNO QUEDAN CUPOS EN LA EMPRESA\n");
								Thread.sleep(2000);										
								break;
							}	
							try
							{
								System.out.print("EJECUTIVO CREADO CORRECTAMENTE - SE REGRESARÁ AL MENU PRINCIPAL");
								Thread.sleep(2000);							
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
							dnibuscado=Dentre.entero("\nINGRESE DNI (sin puntos) DEL EMPLEADO A MODIFICAR: ");		//TODO: VALIDAR
						Empleado empleadobuscadoparamodif=metodosgrles.buscarEmpleadoPorDNI(personas,dnibuscado);
						
						try
						{
							if(empleadobuscadoparamodif!=null)
							{
								System.out.print("\n[menu] SE MODIFICARÁ EL EMPLEADO: "+empleadobuscadoparamodif.getApellido());
								Thread.sleep(2000);
							}
							else
							{
								System.out.print("\n[menu] No se encontro empleado\n");							
								Thread.sleep(2000);	
								break;
							}
							if(menuYesorNot())
							{								
								Empleado auxempleado=new Empleado();
								auxempleado=empleadobuscadoparamodif.modificarEmpleado(empleadobuscadoparamodif);								
								if(auxempleado != null)
								{	
									personas[metodosgrles.buscarIndiceEnPersonas(personas,empleadobuscadoparamodif)]=auxempleado;
								}else
								{
									System.out.print("\n[menu] ERROR - No se reemplazó el empleado");
									Thread.sleep(2000);
								}	
							}
						}catch(Exception ex)
						{
							
						}
						break;
					case 4:
						dnibuscado=Dentre.entero("\nINGRESE DNI (sin puntos) DEL EJECUTIVO A MODIFICAR: ");		//TODO: VALIDAR
						Ejecutivo ejecutivobuscadoparamodif=metodosgrles.buscarEjecutivoPorDNI(personas,dnibuscado);
						
						try
						{
							if(ejecutivobuscadoparamodif!=null)
							{
								System.out.print("\n[menu] SE MODIFICARÁ EL EJECUTIVO: "+ejecutivobuscadoparamodif.getApellido());
								Thread.sleep(2000);
							}
							else
							{
								System.out.print("\n[menu] No se encontro ejecutivo\n");							
								Thread.sleep(2000);	
								break;
							}
							if(menuYesorNot())
							{								
								Ejecutivo auxejecutivoo=new Ejecutivo();
								auxejecutivoo=ejecutivobuscadoparamodif.modificarEmpleado(ejecutivobuscadoparamodif);								
								if(auxejecutivoo != null)
								{	
									personas[metodosgrles.buscarIndiceEnPersonas(personas,ejecutivobuscadoparamodif)]=auxejecutivoo;
								}else
								{
									System.out.print("\n[menu] ERROR - No se reemplazó el ejecutivo");
									Thread.sleep(2000);
								}	
							}
						}catch(Exception ex)
						{
							
						}
						break;
					case 5:
							//bajaEmpleado();
						int dniempleado=Dentre.entero("\nINGRESE DNI (sin puntos) DEL EMPLEADO A DAR DE BAJA: ");		//TODO: VALIDAR
						Empleado empleadobuscado=metodosgrles.buscarEmpleadoPorDNI(personas,dniempleado);
						try
						{
							if(empleadobuscado!=null)
							{
								System.out.print("\n[menu] SE BORRARA EL EMPLEADO: "+empleadobuscado.getApellido());
								Thread.sleep(2000);
							}else
							{
								System.out.print("\n[menu] No se encontro empleado\n");							
								Thread.sleep(2000);	
								break;
							}
							
							if(menuYesorNot())
							{								
								Persona[] auxpersonas=new Persona[10];
								auxpersonas=empleadobuscado.bajaDeEmpleado(personas,empleadobuscado);						
								if(auxpersonas != null)
								{
									personas=auxpersonas;
									System.out.print("\n[menu] Empleado dado de baja correctamente");
									Thread.sleep(2000);						
								}else
								{
										System.out.print("\n[menu] ERROR - No se pudo dar de baja empleado");
										Thread.sleep(2000);
								}	
							}
						}catch(Exception ex)
						{}						
						break;
					case 6:
						//	bajaEjecutivo();
						dnibuscado=Dentre.entero("\nINGRESE DNI (sin puntos) DEL EJECUTIVO A DAR DE BAJA: ");
						Ejecutivo ejecutivobuscado=metodosgrles.buscarEjecutivoPorDNI(personas,dnibuscado);
						try
						{
							if(ejecutivobuscado!=null)
							{
								System.out.print("\n[menu] SE BORRARA EL EJECUTIVO: "+ejecutivobuscado.getApellido());
								Thread.sleep(2000);
							}else
							{
								System.out.print("\n[menu] No se encontro empleado\n");							
								Thread.sleep(2000);	
								break;
							}

							if(menuYesorNot())
							{								
								Persona[] auxpersonas=new Persona[10];
								auxpersonas=ejecutivobuscado.bajaDeEmpleado(personas,ejecutivobuscado);						
								if(auxpersonas != null)
								{
									personas=auxpersonas;
									System.out.print("\n[menu] Empleado dado de baja correctamente");
									Thread.sleep(2000);
								}else
								{
									System.out.print("\n[menu] No se pudo dar de baja empleado");
									Thread.sleep(2000);
								}											
							}							
						}catch (InterruptedException e) {
							e.printStackTrace();							
						}
						break;
					case 7:
						int indicevacas=0;
						boolean salir=false;
						while((indicevacas<personas.length)&&(!salir))
						{
							if(personas[indicevacas] instanceof Empleado)
							{		
								System.out.print("\n[menu] EL EMPLEADO "+personas[indicevacas].getApellido()+" TIENE "+ 
							    personas[indicevacas].getCantidadDiasVacaciones()+" DE VACACIONES\n");
								
								if(menuYesorNot())
								{									
								}else
								{
									salir=true;
								}								
							}							
							else if(personas[indicevacas] instanceof Ejecutivo)
							{
								System.out.print("\n[menu] EL EJECUTIVO "+personas[indicevacas].getApellido()+" TIENE "+ 
							    personas[indicevacas].getCantidadDiasVacaciones()+" DE VACACIONES\n");
								
								if(menuYesorNot())
								{									
								}else
								{
									salir=true;
								}
							}
							indicevacas++;
						}
						break;
					case 8:
						try
						{
							dnibuscado=Dentre.entero("\nINGRESE DNI (sin puntos): ");
							
							Persona personabuscada=metodosgrles.buscarPersonaPorDNI(personas,dnibuscado);
							
							if(personabuscada!=null)
							{
								System.out.print("\n[menu] DISMINUIR HORAS A LA PERSONA: "+personabuscada.getApellido());
								Thread.sleep(2000);
							}else
							{
								System.out.print("\n[menu] No se encontro persona\n");							
								Thread.sleep(2000);	
								break;
							}
	
							if(menuYesorNot())
							{			
								int canthorasdisminuir=Dentre.entero("\nINGRESE CANTIDAD DE HORAS QUE DESEA DISMINUIR POR DIA: ");
								
								
									if(personabuscada instanceof Empleado)
									{
										Empleado empleadohoras=(Empleado)personabuscada;
										if(empleadohoras.setDisminuirHoras(canthorasdisminuir))
										{
											System.out.print("\n[menu] HORAS DE EMPLEADO DISMINUIDAS CORRECTAMENTE\n");							
											Thread.sleep(2000);	
										}else
										{
											System.out.print("\n[menu] NO SE DISMINUYERON LAS HORAS DEL EMPLEADO. ERROR\n");							
											Thread.sleep(2000);
										}
									}
									else if(personabuscada instanceof Ejecutivo)
									{
										Ejecutivo ejecutivohoras=(Ejecutivo)personabuscada;
										if(ejecutivohoras.setDisminuirHoras(canthorasdisminuir))
										{
											System.out.print("\n[menu] HORAS DE EJECUTIVO DISMINUIDAS CORRECTAMENTE\n");							
											Thread.sleep(2000);	
										}else
										{
											System.out.print("\n[menu] NO SE DISMINUYERON LAS HORAS DEL EJECUTIVO. ERROR\n");							
											Thread.sleep(2000);
										}
									}
							}	
						}catch(Exception e)
						{
							
						}
						
						
						break;
					case 9:
						try
						{
							dnibuscado=Dentre.entero("\nINGRESE DNI (sin puntos): ");
							
							Persona personabuscada=metodosgrles.buscarPersonaPorDNI(personas,dnibuscado);
							
							if(personabuscada!=null)
							{
								System.out.print("\n[menu] AUMENTAR SUEDO DE: "+personabuscada.getApellido());
								Thread.sleep(2000);
							}else
							{
								System.out.print("\n[menu] No se encontro persona\n");							
								Thread.sleep(2000);	
								break;
							}
	
							if(menuYesorNot())
							{			
								double porcentajeaumento=Dentre.doble("\nINGRESE PORCENTAJE DE AUMENTO (1-200%): ");
								if((porcentajeaumento<1)||(porcentajeaumento>200))
								{
									System.out.print("\n[menu] EL PORCENTAJE INGRESADO ES INCORRECTO\n");							
									Thread.sleep(2000);
									break;
								}
								
								if(personabuscada instanceof Empleado)
								{
									auxempleado=(Empleado)personabuscada;
									auxempleado.setSueldo(((porcentajeaumento/100)+1)*auxempleado.getSueldo());
									
										System.out.print("\n[menu] SUELDO AUMENTADO CORRECTAMENTE\n");							
										Thread.sleep(2000);	
									
								}
								else if(personabuscada instanceof Ejecutivo)
								{
									auxempleado=(Empleado)personabuscada;
									auxempleado.setSueldo(((porcentajeaumento/100)+1)*auxempleado.getSueldo());
									
										System.out.print("\n[menu] SUELDO AUMENTADO CORRECTAMENTE\n");							
										Thread.sleep(2000);
								}
							}
						}catch(Exception e)
						{
							
						}
						break;
					case 10:
						int j=0;
						boolean exit=false;
						boolean next;
						try
						{
							while((j<personas.length)&&(!exit))
							{
								 if(personas[j] instanceof Ejecutivo)
								{
										next=false;
										while(!next)
										{
											Ejecutivo ejecutivo_show=(Ejecutivo) personas[j];
											
											System.out.print("\n**DATOS EJECUTIVOS:\n" +ejecutivo_show.toString());
											
											/*System.out.print("\nEL EJECUTIVO "+ejecutivo_show.getApellido()+", "+ejecutivo_show.getNombre()+" - DE DNI: "
											+ejecutivo_show.getDni()+" TIENE UN SUELDO DE: $"+ejecutivo_show.getSueldo()+" Y SU EDAD ES: "+ejecutivo_show.getEdad()+
											".\nTRABAJA "+ejecutivo_show.getCantHorasTrabajoDiarias()+" HORAS DIARIAS.\n");
											*/
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
									}
									else if(personas[j] instanceof Empleado)
									{									
										next=false;
										while(!next)
										{
											Empleado empleado_show=(Empleado) personas[j];
											System.out.print("\n**DATOS EMPLEADOS:\n" +empleado_show.toString());
											
											/*System.out.print("\n** EL EMPLEADO "+empleado_show.getApellido()+", "+empleado_show.getNombre()+" - DE DNI: "
											+empleado_show.getDni()+" TIENE UN SUELDO DE: $"+empleado_show.getSueldo()
											+".\nTRABAJA "+empleado_show.getCantHorasTrabajoDiarias()+" HORAS DIARIAS\n");*/
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
										
										System.out.print("\nNO HAY MAS EMPLEADO EN SU EMPRESA\n");
										Thread.sleep(2000);
										break;
									}						
									
								j++;
							}						
							
						}catch(Exception ex)
						{
							System.out.print("\nException: "+ex);							
						}
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
			switch(Dentre.caracter("\nINGRESE C para continuar y Q para salir: \n"))
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
	
	public boolean menuYesorNot()
	{		
		while(true)
		{
			switch(Dentre.caracter("\nCONTINUAR (Y - N)? \n"))
			{
				case 'y':
				case 'Y':					
					return true;
				case 'N':
				case 'n':					
					return false;
			}
		}
		
	}

}
