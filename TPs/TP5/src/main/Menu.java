package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

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

public class Menu 
{
	
	MenuPrincipal menuprinc;
	MetodosGrl metgral;
	MenuAdministrador menuAdministrador;
	MenuTecnico menuTecnico;
	MenuInicio menuInicio;
	
	SQLInserts sqlinserts=new SQLInserts();
	SQLSelects sqlselects=new SQLSelects();
	SQLDelete sqldelete=new SQLDelete();
	SQLCarga sqlcarga =new SQLCarga();
	SQLModif sqlmodif=new SQLModif();
	public Menu()
	{
		menuprinc=new MenuPrincipal();
		menuTecnico=new MenuTecnico();
		//menuInicio=new MenuInicio();
		
	}
	public MenuPrincipal getMenuPrincipal()
	{
		return this.menuprinc;
	}
	//////////////
	public MenuTecnico getMenuTecnico() {
		return menuTecnico;
	}
	public void setMenuTecnico(MenuTecnico menuTecnico) {
		this.menuTecnico = menuTecnico;
	}
	
	public MenuAdministrador getMenuAdministrador() {
		return menuAdministrador;
	}
	public void setMenuAdministrador(MenuAdministrador menuAdministrador) {
		this.menuAdministrador = menuAdministrador;
	}
	
	public MenuInicio getMenuInicio()
	{
		return this.menuInicio;
	}
	public void setMenuInicio(MenuInicio menuInicio)
	{
		this.menuInicio=menuInicio;
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
			
			try
			{	
				System.out.print("\n\n***CARGA DE ENTIDADES***\n");
				clientesG=sqlcarga.cargaClientes();
				reparacionesG=sqlcarga.cargaReparaciones();
				autopartesG=sqlcarga.cargaAutopartes();
			}
			catch(MiException e)
			{
				throw  e;
			}
			catch(SQLException e)
			{
				throw new MiException("[MENU] SQL Error al cargar: "+e);
			}catch(Exception e)
			{
				throw new MiException("[MENU] Error al cargar: "+e);
			}
			
			sigo=false;
			while(!sigo)
			{
								
				System.out.print("\n\n\n***MENU PRINCIPAL***");
				System.out.print("\n1-CARGAR CLIENTE");
				System.out.print("\n2-MODIFICACION CLIENTE");
				System.out.print("\n3-BAJA CLIENTE");
				System.out.print("\n4-CARGAR AUTOPARTE");
				System.out.print("\n5-MODIFICACION AUTOPARTE");
				System.out.print("\n6-BAJA AUTOPARTE");
				System.out.print("\n7-CARGAR REPARACION");
				System.out.print("\n8-FINALIZAR REPARACION");
				//System.out.print("\n9-MODIFICAR REPARACION");			//POR AHORA NO MODIFICO REPARACIONES				
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
							cliente.setId(sqlselects.buscarUltimoClienteId(cliente.getId()));
							if(sqlinserts.insertarCliente(cliente))
							{
								System.out.print("\nCLIENTE INGRESADO CORRECTAMENTE");
								Thread.sleep(2000);
								clientesG.add(cliente);
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
								//Cliente cliente=sqlselects.buscarClientePorApodo(Dentre.texto("\n INGRESE APODO CLIENTE A MODIFICAR: "));
								Cliente cliente=metgral.buscarClientePorApodo(Dentre.texto("\n INGRESE NOMBRE CLIENTE A MODIFICAR: "),clientesG);
								if(cliente==null)
								{
									System.out.print("\nCLIENTE NO ENCONTRADO");
									Thread.sleep(2000);
									break;
								}
								Cliente clienteAux=cliente;
								//PODRIA VALIDAR Y PREGUNTAR SI QUIERE DEJAR IGUAL O CAMBIAR
								//EJ								
								if(Dentre.texto("\nMODIFICAR NOMBRE CLIENTE? (yes - not): "+cliente.getNombre()).equals("yes"))
								{
									clienteAux.setNombre(Dentre.texto("\nMODIFIQUE NOMBRE: "));	
								}
								//...
								clienteAux.setMail(Dentre.texto("\nMODIFIQUE MAIL: "));
								clienteAux.setAuto(Dentre.texto("\nMODIFIQUE AUTO: "));
								
								if(sqlmodif.updateCliente(clienteAux))
								{
									System.out.print("\nCLIENTE MODIFICADO CORRECTAMENTE");
									Thread.sleep(2000);
									clientesG=metgral.eliminarClienteDeLista(clientesG,cliente);									
									clientesG.add(clienteAux);
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
							//Cliente cliente=sqlselects.buscarClientePorApodo(Dentre.texto("\n INGRESE APODO CLIENTE A DAR DE BAJA: "));
							Cliente cliente=metgral.buscarClientePorApodo(Dentre.texto("\n INGRESE NOMBRE CLIENTE A DAR DE BAJA: "),clientesG);
							if(cliente==null)
							{
								System.out.print("\nCLIENTE NO ENCONTRADO");
								Thread.sleep(2000);
								break;
							}
							if(sqldelete.eliminarCliente(cliente))
							{
								System.out.print("\nCLIENTE ELIMINADO CORRECTAMENTE");
								Thread.sleep(2000);
								clientesG=metgral.eliminarClienteDeLista(clientesG,cliente);								
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
									
									filtro.setId(sqlselects.buscarUltimaAutoparteId());									
									filtro.setFiltro_ID(sqlselects.buscarUltimoFiltroId());
									filtro.setMaterial(Dentre.texto("\nINGRESE TIPO MATERIAL: "));
									filtro.setTamaño(Dentre.texto("\nINGRESE TAMAÑO: "));
									filtro.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									filtro.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									filtro.setTipoAutoparte("filtro");
									filtro.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									filtro.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									
									sqlinserts.insertarFiltro(filtro);
									//sqlinserts.insertarAutoparte(filtro.getAutoparteID(),filtro.getTipoAutoparte(),filtro.getMarca(),filtro.getModelo(),filtro.getCosto(),filtro.getCantDisponible());
									sqlinserts.insertarAutoparte(filtro);
									autopartesG.add(filtro);
									break;
									
								case Definiciones.ACEITE_INDICE:
									Aceite aceite =new Aceite();
									
									aceite.setId(sqlselects.buscarUltimaAutoparteId());
									
									aceite.setAceite_ID(sqlselects.buscarUltimoAceiteId());
									aceite.setCantidadlitros(Dentre.entero("\nINGRESE CANTIDAD LITROS: "));
									aceite.setTipoAceite(Dentre.texto("\nINGRESE TAMAÑO: "));
									aceite.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									aceite.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									aceite.setTipoAutoparte("aceite");
									aceite.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									aceite.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									
									sqlinserts.insertarAceite(aceite);
									//sqlinserts.insertarAutoparte(aceite.getAutoparteID(),aceite.getTipoAutoparte(),aceite.getMarca(),aceite.getModelo(),aceite.getCosto(),aceite.getCantDisponible());
									sqlinserts.insertarAutoparte(aceite);
									autopartesG.add(aceite);
									break;
									
								case Definiciones.LAMPARA_INDICE:
									Lampara lampara =new Lampara();
									
									lampara.setId(sqlselects.buscarUltimaAutoparteId());
									
									lampara.setLampara_ID(sqlselects.buscarUltimoLamparaId());
									lampara.setColor(Dentre.texto("\nINGRESE COLOR: "));
									lampara.setTamaño(Dentre.texto("\nINGRESE TAMAÑO: "));
									lampara.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									lampara.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									lampara.setTipoAutoparte("lampara");
									lampara.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									lampara.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									
									sqlinserts.insertarLampara(lampara);
									//sqlinserts.insertarAutoparte(lampara.getAutoparteID(),lampara.getTipoAutoparte(),lampara.getMarca(),lampara.getModelo(),lampara.getCosto(),lampara.getCantDisponible());
									sqlinserts.insertarAutoparte(lampara);
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
											//System.out.print("\nID: "+filtro.getFiltro_ID()+" - Marca: "+filtro.getMarca()+" - Modelo:"+filtro.getModelo());
											System.out.print("\n"+filtro.toString());
										}
									}
																		
									opcionmodif=Dentre.entero("\nINGRESE ID FILTRO A MODIFICAR: ");
									
									index=0;
									for(Autoparte autoparte : autopartesG)
									{
										if(autoparte instanceof Filtro)
										{					
											filtro=(Filtro)autoparte;
											if(opcionmodif==filtro.getFiltro_ID())
											{
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
												break;
											}
										}
										index++;
									}									
																				
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
											//System.out.print("\nID: "+aceite.getAceite_ID()+" - Marca: "+aceite.getMarca()+" - Modelo:"+aceite.getModelo());
											System.out.print("\n"+aceite.toString());
										}
									}
																		
									opcionmodif=Dentre.entero("\nINGRESE ID ACEITE A MODIFICAR: ");
									
									index=0;
									for(Autoparte autoparte : autopartesG)
									{
										if(autoparte instanceof Aceite)
										{					
											aceite=(Aceite)autoparte;
											if(opcionmodif==aceite.getAceite_ID())
											{
												//PODRIA PREGUNTAR SI DESEA MODIFICAR...
												aceite.setCantidadlitros(Dentre.entero("\nACTUAL("+aceite.getCantidadlitros()+") - MODIFIQUE CANTIDAD LITROS: "));
												aceite.setTipoAceite(Dentre.texto("\nACTUAL("+aceite.getTipoAceite()+") - MODIFIQUE TIPO ACEITE: "));
												aceite.setMarca(Dentre.texto("\nACTUAL("+aceite.getMarca()+") - MODIFIQUE MARCA: "));
												aceite.setModelo(Dentre.texto("\nACTUAL("+aceite.getModelo()+") - MODIFIQUE MODELO: "));
												aceite.setCantDisponible(Dentre.entero("\nACTUAL("+aceite.getCantDisponible()+") - MODIFIQUE CANTIDAD: "));
												aceite.setCosto(Dentre.doble("\nACTUAL("+aceite.getCosto()+") - MODIFIQUE COSTO: "));
												
												break;
											}
										}
										index++;
									}				
									
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
											//System.out.print("\nID: "+lampara.getLampara_ID()+" - Marca: "+lampara.getMarca()+" - Modelo:"+lampara.getModelo());
											System.out.print("\n"+lampara.toString());
										}
									}
																		
									opcionmodif=Dentre.entero("\nINGRESE ID LAMPARA A MODIFICAR: ");
									
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
								boolean hayAutoparte=false;
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
												//System.out.print("\nID: "+filtro.getFiltro_ID()+" - Marca: "+filtro.getMarca()+" - Modelo:"+filtro.getModelo());
												System.out.print("\n"+filtro.toString());
											}
										}
																			
										opcionmodif=Dentre.entero("\n\nINGRESE ID FILTRO A DAR DE BAJA: ");
										index=0;
										for(Autoparte autoparte : autopartesG)
										{
											if(autoparte instanceof Filtro)
											{					
												filtro=(Filtro)autoparte;
												if(opcionmodif==filtro.getFiltro_ID())
												{
													if(sqldelete.eliminarAutoparte(filtro))
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
													hayAutoparte=true;
													break;
												}
											}
											index++;
										}										
										if(!hayAutoparte)
											System.out.print("\nNO HAY FILTRO CON ESE ID");
										
									break;
									case Definiciones.ACEITE_INDICE:
										
										System.out.print("\n**ACEITES**");
										
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Aceite)
											{												
												aceite=(Aceite)autoparte;
												//System.out.print("\nID: "+aceite.getAceite_ID()+" - Marca: "+aceite.getMarca()+" - Modelo:"+aceite.getModelo());
												System.out.print("\n"+aceite.toString());
											}
										}
																			
										opcionmodif=Dentre.entero("\n\nINGRESE ID ACEITE A DAR DE BAJA: ");
										index=0;
										for(Autoparte autoparte : autopartesG)
										{
											if(autoparte instanceof Aceite)
											{					
												aceite=(Aceite)autoparte;
												if(opcionmodif==aceite.getAceite_ID())
												{
													if(sqldelete.eliminarAutoparte(aceite))
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
													hayAutoparte=true;
													break;													
												}
											}
											index++;
										}		
										if(!hayAutoparte)
											System.out.print("\nNO HAY ACEITE CON ESE ID");
										
										break;
										
									case Definiciones.LAMPARA_INDICE:
										
										System.out.print("\n**LAMPARAS**");
										
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Lampara)
											{												
												lampara=(Lampara)autoparte;
												//System.out.print("\nID: "+lampara.getLampara_ID()+" - Marca: "+lampara.getMarca()+" - Modelo:"+lampara.getModelo());
												System.out.print("\n"+lampara.toString());
											}
										}
																			
										opcionmodif=Dentre.entero("\n\nINGRESE ID LAMPARA A DAR DE BAJA: ");
										index=0;
										for(Autoparte autoparte : autopartesG)
										{
											if(autoparte instanceof Lampara)
											{					
												lampara=(Lampara)autoparte;
												if(opcionmodif==lampara.getLampara_ID())
												{
													if(sqldelete.eliminarAutoparte(lampara))
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
													hayAutoparte=true;
													break;
												}
											}
											index++;
										}	
										if(!hayAutoparte)
											System.out.print("\nNO HAY LAMPARA CON ESE ID");
										
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
								//Cliente clientereparacion=sqlselects.buscarClientePorApodo(Dentre.texto("\nINGRESE NOMBRE CLIENTE: "));
								Cliente clientereparacion=metgral.buscarClientePorApodo(Dentre.texto("\nINGRESE NOMBRE CLIENTE: "),clientesG);
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
								
								if(sqlinserts.insertarReparacionInicio(nuevareparacion))
								{
									reparacionesG.add(nuevareparacion);
									
									System.out.print("\nCARGA DE NUEVA REPARACION CORRECTA\n");
									Thread.sleep(2000);
								}else{
									System.out.print("\nFALLO CARGA DE NUEVA REPARACION\n");
									Thread.sleep(2000);
								}
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
						try
						{
							double totalCosto=0;
							Cliente cliente= metgral.buscarClientePorApodo(Dentre.texto("\nINGRESE NOMBRE CLIENTE: "), clientesG);
							int index=0;
							if(cliente==null)
							{
								System.out.print("\nCLIENTE NO ENCONTRADO\n");
								Thread.sleep(2000);
								break;
							}
							
							for(Reparacion reparacion:reparacionesG)
							{
								if(reparacion.getCliente().getId()==cliente.getId())
								{		
									if(reparacion.getEntregado()==1)
									{
										System.out.print("\nCLIENTE NO POSEE NINGUNA REPARACION ABIERTA\n");
										break;
									}
									else
									{
										Calendar c = new GregorianCalendar();
									    Date d1 = c.getTime(); 
										reparacion.setFechaentrega(d1.toString());
										reparacion.setEntregado(1);
										
										Filtro filtro=new Filtro();
										Aceite aceite=new Aceite();
										Lampara lampara=new Lampara();
										
										boolean salir=false;
										while(!salir)
										{
										
											for (Autoparte autoparte : autopartesG)
											{										
												if(autoparte instanceof Filtro)
												{	
													filtro=(Filtro)autoparte;
													System.out.print("\n"+filtro.toString());
												}
												else if(autoparte instanceof Aceite)
												{	
													aceite=(Aceite)autoparte;
													System.out.print("\n"+aceite.toString());
												}
												else if(autoparte instanceof Lampara)
												{	
													lampara=(Lampara)autoparte;
													System.out.print("\n"+lampara.toString());
												}
											}										
											int opcionmodif=Dentre.entero("\n\n**INGRESE ID AUTOPARTE PARA AGREGAR ('99' para salir): ");
											if(opcionmodif==99)
											{
												salir=true;
											}else
											{
											
												List<Autoparte> autopartes=new ArrayList<Autoparte>();
												for (Autoparte autoparte : autopartesG)
												{										
													if(autoparte.getId()==opcionmodif)
													{
														autopartes.add(autoparte);												
														//reparacion.getAutopartes().add(autoparte);	//Null pointer exception
														reparacion.setCosto(reparacion.getCosto()+autoparte.getCosto());
													}
												
												}	
												reparacion.setAutopartes(autopartes);
											}
										}
									//Podria validar si se cargo autopartes o no se cargo nada
									sqlinserts.insertarupdateReparacionFinal(reparacion,sqlselects.buscarUltimaReparacionAutoparteId());
									reparacionesG.remove(index);
									reparacionesG.add(reparacion);	
									System.out.print("\n\nSE AGREGO AUTOPARTE\n");
									Thread.sleep(2000);
									}
									index++;
								}
							}
						}
						catch(MiException e)
						{
							throw e;
						}catch(SQLException e)
						{
							throw new MiException("[Exception al finalizar reparación] SQL Exception: "+e);
						}
						catch(Exception e)
						{
							throw new MiException("[Exception al finalizar reparación] Exception: "+e);
						}
						
						
						break;
					case 9:
						
						break;
					case 10:
							try
							{		
								System.out.print("\n1-MOSTRAR CLIENTES");
								System.out.print("\n2-MOSTRAR REPARACIONES");
								System.out.print("\n3-MOSTRAR AUTOPARTES");
								switch(Dentre.entero("\nINGRESE OPCION: "))
								{
									case 1:									
										System.out.print("\n***CLIENTES***");
										for (Cliente cliente : clientesG)
										{	
											System.out.print("\n"+cliente.toString());											
										}
										Thread.sleep(2000);
									break;
									
									case 2:
										try
										{
											System.out.print("\n***REPARACIONES***");
											System.out.print("\n********FINALIZADOS********");
											for(Reparacion reparacion : reparacionesG)
											{				
												if(reparacion.getEntregado()==1)
												{												
													System.out.print("\n"+reparacion.toString());
													System.out.print("\n    ºAUTOPARTESº: \n");
													for(Autoparte autoparte: reparacion.getAutopartes()){
														System.out.print("    "+autoparte.toString());
													}
															
												}
											}
											
											System.out.print("\n********EN REPARACION********");
											for(Reparacion reparacion : reparacionesG)
											{				
												
												if(reparacion.getEntregado()==0)
												{
													//System.out.print("\n"+reparacion.toString());
													System.out.print("\nID: "+reparacion.getId()+" - FECHA I: "+reparacion.getFechainicio()+" NOMBRE CLIENTE: "+reparacion.getCliente().getNombre());
												}
											}
											Thread.sleep(2000);
											System.out.print("\n\n");
											
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
									case 3:
										System.out.print("\n***AUTOPARTES***");
										Filtro filtro=new Filtro();
										Aceite aceite=new Aceite();
										Lampara lampara=new Lampara();
										
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Filtro)
											{
												System.out.print("\n***FILTROS***");
												filtro=(Filtro)autoparte;
												System.out.print("\n"+filtro.toString());
											}											
										}
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Aceite)
											{
												System.out.print("\n***ACEITES***");
												aceite=(Aceite)autoparte;
												System.out.print("\n"+aceite.toString());
											}
										}
										for (Autoparte autoparte : autopartesG)
										{										
											if(autoparte instanceof Lampara)
											{
												System.out.print("\n***LAMPARAS***");
												lampara=(Lampara)autoparte;
												System.out.print("\n"+lampara.toString());
											}
										}
										Thread.sleep(2000);
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
	//#############################################################################################################
	//################################### MENU ADMINISTRADOR ####################################################
	public class MenuAdministrador
	{	
		
		JPanel panelOp;
		JPanel panelSalir;
		JPanel panel;
		JTextField fieldNameUser, fieldApodoUser,fieldPassUser,fieldMailUser,fieldJerarquiaUser,fieldLogginUser;
		JFrame frameAdmin;
		JButton  btCrearUsuario,btModificarUsuario,btBajaUsuario,btMostrarUsuarios;
		JButton btAtras;		
		JButton btSalir;

		public JFrame getFrameAdmin() 
		{
			return frameAdmin;
		}

		public void setFrameAdmin(JFrame frameAdmin) 
		{
			this.frameAdmin = frameAdmin;
		}

		public MenuAdministrador(JFrame frame,final List<Usuario> usuarios)
		{				
			try
			{	
				metgral=new MetodosGrl();				
						
				this.setFrameAdmin(frame);		
				this.getFrameAdmin().getContentPane().removeAll();
				this.getFrameAdmin().getContentPane().repaint();
				
				panel=new JPanel();					
		        panel =(JPanel) this.getFrameAdmin().getContentPane();
		        panel.removeAll();
		        panel.setBackground(Color.cyan);
		        panel.setLayout(new BorderLayout());
		        
		        panelOp = new JPanel();
		        panelOp.setBackground(Color.black);
		        panelOp.setLayout(new GridLayout(6, 1));
		        panelOp.setBorder(new EmptyBorder(4, 4, 4, 4));
		        panelOp.removeAll();
		        
		        panelSalir = new JPanel();
		        panelSalir.setLayout(new GridLayout(2, 1));
		        panelSalir.setBorder(new EmptyBorder(4, 4, 4, 4));
		        panelSalir.setBackground(Color.blue);
		        panelSalir.removeAll();
		        
			}catch(MiException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw new MiException("[MENU ADMINISTRADOR] Exception : "+e);
			}
	        try
			{
	        	
		        ///################### CREAR USER #############
		        btCrearUsuario=new JButton();
				btCrearUsuario.setText("Crear Usuario");
				btCrearUsuario.addMouseListener(new MouseAdapter() {
								
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	try
							{
			            		getFrameAdmin().getContentPane().removeAll();
			    				getFrameAdmin().getContentPane().repaint();
			    				
			    				JPanel panelCrearUser=new JPanel();					
								panelCrearUser =(JPanel) getFrameAdmin().getContentPane();
								panelCrearUser.removeAll();
								panelCrearUser.setBackground(Color.lightGray);
								panelCrearUser.setLayout(new BorderLayout());
								
								JPanel panelGridUser=new JPanel();			
								panelGridUser.setLayout(new GridLayout(6, 2));
								panelGridUser.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUser.setBackground(Color.pink);
								panelGridUser.removeAll();
											
								JPanel panelGridUserEnd = new JPanel();
								panelGridUserEnd.setLayout(new GridLayout(3, 1));
								panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUserEnd.setBackground(Color.blue);
								panelGridUserEnd.removeAll();
								
								final JTextArea areaName=new JTextArea();
								areaName.setBorder(Definiciones.line_blackline);
								JTextField fieldName=new JTextField("Nombre");
								final JTextArea areaEmail=new JTextArea();
								areaEmail.setBorder(Definiciones.line_blackline);
								JTextField fieldEmail=new JTextField("Email");
								final JTextArea areaUserName=new JTextArea();
								areaUserName.setBorder(Definiciones.line_blackline);
								JTextField fieldUserName=new JTextField("Nombre de Usuario");
								final JTextArea areaPassword=new JTextArea();
								areaPassword.setBorder(Definiciones.line_blackline);
								JTextField fieldPassword=new JTextField("Contraseña");
								final JTextArea areaJerarquia=new JTextArea();
								areaJerarquia.setBorder(Definiciones.line_blackline);
								JTextField fieldJerarquia=new JTextField("Jerarquia");
																								
								panelGridUser.add(areaName);
								panelGridUser.add(fieldName);
								
								panelGridUser.add(areaEmail);
								panelGridUser.add(fieldEmail);
								
								panelGridUser.add(areaUserName);
								panelGridUser.add(fieldUserName);
								
								panelGridUser.add(areaPassword);
								panelGridUser.add(fieldPassword);
								
								panelGridUser.add(areaJerarquia);
								panelGridUser.add(fieldJerarquia);
								
								JButton btSubmitCreate=new JButton();
								btSubmitCreate.setText("SUBMIT");								
								btSubmitCreate.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	Usuario userCreate=new Usuario();
					            	
					            	userCreate.setName(areaName.getText());							            	
									userCreate.setEmail(areaEmail.getText());							
									userCreate.setUsername(areaUserName.getText());							
									userCreate.setPassword(areaPassword.getText());							
									userCreate.setJerarquia(areaJerarquia.getText());
									userCreate.setLogueado(0);
									
					            	try {
					            		userCreate.setId(sqlselects.buscarUltimoUsuarioId());			//podria buscar en la lista, pero ...
					            		if(userCreate.getName().equals("")||userCreate.getPassword().equals("")||userCreate.getUsername().equals(""))
					            		{
					            			System.out.print("\nDATOS VACIOS");				//DESPUES HACER CUADROS INFORMATIVOS
					            			Thread.sleep(2000);
					            			MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
											menuAdministrador.mostrar();
					            		}else
					            		{
											if(sqlinserts.insertarUsuario(userCreate))
											{
												System.out.print("\nUSUARIO CREADO CORRECTAMENTE");
												Thread.sleep(2000);							
												usuarios.add(userCreate);
												MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
												menuAdministrador.mostrar();
											}else
											{
												System.out.print("\nFALLO CREACION USUARIO");
												Thread.sleep(2000);
											}
					            		}
									} catch (MiException e) {
											throw e;
									} catch (SQLException e) {
											throw new MiException("[CREATE USER] SQL EXCEPTION : "+e);
									} catch (InterruptedException e) {
										throw new MiException("[CREATE USER] INTERRUPTED EXCEPTION : "+e);
						            } catch (Exception e) {
										throw new MiException("[CREATE USER]EXCEPTION : "+e);												
									}
					            }
							});
									
								JButton btVolver=new JButton();
								btVolver.setText("VOLVER");								
								btVolver.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
									menuAdministrador.mostrar();					            	
					            }
								});
								
								JButton btSalir=new JButton();
								btSalir.setText("SALIR");								
								btSalir.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	System.exit(0);					            	
					            }
								});
								
								//panelGridUser.add(btSubmitCreate);
								panelCrearUser.add("North",panelGridUser);
								panelGridUserEnd.add(btSubmitCreate);
								panelGridUserEnd.add(btVolver);
								panelGridUserEnd.add(btSalir);
								panelCrearUser.add("South",panelGridUserEnd);
								
								getFrameAdmin().setContentPane(panelCrearUser);
							}
							catch(MiException e)
							{							
								throw e;
							}
							catch(Exception e)
							{							
								throw new MiException("[btCrearUsuario]CREATE USER EXCEPTION: "+e);
							}
			            }
			        });
					panelOp.add(btCrearUsuario);
					
					///###################### MODIFICAR USER ##########################
					btModificarUsuario=new JButton();
					btModificarUsuario.setText("Modificar Usuario");
					btModificarUsuario.addMouseListener(new MouseAdapter() {
						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	try
							{
			            		getFrameAdmin().getContentPane().removeAll();
			    				getFrameAdmin().getContentPane().repaint();
			    				
			    				JPanel panelModificarUser=new JPanel();					
			    				panelModificarUser =(JPanel) getFrameAdmin().getContentPane();			    				
			    				panelModificarUser.setBackground(Color.lightGray);
			    				panelModificarUser.setLayout(new BorderLayout());
								
								JPanel panelGridUserEnd = new JPanel();
								panelGridUserEnd.setLayout(new GridLayout(3, 1));
								panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUserEnd.setBackground(Color.blue);
								
								JPanel panelGridModif=new JPanel();			
								panelGridModif.setLayout(new GridLayout(1,2));
								panelGridModif.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridModif.setBackground(Color.pink);
								
								final JTextArea areaToSeek=new JTextArea();
								JTextField fieldToSeek=new JTextField("INGRESE APODO USUARIO A MODIFICAR");
																	
								panelGridModif.add(areaToSeek);
								panelGridModif.add(fieldToSeek);
									
								JButton btSubmitModif=new JButton();
								btSubmitModif.setText("SUBMIT");								
								btSubmitModif.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	try
						            	{
							            	if(areaToSeek.getText().equals(""))
							            	{
							            		System.out.print("[SUBMIT MODIFICAR] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
							            		Thread.sleep(2000);
							            		return;
							            	}else
							            	{
							            		final Usuario usuarioModif=metgral.buscarUsuarioPorApodo(areaToSeek.getText(),usuarios);
							            		if(usuarioModif==null)
												{
													System.out.print("\nNO SE ENCONTRO EL USUARIO");
													Thread.sleep(2000);
													return;
												}else
												{
													getFrameAdmin().getContentPane().removeAll();
								    				getFrameAdmin().getContentPane().repaint();
								    				
								    				JPanel panelModificacion=new JPanel();					
								    				panelModificacion =(JPanel) getFrameAdmin().getContentPane();
								    				panelModificacion.removeAll();
								    				panelModificacion.setBackground(Color.lightGray);
								    				panelModificacion.setLayout(new BorderLayout());
								    				
													final JPanel panelGridModifUser=new JPanel();			
													panelGridModifUser.setLayout(new GridLayout(5,2));
													panelGridModifUser.setBorder(new EmptyBorder(4, 4, 4, 4));
													panelGridModifUser.setBackground(Color.pink);				
													
													final JPanel panelGridUserEnd = new JPanel();
													panelGridUserEnd.setLayout(new GridLayout(3, 1));
													panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
													panelGridUserEnd.setBackground(Color.blue);
													
													
													final JTextArea areaName=new JTextArea();
													areaName.setBorder(Definiciones.line_blackline);
													JTextField fieldName=new JTextField("Nombre ("+usuarioModif.getName()+")");
													final JTextArea areaEmail=new JTextArea();
													areaEmail.setBorder(Definiciones.line_blackline);
													JTextField fieldEmail=new JTextField("Email ("+usuarioModif.getEmail()+")");
													final JTextArea areaUserName=new JTextArea();
													areaUserName.setBorder(Definiciones.line_blackline);
													JTextField fieldUserName=new JTextField("Nombre de Usuario ("+usuarioModif.getUsername()+")");
													final JTextArea areaPassword=new JTextArea();
													areaPassword.setBorder(Definiciones.line_blackline);
													JTextField fieldPassword=new JTextField("Contraseña ("+usuarioModif.getPassword()+")");
													final JTextArea areaJerarquia=new JTextArea();
													areaJerarquia.setBorder(Definiciones.line_blackline);
													JTextField fieldJerarquia=new JTextField("Jerarquia ("+usuarioModif.getJerarquia()+")");
																													
													panelGridModifUser.add(areaName);
													panelGridModifUser.add(fieldName);
													
													panelGridModifUser.add(areaEmail);
													panelGridModifUser.add(fieldEmail);
													
													panelGridModifUser.add(areaUserName);
													panelGridModifUser.add(fieldUserName);
													
													panelGridModifUser.add(areaPassword);
													panelGridModifUser.add(fieldPassword);
													
													panelGridModifUser.add(areaJerarquia);
													panelGridModifUser.add(fieldJerarquia);
													
													final JButton btSubmitModificar=new JButton();
													btSubmitModificar.setText("SUBMIT");								
													btSubmitModificar.addMouseListener(new MouseAdapter() {												
														@Override
														public void mouseReleased(MouseEvent evt) {
															try
															{
											            		//PODRIA MEJORARSE PREGUNTANDO SI DESEA MODIFICAR
																//TODO: AGREGAR JERARQUIAS DE USUARIO
																Usuario userAux=new Usuario();		
																userAux=usuarioModif;
																userAux.setName(areaName.getText());							            	
																userAux.setEmail(areaEmail.getText());							
																userAux.setUsername(areaUserName.getText());							
																userAux.setPassword(areaPassword.getText());							
																userAux.setJerarquia(areaJerarquia.getText());
																userAux.setLogueado(0);
																userAux.setId(usuarioModif.getId());
																
																if(sqlmodif.updateUsuario(userAux))
																{
																	System.out.print("\nUSUARIO MODIFICADO CORRECTAMENTE");
																	Thread.sleep(2000);
																	int i=0;
																	for(Usuario user:usuarios)
																	{
																		if(usuarioModif.getName().equals(user.getName()))
																		{
																			break;
																		}
																		i++;
																	}
																	usuarios.remove(i);								
																	//usuarios=metgral.eliminarUsuarioDeLista(usuarios,usuarioModif);										
																	usuarios.add(userAux);
																	
																	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
																	menuAdministrador.mostrar();
																	
																}else
																{
																	System.out.print("\nFALLO MODIFICACION USUARIO");
																	Thread.sleep(2000);
																	return;
																}
															}catch(MiException e)
															{
																throw e;
															}catch(Exception e)
															{
																throw new MiException("[MODIFICAR USER] EXCEPTION : "+e);
															}
										            	
										            	}
													});
													JButton btVolver=new JButton();
													btVolver.setText("VOLVER");								
													btVolver.addMouseListener(new MouseAdapter() {												
										            @Override
										            public void mouseReleased(MouseEvent evt) {
										            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
														menuAdministrador.mostrar();				            	
										            }
													});
													
													JButton btSalir=new JButton();
													btSalir.setText("SALIR");								
													btSalir.addMouseListener(new MouseAdapter() {												
										            @Override
										            public void mouseReleased(MouseEvent evt) {
										            	System.exit(0);					            	
										            }
													});
													
													panelModificacion.add("North",panelGridModifUser);
													panelGridUserEnd.add(btSubmitModificar);
													panelGridUserEnd.add(btVolver);
													panelGridUserEnd.add(btSalir);
													panelModificacion.add("South",panelGridUserEnd);												
													getFrameAdmin().setContentPane(panelModificacion);
													
													
													
												}
											
											}
						            	}
						            	catch(MiException e)
						            	{
						            		throw e;
						            	}catch(Exception e)
						            	{
						            		throw new MiException("[BAJA USUARIO] MI EXCEPTION : "+e);
						            	} 
						            }
									});
									JButton btVolver=new JButton();
									btVolver.setText("VOLVER");								
									btVolver.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
										menuAdministrador.mostrar();				            	
						            }
									});
									
									JButton btSalir=new JButton();
									btSalir.setText("SALIR");								
									btSalir.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	System.exit(0);					            	
						            }
									});
									
									//panelGridUser.add(btSubmitCreate);
									panelModificarUser.add("North",panelGridModif);
									panelGridUserEnd.add(btSubmitModif);
									panelGridUserEnd.add(btVolver);
									panelGridUserEnd.add(btSalir);
									panelModificarUser.add("South",panelGridUserEnd);
									
									getFrameAdmin().setContentPane(panelModificarUser);
								}
								catch(MiException e)
								{							
									throw e;
								}
								catch(Exception e)
								{							
									throw new MiException("[btCrearUsuario]CREATE USER EXCEPTION: "+e);
								}			            				            	
			            }
			        });
					panelOp.add(btModificarUsuario);
					
					///###################### ELIMINAR USER ##########################
					btBajaUsuario=new JButton();
					btBajaUsuario.setText("Baja Usuario");
					btBajaUsuario.addMouseListener(new MouseAdapter() {				 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            									
			            		try
								{
				            		getFrameAdmin().getContentPane().removeAll();
				    				getFrameAdmin().getContentPane().repaint();
				    				
				    				JPanel panelBajaUser=new JPanel();					
				    				panelBajaUser =(JPanel) getFrameAdmin().getContentPane();
				    				panelBajaUser.removeAll();
				    				panelBajaUser.setBackground(Color.lightGray);
				    				panelBajaUser.setLayout(new BorderLayout());
									
									JPanel panelGridUserEnd = new JPanel();
									panelGridUserEnd.setLayout(new GridLayout(3, 1));
									panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
									panelGridUserEnd.setBackground(Color.blue);
									panelGridUserEnd.removeAll();
									
									
									JPanel panelGridSeek=new JPanel();			
									panelGridSeek.setLayout(new GridLayout(1,2));
									panelGridSeek.setBorder(new EmptyBorder(4, 4, 4, 4));
									panelGridSeek.setBackground(Color.pink);
									panelGridSeek.removeAll();
									
									final JTextArea areaToSeek=new JTextArea();
									areaToSeek.setBorder(Definiciones.line_blackline);
									JTextField fieldToSeek=new JTextField("INGRESE APODO USUARIO A DAR DE BAJA");
																		
									panelGridSeek.add(areaToSeek);
									panelGridSeek.add(fieldToSeek);
										
									JButton btSubmitBaja=new JButton();
									btSubmitBaja.setText("SUBMIT");								
									btSubmitBaja.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	try
						            	{
							            	if(areaToSeek.getText().equals(""))
							            	{
							            		System.out.print("[SUBMIT BAJA] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
							            		Thread.sleep(2000);
							            		return;
							            	}else
							            	{
							            		Usuario usuarioBaja=metgral.buscarUsuarioPorApodo(areaToSeek.getText(),usuarios);
							            		if(usuarioBaja==null)
												{
													System.out.print("\nNO SE ENCONTRO USER");
													return;
												}
							            		//SE PUEDE AGREGAR PREGUNTA DE SI ES EL USUARIO BUSCADO
							            		if(sqldelete.eliminarUsuario(usuarioBaja))
												{
													System.out.print("\nUSUARIO ELIMINADO CORRECTAMENTE");		//DEBUG AGREGAR VENTANA EMERG.
													Thread.sleep(2000);
													int i=0;
													for(Usuario user:usuarios)
													{
														if(usuarioBaja.getName().equals(user.getName()))
														{
															break;
														}
														i++;
													}
													usuarios.remove(i);												
													//usuarios=metgral.eliminarUsuarioDeLista(usuarios,usuarioBaja);						
													MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
													menuAdministrador.mostrar();
												}else
												{
													System.out.print("\nFALLO BAJA USUARIO");
													Thread.sleep(2000);
												}
							            	}
						            	}
						            	catch(MiException e)
						            	{
						            		throw e;
						            	}catch(Exception e)
						            	{
						            		throw new MiException("[BAJA USUARIO] MI EXCEPTION : "+e);
						            	} 
						            }
									});
									JButton btVolver=new JButton();
									btVolver.setText("VOLVER");								
									btVolver.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
										menuAdministrador.mostrar();				            	
						            }
									});
									
									JButton btSalir=new JButton();
									btSalir.setText("SALIR");								
									btSalir.addMouseListener(new MouseAdapter() {												
						            @Override
						            public void mouseReleased(MouseEvent evt) {
						            	System.exit(0);					            	
						            }
									});
									
									//panelGridUser.add(btSubmitCreate);
									panelBajaUser.add("North",panelGridSeek);
									panelGridUserEnd.add(btSubmitBaja);
									panelGridUserEnd.add(btVolver);
									panelGridUserEnd.add(btSalir);
									panelBajaUser.add("South",panelGridUserEnd);
									
									getFrameAdmin().setContentPane(panelBajaUser);
								}
								catch(MiException e)
								{							
									throw e;
								}
								catch(Exception e)
								{							
									throw new MiException("[btCrearUsuario]CREATE USER EXCEPTION: "+e);
								}			            				            	
			            }
			        });
					panelOp.add(btBajaUsuario);
						
					///###################### MOSTRAR USER ##########################
					btMostrarUsuarios=new JButton();
					btMostrarUsuarios.setText("Mostrar Usuarios");
					btMostrarUsuarios.addMouseListener(new MouseAdapter() {				 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	try
							{
			            		getFrameAdmin().getContentPane().removeAll();
			    				getFrameAdmin().getContentPane().repaint();
			    				
			    				JPanel panelCrearUser=new JPanel();					
								panelCrearUser =(JPanel) getFrameAdmin().getContentPane();
								panelCrearUser.removeAll();
								panelCrearUser.setBackground(Color.lightGray);
								panelCrearUser.setLayout(new BorderLayout());
								
								JPanel panelGridUserEnd = new JPanel();
								panelGridUserEnd.setLayout(new GridLayout(2, 1));
								panelGridUserEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUserEnd.setBackground(Color.blue);
								panelGridUserEnd.removeAll();
								
								int cantUser=0;
								for(Usuario usuario : usuarios)
								{
									cantUser++;
									System.out.print("\n"+usuario.toString());
								}
								
								JPanel panelGridUser=new JPanel();			
								panelGridUser.setLayout(new GridLayout(cantUser,1));
								panelGridUser.setBorder(new EmptyBorder(4, 4, 4, 4));
								panelGridUser.setBackground(Color.pink);
								panelGridUser.removeAll();
											
								for(Usuario usuario : usuarios)
								{
									JTextField fieldUser=new JTextField();
									fieldUser.setText(usuario.toString());
									panelGridUser.add(fieldUser);
								}
								
									
								JButton btVolver=new JButton();
								btVolver.setText("VOLVER");								
								btVolver.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	MenuAdministrador menuAdministrador=new MenuAdministrador(getFrameAdmin(),usuarios);
									menuAdministrador.mostrar();				            	
					            }
								});
								
								JButton btSalir=new JButton();
								btSalir.setText("SALIR");								
								btSalir.addMouseListener(new MouseAdapter() {												
					            @Override
					            public void mouseReleased(MouseEvent evt) {
					            	System.exit(0);					            	
					            }
								});
								
								//panelGridUser.add(btSubmitCreate);
								panelCrearUser.add("North",panelGridUser);
								
								panelGridUserEnd.add(btVolver);
								panelGridUserEnd.add(btSalir);
								panelCrearUser.add("South",panelGridUserEnd);
								
								getFrameAdmin().setContentPane(panelCrearUser);
							}
							catch(MiException e)
							{							
								throw e;
							}
							catch(Exception e)
							{							
								throw new MiException("[btCrearUsuario]CREATE USER EXCEPTION: "+e);
							}
			            }
			        });
					panelOp.add(btMostrarUsuarios);					

					/////##################### VOLVER ####################
					btAtras=new JButton();
					btAtras.setText("VOLVER");			
					btAtras.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	
			            	MenuInicio menuInicio=new MenuInicio(getFrameAdmin(),usuarios);
			            	menuInicio.mostrar();
			            }
			        });	
					
			        /////###################### SALIR ###################
					btSalir=new JButton();
					btSalir.setText("SALIR");			
					btSalir.addMouseListener(new MouseAdapter() {
						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			                System.exit(0);
			            }
			        });					
					panelSalir.add(btAtras);
					panelSalir.add(btSalir);
					
					panel.add("South",panelSalir);
					//panelOperaciones.add(btSalir);
					panel.add("North",panelOp);
					
			}catch(MiException e)
			{							
				throw e;
			}
			catch(Exception e)
			{							
				throw new MiException("[MENU ADMINISTRADOR]ADMINISTRADOR EXCEPTION: "+e);
			}	
			return;
		}

		public void mostrar() 
		{
			  
	           this.getFrameAdmin().setVisible(true);
				  
       }
		
	}
	
////////######################################### MENU INICIO ##################################################################
	public class MenuInicio 
	{
		
		JButton  btModoTecnico,btModoSistema, btSalir;
		JFrame frameInicio;

		public void setFrameInicio(JFrame frameInicio)
		{
			this.frameInicio=frameInicio;
		}
		public JFrame getFrameInicio()
		{
			return this.frameInicio;
		}
		public MenuInicio(final JFrame frame,final List<Usuario> usuariosL)
		{
			
			this.setFrameInicio(frame);
			
			JPanel panelOperaciones;					
			JTextField fieldModo=new JTextField();					
			this.getFrameInicio().getContentPane().removeAll();			

			JPanel panel = (JPanel)this.getFrameInicio().getContentPane();
	        panel.setLayout(new BorderLayout());	        
	        panel.setBackground(Color.cyan);
	        
	        panelOperaciones = new JPanel();
	        panelOperaciones.setLayout(new GridLayout(4, 1));
	        panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
	        panelOperaciones.setBackground(Color.cyan);
	        
				try
				{										
					
					fieldModo.setText("MODO DE USO");
					panel.add("North",fieldModo);
					
					//###################################### BOTON TECNICO	//##########################
					btModoTecnico=new JButton();
					btModoTecnico.setText("MODO TECNICO");			
					//btModoTecnico.addActionListener(this);
					btModoTecnico.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.out.print("\n MODO TECNICO");
							
							//if(menuTecnico.validarSenha(frame,usuariosL))
							
			            	ValidarSenha validarSenha=new ValidarSenha(frame,usuariosL);
			            	validarSenha.mostrar();
							
								//menuInicio.empezarMenuTecnico(usuariosL);
								//MenuAdministrador menuAdmin= new MenuAdministrador(frame,usuariosL); 
								//menuAdmin.mostrar();
								                
			            }
			        });
					panelOperaciones.add(btModoTecnico);
					
					//########################################## BOTON SISTEMA ################################
					btModoSistema=new JButton();
					btModoSistema.setText("MODO SISTEMA");			
					//btModoSistema.addActionListener(this);
					btModoSistema.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			            	System.out.print("\nMODO SISTEMA");
							Usuario usuarioLogueado=menuTecnico.empezarLogginUsuario(usuariosL);
							
							if(usuarioLogueado==null)
							{							
							}else
							{
								try 
								{
									if(menuprinc.empezarMenu(usuarioLogueado)=="salir")
									{							
									}
								}catch(MiException  e) {
									System.out.print("\nEXCEPTION SISTEMA : "+e);
									throw e;
								}
								catch(Exception  e) {
									System.out.print("\nEXCEPTION GENERAL SISTEMA : "+e);
									throw new MiException("\nEXCEPTION GENERAL SISTEMA : "+e);
								}
							}   
			            }
			        });
					
					panelOperaciones.add(btModoSistema);
					panel.add("Center",panelOperaciones);

					//################################################### BOTON SALIR ####################
					btSalir=new JButton();
					btSalir.setText("SALIR");			
					//btSalir.addActionListener(this);
					btSalir.addMouseListener(new MouseAdapter() {						 
			            @Override
			            public void mouseReleased(MouseEvent evt) {
			        		System.out.print("\nSALIR");							
							System.exit(0);
			            }
			        });
					
					
					panel.add("South",btSalir);			
					
					
				}catch(MiException e)
				{
					throw e;
				}
				catch(Exception e)
				{							
					throw new MiException("[menuTipoSistema]MENU TIPO SISTEMA EXCEPTION: "+e);
				}
			
		}	
	   public void mostrar() 
	   {
		  
           this.getFrameInicio().setVisible(true);
			  
       }

        
	}
	
	public class MenuTecnico 
	{		
		JFrame frameTecnico;
		
		public MenuTecnico()
		{
			
		}
		public JFrame getFrameTecnico() {
			return frameTecnico;
		}

		public void setFrameTecnico(JFrame frameTecnico) {
			this.frameTecnico = frameTecnico;
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
								if(usuario.getUsername().equals(user)&&usuario.getPassword().equals(pass))
								{
									usuarioLoggin=usuario;
									System.out.print("\nUSUARIO LOGUEADO");
									Thread.sleep(1000);
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
		
		
	}
	
	//############### VALIDAR SENHA ####################################################################################
	public class ValidarSenha	
	{
		JFrame frameSenha;
		public void setFrameSenha(JFrame frameSenha)
		{
			this.frameSenha=frameSenha;
		}
		public JFrame getFrameSenha()
		{
			return this.frameSenha;
		}
		public ValidarSenha()
		{
			
		}
		public ValidarSenha(final JFrame frame,final List<Usuario> usuariosL)throws MiException
		{
			try
			{
				this.setFrameSenha(frame);
									
				//frame.getContentPane().removeAll();			
				this.getFrameSenha().getContentPane().removeAll();
				this.getFrameSenha().getContentPane().repaint();
				
				JPanel panel = (JPanel)this.getFrameSenha().getContentPane();
		        panel.setLayout(new BorderLayout());	        
		        panel.setBackground(Color.cyan);
		        
		        JPanel panelOperaciones = new JPanel();
		        panelOperaciones.setLayout(new GridLayout(1, 2));
		        panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
		        panelOperaciones.setBackground(Color.cyan);
		        
		        JPanel panelEnd = new JPanel();
		        panelEnd.setLayout(new GridLayout(3, 1));
		        panelEnd.setBorder(new EmptyBorder(4, 4, 4, 4));
		        panelEnd.setBackground(Color.cyan);
		        
		        JTextField fieldTitulo=new JTextField("VALIDACION DE CLAVE");
		        
		        JTextField fieldPass=new JTextField("PASS TECNICA: ");
		        final JTextArea areaPass=new JTextArea("",3,8);
		        areaPass.setBorder(Definiciones.line_blackline);
		        
		        ///############### SUBMIT ####################################
		        JButton btSubmitSenha=new JButton();
		        btSubmitSenha.setText("SUBMIT");								
		        btSubmitSenha.addMouseListener(new MouseAdapter() {												
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	try
		            	{
		            		if((!areaPass.getText().equals(""))&&(Integer.valueOf(areaPass.getText())==Definiciones.PASS_TECNICO))
							{
		            			System.out.print("\nCONTRASEÑA CORRECTA");
		            			MenuAdministrador menuAdministrador=new MenuAdministrador(frame,usuariosL);
		            			menuAdministrador.mostrar();
							}else
							{
								System.out.print("\nCONTRASEÑA INCORRECTA");
								MenuInicio menuInicio=new MenuInicio(getFrameSenha(),usuariosL);
				            	menuInicio.mostrar();
							}
		            	}
		            	catch(MiException e)
		            	{
		            		throw e;
		            	}catch(Exception e)
		            	{
		            		throw new MiException("[VALIDAR TECNICO] MI EXCEPTION : "+e);
		            	} 
		            }
				});
				
		    	/////##################### VOLVER ####################
				JButton btAtras=new JButton();
				btAtras.setText("VOLVER");			
				btAtras.addMouseListener(new MouseAdapter() {						 
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	
		            	MenuInicio menuInicio=new MenuInicio(frame,usuariosL);
		            	menuInicio.mostrar();
		            }
		        });	
		        
		    	//################################ SALIR ################### BOTON SALIR
				JButton btSalir=new JButton();
				btSalir.setText("SALIR");			
				//btSalir.addActionListener(this);
				btSalir.addMouseListener(new MouseAdapter() {						 
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		        		System.out.print("\nSALIR");					
						System.exit(0);
		            }
		        });
				
				JPanel panelNorth = new JPanel();
				panelNorth.setLayout(new GridLayout(2, 1));
				fieldTitulo.setEnabled(false);
				panelNorth.setAlignmentX(Component.CENTER_ALIGNMENT);				
				panelNorth.add(fieldTitulo);				
				
				panelOperaciones.setMaximumSize(new Dimension(400,50));				
				
				fieldPass.setEnabled(false);
				fieldPass.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelOperaciones.add(fieldPass);
				panelOperaciones.add(areaPass);					
				
				panel.add("North",panelNorth);
				
				JPanel panelBox = new JPanel();
		        panelBox.setLayout(new BoxLayout(panelBox,BoxLayout.Y_AXIS));	        
		        panelBox.setBackground(Color.GREEN);
		       
		        panelBox.add(panelOperaciones);
		        panelBox.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				panel.add("Center",panelBox);
			
				
				panelEnd.add(btSubmitSenha);
				panelEnd.add(btAtras);
				panelEnd.add(btSalir);
				
				panel.add("South",panelEnd);				
				
				
				//getFrameSenha().setContentPane(panel);
			}catch(MiException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw new MiException("[VALIDAR SENHA] EXCEPTION : "+e);
			}
		}
		public void mostrar()
		{
			this.getFrameSenha().setVisible(true);
		}

	}
}