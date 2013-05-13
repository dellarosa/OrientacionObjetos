package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import SQL.SQLCarga;
import SQL.SQLDelete;
import SQL.SQLInserts;
import SQL.SQLModif;
import SQL.SQLSelects;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;
import entities.Usuario;

import utils.Definiciones;
import utils.Dentre;
import utils.MetodosGrl;
import utils.MiException;

public class Menu {
	
	MenuPrincipal menuprinc;
	MetodosGrl metgral;
	MenuInicio menuinicio;
	
	SQLInserts sqlinserts=new SQLInserts();
	SQLSelects sqlselects=new SQLSelects();
	SQLDelete sqldelete=new SQLDelete();
	SQLCarga sqlcarga =new SQLCarga();
	SQLModif sqlmodif=new SQLModif();
	public Menu()
	{
		menuprinc=new MenuPrincipal();
		menuinicio=new MenuInicio();
		
	}
	public MenuPrincipal getMenuPrincipal()
	{
		return this.menuprinc;
	}
	//////////////
	public MenuInicio getMenuInicio()
	{
		return this.menuinicio;
	}
	public class MenuPrincipal
	{
		boolean sigo;		
		
		public MenuPrincipal()
		{
			metgral=new MetodosGrl();
		}
		public String empezarMenu(Usuario user) throws Exception,MiException
		{	
			List<Autoparte> autopartesG=new ArrayList<Autoparte>();
			List<Cliente> clientesG=new ArrayList<Cliente>();
			List<Reparacion> reparacionesG=new ArrayList<Reparacion>();
			//Filtro[] filtrosG=new Filtro[]{};
			//Lampara[] lamparasG=new Lampara[]{};
			//Aceite[] aceitesG=new Aceite[]{};
			
			sigo=false;
			while(!sigo)
			{
				
				try
				{
					autopartesG=sqlcarga.cargaAutopartes();
					clientesG=sqlcarga.cargaClientes();
					reparacionesG=sqlcarga.cargaReparaciones();
					
				}catch(SQLException e)
				{
					throw new MiException("Error de conexión SQL");
				}
				
				System.out.print("\n1-CARGAR CLIENTE");
				System.out.print("\n2-MODIFICACION CLIENTE");
				System.out.print("\n3-BAJA CLIENTE");
				System.out.print("\n4-CARGAR AUTOPARTE");
				System.out.print("\n5-MODIFICACION AUTOPARTE");
				System.out.print("\n6-BAJA AUTOPARTE");
				System.out.print("\n7-CARGAR REPARACION");
				System.out.print("\n8-FINALIZAR REPARACION");
				System.out.print("\n9-VER REPARACIONES");				
				System.out.print("\n10-MOSTRAR DATOS");				
				
				//System.out.print("\n98-CAMBIAR DE USUARIO");
				System.out.print("\n99-SALIR");
				
				int opcion=Dentre.entero("\nINGRESE OPCION:");
				
				switch(opcion)
				{
					case 1:			//CARGAR CLIENTE
						try
						{						
							
							Cliente cliente= new Cliente();
							//cliente.setId(metgral.buscarUltimoCliente(clientesG));
							//TODO BUSCAR ID
							cliente.setNombre(Dentre.texto("\nINGRESE NOMBRE: "));
							cliente.setMail(Dentre.texto("\nINGRESE MAIL: "));
							cliente.setAuto(Dentre.texto("\nINGRESE AUTO: "));
							
							if(sqlinserts.insertarCliente(cliente))
							{
								System.out.print("\nCLIENTE INGRESADO CORRECTAMENTE");
								Thread.sleep(2000);
							}else
							{
								System.out.print("\nFALLO INGRESO CLIENTE");
								Thread.sleep(2000);
							}
							
						}
						catch(MiException e)
						{
							throw e;
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						break;
					case 2:			//MODIFICAR CLIENTE
							try
							{
								Cliente cliente=sqlselects.buscarClientePorApodo(Dentre.texto("\n INGRESE APODO CLIENTE A MODIFICAR: "));
																
								//PODRIA VALIDAR Y PREGUNTAR SI QUIERE DEJAR IGUAL O CAMBIAR
								//EJ
								
								if(Dentre.texto("\nMODIFICAR NOMBRE CLIENTE? (yes - not): "+cliente.getNombre())=="yes")
								{
									cliente.setNombre(Dentre.texto("\nMODIFIQUE NOMBRE: "));	
								}
								//...
								cliente.setMail(Dentre.texto("\nMODIFIQUE MAIL: "));
								cliente.setAuto(Dentre.texto("\nMODIFIQUE AUTO: "));
								
								if(sqlinserts.insertarCliente(cliente))
								{
									System.out.print("\nCLIENTE MODIFICADO CORRECTAMENTE");
									Thread.sleep(2000);
								}else
								{
									System.out.print("\nFALLO MODIFICACION CLIENTE");
									Thread.sleep(2000);
								}	
							}
							catch(MiException e)
							{
								throw e;
							}
							catch(Exception e)
							{							
								throw new MiException("DELETE USER EXCEPTION: "+e);
							}	
														
						break;
					case 3:			//ELIMINAR CLIENTE
						try
						{
							Cliente cliente=sqlselects.buscarClientePorApodo(Dentre.texto("\n INGRESE APODO CLIENTE A DAR DE BAJA: "));
							if(sqldelete.eliminarCliente(cliente))
							{
								System.out.print("\nCLIENTE ELIMINADO CORRECTAMENTE");
								Thread.sleep(2000);
							}else
							{
								System.out.print("\nFALLO BAJA CLIENTE");
								Thread.sleep(2000);
							}	
						}catch(MiException e)
						{
							throw e;
						}
						catch(Exception e)
						{							
							throw new MiException("DELETE USER EXCEPTION: "+e);
						}	
						break;
					case 4:				//CARGAR AUTOPARTE
						try
						{							
							//PODRIA SER MAS DINAMICO							
							System.out.print("\n1-FILTROS");	
							System.out.print("\n2-ACEITE");
							System.out.print("\n3-LAMPARA");
							System.out.print("\n4-NUEVA AUTOPARTE");
							switch(Dentre.entero("\nINGRESE TIPO AUTOPARTE: "))
							{
								case Definiciones.FILTRO_INDICE:
									Filtro filtro =new Filtro();
									
									filtro.setAutoparteID(sqlselects.buscarUltimaAutoparteId());
									
									filtro.setId(sqlselects.buscarUltimoFiltroId());
									filtro.setMaterial(Dentre.texto("\nINGRESE TIPO MATERIAL: "));
									filtro.setTamaño(Dentre.texto("\nINGRESE TAMAÑO: "));
									filtro.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									filtro.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									filtro.setTipoAutoparte("filtro");
									filtro.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									filtro.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									
									sqlinserts.insertarFiltro(filtro);
									sqlinserts.insertarAutoparte(filtro.getAutoparteID(),filtro.getTipoAutoparte(),filtro.getMarca(),filtro.getModelo(),filtro.getCosto(),filtro.getCantDisponible());
										
									break;
									
								case Definiciones.ACEITE_INDICE:
									Aceite aceite =new Aceite();
									
									aceite.setAutoparteID(sqlselects.buscarUltimaAutoparteId());
									
									aceite.setId(sqlselects.buscarUltimoAceiteId());
									aceite.setCantidadlitros(Dentre.entero("\nINGRESE CANTIDAD LITROS: "));
									aceite.setTipoAceite(Dentre.texto("\nINGRESE TAMAÑO: "));
									aceite.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									aceite.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									aceite.setTipoAutoparte("filtro");
									aceite.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									aceite.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									
									sqlinserts.insertarAceite(aceite);
									sqlinserts.insertarAutoparte(aceite.getAutoparteID(),aceite.getTipoAutoparte(),aceite.getMarca(),aceite.getModelo(),aceite.getCosto(),aceite.getCantDisponible());
									break;
									
								case Definiciones.LAMPARA_INDICE:
									Lampara lampara =new Lampara();
									
									lampara.setAutoparteID(sqlselects.buscarUltimaAutoparteId());
									
									lampara.setId(sqlselects.buscarUltimoLamparaId());
									lampara.setColor(Dentre.texto("\nINGRESE COLOR: "));
									lampara.setTamaño(Dentre.texto("\nINGRESE TAMAÑO: "));
									lampara.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									lampara.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									lampara.setTipoAutoparte("filtro");
									lampara.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									lampara.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									
									sqlinserts.insertarLampara(lampara);
									sqlinserts.insertarAutoparte(lampara.getAutoparteID(),lampara.getTipoAutoparte(),lampara.getMarca(),lampara.getModelo(),lampara.getCosto(),lampara.getCantDisponible());
									
									break;
								
								case 4:
									break;
								default:
									break;
							}
							
						}
						catch(MiException e)
						{
							throw e;
						}catch(SQLException e)
						{
							throw new MiException("[CARGAR AUTOPARTE] SQLException : "+e);
						}catch(Exception e)
						{
							throw new MiException("[CARGAR AUTOPARTE] Exception : "+e);
						}
						break;
					case 5:		//MODIFICAR AUTOPARTE
						try
						{							
							int index=0;
							int opcionmodif=0;
							Filtro filtro=new Filtro();
							Aceite aceite= new Aceite();
							Lampara lampara=new Lampara();
							
							//PODRIA SER MAS DINAMICO							
							System.out.print("\n1-FILTROS");	
							System.out.print("\n2-ACEITE");
							System.out.print("\n3-LAMPARA");							
							switch(Dentre.entero("\nINGRESE TIPO AUTOPARTE A MODIFICAR: "))
							{
								case Definiciones.FILTRO_INDICE:
									
									System.out.print("\n**FILTROS**");
																											
									for (Autoparte autoparte : autopartesG)
									{										
										if(autoparte instanceof Filtro)
										{												
											filtro=(Filtro)autoparte;
											System.out.print("\nID: "+filtro.getFiltro_ID()+" - Marca: "+filtro.getMarca()+" - Modelo:"+filtro.getModelo());
										}
									}
																		
									opcionmodif=Dentre.entero("\nINGRESE FILTRO A MODIFICAR: ");
									
									index=0;
									for(Autoparte autoparte : autopartesG)
									{
										if(autoparte instanceof Filtro)
										{					
											filtro=(Filtro)autoparte;
											if(opcionmodif==filtro.getFiltro_ID())
											{
												break;
											}
										}
										index++;
									}									
									
									
									//PODRIA PREGUNTAR SI DESEA MODIFICAR...
									if(Dentre.texto("\nMODIFICAR MATERIAL (S-N): "+filtro.getMaterial()).toString().getBytes()[0]=='N')
									{
										filtro.setMaterial(Dentre.texto("\nMODIFIQUE TIPO MATERIAL: "));
									}
									//....
									filtro.setTamaño(Dentre.texto("\nMODIFIQUE TAMAÑO: "));
									filtro.setMarca(Dentre.texto("\nMODIFIQUE MARCA: "));
									filtro.setModelo(Dentre.texto("\nMODIFIQUE MODELO: "));
									//filtro.setTipoAutoparte("filtro");
									filtro.setCantDisponible(Dentre.entero("\nMODIFIQUE CANTIDAD: "));
									filtro.setCosto(Dentre.doble("\nMODIFIQUE COSTO: "));
									//filtro.setAutoparteID(filtro.getAutoparteID());
											
									sqlmodif.updateFiltro(filtro);
									sqlmodif.updateAutoparte(filtro);
									
									autopartesG.remove(index);
									autopartesG.add(filtro);
										
									break;									
								case Definiciones.ACEITE_INDICE:
									
									System.out.print("\n**ACEITES**");
									
									for (Autoparte autoparte : autopartesG)
									{										
										if(autoparte instanceof Aceite)
										{												
											aceite=(Aceite)autoparte;
											System.out.print("\nID: "+aceite.getAceite_ID()+" - Marca: "+aceite.getMarca()+" - Modelo:"+aceite.getModelo());
										}
									}
																		
									opcionmodif=Dentre.entero("\nINGRESE FILTRO A MODIFICAR: ");
									
									index=0;
									for(Autoparte autoparte : autopartesG)
									{
										if(autoparte instanceof Aceite)
										{					
											aceite=(Aceite)autoparte;
											if(opcionmodif==aceite.getAceite_ID())
											{
												break;
											}
										}
										index++;
									}							
									
									//PODRIA PREGUNTAR SI DESEA MODIFICAR...
									aceite.setCantidadlitros(Dentre.entero("\nACTUAL("+aceite.getCantidadlitros()+") - MODIFIQUE CANTIDAD LITROS: "));
									aceite.setTipoAceite(Dentre.texto("\nACTUAL("+aceite.getTipoAceite()+") - MODIFIQUE TIPO ACEITE: "));
									aceite.setMarca(Dentre.texto("\nACTUAL("+aceite.getMarca()+") - MODIFIQUE MARCA: "));
									aceite.setModelo(Dentre.texto("\nACTUAL("+aceite.getModelo()+") - MODIFIQUE MODELO: "));
									aceite.setCantDisponible(Dentre.entero("\nACTUAL("+aceite.getCantDisponible()+") - MODIFIQUE CANTIDAD: "));
									aceite.setCosto(Dentre.doble("\nACTUAL("+aceite.getCosto()+") - MODIFIQUE COSTO: "));
									
									sqlmodif.updateAceite(aceite);
									sqlmodif.updateAutoparte(aceite);
									autopartesG.remove(index);
									autopartesG.add(aceite);
									
									break;
									
								case Definiciones.LAMPARA_INDICE:
									
									System.out.print("\n**LAMPARAS**");
									
									for (Autoparte autoparte : autopartesG)
									{										
										if(autoparte instanceof Lampara)
										{												
											lampara=(Lampara)autoparte;
											System.out.print("\nID: "+lampara.getLampara_ID()+" - Marca: "+lampara.getMarca()+" - Modelo:"+lampara.getModelo());
										}
									}
																		
									opcionmodif=Dentre.entero("\nINGRESE FILTRO A MODIFICAR: ");
									
									index=0;
									for(Autoparte autoparte : autopartesG)
									{
										if(autoparte instanceof Lampara)
										{					
											lampara=(Lampara)autoparte;
											if(opcionmodif==lampara.getLampara_ID())
											{
												lampara.setColor(Dentre.texto("\nACTUAL("+lampara.getColor()+") - MODIFIQUE COLOR: "));
												lampara.setTamaño(Dentre.texto("\nACTUAL("+lampara.getTamaño()+") - MODIFIQUE TAMAÑO: "));
												lampara.setMarca(Dentre.texto("\nACTUAL("+lampara.getMarca()+") - MODIFIQUE MARCA: "));
												lampara.setModelo(Dentre.texto("\nACTUAL("+lampara.getModelo()+") - MODIFIQUE MODELO: "));									
												lampara.setCantDisponible(Dentre.entero("\nACTUAL("+lampara.getCantDisponible()+") - MODIFIQUE CANTIDAD: "));
												lampara.setCosto(Dentre.doble("\nACTUAL("+lampara.getCosto()+") - MODIFIQUE COSTO: "));
												break;
											}
										}
										index++;
									}	
									
									sqlmodif.updateLampara(lampara);
									sqlmodif.updateAutoparte(lampara);
									
									autopartesG.remove(index);
									autopartesG.add(lampara);
									break;								
								default:
									break;
							}
							
						}
						catch(MiException e)
						{
							throw e;
						}catch(SQLException e)
						{
							throw new MiException("[MODIFICAR AUTOPARTE] SQLException : "+e);
						}catch(Exception e)
						{
							throw new MiException("[MODIFICAR AUTOPARTE] Exception : "+e);
						}
						break;
					case 6:			//BAJA AUTOPARTE
							try
							{	
								int opcionmodif=0;
								Filtro filtro = new Filtro();
								Aceite aceite=new Aceite();
								Lampara lampara=new Lampara();
								int index=0;
								//PODRIA SER MAS DINAMICO							
								System.out.print("\n1-FILTROS");	
								System.out.print("\n2-ACEITE");
								System.out.print("\n3-LAMPARA");							
								switch(Dentre.entero("\nINGRESE TIPO AUTOPARTE A DAR DE BAJA: "))
								{
									case Definiciones.FILTRO_INDICE:
										
										System.out.print("\n**FILTROS**");
																		
										
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Filtro)
											{												
												filtro=(Filtro)autoparte;
												System.out.print("\nID: "+filtro.getFiltro_ID()+" - Marca: "+filtro.getMarca()+" - Modelo:"+filtro.getModelo());
											}
										}
																			
										opcionmodif=Dentre.entero("\nINGRESE FILTRO A DAR DE BAJA: ");
										index=0;
										for(Autoparte autoparte : autopartesG)
										{
											if(autoparte instanceof Filtro)
											{					
												filtro=(Filtro)autoparte;
												if(opcionmodif==filtro.getFiltro_ID())
												{
													if(sqldelete.eliminarAutoparte(autoparte))
													{
														System.out.print("\nAUTOPARTE ELIMINADA CORRECTAMENTE");
														Thread.sleep(2000);
													}else{
														System.out.print("\nFALLO ELIMINACION AUTOPARTE");
														Thread.sleep(2000);
													}
													if(sqldelete.eliminarFiltro(filtro))
													{
														System.out.print("\nFILTRO ELIMINADO CORRECTAMENTE");
														Thread.sleep(2000);
													}else{
														System.out.print("\nFALLO ELIMINACION FILTRO");
														Thread.sleep(2000);
													}
													autopartesG.remove(index);
													break;
												}
											}
											index++;
										}										
										
									break;
									case Definiciones.ACEITE_INDICE:
										
										System.out.print("\n**ACEITES**");
										
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Aceite)
											{												
												aceite=(Aceite)autoparte;
												System.out.print("\nID: "+aceite.getAceite_ID()+" - Marca: "+aceite.getMarca()+" - Modelo:"+aceite.getModelo());
											}
										}
																			
										opcionmodif=Dentre.entero("\nINGRESE ACEITE A DAR DE BAJA: ");
										index=0;
										for(Autoparte autoparte : autopartesG)
										{
											if(autoparte instanceof Aceite)
											{					
												aceite=(Aceite)autoparte;
												if(opcionmodif==aceite.getAceite_ID())
												{
													if(sqldelete.eliminarAutoparte(autoparte))
													{
														System.out.print("\nAUTOPARTE ELIMINADA CORRECTAMENTE");
														Thread.sleep(2000);
													}else{
														System.out.print("\nFALLO ELIMINACION AUTOPARTE");
														Thread.sleep(2000);
													}
													if(sqldelete.eliminarAceite(aceite))
													{
														System.out.print("\nACEITE ELIMINADO CORRECTAMENTE");
														Thread.sleep(2000);
													}else{
														System.out.print("\nFALLO ELIMINACION ACEITE");
														Thread.sleep(2000);
													}
													autopartesG.remove(index);
													break;
												}
											}
											index++;
										}		
										break;
										
									case Definiciones.LAMPARA_INDICE:
										
										System.out.print("\n**LAMPARAS**");
										
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Lampara)
											{												
												lampara=(Lampara)autoparte;
												System.out.print("\nID: "+lampara.getLampara_ID()+" - Marca: "+lampara.getMarca()+" - Modelo:"+lampara.getModelo());
											}
										}
																			
										opcionmodif=Dentre.entero("\nINGRESE ACEITE A DAR DE BAJA: ");
										index=0;
										for(Autoparte autoparte : autopartesG)
										{
											if(autoparte instanceof Lampara)
											{					
												lampara=(Lampara)autoparte;
												if(opcionmodif==lampara.getLampara_ID())
												{
													if(sqldelete.eliminarAutoparte(autoparte))
													{
														System.out.print("\nAUTOPARTE ELIMINADA CORRECTAMENTE");
														Thread.sleep(2000);
													}else{
														System.out.print("\nFALLO ELIMINACION AUTOPARTE");
														Thread.sleep(2000);
													}
													if(sqldelete.eliminarLampara(lampara))
													{
														System.out.print("\nLAMPARA ELIMINADO CORRECTAMENTE");
														Thread.sleep(2000);
													}else{
														System.out.print("\nFALLO ELIMINACION LAMPARA");
														Thread.sleep(2000);
													}
													autopartesG.remove(index);
													break;
												}
											}
											index++;
										}	
										break;
									default:
										break;
								}
								
							}
							catch(MiException e)
							{
								throw e;
							}catch(SQLException e)
							{
								throw new MiException("[BAJA AUTOPARTE] SQLException : "+e);
							}catch(Exception e)
							{
								throw new MiException("[BAJA AUTOPARTE] Exception : "+e);
							}
						break;
					case 7:										//CARGAR REPARACION
							try
							{		
								Cliente clientereparacion=sqlselects.buscarClientePorApodo(Dentre.texto("\nINGRESE NOMBRE CLIENTE: "));
								
								if(clientereparacion==null)
								{
									System.out.print("\nCLIENTE NO REGISTRADO, REGISTRE CLIENTE");
									Thread.sleep(2000);
									break;
								}
								Reparacion nuevareparacion=new Reparacion();
								
							    Calendar c = new GregorianCalendar();
							    Date d1 = c.getTime(); 
								nuevareparacion.setFechainicio(d1.toString());
								nuevareparacion.setCliente(clientereparacion);
								nuevareparacion.setEntregado(0);								
								nuevareparacion.setId(sqlselects.buscarUltimaReparacionId());
								
								reparacionesG.add(nuevareparacion);
								
							}catch(MiException e)
							{
								throw e;
							}
							catch(SQLException e)
							{
								throw new MiException("[CARGA REPARACION] SQLException : "+e);
							}catch(Exception e)
							{
								throw new MiException("[CARGA REPARACION] Exception : "+e);
							}
						break;
					case 8:
						
						break;
					case 9:
						
						break;
					case 10:
							try
							{		
								System.out.print("\n1-MOSTRAR CLIENTES");
								System.out.print("\n2-MOSTRAR REPARACIONES");
								System.out.print("\n3-MOSTRAR AUTOPARTES");
								switch(Dentre.entero("\nINGRESE TIPO AUTOPARTE A DAR DE BAJA: "))
								{
									case 1:									
										System.out.print("\n***CLIENTES***");
										for (Cliente cliente : clientesG)
										{	
											System.out.print("\nID: "+cliente.getId()+" - Nombre: "+cliente.getNombre()+" - Mail: "+cliente.getMail()+" - Auto: "+cliente.getAuto());											
										}
										Thread.sleep(2000);
									break;
									
									case 2:
										System.out.print("\n***REPARACIONES***");
										System.out.print("\n********FINALIZADOS********");
										for(Reparacion reparacion : reparacionesG)
										{				
											if(reparacion.getEntregado()==1)
											{												
												System.out.print("\nID: "+reparacion.getId()+" - Cliente: "+reparacion.getCliente()+" - Fecha I: "+reparacion.getFechainicio()+" - Fecha F: "+reparacion.getFechaentrega()+" - Costo: "+reparacion.getCosto());
												System.out.print("\n    AUTOPARTES: ");
												for(Autoparte autoparte: reparacion.getAutopartes()){
													System.out.print("\n      Tipo: "+autoparte.getTipoAutoparte()+" - ID: "+autoparte.getId()+" - Marca: "+autoparte.getMarca()+" - Modelo: "+autoparte.getModelo());
												}
														
											}
										}
										
										System.out.print("\n********EN REPARACION********");
										for(Reparacion reparacion : reparacionesG)
										{				
											if(reparacion.getEntregado()==0)
											{
												System.out.print("\nID: "+reparacion.getId()+" - Cliente: "+reparacion.getCliente()+" - Fecha I: "+reparacion.getFechainicio());
											}
										}
									break;
									case 3:
									break;
									case 4:
									break;
									default:
										break;
									
								}							
							}
							catch(MiException e)
							{
								throw e;
							}
							catch(Exception e)
							{
								throw new MiException("[CARGA REPARACION] Exception : "+e);
							}
						break;
					case 94:
						
						break;
					case 98:
							return "mudar";
					case 99:
													
							return "salir";
						
					default:
						break;
				}
							
			}
			return "OK";
		}
		
	}
	//#######################################################################################
	public class MenuInicio
	{
		public MenuInicio()
		{
			metgral=new MetodosGrl();
		}
		public String empezarMenuTecnico(List<Usuario> usuarios) throws MiException
		{
			boolean salir=false;			
			while(!salir) //GENERAL
			{
				
								
				System.out.print("\n\n****MENU TECNICO - BIENVENIDO TALLER 2013****");
				System.out.print("\n1-CREAR USUARIO");
				System.out.print("\n2-MODIFICAR USUARIO");
				System.out.print("\n3-BAJA USUARIO");				
				System.out.print("\n4-MOSTRAR USUARIOS");
				System.out.print("\n99-SALIR");
				switch(Dentre.entero("\n\n INGRESE OPCIÓN: "))
				{
					case 1:
						try
						{
							Usuario userCreate=new Usuario();
							
							userCreate.setName(Dentre.texto("\n INGRESE NOMBRE: "));
							userCreate.setEmail(Dentre.texto("\n INGRESE MAIL: "));							
							userCreate.setUsername(Dentre.texto("\n INGRESE APODO: "));							
							userCreate.setPassword(Dentre.texto("\n INGRESE PASSWORD: "));							
							userCreate.setJerarquia(Dentre.texto("\n INGRESE JERARQUIA: "));
							userCreate.setId(sqlselects.buscarUltimoUsuarioId());
							if(sqlinserts.insertarUsuario(userCreate))
							{
								System.out.print("\nUSUARIO MODIFICADO CORRECTAMENTE");
								Thread.sleep(2000);
							}else
							{
								System.out.print("\nFALLO MODIFICACION USUARIO");
								Thread.sleep(2000);
							}
						}
						catch(MiException e)
						{							
							throw e;
						}
						catch(Exception e)
						{							
							throw new MiException("[empezarMenuTecnico]CREATE USER EXCEPTION: "+e);
						}
						break;					
					case 2:
							
						try
						{
							Usuario usuarioModif=sqlselects.buscarUsuarioPorApodo(Dentre.texto("\n INGRESE APODO USUARIO A MODIFICAR: "));
							if(usuarioModif==null)
							{
								System.out.print("\nNO SE ENCONTRO EL USUARIO");
								Thread.sleep(2000);
							}else
							{
								Usuario userAux=new Usuario();
								//PODRIA MEJORARSE PREGUNTANDO SI DESEA MODIFICAR
								//TODO: AGREGAR JERARQUIAS DE USUARIO
								userAux=usuarioModif;
								userAux.setName(Dentre.texto("\n INGRESE NOMBRE A MODIFICAR: "));								
								userAux.setEmail(Dentre.texto("\n INGRESE MAIL A MODIFICAR: "));								
								userAux.setUsername(Dentre.texto("\n INGRESE APODO A MODIFICAR: "));								
								userAux.setPassword(Dentre.texto("\n INGRESE PASSWORD USUARIO: "));
								userAux.setJerarquia(Dentre.texto("\n INGRESE JERARQUIA USUARIO: "));						
								userAux.setLogueado(0);
								
								if(sqlmodif.updateUsuario(userAux))
								{
									System.out.print("\nUSUARIO MODIFICADO CORRECTAMENTE");
									Thread.sleep(2000);
								}else
								{
									System.out.print("\nFALLO MODIFICACION USUARIO");
									Thread.sleep(2000);
								}
							}
						
						}catch(MiException e)
						{
							throw e;							
						}catch(Exception e)
						{
							throw new MiException("[empezarMenuTecnico]MODIFICACION USER EXCEPTION: "+e);							
						}
						break;
					case 3:
						try
						{
							Usuario usuarioBaja=sqlselects.buscarUsuarioPorApodo(Dentre.texto("\n INGRESE APODO USUARIO A DAR DE BAJA: "));
							if(sqldelete.eliminarUsuario(usuarioBaja))
							{
								System.out.print("\nUSUARIO ELIMINADO CORRECTAMENTE");
								Thread.sleep(2000);
							}else
							{
								System.out.print("\nFALLO BAJA USUARIO");
								Thread.sleep(2000);
							}	
						}
						catch(MiException e)
						{							
							throw e;
						}
						catch(Exception e)
						{							
							throw new MiException("[empezarMenuTecnico]DELETE USER EXCEPTION: "+e);
						}	
													
					break;
					case 4:
						try
						{
							System.out.print("\n***USUARIOS***");
							Thread.sleep(2000);
							for(Usuario usuario : usuarios)
							{
								System.out.print("\nID: "+usuario.getId()+" - Nombre: "+usuario.getName()+" - Apodo: "+usuario.getUsername()+" - Pass: "+usuario.getPassword()+" - Jerarquia: "+usuario.getJerarquia() + " - Email: "+usuario.getEmail());
							}
							Thread.sleep(2000);
						}
						catch(MiException e)
						{							
							throw e;
						}
						catch(Exception e)
						{							
							throw new MiException("[empezarMenuTecnico]DELETE USER EXCEPTION: "+e);
						}	
						break;
					case 99:
						return "salir";						
						
					default:
						break;
				}
				
			}
			return null;
		}
		public Usuario empezarLogginUsuario(List<Usuario> usuarios) throws MiException
		{
			boolean salir=false;
			String user;
			String pass;
			
			Usuario usuarioLoggin=new Usuario();
			while(!salir) //GENERAL
			{				
								
				System.out.print("\n\n****MODO SISTEMA - BIENVENIDO TALLER 2013****");				
				System.out.print("\n1-LOGUEARSE AL SISTEMA");
				System.out.print("\n99-SALIR");
				switch(Dentre.entero("\n\n INGRESE OPCIÓN: "))
				{					
					case 1:			
						try
						{
							user=Dentre.texto("\n INGRESE SU USUARIO: ");
							pass=Dentre.texto("\nINGRESE SU CONTRASEÑA: ");		
													
							//if((usuario=metgral.loginUser(user,pass))==null)
							for(Usuario usuario : usuarios)
							{
								System.out.print("\nUSER: "+usuario.getUsername()+" - PASS: "+usuario.getPassword());
								if(usuario.getUsername()==user.toString()&&usuario.getPassword()==pass)
								{
									usuarioLoggin=usuario;
									System.out.print("\nUSUARIO LOGUEADO");
									Thread.sleep(2000);
									return usuarioLoggin;
								}else
								{	
								}
							}							
							System.out.print("\nUSUARIO O CONTRASEÑA INCORRECTA, VUELVA A INGRESAR");
							Thread.sleep(2000);
						
							
						}catch(Exception e)
						{							
							throw new MiException("[empezarMenuInicio]MENU INICIO USER EXCEPTION: "+e);
						}
						
					break;
					case 99:
						return null;						
						
					default:
						break;
				}
				
			}
			return null;
		}
		public int menuTipoSistema() {
			try
			{
				System.out.print("\n\n1-MODO TECNICO");
				System.out.print("\n2-MODO SISTEMA");
				System.out.print("\n99-SALIR SISTEMA");
				switch(Dentre.entero("\n INGRESE MODO DE USO DEL SISTEMA: "))
				{
					case Definiciones.MODO_TECNICO:
						return Definiciones.MODO_TECNICO;
						
					case Definiciones.MODO_SISTEMA:
						return Definiciones.MODO_SISTEMA;
					case Definiciones.MODO_SALIR:
						return Definiciones.MODO_SALIR;
						
						default:
							return -1;
				}				
				
			}catch(Exception e)
			{							
				throw new MiException("[menuTipoSistema]MENU TIPO SISTEMA EXCEPTION: "+e);
			}
		}
		public boolean validarSenha() {
			try
			{
				System.out.print("\n**VALIDACIÓN TÉCNICA**");				
				if(Dentre.entero("\nINGRESE SU CONTRASEÑA TÉCNICA: ")==Definiciones.PASS_TECNICO)
				{
					System.out.print("\nCONTRASEÑA CORRECTA");
					return true; 
				}else
				{
					System.out.print("\nCONTRASEÑA INCORRECTA");		//DEBUG
					return false;
				}
				
			}catch(Exception e)
			{							
				throw new MiException("[validarSenha]VALIDACION MENU TECNICO EXCEPTION: "+e);
			}			
		}
	}
}
